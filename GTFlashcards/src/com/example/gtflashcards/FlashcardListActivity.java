package com.example.gtflashcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * An activity representing a list of Decks. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link FlashcardDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link FlashcardListFragment} and the item details (if present) is a
 * {@link FlashcardDetailFragment}.
 * <p>
 * This activity also implements the required {@link FlashcardListFragment.Callbacks}
 * interface to listen for item selections.
 */
public class FlashcardListActivity extends FragmentActivity implements
		FlashcardListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flashcard_list);
		setupActionBar();
		
		if (findViewById(R.id.deck_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((FlashcardListFragment) getSupportFragmentManager().findFragmentById(
					R.id.deck_list)).setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here.
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
		//todo: implement shuffle
	}
	
	void renameDeck() {
		//todo: implement rename deck
	}

	void deleteDeck() {
		//todo: implement delete deck
	}

	/**
	 * Callback method from {@link FlashcardListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(FlashcardDetailFragment.ARG_ITEM_ID, id);
			FlashcardDetailFragment fragment = new FlashcardDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.deck_detail_container, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, FlashcardDetailActivity.class);
			detailIntent.putExtra(FlashcardDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
}
