package aron.alin.sportclubstore;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.Postare;
import aron.alin.sportclubstore.Clase.PostareAdapter;
import aron.alin.sportclubstore.Clase.Utils;

import static aron.alin.sportclubstore.Clase.Utils.KEY_BUNDLE;


/**
 * A simple {@link Fragment} subclass.
 */
public class FluxFragment extends Fragment implements Utils {
    private FloatingActionButton btnAdd, btnStats;
    private RecyclerView recyclerView;
    private PostareAdapter postareAdapter;
    private TextView tvNicioPostare;
    private List<Postare> lista = new ArrayList<>();
    private List<Integer> listaUrmaritori = new ArrayList<>();
    private Integer idClient;
    private Integer nrPostariTotalMai, nrPostariTotalIun, nrPostariTotalIul, nrPostariPersMai, nrPostariPersIun, nrPostariPersIul;
    public FluxFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_flux, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_postari);
        btnAdd = view.findViewById(R.id.postari_add_btn);
        btnAdd.setOnClickListener(doAdd());
        btnStats = view.findViewById(R.id.postari_stats);
        idClient =  getArguments().getInt(KEY_BUNDLE);
        tvNicioPostare = view.findViewById(R.id.postari_tv_nicio_postare);
        recyclerView.setHasFixedSize(true);
        nrPostariPersMai = nrPostariTotalMai = nrPostariTotalIun = nrPostariTotalIul = nrPostariPersIul = nrPostariPersIun = 0;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        @SuppressLint("StaticFieldLeak") BackgroundWorker bwUrmaritori = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    listaUrmaritori.clear();
                    listaUrmaritori.add(idClient);
                    JSONArray array = object.getJSONArray("rezultat");
                    if(array!=null){
                        for(int i=0;i<array.length();i++){
                            listaUrmaritori.add(Integer.parseInt(array.getJSONObject(i).getString("id_favorit")));
                        }
                    }
                    @SuppressLint("StaticFieldLeak") BackgroundWorker backgroundWorker = new BackgroundWorker(){
                        @Override
                        protected void onPostExecute(String s) {
                            try {
                                JSONObject object = new JSONObject(s);
                                lista.clear();
                                JSONArray array = object.getJSONArray("rezultat");
                                if(array!=null){
                                    for(int i=0;i<array.length();i++){
                                        Integer idPostare = Integer.parseInt(array.getJSONObject(i).getString("id_postare"));
                                        Integer idCl = Integer.parseInt(array.getJSONObject(i).getString("id_client"));
                                        String continut = array.getJSONObject(i).getString("continut");
                                        String poza = array.getJSONObject(i).getString("poza");
                                        String data = array.getJSONObject(i).getString("data").substring(5,7);
                                        switch (data) {
                                            case "05":
                                                nrPostariTotalMai++;
                                                break;
                                            case "06":
                                                nrPostariTotalIun++;
                                                break;
                                            case "07":
                                                nrPostariTotalIul++;
                                                break;
                                        }
                                        for(Integer idInt : listaUrmaritori)
                                            if(idInt.equals(idCl)) {
                                                lista.add(new Postare(idPostare, continut, poza, idCl));
                                                switch (data) {
                                                    case "05":
                                                        nrPostariPersMai++;
                                                        break;
                                                    case "06":
                                                        nrPostariPersIun++;
                                                        break;
                                                    case "07":
                                                        nrPostariPersIul++;
                                                        break;
                                                }
                                            }
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            btnStats.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getContext(), StatsActivity.class);
                                    intent.putExtra(KEY_POSTARI_MAY, nrPostariPersMai);
                                    intent.putExtra(KEY_POSTARI_JUN, nrPostariPersIun);
                                    intent.putExtra(KEY_POSTARI_JUL, nrPostariPersIul);
                                    intent.putExtra(KEY_POSTARI_TOT, nrPostariTotalMai + nrPostariTotalIun + nrPostariTotalIul);
                                    startActivity(intent);
                                }
                            });
                            if(lista.size()==0)
                                tvNicioPostare.setVisibility(View.VISIBLE);
                            else {
                                tvNicioPostare.setVisibility(View.GONE);
                                postareAdapter = new PostareAdapter(getContext(), lista, idClient);
                                recyclerView.setAdapter(postareAdapter);
                            }
                        }
                    };
                    backgroundWorker.execute("getPostari");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        bwUrmaritori.execute("getUrmaritori", idClient.toString());
        return view;
    }
    private View.OnClickListener doAdd(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AdaugaPostareActivity.class);
                intent.putExtra(KEY_ADAUGA_POSTARE, idClient);
                startActivity(intent);
            }
        };
    }
}
