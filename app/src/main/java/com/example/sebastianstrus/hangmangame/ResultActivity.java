package com.example.sebastianstrus.hangmangame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    public static final String RESULT_KEY = "RESULT";
    public static final String WORD_KEY = "WORD";
    public static final String TRIES_KEY = "TRIES";

    private static TextView resultText;
    private static TextView triesText;
    private static TextView wordText;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        boolean won = extras.getBoolean(RESULT_KEY);// "RESULT_KEY"
        String word = extras.getString(WORD_KEY);
        String tries = String.valueOf(extras.getInt(TRIES_KEY));
        this.resultText = (TextView) findViewById(R.id.result_text);
        this.wordText = (TextView) findViewById(R.id.word_text);
        this.triesText = (TextView) findViewById(R.id.tries_text);
        this.resultText.setText(won ? R.string.result_won : R.string.result_lost);
        this.wordText.setText(word);
        this.triesText.setText(tries);
    }



    public void go_back_button_pressed(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.result_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.result_activity_ic_play:
                Intent intent1 = new Intent(this, GameActivity.class);
                startActivity(intent1);
                return true;
            case R.id.result_activity_ic_info:
                Intent intent2 = new Intent(this, AboutActivity.class);
                startActivity(intent2);
                return true;
            case R.id.result_activity_ic_back:
                Intent intent3 = new Intent(this, MainMenuActivity.class);
                startActivity(intent3);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
