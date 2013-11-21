package com.example.gtflashcards.convertor;

import org.json.JSONException;
import org.json.JSONObject;

public interface InputConvertor<T> {
	T inputConvert(JSONObject obj) throws JSONException;
}
