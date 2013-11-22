package com.example.gtflashcards.objects;

import java.util.ArrayList;


public class Deck {
	int id, flashcardCount;
	String name;
	ArrayList<GTFlashcards> flashcards;
	
	public Deck(int id, String name) {
		this.id = id;
		this.name = name;
		this.flashcardCount = 0;
		
		flashcards = new ArrayList<GTFlashcards>();
	}
	
	public ArrayList<GTFlashcards> getFlashcards() {
		return flashcards;
	}

	public void setFlashcards(ArrayList<GTFlashcards> flashcards) {
		this.flashcards = flashcards;
	}

	public void addFlashcard(GTFlashcards flashcard) {
		flashcards.add(flashcard);
	}
	
	public String getName() {
		return name;
	}
}
