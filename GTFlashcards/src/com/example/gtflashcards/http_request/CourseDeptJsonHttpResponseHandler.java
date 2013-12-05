package com.example.gtflashcards.http_request;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

public class CourseDeptJsonHttpResponseHandler extends JsonHttpResponseHandler {
	ArrayList<String> results;
	
	public CourseDeptJsonHttpResponseHandler(ArrayList<String> r) {
		super();
		results = r;
	}
	
	@Override
    public void onSuccess(JSONArray depts) {
        Log.v("Rest Call", "List Course Depts ");
        
        for (int i = 0; i < depts.length(); i++) {
        	try {
				results.add(depts.getJSONObject(i).getString("course_dept"));
        		System.out.println(depts.getJSONObject(i).getString("course_dept"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
}
