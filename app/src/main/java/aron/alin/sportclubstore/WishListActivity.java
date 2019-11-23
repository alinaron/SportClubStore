package aron.alin.sportclubstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.CosCumparaturiAdapter;
import aron.alin.sportclubstore.Clase.ProdusRecycler;
import aron.alin.sportclubstore.Clase.Utils;
import aron.alin.sportclubstore.Clase.WishListAdapter;

public class WishListActivity extends AppCompatActivity implements Utils {
    private Integer idClient;
    private RecyclerView recyclerView;
    private WishListAdapter adapter;
    private List<ProdusRecycler> list = new ArrayList<>();
    private List<Integer> listaProd = new ArrayList<>();
    private BackgroundWorker backgroundWorker;
    private Button btnElimina;
    private TextView tvNuExista;
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            idClient = bundle.getInt(KEY_WISH);
        }
        tvNuExista = findViewById(R.id.wish_list_mesaj);
        recyclerView = findViewById(R.id.wishlist_recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        btnElimina = findViewById(R.id.wishlist_btn_sterge);
        backgroundWorker = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    JSONArray rezultat = object.getJSONArray("rezultat");
                    listaProd.clear();
                    if (rezultat != null) {
                        for (int i = 0; i < rezultat.length(); i++) {
                            Integer idProdus = Integer.parseInt(rezultat.getJSONObject(i).getString("id_favorit"));
                            listaProd.add(idProdus);
                        }
                    }
                    if (listaProd.size() == 0) {
                        tvNuExista.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (final Integer idProd : listaProd) {
                    BackgroundWorker bwProdus = new BackgroundWorker() {
                        String denumire, poza;
                        Double pret, pretNou;

                        @Override
                        protected void onPostExecute(String s) {
                            try {
                                JSONObject prod = new JSONObject(s);
                                JSONObject rezultat = prod.getJSONObject("rezultat");
                                denumire = rezultat.getString("denumire_produs");
                                poza = rezultat.getString("poza");
                                pret = Double.parseDouble(rezultat.getString("pret"));
                                pretNou = 0.95 * pret;
                                list.add(new ProdusRecycler(idProd, denumire, poza, pretNou, idClient));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter = new WishListAdapter(WishListActivity.this, list);
                            recyclerView.setAdapter(adapter);
                        }
                    };
                    bwProdus.execute("getInfoProdus", idProd.toString());
                }
            }
        };
        backgroundWorker.execute("getWishlist", idClient.toString());
        btnElimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundWorker = new BackgroundWorker(){
                    @Override
                    protected void onPostExecute(String s) {
                        Toast.makeText(getApplicationContext(), "Ați eliminat cu succes toate produsele din lista de dorințe", Toast.LENGTH_LONG).show();
                    }
                };
                backgroundWorker.execute("deleteWishlist", idClient.toString());
                finish();
            }
        });
    }
}
