package aron.alin.sportclubstore.Clase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import aron.alin.sportclubstore.R;

public class CustomAdapterLocatii extends ArrayAdapter<Locatie> {
    private Context context;
    private int resursa;
    private List<Locatie> locatii;
    private LayoutInflater layoutInflater;
    public CustomAdapterLocatii(Context context, int resource, List<Locatie> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resursa = resource;
        this.locatii = objects;
        this.layoutInflater = inflater;
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        TextView tvDenumire, tvTarif;
        View linie = layoutInflater.inflate(resursa, parent, false);
        tvDenumire = linie.findViewById(R.id.lv_locatii_denumire);
        tvTarif = linie.findViewById(R.id.lv_locatii_tarif);

        Locatie locatie = locatii.get(position);
        tvDenumire.setText(locatie.getDenumireLocatie());
        if(locatie.getTarif()!=null)
            tvTarif.setText(String.valueOf(locatie.getTarif())+" lei");
        return linie;
    }
}
