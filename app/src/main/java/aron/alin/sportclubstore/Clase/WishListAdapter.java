package aron.alin.sportclubstore.Clase;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import aron.alin.sportclubstore.ProdusActivity;
import aron.alin.sportclubstore.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> implements Utils{
    private Context context;
    private List<ProdusRecycler> list;

    public WishListAdapter(Context context, List<ProdusRecycler> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.wishlist_layout, null);
        return new WishListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final ProdusRecycler produs = list.get(i);
        final Integer idProdus = produs.getIdProdus();
        String denumire = produs.getDenumire();
        String poza = produs.getPoza();
        Double pret = produs.getPret();
        final Integer idCli = produs.getIdClient();


        Picasso.get().load(poza).into(viewHolder.ivProdus);
        viewHolder.tvDenumire.setText(denumire);
        viewHolder.tvPretNou.setText(String.format("%.2f", pret)+" lei");
        viewHolder.tvPret.setPaintFlags(viewHolder.tvPret.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        final Double pretNou = pret/0.95;
        viewHolder.tvPret.setText(String.format("%.2f", pretNou)+ " lei");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.dialog_wish, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(layout);
                final AlertDialog dialog = builder.create();
                Button btnAdauga = layout.findViewById(R.id.dialog_btn_adauga);
                Button btnArata = layout.findViewById(R.id.dialog_btn_arata);
                btnAdauga.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        @SuppressLint("StaticFieldLeak") BackgroundWorker bwInsert = new BackgroundWorker(){
                            @Override
                            protected void onPostExecute(String s) {
                                Toast.makeText(context, "Produsul a fost adăugat cu succes în coșul de cumpărături!", Toast.LENGTH_LONG).show();
                            }
                        };
                        bwInsert.execute("insertProdus2", idCli.toString(), idProdus.toString(), "");
                        @SuppressLint("StaticFieldLeak") BackgroundWorker backgroundWorker = new BackgroundWorker(){
                            @Override
                            protected void onPostExecute(String s) {
                            }
                        };
                        backgroundWorker.execute("deleteFavorite", idCli.toString(),"p",idProdus.toString());
                        list.remove(produs);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                btnArata.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ProdusActivity.class);
                        intent.putExtra(KEY_PRODUS, idProdus);
                        intent.putExtra(KEY_CLIENT_PRODUS, idCli);
                        v.getContext().startActivity(intent);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivProdus, btnSterge;
        private TextView tvDenumire, tvPret, tvPretNou;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProdus = itemView.findViewById(R.id.wishlist_poza);
            btnSterge = itemView.findViewById(R.id.wishlist_del);
            tvDenumire = itemView.findViewById(R.id.wishlist_descriere);
            tvPret = itemView.findViewById(R.id.wishlist_pret);
            tvPretNou = itemView.findViewById(R.id.wishlist_pret_nou);

            btnSterge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pozitie = getAdapterPosition();
                    @SuppressLint("StaticFieldLeak") BackgroundWorker backgroundWorker = new BackgroundWorker(){
                        @Override
                        protected void onPostExecute(String s) {
                            Toast.makeText(context, "Produsul a fost eliminat din lista de dorințe...",Toast.LENGTH_LONG).show();
                        }
                    };
                    backgroundWorker.execute("deleteFavorite", list.get(pozitie).getIdClient().toString(),"p",list.get(pozitie).getIdProdus().toString());
                    list.remove(pozitie);
                    notifyDataSetChanged();
                }
            });

        }
    }
}
