
package com.example.gtflashcards;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gtflashcards.objects.Deck;

public class DeckListActivity extends ListActivity {
	
	Deck deck;
	ListView listview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deck_list);
		setupActionBar();
		
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, MainActivity.getDeckNames()));
		
		listview = getListView();
		listview.setOnItemClickListener(new OnItemClickListener(){

	        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
	        	Object ob = listview.getItemAtPosition(position);
	        	
	        	
	        	Intent intent = new Intent(getApplicationContext(), FlashcardListActivity.class);
	        	
	            //intent.putExtra("deck_name", inName);
	        	
	            
	            startActivity(intent);
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
		getMenuInflater().inflate(R.menu.course_code_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
}