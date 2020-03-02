package com.tyss.survey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.tyss.survey.dto.ImageModel;
import com.tyss.survey.dto.Responce;
import com.tyss.survey.dto.Survey;
import com.tyss.survey.exception.AdminException;

@Repository
public class AdminDAOImpl implements AdminDAO {

	
	@PersistenceContext
	EntityManager entityManager;
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("survey");
	
	public static EntityManager getEntityManager() {
	    return emf.createEntityManager();
	}
	@Override
	public boolean addSurvey(Survey survey) {
		EntityManager entityManager =getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			survey.setActive(true);
			entityManager.persist(survey);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Survey> retrive() {
		EntityManager entityManager =getEntityManager();
		String jpql = "select e FROM Survey e  where e.isActive=:isActive ORDER BY startDate ";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("isActive", true);
		List<Survey> list = query.getResultList();
		return list;
	}

	@Override
	public Survey retriveSurvey(int surveyId) {
		EntityManager entityManager = getEntityManager();
		String jpql = "from Survey where surveyId=:surveyId";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("surveyId", surveyId);
		Survey survey = (Survey) query.getSingleResult();
		return survey;
	}

	@Override
	public boolean removeSurvey(int surveyId) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		String jpql = "from Survey where surveyId=:surveyId";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("surveyId", surveyId);
		Survey survey = (Survey) query.getSingleResult();	
		survey.setActive(false);
		entityManager.persist(survey);
		entityTransaction.commit();
		return true;
		
	}

	@Override
	public boolean updateSurvey(Survey survey) {
		try {
			EntityManager entityManager = getEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			String jpql = "from Survey where surveyId=:surveyId";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("surveyId", survey.getSurveyId());
			Survey surveyObject = (Survey) query.getSingleResult();
			entityTransaction.begin();
			surveyObject.setDescription(survey.getDescription());
			surveyObject.setStartDate(survey.getStartDate());
			surveyObject.setEndDate(survey.getEndDate());
			surveyObject.setQuestions(survey.getQuestions());
			entityManager.persist(surveyObject);
			entityTransaction.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			throw new AdminException("The Survey name that you have not already exits !!");
		}
	}

	@Override
	public boolean saveReponce(Responce responce) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(responce);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			return false;
		}
	}
}
