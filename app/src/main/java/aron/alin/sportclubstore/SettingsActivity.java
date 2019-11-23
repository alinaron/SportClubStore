package aron.alin.sportclubstore;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import aron.alin.sportclubstore.Clase.Utils;

public class SettingsActivity extends AppCompatActivity implements Utils {

    private Integer idClient;
    private Intent myIntent;
    private TextView tvModificaDate, tvModificaAdresa, tvStatistici, tvRaport, tvComenzi, tvPostari, tvDelogheaza, tvFavorite;
    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            idClient = bundle.getInt(KEY_SETTINGS);
        }
        tvModificaDate = findViewById(R.id.setari_modifica_date);
        tvModificaAdresa = findViewById(R.id.setari_modifica_adresa);
        tvStatistici = findViewById(R.id.setari_statistici);
        tvRaport = findViewById(R.id.setari_raport_comenzi);
        tvComenzi = findViewById(R.id.setari_comenzile_mele);
        tvPostari = findViewById(R.id.setari_postarile_mele);
        tvDelogheaza = findViewById(R.id.setari_delogheaza);
        tvFavorite = findViewById(R.id.setari_favorite);

        tvDelogheaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
                Toast.makeText(getApplicationContext(), "Te-ai delogat cu succes!", Toast.LENGTH_LONG).show();
            }
        });

        tvStatistici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIntent = new Intent(getApplicationContext(), MyStats.class);
                myIntent.putExtra(KEY_MY_STATS, idClient);
                startActivity(myIntent);
            }
        });

        tvRaport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIntent = new Intent(getApplicationContext(), RaportComenziActivity.class);
                myIntent.putExtra(KEY_RAPORT, idClient);
                startActivity(myIntent);
            }
        });
        tvFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.layout_favorite, null);
                Button btnLocatii, btnCompetitii;
                btnLocatii = layout.findViewById(R.id.favorite_btn_locatii);
                btnCompetitii = layout.findViewById(R.id.favorite_btn_competitii);
                btnLocatii.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myInt = new Intent(getApplicationContext(), LocatiiFavoriteActivity.class);
                        myInt.putExtra(KEY_LOCATII_FAVORITE, idClient);
                        startActivity(myInt);
                        dialog.dismiss();
                    }
                });
                btnCompetitii.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myInt = new Intent(getApplicationContext(), CompetitiiFavoriteActivity.class);
                        myInt.putExtra(KEY_COMPETITII_FAVORITE, idClient);
                        startActivity(myInt);
                        dialog.dismiss();
                    }
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setView(layout);
                dialog = builder.create();
                dialog.show();
            }
        });
        tvComenzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIntent = new Intent(getApplicationContext(), ComenzileMeleActivity.class);
                myIntent.putExtra(KEY_COMENZI, idClient);
                startActivity(myIntent);
            }
        });
        tvPostari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIntent = new Intent(getApplicationContext(), MyPostsActivity.class);
                myIntent.putExtra(KEY_MY_POSTS, idClient);
                startActivity(myIntent);
            }
        });
        tvModificaAdresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIntent = new Intent(getApplicationContext(), ModificaAdresaActivity.class);
                myIntent.putExtra(KEY_MODIFICA_ADRESA, idClient);
                startActivity(myIntent);
            }
        });
        tvModificaDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIntent = new Intent(getApplicationContext(), ModificaDateActivity.class);
                myIntent.putExtra(KEY_MODIFICA_DATE, idClient);
                startActivity(myIntent);
            }
        });
    }
}
