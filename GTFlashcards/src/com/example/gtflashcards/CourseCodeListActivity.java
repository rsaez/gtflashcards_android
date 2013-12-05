package com.example.gtflashcards;

import java.util.ArrayList;

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

public class CourseCodeListActivity extends ListActivity {

	ListView listview;
	String courseDept;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_code_list);
		// Show the Up button in the action bar.
		setupActionBar();
		
		courseDept = "CS"; //todo: get real courseDept passed in from the course dept page
		
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getCourseCodes()));
		
		listview = getListView();
		listview.setOnItemClickListener(new OnItemClickListener(){

	        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
	        	Object ob = listview.getItemAtPosition(position);
	        	
	        	Intent intent = new Intent(getApplicationContext(), FlashcardListActivty.class);
	            //intent.putExtra("deck_name", inName);
	            startActivity(intent);
	       }
		});
	}
	
	private ArrayList<String> getCourseCodes() {
		//todo: get real course codes
		ArrayList<String> courseCodes = new ArrayList<String>();
		courseCodes.add(courseDept + " 1331");
		courseCodes.add(courseDept + " 2340");
		courseCodes.add(courseDept + " 4261");
		return courseCodes;
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
	
	public void goToDeckListActivity(View view) {
    	Intent intent = new Intent(this, DeckListActivity.class);
    	startActivity(intent);
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
			case R.id.my_decks_option:
	        	goToDeckListActivity(null);
	            return true;
	        
		}
		return super.onOptionsItemSelected(item);
	}

}
