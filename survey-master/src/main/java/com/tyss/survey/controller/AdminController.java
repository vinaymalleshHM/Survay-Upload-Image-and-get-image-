package com.tyss.survey.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.executable.ValidateOnExecution;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyss.survey.dto.AdminResponse;
import com.tyss.survey.dto.ImageModel;
import com.tyss.survey.dto.Responce;
import com.tyss.survey.dto.Survey;
import com.tyss.survey.service.AdminService;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("tyss")
@ValidateOnExecution
public class AdminController {

	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

	@Autowired
	AdminService adminServices;

	@Autowired
	ServletContext context;

	@PostMapping(path = "/add-survey-details")
	public ResponseEntity<AdminResponse> addSurvey(@RequestParam("file") MultipartFile file,
			@RequestParam("user") String user) throws IOException {
		Survey survey = new ObjectMapper().readValue(user, Survey.class);
		survey.setFileName(file.getOriginalFilename());

		boolean isExist = new File(context.getRealPath("/userprofile/")).exists();
		if (isExist) {
			new File(context.getRealPath("/userprofile")).mkdir();
		}
		String fileName = file.getOriginalFilename();
		String modifString = FilenameUtils.getBaseName(fileName) + "_" + System.currentTimeMillis() + "."
				+ FilenameUtils.getExtension(fileName);
		File serverFile = new File(context.getRealPath("/userprofile/" + File.separator + modifString));
		try {
			FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
		} catch (Exception e) {
			// TODO: handle exception
		}
		survey.setFileName(modifString);
		if (adminServices.addSurvey(survey)) {

			AdminResponse response = new AdminResponse();
			response.setError(false);
			response.setMessage("successfully added");
			return new ResponseEntity<AdminResponse>(response, HttpStatus.OK);
		} else {
			AdminResponse response = new AdminResponse();
			response.setError(true);
			response.setMessage("successfully not added");
			return new ResponseEntity<AdminResponse>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path = "/update-survey-details", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AdminResponse updateSurvey(@RequestBody Survey questions) {
		AdminResponse adminResponse = new AdminResponse();
		if (adminServices.updateSurvey(questions)) {
			adminResponse.setError(false);
			adminResponse.setMessage("Survey is updated successfully");
		}
		return adminResponse;
	}

	@GetMapping(path = "/survey-details", produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminResponse getSurvey() {
		AdminResponse adminResponse = new AdminResponse();
		List<Survey> list = adminServices.retrive();
		if (list != null) {
			adminResponse.setError(false);
			adminResponse.setMessage("Details of Survey");
			adminResponse.setList(list);
		}
		return adminResponse;
	}

	@GetMapping(path = "/{surveyId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminResponse getSurveyName(@PathVariable(name = "surveyId") int surveyId) {
		AdminResponse adminResponse = new AdminResponse();
		Survey survey = adminServices.retriveSurvey(surveyId);
		if (survey != null) {
			adminResponse.setError(false);
			adminResponse.setList(Arrays.asList(survey));
		}
		return adminResponse;
	}

	@DeleteMapping(path = "/{surveyId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminResponse deleteSurveyName(@PathVariable int surveyId) {
		AdminResponse adminResponse = new AdminResponse();

		if (adminServices.removeSurvey(surveyId)) {
			adminResponse.setError(false);
			adminResponse.setMessage("Survey is deleted");
		}
		return adminResponse;
	}

	@GetMapping("/images")
	public ResponseEntity<List<String>> getImages(){
		List<String> images=new ArrayList<String>();
		String filepath=context.getRealPath("/userprofile");
		File filefolder=new File(filepath);
		if(filefolder!=null) {
			for (final File file : filefolder.listFiles()) {
				if(!file.isDirectory()) {
					String encodebase64=null;
					try {
					String extension=FilenameUtils.getExtension(file.getName());
					FileInputStream fileinput=new FileInputStream(file);
					byte[] bytes=new byte[(int)file.length()];
					fileinput.read(bytes);
					encodebase64=Base64.getEncoder().encodeToString(bytes);
					images.add("data:image/"+extension+";base64,"+encodebase64);
					fileinput.close();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
					
			}
		}
		return new ResponseEntity<List<String>>(images,HttpStatus.OK);
	}
	
	@PostMapping(path = "/survey-responce", produces = MediaType.APPLICATION_JSON_VALUE,consumes =MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<AdminResponse> addSurveyResponce(@RequestBody Responce responce){
		if (adminServices.saveReponce(responce)) {

			AdminResponse response = new AdminResponse();
			response.setError(false);
			response.setMessage("successfully added");
			return new ResponseEntity<AdminResponse>(response, HttpStatus.OK);
		} else {
			AdminResponse response = new AdminResponse();
			response.setError(true);
			response.setMessage("successfully not added");
			return new ResponseEntity<AdminResponse>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
}