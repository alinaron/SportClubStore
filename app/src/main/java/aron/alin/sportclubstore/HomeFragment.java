package aron.alin.sportclubstore;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.PersoanaRecycler;
import aron.alin.sportclubstore.Clase.PersoaneAdapter;
import aron.alin.sportclubstore.Clase.Produs;

import static aron.alin.sportclubstore.Clase.Utils.KEY_BUNDLE;
import static aron.alin.sportclubstore.Clase.Utils.KEY_CLIENT_PRODUS;
import static aron.alin.sportclubstore.Clase.Utils.KEY_CONT;
import static aron.alin.sportclubstore.Clase.Utils.KEY_COS;
import static aron.alin.sportclubstore.Clase.Utils.KEY_LOGIN;
import static aron.alin.sportclubstore.Clase.Utils.KEY_PRODUS;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private TextView tvBineAiVenit, tvPersoane, tvRecomandariProduse;
    private BackgroundWorker backgroundWorker;
    private String prenume, sex, judet;
    private Integer idClient, idSport, idAdresa;
    private ImageView profilePic, imgCont, imgCos;
    private ImageView[] imgProd = new ImageView[8];
    private TextView[] denProd = new TextView[8];
    private TextView[] pretProd = new TextView[8];
    private CardView[] cardViews = new CardView[8];
    private List<Produs> listaProduse = new ArrayList<>();
    private RecyclerView recyclerViewPersoane;
    private PersoaneAdapter persoaneAdapter;
    private List<PersoanaRecycler> listaPoze = new ArrayList<>();
    private String myPhoto;
    private Animation animation, animationReverse;
    public HomeFragment() {
    }


    @SuppressLint("StaticFieldLeak")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tvBineAiVenit = view.findViewById(R.id.main_page_bine_ai_venit);
        tvPersoane = view.findViewById(R.id.main_page_persoane);
        tvRecomandariProduse = view.findViewById(R.id.main_page_recomandari_produse);
        profilePic = view.findViewById(R.id.main_page_cont);
        imgCont = view.findViewById(R.id.main_page_account);
        imgCos = view.findViewById(R.id.main_page_cart);
        recyclerViewPersoane = view.findViewById(R.id.recycler_view_persoane);
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_alpha);
        animationReverse = AnimationUtils.loadAnimation(getContext(), R.anim.anim_alpha_reverse);

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imgCos.getVisibility() == View.INVISIBLE) {
                    imgCont.setVisibility(View.VISIBLE);
                    imgCont.startAnimation(animation);
                    imgCos.setVisibility(View.VISIBLE);
                    imgCos.startAnimation(animation);
                }
                else if (imgCos.getVisibility() == View.VISIBLE){
                    imgCont.setAnimation(animationReverse);
                    imgCont.setVisibility(View.INVISIBLE);
                    imgCos.setAnimation(animationReverse);
                    imgCos.setVisibility(View.INVISIBLE);
                }
            }
        });

        recyclerViewPersoane.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPersoane.setLayoutManager(linearLayoutManager);

        imgProd[0] = view.findViewById(R.id.home_produs1_poza);
        imgProd[1] = view.findViewById(R.id.home_produs2_poza);
        imgProd[2] = view.findViewById(R.id.home_produs3_poza);
        imgProd[3] = view.findViewById(R.id.home_produs4_poza);
        imgProd[4] = view.findViewById(R.id.home_produs5_poza);
        imgProd[5] = view.findViewById(R.id.home_produs6_poza);
        imgProd[6] = view.findViewById(R.id.home_produs7_poza);
        imgProd[7] = view.findViewById(R.id.home_produs8_poza);
        denProd[0] = view.findViewById(R.id.home_produs1_denumire);
        denProd[1] = view.findViewById(R.id.home_produs2_denumire);
        denProd[2] = view.findViewById(R.id.home_produs3_denumire);
        denProd[3] = view.findViewById(R.id.home_produs4_denumire);
        denProd[4] = view.findViewById(R.id.home_produs5_denumire);
        denProd[5] = view.findViewById(R.id.home_produs6_denumire);
        denProd[6] = view.findViewById(R.id.home_produs7_denumire);
        denProd[7] = view.findViewById(R.id.home_produs8_denumire);
        pretProd[0] = view.findViewById(R.id.home_produs1_pret);
        pretProd[1] = view.findViewById(R.id.home_produs2_pret);
        pretProd[2] = view.findViewById(R.id.home_produs3_pret);
        pretProd[3] = view.findViewById(R.id.home_produs4_pret);
        pretProd[4] = view.findViewById(R.id.home_produs5_pret);
        pretProd[5] = view.findViewById(R.id.home_produs6_pret);
        pretProd[6] = view.findViewById(R.id.home_produs7_pret);
        pretProd[7] = view.findViewById(R.id.home_produs8_pret);
        cardViews[0] = view.findViewById(R.id.home_card1);
        cardViews[1] = view.findViewById(R.id.home_card2);
        cardViews[2] = view.findViewById(R.id.home_card3);
        cardViews[3] = view.findViewById(R.id.home_card4);
        cardViews[4] = view.findViewById(R.id.home_card5);
        cardViews[5] = view.findViewById(R.id.home_card6);
        cardViews[6] = view.findViewById(R.id.home_card7);
        cardViews[7] = view.findViewById(R.id.home_card8);

        idClient =  getArguments().getInt(KEY_BUNDLE);
        imgCos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CosCumparaturiActivity.class);
                intent.putExtra(KEY_COS, idClient);
                startActivity(intent);
            }
        });
        imgCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getContext(), MyAccountActivity.class);
                myIntent.putExtra(KEY_CONT, idClient);
                startActivity(myIntent);
            }
        });
        backgroundWorker = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    final JSONObject object = new JSONObject(s);
                    int success = object.getInt("success");
                    if(success == 1){
                        JSONObject rezultat = object.getJSONObject("result");
                        prenume = rezultat.getString("prenume");
                        if(prenume.substring(prenume.length()-1,prenume.length()).equals(" ")) {
                            prenume = prenume.substring(0, prenume.length()-1);
                        }
                        idSport = Integer.parseInt(rezultat.getString("id_sport"));
                        sex = rezultat.getString("sex");
                        idAdresa = Integer.parseInt(rezultat.getString("id_adresa"));
                        tvBineAiVenit.setText("Bine ai venit, "+prenume+"!");
                        BackgroundWorker bwImage = new BackgroundWorker(){
                            @Override
                            protected void onPostExecute(String s) {
                                try {
                                    JSONObject objPozaProfil = new JSONObject(s);
                                    JSONObject objResult = objPozaProfil.getJSONObject("result");
                                    myPhoto = objResult.getString("poza");
                                    byte[] poz = Base64.decode(myPhoto,Base64.DEFAULT);
                                    Bitmap bitmap = BitmapFactory.decodeByteArray(poz,0, poz.length);
                                    profilePic.setImageBitmap(bitmap);
                                    profilePic.setVisibility(View.VISIBLE);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        bwImage.execute("getImage",idClient.toString());
                        BackgroundWorker bwProduse = new BackgroundWorker(){
                            @Override
                            protected void onPostExecute(String s) {
                                try {
                                    JSONObject result = new JSONObject(s);
                                    JSONArray rezArray = result.getJSONArray("rezultat");
                                    if(rezArray!=null){
                                        listaProduse.clear();
                                        for(int i=0;i<rezArray.length();i++) {
                                            Integer idProdus = Integer.parseInt(rezArray.getJSONObject(i).getString("id_produs"));
                                            Integer idCategorie = Integer.parseInt(rezArray.getJSONObject(i).getString("id_categorie"));
                                            String denumire = rezArray.getJSONObject(i).getString("denumire_produs");
                                            String poza = rezArray.getJSONObject(i).getString("poza");
                                            String descriere = rezArray.getJSONObject(i).getString("descriere_produs");
                                            Integer stoc = Integer.parseInt(rezArray.getJSONObject(i).getString("stoc"));
                                            Double pret = Double.parseDouble(rezArray.getJSONObject(i).getString("pret"));
                                            Integer idMarime = Integer.parseInt(rezArray.getJSONObject(i).getString("id_marime"));
                                            listaProduse.add(new Produs(idProdus, idCategorie, denumire, poza, descriere, stoc, pret, idMarime));
                                        }

                                        for(int i=0; i<listaProduse.size();i++){
                                            Picasso.get().load(listaProduse.get(i).getPoza())
                                                        .into(imgProd[i]);
                                            denProd[i].setText(listaProduse.get(i).getDenumireProdus());
                                            pretProd[i].setText(listaProduse.get(i).getPret().toString()+" lei");
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                for(int i=0;i<listaProduse.size();i++){
                                    cardViews[i].setOnClickListener(doProdus(listaProduse.get(i).getIdProdus()));
                                }
                                if(listaProduse.size()<cardViews.length){
                                    for(int i=listaProduse.size();i<cardViews.length;i++)
                                        cardViews[i].setVisibility(View.INVISIBLE);
                                }
                            }
                        };
                        if(sex.equals("M"))
                            bwProduse.execute("getProduseRecomandate",idSport.toString(),"dama");
                        else if (sex.equals("F"))
                            bwProduse.execute("getProduseRecomandate",idSport.toString(),"barbati");
                        BackgroundWorker bwAdresa = new BackgroundWorker(){
                            @Override
                            protected void onPostExecute(String s) {
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    JSONObject rezultat = jsonObject.getJSONObject("rezultat");
                                    judet = rezultat.getString("judet");
                                    BackgroundWorker bwPoze = new BackgroundWorker(){
                                        @Override
                                        protected void onPostExecute(String s) {
                                            try {
                                                JSONObject objPoze = new JSONObject(s);
                                                listaPoze.clear();
                                                JSONArray objRezultat = objPoze.getJSONArray("rezultat");
                                                if(objRezultat != null){
                                                    for(int i=0;i<objRezultat.length();i++){
                                                        Integer idClient = Integer.parseInt(objRezultat.getJSONObject(i).getString("id_client"));
                                                        String poza = objRezultat.getJSONObject(i).getString("poza");
                                                        String username = objRezultat.getJSONObject(i).getString("username");
                                                        String nume = objRezultat.getJSONObject(i).getString("nume");
                                                        String prenume = objRezultat.getJSONObject(i).getString("prenume");
                                                        String oras = objRezultat.getJSONObject(i).getString("oras");
                                                        String judet = objRezultat.getJSONObject(i).getString("judet");
                                                        String bio = objRezultat.getJSONObject(i).getString("bio");
                                                        if(!poza.equals(myPhoto))
                                                            listaPoze.add(new PersoanaRecycler(idClient,poza,username,nume,prenume,oras,judet,bio));
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            persoaneAdapter = new PersoaneAdapter(getContext(), listaPoze, idClient);
                                            recyclerViewPersoane.setAdapter(persoaneAdapter);
                                        }
                                    };
                                    bwPoze.execute("getAllImages", idSport.toString(), judet);
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

    private View.OnClickListener doProdus(final Integer codProdus){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProdus = new Intent(getContext(), ProdusActivity.class);
                intentProdus.putExtra(KEY_PRODUS, codProdus);
                intentProdus.putExtra(KEY_CLIENT_PRODUS, idClient);
                startActivity(intentProdus);
            }
        };
    }

}
