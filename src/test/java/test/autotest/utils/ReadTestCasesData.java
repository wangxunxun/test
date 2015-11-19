package test.autotest.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadTestCasesData {
	private String excelpath;
	private String sheet;
	private Sheet readsheet;

	public ReadTestCasesData(String excelpath, String sheet) {
		this.excelpath = CommonTools.setPath(excelpath);
		this.sheet = sheet;
		jxl.Workbook readwb = null;
		InputStream instream = null;
		try {
			instream = new FileInputStream(this.excelpath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			readwb = Workbook.getWorkbook(instream);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet readsheet = readwb.getSheet(this.sheet);
		this.readsheet = readsheet;
	}

	public List<List<Object>> readTable() {
		List<String> header = new ArrayList<String>();
		int rsColumns = readsheet.getColumns();
		int rsRows = readsheet.getRows();
		List<Object> actions = new ArrayList<Object>();
		List<Object> testCases = new ArrayList<Object>();
		List<List<Object>> tabledata = new ArrayList<List<Object>>();
		if (rsRows == 0) {
			throw new IllegalStateException("There is now row found in excel file [" + excelpath + "], can't "
					+ "generate map from column name to column index. ");
		}

		for (int j = 0; j < rsColumns; j++) {
			Cell cell = readsheet.getCell(j, 0);
			header.add(cell.getContents());
		}

		for (int i = 1; i < rsRows; i++) {

			if (readsheet.getCell(0, i).getContents() != "" & readsheet.getCell(1, i).getContents() != "") {
				Map<String, String> action = new HashMap<String, String>();
				action.put(header.get(1), readsheet.getCell(1, i).getContents());
				action.put(header.get(2), readsheet.getCell(2, i).getContents());
				action.put(header.get(3), readsheet.getCell(3, i).getContents());
				action.put(header.get(4), readsheet.getCell(4, i).getContents());
				action.put(header.get(5), readsheet.getCell(5, i).getContents());
				action.put(header.get(6), readsheet.getCell(6, i).getContents());
				action.put("row", Integer.toString(i));

				actions.add(action);
				testCases.add(readsheet.getCell(0, i).getContents());
			}
			if (readsheet.getCell(0, i).getContents() == "" & readsheet.getCell(1, i).getContents() != "") {
				Map<String, String> action = new HashMap<String, String>();
				action.put(header.get(1), readsheet.getCell(1, i).getContents());
				action.put(header.get(2), readsheet.getCell(2, i).getContents());
				action.put(header.get(3), readsheet.getCell(3, i).getContents());
				action.put(header.get(4), readsheet.getCell(4, i).getContents());
				action.put(header.get(5), readsheet.getCell(5, i).getContents());
				action.put(header.get(6), readsheet.getCell(6, i).getContents());
				action.put("row", Integer.toString(i));
				actions.add(action);
			}

		}
		tabledata.add(testCases);
		tabledata.add(actions);
		return tabledata;

	}

	public List<Integer> getCaseDis() {
		int rsRows = readsheet.getRows();
		List<Integer> dis = new ArrayList<Integer>();
		for (int i = 1; i < rsRows; i++) {
			Cell[] cells = readsheet.getRow(i);
			if (cells[0].getContents() != "") {
				dis.add(i);
			}
		}
		dis.add(rsRows);
		return dis;

	}

	public int count(int start, int end) {
		int first = 0;
		int second = 0;
		for (int i = 0; i < start; i++) {
			if (readsheet.getCell(1, i).getContents() != "") {

				first = first + 1;
			}
		}
		for (int j = 0; j < end; j++) {
			if (readsheet.getCell(1, j).getContents() != "") {

				second = second + 1;
			}
		}
		return second - first;
	}

	public List<Integer> realCase() {
		List<Integer> dis = getCaseDis();
		List<Integer> casecount = new ArrayList<Integer>();
		for (int i = 0; i < dis.size() - 1; i++) {
			int count = count(dis.get(i), dis.get(i + 1));
			casecount.add(count);
		}

		return casecount;
	}

	public List<Integer> real() {
		List<Integer> data = realCase();
		List<Integer> r = new ArrayList<Integer>();
		r.add(0);
		for (int i = 0; i < data.size(); i++) {
			int c = 0;
			for (int j = 0; j <= i; j++) {
				c = c + data.get(j);
			}
			r.add(c);
		}
		return r;

	}

	public Map<String, Object> getdata() {
		List<List<Object>> tabledata = readTable();
		List<Integer> real = real();
		List<List<Object>> data = new ArrayList<List<Object>>();
		List<Object> tabledata1 = (List<Object>) tabledata.get(1);
		List<Object> tabledata0 = (List<Object>) tabledata.get(0);

		for (int i = 0; i < real.size() - 1; i++) {
			data.add(tabledata1.subList(real.get(i), real.get(i + 1)));
		}

		Map<String, Object> testData = new HashMap<String, Object>();
		for (int j = 0; j < real.size() - 1; j++) {
			testData.put((String) tabledata0.get(j), data.get(j));
		}
		return testData;
	}

}
