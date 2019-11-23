package aron.alin.sportclubstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.Categorie;
import aron.alin.sportclubstore.Clase.CategorieAdapter;
import aron.alin.sportclubstore.Clase.Postare;
import aron.alin.sportclubstore.Clase.PostareAdapter;
import aron.alin.sportclubstore.Clase.Utils;

public class CategoriiActivity extends AppCompatActivity implements Utils {

    private Integer idClient;
    private RecyclerView recyclerView;
    private CategorieAdapter categorieAdapter;
    private List<Categorie> list = new ArrayList<>();
    private BackgroundWorker backgroundWorker;
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorii);

        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null)
        {
            idClient = bundle.getInt(KEY_CATEGORIE);
        }

        recyclerView = findViewById(R.id.categorii_recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        backgroundWorker = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    list.clear();
                    JSONArray array = object.getJSONArray("rezultat");
                    if(array!=null){
                        for(int i=0;i<array.length();i++){
                            Integer idCategorie = Integer.parseInt(array.getJSONObject(i).getString("id_categorie"));
                            String denumireCategorie = array.getJSONObject(i).getString("denumire_categorie");
                            String poza = array.getJSONObject(i).getString("poza");
                            list.add(new Categorie(idCategorie, denumireCategorie, poza, idClient));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                categorieAdapter = new CategorieAdapter(getApplicationContext(), list);
                recyclerView.setAdapter(categorieAdapter);
            }
        };
        backgroundWorker.execute("getCategorii");
    }
}
