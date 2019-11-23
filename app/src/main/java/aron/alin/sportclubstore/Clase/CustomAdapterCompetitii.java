package aron.alin.sportclubstore.Clase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import aron.alin.sportclubstore.R;

public class CustomAdapterCompetitii extends ArrayAdapter<Competitie> {
    private Context context;
    private int resursa;
    private List<Competitie> competitii;
    private LayoutInflater layoutInflater;

    public CustomAdapterCompetitii(Context context, int resource, List<Competitie> objects, LayoutInflater layout) {
        super(context, resource, objects);
        this.context = context;
        this.resursa = resource;
        this.competitii = objects;
        this.layoutInflater = layout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tvDenumire;
        View linie = layoutInflater.inflate(resursa, parent, false);
        tvDenumire = linie.findViewById(R.id.competitii_lv_tv_denumire);

        Competitie competitie = competitii.get(position);
        tvDenumire.setText(competitie.getDenumireCompetitie());
        return linie;
    }
}
