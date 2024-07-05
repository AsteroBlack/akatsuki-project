
/*
 * Java controller for entity table attribut 
 * Created on 2021-04-22 ( Time 11:55:13 )
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

import ci.smile.system.manager.obf.business.AttributBusiness;
import ci.smile.system.manager.obf.rest.fact.ControllerFactory;
import ci.smile.system.manager.obf.utils.ExceptionUtils;
import ci.smile.system.manager.obf.utils.FunctionalError;
import ci.smile.system.manager.obf.utils.StatusCode;
import ci.smile.system.manager.obf.utils.StatusMessage;
import ci.smile.system.manager.obf.utils.Validate;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.AttributDto;
import ci.smile.system.manager.obf.utils.enums.FunctionalityEnum;
import lombok.extern.java.Log;

/**
 * Controller for table "attribut"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/attribut")
public class AttributController {

	@Autowired
	private ControllerFactory<AttributDto> controllerFactory;
	@Autowired
	private AttributBusiness attributBusiness;
	
	@Autowired
	private FunctionalError functionalError;
	@Autowired
	private HttpServletRequest requestBasic;
	@Autowired
	private ExceptionUtils exceptionUtils;

	private Logger slf4jLogger = LoggerFactory.getLogger(getClass());


	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public Response<AttributDto> create(@RequestBody Request<AttributDto> request) {
		log.info("start method /attribut/create");
		Response<AttributDto> response = controllerFactory.create(attributBusiness, request,
				FunctionalityEnum.CREATE_ATTRIBUT);
		log.info("end method /attribut/create");
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public Response<AttributDto> update(@RequestBody Request<AttributDto> request) {
		log.info("start method /attribut/update");
		Response<AttributDto> response = controllerFactory.update(attributBusiness, request,
				FunctionalityEnum.UPDATE_ATTRIBUT);
		log.info("end method /attribut/update");
		return response;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public Response<AttributDto> delete(@RequestBody Request<AttributDto> request) {
		log.info("start method /attribut/delete");
		Response<AttributDto> response = controllerFactory.delete(attributBusiness, request,
				FunctionalityEnum.DELETE_ATTRIBUT);
		log.info("end method /attribut/delete");
		return response;
	}

	@RequestMapping(value = "/getByCriteria", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	public Response<AttributDto> getByCriteria(@RequestBody Request<AttributDto> request) {
		log.info("start method /attribut/getByCriteria");
		Response<AttributDto> response = controllerFactory.getByCriteria(attributBusiness, request,
				FunctionalityEnum.VIEW_ATTRIBUT);
		log.info("end method /attribut/getByCriteria");
		return response;
	}

	@RequestMapping(value = "/getFormulaire", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	public Response<AttributDto> getFormulaire(@RequestBody Request<AttributDto> request) {
		log.info("start method /Radius/getFormulaire");
		Response<AttributDto> response = new Response<AttributDto>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = attributBusiness.getFormulaire(request, locale);
			} else {
				slf4jLogger.info("Erreur| code: {} - message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage());
				return response;
			}
			if (!response.isHasError()) {
				slf4jLogger.info("end method getFormulaire");
				slf4jLogger.info("code: {} - message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS);
			} else {
				slf4jLogger.info("Erreur| code: {} - message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage());
			}
		} catch (CannotCreateTransactionException e) {
			exceptionUtils.CANNOT_CREATE_TRANSACTION_EXCEPTION(response, locale, e);
		} catch (TransactionSystemException e) {
			exceptionUtils.TRANSACTION_SYSTEM_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		}
		slf4jLogger.info("end method /Radius/getFormulaire");
		return response;
	}

	@RequestMapping(value = "/getRadiusAttributPostpaid", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	public Response<AttributDto> getRadiusAttributPostpaid(@RequestBody Request<AttributDto> request) {
		log.info("start method /Radius/getRadiusAttribut");
		Response<AttributDto> response = new Response<AttributDto>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = attributBusiness.getRadiusAttribut(request, locale);
			} else {
				slf4jLogger.info("Erreur| code: {} - message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage());
				return response;
			}
			if (!response.isHasError()) {
				slf4jLogger.info("end method getRadiusAttributPostpaid");
				slf4jLogger.info("code: {} - message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS);
			} else {
				slf4jLogger.info("Erreur| code: {} - message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage());
			}
		} catch (CannotCreateTransactionException e) {
			exceptionUtils.CANNOT_CREATE_TRANSACTION_EXCEPTION(response, locale, e);
		} catch (TransactionSystemException e) {
			exceptionUtils.TRANSACTION_SYSTEM_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		}
		slf4jLogger.info("end method /Radius/getRadiusAttributPostpaid");
		return response;
	}

	@RequestMapping(value = "/getRadiusAttributPrepaid", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	public Response<AttributDto> getRadiusAttributPrepaid(@RequestBody Request<AttributDto> request) {
		log.info("start method /Radius/getRadiusAttribut");
		Response<AttributDto> response = new Response<AttributDto>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = attributBusiness.getRadiusAttributExpiration(request, locale);
			} else {
				slf4jLogger.info("Erreur| code: {} - message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage());
				return response;
			}
			if (!response.isHasError()) {
				slf4jLogger.info("end method getRadiusAttribut");
				slf4jLogger.info("code: {} - message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS);
			} else {
				slf4jLogger.info("Erreur| code: {} - message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage());
			}
		} catch (CannotCreateTransactionException e) {
			exceptionUtils.CANNOT_CREATE_TRANSACTION_EXCEPTION(response, locale, e);
		} catch (TransactionSystemException e) {
			exceptionUtils.TRANSACTION_SYSTEM_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		}
		slf4jLogger.info("end method /Radius/getRadiusAttributPrepaid");
		return response;
	}

}
