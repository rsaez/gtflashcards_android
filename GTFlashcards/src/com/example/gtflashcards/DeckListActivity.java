
package com.example.gtflashcards;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gtflashcards.http_request.DeckJsonHttpResponseHandler;
import com.example.gtflashcards.http_request.GTFlashcardsAPI;
import com.example.gtflashcards.http_request.GTFlashcardsRestClient;
import com.example.gtflashcards.objects.Deck;
import com.loopj.android.http.JsonHttpResponseHandler;

public class DeckListActivity extends ListActivity {
	
	Deck deck;
	ListView listview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deck_list);

		setupActionBar();
		final ArrayList<Deck> deckList = new ArrayList<Deck>();
		final ArrayList<String> deckListString = new ArrayList<String>();
		
		GTFlashcardsRestClient.get("deck", null, new JsonHttpResponseHandler() {
			@Override
		    public void onSuccess(JSONArray decks) {
		        Log.v("Rest Call", "List Decks");
				Log.v("Rest Call", "List Decks, number of json rows " + decks.length());

		        for (int i = 0; i < decks.length(); i++) {
		        	try {
		        		//Log.v("Rest Call", "List Decks, id = " + decks.getJSONObject(i).getInt("id") + ", name = " + decks.getJSONObject(i).getString("name"));
		        		
		        		Deck d = new Deck(decks.getJSONObject(i).getInt("id"), decks.getJSONObject(i).getString("name"));
						deckList.add(d);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		        
		        
		        Log.v("Rest Call", "List Decks, number of convertion rows " + deckList.size());
		        
		    	for (Deck deck : deckList) {
		    		deckListString.add(deck.getName());
		    	}
		    	
				setListAdapter(new ArrayAdapter<String>(DeckListActivity.this,
						android.R.layout.simple_list_item_1, deckListString));
						
				listview = getListView();
				listview.setOnItemClickListener(new OnItemClickListener(){

			        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
			        	MainActivity.currentDeckIndex = position;
			        	Log.v("Rest Call", "List Decks, position = " + position);
			        	
			        	//System.out.println("***item="+listview.getItemAtPosition(position));
			        	
			        	Intent intent = new Intent(getApplicationContext(), FlashcardListActivty.class);
			        	intent.putExtra("deck_id", "" + deckList.get(position).getId());
			        	intent.putExtra("deck_name", "" + deckList.get(position).getName());
			        	
			            //intent.putExtra("deck_name", listview.getItemAtPosition(position).toString());
			            startActivity(intent);
			       }
				});
		        
			}
		});
		
		/*
		try {
			deckList = GTFlashcardsAPI.listDeck();
			
			Log.v("Rest Call", "List Decks, number of rows " + deckList.size());
			
	    	for (Deck deck : deckList) {
	    		deckListString.add(deck.getName());
	    	}
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		/*
		try {
			setListAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, MainActivity.getDeckNames()));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
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
		getMenuInflater().inflate(R.menu.deck_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				NavUtils.navigateUpFromSameTask(this);
				return true;
			case R.id.cloud_option:
	        	goToCloudActivity(null);
	            return true;
			case R.id.new_deck:
	        	goToNewDeckActivity(null);
	            return true;
	        case R.id.new_flashcard:
	        	goToNewFlashcardActivity(null);
	            return true;
	        
	        
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void goToCloudActivity(View view) {
    	Intent intent = new Intent(this, CloudActivity.class);
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
   
   

	
}