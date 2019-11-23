package aron.alin.sportclubstore;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import aron.alin.sportclubstore.Clase.Utils;

public class StatsActivity extends AppCompatActivity implements Utils {

    private BarChart barChart;
    private PieChart pieChart;
    private Integer nrPostariMai, nrPostariIun, nrPostariIul, nrPostariTotal;
    private TextView tvPostariPers, tvPostariTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            nrPostariMai = bundle.getInt(KEY_POSTARI_MAY);
            nrPostariIun = bundle.getInt(KEY_POSTARI_JUN);
            nrPostariIul = bundle.getInt(KEY_POSTARI_JUL);
            nrPostariTotal = bundle.getInt(KEY_POSTARI_TOT);
        }
        ArrayList<Integer> culoriGraficBare = new ArrayList<>();
        ArrayList<Integer> culoriGraficPie = new ArrayList<>();
        culoriGraficBare.add(Color.rgb(105,187,199));
        culoriGraficBare.add(Color.rgb(30,138,201));
        culoriGraficBare.add(Color.rgb(89,123,235));

        culoriGraficPie.add(Color.rgb(69,173,152));
        culoriGraficPie.add(Color.rgb(8,143,153));
        int nrPostariPersTotal = nrPostariMai + nrPostariIun + nrPostariIul;
        barChart = findViewById(R.id.stats_bar_chart);
        ArrayList<BarEntry> valori = new ArrayList<>();
        valori.add(new BarEntry(nrPostariMai, 0));
        valori.add(new BarEntry(nrPostariIun, 1));
        valori.add(new BarEntry(nrPostariIul, 2));
        BarDataSet dataSet = new BarDataSet(valori, "Postări");
        ArrayList<String> etichete = new ArrayList<>();
        etichete.add("Mai");
        etichete.add("Iunie");
        etichete.add("Iulie");

        BarData barData = new BarData(etichete, dataSet);
        dataSet.setColors(culoriGraficBare);
        barChart.setData(barData);
        barChart.setDescription("Număr de postări");

        tvPostariPers = findViewById(R.id.tvPostariPers);
        tvPostariTotal = findViewById(R.id.tvPostariTotal);

        String nrPostariPersTotalRosu = "<font color='#EE0000'>"+ nrPostariPersTotal + "</font>";
        String nrPostariTotalRosu = "<font color='#EE0000'>"+ nrPostariTotal + "</font>";
        tvPostariPers.setText(Html.fromHtml("Tu și prietenii tăi ați adăugat " + nrPostariPersTotalRosu + " postări în ultimele 3 luni!"));
        tvPostariTotal.setText(Html.fromHtml("În ultimele 3 luni au fost adăugate " + nrPostariTotalRosu + " postări noi!"));

        pieChart = findViewById(R.id.stats_pie_chart);
        pieChart.setDescription("Numărul de postări ale prietenilor din totalul postărilor publicate (%)");
        pieChart.setRotationEnabled(true);

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Prieteni");
        labels.add("Altele");

        ArrayList<Entry> pieEntries = new ArrayList<>();
        pieEntries.add(new Entry(nrPostariPersTotal, 0));
        pieEntries.add(new Entry(nrPostariTotal - nrPostariPersTotal, 1));
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Postări");
        PieData pieData = new PieData(labels, pieDataSet);
        pieDataSet.setColors(culoriGraficPie);
        pieData.setValueFormatter(new PercentFormatter());
        pieChart.setUsePercentValues(true);
        pieChart.setData(pieData);

    }
}
