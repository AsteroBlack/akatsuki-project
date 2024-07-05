
/*
 * Java controller for entity table radius
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

import ci.smile.system.manager.obf.business.RadiusBusiness;
import ci.smile.system.manager.obf.rest.fact.ControllerFactory;
import ci.smile.system.manager.obf.utils.ExceptionUtils;
import ci.smile.system.manager.obf.utils.FunctionalError;
import ci.smile.system.manager.obf.utils.StatusCode;
import ci.smile.system.manager.obf.utils.StatusMessage;
import ci.smile.system.manager.obf.utils.Validate;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.RadiusDto;
import ci.smile.system.manager.obf.utils.enums.FunctionalityEnum;
import lombok.extern.java.Log;

/**
 * Controller for table "radius"
 *
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/radius")
public class RadiusController {

	@Autowired
	private ControllerFactory<RadiusDto> controllerFactory;
	@Autowired
	private RadiusBusiness radiusBusiness;

	@Autowired
	private FunctionalError functionalError;
	@Autowired
	private HttpServletRequest requestBasic;
	@Autowired
	private ExceptionUtils exceptionUtils;

	private Logger slf4jLogger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public Response<RadiusDto> create(@RequestBody Request<RadiusDto> request) {
		log.info("start method /radius/create");
		Response<RadiusDto> response = controllerFactory.create(radiusBusiness, request,
				FunctionalityEnum.CREATE_RADIUS);
		log.info("end method /radius/create");
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public Response<RadiusDto> update(@RequestBody Request<RadiusDto> request) {
		log.info("start method /radius/update");
		Response<RadiusDto> response = controllerFactory.update(radiusBusiness, request,
				FunctionalityEnum.UPDATE_RADIUS);
		log.info("end method /radius/update");
		return response;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public Response<RadiusDto> delete(@RequestBody Request<RadiusDto> request) {
		log.info("start method /radius/delete");
		Response<RadiusDto> response = controllerFactory.delete(radiusBusiness, request,
				FunctionalityEnum.DELETE_RADIUS);
		log.info("end method /radius/delete");
		return response;
	}

	@RequestMapping(value = "/getByCriteria", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	public Response<RadiusDto> getByCriteria(@RequestBody Request<RadiusDto> request) {
		log.info("start method /radius/getByCriteria");
		Response<RadiusDto> response = controllerFactory.getByCriteria(radiusBusiness, request,
				FunctionalityEnum.VIEW_RADIUS);
		log.info("end method /radius/getByCriteria");
		return response;
	}

	@RequestMapping(value = "/getMacAdresse", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	public Response<RadiusDto> getMacAdresse(@RequestBody Request<RadiusDto> request) {
		log.info("start method /Radius/deverrouiller");
		Response<RadiusDto> response = new Response<RadiusDto>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = radiusBusiness.getMacAdresse(request, locale);
			} else {
				slf4jLogger.info("Erreur| code: {} - message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage());
				return response;
			}
			if (!response.isHasError()) {
				slf4jLogger.info("end method deverrouiller");
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
		slf4jLogger.info("end method /Radius/deverrouiller");
		return response;
	}

	@RequestMapping(value = "/getUsernameAndGroupeName", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	public Response<RadiusDto> getSomeInfoForWorkflow(@RequestBody Request<RadiusDto> request) {
		log.info("start method /Radius/deverrouiller");
		Response<RadiusDto> response = new Response<RadiusDto>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = radiusBusiness.getSomeInfoForWorkflow(request, locale);
			} else {
				slf4jLogger.info("Erreur| code: {} - message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage());
				return response;
			}
			if (!response.isHasError()) {
				slf4jLogger.info("end method getUsernameAndGroupeName");
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
		slf4jLogger.info("end method /Radius/getUsernameAndGroupeName");
		return response;
	}

}
