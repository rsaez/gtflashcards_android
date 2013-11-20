package com.example.gtflashcards;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class NewFlashcardActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_flashcard);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_flashcard, menu);
		return true;
	}
	
	public void createFlashcard(View view) {
		System.out.println("Create flashcard clicked");
		super.finish();
	}

}
