package com.example.gtflashcards.adapter;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

public abstract class CustomAdapter<T> extends ArrayAdapter<T> implements CustomAdapterInterface<T> {

	public CustomAdapter(Context ctx, int resource, List<T> itemList) {
		super(ctx, android.R.layout.simple_list_item_1, itemList);
	}
	

}
