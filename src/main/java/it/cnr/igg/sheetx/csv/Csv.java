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

//	public Map<String, Integer> getHeaders() throws Exception {
//		FileReader fr = null;
//		try {
//			fr = new FileReader(csvData);
//			CSVParser parser = new CSVParser(fr, CSVFormat.DEFAULT); // CSVParser.parse(csvData, CSVFormat.EXCEL);
//			Map<String, Integer> headers = parser.getHeaderMap();
//			return headers;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw ex;
//		} finally {
//			if (fr != null)
//				fr.close();
//		}
//
//	}

	public ArrayList<ArrayList<String>> getContent() throws Exception {
		content = new ArrayList<ArrayList<String>>();
		FileReader fr = null;
		CSVParser parser = null;
		try {
			fr = new FileReader(csvData);
			parser = new CSVParser(fr, CSVFormat.DEFAULT);
			// parser = new CSVParser(fr, CSVFormat.DEFAULT); // CSVParser.parse(csvData,
			// CSVFormat.EXCEL);
			List<CSVRecord> records = null;
			try {
				records = parser.getRecords();
			} catch (Exception x) {
				try {
					parser.close();
					fr.close();
					fr = new FileReader(csvData);
					parser = new CSVParser(fr, CSVFormat.Builder.create().setDelimiter(';').build());
					records = parser.getRecords();
				} catch (Exception e) {
					try {
						parser.close();
						fr.close();
						fr = new FileReader(csvData);
						parser = new CSVParser(fr, CSVFormat.Builder.create().setDelimiter('	').build());
						records = parser.getRecords();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}

			for (CSVRecord r : records) {
				ArrayList<String> row = new ArrayList<String>();
//				System.out.println(r.toString());
				Iterator<String> i = r.iterator();
				while (i.hasNext()) {
					row.add("" + i.next());
				}
				content.add(row);
			}
			return content;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (parser != null) {
				parser.close();
			}
			if (fr != null)
				fr.close();
		}
	}

	public static void main(String[] args) {
		Csv csv = new Csv("\\dev\\test2.csv");
		try {
//			Map<String, Integer> map = csv.getHeaders();
//			if (map != null) {
//				Set<String> keys = map.keySet();
//				Iterator<String> it = keys.iterator();
//				while(it.hasNext()) {
//					System.out.println("header: " + it.next());
//				}
//			}
			ArrayList<ArrayList<String>> content = csv.getContent();
			for (ArrayList<String> row : content) {
				for (String col : row) {
					System.out.print(col + " ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
