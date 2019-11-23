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

public class ComandaAdapter extends RecyclerView.Adapter<ComandaAdapter.ViewHolder> implements Utils{
    private Context context;
    private List<ProdusRecycler> list;

    public ComandaAdapter(Context context, List<ProdusRecycler> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_produs_comanda, null);
        return new ComandaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ProdusRecycler produs = list.get(i);
        String denumire = produs.getDenumire();
        String poza = produs.getPoza();
        Double pret = produs.getPret();
        viewHolder.tvPret.setText(String.format("%.2f", pret)+" lei");
        viewHolder.tvDenumire.setText(denumire);
        Picasso.get().load(poza).into(viewHolder.ivPoza);

        final Integer idProdus = produs.getIdProdus();
        final Integer idCli = produs.getIdClient();
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
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivPoza;
        private TextView tvDenumire, tvPret;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoza = itemView.findViewById(R.id.comanda_poza);
            tvDenumire = itemView.findViewById(R.id.comanda_denumire);
            tvPret = itemView.findViewById(R.id.comanda_pret);
        }
    }
}
