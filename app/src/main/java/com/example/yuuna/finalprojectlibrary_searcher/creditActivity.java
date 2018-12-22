package com.example.yuuna.finalprojectlibrary_searcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class creditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        Animation translatebu= AnimationUtils.loadAnimation(this, R.anim.creditanimation);
        translatebu.setRepeatMode(Animation.INFINITE);
        translatebu.setRepeatMode(Animation.REVERSE);
        ((TextView)findViewById(R.id.creditnum)).startAnimation(translatebu);
        ((TextView)findViewById(R.id.creditname)).startAnimation(translatebu);
    }
}
