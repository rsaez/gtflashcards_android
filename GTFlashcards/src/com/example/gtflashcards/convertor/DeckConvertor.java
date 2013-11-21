package com.example.gtflashcards.convertor;

import java.util.List;

public class DeckConvertor implements OutputConvertor {

	@Override
	public String outputConvert(List<String> datas) {
		if (datas.size() != 1)
			return null;
		else {
			String name = datas.get(0);
			String result = "name=" + name;
			return result;
		}
	}

}
