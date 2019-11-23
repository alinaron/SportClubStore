package aron.alin.sportclubstore;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import aron.alin.sportclubstore.Clase.Competitie;
import aron.alin.sportclubstore.Clase.CustomAdapterCompetitii;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static aron.alin.sportclubstore.Clase.Utils.KEY_BUNDLE;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompetitiiFragment extends Fragment {

    private List<Competitie> lista = new ArrayList<>();
    private TextView tvTitlu;
    private AlertDialog dialog;
    private BackgroundWorker backgroundWorker;
    private ListView listView;
    private Integer idClient, idSport;
    private String judet, oras, strada;

    public CompetitiiFragment() {
    }


    @SuppressLint("StaticFieldLeak")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_competitii, container, false);
        tvTitlu = view.findViewById(R.id.competitii_tv_titlu);
        listView = view.findViewById(R.id.lv_competitii);
        idClient =  getArguments().getInt(KEY_BUNDLE);

        backgroundWorker = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    int success = object.getInt("success");
                    if(success==1){
                        JSONObject rezultat = object.getJSONObject("result");
                        idSport = Integer.parseInt(rezultat.getString("id_sport"));
                        BackgroundWorker bwCompetitii = new BackgroundWorker(){
                            @Override
                            protected void onPostExecute(String s) {
                                try {
                                    JSONObject objectCompetitii = new JSONObject(s);
                                    JSONArray arrayCompetitii = objectCompetitii.getJSONArray("rezultat");
                                    if(arrayCompetitii!=null){
                                        lista.clear();
                                        for(int i=0;i<arrayCompetitii.length();i++){
                                            Integer idComp, idAdres;
                                            String den, desc, dat, poza;
                                            idComp = Integer.parseInt(arrayCompetitii.getJSONObject(i).getString("id_competitie"));
                                            idAdres = Integer.parseInt(arrayCompetitii.getJSONObject(i).getString("id_adresa"));
                                            den = arrayCompetitii.getJSONObject(i).getString("denumire");
                                            desc = arrayCompetitii.getJSONObject(i).getString("descriere");
                                            dat = arrayCompetitii.getJSONObject(i).getString("data");
                                            poza = arrayCompetitii.getJSONObject(i).getString("poza");
                                            lista.add(new Competitie(idComp, idSport, den, desc, dat, idAdres, poza));
                                        }
                                        if(lista.size()==0)
                                            tvTitlu.setText("Ne cerem scuze! Nu s-a găsit nicio competiție asociată sportului tău...");
                                        else
                                            tvTitlu.setText("Competiții disponibile:");
                                        CustomAdapterCompetitii adapterCompetitii = new CustomAdapterCompetitii(getContext(), R.layout.lv_competitii_layout,lista,getLayoutInflater());
                                        listView.setAdapter(adapterCompetitii);
                                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                final Competitie competitie = lista.get(position);
                                                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                                                View layout = inflater.inflate(R.layout.competitie_layout, null);
                                                ImageView ivPoza = layout.findViewById(R.id.competitie_poza);
                                                final TextView tvDenumire, tvDescriere, tvData, tvAdresa;
                                                Button btnAdauga;
                                                tvDenumire = layout.findViewById(R.id.competitie_denumire_locatie);
                                                tvDescriere = layout.findViewById(R.id.competitie_descriere_locatie);
                                                tvData = layout.findViewById(R.id.competitie_data);
                                                tvAdresa = layout.findViewById(R.id.competitie_adresa);
                                                btnAdauga = layout.findViewById(R.id.competitie_adauga);
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
                                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                                builder.setView(layout);
                                                dialog = builder.create();
                                                btnAdauga.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        BackgroundWorker bwInsert = new BackgroundWorker(){
                                                            @Override
                                                            protected void onPostExecute(String s) {
                                                                try {
                                                                    JSONObject objInsert = new JSONObject(s);
                                                                    int success = objInsert.getInt("success");
                                                                    if(success == 0){
                                                                        Toast.makeText(getContext(), "Competiția se află deja în lista de favorite!", Toast.LENGTH_LONG).show();
                                                                    }
                                                                    else if(success==1){
                                                                        Toast.makeText(getContext(), "Competiția a fost adaugată în lista de favorite!", Toast.LENGTH_LONG).show();
                                                                    }
                                                                } catch (JSONException e) {
                                                                    e.printStackTrace();
                                                                }
                                                            }
                                                        };
                                                        bwInsert.execute("insertFavorite", idClient.toString(), "c", competitie.getIdCompetitie().toString());
                                                    }
                                                });
                                                dialog.show();
                                            }
                                        });
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        bwCompetitii.execute("getCompetitii", idSport.toString());
                    }
                    else if(success==0){
                        Toast.makeText(getContext(), "S-a produs o eroare!", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundWorker.execute("mainpagecomunity", idClient.toString());
        return view;
    }

}
