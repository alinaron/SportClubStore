package aron.alin.sportclubstore.Clase;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import aron.alin.sportclubstore.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class UrmaritoriAdapter extends RecyclerView.Adapter<UrmaritoriAdapter.ViewHolder> {
    private Context context;
    private List<Integer> lista;
    private AlertDialog dialog;

    public UrmaritoriAdapter(Context context, List<Integer> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_urmaritor, null);
        return new UrmaritoriAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        int idClient = lista.get(i);
        @SuppressLint("StaticFieldLeak") BackgroundWorker backgroundWorker = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    int success = object.getInt("success");
                    if(success == 1) {
                        JSONObject rezultat = object.getJSONObject("result");
                        final String username = rezultat.getString("username");
                        String myPhoto = rezultat.getString("poza");
                        final String nume = rezultat.getString("nume");
                        final String prenume = rezultat.getString("prenume");
                        final String bio = rezultat.getString("bio");
                        final byte[] poz = Base64.decode(myPhoto,Base64.DEFAULT);
                        final Bitmap bitmap = BitmapFactory.decodeByteArray(poz,0, poz.length);
                        viewHolder.ivPoza.setImageBitmap(bitmap);
                        viewHolder.tvUsername.setText(username);
                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                                View layout = inflater.inflate(R.layout.persoana_layout, null);
                                ImageView ivPoza = layout.findViewById(R.id.pers_img_profile_pic);
                                TextView tvUsername, tvNumePrenume, tvOrasJudet, tvBio;
                                Button btnAdauga;
                                tvUsername = layout.findViewById(R.id.tv_profile_username);
                                tvNumePrenume = layout.findViewById(R.id.tv_profile_nume_prenume);
                                tvBio = layout.findViewById(R.id.tv_profile_bio);
                                tvOrasJudet = layout.findViewById(R.id.tv_profile_oras_judet);
                                btnAdauga = layout.findViewById(R.id.tv_profile_solicita);
                                ivPoza.setImageBitmap(bitmap);
                                tvUsername.setText("@"+username+" ");
                                tvNumePrenume.setText(" "+nume+" "+prenume);
                                if(bio != null) {
                                    tvBio.setText(" "+bio+" ");
                                }
                                else tvBio.setVisibility(View.GONE);
                                tvOrasJudet.setVisibility(View.GONE);
                                btnAdauga.setVisibility(View.GONE);
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setView(layout);
                                dialog = builder.create();
                                dialog.show();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundWorker.execute("mainpagecomunity", String.valueOf(idClient));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivPoza;
        private TextView tvUsername;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoza = itemView.findViewById(R.id.urmaritor_poza);
            tvUsername = itemView.findViewById(R.id.urmaritor_username);
        }
    }
}
