package com.example.gtflashcards.http_request;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

import com.example.gtflashcards.objects.Deck;
import com.loopj.android.http.JsonHttpResponseHandler;

public class DeckJsonHttpResponseHandler extends JsonHttpResponseHandler {
	ArrayList<Deck> results;
	
	public DeckJsonHttpResponseHandler(ArrayList<Deck> r) {
		super();
		results = r;
	}
	
	@Override
    public void onSuccess(JSONArray decks) {
        Log.v("Rest Call", "List Decks");
		Log.v("Rest Call", "List Decks, number of json rows " + decks.length());

        for (int i = 0; i < decks.length(); i++) {
        	try {
        		//Log.v("Rest Call", "List Decks, id = " + decks.getJSONObject(i).getInt("id") + ", name = " + decks.getJSONObject(i).getString("name"));
        		
        		Deck d = new Deck(decks.getJSONObject(i).getInt("id"), decks.getJSONObject(i).getString("name"));
				results.add(d);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        
        Log.v("Rest Call", "List Decks, number of convertion rows " + results.size());
        
	}
}
