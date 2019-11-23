package aron.alin.sportclubstore.Clase;

import android.annotation.SuppressLint;
import android.content.Context;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import aron.alin.sportclubstore.R;

public class ComentriuAdapter extends RecyclerView.Adapter<ComentriuAdapter.ViewHolder> {
    private Context context;
    private List<Comentariu> list;

    public ComentriuAdapter(Context context, List<Comentariu> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_item, null);
        return new ComentriuAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Integer idComentariu, idPostare, idClient;
        String continut;
        idComentariu = list.get(i).getIdComentariu();
        idPostare = list.get(i).getIdPostare();
        idClient = list.get(i).getIdClient();
        continut = list.get(i).getContinut();
        viewHolder.tvComentariu.setText(continut);
        @SuppressLint("StaticFieldLeak") BackgroundWorker backgroundWorker = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    int success = object.getInt("success");
                    if(success == 1) {
                        JSONObject rezultat = object.getJSONObject("result");
                        String poza = rezultat.getString("poza");
                        String username = rezultat.getString("username");
                        viewHolder.tvUsername.setText(username);
                        byte[] poz = Base64.decode(poza,Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(poz,0, poz.length);
                        viewHolder.ivUser.setImageBitmap(bitmap);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        backgroundWorker.execute("mainpagecomunity", idClient.toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivUser;
        private TextView tvComentariu, tvUsername;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivUser = itemView.findViewById(R.id.comm_poza_profil);
            tvUsername = itemView.findViewById(R.id.comm_tv_user);
            tvComentariu = itemView.findViewById(R.id.comm_tv_continut);
        }
    }
}
