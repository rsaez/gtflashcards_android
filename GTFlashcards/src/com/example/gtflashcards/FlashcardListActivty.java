package com.example.gtflashcards;

import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONException;

import com.example.gtflashcards.http_request.GTFlashcardsRestClient;
import com.example.gtflashcards.objects.Deck;
import com.example.gtflashcards.objects.GTFlashcards;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class FlashcardListActivty extends ListActivity {

	ListView listview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flashcard_list);
		setupActionBar();
		
		Intent intent = getIntent();
		
		final String deckId = intent.getExtras().getString("deck_id");
		final String deckName = intent.getExtras().getString("deck_name");
		
		Log.v("Intent", "deck id = " + deckId + ", deck name = " + deckName);
		
		//this.setTitle(MainActivity.getCurrentDeck().getName());
		this.setTitle(deckName);
		
    	RequestParams params = new RequestParams();
    	params.put("deck_id", deckId);
		
    	final ArrayList<GTFlashcards> cardsInDeck = new ArrayList<GTFlashcards>();
    	final ArrayList<String> questions = new ArrayList<String>();
    	
		GTFlashcardsRestClient.post("cards_in_deck", params, new JsonHttpResponseHandler() {
			
			@Override
		    public void onSuccess(JSONArray cards) {
		        
				Log.v("Rest Call", "List Cards in Deck = " + cards.length());

		        for (int i = 0; i < cards.length(); i++) {
		        	try {
		        		//Log.v("Rest Call", "List Decks, id = " + decks.getJSONObject(i).getInt("id") + ", name = " + decks.getJSONObject(i).getString("name"));
		        		       		
		        		int id = cards.getJSONObject(i).getInt("id");
		        		int upvotes = cards.getJSONObject(i).getInt("upvotes");
		        		int downvotes = cards.getJSONObject(i).getInt("downvotes");
		        		
		        		String creator = cards.getJSONObject(i).getString("creator");
		        		String question = cards.getJSONObject(i).getString("question").replace("%20", " ");
		        		String answer = cards.getJSONObject(i).getString("answer").replace("%20", " ");
		        		String courseDept = cards.getJSONObject(i).getString("course_dept");
		        		String courseCode = cards.getJSONObject(i).getString("course_code");;
		        		
		        		int isPrivate = cards.getJSONObject(i).getInt("private");
		        		int isKnown = cards.getJSONObject(i).getInt("known");		        		

		        		//Log.v("Rest Call", "card detail = " + id + upvotes + downvotes + creator + question + answer + courseDept + courseCode + isPrivate + isKnown);
		        		boolean isPublic;
		        		boolean isKnownBool;
		        		boolean isAnonymous;
		        		
		        		if (isPrivate == 1) isPublic = true;
		        		else isPublic = false;
		        		
		        		if (isKnown == 1) isKnownBool = true;
		        		else isKnownBool = false;
		        		
		        		if (creator.compareTo("anonymous") == 0) isAnonymous = true;
		        		else isAnonymous = false;
		        		
		        		cardsInDeck.add(new GTFlashcards(id, creator, question, answer, courseDept, courseCode, upvotes, downvotes, isPublic, isAnonymous));
		        		
		        		
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		        
		        for (GTFlashcards flashcard : cardsInDeck) {
		        	questions.add(flashcard.getQuestion());
		        }
		        
				setListAdapter(new ArrayAdapter<String>(FlashcardListActivty.this,
						android.R.layout.simple_list_item_1, questions));
				
				//MainActivity.fc = cardsInDeck;
				
				listview = getListView();
				listview.setOnItemClickListener(new OnItemClickListener(){

			        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
			        	MainActivity.currentFlashcardIndex = position;
			        	Intent intent = new Intent(getApplicationContext(), FlashcardActivity.class);
			        	
			        	intent.putExtra("deck_id", "" + deckId);
			        	intent.putExtra("deck_name", "" + deckName);
			        	
			            startActivity(intent);
			       }
				});
			}
		});
		
		
		//setListAdapter(new ArrayAdapter<String>(this,
			//	android.R.layout.simple_list_item_1, MainActivity.getDeckNames()));
		
		/*
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, MainActivity.decks.get(MainActivity.currentDeckIndex).getFlashcardNames()));
		
		listview = getListView();
		listview.setOnItemClickListener(new OnItemClickListener(){

	        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
	        	MainActivity.currentFlashcardIndex = position;
	        	Intent intent = new Intent(getApplicationContext(), FlashcardActivity.class);
	            startActivity(intent);
	       }
		});
		
		*/
		
		ShakeListener mShaker = new ShakeListener(this);
	    mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
	    	public void onShake(){
	    		shuffle();
	    	}
	    });
		

	}
	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.flashcard_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				NavUtils.navigateUpFromSameTask(this);
				return true;
			case R.id.shuffle_option:
	        	shuffle();
	            return true;
			case R.id.rename_option:
	        	renameDeck();
	            return true;
	        case R.id.delete_option:
	        	deleteDeck();
	            return true;
	        case R.id.new_flashcard:
	        	goToNewFlashcardActivity(null);
	            return true;
	        
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void goToNewFlashcardActivity(View view) {
    	Intent intent = new Intent(this, NewFlashcardActivity.class);
    	startActivity(intent);
    }
    
	void shuffle() {
		Deck deck = MainActivity.getCurrentDeck();
		ArrayList<GTFlashcards> list = deck.getFlashcards();
		Collections.shuffle(list);
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, deck.getFlashcardNames()));
		
	}
	
	void renameDeck() {
		//todo: implement rename deck
	}

	void deleteDeck() {
		//todo: implement delete deck
	}
}
