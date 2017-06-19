package com.gav.j17.airplaine.srv;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gav.j17.airplaine.dto.SeatsDescr;
import com.gav.j17.airplaine.dto.SeatsResultDto;
import com.gav.j17.airplaine.util.Loggable;

import ch.qos.logback.classic.Logger;

/**
 * @author alex
 *
 */
@Component
public class SeatsResolver {
	@Loggable
	private Logger logger;
	
	public SeatsResultDto resolve(final SeatsDescr descr) {
		if(!isValidDescr(descr)) {
			logger.warn("Not valid sets descriptor.");
			return new SeatsResultDto(0, 0);
		}
		logger.info("Ok. Forming result by descriptor: {}", descr);
		SeatsResultDto result = new SeatsResultDto(descr.getRowsNumber(), descr.getSeatsNumber());

		List<List<String>> travelGroups = descr.getTravelerGroups();
		
		LinkedList<PotentialRow> potentialRows = new LinkedList<PotentialRow>();
		LinkedList<String> rejectedWindowSitter = new LinkedList<String>();
		LinkedList<String> rejectedMiddleSitter = new LinkedList<String>();
		
		for(List<String> row : travelGroups) {
			PotentialRow potentialRow = new PotentialRow(descr.getSeatsNumber());
			for(String s : row) {
				if(s.endsWith("W")) {
					if(!potentialRow.addWindowSitter(s)) {
						rejectedWindowSitter.add(s);
					}
				} else {
					if(!potentialRow.addMiddleSitter(s)) {
						rejectedMiddleSitter.add(s);
					}
				}
			}
			potentialRows.add(potentialRow);
		}
		//normalize chucks. window sitters in priority:		
		for(PotentialRow row : potentialRows) {
			while(!row.isFullInWindow()) {
				String windowSitter = rejectedWindowSitter.pollFirst();
				if(windowSitter != null) {
					boolean added = row.addWindowSitter(windowSitter);
					if(!added) {
						break;
					}
				} else {
					String middleSitter = rejectedMiddleSitter.pollFirst();
					if(middleSitter != null) {
						boolean added = row.addWindowSitter(middleSitter);
						if(!added) {
							break;
						}
					} else {
						break;
					}
				}
			}
			//middle
			while(!row.isFullInMiddle()) {
				String middleSitter = rejectedMiddleSitter.pollFirst();
				if(middleSitter != null) {
					boolean added = row.addMiddleSitter(middleSitter);
					if(!added) {
						break;
					}
				} else {
					String windowSitter = rejectedWindowSitter.pollFirst();
					if(windowSitter != null) {
						boolean added = row.addMiddleSitter(windowSitter);
						if(!added) {
							break;
						}
					}
				}
			}			
		}		
		
		//evaluate allocation:
		List<List<String>> salon = new ArrayList<>();
		for(PotentialRow row : potentialRows) {
			List<String> salonRow = new ArrayList<String>(descr.getSeatsNumber());
			
			if(row.isNormalized()) {
				salonRow.add(row.windowSitter.get(0));
				salonRow.addAll(row.middleSitters);
				salonRow.add(row.windowSitter.get(1));
			} else {
				//TODO: any special non-normalized algorithm?
			}
			
			salon.add(salonRow);
		}		
		
		result.setSalon(salon);
		return result;		
	}
	
	private class PotentialRow {
		private int middleSize;

		private List<String> middleSitters = new ArrayList<>();
		private List<String> windowSitter = new ArrayList<>();
		
		public PotentialRow(int seatsInRow) {
			this.middleSize = seatsInRow - 2;
		}
		
		public boolean addWindowSitter(String s) {
			if(windowSitter.size() >= 2) {
				return false;
			}
			return  windowSitter.add(s);
		}
		
		public boolean addMiddleSitter(String s) {
			if(middleSitters.size() >= middleSize) {
				return false;
			}
			return middleSitters.add(s);
		}
		
		public boolean isFullInMiddle() {
			return middleSitters.size() >= middleSize;			
		}
		
		public boolean isFullInWindow() {
			return windowSitter.size() == 2;
		}
		
		public boolean isNormalized() {
			return isFullInMiddle() && isFullInWindow();
		}
	}
	
	private boolean isValidDescr(SeatsDescr descr) {
		if(descr == null) {
			logger.error("Travelers list desciption cannot be null");
			return false;
		}
		if(!(descr.getRowsNumber() > 0 && descr.getSeatsNumber() > 0)) {
			logger.error("Number of rows/seats has to be positive. Got: {}/{}.", descr.getRowsNumber(), descr.getSeatsNumber());
			return false;
		}
		if(descr.getTravelerGroups() == null || descr.getTravelerGroups().size() == 0) {
			logger.error("Travelers group list has to be filled. Got: {}", descr.getTravelerGroups());
			return false;
		}
		return true;
	}
}
