package aron.alin.sportclubstore;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.Client;
import aron.alin.sportclubstore.Clase.Utils;

public class ModificaDateActivity extends AppCompatActivity implements Utils {

    private Integer idClient;
    private ImageView ivPoza;
    private EditText etUsername, etPassword, etNume, etPrenume, etTelefon;
    private TextView tvData;
    private Spinner spnSport;
    private RadioGroup rgSex;
    private RadioButton rbM, rbF;
    private EditText etBio;
    private Button btnAnuleaza, btnSalveaza;
    private DatePickerDialog.OnDateSetListener datepicker;
    private Uri imageUri;
    String bio, username, password, nume, prenume, telefon, sex, dataNastere, pozaCodata;
    Integer idSport;
    Bitmap pozaProfil;
    byte[] poza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_date);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            idClient = bundle.getInt(KEY_MODIFICA_DATE);
        }
        ivPoza = findViewById(R.id.modifica_image_profile_pic);
        etUsername = findViewById(R.id.modifica_username);
        etPassword = findViewById(R.id.modifica_password);
        etNume = findViewById(R.id.modifica_nume);
        etPrenume = findViewById(R.id.modifica_prenume);
        etTelefon = findViewById(R.id.modifica_telefon);
        tvData = findViewById(R.id.modifica_data);
        spnSport = findViewById(R.id.modifica_spinner_sport);
        rgSex = findViewById(R.id.modifica_rg);
        rbM = findViewById(R.id.modifica_sex_m);
        rbF = findViewById(R.id.modifica_sex_f);
        etBio = findViewById(R.id.modifica_bio);
        btnAnuleaza = findViewById(R.id.modifica_btn_anuleaza);
        btnSalveaza = findViewById(R.id.modifica_btn_salveaza);
        tvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int an = calendar.get(Calendar.YEAR);
                int luna = calendar.get(Calendar.MONTH);
                int zi = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(ModificaDateActivity.this,
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
        InputFilter[] editFilters = etUsername.getFilters();
        InputFilter[] newFilters = new InputFilter[editFilters.length + 1];
        System.arraycopy(editFilters, 0, newFilters, 0, editFilters.length);
        newFilters[editFilters.length] = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                return String.valueOf(source).toLowerCase();
            }
        };
        etUsername.setFilters(newFilters);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.sporturi, R.layout.spinner_layout);
        spnSport.setAdapter(adapter);
        @SuppressLint("StaticFieldLeak") BackgroundWorker backgroundWorker = new BackgroundWorker(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    JSONObject rezultat = object.getJSONObject("result");
                    String username = rezultat.getString("username");
                    String password = rezultat.getString("password");
                    String nume = rezultat.getString("nume");
                    String prenume = rezultat.getString("prenume");
                    String telefon = rezultat.getString("telefon");
                    String data = rezultat.getString("data_nastere");
                    Integer idSport = Integer.parseInt(rezultat.getString("id_sport"));
                    String sex = rezultat.getString("sex");
                    Integer idAdresa = Integer.parseInt(rezultat.getString("id_adresa"));
                    String poza = rezultat.getString("poza");
                    String bio = rezultat.getString("bio");
                    etUsername.setText(username);
                    etPassword.setText(password);
                    etNume.setText(nume);
                    etPrenume.setText(prenume);
                    etTelefon.setText(telefon);
                    tvData.setText(data);
                    etBio.setText(bio);
                    if(sex.equals("M"))
                        rbM.setChecked(true);
                    else if(sex.equals("F"))
                        rbF.setChecked(true);
                    spnSport.setSelection(idSport-1);
                    byte[] poz = Base64.decode(poza,Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(poz,0, poz.length);
                    ivPoza.setImageBitmap(bitmap);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundWorker.execute("mainpagecomunity", idClient.toString());
        ivPoza.setOnClickListener(doChange());
        btnAnuleaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSalveaza.setOnClickListener(doSave());
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
                ivPoza.setImageURI(imageUri);
            }
        }
    }
    private boolean isValid(){
        if(etUsername.getText() == null || etUsername.getText().toString() == null || etUsername.getText().toString().isEmpty())
            return false;
        else if(etPassword.getText() == null || etPassword.getText().toString() == null || etPassword.getText().toString().isEmpty())
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
        else if(etPassword.getText().toString().length()<6)
            return false;
        else if(!rbF.isChecked() && !rbM.isChecked())
            return false;
        else if(etUsername.getText().toString().length()<5)
            return false;
        return true;
    }
    private View.OnClickListener doSave(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()){
                    if (etBio.getText()==null || etBio.getText().toString() == null || etBio.getText().toString().isEmpty()) {
                        bio = "";
                    }
                    else bio = etBio.getText().toString();
                    username = etUsername.getText().toString();
                    password = etPassword.getText().toString();
                    nume = etNume.getText().toString();
                    prenume = etPrenume.getText().toString();
                    telefon = etTelefon.getText().toString();
                    if(rbF.isChecked())
                        sex = "F";
                    else if (rbM.isChecked())
                        sex = "M";
                    dataNastere = tvData.getText().toString();
                    idSport = spnSport.getSelectedItemPosition() + 1;
                    pozaProfil = ((BitmapDrawable)ivPoza.getDrawable()).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    pozaProfil.compress(Bitmap.CompressFormat.JPEG, 40, stream);
                    poza = stream.toByteArray();
                    pozaCodata = Base64.encodeToString(poza, Base64.DEFAULT);
                    @SuppressLint("StaticFieldLeak") BackgroundWorker bwUpdate = new BackgroundWorker(){
                        @Override
                        protected void onPostExecute(String s) {
                            try {
                                JSONObject object = new JSONObject(s);
                                Integer ok = Integer.parseInt(object.getString("success"));
                                if(ok == 1){
                                    Toast.makeText(getApplicationContext(), "Datele au fost actualizate cu succes!", Toast.LENGTH_LONG).show();
                                    finish();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Eroare la actualizarea datelor!", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    bwUpdate.execute("updateUser", idClient.toString(), username, password, nume, prenume, telefon, dataNastere, idSport.toString(), sex, pozaCodata, bio);
                }
                else {
                    if(etUsername.getText() == null || etUsername.getText().toString() == null || etUsername.getText().toString().isEmpty())
                        etUsername.setError("Introduceți un username valid!");
                    else if(etPassword.getText() == null || etPassword.getText().toString() == null || etPassword.getText().toString().isEmpty())
                        etPassword.setError("Introduceți parola!");
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
                    else if(etPassword.getText().toString().length()<6)
                        etPassword.setError("Introduceți o parolă mai puternică!");
                    else if(etUsername.getText().toString().length()<5)
                        etUsername.setError("Numele de utilizator trebuie să conțină cel puțin 5 caractere!");
                }
            }
        };
    }
}
