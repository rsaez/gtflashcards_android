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
	
	
	
	
}
