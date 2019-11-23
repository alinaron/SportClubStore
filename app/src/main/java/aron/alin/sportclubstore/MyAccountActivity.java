package aron.alin.sportclubstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.Utils;

public class MyAccountActivity extends AppCompatActivity implements Utils {

    private Integer idClient;
    private FloatingActionButton fbSetari, fbCos, fbWishList;
    private ImageView ivProfil;
    private TextView tvUsername, tvUrmaritori, tvUrmariti, tvNume, tvBio, tvTelefon, tvDataNastere;
    private Integer idAdresa;
    private String nume, prenume, username, telefon, bio, data_nastere, myPhoto;
    private List<Integer> listaUrmaritori, listaUrmariti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            idClient = bundle.getInt(KEY_CONT);
        }
        fbSetari = findViewById(R.id.my_profile_btn_setari);
        fbCos = findViewById(R.id.my_profile_btn_cart);
        fbWishList = findViewById(R.id.my_profile_btn_wishlist);
        ivProfil = findViewById(R.id.my_profile_photo_profil);
        tvUsername = findViewById(R.id.my_profile_tv_username);
        tvUrmariti = findViewById(R.id.my_profile_tv_urmariti);
        tvUrmaritori = findViewById(R.id.my_profile_tv_urmaritori);
        tvNume = findViewById(R.id.my_profile_tv_nume);
        tvBio = findViewById(R.id.my_profile_tv_bio);
        tvTelefon = findViewById(R.id.my_profile_tv_telefon);
        tvDataNastere = findViewById(R.id.my_profile_tv_data_nastere);

        listaUrmaritori = new ArrayList<>();
        listaUrmariti = new ArrayList<>();
        fbSetari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                myIntent.putExtra(KEY_SETTINGS, idClient);
                startActivity(myIntent);
            }
        });
        fbCos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), CosCumparaturiActivity.class);
                myIntent.putExtra(KEY_COS, idClient);
                startActivity(myIntent);
            }
        });
        fbWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), WishListActivity.class);
                myIntent.putExtra(KEY_WISH, idClient);
                startActivity(myIntent);
            }
        });
        @SuppressLint("StaticFieldLeak") BackgroundWorker backgroundWorker = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    int success = object.getInt("success");
                    if(success == 1) {
                        JSONObject rezultat = object.getJSONObject("result");
                        username = rezultat.getString("username");
                        prenume = rezultat.getString("prenume");
                        if (prenume.substring(prenume.length() - 1, prenume.length()).equals(" ")) {
                            prenume = prenume.substring(0, prenume.length() - 1);
                        }
                        nume = rezultat.getString("nume");
                        if (nume.substring(nume.length() - 1, nume.length()).equals(" ")) {
                            nume = nume.substring(0, nume.length() - 1);
                        }
                        telefon = rezultat.getString("telefon");
                        data_nastere = rezultat.getString("data_nastere");
                        idAdresa = Integer.parseInt(rezultat.getString("id_adresa"));
                        bio = rezultat.getString("bio");
                        myPhoto = rezultat.getString("poza");
                        byte[] poz = Base64.decode(myPhoto,Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(poz,0, poz.length);
                        ivProfil.setImageBitmap(bitmap);
                        tvUsername.setText("@" + username + " ");
                        tvNume.setText(prenume + " " + nume);
                        tvTelefon.setText("Telefon: " + telefon);
                        tvDataNastere.setText("Data nașterii: " + data_nastere);
                        tvBio.setText(bio);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundWorker.execute("mainpagecomunity", idClient.toString());
        @SuppressLint("StaticFieldLeak") BackgroundWorker bwUrmaritori = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    JSONArray array = object.getJSONArray("rezultat");
                    listaUrmaritori.clear();
                    if(array !=null){
                        for(int i=0;i<array.length();i++){
                            int idUrmaritor = Integer.parseInt(array.getJSONObject(i).getString("id_client"));
                            listaUrmaritori.add(idUrmaritor);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvUrmaritori.setText(listaUrmaritori.size()+ " urmăritori");
                tvUrmaritori.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(getApplicationContext(), FollowersActivity.class);
                        myIntent.putExtra(KEY_FOLLOW_USER, idClient);
                        myIntent.putExtra(KEY_LISTA_URMARITORI, (Serializable) listaUrmaritori);
                        startActivity(myIntent);
                    }
                });
            }
        };
        bwUrmaritori.execute("getFolloweri", idClient.toString());

        @SuppressLint("StaticFieldLeak") BackgroundWorker bwUrmariti = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    JSONArray array = object.getJSONArray("rezultat");
                    listaUrmariti.clear();
                    if(array !=null){
                        for(int i=0;i<array.length();i++){
                            int idUrmarit = Integer.parseInt(array.getJSONObject(i).getString("id_favorit"));
                            listaUrmariti.add(idUrmarit);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvUrmariti.setText(listaUrmariti.size()+ " urmăriți");
                tvUrmariti.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(getApplicationContext(), FollowingActivity.class);
                        myIntent.putExtra(KEY_FOLLOW_USER, idClient);
                        myIntent.putExtra(KEY_LISTA_URMARITORI, (Serializable) listaUrmariti);
                        startActivity(myIntent);
                    }
                });
            }
        };
        bwUrmariti.execute("getUrmaritori", idClient.toString());
    }
}
