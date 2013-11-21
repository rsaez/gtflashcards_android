package com.example.gtflashcards.convertor;

public class ConvertorFactory {
	
	private static CourseConvertor courseConvertor = new CourseConvertor();
	private static DeckConvertor deckConvertor = new DeckConvertor();
	
	static public CourseConvertor COURSE_CONVERTOR() {
		return courseConvertor;
	}
	
	static public DeckConvertor DECK_CONVERTOR() {
		return deckConvertor;
	}
}
