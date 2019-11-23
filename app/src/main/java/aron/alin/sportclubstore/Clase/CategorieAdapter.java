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

import aron.alin.sportclubstore.ListaProduseActivity;
import aron.alin.sportclubstore.R;

public class CategorieAdapter extends RecyclerView.Adapter<CategorieAdapter.ViewHolder> implements Utils{
    private Context context;
    private List<Categorie> lista;

    public CategorieAdapter(Context context, List<Categorie> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.categorie_item, null);
        return new CategorieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Categorie categorie = lista.get(i);
        final Integer idCategorie = categorie.getIdCategorie();
        String denumireCategorie = categorie.getDenumireCategorie();
        String poza = categorie.getPoza();
        final Integer idCli = categorie.getIdClient();

        viewHolder.tvDenumire.setText(denumireCategorie);
        Picasso.get().load(poza).into(viewHolder.ivPoza);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListaProduseActivity.class);
                intent.putExtra(KEY_LISTA_PRODUSE ,idCategorie);
                intent.putExtra(KEY_CLIENT, idCli);
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
        private TextView tvDenumire;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoza = itemView.findViewById(R.id.categorie_poza);
            tvDenumire = itemView.findViewById(R.id.categorie_tv_denumire);
        }
    }
}
