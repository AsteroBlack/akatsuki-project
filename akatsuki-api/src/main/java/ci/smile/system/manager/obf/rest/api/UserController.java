

/*
 * Java controller for entity table user 
 * Created on 2019-12-16 ( Time 11:38:54 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package ci.smile.system.manager.obf.rest.api;

import java.util.Locale;
import java.util.Map;

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

import ci.smile.system.manager.obf.business.UserBusiness;
import ci.smile.system.manager.obf.rest.fact.ControllerFactory;
import ci.smile.system.manager.obf.utils.ExceptionUtils;
import ci.smile.system.manager.obf.utils.FunctionalError;
import ci.smile.system.manager.obf.utils.StatusCode;
import ci.smile.system.manager.obf.utils.StatusMessage;
import ci.smile.system.manager.obf.utils.Validate;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.UserDto;
import ci.smile.system.manager.obf.utils.enums.FunctionalityEnum;

/**
Controller for table "user"
 * 
 * @author SFL Back-End developper
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private ControllerFactory<UserDto> controllerFactory;
	@Autowired
	private UserBusiness userBusiness;

	
	private Logger log = LoggerFactory.getLogger(getClass());

	
	@Autowired
	private FunctionalError functionalError;

	@Autowired
	private ExceptionUtils exceptionUtils;

	private Logger slf4jLogger = LoggerFactory.getLogger(getClass());

	@Autowired
	private HttpServletRequest requestBasic;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
	public Response<UserDto> create(@RequestBody Request<UserDto> request) {
		log.info("start method /user/create");
		Response<UserDto> response = controllerFactory.create(userBusiness, request, FunctionalityEnum.CREATE_USER);
		log.info("end method /user/create");
		return response;
	}

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
	public Response<UserDto> update(@RequestBody Request<UserDto> request) {
		log.info("start method /user/update");
		Response<UserDto> response = controllerFactory.update(userBusiness, request, FunctionalityEnum.UPDATE_USER);
		log.info("end method /user/update");
		return response;
	}

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
	public Response<UserDto> delete(@RequestBody Request<UserDto> request) {
		log.info("start method /user/delete");
		Response<UserDto> response = controllerFactory.delete(userBusiness, request, FunctionalityEnum.DELETE_USER);
		log.info("end method /user/delete");
		return response;
	}

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
	public Response<UserDto> getByCriteria(@RequestBody Request<UserDto> request) {
		log.info("start method /user/getByCriteria");
		Response<UserDto> response = controllerFactory.getByCriteria(userBusiness, request, FunctionalityEnum.VIEW_USER);
		log.info("end method /user/getByCriteria");
		return response;
	}

	@RequestMapping(value = "/connexionLdap", method = RequestMethod.POST, consumes = {
	"application/json" }, produces = { "application/json" })
	public Response<UserDto> loginLdap(@RequestBody Request<UserDto> request) {
		log.info("start method /user/loginLdap");
		Response<UserDto> response = new Response<UserDto>();

		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = userBusiness.connexionLdap(request, locale);
			} else {
				return response;
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
		log.info("end method /user/loginLdap");
		return response;
	}

	@RequestMapping(value = "/connexion", method = RequestMethod.POST, consumes = {
	"application/json" }, produces = { "application/json" })
	public Response<UserDto> connexion(@RequestBody Request<UserDto> request) {
		log.info("start method /user/connexion");
		Response<UserDto> response = new Response<UserDto>();

		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = userBusiness.connexion(request, locale);
			} else {
				return response;
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
		log.info("end method /user/connexion");
		return response;
	}


	@RequestMapping(value="/getPublicKey",method=RequestMethod.GET,produces={"application/json"})
	public Response<UserDto> getPublicKey() {
		slf4jLogger.info("start method /user/getPublicKey");
		Response<UserDto> response = new Response<UserDto>();

		String languageID = (String)requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");

		try {
			if(!response.isHasError()){
				//response = userBusiness.getPublicKey(locale);
			}else{
				slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage());
				return response;
			}

			if(!response.isHasError()){
				slf4jLogger.info("end method getPublicKey");
				slf4jLogger.info("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS);  
			}else{
				slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage());
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
		slf4jLogger.info("end method /user/getPublicKey");
		return response;
	}

	
	
	
	@RequestMapping(value = "/getUserByLap", method = RequestMethod.POST, consumes = {
	"application/json" }, produces = { "application/json" })
	public Response<UserDto> getUserByLap(@RequestBody Request<UserDto> request) {
		log.info("start method /user/getUserByLap");
		Response<UserDto> response = new Response<UserDto>();

		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = userBusiness.getUserByLap(request, locale);
			} else {
				slf4jLogger.info("Erreur| code: {} - message: {}",
				response.getStatus().getCode(), response.getStatus().getMessage());
				return response;
			}
			if (!response.isHasError()) {
				slf4jLogger.info("end method getUserByLap");
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
		slf4jLogger.info("end method /user/getUserByLap");
		return response;
	}



	
	@RequestMapping(value = "/deverrouiller", method = RequestMethod.POST, consumes = {
	"application/json" }, produces = { "application/json" })
	public Response<UserDto> deverrouiller(@RequestBody Request<UserDto> request) {
		log.info("start method /user/deverrouiller");
		Response<UserDto> response = new Response<UserDto>();

		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = userBusiness.deverrouiller(request, locale);
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
		slf4jLogger.info("end method /user/deverrouiller");
		return response;
	}


	
	
	@RequestMapping(value = "/verrouiller", method = RequestMethod.POST, consumes = {
	"application/json" }, produces = { "application/json" })
	public Response<UserDto> verrouiller(@RequestBody Request<UserDto> request) {
		log.info("start method /user/verrouiller");
		Response<UserDto> response = new Response<UserDto>();

		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = userBusiness.verrouiller(request, locale);
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
		slf4jLogger.info("end method /user/verrouiller");
		return response;
	}


	

	@RequestMapping(value = "/deconnexion", method = RequestMethod.POST, consumes = {"application/json" }, produces = { "application/json" })
	public Response<UserDto> deconnexion(@RequestBody Request<UserDto> request) {
		log.info("start method /user/deconnexion");
		Response<UserDto> response = new Response<UserDto>();

		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = userBusiness.deconnexion(request, locale);
			} else {
				slf4jLogger.info("Erreur| code: {} - message: {}",
				response.getStatus().getCode(), response.getStatus().getMessage());
				return response;
			}
			if (!response.isHasError()) {
				slf4jLogger.info("end method deconnexion");
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
		slf4jLogger.info("end method /user/deconnexion");
		return response;
	}
	


	
	
	
	@RequestMapping(value = "/getUserCreate", method = RequestMethod.POST, consumes = {"application/json" }, produces = { "application/json" })
	public Response<UserDto> getUserCreate(@RequestBody Request<UserDto> request) {
		log.info("start method /user/getUserCreate");
		Response<UserDto> response = new Response<UserDto>();

		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
			//	response = userBusiness.getUserCreate(request, locale);
			} else {
				slf4jLogger.info("Erreur| code: {} - message: {}",
				response.getStatus().getCode(), response.getStatus().getMessage());
				return response;
			}
			if (!response.isHasError()) {
				slf4jLogger.info("end method getUserCreate");
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
		slf4jLogger.info("end method /user/getUserCreate");
		return response;
	}
	
	
	@RequestMapping(value="/getActiveSession",method=RequestMethod.GET,produces={"application/json"})
	public Response<UserDto> getActiveSession() {
		slf4jLogger.info("start method /user/getActiveSession");
		Response<UserDto> response = new Response<UserDto>();

		String languageID = (String)requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");

		try {
			if(!response.isHasError()){
				//response = userBusiness.getActiveSession(locale);
			}else{
				slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage());
				return response;
			}
			if(!response.isHasError()){
				slf4jLogger.info("end method getActiveSession");
				slf4jLogger.info("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS);  
			}else{
				slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage());
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
		slf4jLogger.info("end method /user/getActiveSession");
		return response;
	}

	
	@RequestMapping(value = "/connexionExterne", method = RequestMethod.POST, consumes = {
	"application/json" }, produces = { "application/json" })
	public Response<UserDto> connexionExterne(@RequestBody Request<UserDto> request) {
		log.info("start method /user/connexionExterne");
		Response<UserDto> response = new Response<UserDto>();

		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
		//		response = userBusiness.connexionExterne(request, locale);
			} else {
				return response;
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
		log.info("end method /user/connexionExterne");
		return response;
	}

	@RequestMapping(value = "/getSessionUser", method = RequestMethod.POST, consumes = {
	"application/json" }, produces = { "application/json" })
	public Response<UserDto> getSessionUser(@RequestBody Request<UserDto> request) {
		log.info("start method /user/getSessionUser");
		Response<UserDto> response = new Response<UserDto>();

		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
		//		response = userBusiness.getSessionUser(request, locale);
			} else {
				return response;
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
		log.info("end method /user/getSessionUser");
		return response;
	}
	
	
	
	@RequestMapping(value="/deleteKeys",method = RequestMethod.POST, consumes = {
	"application/json" },produces={"application/json"})
	public Response<Map<String, Object>> deleteKeys(@RequestBody Request<UserDto> request) {
		slf4jLogger.info("start method /user/deleteKeys");
		Response<Map<String, Object>> response = new Response<>();

		String languageID = (String)requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");

		try {
		//	response = userBusiness.deleteKeys(locale);

			if(!response.isHasError()){
				slf4jLogger.info("end method deleteKeys");
				slf4jLogger.info("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS);  
			}else{
				slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage());
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
		slf4jLogger.info("end method /user/deleteKeys");
		return response;
	}

}
