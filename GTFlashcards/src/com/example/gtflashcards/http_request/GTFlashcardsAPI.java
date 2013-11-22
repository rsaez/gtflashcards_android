package com.example.gtflashcards.http_request;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class GTFlashcardsAPI {
	
	// Usage:
	// A "name" string is required
	// i.e. GTFlashcardsAPI.createDeck("newDeck");
	// which will create a new deck named "newDeck"
	public static void createDeck(String name) {
		RequestParams params = new RequestParams();
		params.put("name", name);
		
		GTFlashcardsRestClient.post("create_deck", params, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(String response) {
				Log.v("Rest Call", "createDeck " + response);
			}
			
		});	
	}
	

	public static void addFlashcard(String question, String answer, String course_dept, 
									String course_code, boolean isPrivate, boolean isAnonymous) {
		
		RequestParams params = new RequestParams();
		params.put("question", question);
		params.put("answer", answer);
		params.put("course_dept", course_dept);
		params.put("course_code", course_code);
		
		if (isPrivate)
			params.put("private", "1");
		else
			params.put("private", "0");

		if (isAnonymous)
			params.put("anonymous", "1");
		else
			params.put("anonymous", "0");
		
		
		GTFlashcardsRestClient.post("flashcard", params, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(String response) {
				Log.v("Rest Call", "addFlashcard " + response);
			}
			
		});
		
	}
	
	
	
	
}
