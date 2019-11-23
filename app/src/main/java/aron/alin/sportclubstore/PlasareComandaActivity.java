package aron.alin.sportclubstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.ProdusRecycler;
import aron.alin.sportclubstore.Clase.Utils;

public class PlasareComandaActivity extends AppCompatActivity implements Utils {

    private Integer idClient;
    private List<ProdusRecycler> lista;
    private TextView tvDateFacturare, tvSumaPlata;
    private RadioGroup rgModalitatePlata;
    private RadioButton rbRamburs, rbCard;
    private Button btnFinalizeaza;
    private String nume, prenume, telefon;
    private Integer idAdresa;
    private String modalitatePlata;
    private Integer nrComanda;
    private Double valoare=0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plasare_comanda);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            idClient = bundle.getInt(KEY_PLASEAZA_COMANDA_CLIENT);
            lista = (List<ProdusRecycler>) bundle.getSerializable(KEY_PLASEAZA_COMANDA_LISTA);
        }
        tvDateFacturare = findViewById(R.id.confirmare_comanda_tv_date);
        tvSumaPlata = findViewById(R.id.confirmare_comanda_suma_plata);
        rgModalitatePlata = findViewById(R.id.confirmare_comanda_rg);
        rbRamburs = findViewById(R.id.confirmare_comanda_ramburs);
        rbCard = findViewById(R.id.confirmare_comanda_card);
        btnFinalizeaza = findViewById(R.id.confirmare_comanda_finalizeaza_comanda);
        for (ProdusRecycler produs : lista) {
            valoare += produs.getPret();
        }
        tvSumaPlata.setText(String.format("%.2f", valoare) + " lei");
        @SuppressLint("StaticFieldLeak") BackgroundWorker backgroundWorker = new BackgroundWorker() {
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    int success = object.getInt("success");
                    if (success == 1) {
                        JSONObject rezultat = object.getJSONObject("result");
                        nume = rezultat.getString("nume");
                        prenume = rezultat.getString("prenume");
                        idAdresa = Integer.parseInt(rezultat.getString("id_adresa"));
                        telefon = rezultat.getString("telefon");
                        BackgroundWorker bwAdresa = new BackgroundWorker() {
                            @Override
                            protected void onPostExecute(String s) {
                                try {
                                    JSONObject obj = new JSONObject(s);
                                    JSONObject rezultat = obj.getJSONObject("rezultat");
                                    String judet = rezultat.getString("judet");
                                    String oras = rezultat.getString("oras");
                                    String adresa = rezultat.getString("strada");
                                    tvDateFacturare.setText(nume + " " + prenume + "\n" + adresa + "\n" + oras + "\n" + judet + "\n" + telefon);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        bwAdresa.execute("getAdresaClient", idAdresa.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundWorker.execute("mainpagecomunity", idClient.toString());

        btnFinalizeaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbCard.isChecked()) {
                    modalitatePlata = "card";

                } else if (rbRamburs.isChecked()) {
                    modalitatePlata = "ramburs";
                }
                else {
                    modalitatePlata = null;
                }
                if (modalitatePlata!=null) {
                    @SuppressLint("StaticFieldLeak") BackgroundWorker bwNrComanda = new BackgroundWorker() {
                        @Override
                        protected void onPostExecute(String s) {
                            try {
                                JSONObject object = new JSONObject(s);
                                JSONObject rezultat = object.getJSONObject("rezultat");
                                nrComanda = Integer.parseInt(rezultat.getString("nr"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            BackgroundWorker bwInsertComanda = new BackgroundWorker() {
                                @Override
                                protected void onPostExecute(String s) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(s);
                                        int success = Integer.parseInt(jsonObject.getString("success"));
                                        if (success == 1) {
                                            Toast.makeText(getApplicationContext(), "Comanda a fost plasată cu succes!", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Eroare la plasarea comenzii! Încercati din nou.", Toast.LENGTH_LONG).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            bwInsertComanda.execute("insertComanda", nrComanda.toString(), idClient.toString(), valoare.toString(), modalitatePlata);
                            for (ProdusRecycler produs : lista) {
                                BackgroundWorker bwInsertRandComenzi = new BackgroundWorker() {
                                    @Override
                                    protected void onPostExecute(String s) {
                                        try {
                                            JSONObject obj = new JSONObject(s);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                bwInsertRandComenzi.execute("insertRandComenzi", nrComanda.toString(), produs.getIdProdus().toString());
                            }
                        }
                    };
                    bwNrComanda.execute("getNrComenzi");
                    @SuppressLint("StaticFieldLeak") BackgroundWorker bwDelete = new BackgroundWorker() {
                    };
                    bwDelete.execute("deleteAllCos", idClient.toString());
                    if (modalitatePlata.equals("card")) {
                        Intent myIntent = new Intent(getApplicationContext(), CheckOutActivity.class);
                        myIntent.putExtra(KEY_VALOARE_PLATA, valoare);
                        startActivity(myIntent);
                    }
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Selectați o modalitate de plată!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
