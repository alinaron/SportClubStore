package aron.alin.sportclubstore;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.Produs;
import aron.alin.sportclubstore.Clase.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class MagazinFragment extends Fragment implements Utils {


    private FloatingActionButton fbCos, fbSearch;
    private EditText etSearch;
    private Integer idClient;
    private ImageView ivCategorie, ivPentruTine;
    private ImageView[] imgProd = new ImageView[10];
    private TextView[] denProd = new TextView[10];
    private TextView[] pretProd = new TextView[10];
    private CardView[] cardViews = new CardView[10];
    private List<Produs> listaProduse = new ArrayList<>();

    public MagazinFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_magazin, container, false);
        fbCos = view.findViewById(R.id.buton_cos_magazin);
        fbSearch = view.findViewById(R.id.magazin_btn_searcg);
        ivCategorie = view.findViewById(R.id.magazin_categorie);
        ivPentruTine = view.findViewById(R.id.magazin_pt_tine);
        etSearch = view.findViewById(R.id.magazin_et_search);
        InputFilter[] editFilters = etSearch.getFilters();
        InputFilter[] newFilters = new InputFilter[editFilters.length + 1];
        System.arraycopy(editFilters, 0, newFilters, 0, editFilters.length);
        newFilters[editFilters.length] = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                return String.valueOf(source).toLowerCase();
            }
        };
        etSearch.setFilters(newFilters);
        fbSearch.setOnClickListener(doSearch());
        ivCategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoriiActivity.class);
                intent.putExtra(KEY_CATEGORIE, idClient);
                startActivity(intent);
            }
        });
        ivPentruTine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListaProduseActivity.class);
                intent.putExtra(KEY_CLIENT, idClient);
                startActivity(intent);
            }
        });
        imgProd[0] = view.findViewById(R.id.mag_produs1_poza);
        imgProd[1] = view.findViewById(R.id.mag_produs2_poza);
        imgProd[2] = view.findViewById(R.id.mag_produs3_poza);
        imgProd[3] = view.findViewById(R.id.mag_produs4_poza);
        imgProd[4] = view.findViewById(R.id.mag_produs5_poza);
        imgProd[5] = view.findViewById(R.id.mag_produs6_poza);
        imgProd[6] = view.findViewById(R.id.mag_produs7_poza);
        imgProd[7] = view.findViewById(R.id.mag_produs8_poza);
        imgProd[8] = view.findViewById(R.id.mag_produs9_poza);
        imgProd[9] = view.findViewById(R.id.mag_produs10_poza);
        denProd[0] = view.findViewById(R.id.mag_produs1_denumire);
        denProd[1] = view.findViewById(R.id.mag_produs2_denumire);
        denProd[2] = view.findViewById(R.id.mag_produs3_denumire);
        denProd[3] = view.findViewById(R.id.mag_produs4_denumire);
        denProd[4] = view.findViewById(R.id.mag_produs5_denumire);
        denProd[5] = view.findViewById(R.id.mag_produs6_denumire);
        denProd[6] = view.findViewById(R.id.mag_produs7_denumire);
        denProd[7] = view.findViewById(R.id.mag_produs8_denumire);
        denProd[8] = view.findViewById(R.id.mag_produs9_denumire);
        denProd[9] = view.findViewById(R.id.mag_produs10_denumire);
        pretProd[0] = view.findViewById(R.id.mag_produs1_pret);
        pretProd[1] = view.findViewById(R.id.mag_produs2_pret);
        pretProd[2] = view.findViewById(R.id.mag_produs3_pret);
        pretProd[3] = view.findViewById(R.id.mag_produs4_pret);
        pretProd[4] = view.findViewById(R.id.mag_produs5_pret);
        pretProd[5] = view.findViewById(R.id.mag_produs6_pret);
        pretProd[6] = view.findViewById(R.id.mag_produs7_pret);
        pretProd[7] = view.findViewById(R.id.mag_produs8_pret);
        pretProd[8] = view.findViewById(R.id.mag_produs9_pret);
        pretProd[9] = view.findViewById(R.id.mag_produs10_pret);
        cardViews[0] = view.findViewById(R.id.mag_card1);
        cardViews[1] = view.findViewById(R.id.mag_card2);
        cardViews[2] = view.findViewById(R.id.mag_card3);
        cardViews[3] = view.findViewById(R.id.mag_card4);
        cardViews[4] = view.findViewById(R.id.mag_card5);
        cardViews[5] = view.findViewById(R.id.mag_card6);
        cardViews[6] = view.findViewById(R.id.mag_card7);
        cardViews[7] = view.findViewById(R.id.mag_card8);
        cardViews[8] = view.findViewById(R.id.mag_card9);
        cardViews[9] = view.findViewById(R.id.mag_card10);
        idClient =  getArguments().getInt(KEY_BUNDLE);

        fbCos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CosCumparaturiActivity.class);
                intent.putExtra(KEY_COS, idClient);
                startActivity(intent);
            }
        });
        @SuppressLint("StaticFieldLeak") BackgroundWorker bwProduse = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject result = new JSONObject(s);
                    JSONArray rezArray = result.getJSONArray("rezultat");
                    if(rezArray!=null){
                        listaProduse.clear();
                        for(int i=0;i<rezArray.length();i++) {
                            Integer idProdus = Integer.parseInt(rezArray.getJSONObject(i).getString("id_produs"));
                            Integer idCategorie = Integer.parseInt(rezArray.getJSONObject(i).getString("id_categorie"));
                            String denumire = rezArray.getJSONObject(i).getString("denumire_produs");
                            String poza = rezArray.getJSONObject(i).getString("poza");
                            String descriere = rezArray.getJSONObject(i).getString("descriere_produs");
                            Integer stoc = Integer.parseInt(rezArray.getJSONObject(i).getString("stoc"));
                            Double pret = Double.parseDouble(rezArray.getJSONObject(i).getString("pret"));
                            Integer idMarime = Integer.parseInt(rezArray.getJSONObject(i).getString("id_marime"));
                            listaProduse.add(new Produs(idProdus, idCategorie, denumire, poza, descriere, stoc, pret, idMarime));
                        }

                        for(int i=0; i<listaProduse.size();i++){
                            Picasso.get().load(listaProduse.get(i).getPoza())
                                    .into(imgProd[i]);
                            denProd[i].setText(listaProduse.get(i).getDenumireProdus());
                            pretProd[i].setText(listaProduse.get(i).getPret().toString()+" lei");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for(int i=0;i<listaProduse.size();i++){
                    cardViews[i].setOnClickListener(doProdus(listaProduse.get(i).getIdProdus()));
                }
                if(listaProduse.size()<cardViews.length){
                    for(int i=listaProduse.size();i<cardViews.length;i++)
                        cardViews[i].setVisibility(View.INVISIBLE);
                }
            }
        };
        bwProduse.execute("getUltimeleProduse");
        return view;
    }

    private View.OnClickListener doProdus(final Integer codProdus){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProdus = new Intent(getContext(), ProdusActivity.class);
                intentProdus.putExtra(KEY_PRODUS, codProdus);
                intentProdus.putExtra(KEY_CLIENT_PRODUS, idClient);
                startActivity(intentProdus);
            }
        };
    }

    private View.OnClickListener doSearch(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etSearch.getText().toString().isEmpty() || etSearch.getText() == null){
                    etSearch.setError("Introduceți denumirea produsului căutat!");
                }
                else {
                    Intent intent = new Intent(getContext(), SearchProductActivity.class);
                    intent.putExtra(KEY_SEARCH, etSearch.getText().toString());
                    intent.putExtra(KEY_CLIENT_PRODUS, idClient);
                    startActivity(intent);
                }
            }
        };
    }

}
