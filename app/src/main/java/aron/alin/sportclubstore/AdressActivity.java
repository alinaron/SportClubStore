package aron.alin.sportclubstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import aron.alin.sportclubstore.Clase.Adresa;
import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.Client;
import aron.alin.sportclubstore.Clase.Utils;

public class AdressActivity extends AppCompatActivity implements Utils {

    private Spinner spinnerJudet;
    private EditText etOras, etStrada;
    private Button btnInsert;
    private Client client;
    private BackgroundWorker backgroundWorker;
    private Integer idAdresa;
    private String judet, oras, strada;
    private Integer idClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress);
        init();
    }

    private void init(){
        spinnerJudet = findViewById(R.id.adress_spinner_judet);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.judete,R.layout.spinner_layout);
        spinnerJudet.setAdapter(adapter);
        etOras = findViewById(R.id.adress_oras_et);
        etStrada = findViewById(R.id.adress_strada_et);
        btnInsert = findViewById(R.id.address_insert_btn);
        btnInsert.setOnClickListener(doInsert());
    }

    private View.OnClickListener doInsert(){
        return new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {
                if (!isValid()) {
                    if (etOras.getText() == null || etOras.getText().toString() == null || etOras.getText().toString().isEmpty())
                        etOras.setError("Introduceți orașul de domiciliu!");
                    else if (etStrada.getText() == null || etStrada.getText().toString() == null || etStrada.getText().toString().isEmpty())
                        etStrada.setError("Introduceți strada!");
                } else {
                    Intent intent = getIntent();
                    client = (Client) intent.getSerializableExtra(KEY_CLIENT);
                    if (client == null)
                        Toast.makeText(getApplicationContext(), "S-a produs o eroare! Repetati procesul!", Toast.LENGTH_LONG).show();
                    else {
                        judet = spinnerJudet.getSelectedItem().toString();
                        oras = etOras.getText().toString();
                        strada = etStrada.getText().toString();
                        backgroundWorker = new BackgroundWorker(){
                            @Override
                            protected void onPostExecute(String s) {
                                try {
                                    JSONObject object = new JSONObject(s);
                                    int success = object.getInt("success");
                                    if(success == 0){
                                        Toast.makeText(getApplicationContext(), "S-a produs o eroare! Reîncercati!", Toast.LENGTH_LONG).show();
                                    }
                                    else if (success == 1){
                                        JSONObject result = object.getJSONObject("result");
                                        idAdresa = Integer.parseInt(result.getString("nr"))+1;
                                        BackgroundWorker backgroundWorkerInsert = new BackgroundWorker(){
                                            @Override
                                            protected void onPostExecute(String s) {
                                                try {
                                                    JSONObject object1 = new JSONObject(s);
                                                    int ok = object1.getInt("success");
                                                    if(ok == 0){
                                                        Toast.makeText(getApplicationContext(), "Eroare! Reîncercati!", Toast.LENGTH_LONG).show();
                                                    }
                                                    else if (ok == 1){
                                                        BackgroundWorker backgroundWorkerInsertUser = new BackgroundWorker(){
                                                            @Override
                                                            protected void onPostExecute(String s) {
                                                                try {
                                                                    JSONObject objectInsert = new JSONObject(s);
                                                                    int result = objectInsert.getInt("success");
                                                                    if(result == 0)
                                                                    {
                                                                        Toast.makeText(getApplicationContext(), "S-a produs o eroare! Verificați câmpurile introduse!", Toast.LENGTH_LONG).show();
                                                                    }
                                                                    else if (result == 1){
                                                                        client.setIdAdresa(idAdresa);
                                                                        BackgroundWorker bw = new BackgroundWorker(){
                                                                            @Override
                                                                            protected void onPostExecute(String s) {
                                                                                try {
                                                                                    JSONObject object = new JSONObject(s);
                                                                                    int success = object.getInt("success");
                                                                                    if (success == 1){
                                                                                        JSONObject rezultat = object.getJSONObject("result");
                                                                                        idClient = Integer.parseInt(rezultat.getString("id_client"));
                                                                                        Toast.makeText(getApplicationContext(), "Cont creat cu succes!", Toast.LENGTH_LONG).show();
                                                                                        Intent intent1 = new Intent(getApplicationContext(), MainPageComunityActivity.class);
                                                                                        intent1.putExtra(KEY_LOGIN, idClient);
                                                                                        startActivity(intent1);
                                                                                    }
                                                                                } catch (JSONException e) {
                                                                                    e.printStackTrace();
                                                                                }
                                                                            }
                                                                        };
                                                                        bw.execute("login", client.getUsername(), client.getPassword());
                                                                    }
                                                                } catch (JSONException e) {
                                                                    e.printStackTrace();
                                                                }
                                                            }
                                                        };
                                                        backgroundWorkerInsertUser.execute("insertUser", client.getUsername(), client.getPassword(),
                                                                client.getNume(), client.getPrenume(), client.getTelefon(), client.getDataNastere(),
                                                                client.getIdSport().toString(), client.getSex(), idAdresa.toString(), client.getPoza(), client.getBio());
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        };
                                        backgroundWorkerInsert.execute("insertAdress", idAdresa.toString(), judet, oras, strada);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        backgroundWorker.execute("getNrAdrese");

                    }
                }
            }
        };
    }

    private boolean isValid() {
        if (etOras.getText() == null || etOras.getText().toString() == null || etOras.getText().toString().isEmpty())
            return false;
        else if (etStrada.getText() == null || etStrada.getText().toString() == null || etStrada.getText().toString().isEmpty())
            return false;
        return true;
    }
}
