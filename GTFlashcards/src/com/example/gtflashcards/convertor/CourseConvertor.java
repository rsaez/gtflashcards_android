package com.example.gtflashcards.convertor;

import org.json.JSONException;
import org.json.JSONObject;

import edu.gatech.trylistview.Course;

public class CourseConvertor implements InputConvertor<Course>{

	protected CourseConvertor() {}
	
	@Override
	public Course inputConvert(JSONObject obj) throws JSONException {
        String course = obj.getString("course_dept");
        return new Course(course);
	}
	
	public String convetOutput(String course) {
		String result = "course_dept="+course;
		return result;
	}


}
