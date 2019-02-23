package test.model;

import static org.junit.Assert.*;

import org.junit.Test;

import uz.diploma.model.record.source.InternetSource;

public class SourceLocationTest {
	
	

	@Test
	public void createCorectURIReturnsCorrectlyFormatedString() {
		String incorrectlyFormatedWebAddress = "www.allegro.pl";
		InternetSource source = new InternetSource(incorrectlyFormatedWebAddress);
		assertTrue(source.getSource().equals("http://www.allegro.pl"));
	}

}
