package com.allan.automation.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.allan.automation.entites.AutoActionFlow;
import com.allan.automation.entites.AutoFlow;
import com.allan.automation.entites.AutoTestCase;
import com.allan.automation.entites.AutoTestStep;

public class AutomationExcelGenerator {
	public static ByteArrayInputStream autoTestCaseToExcel(List<AutoTestCase> autoTestCases) throws IOException {
		try (Workbook wb = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			// Action sheet
			// flowType, stepOrder , isExecute , description , action , parameters , return
			// parameters
			Sheet flowSheet = wb.createSheet("flow");

			Sheet dataSheet = wb.createSheet("test data");
			Row dataHeaderRow = dataSheet.createRow(0);
			dataHeaderRow.createCell(0).setCellValue("jirakey");
			dataHeaderRow.createCell(1).setCellValue("isExecute");
			// fill header
			Row flowHeaderRow = flowSheet.createRow(0);
			flowHeaderRow.createCell(0).setCellValue("flowType");
			flowHeaderRow.createCell(1).setCellValue("stepOrder");
			flowHeaderRow.createCell(2).setCellValue("isExecute");
			flowHeaderRow.createCell(3).setCellValue("description");
			flowHeaderRow.createCell(4).setCellValue("action");
			flowHeaderRow.createCell(5).setCellValue("parameters");

			// Test Case sheet
			// JIRA key , summary , flowType , isExecute, stepOrder
			Sheet caseSheet = wb.createSheet("test case");

			// fill header
			Row caseHeaderRow = caseSheet.createRow(0);
			caseHeaderRow.createCell(0).setCellValue("jiraKey");
			caseHeaderRow.createCell(1).setCellValue("summary");
			caseHeaderRow.createCell(2).setCellValue("flowType");
			caseHeaderRow.createCell(3).setCellValue("isExecute");
			caseHeaderRow.createCell(4).setCellValue("stepOrder");
			int rowIndex = 1;
			int flowRowIndex = 1;
			int dataRowIndex = 1;
			Set<String> dateHeaderSet = new HashSet<>();
			for (AutoTestCase autoTestCase : autoTestCases) {
				Set<AutoTestStep> autoTestSteps = autoTestCase.getAutoTestSteps();
				for (AutoTestStep autoTestStep : autoTestSteps) {
					Row caseDataRow = caseSheet.createRow(rowIndex++);
					caseDataRow.createCell(0).setCellValue(autoTestCase.getJiraKey());
					caseDataRow.createCell(1).setCellValue(autoTestCase.getSummary());

//					AutoAction autoAction = autoTestStep.getAutoAction();
					AutoFlow autoFlow = autoTestStep.getAutoFlow();
//					if (autoAction != null) {
//						caseDataRow.createCell(2).setCellValue(autoAction.getName());
//
//						Row flowDataRow = flowSheet.createRow(flowRowIndex++);
//						flowDataRow.createCell(0).setCellValue(autoAction.getName());
//						flowDataRow.createCell(1).setCellValue(1);
//						flowDataRow.createCell(2).setCellValue("Y");
//						flowDataRow.createCell(3).setCellValue(autoAction.getDescription());
//						flowDataRow.createCell(4).setCellValue(autoAction.getName());
////						List<String> parsedParameters = autoAction.getParsedParameters();
////						for (int i = 0; i < parsedParameters.size(); i++) {
////							String parameter =  parsedParameters.get(i);
////							flowDataRow.createCell(5 + i).setCellValue(parameter);
////							
////						}
//
//					}

					if (autoFlow != null) {
						caseDataRow.createCell(2).setCellValue(autoFlow.getName());

						Set<AutoActionFlow> autoActionFlows = autoFlow.getAutoActionFlows();
						for (AutoActionFlow autoActionFlow : autoActionFlows) {
							Row flowDataRow = flowSheet.createRow(flowRowIndex++);
							flowDataRow.createCell(0).setCellValue(autoFlow.getName());
							flowDataRow.createCell(1).setCellValue(autoActionFlow.getActionOrder());
							flowDataRow.createCell(2).setCellValue("Y");
							flowDataRow.createCell(3).setCellValue(autoActionFlow.getAutoAction().getDescription());
							flowDataRow.createCell(4).setCellValue(autoActionFlow.getAutoAction().getName());

//							List<String> parsedParameters = autoActionFlow.getAutoAction().getParsedParameters();
//							for (int i = 0; i < parsedParameters.size(); i++) {
//								String parameter =  parsedParameters.get(i);
//								flowDataRow.createCell(5 + i).setCellValue(parameter);
//							}
						}

					}

					caseDataRow.createCell(3).setCellValue("Y");
					caseDataRow.createCell(4).setCellValue(autoTestStep.getStepOrder());
				}

			}

			// Test Data sheet

			wb.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static <T> ByteArrayInputStream listToExcel(List<T> list) throws IOException {
		if (list == null || list.isEmpty()) {
			return null;
		}
		try (Workbook wb = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			Sheet sheet = wb.createSheet();
			IntStream.range(0, list.size()).forEach(index -> {
				T t = list.get(index);
				Row dataRow = sheet.createRow(index + 1);
				Row headerRow = sheet.createRow(0);
				for (int i = 0; i < t.getClass().getDeclaredFields().length; i++) {
					Field f = t.getClass().getDeclaredFields()[i];
					if (index == 0) {
						Cell headerCell = headerRow.createCell(i);
						headerCell.setCellValue(f.getName());
					}

					Cell dataCell = dataRow.createCell(i);
					Object value;
					try {
						value = runGetter(f, t);
						dataCell.setCellValue(value + "");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			});
			wb.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}

	}

	public static Object runGetter(Field field, Object o) throws Exception {
		for (Method method : o.getClass().getMethods()) {
			if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3))) {
				if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
					return method.invoke(o);
				}
			}
		}
		return null;
	}
}
