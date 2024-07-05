/*
* Java controller for entity table Action 
 * Created on 2019-12-19 ( Time 23:54:47 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
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

import ci.smile.system.manager.obf.business.ActionBusiness;
import ci.smile.system.manager.obf.rest.fact.ControllerFactory;
import ci.smile.system.manager.obf.utils.ExceptionUtils;
import ci.smile.system.manager.obf.utils.FunctionalError;
import ci.smile.system.manager.obf.utils.StatusCode;
import ci.smile.system.manager.obf.utils.StatusMessage;
import ci.smile.system.manager.obf.utils.Validate;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.ActionDto;
import ci.smile.system.manager.obf.utils.enums.FunctionalityEnum;

/**
Controller for table "Action"
 * 
 * @author SFL Back-End developper
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value="/action")
public class ActionController {

	@Autowired
    private ControllerFactory<ActionDto> controllerFactory;
	@Autowired
	private ActionBusiness actionBusiness;
	

	@Autowired
	private FunctionalError functionalError;

	@Autowired
	private ExceptionUtils exceptionUtils;

	private Logger slf4jLogger = LoggerFactory.getLogger(getClass());
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private HttpServletRequest requestBasic;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ActionDto> create(@RequestBody Request<ActionDto> request) {
    	log.info("start method /Action/create");
        Response<ActionDto> response = controllerFactory.create(actionBusiness, request, FunctionalityEnum.CREATE_ACTION);
		log.info("end method /Action/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ActionDto> update(@RequestBody Request<ActionDto> request) {
    	log.info("start method /Action/update");
        Response<ActionDto> response = controllerFactory.update(actionBusiness, request, FunctionalityEnum.UPDATE_ACTION);
		log.info("end method /Action/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ActionDto> delete(@RequestBody Request<ActionDto> request) {
    	log.info("start method /Action/delete");
        Response<ActionDto> response = controllerFactory.delete(actionBusiness, request, FunctionalityEnum.DELETE_ACTION);
		log.info("end method /Action/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ActionDto> getByCriteria(@RequestBody Request<ActionDto> request) {
    	log.info("start method /Action/getByCriteria");
        Response<ActionDto> response = controllerFactory.getByCriteria(actionBusiness, request, FunctionalityEnum.VIEW_ACTION);
		log.info("end method /Action/getByCriteria");
        return response;
    }
	
	@RequestMapping(value = "/deverrouiller", method = RequestMethod.POST, consumes = {
	"application/json" }, produces = { "application/json" })
	public Response<ActionDto> deverrouiller(@RequestBody Request<ActionDto> request) {
		log.info("start method /Action/deverrouiller");
		Response<ActionDto> response = new Response<ActionDto>();

		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = actionBusiness.deverrouiller(request, locale);
			} else {
				slf4jLogger.info("Erreur| code: {} - message: {}",
				response.getStatus().getCode(), response.getStatus().getMessage());
				return response;
			}
			if (!response.isHasError()) {
				slf4jLogger.info("end method deverrouiller");
				slf4jLogger.info("code: {} - message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS);
			} else {
				slf4jLogger.info("Erreur| code: {} - message: {}",
						response.getStatus().getCode(), response.getStatus().getMessage());
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
		slf4jLogger.info("end method /Action/deverrouiller");
		return response;
	}


	
	
	@RequestMapping(value = "/getAll", method = RequestMethod.POST, consumes = {
	"application/json" }, produces = { "application/json" })
	public Response<ActionDto> getAll(@RequestBody Request<ActionDto> request) {
		log.info("start method /Action/getAll");
		Response<ActionDto> response = new Response<ActionDto>();

		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = actionBusiness.getAll(request, locale);
			} else {
				slf4jLogger.info("Erreur| code: {} - message: {}",
				response.getStatus().getCode(), response.getStatus().getMessage());
				return response;
			}
			if (!response.isHasError()) {
				slf4jLogger.info("end method getAll");
				slf4jLogger.info("code: {} - message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS);
			} else {
				slf4jLogger.info("Erreur| code: {} - message: {}",
						response.getStatus().getCode(), response.getStatus().getMessage());
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
		slf4jLogger.info("end method /Action/getAll");
		return response;
	}
	
	
	@RequestMapping(value = "/verrouiller", method = RequestMethod.POST, consumes = {
	"application/json" }, produces = { "application/json" })
	public Response<ActionDto> verrouiller(@RequestBody Request<ActionDto> request) {
		log.info("start method /Action/verrouiller");
		Response<ActionDto> response = new Response<ActionDto>();

		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = actionBusiness.verrouiller(request, locale);
			} else {
				slf4jLogger.info("Erreur| code: {} - message: {}",
				response.getStatus().getCode(), response.getStatus().getMessage());
				return response;
			}
			if (!response.isHasError()) {
				slf4jLogger.info("end method verrouiller");
				slf4jLogger.info("code: {} - message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS);
			} else {
				slf4jLogger.info("Erreur| code: {} - message: {}",
						response.getStatus().getCode(), response.getStatus().getMessage());
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
		slf4jLogger.info("end method /Action/verrouiller");
		return response;
	}


}
