/*
* Java controller for entity table menus 
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

import ci.smile.system.manager.obf.business.MenusBusiness;
import ci.smile.system.manager.obf.rest.fact.ControllerFactory;
import ci.smile.system.manager.obf.utils.ExceptionUtils;
import ci.smile.system.manager.obf.utils.FunctionalError;
import ci.smile.system.manager.obf.utils.StatusCode;
import ci.smile.system.manager.obf.utils.StatusMessage;
import ci.smile.system.manager.obf.utils.Validate;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.MenusDto;
import ci.smile.system.manager.obf.utils.enums.FunctionalityEnum;

/**
Controller for table "menus"
 * 
 * @author SFL Back-End developper
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value="/menus")
public class MenusController {

	@Autowired
    private ControllerFactory<MenusDto> controllerFactory;
	@Autowired
	private MenusBusiness menusBusiness;
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private FunctionalError functionalError;

	@Autowired
	private ExceptionUtils exceptionUtils;

	private Logger slf4jLogger = LoggerFactory.getLogger(getClass());

	@Autowired
	private HttpServletRequest requestBasic;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<MenusDto> create(@RequestBody Request<MenusDto> request) {
    	log.info("start method /menus/create");
        Response<MenusDto> response = controllerFactory.create(menusBusiness, request, FunctionalityEnum.CREATE_MENUS);
		log.info("end method /menus/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<MenusDto> update(@RequestBody Request<MenusDto> request) {
    	log.info("start method /menus/update");
        Response<MenusDto> response = controllerFactory.update(menusBusiness, request, FunctionalityEnum.UPDATE_MENUS);
		log.info("end method /menus/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<MenusDto> delete(@RequestBody Request<MenusDto> request) {
    	log.info("start method /menus/delete");
        Response<MenusDto> response = controllerFactory.delete(menusBusiness, request, FunctionalityEnum.DELETE_MENUS);
		log.info("end method /menus/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<MenusDto> getByCriteria(@RequestBody Request<MenusDto> request) {
    	log.info("start method /menus/getByCriteria");
        Response<MenusDto> response = controllerFactory.getByCriteria(menusBusiness, request, FunctionalityEnum.VIEW_MENUS);
		log.info("end method /menus/getByCriteria");
        return response;
    }
	
	
	@RequestMapping(value = "/deverrouiller", method = RequestMethod.POST, consumes = {
	"application/json" }, produces = { "application/json" })
	public Response<MenusDto> deverrouiller(@RequestBody Request<MenusDto> request) {
		log.info("start method /Menus/deverrouiller");
		Response<MenusDto> response = new Response<>();

		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = menusBusiness.deverrouiller(request, locale);
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
		slf4jLogger.info("end method /Menus/deverrouiller");
		return response;
	}


	
	
	@RequestMapping(value = "/verrouiller", method = RequestMethod.POST, consumes = {
	"application/json" }, produces = { "application/json" })
	public Response<MenusDto> verrouiller(@RequestBody Request<MenusDto> request) {
		log.info("start method /Menus/verrouiller");
		Response<MenusDto> response = new Response<MenusDto>();

		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = menusBusiness.verrouiller(request, locale);
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
		slf4jLogger.info("end method /Menus/verrouiller");
		return response;
	}
}
