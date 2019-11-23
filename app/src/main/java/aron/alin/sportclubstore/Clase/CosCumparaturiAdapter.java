package aron.alin.sportclubstore.Clase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


import java.util.List;

import aron.alin.sportclubstore.ProdusActivity;
import aron.alin.sportclubstore.R;

public class CosCumparaturiAdapter extends RecyclerView.Adapter<CosCumparaturiAdapter.ViewHolder> implements Utils{
    private Context context;
    private List<ProdusRecycler> list;


    public CosCumparaturiAdapter(Context context, List<ProdusRecycler> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cos_cumparaturi_item, null);
        return new CosCumparaturiAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ProdusRecycler produs = list.get(i);
        final Integer idProdus = produs.getIdProdus();
        String denumire = produs.getDenumire();
        String poza = produs.getPoza();
        Double pret = produs.getPret();
        final Integer idCli = produs.getIdClient();


        Picasso.get().load(poza).into(viewHolder.ivProdus);
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
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivProdus, btnSterge;
        private TextView tvDenumire, tvPret;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProdus = itemView.findViewById(R.id.cos_cumparaturi_poza);
            btnSterge = itemView.findViewById(R.id.cos_cumparaturi_del);
            tvDenumire = itemView.findViewById(R.id.cos_cumparaturi_descriere);
            tvPret = itemView.findViewById(R.id.cos_cumparaturi_pret);

            btnSterge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pozitie = getAdapterPosition();
                    @SuppressLint("StaticFieldLeak") BackgroundWorker backgroundWorker = new BackgroundWorker(){
                        @Override
                        protected void onPostExecute(String s) {
                            Toast.makeText(context, "Produsul a fost eliminat din coșul de cumpărături...",Toast.LENGTH_LONG).show();
                            }
                            };
                    backgroundWorker.execute("deleteCos", list.get(pozitie).getIdClient().toString(), list.get(pozitie).getIdProdus().toString());
                    list.remove(pozitie);
                    notifyDataSetChanged();
                    }
            });

        }
    }
}
