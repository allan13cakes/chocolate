package com.allan.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AutoSampleTest {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		List<AutoSample> list = new ArrayList<>();
		list.add(new AutoSample("test"));
		list.add(new AutoSample("test1"));
		list.add(new AutoSample("test2"));
		AutoSample.store(list);
		List<AutoSample> newList = AutoSample.load();
		System.out.println(newList.size());

	}
}
