package aron.alin.sportclubstore;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.CustomAdapterLocatii;
import aron.alin.sportclubstore.Clase.Locatie;
import aron.alin.sportclubstore.Clase.Utils;

public class LocatiiFavoriteActivity extends AppCompatActivity implements Utils {

    private TextView tvNimic;
    private ListView listView;
    private Integer idClient;
    private List<Integer> listaId = new ArrayList<>();
    private List<Locatie> lista = new ArrayList<>();
    private AlertDialog dialog;
    private String jud, oras, strada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locatii_favorite);
        tvNimic = findViewById(R.id.locatii_favorite_nimic);
        listView = findViewById(R.id.locatii_favorite_lista);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            idClient = bundle.getInt(KEY_LOCATII_FAVORITE);
        }

        @SuppressLint("StaticFieldLeak") BackgroundWorker backgroundWorker = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    JSONArray rezultat = object.getJSONArray("rezultat");
                    listaId.clear();
                    if(rezultat!=null){
                        for(int i=0; i<rezultat.length();i++){
                            Integer idLocatie = Integer.parseInt(rezultat.getJSONObject(i).getString("id_favorit"));
                            listaId.add(idLocatie);
                        }
                        if(listaId.size()==0){
                            tvNimic.setVisibility(View.VISIBLE);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for(Integer idLocatie: listaId){
                    BackgroundWorker bwLocatie = new BackgroundWorker(){
                        @Override
                        protected void onPostExecute(String s) {
                            try {
                                JSONObject object = new JSONObject(s);
                                JSONObject rezultat = object.getJSONObject("rezultat");
                                Integer idLoc = Integer.parseInt(rezultat.getString("id_locatie"));
                                Integer idSpo = Integer.parseInt(rezultat.getString("id_sport"));
                                String denLoc = rezultat.getString("denumire_locatie");
                                String tar = rezultat.getString("tarif");
                                String poz = rezultat.getString("poza");
                                Double tarif = null;
                                if (!tar.equals("null"))
                                    tarif = Double.parseDouble(tar);
                                String descLoc = rezultat.getString("descriere");
                                Integer idAdresa = Integer.parseInt(rezultat.getString("id_adresa"));
                                lista.add(new Locatie(idLoc, idSpo, denLoc, tarif, descLoc, idAdresa, poz));
                                CustomAdapterLocatii adapter = new CustomAdapterLocatii(getApplicationContext(), R.layout.lv_locatii_layout, lista, getLayoutInflater());
                                listView.setAdapter(adapter);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        final Locatie locatie = lista.get(position);
                                        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                                        View layout = inflater.inflate(R.layout.locatie_layout, null);
                                        ImageView ivPoza = layout.findViewById(R.id.locatie_poza);
                                        final TextView tvDenumire, tvDescriere, tvTarif, tvAdresa;
                                        Button btnAdauga;
                                        tvDenumire = layout.findViewById(R.id.locatie_denumire_locatie);
                                        tvDescriere = layout.findViewById(R.id.locatie_descriere_locatie);
                                        tvTarif = layout.findViewById(R.id.locatie_tarif);
                                        tvAdresa = layout.findViewById(R.id.locatie_adresa);
                                        btnAdauga = layout.findViewById(R.id.locatie_adauga);
                                        btnAdauga.setVisibility(View.GONE);
                                        tvDenumire.setText(locatie.getDenumireLocatie());
                                        tvDescriere.setText(locatie.getDescriereLocatie());
                                        if(locatie.getTarif() == null)
                                            tvTarif.setVisibility(View.GONE);
                                        else tvTarif.setText(locatie.getTarif().toString()+" lei");
                                        Picasso.get().load(locatie.getPoza())
                                                .into(ivPoza);
                                        BackgroundWorker bwAdresa = new BackgroundWorker(){
                                            @Override
                                            protected void onPostExecute(String s) {
                                                try {
                                                    JSONObject objAdresa = new JSONObject(s);
                                                    JSONObject objRezultat = objAdresa.getJSONObject("rezultat");
                                                    jud = objRezultat.getString("judet");
                                                    oras = objRezultat.getString("oras");
                                                    strada = objRezultat.getString("strada");
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                                if (oras.substring(oras.length()-1,oras.length()).equals(" "))
                                                    oras = oras.substring(0, oras.length()-1);
                                                tvAdresa.setText(jud+", "+oras+", "+strada);
                                            }
                                        };
                                        bwAdresa.execute("getAdresaClient", locatie.getIdAdresa().toString());
                                        AlertDialog.Builder builder = new AlertDialog.Builder(LocatiiFavoriteActivity.this);
                                        builder.setView(layout);
                                        dialog = builder.create();
                                        dialog.show();
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    bwLocatie.execute("getLocatie", idLocatie.toString());
                }
            }
        };
        backgroundWorker.execute("getFavorite", idClient.toString(), "l");
    }
}
