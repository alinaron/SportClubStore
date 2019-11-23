package aron.alin.sportclubstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.Utils;

import static aron.alin.sportclubstore.Clase.Utils.REQ_GALERIE;

public class AdaugaPostareActivity extends AppCompatActivity implements Utils {

    private EditText etContinut;
    private ImageView imgView;
    private TextView tvButon;
    private Button btnAdauga, btnAnuleaza;
    private Uri imageUri;
    private Bitmap pozaProfil;
    private byte[] poza;
    private String pozaCodata = "null";
    private Intent intent;
    private Integer idClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_postare);
        etContinut = findViewById(R.id.postare_noua_continut);
        imgView = findViewById(R.id.postare_noua_poza);
        imgView.setVisibility(View.INVISIBLE);
        tvButon = findViewById(R.id.postare_noua_tv_poza);
        btnAdauga = findViewById(R.id.postare_noua_btn_adauga);
        btnAnuleaza = findViewById(R.id.postare_noua_btn_anuleaza);
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            idClient = bundle.getInt(KEY_ADAUGA_POSTARE);
        }
        tvButon.setOnClickListener(doFoto());
        btnAdauga.setOnClickListener(doAdauga());
        btnAnuleaza.setOnClickListener(doAnuleaza());
    }

    private View.OnClickListener doAnuleaza(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
    }

    private View.OnClickListener doFoto(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_GALERIE);
            }
        };
    }

    private View.OnClickListener doAdauga(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String continut = null;
                if (etContinut.getText() != null || etContinut.getText().toString().isEmpty())
                    continut = etContinut.getText().toString();
                if (continut.isEmpty() && pozaCodata.equals("null")) {
                    Toast.makeText(getApplicationContext(), "Eroare la adăugarea postării!", Toast.LENGTH_LONG).show();
                } else {
                    @SuppressLint("StaticFieldLeak") BackgroundWorker backgroundWorker = new BackgroundWorker() {
                        @Override
                        protected void onPostExecute(String s) {
                            try {
                                JSONObject object = new JSONObject(s);
                                Integer success = object.getInt("success");
                                if (success == 1) {
                                    Toast.makeText(getApplicationContext(), "Postarea a fost adăugată!", Toast.LENGTH_LONG).show();
                                    Intent intent1 = new Intent(getApplicationContext(),MainPageComunityActivity.class);
                                    intent1.putExtra(KEY_LOGIN, idClient);
                                    intent1.putExtra(KEY_POST, 1);
                                    startActivity(intent1);
                                    finish();

                                } else if (success == 0)
                                    Toast.makeText(getApplicationContext(), "Eroare la adăugarea postării!", Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    if(pozaCodata.equals("null"))
                        backgroundWorker.execute("insertPost2", continut, idClient.toString());
                    else
                        backgroundWorker.execute("insertPost", continut, pozaCodata, idClient.toString());
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQ_GALERIE) {
            if(data != null){
                imageUri = data.getData();
                imgView.setImageURI(imageUri);
                imgView.setVisibility(View.VISIBLE);
                pozaProfil = ((BitmapDrawable) imgView.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                pozaProfil.compress(Bitmap.CompressFormat.JPEG, 40, stream);
                poza = stream.toByteArray();
                pozaCodata = Base64.encodeToString(poza, Base64.DEFAULT);
            }
        }
    }
}
