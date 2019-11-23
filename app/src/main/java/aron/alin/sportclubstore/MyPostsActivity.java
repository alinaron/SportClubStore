package aron.alin.sportclubstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.Postare;
import aron.alin.sportclubstore.Clase.PostareAdapter;
import aron.alin.sportclubstore.Clase.Utils;

public class MyPostsActivity extends AppCompatActivity implements Utils {

    private Integer idClient;
    private RecyclerView recyclerView;
    private PostareAdapter postareAdapter;
    private List<Postare> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posts);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            idClient = bundle.getInt(KEY_MY_POSTS);
        }
        recyclerView = findViewById(R.id.my_posts_recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        @SuppressLint("StaticFieldLeak") BackgroundWorker backgroundWorker = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    lista.clear();
                    JSONArray array = object.getJSONArray("rezultat");
                    if(array!=null) {
                        for (int i = 0; i < array.length(); i++) {
                            Integer idPostare = Integer.parseInt(array.getJSONObject(i).getString("id_postare"));
                            Integer idCl = Integer.parseInt(array.getJSONObject(i).getString("id_client"));
                            String continut = array.getJSONObject(i).getString("continut");
                            String poza = array.getJSONObject(i).getString("poza");
                            lista.add(new Postare(idPostare, continut, poza, idCl));
                        }
                        postareAdapter = new PostareAdapter(getApplicationContext(), lista, idClient);
                        recyclerView.setAdapter(postareAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundWorker.execute("getMyPosts", idClient.toString());
    }
}
