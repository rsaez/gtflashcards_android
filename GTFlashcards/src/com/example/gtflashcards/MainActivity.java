package com.example.gtflashcards;

import java.util.ArrayList;

import com.example.gtflashcards.objects.Deck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	static ArrayList<Deck> decks;

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
    
    public void goToNewFlashcardActivity(View view) {
    	Intent intent = new Intent(this, NewFlashcardActivity.class);
    	startActivity(intent);
    }
    
    public void goToMyDecksActivity(View view) {
    	Intent intent = new Intent(this, FlashcardListActivity.class);
    	startActivity(intent);
    }
    
    public void goToNewDeckActivity(View view) {
    	Intent intent = new Intent(this, NewDeckActivity.class);
    	startActivity(intent);
    }
    
    public static ArrayList<String> getDeckNames() {
    	ArrayList<String> deckNames = new ArrayList<String>(); 
    	for (Deck deck : decks) {
    		deckNames.add(deck.getName());
    	}
    	return deckNames;
    }

}
