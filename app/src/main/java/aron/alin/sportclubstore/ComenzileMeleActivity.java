package aron.alin.sportclubstore;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.Comanda;
import aron.alin.sportclubstore.Clase.ComandaAdapter;
import aron.alin.sportclubstore.Clase.CustomAdapterComenzi;
import aron.alin.sportclubstore.Clase.ProdusRecycler;
import aron.alin.sportclubstore.Clase.Utils;

public class ComenzileMeleActivity extends AppCompatActivity implements Utils {
    private Integer idClient;
    private List<Comanda> list = new ArrayList<>();
    private ListView lvComenzi;
    private List<ProdusRecycler> listaProduse = new ArrayList<>();
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comenzile_mele);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            idClient = bundle.getInt(KEY_COMENZI);
        }
        lvComenzi = findViewById(R.id.comenzile_mele_lista);
        BackgroundWorker backgroundWorker = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    JSONArray rezultat = object.getJSONArray("rezultat");
                    if(rezultat!=null){
                        list.clear();
                        for(int i=0;i<rezultat.length();i++){
                            Integer idComanda = Integer.parseInt(rezultat.getJSONObject(i).getString("nr_comanda"));
                            String modalitatePlata = rezultat.getJSONObject(i).getString("modalitate_plata");
                            Double valoare = Double.parseDouble(rezultat.getJSONObject(i).getString("valoare"));
                            list.add(new Comanda(idComanda, valoare, modalitatePlata));
                        }
                        CustomAdapterComenzi adapterComenzi = new CustomAdapterComenzi(getApplicationContext(), R.layout.lv_comenzile_mele_layout, list, getLayoutInflater());
                        lvComenzi.setAdapter(adapterComenzi);
                        lvComenzi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                final Comanda comanda = list.get(position);
                                BackgroundWorker bwProduse = new BackgroundWorker(){
                                    @Override
                                    protected void onPostExecute(String s) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(s);
                                            JSONArray array = jsonObject.getJSONArray("rezultat");
                                            listaProduse.clear();
                                            if(array!=null){
                                                for(int i=0;i<array.length();i++) {
                                                    JSONObject produs = array.getJSONObject(i);
                                                    Integer idProdus = Integer.parseInt(produs.getString("id_produs"));
                                                    String denumire = produs.getString("denumire_produs");
                                                    String poza = produs.getString("poza");
                                                    Double pret = Double.parseDouble(produs.getString("pret"));
                                                    listaProduse.add(new ProdusRecycler(idProdus, denumire, poza, pret, idClient));
                                                }
                                                Intent myIntent = new Intent(getApplicationContext(), ComenziProduseActivity.class);
                                                myIntent.putExtra(KEY_PRODUSE_COMENZI, (Serializable) listaProduse);
                                                myIntent.putExtra(KEY_NR_COMANDA, comanda.getNrComanda());
                                                startActivity(myIntent);
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                bwProduse.execute("getMyProducts", comanda.getNrComanda().toString());
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundWorker.execute("getComenzi", idClient.toString());
    }
}
