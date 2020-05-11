package com.automation.extractor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.automation.entity.Action;
import com.automation.entity.Flow;
import com.automation.entity.FlowAction;
import com.automation.entity.Parameter;
import com.automation.entity.TestCase;
import com.automation.entity.TestStep;

public class ExcelExtractor {
	private static final String PARAMETERS = "PARAMETERS";
	private static final String ACTION_STEP = "ACTIONSTEP";
	private DataFormatter formatter = new DataFormatter();

	@SuppressWarnings("unchecked")
	public List<TestCase> parser(String path) throws Exception {
		List<TestCase> testCases = new ArrayList<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		try (Workbook wb = WorkbookFactory.create(new FileInputStream(path));) {
			Sheet sheet = wb.getSheetAt(0);

			int rowStart = sheet.getFirstRowNum();
			int rowEnd = sheet.getLastRowNum();
			Row headerRow = null;
			for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
				if (rowNum == 1) {
					headerRow = sheet.getRow(rowNum);
				}
				if (rowNum > 1) {
					Map<String, Object> dataMap = new LinkedHashMap<>();
					Row r = sheet.getRow(rowNum);
					if (r == null) {
						continue;
					}
					int lastColumn = r.getLastCellNum();
					for (int cn = 0; cn < lastColumn; cn++) {
						Cell c = r.getCell(cn);
						Object value = getCellValue(sheet, r, c);
						String key = getCellValue(sheet, r, headerRow.getCell(cn)) == null ? ""
								: getCellValue(sheet, r, headerRow.getCell(cn)).toString().toUpperCase();
						if (dataMap.get(key) != null) {
							if (dataMap.get(key) instanceof List) {
								List<Object> list = (List<Object>) dataMap.get(key);
								list.add(value);
								dataMap.put(key, list);
							} else {
								List<Object> list = new ArrayList<>();
								list.add(dataMap.get(key));
								list.add(value);
								dataMap.put(key, list);
							}
						} else {
							dataMap.put(key, value);
						}

					}

					dataList.add(dataMap);
				}

			}

		}

		dataList.stream().filter(data -> data != null && data.get("JIRAKEY") != null && !"".equals(data.get("JIRAKEY")))
				.collect(Collectors.groupingBy(data -> data.get("JIRAKEY"))).forEach((jiraKey, list) -> {
					TestCase testCase = new TestCase();
					String summary = list.get(0).get("SUMMARY") + "";
					String component = list.get(0).get("COMPONENT") + "";
					String subComponent = list.get(0).get("SUBCOMPONENT") + "";
					testCase.setJiraKey(jiraKey + "");
					testCase.setSummary(summary);
					testCase.setComponent(component);
					testCase.setSubComponent(subComponent);

					List<TestStep> testSteps = new ArrayList<>();
					list.stream().filter(data -> data != null && data.get("STEPORDER") != null)
							.collect(Collectors.groupingBy(data -> data.get("STEPORDER")))
							.forEach((stepOrder, stepList) -> {
								TestStep testStep = new TestStep();

								String description = stepList.get(0).get("DESCRIPTION") + "";
								testStep.setStepOrder(stepOrder + "");
								testStep.setDescription(description);
								Flow flow = new Flow();
								List<FlowAction> flowActions = new ArrayList<>();

								stepList.stream().filter(data -> data != null && data.get(ACTION_STEP) != null)
										.collect(Collectors.groupingBy(data -> data.get(ACTION_STEP)))
										.forEach((actionOrder, actionList) -> {
											FlowAction flowAction = new FlowAction();
											Action action = new Action();

											List<Parameter> parameters = new ArrayList<>();
											Object parameterO = actionList.get(0).get(PARAMETERS);
											if (parameterO != null) {
												if (parameters instanceof List) {
													List<Object> pl = (List<Object>) parameterO;
													pl.forEach(p -> {
														if (p != null) {
															Parameter parameter = new Parameter();
															parameter.setName(p + "");
															parameters.add(parameter);
														}
													});
												} else {
													Parameter parameter = new Parameter();
													parameter.setName(parameterO + "");
													parameters.add(parameter);
												}
											}
											String name = actionList.get(0).get("ACTIONNAME") + "";
											action.setName(name);
											action.setParameters(parameters);
											flowAction.setActionOrder((int) Float.parseFloat(actionOrder + ""));
											flowAction.setAction(action);
											flowActions.add(flowAction);
										});
								flow.setFlowActions(flowActions);
								testStep.setFlow(flow);
								testSteps.add(testStep);
							});
					testCase.setTestSteps(testSteps);
					testCases.add(testCase);
				});

