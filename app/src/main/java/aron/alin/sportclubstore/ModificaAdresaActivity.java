package aron.alin.sportclubstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.Utils;

public class ModificaAdresaActivity extends AppCompatActivity implements Utils {

    private Integer idClient;
    private Spinner spinnerJudet;
    private EditText etOras, etStrada;
    private Button btnSalveaza, btnAnuleaza;
    private Integer idAdresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_adresa);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            idClient = bundle.getInt(KEY_MODIFICA_ADRESA);
        }
        spinnerJudet = findViewById(R.id.modifica_spinner_judet);
        etOras = findViewById(R.id.modifica_oras_et);
        etStrada = findViewById(R.id.modifica_strada_et);
        btnAnuleaza = findViewById(R.id.modifica_btn_anuleaza_adr);
        btnAnuleaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSalveaza = findViewById(R.id.modifica_btn_salveaza_adr);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.judete,R.layout.spinner_layout);
        spinnerJudet.setAdapter(adapter);
        @SuppressLint("StaticFieldLeak") BackgroundWorker backgroundWorker = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    JSONObject rezultat = object.getJSONObject("result");
                    final Integer idAdresa = Integer.parseInt(rezultat.getString("id_adresa"));
                    BackgroundWorker bwAdresa = new BackgroundWorker(){
                        @Override
                        protected void onPostExecute(String s) {
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                JSONObject rezultat = jsonObject.getJSONObject("rezultat");
                                String judet = rezultat.getString("judet");
                                String oras = rezultat.getString("oras");
                                String strada = rezultat.getString("strada");
                                etOras.setText(oras);
                                etStrada.setText(strada);
                                spinnerJudet.setSelection(adapter.getPosition(judet));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    bwAdresa.execute("getAdresaClient", idAdresa.toString());
                    btnSalveaza.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String judet = spinnerJudet.getSelectedItem().toString();
                            String oras = etOras.getText().toString();
                            String strada = etStrada.getText().toString();
                            BackgroundWorker bwUpdate = new BackgroundWorker(){
                                @Override
                                protected void onPostExecute(String s) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(s);
                                        Integer succes = Integer.parseInt(jsonObject.getString("success"));
                                        if(succes == 1){
                                            Toast.makeText(getApplicationContext(), "Adresa a fost actualizată cu succes!", Toast.LENGTH_LONG).show();
                                            finish();
                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(), "Eroare la actualizarea adresei!", Toast.LENGTH_LONG).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            bwUpdate.execute("updateAdresa", idAdresa.toString(), judet, oras, strada);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundWorker.execute("mainpagecomunity", idClient.toString());
    }
}
