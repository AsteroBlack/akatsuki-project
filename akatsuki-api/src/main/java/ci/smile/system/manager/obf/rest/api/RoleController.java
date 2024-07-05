


/*
 * Java controller for entity table role 
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

import ci.smile.system.manager.obf.business.RoleBusiness;
import ci.smile.system.manager.obf.rest.fact.ControllerFactory;
import ci.smile.system.manager.obf.utils.ExceptionUtils;
import ci.smile.system.manager.obf.utils.FunctionalError;
import ci.smile.system.manager.obf.utils.StatusCode;
import ci.smile.system.manager.obf.utils.StatusMessage;
import ci.smile.system.manager.obf.utils.Validate;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.RoleDto;
import ci.smile.system.manager.obf.utils.enums.FunctionalityEnum;

/**
Controller for table "role"
 * 
 * @author SFL Back-End developper
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value="/role")
public class RoleController {

	@Autowired
    private ControllerFactory<RoleDto> controllerFactory;
	@Autowired
	private RoleBusiness roleBusiness;
	

	@Autowired
	private FunctionalError functionalError;

	@Autowired
	private ExceptionUtils exceptionUtils;

	private Logger slf4jLogger = LoggerFactory.getLogger(getClass());
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private HttpServletRequest requestBasic;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleDto> create(@RequestBody Request<RoleDto> request) {
    	log.info("start method /role/create");
        Response<RoleDto> response = controllerFactory.create(roleBusiness, request, FunctionalityEnum.CREATE_ROLE);
		log.info("end method /role/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleDto> update(@RequestBody Request<RoleDto> request) {
    	log.info("start method /role/update");
        Response<RoleDto> response = controllerFactory.update(roleBusiness, request, FunctionalityEnum.UPDATE_ROLE);
		log.info("end method /role/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleDto> delete(@RequestBody Request<RoleDto> request) {
    	log.info("start method /role/delete");
        Response<RoleDto> response = controllerFactory.delete(roleBusiness, request, FunctionalityEnum.DELETE_ROLE);
		log.info("end method /role/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleDto> getByCriteria(@RequestBody Request<RoleDto> request) {
    	log.info("start method /role/getByCriteria");
        Response<RoleDto> response = controllerFactory.getByCriteria(roleBusiness, request, FunctionalityEnum.VIEW_ROLE);
		log.info("end method /role/getByCriteria");
        return response;
    }
	
	
	
	
	@RequestMapping(value = "/deverrouiller", method = RequestMethod.POST, consumes = {
	"application/json" }, produces = { "application/json" })
	public Response<RoleDto> deverrouiller(@RequestBody Request<RoleDto> request) {
		log.info("start method /Role/deverrouiller");
		Response<RoleDto> response = new Response<>();

		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = roleBusiness.deverrouiller(request, locale);
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
		slf4jLogger.info("end method /Role/deverrouiller");
		return response;
	}


	
	
	@RequestMapping(value = "/verrouiller", method = RequestMethod.POST, consumes = {
	"application/json" }, produces = { "application/json" })
	public Response<RoleDto> verrouiller(@RequestBody Request<RoleDto> request) {
		log.info("start method /Role/verrouiller");
		Response<RoleDto> response = new Response<RoleDto>();

		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = roleBusiness.verrouiller(request, locale);
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
		slf4jLogger.info("end method /Role/verrouiller");
		return response;
	}
	
	
	
}
