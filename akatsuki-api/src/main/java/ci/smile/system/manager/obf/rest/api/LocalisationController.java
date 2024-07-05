

/*
 * Java controller for entity table localisation 
 * Created on 2021-03-15 ( Time 12:39:07 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
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
Controller for table "localisation"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/localisation")
public class LocalisationController {

	@Autowired
    private ControllerFactory<LocalisationDto> controllerFactory;
	@Autowired
	private LocalisationBusiness localisationBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<LocalisationDto> create(@RequestBody Request<LocalisationDto> request) {
    	log.info("start method /localisation/create");
        Response<LocalisationDto> response = controllerFactory.create(localisationBusiness, request, FunctionalityEnum.CREATE_LOCALISATION);
		log.info("end method /localisation/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<LocalisationDto> update(@RequestBody Request<LocalisationDto> request) {
    	log.info("start method /localisation/update");
        Response<LocalisationDto> response = controllerFactory.update(localisationBusiness, request, FunctionalityEnum.UPDATE_LOCALISATION);
		log.info("end method /localisation/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<LocalisationDto> delete(@RequestBody Request<LocalisationDto> request) {
    	log.info("start method /localisation/delete");
        Response<LocalisationDto> response = controllerFactory.delete(localisationBusiness, request, FunctionalityEnum.DELETE_LOCALISATION);
		log.info("end method /localisation/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<LocalisationDto> getByCriteria(@RequestBody Request<LocalisationDto> request) {
    	log.info("start method /localisation/getByCriteria");
        Response<LocalisationDto> response = controllerFactory.getByCriteria(localisationBusiness, request, FunctionalityEnum.VIEW_LOCALISATION);
		log.info("end method /localisation/getByCriteria");
        return response;
    }
}
