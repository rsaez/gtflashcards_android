package com.example.gtflashcards;

import com.example.gtflashcards.objects.Deck;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewDeckActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_deck);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_deck, menu);
		return true;
	}
	
	public void createDeck(View view) {
		System.out.println("Create deck clicked");
		
		String deckName = ((EditText) findViewById(R.id.deck_name_input)).getText().toString();
		if (deckName.length() > 0) {
			System.out.println("DeckName="+deckName);
			int id = -1; //todo: use real id from server
	    
			Deck deck = new Deck(id, deckName);
			MainActivity.decks.add(deck);
			
			Toast.makeText(NewDeckActivity.this, "Successfully created deck", Toast.LENGTH_SHORT).show();
			super.finish();
		} else {
			Toast.makeText(NewDeckActivity.this, "Plese enter a valid deck name", Toast.LENGTH_SHORT).show();			
		}
	}

}
