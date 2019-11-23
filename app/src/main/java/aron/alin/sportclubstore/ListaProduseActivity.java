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
import aron.alin.sportclubstore.Clase.Categorie;
import aron.alin.sportclubstore.Clase.CategorieAdapter;
import aron.alin.sportclubstore.Clase.ListaProduseAdapter;
import aron.alin.sportclubstore.Clase.ProdusRecycler;
import aron.alin.sportclubstore.Clase.Utils;

public class ListaProduseActivity extends AppCompatActivity implements Utils {

    private Integer idCategorie;
    private Integer idClient;
    private TextView tvTitlu;
    private RecyclerView recyclerView;
    private ListaProduseAdapter adapter;
    private List<ProdusRecycler> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produse);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            idCategorie = bundle.getInt(KEY_LISTA_PRODUSE);
            idClient = bundle.getInt(KEY_CLIENT);
        }
        tvTitlu = findViewById(R.id.lista_produse_titlu);
        recyclerView = findViewById(R.id.lista_produse_recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        if(idCategorie == 0) {
            @SuppressLint("StaticFieldLeak") BackgroundWorker backgroundWorker = new BackgroundWorker(){
                @Override
                protected void onPostExecute(String s) {
                    try {
                        JSONObject object = new JSONObject(s);
                        int success = object.getInt("success");
                        if(success == 1) {
                            JSONObject rezultat = object.getJSONObject("result");
                            idCategorie = Integer.parseInt(rezultat.getString("id_sport"));
                        }
                        showTitle(idCategorie);
                        showMe();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            backgroundWorker.execute("mainpagecomunity", idClient.toString());
        }
        else{
            showTitle(idCategorie);
            showMe();
        }
    }

    private void showTitle(Integer id){
        @SuppressLint("StaticFieldLeak") BackgroundWorker bwTitlu = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    JSONObject rezultat = object.getJSONObject("result");
                    String denumire = rezultat.getString("denumire_categorie");
                    tvTitlu.setText("Produse "+ denumire.toLowerCase());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        bwTitlu.execute("getCategorie", id.toString());
    }
    private void showMe(){
        @SuppressLint("StaticFieldLeak") BackgroundWorker bw = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    list.clear();
                    JSONArray array = object.getJSONArray("rezultat");
                    if(array!=null){
                        for(int i=0;i<array.length();i++){
                            Integer idProdus = Integer.parseInt(array.getJSONObject(i).getString("id_produs"));
                            String denumire = array.getJSONObject(i).getString("denumire_produs");
                            String poza = array.getJSONObject(i).getString("poza");
                            Double pret = Double.parseDouble(array.getJSONObject(i).getString("pret"));
                            list.add(new ProdusRecycler(idProdus, denumire, poza, pret, idClient));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new ListaProduseAdapter(getApplicationContext(), list);
                recyclerView.setAdapter(adapter);
            }
        };
        bw.execute("getProduseCategorii", idCategorie.toString());
    }
}
