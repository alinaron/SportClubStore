package aron.alin.sportclubstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.Favorit;
import aron.alin.sportclubstore.Clase.Utils;

public class ProdusActivity extends AppCompatActivity implements Utils {

    private ImageView imgProdus, imgCos, imgWishList, imgFavoriteDa, imgFavoriteNu;
    private TextView tvDenumire, tvPret, tvStoc, tvDescriere, tvMarime;
    private Button btnProdus;
    private Intent intent;
    private Integer idProdus, idMarime, idClient, codVerificare;
    private BackgroundWorker backgroundWorker;
    private String poza, denumire, pret, stoc, descriere;
    private Spinner spnMarime;
    private ArrayAdapter<CharSequence> adapter;
    private List<Favorit> listaProduseFavorite = new ArrayList<>();
    private String marimeProdus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produs);
        init();
    }

    @SuppressLint("StaticFieldLeak")
    private void init(){
        imgProdus = findViewById(R.id.produs_img);
        imgCos = findViewById(R.id.produs_img_cos);
        imgWishList = findViewById(R.id.produs_img_wishlist);
        imgFavoriteDa = findViewById(R.id.produs_img_favDa);
        imgFavoriteDa.setVisibility(View.INVISIBLE);
        imgFavoriteNu = findViewById(R.id.produs_img_favNu);
        imgFavoriteNu.setVisibility(View.INVISIBLE);
        tvDenumire = findViewById(R.id.produs_denumire);
        tvPret = findViewById(R.id.produs_pret);
        tvStoc = findViewById(R.id.produs_stoc);
        tvDescriere = findViewById(R.id.produs_descriere);
        tvMarime = findViewById(R.id.produse_marime);
        spnMarime = findViewById(R.id.produse_marime_spinner);
        btnProdus = findViewById(R.id.produs_buton);

        btnProdus.setOnClickListener(doAdd());

        intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            idProdus = bundle.getInt(KEY_PRODUS);
            idClient = bundle.getInt(KEY_CLIENT_PRODUS);
            codVerificare = bundle.getInt(KEY_VERIFICARE_DISCOUNT);
        }
        imgFavoriteDa.setOnClickListener(doDelete(idClient, "p", idProdus));
        imgFavoriteNu.setOnClickListener(doInsert(idClient, "p", idProdus));
        imgCos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), CosCumparaturiActivity.class);
                myIntent.putExtra(KEY_COS, idClient);
                startActivity(myIntent);
            }
        });
        imgWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), WishListActivity.class);
                myIntent.putExtra(KEY_WISH, idClient);
                startActivity(myIntent);
            }
        });
        backgroundWorker = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    JSONObject rezultat = object.getJSONObject("rezultat");
                    poza = rezultat.getString("poza");
                    denumire = rezultat.getString("denumire_produs");
                    pret = rezultat.getString("pret");
                    stoc = rezultat.getString("stoc");
                    descriere = rezultat.getString("descriere_produs");
                    idMarime = Integer.parseInt(rezultat.getString("id_marime"));
                    Picasso.get().load(poza).into(imgProdus);
                    tvDenumire.setText(denumire);
                    tvPret.setText(pret+" lei");
                    tvStoc.setText(stoc+" produse în stoc!");
                    tvDescriere.setText(descriere);
                    if(idMarime == 0){
                        tvMarime.setVisibility(View.INVISIBLE);
                        spnMarime.setVisibility(View.INVISIBLE);
                    }
                    else{
                        if(idMarime == 1)
                            adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.marimi1, R.layout.spinner_layout);
                        else if(idMarime == 3)
                            adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.marimi3, R.layout.spinner_layout);
                        else if(idMarime == 4)
                            adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.marimi4, R.layout.spinner_layout);
                        else if(idMarime == 5)
                            adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.marimi5, R.layout.spinner_layout);
                        spnMarime.setAdapter(adapter);
                    }
                    BackgroundWorker bwProduse = new BackgroundWorker(){
                        @Override
                        protected void onPostExecute(String s) {
                            try {
                                JSONObject objF = new JSONObject(s);
                                listaProduseFavorite.clear();
                                JSONArray objFavorite = objF.getJSONArray("rezultat");
                                if(objFavorite!=null){
                                    for(int i=0;i<objFavorite.length();i++){
                                        Integer idPers = Integer.parseInt(objFavorite.getJSONObject(i).getString("id_client"));
                                        String codF = objFavorite.getJSONObject(i).getString("cod_favorit");
                                        Integer idF = Integer.parseInt(objFavorite.getJSONObject(i).getString("id_favorit"));
                                        listaProduseFavorite.add(new Favorit(idPers, codF, idF));
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            imgFavoriteNu.setVisibility(View.VISIBLE);
                            for(Favorit f: listaProduseFavorite){
                                if(f.getIdClient().equals(idClient) && f.getCodFavorit().equals("p") && f.getIdFavorit().equals(idProdus)){
                                    imgFavoriteDa.setVisibility(View.VISIBLE);
                                    break;
                                }
                            }
                        }
                    };
                    bwProduse.execute("getFavoriteAll", idClient.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundWorker.execute("getInfoProdus", idProdus.toString());
    }

    private View.OnClickListener doAdd(){
        return new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(stoc ) - 1 >= 0) {
                    if(spnMarime.getVisibility() == View.VISIBLE)
                        marimeProdus = spnMarime.getSelectedItem().toString();
                    else
                        marimeProdus = "";

                    backgroundWorker = new BackgroundWorker() {
                        @Override
                        protected void onPostExecute(String s) {
                            if (codVerificare == 1) {
                                BackgroundWorker bwInsertProdus = new BackgroundWorker() {
                                    @Override
                                    protected void onPostExecute(String s) {
                                        try {
                                            JSONObject object = new JSONObject(s);
                                            int res = object.getInt("success");
                                            if (res == 1) {
                                                Toast.makeText(getApplicationContext(), "Produsul a fost adaugat în coș!", Toast.LENGTH_LONG).show();
                                                finish();
                                            } else if (res == 0) {
                                                Toast.makeText(getApplicationContext(), "Produsul nu a putut fi adăugat în coș!", Toast.LENGTH_LONG).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                bwInsertProdus.execute("insertProdus2", idClient.toString(), idProdus.toString(), marimeProdus);
                            }
                            else {
                                BackgroundWorker bwInsertProdus = new BackgroundWorker() {
                                    @Override
                                    protected void onPostExecute(String s) {
                                        try {
                                            JSONObject object = new JSONObject(s);
                                            int res = object.getInt("success");
                                            if (res == 1) {
                                                Toast.makeText(getApplicationContext(), "Produsul a fost adăugat în coș!", Toast.LENGTH_LONG).show();
                                                finish();
                                            } else if (res == 0) {
                                                Toast.makeText(getApplicationContext(), "Produsul nu a putut fi adăugat în coș!", Toast.LENGTH_LONG).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                bwInsertProdus.execute("insertProdus", idClient.toString(), idProdus.toString(), marimeProdus);
                            }
                        }
                    };
                    backgroundWorker.execute("updateStoc", idProdus.toString());
                }
                else Toast.makeText(getApplicationContext(),"Produsul nu se mai află în stoc!", Toast.LENGTH_LONG).show();
            }
        };
    }

    private View.OnClickListener doDelete(final Integer idC, final String codF, final Integer idF){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("StaticFieldLeak") BackgroundWorker bwDelete = new BackgroundWorker(){
                    @Override
                    protected void onPostExecute(String s) {
                        imgFavoriteDa.setVisibility(View.INVISIBLE);
                        imgFavoriteNu.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "Produsul a fost eliminat din WishList!", Toast.LENGTH_LONG).show();
                    }
                };
                bwDelete.execute("deleteFavorite", idC.toString(), codF, idF.toString());
            }
        };
    }

    private View.OnClickListener doInsert(final Integer idC, final String codF, final Integer idF){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("StaticFieldLeak") BackgroundWorker bwInsert = new BackgroundWorker(){
                    @Override
                    protected void onPostExecute(String s) {
                        try {
                            JSONObject object = new JSONObject(s);
                            int success = object.getInt("success");
                            if(success == 1){
                                imgFavoriteNu.setVisibility(View.INVISIBLE);
                                imgFavoriteDa.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), "Produsul a fost adăugat în WishList!", Toast.LENGTH_LONG).show();
                            }
                            else if(success == 0){
                                Toast.makeText(getApplicationContext(),"Produsul se află deja în WishList!",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                bwInsert.execute("insertFavorite", idC.toString(), codF, idF.toString());
            }
        };
    }
}
