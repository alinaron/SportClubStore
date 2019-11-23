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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.Categorie;
import aron.alin.sportclubstore.Clase.CategorieAdapter;
import aron.alin.sportclubstore.Clase.CosCumparaturiAdapter;
import aron.alin.sportclubstore.Clase.ListaProduseAdapter;
import aron.alin.sportclubstore.Clase.ProdusRecycler;
import aron.alin.sportclubstore.Clase.Utils;

public class CosCumparaturiActivity extends AppCompatActivity implements Utils {

    private Integer idClient;
    private RecyclerView recyclerView;
    private CosCumparaturiAdapter cosCumparaturiAdapter;
    private List<Integer> listProd = new ArrayList<>();
    private List<ProdusRecycler> list = new ArrayList<>();
    private BackgroundWorker backgroundWorker;
    private Button btnElimina, btnPlaseaza;
    private TextView tvTotal, tvNuExista;
    Double total = 0.0;
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cos_cumparaturi);
        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null)
        {
            idClient = bundle.getInt(KEY_COS);
        }

        tvNuExista = findViewById(R.id.cos_cumparaturi_niciun_produs);
        tvTotal = findViewById(R.id.cos_cumparaturi_total);
        recyclerView = findViewById(R.id.cos_cumparatri_recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        btnPlaseaza = findViewById(R.id.cos_cumparaturi_continua_comanda);
        backgroundWorker = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    list.clear();
                    JSONArray array = object.getJSONArray("rezultat");
                    if(array!=null){
                        for(int i=0;i<array.length();i++){
                            Integer idProdus = Integer.parseInt(array.getJSONObject(i).getString("id_produs"));
                            listProd.add(idProdus);
                        }
                    }
                    if(listProd.size() == 0) {
                        tvNuExista.setVisibility(View.VISIBLE);
                        tvTotal.setVisibility(View.GONE);
                        btnPlaseaza.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "Coșul de cumpărături nu conține niciun produs!", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for(final Integer idProd: listProd){
                    BackgroundWorker bwProdus = new BackgroundWorker(){
                        String denumire, poza;
                        Double pret;
                        @Override
                        protected void onPostExecute(String s) {
                            try {
                                JSONObject prod = new JSONObject(s);
                                JSONObject rezultat = prod.getJSONObject("rezultat");
                                denumire = rezultat.getString("denumire_produs");
                                poza = rezultat.getString("poza");
                                pret = Double.parseDouble(rezultat.getString("pret"));
                                total+=pret;
                                list.add(new ProdusRecycler(idProd, denumire, poza, pret, idClient));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            tvTotal.setText("Total: " + String.format("%.2f", total)  + " lei");
                            cosCumparaturiAdapter = new CosCumparaturiAdapter(getApplicationContext(), list);
                            recyclerView.setAdapter(cosCumparaturiAdapter);
                            btnPlaseaza.setOnClickListener(doPlaseaza(list));
                        }
                    };
                    bwProdus.execute("getInfoProdus", idProd.toString());

                }

            }
        };
        backgroundWorker.execute("getCos", idClient.toString());
        btnElimina = findViewById(R.id.cos_cumparaturi_elimina);
        btnElimina.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {
                backgroundWorker = new BackgroundWorker(){
                    @Override
                    protected void onPostExecute(String s) {
                        Toast.makeText(getApplicationContext(), "Ai eliminat cu succes toate produsele din coșul de cumpărături", Toast.LENGTH_LONG).show();
                    }
                };
                backgroundWorker.execute("deleteAllCos", idClient.toString());
                finish();
            }
        });
    }
    private View.OnClickListener doPlaseaza(final List<ProdusRecycler> lista){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlasareComandaActivity.class);
                intent.putExtra(KEY_PLASEAZA_COMANDA_CLIENT, idClient);
                intent.putExtra(KEY_PLASEAZA_COMANDA_LISTA, (Serializable)lista);
                startActivity(intent);
                finish();
            }
        };
    }
}

