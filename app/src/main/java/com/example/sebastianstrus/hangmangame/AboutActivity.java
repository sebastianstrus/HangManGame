package com.example.sebastianstrus.hangmangame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void go_back_button_pressed(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

/*
    class AboutActivity implements View.OnClickListener {
        AboutActivity() {
        }
        public void onClick(View v) {
            MainActivity.this.startActivity(new Intent(AboutActivity.this, MainActivity.class));
        }
    }*/
/*
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0092R.menu.about, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == C0092R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
