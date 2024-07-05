

/*
 * Java controller for entity table offer_groupe 
 * Created on 2023-01-14 ( Time 17:25:32 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package ci.smile.system.manager.obf.rest.api;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.TransactionSystemException;
import java.util.Locale;

import ci.smile.system.manager.obf.utils.*;
import ci.smile.system.manager.obf.utils.dto.*;
import ci.smile.system.manager.obf.utils.contract.*;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.enums.FunctionalityEnum;
import ci.smile.system.manager.obf.business.*;
import ci.smile.system.manager.obf.rest.fact.ControllerFactory;

/**
Controller for table "offer_groupe"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/offerGroupe")
public class OfferGroupeController {

     @Autowired
	 private HttpServletRequest requestBasic;
	 @Autowired
	 private FunctionalError functionalError;
	 @Autowired
	 private ExceptionUtils exceptionUtils;

	 private Logger slf4jLogger = LoggerFactory.getLogger(getClass());

	@Autowired
    private ControllerFactory<OfferGroupeDto> controllerFactory;
	@Autowired
	private OfferGroupeBusiness offerGroupeBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<OfferGroupeDto> create(@RequestBody Request<OfferGroupeDto> request) {
    	log.info("start method /offerGroupe/create");
        Response<OfferGroupeDto> response = controllerFactory.create(offerGroupeBusiness, request, FunctionalityEnum.CREATE_OFFER_GROUPE);
		log.info("end method /offerGroupe/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<OfferGroupeDto> update(@RequestBody Request<OfferGroupeDto> request) {
    	log.info("start method /offerGroupe/update");
        Response<OfferGroupeDto> response = controllerFactory.update(offerGroupeBusiness, request, FunctionalityEnum.UPDATE_OFFER_GROUPE);
		log.info("end method /offerGroupe/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<OfferGroupeDto> delete(@RequestBody Request<OfferGroupeDto> request) {
    	log.info("start method /offerGroupe/delete");
        Response<OfferGroupeDto> response = controllerFactory.delete(offerGroupeBusiness, request, FunctionalityEnum.DELETE_OFFER_GROUPE);
		log.info("end method /offerGroupe/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<OfferGroupeDto> getByCriteria(@RequestBody Request<OfferGroupeDto> request) {
    	log.info("start method /offerGroupe/getByCriteria");
        Response<OfferGroupeDto> response = controllerFactory.getByCriteria(offerGroupeBusiness, request, FunctionalityEnum.VIEW_OFFER_GROUPE);
		log.info("end method /offerGroupe/getByCriteria");
        return response;
    }

    @RequestMapping(value = "/custom", method = RequestMethod.POST, consumes = {
	   "application/json" }, produces = { "application/json" })
	 public Response<OfferGroupeDto> custom(@RequestBody Request<OfferGroupeDto> request) {
	  log.info("OfferGroupeDto method /$/custom");
	  Response<OfferGroupeDto> response = new Response<OfferGroupeDto>();
	  String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
	  Locale locale = new Locale(languageID, "");
	  try {
	   response = Validate.validateList(request, response, functionalError, locale);
	   if (!response.isHasError()) {
	    response = offerGroupeBusiness.custom(request, locale);
	   } else {
	    slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
	      response.getStatus().getMessage());
	    return response;
	   }
	   if (!response.isHasError()) {
	    slf4jLogger.info("end method custom");
	    slf4jLogger.info("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS);
	   } else {
	    slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
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
	  slf4jLogger.info("end method /OfferGroupeDto/custom");
	  return response;
	 }
}
