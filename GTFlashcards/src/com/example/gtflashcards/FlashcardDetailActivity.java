package com.example.gtflashcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * An activity representing a single Deck detail screen. This activity is only
 * used on handset devices. On tablet-size devices, item details are presented
 * side-by-side with a list of items in a {@link FlashcardListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link FlashcardDetailFragment}.
 */
public class FlashcardDetailActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flashcard_detail);

		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// savedInstanceState is non-null when there is fragment state
		// saved from previous configurations of this activity
		// (e.g. when rotating the screen from portrait to landscape).
		// In this case, the fragment will automatically be re-added
		// to its container so we don't need to manually add it.
		// For more information, see the Fragments API guide at:
		//
		// http://developer.android.com/guide/components/fragments.html
		//
		if (savedInstanceState == null) {
			// Create the detail fragment and add it to the activity
			// using a fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(FlashcardDetailFragment.ARG_ITEM_ID, getIntent()
					.getStringExtra(FlashcardDetailFragment.ARG_ITEM_ID));
			FlashcardDetailFragment fragment = new FlashcardDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.deck_detail_container, fragment).commit();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.flashcard_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				NavUtils.navigateUpFromSameTask(this);
				return true;
			case R.id.edit_option:
	        	editFlashcard();
	            return true;
	        case R.id.delete_option:
	        	deleteFlashcard();
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
	
	void editFlashcard() {
		//todo: implement edit flashcard
	}
	
	void deleteFlashcard() {
		//todo: implement delete flashcard
	}
    
}
