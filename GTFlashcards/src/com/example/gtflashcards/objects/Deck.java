package com.example.gtflashcards.objects;

import java.util.ArrayList;


public class Deck {
	int id, flashcardCount;
	String name;
	ArrayList<Flashcard> flashcards;
	
	public Deck(int id, String name) {
		this.id = id;
		this.name = name;
		this.flashcardCount = 0;
		
		flashcards = new ArrayList<Flashcard>();
	}
	
	public ArrayList<Flashcard> getFlashcards() {
		return flashcards;
	}

	public void setFlashcards(ArrayList<Flashcard> flashcards) {
		this.flashcards = flashcards;
	}

	public void addFlashcard(Flashcard flashcard) {
		flashcards.add(flashcard);
	}
	
	public String getName() {
		return name;
	}
}
