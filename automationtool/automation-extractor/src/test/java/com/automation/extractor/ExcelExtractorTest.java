package com.automation.extractor;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.automation.entity.TestCase;

public class ExcelExtractorTest {
	private ExcelExtractor extractor;

	@Before
	public void before() {
		extractor = new ExcelExtractor();
	}

	@Test
	public void parserTest() throws Exception {
		List<TestCase> testCases = extractor.parser("src/test/resources/testcase.xlsx");
		testCases.forEach(System.out::println);
	}

	@Test
	public void generateTest() throws Exception {
		List<TestCase> testCases = extractor.parser("src/test/resources/testcase.xlsx");
		extractor.generate(testCases, "src/test/resources/testcase2.xlsx");
	}
}
