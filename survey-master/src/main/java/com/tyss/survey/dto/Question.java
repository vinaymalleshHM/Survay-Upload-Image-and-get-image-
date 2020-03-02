package com.tyss.survey.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
@Entity
@Table( name = "questions")
public class Question implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@GeneratedValue
	@Id
	@Column
	private int questionId;
	
	
	@Column
	private  String questionName;
	
	@Column
	private String questionType;
	
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "survey_id")
	private Survey survey;
	
	@ElementCollection
	@CollectionTable(name = "options")
	@Column(name="options")
	private List<String> options;
	
	
}
