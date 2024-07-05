

/*
 * Java controller for entity table qualificatif 
 * Created on 2023-01-26 ( Time 00:38:59 )
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

import ci.smile.system.manager.obf.utils.*;
import ci.smile.system.manager.obf.utils.dto.*;
import ci.smile.system.manager.obf.utils.contract.*;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.enums.FunctionalityEnum;
import ci.smile.system.manager.obf.business.*;
import ci.smile.system.manager.obf.rest.fact.ControllerFactory;

/**
Controller for table "qualificatif"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/qualificatif")
public class QualificatifController {

	@Autowired
    private ControllerFactory<QualificatifDto> controllerFactory;
	@Autowired
	private QualificatifBusiness qualificatifBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<QualificatifDto> create(@RequestBody Request<QualificatifDto> request) {
    	log.info("start method /qualificatif/create");
        Response<QualificatifDto> response = controllerFactory.create(qualificatifBusiness, request, FunctionalityEnum.CREATE_QUALIFICATIF);
		log.info("end method /qualificatif/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<QualificatifDto> update(@RequestBody Request<QualificatifDto> request) {
    	log.info("start method /qualificatif/update");
        Response<QualificatifDto> response = controllerFactory.update(qualificatifBusiness, request, FunctionalityEnum.UPDATE_QUALIFICATIF);
		log.info("end method /qualificatif/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<QualificatifDto> delete(@RequestBody Request<QualificatifDto> request) {
    	log.info("start method /qualificatif/delete");
        Response<QualificatifDto> response = controllerFactory.delete(qualificatifBusiness, request, FunctionalityEnum.DELETE_QUALIFICATIF);
		log.info("end method /qualificatif/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<QualificatifDto> getByCriteria(@RequestBody Request<QualificatifDto> request) {
    	log.info("start method /qualificatif/getByCriteria");
        Response<QualificatifDto> response = controllerFactory.getByCriteria(qualificatifBusiness, request, FunctionalityEnum.VIEW_QUALIFICATIF);
		log.info("end method /qualificatif/getByCriteria");
        return response;
    }
}
