package com.example.gtflashcards;

import java.util.ArrayList;

import com.example.gtflashcards.objects.Deck;
import com.example.gtflashcards.objects.Flashcard;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	static ArrayList<Deck> decks;
	static ArrayList<Flashcard> fc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		decks = new ArrayList<Deck>();
		//dummy data
		decks.add(new Deck(0, "CS 4261"));
		decks.add(new Deck(0, "MATH 3215"));
		decks.add(new Deck(0, "Deck 3"));
		
		//dummy flashcards
		fc = new ArrayList<Flashcard>();
		
		fc.add(new Flashcard(1, "rsaez", "Major carriers", "AT&T, T-MObile, Verizon, Sprint", "CS", "4261", 0, 0, true, false));
		fc.add(new Flashcard(1, "rsaez", "Major mobile operating systems", "Android, IOS, Windows", "CS", "4261", 0, 0, true, false));
		fc.add(new Flashcard(1, "rsaez", "What is JQuery", "Javascript library for implementing common functionality", "CS", "4261", 0, 0, true, false));
		fc.add(new Flashcard(1, "rsaez", "Project development methods", "Agile, waterfall", "CS", "4261", 0, 0, true, false));
		fc.add(new Flashcard(1, "rsaez", "Front-end web development laguages", "Javascript, HTML, CSS", "CS", "4261", 0, 0, true, false));
		fc.add(new Flashcard(1, "rsaez", "Mobile device platforms", "Feature phones smart phones, tablets, e-readers, embedded devices, wearables", "CS", "4261", 0, 0, true, false));
		fc.add(new Flashcard(1, "rsaez", "Mobile device constraints", "Screen size, battery life, touch interfaces", "CS", "4261", 0, 0, true, false));
		
		fc.add(new Flashcard(1, "rsaez", "Big-o of insertion sort", "O(n^2)", "CS", "1332", 0, 0, true, false));
		fc.add(new Flashcard(1, "rsaez", "What is in-place sorting", "when sorting doesnt need extra space", "CS", "1332", 0, 0, true, false));
		fc.add(new Flashcard(1, "rsaez", "Set", "unordered collection of entities, dulicates not allowed", "CS", "1332", 0, 0, true, false));
		fc.add(new Flashcard(1, "rsaez", "Bag", "unordered list, duplicates allowed", "CS", "1332", 0, 0, true, false));		
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
    	String url = "https://login.gatech.edu/cas/login";
    	Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		startActivity(myIntent);
    }
    
    public void goToDeckListActivity(View view) {
    	Intent intent = new Intent(this, DeckListActivity.class);
    	startActivity(intent);
    }
    
    public void goToNewFlashcardActivity(View view) {
    	Intent intent = new Intent(this, NewFlashcardActivity.class);
    	startActivity(intent);
    }
    
    public void goToFlashcardListActivity(View view) {
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
