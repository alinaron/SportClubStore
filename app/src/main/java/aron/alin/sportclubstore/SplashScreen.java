package aron.alin.sportclubstore;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class SplashScreen extends AppCompatActivity {

    private ImageView logo;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logo = findViewById(R.id.splash_logo);
        mediaPlayer = MediaPlayer.create(this, R.raw.sportsound);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animatie);
        logo.startAnimation(animation);
        mediaPlayer.start();
        final Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
