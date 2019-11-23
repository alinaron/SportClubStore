package aron.alin.sportclubstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.Utils;

public class MyStats extends AppCompatActivity implements Utils {

    private Integer idClient;
    private TextView tvPostari, tvComenzi;
    private BarChart chartPostari, chartComenzi;
    private List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stats);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            idClient = bundle.getInt(KEY_MY_STATS);
        }
        tvPostari = findViewById(R.id.stats_tv_postari);
        tvComenzi = findViewById(R.id.stats_comenzi);
        chartComenzi = findViewById(R.id.stats_chart_comenzi);
        chartPostari = findViewById(R.id.stats_chart_postari);

        final ArrayList<Integer> culoriGrafic1 = new ArrayList<>();
        final ArrayList<Integer> culoriGrafic2 = new ArrayList<>();
        culoriGrafic1.add(Color.rgb(105,187,199));
        culoriGrafic1.add(Color.rgb(30,138,201));
        culoriGrafic1.add(Color.rgb(89,123,235));

        final ArrayList<BarEntry> valori = new ArrayList<>();
        @SuppressLint("StaticFieldLeak") BackgroundWorker backgroundWorker = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    int nrMai, nrIunie, nrIulie, nrTotal;
                    nrMai = nrIulie = nrIunie = nrTotal = 0;
                    JSONObject object = new JSONObject(s);
                    JSONArray rezultat = object.getJSONArray("rezultat");
                    list.clear();
                    if(rezultat!=null){
                        for(int i=0;i<rezultat.length();i++){
                            String data = rezultat.getJSONObject(i).getString("data");
                            list.add(data);
                        }
                        nrTotal = list.size();
                        for(String data: list){
                            switch (data.substring(5, 7)) {
                                case "05":
                                    nrMai++;
                                    break;
                                case "06":
                                    nrIunie++;
                                    break;
                                case "07":
                                    nrIulie++;
                                    break;
                            }
                        }
                        valori.add(new BarEntry(nrMai,0));
                        valori.add(new BarEntry(nrIunie,1));
                        valori.add(new BarEntry(nrIulie,2));
                        BarDataSet dataSet = new BarDataSet(valori, "Postări");
                        ArrayList<String> etichete = new ArrayList<>();
                        etichete.add("Mai");
                        etichete.add("Iunie");
                        etichete.add("Iulie");

                        BarData barData = new BarData(etichete, dataSet);
                        dataSet.setColors(culoriGrafic1);
                        chartPostari.setData(barData);
                        chartPostari.setDescription("Număr de postări");
                        chartPostari.invalidate();
                        chartPostari.refreshDrawableState();
                        final int finalNrTotal = nrTotal;
                        BackgroundWorker bwComentarii = new BackgroundWorker(){
                            @Override
                            protected void onPostExecute(String s) {
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    JSONArray jsonArray = jsonObject.getJSONArray("rezultat");
                                    int nrComentarii = jsonArray.length();
                                    String nrPostariRosu = "<font color='#EE0000'>"+ finalNrTotal + "</font>";
                                    String nrComentariiRosu = "<font color='#EE0000'>"+ nrComentarii + "</font>";
                                    tvPostari.setText(Html.fromHtml("În ultimele 3 luni ai adăugat " + nrPostariRosu + " postări și " + nrComentariiRosu + " comentarii."));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        bwComentarii.execute("getMyComments", idClient.toString());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundWorker.execute("getMyPosts", idClient.toString());

        culoriGrafic2.add(Color.rgb(69,173,152));
        culoriGrafic2.add(Color.rgb(8,143,153));
        culoriGrafic2.add(Color.rgb(30,138,201));

        final ArrayList<BarEntry> valori2 = new ArrayList<>();
        @SuppressLint("StaticFieldLeak") final BackgroundWorker bwComenzi = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    JSONArray array = object.getJSONArray("rezultat");
                    list.clear();
                    double vMai, vIun, vIul, vTot;
                    vMai = vIul = vIun = vTot = 0.0;
                    if(array!=null){
                        for(int i=0;i<array.length();i++){
                            String data_comanda = array.getJSONObject(i).getString("data_comanda");
                            Double valoare = Double.parseDouble(array.getJSONObject(i).getString("valoare"));
                            switch (data_comanda.substring(5, 7)) {
                                case "05":
                                    vMai += valoare;
                                    break;
                                case "06":
                                    vIun += valoare;
                                    break;
                                case "07":
                                    vIul += valoare;
                                    break;
                            }
                        }
                        vTot = vMai + vIul + vIun;
                        valori2.add(new BarEntry((float)vMai, 0));
                        valori2.add(new BarEntry((float)vIun, 1));
                        valori2.add(new BarEntry((float)vIul, 2));
                        BarDataSet dataSet = new BarDataSet(valori2, "");
                        ArrayList<String> etichete2 = new ArrayList<>();
                        etichete2.add("Mai");
                        etichete2.add("Iunie");
                        etichete2.add("Iulie");
                        BarData barData = new BarData(etichete2, dataSet);
                        dataSet.setColors(culoriGrafic2);
                        chartComenzi.setData(barData);
                        chartComenzi.setDescription("Valoare cumpărături");
                        chartComenzi.invalidate();
                        chartComenzi.refreshDrawableState();
                        String valComenzi = "<font color='#EE0000'>"+ String.format("%.2f", vTot) + "</font>";
                        tvComenzi.setText(Html.fromHtml("În ultimele 3 luni ai făcut cumpărături în valoare de "+valComenzi+" lei."));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        bwComenzi.execute("getComenzi", idClient.toString());
    }
}
