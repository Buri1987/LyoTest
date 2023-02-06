package com.nvl.TestCaseAdapter.OwnJavaLibs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nvl.TestCaseAdapter.OSLCServerTestCasesThirdPartyToolResourcesFactory;
import com.nvl.TestCaseAdapter.resources.TestCase;
import org.eclipse.lyo.oslc.domains.qm.*;
import org.eclipse.lyo.oslc4j.core.model.Link;



public class CsvFileManager {

	private static final String SRC_MAIN_RESOURCES_TEST_CASES_CSV = "src/main/resources/TestCases.CSV";
	private static String CSV_FIELD_SEPARATOR = ",";
	public static void main(String[] args) throws URISyntaxException {
		loadTestCasesFromFile(SRC_MAIN_RESOURCES_TEST_CASES_CSV);
	}

	static public List<TestCase> loadTestCasesFromFile(String path) throws URISyntaxException {
		List<TestCase> clients = null;
		File csvfile = new File(path);
		try {
			clients = parseRecordsToClients(csvfile);

			System.out.println("DEBUG");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return clients;
	}

	static private String[] loadCsvFileRecords(File file) throws IOException {
		List<String> records = new ArrayList<String>();
		BufferedReader br = null;
		br = new BufferedReader(new FileReader(file));
		String record = null;
		while ((record = br.readLine()) != null)
			records.add(record);
		br.close();
		return records.toArray(new String[records.size()]);
	}
	public static void saveTestCasesToCSVFile(List<TestCase> testCases, File file) {

		// Prepare text content to save into file
		String buffer = "ID" + CSV_FIELD_SEPARATOR + "Name" + CSV_FIELD_SEPARATOR + "Priority" + CSV_FIELD_SEPARATOR
				+ "State" + CSV_FIELD_SEPARATOR + "Owner" + CSV_FIELD_SEPARATOR + "CreatedBy" + CSV_FIELD_SEPARATOR
				+ "Type" + CSV_FIELD_SEPARATOR + "Function" + CSV_FIELD_SEPARATOR + "TestPhase" + CSV_FIELD_SEPARATOR
				+ "Weight" + CSV_FIELD_SEPARATOR + "Modified" + CSV_FIELD_SEPARATOR + "ValidatesRequirements\r\n";
		for (TestCase tc : testCases) {
			buffer = buffer + tc.getIdentifier();
			buffer = buffer + CSV_FIELD_SEPARATOR + tc.getIdentifier();
			buffer = buffer + CSV_FIELD_SEPARATOR + tc.getTitle();
			buffer = buffer + CSV_FIELD_SEPARATOR + tc.getIdentifier();
			buffer = buffer + CSV_FIELD_SEPARATOR + tc.getTitle();
			//buffer = buffer + CSV_FIELD_SEPARATOR + tc.getDokumentenNummer();
			//buffer = buffer + CSV_FIELD_SEPARATOR + tc.getTestLevel();
			buffer = buffer + CSV_FIELD_SEPARATOR + ZonedDateTime
					.ofInstant(tc.getCreated().toInstant(), ZoneId.systemDefault()).toString();


			buffer = buffer + "\r\n";
        	
         
        
        	
        }


		// Save array of test cases into CSV file
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
		} catch (IOException e) {
			System.out.println("Can not construct file writer");
		}
		if (fw != null) {
			bw = new BufferedWriter(fw);
			try {
				bw.write(buffer);
				bw.flush();
			} catch (IOException e) {
				System.out.println("Can not write to CSV file");
			}
		}
	}

	static private List<TestCase> parseRecordsToClients(File file) throws IOException, URISyntaxException {
		List<TestCase> testCases = new ArrayList<TestCase>();
		String[] records = loadCsvFileRecords(file);
		for (int i = 0; i < records.length; i++) {
			if (i == 0)
				continue;
			String[] values = records[i].split(",");
			// Client client = new Client();
			TestCase testCase = OSLCServerTestCasesThirdPartyToolResourcesFactory.createTestCase(values[0]);
			testCase.setIdentifier(values[0]);
			testCase.setTitle(values[1]);
			testCase.setDokumentenNummer(values[2]);
			testCase.setTestLevel(values[3]);
			ZonedDateTime created = ZonedDateTime.parse(values[4]);
			//URI uri = new URI("http://open-services.net/ns/qm#TestCase");
			//Link link = new Link(uri, "TC");
			//testCase.setRdfObject("");
			//testCase.setType("http://open-services.net/ns/qm#TestCase");
			//testCase.addType(new URI("http://open-services.net/ns/qm#TestCase"));
			URI uri = new URI("http://open-services.net/ns/qm#TestCase");
			Link link = new Link(uri);
			
			//Set<URI> type = new HashSet<>();
			//type.add(uri);
			//testCase.setTypes(type);
			
			//testCase.setType(type);
			///testCase.addTypes(link);
			//testCase.addRdfObject(link);
			testCase.setCreated(Date.from(created.toInstant()));


			
			


			testCases.add(testCase);
		}
		return testCases;
	}

}
