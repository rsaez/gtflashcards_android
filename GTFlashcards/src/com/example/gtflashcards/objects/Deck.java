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
		flashcards.add(new GTFlashcards(1, "rsaez", "Big-o of insertion sort", "O(n^2)", "CS", "1332", 0, 0, true, false));
		flashcards.add(new GTFlashcards(1, "rsaez", "What is in-place sorting", "when sorting doesnt need extra space", "CS", "1332", 0, 0, true, false));
		flashcards.add(new GTFlashcards(1, "rsaez", "Set", "unordered collection of entities, dulicates not allowed", "CS", "1332", 0, 0, true, false));
		flashcards.add(new GTFlashcards(1, "rsaez", "Bag", "unordered list, duplicates allowed", "CS", "1332", 0, 0, true, false));
		
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
	
	public ArrayList<String> getFlashcardNames() {
		ArrayList<String> flashcardNames = new ArrayList<String>();
		for (GTFlashcards f : flashcards) {
			flashcardNames.add(f.question);
		}
		return flashcardNames;
	}
}
