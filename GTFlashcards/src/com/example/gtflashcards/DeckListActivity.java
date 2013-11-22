
package com.example.gtflashcards;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
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
	
}