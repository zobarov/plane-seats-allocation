package com.gav.j17.airplaine.dto;

import java.util.ArrayList;
import java.util.List;

public class SeatsDescr {
	private Integer rowsNumber;
	private Integer seatsNumber;	
	private List<List<String>> travelerGroups;
	
	public SeatsDescr(Integer rows, Integer seats) {
		this.rowsNumber = rows;
		this.seatsNumber = seats;
	}
	
	public Integer getRowsNumber() {
		return rowsNumber;
	}

	public void setRowsNumber(Integer rowsNumber) {
		this.rowsNumber = rowsNumber;
	}

	public Integer getSeatsNumber() {
		return seatsNumber;
	}

	public void setSeatsNumber(Integer seatsNumber) {
		this.seatsNumber = seatsNumber;
	}

	public List<List<String>> getTravelerGroups() {
		return travelerGroups;
	}
	
	public void addTravelerGroup(List<String> aGroup) {
		if(this.travelerGroups == null) {
			travelerGroups = new ArrayList<List<String>>();
		}
		this.travelerGroups.add(aGroup);		
	}

	public void setTravelerGroups(List<List<String>> travelerGroups) {
		this.travelerGroups = travelerGroups;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("SeatsDescr");
		sb.append("{rows:").append(rowsNumber).append(", seats: ")
			.append(seatsNumber).append(", travelGroups:")
			.append(travelerGroups.toString())
			.append("}");
		return sb.toString();
	}

	

}
