package com.example.gtflashcards;

import java.util.ArrayList;

public class Flashcard {
	int id, upvotes, downvotes;
	String creator, question, answer, courseDept, courseCode;
	ArrayList<String> tags;
	boolean isPublic, isAnonymous, isKnown;
	
	public Flashcard() {
		tags = new ArrayList<String>();
	}
}
