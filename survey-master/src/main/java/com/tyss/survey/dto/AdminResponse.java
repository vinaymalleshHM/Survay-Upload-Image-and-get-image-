package com.tyss.survey.dto;

import java.util.List;

import lombok.Data;

@Data
public class AdminResponse {

	private boolean error;

	private String message;

	private List<Survey> list;
	
}
