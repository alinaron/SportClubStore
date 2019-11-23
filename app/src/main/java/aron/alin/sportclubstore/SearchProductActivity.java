package aron.alin.sportclubstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.ListaProduseAdapter;
import aron.alin.sportclubstore.Clase.ProdusRecycler;
import aron.alin.sportclubstore.Clase.Utils;

public class SearchProductActivity extends AppCompatActivity implements Utils {

    private Integer idClient;
    private String keyword;
    private List<ProdusRecycler> listaProduse = new ArrayList<>();
    private TextView tvTitlu;
    private RecyclerView recyclerView;
    private ListaProduseAdapter listaProduseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            idClient = bundle.getInt(KEY_CLIENT_PRODUS);
            keyword = bundle.getString(KEY_SEARCH);
        }
        tvTitlu = findViewById(R.id.search_produs_titlu);
        recyclerView = findViewById(R.id.search_produse_recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        tvTitlu.setText("Rezultatele cautarii pentru \"" + keyword + "\":");
        @SuppressLint("StaticFieldLeak") BackgroundWorker backgroundWorker = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    listaProduse.clear();
                    JSONArray array = object.getJSONArray("rezultat");
                    if(array != null){
                        for(int i=0;i<array.length();i++){
                            Integer idProdus = Integer.parseInt(array.getJSONObject(i).getString("id_produs"));
                            String denumire = array.getJSONObject(i).getString("denumire_produs");
                            String poza = array.getJSONObject(i).getString("poza");
                            Double pret = Double.parseDouble(array.getJSONObject(i).getString("pret"));
                            listaProduse.add(new ProdusRecycler(idProdus, denumire, poza, pret, idClient));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(listaProduse.size() == 0){
                    tvTitlu.setText("Nu există niciun rezultat pentru \"" + keyword + "\"!");
                }
                listaProduseAdapter = new ListaProduseAdapter(getApplicationContext(), listaProduse);
                recyclerView.setAdapter(listaProduseAdapter);
            }
        };
        backgroundWorker.execute("getSearch", keyword);
    }
}
