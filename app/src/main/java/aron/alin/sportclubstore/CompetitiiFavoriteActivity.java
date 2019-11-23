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

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.Competitie;
import aron.alin.sportclubstore.Clase.CustomAdapterCompetitii;
import aron.alin.sportclubstore.Clase.CustomAdapterLocatii;
import aron.alin.sportclubstore.Clase.Locatie;
import aron.alin.sportclubstore.Clase.Utils;

public class CompetitiiFavoriteActivity extends AppCompatActivity implements Utils {

    private TextView tvNimic;
    private ListView listView;
    private Integer idClient;
    private List<Integer> listaId = new ArrayList<>();
    private List<Competitie> lista = new ArrayList<>();
    private AlertDialog dialog;
    private String judet, oras, strada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competitii_favorite);
        tvNimic = findViewById(R.id.competitii_favorite_nimic);
        listView = findViewById(R.id.competitii_favorite_lista);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            idClient = bundle.getInt(KEY_COMPETITII_FAVORITE);
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
                            Integer idCompetitie = Integer.parseInt(rezultat.getJSONObject(i).getString("id_favorit"));
                            listaId.add(idCompetitie);
                        }
                        if(listaId.size()==0){
                            tvNimic.setVisibility(View.VISIBLE);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for(Integer idCompetitie: listaId){
                    BackgroundWorker bwLocatie = new BackgroundWorker(){
                        @Override
                        protected void onPostExecute(String s) {
                            try {
                                JSONObject object = new JSONObject(s);
                                JSONObject rezultat = object.getJSONObject("rezultat");
                                Integer idComp, idAdres, idSport;
                                String den, desc, dat, poza;
                                idSport = Integer.parseInt(rezultat.getString("id_sport"));
                                idComp = Integer.parseInt(rezultat.getString("id_competitie"));
                                idAdres = Integer.parseInt(rezultat.getString("id_adresa"));
                                den = rezultat.getString("denumire");
                                desc = rezultat.getString("descriere");
                                dat = rezultat.getString("data");
                                poza = rezultat.getString("poza");
                                lista.add(new Competitie(idComp, idSport, den, desc, dat, idAdres, poza));
                                CustomAdapterCompetitii adapterCompetitii = new CustomAdapterCompetitii(getApplicationContext(), R.layout.lv_competitii_layout,lista,getLayoutInflater());
                                listView.setAdapter(adapterCompetitii);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        final Competitie competitie = lista.get(position);
                                        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                                        View layout = inflater.inflate(R.layout.competitie_layout, null);
                                        ImageView ivPoza = layout.findViewById(R.id.competitie_poza);
                                        final TextView tvDenumire, tvDescriere, tvData, tvAdresa;
                                        Button btnAdauga;
                                        tvDenumire = layout.findViewById(R.id.competitie_denumire_locatie);
                                        tvDescriere = layout.findViewById(R.id.competitie_descriere_locatie);
                                        tvData = layout.findViewById(R.id.competitie_data);
                                        tvAdresa = layout.findViewById(R.id.competitie_adresa);
                                        btnAdauga = layout.findViewById(R.id.competitie_adauga);
                                        btnAdauga.setVisibility(View.GONE);
                                        tvDenumire.setText(competitie.getDenumireCompetitie());
                                        tvData.setText(competitie.getDataCompetitie());
                                        tvDescriere.setText(competitie.getDescriereCompetitie());
                                        Picasso.get().load(competitie.getPoza()).into(ivPoza);
                                        BackgroundWorker bwAdresa = new BackgroundWorker(){
                                            @Override
                                            protected void onPostExecute(String s) {
                                                try {
                                                    JSONObject objAdresa = new JSONObject(s);
                                                    JSONObject objRezultat = objAdresa.getJSONObject("rezultat");
                                                    judet = objRezultat.getString("judet");
                                                    oras = objRezultat.getString("oras");
                                                    strada = objRezultat.getString("strada");
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                                if (oras.substring(oras.length()-1,oras.length()).equals(" "))
                                                    oras = oras.substring(0, oras.length()-1);
                                                tvAdresa.setText(judet+", "+oras+", "+strada);
                                            }
                                        };
                                        bwAdresa.execute("getAdresaClient", competitie.getIdAdresa().toString());
                                        AlertDialog.Builder builder = new AlertDialog.Builder(CompetitiiFavoriteActivity.this);
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
                    bwLocatie.execute("getCompetitie", idCompetitie.toString());
                }
            }
        };
        backgroundWorker.execute("getFavorite", idClient.toString(), "c");
    }
}