		return testCases;

	}

	public void generate(List<TestCase> testCases, String output) throws Exception {
		if (testCases == null) {
			return;
		}
		SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
		Sheet sh = wb.createSheet();

		Row headerRow = sh.createRow(0);
		String[] headers = { "JIRAKEY", "SUMMARY", "COMPONENT", "SUBCOMPONENT", "STEPORDER", "DESCRIPTION", ACTION_STEP,
				"ACTIONNAME", PARAMETERS };

		for (int index = 0; index < headers.length; index++) {
			headerRow.createCell(index).setCellValue(headers[index]);
		}

		int currentRow = 1;
		int maxParameter = 1;
		int caseSize = testCases.size();
		for (int i = 0; i < caseSize; i++) {
			int startCaseRow = currentRow;
			int totalCaseRow = 0;
			TestCase testCase = testCases.get(i);
			List<TestStep> testSteps = testCase.getTestSteps();
			int stepSize = testSteps.size();
			for (int stepI = 0; stepI < stepSize; stepI++) {
				int startStepRow = currentRow;
				int totalStepRow = 0;
				TestStep testStep = testSteps.get(stepI);
				Flow flow = testStep.getFlow();
				List<FlowAction> flowActions = flow.getFlowActions();
				int flowActionSize = flowActions.size();
				for (int flowActionI = 0; flowActionI < flowActionSize; flowActionI++) {
					FlowAction flowAction = flowActions.get(flowActionI);
					Action action = flowAction.getAction();
					List<Parameter> parameters = action.getParameters();
					int parameterSize = parameters.size();
					maxParameter = maxParameter > parameterSize ? maxParameter : parameterSize;
					/**
					 * generate data
					 */
					Row dataRow = sh.createRow(currentRow++);
					dataRow.createCell(0).setCellValue(testCase.getJiraKey());
					dataRow.createCell(1).setCellValue(testCase.getSummary());
					dataRow.createCell(2).setCellValue(testCase.getComponent());
					dataRow.createCell(3).setCellValue(testCase.getSubComponent());
					dataRow.createCell(4).setCellValue(testStep.getStepOrder());
					dataRow.createCell(5).setCellValue(testStep.getDescription());
					dataRow.createCell(6).setCellValue(flowAction.getActionOrder());
					dataRow.createCell(7).setCellValue(action.getName());

					for (int parameterIndex = 0; parameterIndex < parameters.size(); parameterIndex++) {
						dataRow.createCell(8 + parameterIndex).setCellValue(parameters.get(parameterIndex).getName());
					}

					totalCaseRow++;
					totalStepRow++;

				}
				sh.addMergedRegion(new CellRangeAddress(startStepRow, startStepRow + totalStepRow-1, 4, 4));
				sh.addMergedRegion(new CellRangeAddress(startStepRow, startStepRow + totalStepRow-1, 5, 5));

			}

			sh.addMergedRegion(new CellRangeAddress(startCaseRow, startCaseRow + totalCaseRow-1, 0, 0));
			sh.addMergedRegion(new CellRangeAddress(startCaseRow, startCaseRow + totalCaseRow-1, 1, 1));
			sh.addMergedRegion(new CellRangeAddress(startCaseRow, startCaseRow + totalCaseRow-1, 2, 2));
			sh.addMergedRegion(new CellRangeAddress(startCaseRow, startCaseRow + totalCaseRow-1, 3, 3));
		}

		sh.addMergedRegion(new CellRangeAddress(0, 0, 8, 8 + maxParameter));
		FileOutputStream out = new FileOutputStream(output);
		wb.write(out);
		out.close();
		wb.dispose();
		wb.close();
	}

	public Object getCellValue(Sheet sheet, Row r, Cell c) {
		if (c == null) {
			return null;
		}
		Object value = null;
		// check if merge
		Optional<CellRangeAddress> optional = sheet.getMergedRegions().stream().filter(region -> {
			return region.containsRow(c.getRowIndex()) && region.containsColumn(c.getColumnIndex());
		}).findFirst();
		if (optional.isPresent()) {
			CellRangeAddress region = optional.get();
			Cell mc = sheet.getRow(region.getFirstRow()).getCell(region.getFirstColumn());
			value = getCellValue(mc);
		} else {
			value = getCellValue(c);
		}
		return value;
	}

	private Object getCellValue(Cell c) {
		Object value;
		switch (c.getCellType()) {
		case STRING:
			value = c.getRichStringCellValue().getString();
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(c)) {
				value = c.getDateCellValue();
			} else {
				value = c.getNumericCellValue();
			}
			break;
		case BOOLEAN:
			value = c.getBooleanCellValue();
			break;
		case FORMULA:
			value = c.getCellFormula();
			break;
		case BLANK:
			value = "";
			break;
		default:
			value = formatter.formatCellValue(c);
		}
		return value;
	}
}
