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
import aron.alin.sportclubstore.Clase.CustomAdapterLocatii;
import aron.alin.sportclubstore.Clase.Locatie;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static aron.alin.sportclubstore.Clase.Utils.KEY_BUNDLE;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocatiiFragment extends Fragment {

    private List<Locatie> lista = new ArrayList<>();
    private BackgroundWorker backgroundWorker;
    private ListView listView;
    private TextView tvTitlu;
    private Integer idClient, idSport, idAdresa;
    private String judet;
    private AlertDialog dialog;
    private String jud, oras, strada;

    public LocatiiFragment() {
    }


    @SuppressLint("StaticFieldLeak")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_locatii, container, false);
        tvTitlu = view.findViewById(R.id.locatii_tv_titlu);
        listView = view.findViewById(R.id.locatii_lv);

        idClient =  getArguments().getInt(KEY_BUNDLE);
        backgroundWorker = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    int success = object.getInt("success");
                    if(success == 1){
                        JSONObject rezultat = object.getJSONObject("result");
                        idSport = Integer.parseInt(rezultat.getString("id_sport"));
                        idAdresa = Integer.parseInt(rezultat.getString("id_adresa"));
                        BackgroundWorker bwAdresa = new BackgroundWorker(){
                            @Override
                            protected void onPostExecute(String s) {
                                try {
                                    JSONObject jsonO = new JSONObject(s);
                                    final JSONObject obAdr = jsonO.getJSONObject("rezultat");
                                    judet = obAdr.getString("judet");
                                    BackgroundWorker bwLocatii = new BackgroundWorker(){
                                        @Override
                                        protected void onPostExecute(String s) {
                                            try {
                                                JSONObject objLocatii = new JSONObject(s);
                                                JSONArray locRezultat = objLocatii.getJSONArray("rezultat");
                                                if(locRezultat!=null){
                                                    lista.clear();
                                                    for(int i=0;i<locRezultat.length();i++){
                                                        Integer idLoc = Integer.parseInt(locRezultat.getJSONObject(i).getString("id_locatie"));
                                                        Integer idSpo = Integer.parseInt(locRezultat.getJSONObject(i).getString("id_sport"));
                                                        String denLoc = locRezultat.getJSONObject(i).getString("denumire_locatie");
                                                        String tar = locRezultat.getJSONObject(i).getString("tarif");
                                                        String poz = locRezultat.getJSONObject(i).getString("poza");
                                                        Double tarif = null;
                                                        if (!tar.equals("null"))
                                                            tarif = Double.parseDouble(tar);
                                                        String descLoc = locRezultat.getJSONObject(i).getString("descriere");
                                                        Integer idAdresa = Integer.parseInt(locRezultat.getJSONObject(i).getString("id_adresa"));
                                                        lista.add(new Locatie(idLoc, idSpo, denLoc, tarif, descLoc, idAdresa, poz));
                                                    }
                                                    if(lista.size()==0)
                                                        tvTitlu.setText("Ne cerem scuze! Nu s-a gasit nicio locație pentru sportul tău în "+ judet);
                                                    else
                                                        tvTitlu.setText("Poți practica sportul tău preferat în următoarele locații din "+judet+":");
                                                    CustomAdapterLocatii adapter = new CustomAdapterLocatii(getContext(), R.layout.lv_locatii_layout, lista, getLayoutInflater());
                                                    listView.setAdapter(adapter);

                                                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                        @Override
                                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                            final Locatie locatie = lista.get(position);
                                                            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                                                            View layout = inflater.inflate(R.layout.locatie_layout, null);
                                                            ImageView ivPoza = layout.findViewById(R.id.locatie_poza);
                                                            final TextView tvDenumire, tvDescriere, tvTarif, tvAdresa;
                                                            Button btnAdauga;
                                                            tvDenumire = layout.findViewById(R.id.locatie_denumire_locatie);
                                                            tvDescriere = layout.findViewById(R.id.locatie_descriere_locatie);
                                                            tvTarif = layout.findViewById(R.id.locatie_tarif);
                                                            tvAdresa = layout.findViewById(R.id.locatie_adresa);
                                                            btnAdauga = layout.findViewById(R.id.locatie_adauga);
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
                                                                                    Toast.makeText(getContext(), "Locația se află deja în lista de favorite!", Toast.LENGTH_LONG).show();
                                                                                }
                                                                                else if(success==1){
                                                                                    Toast.makeText(getContext(), "Locația a fost adaugată în lista de favorite!", Toast.LENGTH_LONG).show();
                                                                                }
                                                                            } catch (JSONException e) {
                                                                                e.printStackTrace();
                                                                            }
                                                                        }
                                                                    };
                                                                    bwInsert.execute("insertFavorite", idClient.toString(), "l", locatie.getIdLocatie().toString());
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
                                    bwLocatii.execute("getLocatii", idSport.toString(), judet);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        bwAdresa.execute("getAdresaClient", idAdresa.toString());
                    }
                    else {
                        Toast.makeText(getActivity(), "S-a produs o eroare! Încercați mai târziu!", Toast.LENGTH_LONG).show();
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
