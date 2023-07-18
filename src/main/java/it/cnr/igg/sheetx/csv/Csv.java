package it.cnr.igg.sheetx.csv;

import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

import it.cnr.igg.sheetx.exceptions.SheetxException;

public class Csv {
	private File csvData = null;
	private ArrayList<ArrayList<String>> content = null;

	public Csv(String filePath) {
		csvData = new File(filePath);
	}
	
	public Map<String, Integer> getHeaders() throws Exception {
		FileReader fr = null;
		try {
			fr = new FileReader(csvData);
			CSVParser parser = new CSVParser(fr, CSVFormat.DEFAULT); // CSVParser.parse(csvData, CSVFormat.EXCEL);
			Map<String, Integer> headers = parser.getHeaderMap();
			return headers;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (fr != null)
				fr.close();
		}
	
	}

	public ArrayList<ArrayList<String>> getContent() throws Exception {
		content = new ArrayList<ArrayList<String>>();
		FileReader fr = null;
		try {
			fr = new FileReader(csvData);
			CSVParser parser = new CSVParser(fr, CSVFormat.DEFAULT); // CSVParser.parse(csvData, CSVFormat.EXCEL);
			List<CSVRecord> records = parser.getRecords();
			for (CSVRecord r : records) {
				System.out.println(r.toString());
			}
			return content;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (fr != null)
				fr.close();
		}
	}

}
