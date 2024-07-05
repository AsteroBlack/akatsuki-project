

/*
 * Java controller for entity table assignation 
 * Created on 2023-01-26 ( Time 00:38:56 )
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
Controller for table "assignation"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/assignation")
public class AssignationController {

	@Autowired
    private ControllerFactory<AssignationDto> controllerFactory;
	@Autowired
	private AssignationBusiness assignationBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AssignationDto> create(@RequestBody Request<AssignationDto> request) {
    	log.info("start method /assignation/create");
        Response<AssignationDto> response = controllerFactory.create(assignationBusiness, request, FunctionalityEnum.CREATE_ASSIGNATION);
		log.info("end method /assignation/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AssignationDto> update(@RequestBody Request<AssignationDto> request) {
    	log.info("start method /assignation/update");
        Response<AssignationDto> response = controllerFactory.update(assignationBusiness, request, FunctionalityEnum.UPDATE_ASSIGNATION);
		log.info("end method /assignation/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AssignationDto> delete(@RequestBody Request<AssignationDto> request) {
    	log.info("start method /assignation/delete");
        Response<AssignationDto> response = controllerFactory.delete(assignationBusiness, request, FunctionalityEnum.DELETE_ASSIGNATION);
		log.info("end method /assignation/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AssignationDto> getByCriteria(@RequestBody Request<AssignationDto> request) {
    	log.info("start method /assignation/getByCriteria");
        Response<AssignationDto> response = controllerFactory.getByCriteria(assignationBusiness, request, FunctionalityEnum.VIEW_ASSIGNATION);
		log.info("end method /assignation/getByCriteria");
        return response;
    }
}
