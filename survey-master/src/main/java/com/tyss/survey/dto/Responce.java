package com.tyss.survey.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Responce")
public class Responce {

	@GeneratedValue
	@Id
	@Column
	private int responceId;
	
	@Column
	private String surveyName;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "responceId")
	private List<ResponceQuestion> questions;
}
