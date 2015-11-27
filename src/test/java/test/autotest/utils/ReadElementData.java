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

public class ReadElementData {
	private String excelpath;
	private String sheet;
	private Sheet readsheet;

	public ReadElementData(String excelpath, String sheet) {
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
		List<Object> elements = new ArrayList<Object>();
		List<Object> pages = new ArrayList<Object>();
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

			List<Object> element = new ArrayList<Object>();
			if (readsheet.getCell(0, i).getContents() != "" & readsheet.getCell(1, i).getContents() != "") {
				Map<String, String> elementValue = new HashMap<String, String>();
				elementValue.put(header.get(2), readsheet.getCell(2, i).getContents());
				elementValue.put(header.get(3), readsheet.getCell(3, i).getContents());
				elementValue.put(header.get(4), readsheet.getCell(4, i).getContents());
				element.add(readsheet.getCell(1, i).getContents());
				element.add(elementValue);
				elements.add(element);
				pages.add(readsheet.getCell(0, i).getContents());
			}
			if (readsheet.getCell(0, i).getContents() == "" & readsheet.getCell(1, i).getContents() != "") {
				Map<String, String> elementValue = new HashMap<String, String>();
				elementValue.put(header.get(2), readsheet.getCell(2, i).getContents());
				elementValue.put(header.get(3), readsheet.getCell(3, i).getContents());
				elementValue.put(header.get(4), readsheet.getCell(4, i).getContents());
				element.add(readsheet.getCell(1, i).getContents());
				element.add(elementValue);
				elements.add(element);
			}

		}
		tabledata.add(pages);
		tabledata.add(elements);
		return tabledata;

	}

	public List<Integer> getPageDis() {
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

	public List<Integer> realPage() {
		List<Integer> dis = getPageDis();
		List<Integer> pagecount = new ArrayList<Integer>();
		for (int i = 0; i < dis.size() - 1; i++) {
			int count = count(dis.get(i), dis.get(i + 1));
			pagecount.add(count);
		}
		return pagecount;
	}

	public List<Integer> real() {
		List<Integer> data = realPage();
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

	@SuppressWarnings("unchecked")
	public Map<String, Map<String, Map<String, String>>> getdata() {
		List<List<Object>> tabledata = readTable();
		List<Integer> real = real();
		List<List<Object>> data = new ArrayList<List<Object>>();
		List<Object> tabledata1 = (List<Object>) tabledata.get(1);
		List<Object> tabledata0 = (List<Object>) tabledata.get(0);

		for (int i = 0; i < real.size() - 1; i++) {
			data.add(tabledata1.subList(real.get(i), real.get(i + 1)));
		}

		Map<String, Map<String, Map<String, String>>> d = new HashMap<String, Map<String, Map<String, String>>>();

		for (int j = 0; j < real.size() - 1; j++) {
			Map<String, Map<String, String>> a = new HashMap<String, Map<String, String>>();
			for (int l = 0; l < data.get(j).size(); l++) {
				String kk = (String) ((List<?>) ((List<?>) data.get(j)).get(l)).get(0);
				Map<String, String> vv = (Map<String, String>) ((List<?>) ((List<?>) data.get(j)).get(l)).get(1);
				a.put(kk, vv);
			}
			d.put((String) tabledata0.get(j), a);
		}
		return d;
	}

}
