package com.example.gtflashcards;

import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.gtflashcards.http_request.GTFlashcardsAPI;
import com.example.gtflashcards.objects.Deck;
import com.example.gtflashcards.objects.GTFlashcards;

public class MainActivity extends Activity {
	
	static ArrayList<Deck> decks = null;
	static ArrayList<GTFlashcards> fc = null;
	static ArrayList<String> courseDepts = null;
	
	static int currentDeckIndex = 0;
	static int currentFlashcardIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		decks = new ArrayList<Deck>();
		//dummy data
		decks.add(new Deck(0, "CS 4261"));
		decks.add(new Deck(0, "MATH 3215"));
		decks.add(new Deck(0, "CS1332"));
		
		//dummy flashcards
		fc = new ArrayList<GTFlashcards>();
		
		fc.add(new GTFlashcards(1, "rsaez", "Major carriers", "AT&T, T-MObile, Verizon, Sprint", "CS", "4261", 0, 0, true, false));
		fc.add(new GTFlashcards(1, "rsaez", "Major mobile operating systems", "Android, IOS, Windows", "CS", "4261", 0, 0, true, false));
		fc.add(new GTFlashcards(1, "rsaez", "What is JQuery", "Javascript library for implementing common functionality", "CS", "4261", 0, 0, true, false));
		fc.add(new GTFlashcards(1, "rsaez", "Project development methods", "Agile, waterfall", "CS", "4261", 0, 0, true, false));
		fc.add(new GTFlashcards(1, "rsaez", "Front-end web development laguages", "Javascript, HTML, CSS", "CS", "4261", 0, 0, true, false));
		fc.add(new GTFlashcards(1, "rsaez", "Mobile device platforms", "Feature phones smart phones, tablets, e-readers, embedded devices, wearables", "CS", "4261", 0, 0, true, false));
		fc.add(new GTFlashcards(1, "rsaez", "Mobile device constraints", "Screen size, battery life, touch interfaces", "CS", "4261", 0, 0, true, false));
		
		fc.add(new GTFlashcards(1, "rsaez", "Big-o of insertion sort", "O(n^2)", "CS", "1332", 0, 0, true, false));
		fc.add(new GTFlashcards(1, "rsaez", "What is in-place sorting", "when sorting doesnt need extra space", "CS", "1332", 0, 0, true, false));
		fc.add(new GTFlashcards(1, "rsaez", "Set", "unordered collection of entities, dulicates not allowed", "CS", "1332", 0, 0, true, false));
		fc.add(new GTFlashcards(1, "rsaez", "Bag", "unordered list, duplicates allowed", "CS", "1332", 0, 0, true, false));
		
		
		setContentView(R.layout.activity_main);
		
		downloadCourseDeptsFromServer();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.new_deck:
	        	goToNewDeckActivity(null);
	            return true;
	        case R.id.new_flashcard:
	        	goToNewFlashcardActivity(null);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
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
    
    public void goToNewDeckActivity(View view) {
    	Intent intent = new Intent(this, NewDeckActivity.class);
    	startActivity(intent);
    }
    
    public void goToCloudActivity(View view) {
    	Intent intent = new Intent(this, CloudActivity.class);
    	startActivity(intent);
    }
    
    public static ArrayList<String> getDeckNames() throws JSONException {
    	ArrayList<String> deckNames = new ArrayList<String>(); 
    	
    	//decks = GTFlashcardsAPI.listDeck();
    	
    	for (Deck deck : decks) {
    		deckNames.add(deck.getName());
    	}
    	return deckNames;
    }
    
    public static ArrayList<String> getFcNames() {
    	ArrayList<String> fcNames = new ArrayList<String>(); 
    	for (GTFlashcards flashcard : fc) {
    		fcNames.add(flashcard.getQuestion());
    	}
    	return fcNames;
    }
    
    public static ArrayList<String> getCourseDepts() {
    	if (courseDepts == null) {
    		downloadCourseDeptsFromServer();
    	}
    	return courseDepts;
    }
    
    public static void downloadCourseDeptsFromServer() {
    	//todo: download course codes from server here
    	/*
    	courseDepts = new ArrayList<String>();
    	courseDepts.add("CS");
    	courseDepts.add("MATH");
    	courseDepts.add("ECE");
    	*/
    	
    	courseDepts = GTFlashcardsAPI.getCourseDept();
    }
    
    public static GTFlashcards getCurrentFlashcard() {
    	if (currentDeckIndex > -1 && currentFlashcardIndex > -1) {
    		return decks.get(currentDeckIndex).getFlashcards().get(currentFlashcardIndex);
    	}
    	return null;
    }
    
    public static Deck getCurrentDeck() {
    	if (currentDeckIndex > -1) {
    		return decks.get(currentDeckIndex);
    	}
    	return null;
    }

}
