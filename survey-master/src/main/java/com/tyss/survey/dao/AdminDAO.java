package com.tyss.survey.dao;

import java.util.List;

import com.tyss.survey.dto.ImageModel;
import com.tyss.survey.dto.Responce;
import com.tyss.survey.dto.Survey;

public interface AdminDAO {

	public boolean addSurvey(Survey survey);

	public List<Survey> retrive();

	
	public Survey retriveSurvey(int surveyId);

	public boolean removeSurvey(int surveyId);

	public boolean updateSurvey(Survey survey);

	
	public boolean saveReponce(Responce responce);

}
