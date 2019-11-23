package aron.alin.sportclubstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.Comentariu;
import aron.alin.sportclubstore.Clase.ComentriuAdapter;
import aron.alin.sportclubstore.Clase.ListaProduseAdapter;
import aron.alin.sportclubstore.Clase.ProdusRecycler;
import aron.alin.sportclubstore.Clase.Utils;

public class ViewPostActivity extends AppCompatActivity implements Utils {

    private Integer idPostare, idClient;
    private ImageView ivPozaProfil, ivPoza;
    private TextView tvNume, tvContinut;
    private EditText etComentariu;
    private Button btnAdaugaComm;
    private RecyclerView recyclerView;
    private ComentriuAdapter adapter;
    private List<Comentariu> lista = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            idPostare = bundle.getInt(KEY_POSTARE);
            idClient = bundle.getInt(KEY_CLIENT_POSTARE);
        }
        ivPozaProfil = findViewById(R.id.view_postare_poza_profil);
        ivPoza = findViewById(R.id.view_postare_poza);
        tvNume = findViewById(R.id.view_postare_tv_username);
        tvContinut = findViewById(R.id.view_postare_tv_continut);
        etComentariu = findViewById(R.id.etComentariu);
        btnAdaugaComm = findViewById(R.id.btnAddComment);
        recyclerView = findViewById(R.id.view_post_recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnAdaugaComm.setOnClickListener(doAddComment());
        @SuppressLint("StaticFieldLeak") BackgroundWorker backgroundWorker = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    JSONObject rezultat = object.getJSONObject("result");
                    String pozaPost = rezultat.getString("poza");
                    String continut = rezultat.getString("continut");
                    if(!pozaPost.equals("null")) {
                        byte[] pozik = Base64.decode(pozaPost, Base64.DEFAULT);
                        Bitmap bitmapPost = BitmapFactory.decodeByteArray(pozik, 0, pozik.length);
                        ivPoza.setImageBitmap(bitmapPost);
                    }
                    else{
                        ivPoza.setVisibility(View.GONE);
                    }
                    if(!continut.equals("")){
                        tvContinut.setText(continut);
                    }
                    else {
                        tvContinut.setVisibility(View.GONE);
                    }
                    Integer idClient = Integer.parseInt(rezultat.getString("id_client"));
                    BackgroundWorker bwClient = new BackgroundWorker(){
                        @Override
                        protected void onPostExecute(String s) {
                            try {
                                JSONObject object = new JSONObject(s);
                                int success = object.getInt("success");
                                if(success == 1) {
                                    JSONObject rezultat = object.getJSONObject("result");
                                    String poza = rezultat.getString("poza");
                                    String username = rezultat.getString("username");
                                    byte[] poz = Base64.decode(poza,Base64.DEFAULT);
                                    Bitmap bitmap = BitmapFactory.decodeByteArray(poz,0, poz.length);
                                    tvNume.setText(username);
                                    ivPozaProfil.setImageBitmap(bitmap);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    bwClient.execute("mainpagecomunity", idClient.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundWorker.execute("getPostare", idPostare.toString());
        @SuppressLint("StaticFieldLeak") BackgroundWorker bwComentarii = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    lista.clear();
                    JSONArray array = object.getJSONArray("rezultat");
                    if(array!=null){
                        for(int i=0;i<array.length();i++){
                            Integer idComentariu = Integer.parseInt(array.getJSONObject(i).getString("id_comentariu"));
                            Integer idPostare = Integer.parseInt(array.getJSONObject(i).getString("id_postare"));
                            Integer idClient = Integer.parseInt(array.getJSONObject(i).getString("id_client"));
                            String content = array.getJSONObject(i).getString("continut");
                            lista.add(new Comentariu(idComentariu, idPostare, content, idClient));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new ComentriuAdapter(getApplicationContext(), lista);
                recyclerView.setAdapter(adapter);
            }
        };
        bwComentarii.execute("getComentarii", idPostare.toString());
    }

    public View.OnClickListener doAddComment(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etComentariu.getText().toString().isEmpty() || etComentariu.getText() == null){
                    etComentariu.setError("Introduceți comentariul aici!");
                }
                else {
                    @SuppressLint("StaticFieldLeak") BackgroundWorker bwInsertComm = new BackgroundWorker(){
                        @Override
                        protected void onPostExecute(String s) {
                            try {
                                JSONObject object = new JSONObject(s);
                                int res = object.getInt("success");
                                if (res == 1) {
                                    Toast.makeText(getApplicationContext(), "Comentariu adăugat cu succes!", Toast.LENGTH_LONG).show();
                                    finish();
                                    startActivity(getIntent());
                                } else if (res == 0) {
                                    Toast.makeText(getApplicationContext(), "Eroare la adăugarea comentariului!", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    bwInsertComm.execute("insertComment", idPostare.toString(), etComentariu.getText().toString(), idClient.toString());
                }
            }
        };
    }
}
