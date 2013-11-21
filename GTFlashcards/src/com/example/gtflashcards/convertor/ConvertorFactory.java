package com.example.gtflashcards.convertor;

public class ConvertorFactory {
	
	private static CourseConvertor courseConvertor = new CourseConvertor();
	
	static public CourseConvertor COURSE_CONVERTOR() {
		return courseConvertor;
	}
}
