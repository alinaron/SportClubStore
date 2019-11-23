package aron.alin.sportclubstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import aron.alin.sportclubstore.Clase.BackgroundWorker;
import aron.alin.sportclubstore.Clase.Utils;

public class MainPageComunityActivity extends AppCompatActivity implements Utils {

    private boolean doubleBackToExitPressedOnce = false;
    private Integer idClient;
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private HomeFragment homeFragment;
    private LocatiiFragment locatiiFragment;
    private CompetitiiFragment competitiiFragment;
    private FluxFragment fluxFragment;
    private MagazinFragment magazinFragment;
    private Integer codVerif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_comunity);
        bottomNavigationView = findViewById(R.id.main_page_nav_view);
        frameLayout = findViewById(R.id.main_page_frame);
        homeFragment = new HomeFragment();
        locatiiFragment = new LocatiiFragment();
        competitiiFragment = new CompetitiiFragment();
        fluxFragment = new FluxFragment();
        magazinFragment = new MagazinFragment();

        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null)
        {
            idClient = bundle.getInt(KEY_LOGIN);
            codVerif = bundle.getInt(KEY_POST);
        }

        if(codVerif == 1)
            setFragment(fluxFragment);
        else
            setFragment(homeFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_acasa:
                        setFragment(homeFragment);
                        return true;
                    case R.id.nav_locatii:
                        setFragment(locatiiFragment);
                        return true;
                    case R.id.nav_competitii:
                        setFragment(competitiiFragment);
                        return true;
                    case R.id.nav_flux:
                        setFragment(fluxFragment);
                        return true;
                    case R.id.nav_magazin:
                        setFragment(magazinFragment);
                        return true;
                    default:
                        return false;

                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Apasă butonul BACK din nou pentru a te deloga", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    private void setFragment (final Fragment fragment){
        Bundle bundle1 = new Bundle();
        bundle1.putInt(KEY_BUNDLE, idClient);
        fragment.setArguments(bundle1);
        android.support.v4.app.FragmentTransaction fragmentTransaction  = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_page_frame, fragment);
        fragmentTransaction.commit();

    }

}
