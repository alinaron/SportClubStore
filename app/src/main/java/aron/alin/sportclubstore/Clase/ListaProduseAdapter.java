package aron.alin.sportclubstore.Clase;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import aron.alin.sportclubstore.ProdusActivity;
import aron.alin.sportclubstore.R;

public class ListaProduseAdapter extends RecyclerView.Adapter<ListaProduseAdapter.ViewHolder> implements Utils{
    private Context context;
    private List<ProdusRecycler> lista;

    public ListaProduseAdapter(Context context, List<ProdusRecycler> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.lista_produse_item, null);
        return new ListaProduseAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ProdusRecycler produs = lista.get(i);
        final Integer idProdus = produs.getIdProdus();
        String denumire = produs.getDenumire();
        String poza = produs.getPoza();
        Double pret = produs.getPret();
        final Integer idCli = produs.getIdClient();

        if(pret<50){
            viewHolder.tvPretMic.setVisibility(View.VISIBLE);
        }
        else viewHolder.tvPretMic.setVisibility(View.GONE);

        Picasso.get().load(poza).into(viewHolder.ivPoza);
        viewHolder.tvDenumire.setText(denumire);
        viewHolder.tvPret.setText(pret.toString()+" lei");

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProdusActivity.class);
                intent.putExtra(KEY_PRODUS, idProdus);
                intent.putExtra(KEY_CLIENT_PRODUS, idCli);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivPoza;
        private TextView tvDenumire, tvPretMic, tvPret;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoza = itemView.findViewById(R.id.lista_produse_poza);
            tvDenumire = itemView.findViewById(R.id.lista_produse_denumire);
            tvPretMic = itemView.findViewById(R.id.lista_produse_pret_mic);
            tvPret = itemView.findViewById(R.id.lista_produse_pret);
        }
    }
}
