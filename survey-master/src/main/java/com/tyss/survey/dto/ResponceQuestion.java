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
@Table(name = "ResponceQuestions")
public class ResponceQuestion {

	@GeneratedValue
	@Id
	@Column
	private int questionId;
	
	@Column
	private String name;
	
	@Column
	private int questionTypeId;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "questionId")
	private List<ResponceOption> options;
	
}
