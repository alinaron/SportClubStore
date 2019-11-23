package aron.alin.sportclubstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import aron.alin.sportclubstore.Clase.CosCumparaturiAdapter;
import aron.alin.sportclubstore.Clase.UrmaritiAdapter;
import aron.alin.sportclubstore.Clase.Utils;

public class FollowingActivity extends AppCompatActivity implements Utils {

    private Integer idClient;
    private List<Integer> lista;
    private RecyclerView recyclerView;
    private UrmaritiAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            idClient = bundle.getInt(KEY_FOLLOW_USER);
            lista = (List<Integer>) bundle.getSerializable(KEY_LISTA_URMARITORI);
        }
        recyclerView = findViewById(R.id.urmariti_recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new UrmaritiAdapter(FollowingActivity.this, lista, idClient);
        recyclerView.setAdapter(adapter);
    }
}
