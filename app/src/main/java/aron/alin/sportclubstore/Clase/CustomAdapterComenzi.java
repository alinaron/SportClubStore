package aron.alin.sportclubstore.Clase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import aron.alin.sportclubstore.R;

public class CustomAdapterComenzi extends ArrayAdapter<Comanda> {
    private Context context;
    private int resursa;
    private List<Comanda> comenzi;
    private LayoutInflater layoutInflater;
    public CustomAdapterComenzi(Context context, int resource, List<Comanda> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resursa = resource;
        this.comenzi = objects;
        this.layoutInflater = inflater;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tvNumarComanda, tvValoare;
        View linie = layoutInflater.inflate(resursa, parent, false);
        tvNumarComanda = linie.findViewById(R.id.lv_comenzi_id);
        tvValoare = linie.findViewById(R.id.lv_comenzi_valoare);
        Comanda comanda = comenzi.get(position);
        tvNumarComanda.setText("Comanda #"+String.format("%04d", comanda.getNrComanda()));
        tvValoare.setText(String.format("%.2f", comanda.getValoare()) + " lei");
        return linie;
    }
}
