package aron.alin.sportclubstore.Clase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import aron.alin.sportclubstore.R;
import aron.alin.sportclubstore.ViewPostActivity;

public class PostareAdapter extends RecyclerView.Adapter<PostareAdapter.ViewHolder> implements Utils{
    private Context context;
    private Integer idCli;
    private List<Postare> lista;
    String pozaProfil;
    String username;
    public PostareAdapter(Context context, List<Postare> lista, Integer idCli) {
        this.context = context;
        this.lista = lista;
        this.idCli = idCli;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.postare_layout, null);
        return new PostareAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Integer idPostare = lista.get(i).getIdPostare();
        String continut = lista.get(i).getContinut();
        String poza = lista.get(i).getPoza();
        Integer idClient = lista.get(i).getIdClient();
        if(continut.equals("null"))
            viewHolder.tvContinut.setVisibility(View.GONE);
        else
            viewHolder.tvContinut.setText(continut);
        if(poza.equals("null"))
            viewHolder.imgPoza.setVisibility(View.GONE);
        else {
            byte[] pz = Base64.decode(poza,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(pz,0, pz.length);
            viewHolder.imgPoza.setImageBitmap(bitmap);
        }
        @SuppressLint("StaticFieldLeak") BackgroundWorker backgroundWorker = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    int success = object.getInt("success");
                    if(success == 1) {
                        JSONObject rezultat = object.getJSONObject("result");
                        pozaProfil = rezultat.getString("poza");
                        username = rezultat.getString("username");
                        viewHolder.tvUsername.setText(username);
                        byte[] pzd = Base64.decode(pozaProfil,Base64.DEFAULT);
                        Bitmap bitmapd = BitmapFactory.decodeByteArray(pzd,0, pzd.length);
                        viewHolder.imgPozaProfil.setImageBitmap(bitmapd);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundWorker.execute("mainpagecomunity", idClient.toString());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewPostActivity.class);
                intent.putExtra(KEY_POSTARE, idPostare);
                intent.putExtra(KEY_CLIENT_POSTARE, idCli);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgPozaProfil, imgPoza;
        private TextView tvUsername, tvContinut;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPoza = itemView.findViewById(R.id.postare_poza);
            imgPozaProfil = itemView.findViewById(R.id.postare_poza_profil);
            tvUsername = itemView.findViewById(R.id.postare_tv_username);
            tvContinut = itemView.findViewById(R.id.postare_tv_continut);
        }
    }

}
