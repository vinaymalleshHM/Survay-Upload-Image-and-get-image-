package com.tyss.survey.dto;

import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse {

	private boolean error;
	
	private List<String> details;
	
}
