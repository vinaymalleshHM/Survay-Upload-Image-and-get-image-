
package com.tyss.survey.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.Data;

@Data
@Entity
@Table(name = "survey")
public class Survey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column
	private int surveyId;

	@Column
	private String surveyName;

	@Column
	@NotBlank(message = "Survey Descrption cannot be blank")
	private String description;

	@Column
	private Date startDate;

	@Column
	private Date endDate;
	
	@Column
	private boolean isActive;

	@Column
	private String fileName;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "survey_id")
	private List<Question> questions;
//	
//	@OneToOne
//	private ImageModel model;

}
