package com.tyss.survey.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ResponceOption")
public class ResponceOption {

	@GeneratedValue
	@Id
	@Column
	private int optionId;
	
	@Column
	private String name;
	
	@Column
	private boolean selected;
}
