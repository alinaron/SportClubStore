package aron.alin.sportclubstore;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.Utils;

public class LoginActivity extends AppCompatActivity implements Utils {

    private TextView tvParolaUitata;
    private EditText etUser, etPass;
    private Button btnLogheaza;
    private SharedPreferences sharedPreferences;
    private CheckBox checkBox;
    private BackgroundWorker backgroundWorker;
    private Integer idClient;
    private String uName, phone;
    private Bitmap icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        tvParolaUitata = findViewById(R.id.login_tv_am_uitat_parola);
        tvParolaUitata.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        etUser = findViewById(R.id.login_et_username);
        etPass = findViewById(R.id.login_et_password);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
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
        btnLogheaza = findViewById(R.id.login_autentifica_btn);
        btnLogheaza.setOnClickListener(doLogin());
        checkBox = findViewById(R.id.login_checkbox);
        checkBox.setChecked(true);
        tvParolaUitata.setOnClickListener(doParola());
        icon = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        restoreSharedPref();
    }


    private View.OnClickListener doLogin(){
        return new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    String username = etUser.getText().toString();
                    String password = etPass.getText().toString();
                    if(checkBox.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(SHARED_USER, username);
                        editor.putString(SHARED_PASS, password);
                        boolean result = editor.commit();
                    }
                    backgroundWorker = new BackgroundWorker(){
                        @Override
                        protected void onPostExecute(String s) {
                            try {
                                JSONObject object = new JSONObject(s);
                                int success = object.getInt("success");
                                if(success == 0){
                                    Toast.makeText(getApplicationContext(),"Username sau parolă greșite!", Toast.LENGTH_LONG).show();
                                    etUser.setError("Username sau parolă greșite!");
                                    etPass.setError("Username sau parolă greșite!");
                                }
                                else if (success == 1){
                                    etUser.setError(null);
                                    etPass.setError(null);
                                    JSONObject rezultat = object.getJSONObject("result");
                                    idClient = Integer.parseInt(rezultat.getString("id_client"));
                                    Intent intent = new Intent(getApplicationContext(), MainPageComunityActivity.class);
                                    intent.putExtra(KEY_LOGIN, idClient);
                                    startActivity(intent);
                                }
                            } catch (JSONException e) {
//                                e.printStackTrace();
                                Intent intent = new Intent(getApplicationContext(), ErrorActivity.class);
                                startActivity(intent);
                            }
                        }
                    };
                    backgroundWorker.execute("login", username, password);
                } else if ((etUser.getText() == null) || (etUser.getText().toString().trim().isEmpty()) || (etUser.getText().toString() == null)) {
                    etUser.setError("Introduceți numele de utilizator!");
                } else if ((etPass.getText() == null) || (etPass.getText().toString().trim().isEmpty()) || (etPass.getText().toString() == null)) {
                    etPass.setError("Introduceți parola!");
                }
            }
        };
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "myChannel";
            String description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private View.OnClickListener doParola(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.dialog_layout, null);
                final EditText nameBox = layout.findViewById(R.id.forgot_etUsername);
                final EditText phoneBox = layout.findViewById(R.id.forgot_etPhone);
                InputFilter[] editFilters = etUser.getFilters();
                InputFilter[] newFilters = new InputFilter[editFilters.length + 1];
                System.arraycopy(editFilters, 0, newFilters, 0, editFilters.length);
                newFilters[editFilters.length] = new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        return String.valueOf(source).toLowerCase();
                    }
                };
                nameBox.setFilters(newFilters);
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setView(layout);
                builder.setPositiveButton("Recuperează parola", new DialogInterface.OnClickListener() {
                    @SuppressLint("StaticFieldLeak")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (esteValid(nameBox, phoneBox)) {
                            uName = nameBox.getText().toString();
                            phone = phoneBox.getText().toString();
                            backgroundWorker = new BackgroundWorker() {
                                @Override
                                protected void onPostExecute(String s) {
                                    try {
                                        JSONObject object = new JSONObject(s);
                                        int success = object.getInt("success");
                                        if (success == 0) {
                                            Toast.makeText(getApplicationContext(),"Username sau număr de telefon greșite!", Toast.LENGTH_LONG).show();
                                        } else if (success == 1) {
                                            JSONObject rezultat = object.getJSONObject("result");
                                            String parola = rezultat.getString("password");
                                            String title = "Recuperare parolă";
                                            String body = "Parola dumneavoastră este: " + parola;
                                            createNotificationChannel();
                                            NotificationCompat.Builder builder = new NotificationCompat.Builder(LoginActivity.this, NOTIFICATION_CHANNEL_ID)
                                                    .setSmallIcon(R.drawable.logo)
                                                    .setContentTitle(title)
                                                    .setContentText(body)
                                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                                    .setLargeIcon(icon)
                                                    .setTimeoutAfter(8000);
                                            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(LoginActivity.this);
                                            notificationManager.notify(3321, builder.build());
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            backgroundWorker.execute("forgotPass", uName, phone);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Completați câmpurile obligatorii!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.setNegativeButton("Anulează", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        };
    }

    private void restoreSharedPref() {
        String username = sharedPreferences.getString(SHARED_USER, null);
        String password = sharedPreferences.getString(SHARED_PASS, null);

        etUser.setText(username);
        etPass.setText(password);
    }

    private boolean isValid() {
        if ((etUser.getText() == null) || (etUser.getText().toString().trim().isEmpty()) || (etUser.getText().toString() == null)) {
            return false;
        }
        if ((etPass.getText() == null) || (etPass.getText().toString().trim().isEmpty()) || (etPass.getText().toString() == null)) {
            return false;
        }
        return true;
    }

    private boolean esteValid(EditText e1, EditText e2) {
        if ((e1.getText() == null) || (e1.getText().toString().trim().isEmpty()) || (e1.getText().toString() == null)) {
            return false;
        }
        if ((e2.getText() == null) || (e2.getText().toString().trim().isEmpty()) || (e2.getText().toString() == null)) {
            return false;
        }
        return true;
    }
}
