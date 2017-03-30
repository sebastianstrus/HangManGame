package com.example.sebastianstrus.hangmangame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private Hangman game;
    private Button guessButton;
    private EditText guessEdit;
    private ImageView image;
    private TextView usedLetters; // A, B, E
    private TextView triesLeftText; // 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0
    private TextView wordText; //  ELEC-R-C


    private class GuessListener implements View.OnClickListener {
        private GuessListener() {
        }

        public void onClick(View v) {
            String guess = GameActivity.this.guessEdit.getText().toString();
            if (guess.length() == 1) {
                GameActivity.this.guess(guess.charAt(0));
                if (GameActivity.this.game.getTriesLeft() <= 0) {
                    GameActivity.this.showResult(false);
                }
                if (GameActivity.this.game.hasWon()) {
                    GameActivity.this.showResult(true);
                    return;
                }
                return;
            }
            GameActivity.this.inputError("You can enter only one letter!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.game = new Hangman(loadWords());
        this.image = (ImageView) findViewById(R.id.image);
        this.triesLeftText = (TextView) findViewById(R.id.tries_text);
        this.usedLetters = (TextView) findViewById(R.id.letters_text);
        this.wordText = (TextView) findViewById(R.id.word_text);
        this.guessEdit = (EditText) findViewById(R.id.guess_edit);
        this.guessButton = (Button) findViewById(R.id.guess_button);
        this.guessButton.setOnClickListener(new GuessListener());
        refresh();

    }

    private void guess(char c) {
        if (this.game.hasUsedLetter(c)) {
            inputError("You have already used this letter!");
        } else {
            this.game.guess(c);
        }
        refresh();
        this.guessEdit.setText("");
    }

    private void refresh() {
        this.wordText.setText(this.game.getWord());
        this.triesLeftText.setText(""+this.game.getTriesLeft()); // ""+
        this.usedLetters.setText(this.game.getLettersUsed());

        if(this.game.getTriesLeft() < 7) {
            this.triesLeftText.setTextColor(Color.parseColor("#FF9800"));
        }
        if(this.game.getTriesLeft() < 4) {
            this.triesLeftText.setTextColor(Color.parseColor("#DD2C00"));
        }
        refreshImage();
    }

    private void inputError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



    private ArrayList<String> loadWords() {
        ArrayList<String> words = new ArrayList();
        words.add("SEBASTIAN");
        words.add("FREDRIK");
        words.add("MARCUS");
        words.add("ANDROID");
        words.add("IPHONE");
        return words;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_activity_menu, menu);
        // return super.onCreateOptionsMenu(menu);
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
            case R.id.game_activity_ic_back:
                Intent intent1 = new Intent(this, MainMenuActivity.class);
                startActivity(intent1);
                return true;
            case R.id.game_activity_ic_info:
                Intent intent2 = new Intent(this, AboutActivity.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshImage() {
        ImageView im = (ImageView) findViewById(R.id.image);
        im.setImageResource(getResources().getIdentifier("hang"+this.game.getTriesLeft(), "drawable", getPackageName()));

    }
    private void showResult(boolean won) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(ResultActivity.RESULT_KEY, won);
        intent.putExtra(ResultActivity.WORD_KEY, this.game.getRealWord());
        intent.putExtra(ResultActivity.TRIES_KEY, this.game.getTriesLeft());
        startActivity(intent);
    }
}
