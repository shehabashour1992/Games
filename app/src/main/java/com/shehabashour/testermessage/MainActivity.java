package com.shehabashour.testermessage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.shehabashour.testermessage.BaloonGame.BalloonActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         getWindow().setBackgroundDrawableResource(R.drawable.beautiful);


        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(this, BalloonActivity.class));

                break;
            case R.id.button2:
                startActivity(new Intent(this, MathActivity.class));

                break;
        }
    }


}
