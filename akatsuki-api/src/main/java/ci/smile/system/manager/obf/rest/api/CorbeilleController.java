

/*
 * Java controller for entity table corbeille 
 * Created on 2023-01-26 ( Time 00:38:58 )
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
Controller for table "corbeille"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/corbeille")
public class CorbeilleController {

	@Autowired
    private ControllerFactory<CorbeilleDto> controllerFactory;
	@Autowired
	private CorbeilleBusiness corbeilleBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CorbeilleDto> create(@RequestBody Request<CorbeilleDto> request) {
    	log.info("start method /corbeille/create");
        Response<CorbeilleDto> response = controllerFactory.create(corbeilleBusiness, request, FunctionalityEnum.CREATE_CORBEILLE);
		log.info("end method /corbeille/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CorbeilleDto> update(@RequestBody Request<CorbeilleDto> request) {
    	log.info("start method /corbeille/update");
        Response<CorbeilleDto> response = controllerFactory.update(corbeilleBusiness, request, FunctionalityEnum.UPDATE_CORBEILLE);
		log.info("end method /corbeille/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CorbeilleDto> delete(@RequestBody Request<CorbeilleDto> request) {
    	log.info("start method /corbeille/delete");
        Response<CorbeilleDto> response = controllerFactory.delete(corbeilleBusiness, request, FunctionalityEnum.DELETE_CORBEILLE);
		log.info("end method /corbeille/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CorbeilleDto> getByCriteria(@RequestBody Request<CorbeilleDto> request) {
    	log.info("start method /corbeille/getByCriteria");
        Response<CorbeilleDto> response = controllerFactory.getByCriteria(corbeilleBusiness, request, FunctionalityEnum.VIEW_CORBEILLE);
		log.info("end method /corbeille/getByCriteria");
        return response;
    }
}
