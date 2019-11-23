package aron.alin.sportclubstore.Clase;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import aron.alin.sportclubstore.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class PersoaneAdapter extends RecyclerView.Adapter<PersoaneAdapter.ViewHolder>{

    private Context context;
    private List<PersoanaRecycler> lista;
    private Integer idPersoana;
    private AlertDialog dialog;

    public PersoaneAdapter(Context context, List<PersoanaRecycler> lista, Integer idPersoana) {
        this.context = context;
        this.lista = lista;
        this.idPersoana = idPersoana;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.persoane_item, viewGroup, false);
        return new PersoaneAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Integer idClient = lista.get(i).getIdClient();
        final String poza = lista.get(i).getPoza();
        final String username = lista.get(i).getUsername();
        final String nume = lista.get(i).getNume();
        final String prenume = lista.get(i).getPrenume();
        final String oras = lista.get(i).getOras().substring(0, lista.get(i).getOras().length()-1);
        final String judet = lista.get(i).getJudet();
        final String bio = lista.get(i).getBio();

        byte[] poz = Base64.decode(poza,Base64.DEFAULT);
        final Bitmap bitmap = BitmapFactory.decodeByteArray(poz,0, poz.length);
        viewHolder.imgPersoana.setImageBitmap(bitmap);
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
                tvOrasJudet.setText(oras+", "+judet);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(layout);
                dialog = builder.create();
                btnAdauga.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        @SuppressLint("StaticFieldLeak") BackgroundWorker bwFavorite = new BackgroundWorker(){
                            @Override
                            protected void onPostExecute(String s) {
                                try {
                                    JSONObject object = new JSONObject(s);
                                    int success = object.getInt("success");
                                    if(success == 0){
                                        Toast.makeText(context, "Urmărești deja pe "+nume+" "+prenume, Toast.LENGTH_LONG).show();
                                    }
                                    else if(success == 1){
                                        Toast.makeText(context, "Ai început să urmărești pe "+nume+" "+prenume, Toast.LENGTH_LONG).show();
                                        dialog.dismiss();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        bwFavorite.execute("insertFavorite", idPersoana.toString(), "o", idClient.toString());
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgPersoana;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPersoana = itemView.findViewById(R.id.persoana_poza);
        }
    }
}
