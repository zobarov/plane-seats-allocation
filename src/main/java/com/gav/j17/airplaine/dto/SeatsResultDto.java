package com.gav.j17.airplaine.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SeatsResultDto {
	private List<List<String>> salon;	
	private Integer satisfactionPercent;
	private boolean isCorrectlyFilled;
	
	public SeatsResultDto(Integer rows, Integer seats) {
		this.salon = new ArrayList<List<String>>();
		for(int i = 0; i < rows; i++) {
			List<String> row = new ArrayList<String>(seats);
			Collections.fill(row, "N/A");
			this.salon.add(row);
		}
		this.satisfactionPercent = 0;
		this.isCorrectlyFilled = false;
	}

	public List<List<String>> getSalon() {
		return salon;
	}

	public void setSalon(List<List<String>> salon) {
		this.salon = salon;
	}

	public Integer getSatisfactionPercent() {
		return satisfactionPercent;
	}

	public void setSatisfactionPercent(Integer satisfactionPercent) {
		this.satisfactionPercent = satisfactionPercent;
	}

	public boolean isCorrectlyFilled() {
		return isCorrectlyFilled;
	}

	public void setCorrectlyFilled(boolean isCorrectlyFilled) {
		this.isCorrectlyFilled = isCorrectlyFilled;
	}

}
