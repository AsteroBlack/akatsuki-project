
/*
 * Java controller for entity table coefficient_modele 
 * Created on 2021-03-09 ( Time 11:10:42 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package ci.smile.system.manager.obf.rest.api;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ci.smile.system.manager.obf.business.CoefficientModeleBusiness;
import ci.smile.system.manager.obf.rest.fact.ControllerFactory;
import ci.smile.system.manager.obf.utils.ExceptionUtils;
import ci.smile.system.manager.obf.utils.FunctionalError;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.CoefficientModeleDto;
import ci.smile.system.manager.obf.utils.enums.FunctionalityEnum;
import lombok.extern.java.Log;

/**
 * Controller for table "coefficient_modele"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/coefficientModele")
public class CoefficientModeleController {

	@Autowired
	private ControllerFactory<CoefficientModeleDto> controllerFactory;
	@Autowired
	private CoefficientModeleBusiness coefficientModeleBusiness;
	
	@Autowired
	private FunctionalError functionalError;
	@Autowired
	private HttpServletRequest requestBasic;
	@Autowired
	private ExceptionUtils exceptionUtils;

	private Logger slf4jLogger = LoggerFactory.getLogger(getClass());


	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public Response<CoefficientModeleDto> create(@RequestBody Request<CoefficientModeleDto> request) {
		log.info("start method /coefficientModele/create");
		Response<CoefficientModeleDto> response = controllerFactory.create(coefficientModeleBusiness, request,
				FunctionalityEnum.CREATE_COEFFICIENT_MODELE);
		log.info("end method /coefficientModele/create");
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public Response<CoefficientModeleDto> update(@RequestBody Request<CoefficientModeleDto> request) {
		log.info("start method /coefficientModele/update");
		Response<CoefficientModeleDto> response = controllerFactory.update(coefficientModeleBusiness, request,
				FunctionalityEnum.UPDATE_COEFFICIENT_MODELE);
		log.info("end method /coefficientModele/update");
		return response;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public Response<CoefficientModeleDto> delete(@RequestBody Request<CoefficientModeleDto> request) {
		log.info("start method /coefficientModele/delete");
		Response<CoefficientModeleDto> response = controllerFactory.delete(coefficientModeleBusiness, request,
				FunctionalityEnum.DELETE_COEFFICIENT_MODELE);
		log.info("end method /coefficientModele/delete");
		return response;
	}

	@RequestMapping(value = "/getByCriteria", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	public Response<CoefficientModeleDto> getByCriteria(@RequestBody Request<CoefficientModeleDto> request) {
		log.info("start method /coefficientModele/getByCriteria");
		Response<CoefficientModeleDto> response = controllerFactory.getByCriteria(coefficientModeleBusiness, request,
				FunctionalityEnum.VIEW_COEFFICIENT_MODELE);
		log.info("end method /coefficientModele/getByCriteria");
		return response;
	}

	@RequestMapping(value = "/getCoefficient", method = RequestMethod.GET, produces = { "application/json" })
	public Response<CoefficientModeleDto> getCoefficient() {
		log.info("start method /coefficientModele/getCoefficient");
		Response<CoefficientModeleDto> response = new Response<CoefficientModeleDto>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = coefficientModeleBusiness.getCoefficient();
		} catch (CannotCreateTransactionException e) {
			exceptionUtils.CANNOT_CREATE_TRANSACTION_EXCEPTION(response, locale, e);
		} catch (TransactionSystemException e) {
			exceptionUtils.TRANSACTION_SYSTEM_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		}
		log.info("end method /coefficientModele/getCoefficient");
		return response;
	}

	@RequestMapping(value = "/logicToSearch", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	public Object logicToSearch(@RequestBody Request<CoefficientModeleDto> request) {
		log.info("start method /coefficientModele/logicToSearch");
		Object response = new Response<CoefficientModeleDto>();
		Locale locale = new Locale("fr");
		try {
			response = coefficientModeleBusiness.logicToSearch(request, locale);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("end method /coefficientModele/logicToSearch");
		return response;
	}

	@RequestMapping(value = "/getNdByMac", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public Object searchNdByMac(@RequestBody Request<CoefficientModeleDto> request) {
		log.info("start method /coefficientModele/getNdByMac");
		Response<CoefficientModeleDto> response = new Response<CoefficientModeleDto>();
		Locale locale = new Locale("fr");
		try {
			response = coefficientModeleBusiness.getNdByMac(request, locale);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("end method /coefficientModele/getNdByMac");
		return response;
	}

}
