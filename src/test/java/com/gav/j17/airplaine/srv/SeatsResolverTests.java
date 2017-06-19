package com.gav.j17.airplaine.srv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gav.j17.airplaine.dto.SeatsDescr;
import com.gav.j17.airplaine.dto.SeatsResultDto;



/**
 * @author alex
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeatsResolverTests {
	@Autowired
	private SeatsResolver seatsResolver;
	
	@Test
	public void shouldEmpty_DescrNull() {
		//given:
		SeatsDescr descr = null;
		//when:
		SeatsResultDto result = seatsResolver.resolve(descr);
		//then:
		assertEquals(false, result.isCorrectlyFilled());
		assertSame(0, result.getSatisfactionPercent());
	}
	
	@Test
	public void shouldEmpty_DescrZeroRows() {
		//given:
		SeatsDescr descr = new SeatsDescr(0, 5);
		descr.setTravelerGroups(Collections.emptyList());
		//when:
		SeatsResultDto result = seatsResolver.resolve(descr);
		//then:
		assertEquals(false, result.isCorrectlyFilled());
		assertSame(0, result.getSatisfactionPercent());
	}
	
	@Test
	public void shouldEmpty_DescrZeroSeats() {
		//given:
		SeatsDescr descr = new SeatsDescr(5, 0);
		descr.setTravelerGroups(Collections.emptyList());
		//when:
		SeatsResultDto result = seatsResolver.resolve(descr);
		//then:
		assertEquals(false, result.isCorrectlyFilled());
		assertSame(0, result.getSatisfactionPercent());
	}

	@Test
	public void shouldEmpty_DescrZeroRowsAndSeats() {
		//given:
		SeatsDescr descr = new SeatsDescr(0, 0);
		descr.setTravelerGroups(Collections.emptyList());
		//when:
		SeatsResultDto result = seatsResolver.resolve(descr);
		//then:
		assertEquals(false, result.isCorrectlyFilled());
		assertSame(0, result.getSatisfactionPercent());
	}
	
	@Test
	public void shouldEmpty_DescrEmptyGroups() {
		//given:
		SeatsDescr descr = new SeatsDescr(5, 5);
		descr.setTravelerGroups(Collections.emptyList());
		//when:
		SeatsResultDto result = seatsResolver.resolve(descr);
		//then:
		assertEquals(false, result.isCorrectlyFilled());
		assertSame(0, result.getSatisfactionPercent());
	}
	
	@Test
	public void testShortBasicGroup() {
		//given:
		SeatsDescr descr = new SeatsDescr(4, 4);
		descr.addTravelerGroup(Arrays.asList("1", "2", "3W", "4W"));
		//when:
		SeatsResultDto result = seatsResolver.resolve(descr);
		//then:
		//assertEquals(true, result.isCorrectlyFilled());
		//assertSame(100, result.getSatisfactionPercent());
		List<String> row1 = Arrays.asList("3W", "1", "2", "4W");
		List<List<String>> expectedSalon = new ArrayList<List<String>>();
		expectedSalon.add(row1);
		assertEquals(expectedSalon, result.getSalon());
	}
	
	@Test
	public void testNormalGroups() {
		//given:
		SeatsDescr descr = new SeatsDescr(4, 4);
		descr.addTravelerGroup(Arrays.asList("1", "2", "3W", "4W"));
		descr.addTravelerGroup(Arrays.asList("5W", "6W", "7", "8"));
		//when:
		SeatsResultDto result = seatsResolver.resolve(descr);
		//then:
		//assertEquals(true, result.isCorrectlyFilled());
		//assertSame(100, result.getSatisfactionPercent());
		List<String> row1 = Arrays.asList("3W", "1", "2", "4W");
		List<String> row2 = Arrays.asList("5W", "7", "8", "6W");
		List<List<String>> expectedSalon = new ArrayList<List<String>>();
		expectedSalon.add(row1);
		expectedSalon.add(row2);
		assertEquals(expectedSalon, result.getSalon());
	}
	
	@Test
	public void test_WindowSitterInOneRow() {
		//given:
		SeatsDescr descr = new SeatsDescr(4, 4);
		descr.addTravelerGroup(Arrays.asList("6W", "5W", "3W", "4W"));
		descr.addTravelerGroup(Arrays.asList("2", "1", "7", "8"));
		//when:
		SeatsResultDto result = seatsResolver.resolve(descr);
		//then:
		//assertEquals(true, result.isCorrectlyFilled());
		//assertSame(100, result.getSatisfactionPercent());
		List<String> row1 = Arrays.asList("6W", "7", "8", "5W");
		List<String> row2 = Arrays.asList("3W", "2", "1", "4W");
		List<List<String>> expectedSalon = new ArrayList<List<String>>();
		expectedSalon.add(row1);
		expectedSalon.add(row2);
		assertEquals(expectedSalon, result.getSalon());
	}
	
	@Test
	public void test_ThereAreNoWindowSitters() {
		//given:
		SeatsDescr descr = new SeatsDescr(4, 4);
		descr.addTravelerGroup(Arrays.asList("6", "5", "3", "4"));
		descr.addTravelerGroup(Arrays.asList("2", "1", "7", "8"));
		//when:
		SeatsResultDto result = seatsResolver.resolve(descr);
		//then:
		//assertEquals(true, result.isCorrectlyFilled());
		//assertSame(100, result.getSatisfactionPercent());
		List<String> row1 = Arrays.asList("3", "6", "5", "4");
		List<String> row2 = Arrays.asList("7", "2", "1", "8");
		List<List<String>> expectedSalon = new ArrayList<List<String>>();
		expectedSalon.add(row1);
		expectedSalon.add(row2);
		assertEquals(expectedSalon, result.getSalon());
	}
	
	@Test
	public void test_ThereAreNoMiddleSitters() {
		//given:
		SeatsDescr descr = new SeatsDescr(4, 4);
		descr.addTravelerGroup(Arrays.asList("1W", "2W", "3W", "4W"));
		descr.addTravelerGroup(Arrays.asList("5W", "6W", "7W", "8W"));
		//when:
		SeatsResultDto result = seatsResolver.resolve(descr);
		//then:
		//assertEquals(true, result.isCorrectlyFilled());
		//assertSame(100, result.getSatisfactionPercent());
		List<String> row1 = Arrays.asList("1W", "3W", "4W", "2W");
		List<String> row2 = Arrays.asList("5W", "7W", "8W", "6W");
		List<List<String>> expectedSalon = new ArrayList<List<String>>();
		expectedSalon.add(row1);
		expectedSalon.add(row2);
		assertEquals(expectedSalon, result.getSalon());
	}

}
