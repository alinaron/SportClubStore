package aron.alin.sportclubstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import aron.alin.sportclubstore.Clase.ComandaAdapter;
import aron.alin.sportclubstore.Clase.ProdusRecycler;
import aron.alin.sportclubstore.Clase.Utils;

public class ComenziProduseActivity extends AppCompatActivity implements Utils {

    private List<ProdusRecycler> list;
    private Integer nrComanda;
    private TextView tvTitlu;
    private RecyclerView recyclerView;
    private ComandaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comenzi_produse);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            nrComanda = bundle.getInt(KEY_NR_COMANDA);
            list = (List<ProdusRecycler>) bundle.getSerializable(KEY_PRODUSE_COMENZI);
        }
        tvTitlu = findViewById(R.id.dialog_comenzi_titlu);
        tvTitlu.setText("Comanda #" + String.format("%04d", nrComanda));
        recyclerView = findViewById(R.id.dialog_comenzi_recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ComandaAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(adapter);

    }
}
