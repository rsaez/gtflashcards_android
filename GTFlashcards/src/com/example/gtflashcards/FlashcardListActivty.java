package com.example.gtflashcards;

import java.util.ArrayList;
import java.util.Collections;

import com.example.gtflashcards.objects.Deck;
import com.example.gtflashcards.objects.GTFlashcards;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
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
			
		//setListAdapter(new ArrayAdapter<String>(this,
			//	android.R.layout.simple_list_item_1, MainActivity.getDeckNames()));
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
		
		this.setTitle(MainActivity.getCurrentDeck().getName());
		
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
