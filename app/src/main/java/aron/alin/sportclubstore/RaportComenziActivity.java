package aron.alin.sportclubstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.Utils;

public class RaportComenziActivity extends AppCompatActivity implements Utils {

    private Integer idClient;
    private TextView tvTotal, tvMediu, tvScump, tvMare, tvProdusScump, tvProdusIeftin, tvModalitatePlata;
    private List<Integer> listaComenzi = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raport_comenzi);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            idClient = bundle.getInt(KEY_RAPORT);
        }
        tvTotal = findViewById(R.id.raport_valoare_totala);
        tvMediu = findViewById(R.id.raport_valoare_medie);
        tvScump = findViewById(R.id.raport_cea_mai_scumpa);
        tvMare = findViewById(R.id.raport_cea_mai_mare);
        tvProdusScump = findViewById(R.id.raport_produs_scump);
        tvProdusIeftin = findViewById(R.id.raport_produs_ieftin);
        tvModalitatePlata = findViewById(R.id.raport_modalitate_plata);

        @SuppressLint("StaticFieldLeak") BackgroundWorker backgroundWorker = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    double valoareComenzi = 0;
                    int nrRamburs = 0, nrCard = 0;
                    double max = 0;
                    listaComenzi.clear();
                    JSONObject object = new JSONObject(s);
                    JSONArray array = object.getJSONArray("rezultat");
                    if(array!=null){
                        for(int i=0;i<array.length();i++){
                            Integer idComanda = Integer.parseInt(array.getJSONObject(i).getString("nr_comanda"));
                            double valoare = Double.parseDouble(array.getJSONObject(i).getString("valoare"));
                            String modalitatePlata = array.getJSONObject(i).getString("modalitate_plata");
                            valoareComenzi += valoare;
                            if(modalitatePlata.equals("ramburs"))
                                nrRamburs++;
                            else if (modalitatePlata.equals("card"))
                                nrCard++;
                            if(valoare > max)
                                max = valoare;
                            listaComenzi.add(idComanda);
                        }
                        String valComenzi = "<font color='#EE0000'>"+ String.format("%.2f", valoareComenzi) + "</font>";
                        String valMedComenzi = "<font color='#EE0000'>"+ String.format("%.2f", valoareComenzi/listaComenzi.size()) + "</font>";
                        String nrRambursRosu = "<font color='#EE0000'>"+ nrRamburs + "</font>";
                        String nrCardRosu = "<font color='#EE0000'>"+ nrCard + "</font>";
                        String maxRosu = "<font color='#EE0000'>"+ String.format("%.2f", max) + "</font>";
                        tvTotal.setText(Html.fromHtml("Valoarea totală a comenzilor: " + valComenzi + " lei"));
                        tvMediu.setText(Html.fromHtml("Valoarea medie a comenzilor: "+ valMedComenzi + " lei"));
                        tvScump.setText(Html.fromHtml("Cea mai scumpă comandă: "+ maxRosu + " lei"));
                        tvModalitatePlata.setText(Html.fromHtml("Ai plătit " + nrCardRosu + " comenzi cu cardul și " + nrRambursRosu + " comenzi ramburs"));

                        BackgroundWorker bwProd = new BackgroundWorker(){
                            @Override
                            protected void onPostExecute(String s) {
                                try {
                                    double pretMax = 0, pretMin = 5000;
                                    String denMax = "", denMin = "";
                                    JSONObject jsonObject = new JSONObject(s);
                                    JSONArray jsonArray = jsonObject.getJSONArray("rezultat");
                                    if(jsonArray!=null){
                                        for(int i=0;i<jsonArray.length();i++){
                                            double pret = Double.parseDouble(jsonArray.getJSONObject(i).getString("pret"));
                                            String denumire = jsonArray.getJSONObject(i).getString("denumire_produs");
                                            if(pret>pretMax) {
                                                pretMax = pret;
                                                denMax = denumire;
                                            }
                                            if(pret<pretMin) {
                                                pretMin = pret;
                                                denMin = denumire;
                                            }
                                        }
                                        if(pretMin == 5000)
                                            pretMin = 0;
                                        String denMaxRosu = "<font color='#EE0000'>"+ denMax + "</font>";
                                        String denMinRosu = "<font color='#EE0000'>"+ denMin + "</font>";
                                        String pretMaxRosu = "<font color='#EE0000'>"+ String.format("%.2f", pretMax) + "</font>";
                                        String pretMinRosu = "<font color='#EE0000'>"+ String.format("%.2f", pretMin) + "</font>";
                                        String nrTotRosu = "<font color='#EE0000'>"+ jsonArray.length() + "</font>";
                                        tvProdusIeftin.setText(Html.fromHtml("Cel mai ieftin produs achiziționat: "+denMinRosu + " ("+pretMinRosu+" lei)"));
                                        tvProdusScump.setText(Html.fromHtml("Cel mai scump produs achiziționat: "+denMaxRosu + " ("+pretMaxRosu+" lei)"));
                                        tvMare.setText(Html.fromHtml("Numărul total de produse achiziționate: " + nrTotRosu));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        bwProd.execute("getProduseComenzi", idClient.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundWorker.execute("getComenzi", idClient.toString());
    }
}
