package aron.alin.sportclubstore;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PaintDrawable;
import android.location.Address;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.Client;
import aron.alin.sportclubstore.Clase.Utils;


public class SignInActivity extends AppCompatActivity implements Utils {

    private ImageView profilePhoto;
    private CardView littleThing;
    private EditText etUser, etPass, etConfirmPass, etNume, etPrenume, etTelefon, etBio;
    private RadioGroup rgSex;
    private RadioButton rbM, rbF;
    private Spinner spnSport;
    private TextView tvData;
    private Button btnSave;
    private DatePickerDialog.OnDateSetListener datepicker;
    private String username, password, nume, prenume, telefon, dataNastere, bio, sex;
    private Integer idSport;
    private BackgroundWorker backgroundWorker;
    private Bitmap pozaProfil;
    private String sport;
    private Client client;
    private byte[] poza;
    private String pozaCodata;
    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();
    }
    private void init(){
        spnSport = findViewById(R.id.spinner_sport);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.sporturi, R.layout.spinner_layout);
        spnSport.setAdapter(adapter);
        tvData = findViewById(R.id.sign_in_data);
        tvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int an = calendar.get(Calendar.YEAR);
                int luna = calendar.get(Calendar.MONTH);
                int zi = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(SignInActivity.this,
                        android.R.style.Theme_Holo_Light_Panel, datepicker, an, luna, zi);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dialog.show();
            }
        });

        datepicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month ++;
                String data = day+"-"+month+"-"+year;
                tvData.setText(data);
            }
        };
        profilePhoto = findViewById(R.id.sign_in_image_profile_pic);
        littleThing = findViewById(R.id.card_view_sign_in_plus);
        profilePhoto.setOnClickListener(doChange());
        etUser = findViewById(R.id.sign_in_username);
        etPass = findViewById(R.id.sign_in_password);
        etConfirmPass = findViewById(R.id.sign_in_password_confirm);
        etNume = findViewById(R.id.sign_in_nume);
        etPrenume = findViewById(R.id.sign_in_prenume);
        etTelefon = findViewById(R.id.sign_in_telefon);
        etBio = findViewById(R.id.sign_in_bio);
        rgSex = findViewById(R.id.sign_in_rg);
        rbM = findViewById(R.id.sign_in_sex_m);
        rbF = findViewById(R.id.sign_in_sex_f);
        InputFilter[] editFilters = etUser.getFilters();
        InputFilter[] newFilters = new InputFilter[editFilters.length + 1];
        System.arraycopy(editFilters, 0, newFilters, 0, editFilters.length);
        newFilters[editFilters.length] = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                return String.valueOf(source).toLowerCase();
            }
        };
        etUser.setFilters(newFilters);
        btnSave = findViewById(R.id.sign_in_next);
        btnSave.setOnClickListener(doSave());
    }

    private View.OnClickListener doChange(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_GALERIE);
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQ_GALERIE) {
            if(data != null){
                imageUri = data.getData();
                profilePhoto.setImageURI(imageUri);
                littleThing.setVisibility(View.INVISIBLE);
            }
        }
    }
    private boolean isValid(){
        if(etUser.getText() == null || etUser.getText().toString() == null || etUser.getText().toString().isEmpty())
            return false;
        else if(etPass.getText() == null || etPass.getText().toString() == null || etPass.getText().toString().isEmpty())
            return false;
        else if(etConfirmPass.getText() == null || etConfirmPass.getText().toString() == null || etConfirmPass.getText().toString().isEmpty())
            return false;
        else if(etNume.getText() == null || etNume.getText().toString() == null || etNume.getText().toString().isEmpty())
            return false;
        else if(etPrenume.getText() == null || etPrenume.getText().toString() == null || etPrenume.getText().toString().isEmpty())
            return false;
        else if(etTelefon.getText() == null || etTelefon.getText().toString() == null || etTelefon.getText().toString().isEmpty())
            return false;
        else if(tvData.getText() == null || tvData.getText().toString() == null || tvData.getText().toString().isEmpty())
            return false;
        else if(spnSport.getSelectedItem().toString()==null || spnSport.getSelectedItem().toString().isEmpty())
            return false;
        else if(etTelefon.getText().toString().length()!=10)
            return false;
        else if(etPass.getText().toString().length()<6)
            return false;
        else if(!rbF.isChecked() && !rbM.isChecked())
            return false;
        else if(etUser.getText().toString().length()<5)
            return false;
        return true;
    }
    private View.OnClickListener doSave(){
        return new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {

                if(isValid()){
                    if(!etPass.getText().toString().equals(etConfirmPass.getText().toString())){
                        etConfirmPass.setError("Parolele nu coincid!" );
                    }
                    else {
                        if (etBio.getText()==null || etBio.getText().toString() == null || etBio.getText().toString().isEmpty()) {
                            bio = "";
                        }
                        else bio = etBio.getText().toString();
                        username = etUser.getText().toString();
                        password = etPass.getText().toString();
                        nume = etNume.getText().toString();
                        prenume = etPrenume.getText().toString();
                        telefon = etTelefon.getText().toString();
                        if(rbF.isChecked())
                            sex = "F";
                        else if (rbM.isChecked())
                            sex = "M";
                        dataNastere = tvData.getText().toString();
                        sport = spnSport.getSelectedItem().toString();

                        ////////////////////////////////////////////////////////////////////
                        pozaProfil = ((BitmapDrawable)profilePhoto.getDrawable()).getBitmap();
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        pozaProfil.compress(Bitmap.CompressFormat.JPEG, 40, stream);
                        poza = stream.toByteArray();
                        pozaCodata = Base64.encodeToString(poza, Base64.DEFAULT);
                        ////////////////////////////////////////////////////////////////////

                        backgroundWorker = new BackgroundWorker(){
                            @Override
                            protected void onPostExecute(String s) {
                                if(s!=null) {
                                    try {
                                        JSONObject object = new JSONObject(s);
                                        int success = object.getInt("success");
                                        if(success==0){
                                            etUser.setError("Username indisponibil! Încercați altă variantă!");
                                        }
                                        else if(success==1){
                                            BackgroundWorker backgroundWorker1 = new BackgroundWorker(){
                                                @Override
                                                protected void onPostExecute(String s) {
                                                    try {
                                                        JSONObject object1 = new JSONObject(s);
                                                        int success2 = object1.getInt("success");
                                                        if(success2==0){
                                                            Toast.makeText(getApplicationContext(),"S-a produs o eroare!",Toast.LENGTH_LONG).show();
                                                        }
                                                        else if(success2==1){
                                                            JSONObject rezultat = object1.getJSONObject("result");
                                                            idSport = Integer.parseInt(rezultat.getString("id_sport"));
                                                            client = new Client(username, password, nume, prenume, telefon, dataNastere, idSport, sex, null, pozaCodata, bio);
                                                            Intent intent = new Intent(getApplicationContext(), AdressActivity.class);
                                                            intent.putExtra(KEY_CLIENT, client);
                                                            startActivity(intent);
                                                        }
                                                    } catch (JSONException e) {
//                                                        e.printStackTrace();
                                                        Intent intent = new Intent(getApplicationContext(), ErrorActivity.class);
                                                        startActivity(intent);
                                                    }
                                                }
                                            };
                                            backgroundWorker1.execute("sport", sport);
                                        }
                                    } catch (JSONException e) {
//                                        e.printStackTrace();
                                        Intent intent = new Intent(getApplicationContext(), ErrorActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            }
                        };
                        backgroundWorker.execute("username", username);
                    }
                }
                else {
                    if(etUser.getText() == null || etUser.getText().toString() == null || etUser.getText().toString().isEmpty())
                        etUser.setError("Introduceți un username valid!");
                    else if(etPass.getText() == null || etPass.getText().toString() == null || etPass.getText().toString().isEmpty())
                        etPass.setError("Introduceți parola!");
                    else if(etConfirmPass.getText() == null || etConfirmPass.getText().toString() == null || etConfirmPass.getText().toString().isEmpty())
                        etConfirmPass.setError("Confirmați parola!");
                    else if(etNume.getText() == null || etNume.getText().toString() == null || etNume.getText().toString().isEmpty())
                        etNume.setError("Introduceți numele de familie!");
                    else if(etPrenume.getText() == null || etPrenume.getText().toString() == null || etPrenume.getText().toString().isEmpty())
                        etPrenume.setError("Introduceți prenumele!");
                    else if(etTelefon.getText() == null || etTelefon.getText().toString() == null || etTelefon.getText().toString().isEmpty())
                        etTelefon.setError("Introduceți numărul de telefon!");
                    else if(tvData.getText() == null || tvData.getText().toString() == null || tvData.getText().toString().isEmpty())
                        tvData.setError("Introduceți data nașterii!");
                    else if(etTelefon.getText().toString().length()!=10)
                        etTelefon.setError("Număr de telefon invalid!");
                    else if(!rbF.isChecked() && !rbM.isChecked())
                        Toast.makeText(getApplicationContext(),"Selectați sexul!",Toast.LENGTH_LONG).show();
                    else if(etPass.getText().toString().length()<6)
                        etPass.setError("Introduceți o parolă mai puternică!");
                    else if(etUser.getText().toString().length()<5)
                        etUser.setError("Numele de utilizator trebuie sa conțină cel puțin 5 caractere!");
                }
            }
        };
    }
}
