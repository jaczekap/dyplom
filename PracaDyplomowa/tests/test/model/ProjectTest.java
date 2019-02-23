package test.model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import testData.DataTestFiller;
import uz.diploma.model.project.Project;
import uz.diploma.model.record.element.Record;

public class ProjectTest {
	
	DataTestFiller filler;
	Project project;
	Record testRecord1;
	Record testRecord2;

	@Before
	public void setUp() throws Exception {
		filler = new DataTestFiller();
		project = filler.getProject();
		testRecord1 = filler.getRecord();
		testRecord2 = filler.getRecord1();
	}

	@Test
	public void findRecordsByTableOfContents_ShouldFindExistingData() {
		List<Record> testList = Arrays.asList(testRecord2);
		assertEquals(testList, project.findRecordsByTableOfContents("osady"));
	}

	@Test
	public void findRecordsByDescription_ShouldFindExistingData() {
		List<Record> testList = Arrays.asList(testRecord1, testRecord2);
		assertEquals(testList, project.findRecordsByDescription("pañstw"));
	}

	@Test
	public void findRecordsByKeyWord_ShouldFindExistingData() {
		List<Record> testList = Arrays.asList(testRecord1, testRecord2);
		assertEquals(testList, project.findRecordsByKeyWord("tron"));
	}

	@Test
	public void findRecordsByType_ShouldFindExistingData() {
		List<Record> testList = Arrays.asList(testRecord2);
		assertEquals(testList, project.findRecordsByType("magazine"));
	}

	@Test
	public void findRecordsByPeriod_ShouldFindExistingData() {
		List<Record> testList = Arrays.asList(testRecord2);
		assertEquals(testList, project.findRecordsByPeriod("modern"));
	}

	@Test
	public void findRecordsByTitle_ShouldFindExistingData() {
		List<Record> testList = Arrays.asList(testRecord1);
		assertEquals(testList, project.findRecordsByTitle("Grecji"));
	}

	@Test
	public void findRecordsByEvent_ShouldFindExistingData() {
		List<Record> testList = Arrays.asList(testRecord1);
		assertEquals(testList, project.findRecordsByEvent("Maratonem"));
	}

	@Test
	public void findRecordsByAuthor_ShouldFindExistingData() {
		List<Record> testList = Arrays.asList(testRecord2);
		assertEquals(testList, project.findRecordsByAuthor("Zenek"));
	}

	@Test
	public void findRecordsByLocation_ShouldFindExistingData() {
		List<Record> testList = Arrays.asList(testRecord2);
		assertEquals(testList, project.findRecordsByLocation("Mezopotamia"));
	}

	@Test
	public void findRecordsByCharacter_ShouldFindExistingData() {
		List<Record> testList = Arrays.asList(testRecord1);
		assertEquals(testList, project.findRecordsByCharacter("Perykles"));
	}

	@Test
	public void findRecordsByQuotation_ShouldFindExistingData() {
		List<Record> testList = Arrays.asList(testRecord1);
		assertEquals(testList, project.findRecordsByQuotation("perskiej"));
	}

	@Test
	public void findAll_ShouldFindExistingData() {
		List<Record> testList = Arrays.asList(testRecord2, testRecord1);
		assertEquals(testList, project.findAll("okres"));
	}
	
	@Test
	public void findAll_ShouldNotFindAnyData() {
		List<Record> testList = Arrays.asList(testRecord2, testRecord1);
		assertNotEquals(testList, project.findAll("alllalllaa"));
	}

}
