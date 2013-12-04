package com.example.gtflashcards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class CloudActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cloud);
		setupActionBar();
	}
	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cloud, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.my_decks_option:
	        	goToDeckListActivity(null);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void goToDeckListActivity(View view) {
    	Intent intent = new Intent(this, DeckListActivity.class);
    	startActivity(intent);
    }
	    
	
	public void goToCourseDeptListActivity(View view) {
		Intent intent = new Intent(this, CourseDeptListActivity.class);
    	startActivity(intent);
	}

}
