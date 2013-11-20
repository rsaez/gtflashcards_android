package com.example.gtflashcards.objects;

import java.util.ArrayList;

public class Flashcard {
	int id, upvotes, downvotes;
	String creator, question, answer, courseDept, courseCode;
	ArrayList<String> tags;
	boolean isPublic, isAnonymous, isKnown;
	
	public Flashcard(int id, String creator, String question, String answer, String courseDept, String courseCode, int upvotes, int downvotes, boolean isPublic, boolean isAnonymous) {
		this.id = id;
		this.creator = creator;
		this.question = question;
		this.answer = answer;
		this.courseDept = courseDept;
		this.courseCode = courseCode;
		this.upvotes = upvotes;
		this.downvotes = downvotes;
		this.isPublic = isPublic;
		this.isAnonymous = isAnonymous;
		
		tags = new ArrayList<String>();
	}
	
	public void update(String creator, String question, String answer, String courseDept, String courseCode, boolean isPublic, boolean isAnonymous) {
		this.creator = creator;
		this.question = question;
		this.answer = answer;
		this.courseDept = courseDept;
		this.courseCode = courseCode;
		this.isPublic = isPublic;
		this.isAnonymous = isAnonymous;
	}
	
	public void setKnown(boolean isKnown) {
		this.isKnown = isKnown;
	}
}
