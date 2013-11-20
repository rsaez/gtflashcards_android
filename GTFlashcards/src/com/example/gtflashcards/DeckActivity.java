
package com.example.gtflashcards;

import com.example.gtflashcards.objects.Deck;
import com.example.gtflashcards.objects.Flashcard;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class DeckActivity extends ListActivity {
	
	Deck deck;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deck);
		
		setListAdapter(new ArrayAdapter<Flashcard>(this,
				android.R.layout.simple_list_item_1, deck.getFlashcards()));
	}
	
}