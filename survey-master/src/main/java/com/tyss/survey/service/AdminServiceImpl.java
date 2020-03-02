package com.tyss.survey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.survey.dao.AdminDAO;
import com.tyss.survey.dto.ImageModel;
import com.tyss.survey.dto.Responce;
import com.tyss.survey.dto.Survey;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO dao;

	@Override
	public boolean addSurvey(Survey survey) {
		// TODO Auto-generated method stub
		return dao.addSurvey(survey);
	}

	@Override
	public List<Survey> retrive() {
		// TODO Auto-generated method stub
		return dao.retrive();
	}

	@Override
	public Survey retriveSurvey(int surveyId) {
		// TODO Auto-generated method stub
		return dao.retriveSurvey(surveyId);
	}

	@Override
	public boolean removeSurvey(int surveyId) {
		// TODO Auto-generated method stub
		return dao.removeSurvey(surveyId);
	}

	@Override
	public boolean updateSurvey(Survey survey) {
		// TODO Auto-generated method stub
		return dao.updateSurvey(survey);
	}

	@Override
	public boolean saveReponce(Responce responce) {
		// TODO Auto-generated method stub
		return dao.saveReponce(responce);
	}

}
