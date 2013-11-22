package com.example.gtflashcards;

import java.util.ArrayList;
import java.util.List;

import com.example.gtflashcards.http_request.GTFlashcardsAPI;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewFlashcardActivity extends Activity {

	Spinner deckSpinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_flashcard);
		
		addItemsToDeckSpinner();
	}
	
	public void addItemsToDeckSpinner() {
		deckSpinner = (Spinner) findViewById(R.id.deck_spinner);
		ArrayList<String> deckNames = MainActivity.getDeckNames();
		deckNames.add(0, "No Deck");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, deckNames);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		deckSpinner.setAdapter(dataAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_flashcard, menu);
		return true;
	}
	
	public void createFlashcard(View view) {
		System.out.println("Create flashcard clicked");
		
		int id = -1;
		String creator = "creator";
		String question = ((EditText) findViewById(R.id.question_input)).getText().toString();
		String answer = ((EditText) findViewById(R.id.answer_input)).getText().toString();
		String[] tags = parseTags(((EditText) findViewById(R.id.tags_input)).getText().toString());
		String course = ((EditText) findViewById(R.id.course_input)).getText().toString();
		String[] courseSplit = course.split(" ");
		if (question.length() > 0 && answer.length() > 0 && courseSplit.length == 2) {
			String courseDept = courseSplit[0];
			String courseCode = courseSplit[1];
			String deck = String.valueOf(deckSpinner.getSelectedItem());
		
			int upvotes = 0;
			int downvotes = 0;
			boolean isPrivate = false;
			boolean isAnonymous = false;
		
		
			System.out.println("Question="+question);
			System.out.println("Answer="+answer);
			System.out.println("Tags="+tags);
			System.out.println("CourseDept="+courseDept);
			System.out.println("CourseCode="+courseCode);
			System.out.println("Deck="+deck);
			
			
			// Frank added this API call
			
			GTFlashcardsAPI.addFlashcard(question, answer, courseDept, courseCode, isPrivate, isAnonymous);
			
			// End
			
			
			
			//Flashcard flashcard = new Flashcard(id, creator, question, answer, courseDept, courseCode, upvotes, downvotes, isPublic, isAnonymous);
    	
			Toast.makeText(NewFlashcardActivity.this, "Successfully created flashcard", Toast.LENGTH_SHORT).show();
			super.finish();
		} else {
			Toast.makeText(NewFlashcardActivity.this, "Error creating flashcard", Toast.LENGTH_SHORT).show();
		}	
	}
	
	String[] parseTags(String tagString) {
		return tagString.split(" ");
	}

}
