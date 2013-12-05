package com.example.gtflashcards.objects;

import java.util.ArrayList;

import android.app.Application;

public class GTFlashcards extends Application{
	int id, upvotes, downvotes;
	String creator, question, answer, courseDept, courseCode;
	ArrayList<String> tags;
	boolean isPublic, isAnonymous, isKnown;
	
	public GTFlashcards(int id, String creator, String question, String answer, String courseDept, String courseCode, int upvotes, int downvotes, boolean isPublic, boolean isAnonymous) {
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}

	public int getDownvotes() {
		return downvotes;
	}

	public void setDownvotes(int downvotes) {
		this.downvotes = downvotes;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCourseDept() {
		return courseDept;
	}

	public void setCourseDept(String courseDept) {
		this.courseDept = courseDept;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public boolean isAnonymous() {
		return isAnonymous;
	}

	public void setAnonymous(boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public boolean isKnown() {
		return isKnown;
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
	
	public String getTagsString() {
		String tagString = "";
		for (int i = 0; i < tags.size()-1; i++) {
			tagString += tags.get(i) + ", ";
		}
		tagString += tags.get(tags.size()-1);
		return tagString;
	}
}
