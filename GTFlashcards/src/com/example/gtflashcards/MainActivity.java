package com.example.gtflashcards;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	ArrayList<Deck> decks;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		decks = new ArrayList<Deck>();
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/** Called when the user clicks the Login button */
    public void login(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, LoginActivity.class);
    	startActivity(intent);
    }
    
    public void createDeck() {
    	int id = 1; //todo: use real id from server
    	String name = "newDeck"; //todo: get name from input field on activity
    	int flashcardCount = 0; //todo: get real count from server
    	
    	Deck deck = new Deck(id, name, flashcardCount);
    	decks.add(deck);
    }
    
    public void goToNewFlashcardActivity(View view) {
    	Intent intent = new Intent(this, NewFlashcardActivity.class);
    	startActivity(intent);
    }
    
    public void goToMyDecksActivity(View view) {
    	Intent intent = new Intent(this, DeckListActivity.class);
    	startActivity(intent);
    }

}
