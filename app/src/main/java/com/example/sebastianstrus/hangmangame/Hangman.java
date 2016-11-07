package com.example.sebastianstrus.hangmangame;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by sebastianstrus on 27/10/16, represents the Hangman Game.
 */
public class Hangman {
    private ArrayList<String> allWords;
    private boolean[] correctLetters;
    private int lettersLeft;
    private List<Character> lettersUsed;
    private Random random;
    private int triesLeft;
    private char[] word;


    /**
     * Creates a new Hangman game, and chooses a word.
     * @param allWords -  a list of all words the game can choose from
     */
    public Hangman(ArrayList<String> allWords) {
        this.random = new Random();
        this.triesLeft = 10;
        this.allWords = allWords;
        newWord();
    }

    /**
     * Choose a new word.
     */
    public void newWord() {
        if (this.allWords.size() > 0) {
            this.word = ((String) this.allWords.get(this.random.nextInt(this.allWords.size()))).toCharArray();
            String s = "";
            for (char append : this.word) {
                s = new StringBuilder(String.valueOf(s)).append(append).toString();
            }
            Log.d("hej", "Chose word " + s);
        } else {
            this.word = "NOWORD".toCharArray();
        }
        this.correctLetters = new boolean[this.word.length];
        this.lettersUsed = new ArrayList();
        this.lettersLeft = this.word.length;
    }


    /**
     * @return Returns the current word, hiding all the letters the user hasn't guessed yet Example: Word is "NIKLAS".
     */
    public String getWord() {
        char[] showWord = new char[this.word.length];
        int i = 0;
        while (i < this.word.length) {
            showWord[i] = this.correctLetters[i] ? this.word[i] : '-';
            i++;
        }
        return new String(showWord);
    }


    /**
     * @return Returns the current word, without any hidden letters.
     */
    public String getRealWord() {
        return new String(this.word);
    }


    /**
     * Makes a guess for a letter.
     * @param guess -  the letter the user has entered
     */
    public void guess(char guess) {
        guess = Character.toUpperCase(guess);
        boolean guessIsCorrect = false;
        for (int i = 0; i < this.word.length; i++) {
            if (this.word[i] == guess) {
                guessIsCorrect = true;
                this.correctLetters[i] = true;
                this.lettersLeft--;
            }
        }
        if (!guessIsCorrect) {
            this.triesLeft--;
        }
        this.lettersUsed.add(Character.valueOf(guess));
    }


    /**
     * @return Returns the number of tries left.
     */
    public int getTriesLeft() {
        return this.triesLeft;
    }


    /**
     * Checks to see if the supplied char has been guessed for already (erroneously or correctly)
     * @param c - the letter
     * @return true if letter has been used already, false if letter is free
     */
    public boolean hasUsedLetter(char c) {
        c = Character.toUpperCase(c);
        for (int i = 0; i < this.lettersUsed.size(); i++) {
            if (((Character) this.lettersUsed.get(i)).charValue() == c) {
                return true;
            }
        }
        return false;
    }


    /**
     * @return Returns a String with all guesses the user has made (Example: "A, B, Q")
     */
    public String getLettersUsed() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.lettersUsed.size(); i++) {
            if (i == 0) {
                sb.append(this.lettersUsed.get(i));
            } else {
                sb.append(", " + this.lettersUsed.get(i));
            }
        }
        return sb.toString();
    }


    /**
     * Checks to see if the user has guessed all letters correctly
     * @return true if user has won (all letters correctly guessed), false if not done yet
     */
    public boolean hasWon() {
        return this.lettersLeft <= 0;
    }
}
