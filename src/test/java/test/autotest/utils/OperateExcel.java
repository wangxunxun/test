package test.autotest.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class OperateExcel {

	protected Workbook wb;
	protected WritableWorkbook wbe;
	protected WritableSheet sheet;
	protected WritableFont font;
	protected static WritableCellFormat format;

	public OperateExcel(String excelPath, String className) throws BiffException, IOException {

		wb = Workbook.getWorkbook(new File(excelPath));
		wbe = Workbook.createWorkbook(new File(excelPath), wb);
		sheet = wbe.getSheet(className);

	}

	public void writeLastRow(int cow, Object content)
			throws RowsExceededException, WriteException, BiffException, IOException {

		int row = sheet.getRows();
		if (format != null) {
			Label lable = new Label(cow, row, (String) content, format);
			sheet.addCell(lable);
		} else {
			Label lable = new Label(cow, row, (String) content);
			sheet.addCell(lable);
		}
	}

	public void writeLastRow(int cow, String content, int size, Colour colour)
			throws RowsExceededException, WriteException {
		int row = sheet.getRows();
		WritableFont font = new WritableFont(WritableFont.ARIAL, size, WritableFont.BOLD);
		font.setColour(colour);
		WritableCellFormat format = new WritableCellFormat(font);
		format.setBorder(Border.ALL, BorderLineStyle.THIN);
		format.setVerticalAlignment(VerticalAlignment.CENTRE);
		format.setWrap(true);
		Label label = new Label(cow, row, content, format);
		sheet.addCell(label);
	}

	public void writeSameRow(int cow, String content, int size, Colour colour)
			throws RowsExceededException, WriteException {
		int row = sheet.getRows();
		WritableFont font = new WritableFont(WritableFont.ARIAL, size, WritableFont.BOLD);
		font.setColour(colour);
		WritableCellFormat format = new WritableCellFormat(font);
		format.setBorder(Border.ALL, BorderLineStyle.THIN);
		format.setVerticalAlignment(VerticalAlignment.CENTRE);
		format.setWrap(true);
		Label label = new Label(cow, row - 1, content, format);
		sheet.addCell(label);
	}

	public void writeSameRow(int cow, Object content)
			throws RowsExceededException, WriteException, BiffException, IOException {

		int row = sheet.getRows();
		if (format != null) {
			Label lable = new Label(cow, row - 1, (String) content, format);
			sheet.addCell(lable);
		} else {
			Label lable = new Label(cow, row - 1, (String) content);
			sheet.addCell(lable);
		}
	}

	public void writeData(int cow, int row, Object content, WritableCellFormat format)
			throws RowsExceededException, WriteException, BiffException, IOException {

		if (format != null) {
			Label lable = new Label(cow, row, (String) content, format);
			sheet.addCell(lable);
		} else {
			Label lable = new Label(cow, row, (String) content);
			sheet.addCell(lable);
		}
	}

	public void writeData(int cow, int row, Object content)
			throws RowsExceededException, WriteException, BiffException, IOException {

		if (format != null) {
			Label lable = new Label(cow, row, (String) content, format);
			sheet.addCell(lable);
		} else {
			Label lable = new Label(cow, row, (String) content);
			sheet.addCell(lable);
		}
	}

	public void close() throws IOException, WriteException {
		wbe.write();
		wbe.close();
	}

	public void mergeCells(int col1, int row1, int col2, int row2) throws RowsExceededException, WriteException {
		sheet.mergeCells(col1, row1, col2, row2);
	}

	public void setColumnView(int col, int width) {
		sheet.setColumnView(col, width);
	}

	public void writeTitle(int cow, int row, String content, int size) throws RowsExceededException, WriteException {
		WritableFont font = new WritableFont(WritableFont.ARIAL, size, WritableFont.BOLD);
		WritableCellFormat format = new WritableCellFormat(font);
		Label label = new Label(cow, row, content, format);
		sheet.addCell(label);
	}

	public void writeDataByColour(int cow, int row, String content, int size, Colour colour)
			throws RowsExceededException, WriteException {
		WritableFont font = new WritableFont(WritableFont.ARIAL, size);
		WritableCellFormat format = new WritableCellFormat(font);
		format.setBackground(colour);
		format.setBorder(Border.ALL, BorderLineStyle.THIN);
		Label label = new Label(cow, row, content, format);
		sheet.addCell(label);
	}

	public void setFormat(int size, Boolean wrap) throws WriteException {
		font = new WritableFont(WritableFont.ARIAL, size);
		format = new WritableCellFormat(font);
		format.setWrap(wrap);
		format.setBorder(Border.ALL, BorderLineStyle.THIN);
		format.setVerticalAlignment(VerticalAlignment.CENTRE);
	}

	public static WritableCellFormat getFormat(int size, Boolean wrap, Colour fontColour) throws WriteException {
		WritableFont font1 = new WritableFont(WritableFont.ARIAL, size);
		font1.setColour(fontColour);
		WritableCellFormat format1 = new WritableCellFormat(font1);
		format1.setWrap(wrap);
		return format1;
	}

	public void setHyperLinkForFile(int cow, int row, String filePath, String desc)
			throws MalformedURLException, RowsExceededException, WriteException {
		WritableHyperlink link = new WritableHyperlink(cow, row, new File(filePath), desc);
		sheet.addHyperlink(link);
	}

	public void setHyperLinkForSheet(int col, int row, String desc, String sheetName, int destCol, int destRow)
			throws RowsExceededException, WriteException {
		WritableSheet desSheet = wbe.getSheet(sheetName);
		WritableHyperlink link = new WritableHyperlink(col, row, desc, desSheet, destCol, destRow);
		sheet.addHyperlink(link);
	}

	public void setHyperLinkByFormu(int col, int row, String path, String name)
			throws RowsExceededException, WriteException {
		WritableFont font1 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,
				UnderlineStyle.SINGLE);
		font1.setColour(Colour.BLUE);
		WritableCellFormat format1 = new WritableCellFormat(font1);
		format1.setWrap(true);
		format1.setBorder(Border.ALL, BorderLineStyle.THIN);
		String formu = "HYPERLINK(\"" + path + "\",\"" + name + "\")";
		Formula formula = new Formula(col, row, formu, format1);
		sheet.addCell(formula);
	}

	public void setVerticalFreeze(int col) {
		SheetSettings setting = sheet.getSettings();
		setting.setVerticalFreeze(col);
	}

	public void copySheet(String modelPath, String destPath) throws BiffException, IOException, WriteException {
		WritableWorkbook wb = Workbook.createWorkbook(new File(destPath));
		Workbook model = Workbook.getWorkbook(new File(modelPath));
		Sheet modelSheet = model.getSheet(0);
		wb.importSheet("newsheet", 0, modelSheet);
		wb.write();
		wb.close();

	}

	public void writeLogToExcel(List<List<String>> logData)
			throws BiffException, IOException, RowsExceededException, WriteException {

		int currentRow = 1;
		List<Integer> firstCol = new ArrayList<Integer>();
		for (int i = 0; i < logData.size(); i++) {
			String title = logData.get(i).get(0);
			String content = logData.get(i).get(1);
			if (title == "") {
				writeLastRow(0, title);
				writeSameRow(1, content);
			} else {
				currentRow = sheet.getRows();
				firstCol.add(currentRow);
				writeLastRow(0, title, 12, Colour.BLACK);
				writeSameRow(1, content, 12, Colour.BLACK);
			}
		}

		firstCol.add(sheet.getRows());
		if (logData.get(0).get(0) == "ClassName") {
			for (int i = 0; i < firstCol.size() - 3; i++) {
				int endRow = firstCol.get(i + 3) - 1;
				int startRow = firstCol.get(i + 2);
				if (endRow > startRow) {
					sheet.mergeCells(0, startRow, 0, endRow);
				}
			}

		} else {
			for (int i = 0; i < firstCol.size() - 1; i++) {
				int endRow = firstCol.get(i + 1) - 1;
				int startRow = firstCol.get(i);
				if (endRow > startRow) {
					sheet.mergeCells(0, startRow, 0, endRow);
				}
			}
		}
	}

	public void writeTestSummaryToExcel(List<Map<String, String>> classData)
			throws RowsExceededException, WriteException, BiffException, IOException {
		if (classData.size() != 0) {
			int startRow = sheet.getRows();
			int methodsCounts = classData.size();
			int successCount = 0;
			int failureCount = 0;
			int skippedCount = 0;
			String totalTimeString = sheet.getCell(4, 4).getContents();
			String[] aa = totalTimeString.split("s");
			float TotalTime = Float.parseFloat(aa[0]);
			for (int i = 0; i < classData.size(); i++) {
				String className = classData.get(i).get("className");
				String method = classData.get(i).get("method");
				String time = classData.get(i).get("time");
				String status = classData.get(i).get("status");
				String comment = classData.get(i).get("comment");
				String screenPath = classData.get(i).get("screenPath");
				String abScreenPath = classData.get(i).get("abScreenPath");
				String caseInfo = classData.get(i).get("caseInfo");
				String classInfo = classData.get(i).get("classInfo");
				float time1 = Float.parseFloat(time) / 1000;
				TotalTime = TotalTime + Float.parseFloat(time) / 1000;
				time = String.valueOf(time1) + "s";
				if (i == 0) {
					writeLastRow(0, className);
					if (classInfo != null) {
						writeSameRow(1, classInfo);
					} else {
						writeSameRow(1, "");
					}

					writeSameRow(3, "");
					setHyperLinkForSheet(3, startRow, className + "-log", className, 0, 1);
				} else {
					writeLastRow(0, "");
				}

				writeSameRow(5, method);
				writeSameRow(7, time);
				if (caseInfo != null) {
					writeSameRow(6, caseInfo);
				} else {
					writeSameRow(6, "");
				}

				if (screenPath != null) {
					setHyperLinkByFormu(8, sheet.getRows() - 1, abScreenPath, method);
				} else {
					writeSameRow(8, "");
				}
				writeSameRow(10, comment);
				if (status.equals("Success")) {
					successCount = successCount + 1;
					writeDataByColour(9, sheet.getRows() - 1, status, 10, Colour.GREEN);
				}
				if (status.equals("Failure")) {
					failureCount = failureCount + 1;
					writeDataByColour(9, sheet.getRows() - 1, status, 10, Colour.RED);
				}
				if (status.equals("Skipped")) {
					skippedCount = skippedCount + 1;
					writeDataByColour(9, sheet.getRows() - 1, status, 10, Colour.YELLOW);
				}
			}
			String classSuccessRate = CommonTools.getPercent(successCount, methodsCounts);
			writeData(2, startRow, classSuccessRate);
			writeData(4, startRow, String.valueOf(methodsCounts));
			int endRow = sheet.getRows();
			mergeCells(0, startRow, 0, endRow - 1);
			mergeCells(1, startRow, 1, endRow - 1);
			mergeCells(2, startRow, 2, endRow - 1);
			mergeCells(3, startRow, 3, endRow - 1);
			mergeCells(4, startRow, 4, endRow - 1);
			int oldSuccessCount = Integer.parseInt(sheet.getCell(0, 4).getContents());
			int oldFailureCount = Integer.parseInt(sheet.getCell(1, 4).getContents());
			int oldSkippedCount = Integer.parseInt(sheet.getCell(2, 4).getContents());
			int newSuccessCount = successCount + oldSuccessCount;
			int newFailureCount = failureCount + oldFailureCount;
			int newSiippedCount = skippedCount + oldSkippedCount;
			int total = newSuccessCount + newFailureCount + newSiippedCount;
			writeData(0, 4, String.valueOf(newSuccessCount));
			writeData(1, 4, String.valueOf(newFailureCount));
			writeData(2, 4, String.valueOf(newSiippedCount));
			String allPercent = CommonTools.getPercent(newSuccessCount, total);
			writeData(3, 4, allPercent);
			writeData(4, 4, String.format("%.3f", TotalTime) + "s");
			writeData(5, 4, String.valueOf(total));
		} else {
			System.out.println("Please run the test case in listener mode.");
		}
	}

	public static void main(String[] args) throws RowsExceededException, BiffException, WriteException, IOException {

		// OperateExcel excel = new
		// OperateExcel("/Users/wangxun/Documents/workspace/java/appautotest/ticketIOS.xls",
		// "elements");
		// excel.setColumnView(1, 60);
		// excel.setFormat(19, true);
		/*
		 * excel.writeData(0, 0,
		 * "3434343434343434354354545454545454545454545454545");
		 * excel.setHyperLinkForFile(4,5,"F:/ttt.xls");
		 * excel.setHyperLinkForSheet(5, 5, "5534343", "TestSummary", 6, 6);
		 */
		// excel.copySheet("/Users/wangxun/Documents/workspace/java/appautotest/ticketWeb.xls","/Users/wangxun/Documents/workspace/java/appautotest/ticketIOS.xls");
		// excel.close();
		OperateExcel ddd = new OperateExcel("C:/Users/xun/Desktop/新建文件夹/TestReport_20151114_17_27_02.480.xls",
				"TestSummary");
		String a1 = "20151118_093018766.png";
		String a2 = "20151118_094219964.png";
		String a3 = "20151118_095301378.png";
		String a4 = "20151118_100147047.png";
		String a5 = "20151118_100324596.png";
		String a6 = "20151118_100558534.png";
		String a7 = "20151118_100710031.png";
		String a8 = "20151118_101406375.png";
		String a9 = "20151118_103437497.png";
		String a10 = "20151118_104436889.png";

		ddd.setHyperLinkByFormu(10, 9, "screenCaptures/Android/" + a1, a1);
		ddd.setHyperLinkByFormu(10, 35, "screenCaptures/Android/" + a2, a2);
		ddd.setHyperLinkByFormu(10, 51, "screenCaptures/Android/" + a3, a3);

		ddd.setHyperLinkByFormu(10, 59, "screenCaptures/Android/" + a4, a4);
		ddd.setHyperLinkByFormu(10, 61, "screenCaptures/Android/" + a5, a5);
		ddd.setHyperLinkByFormu(10, 62, "screenCaptures/Android/" + a6, a6);
		ddd.setHyperLinkByFormu(10, 65, "screenCaptures/Android/" + a7, a7);
		ddd.setHyperLinkByFormu(10, 67, "screenCaptures/Android/" + a8, a8);
		ddd.setHyperLinkByFormu(10, 78, "screenCaptures/Android/" + a9, a9);
		ddd.setHyperLinkByFormu(10, 99, "screenCaptures/Android/" + a10, a10);
		ddd.close();
		int a = 3;
		int b = 52;
		float c = b;
		float d = a / c;
		System.out.println(d);
		System.out.println("end");
	}

}
