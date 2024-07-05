

/*
 * Java controller for entity table groupe_radius 
 * Created on 2021-03-15 ( Time 12:39:06 )
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
Controller for table "groupe_radius"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/groupeRadius")
public class GroupeRadiusController {

	@Autowired
    private ControllerFactory<GroupeRadiusDto> controllerFactory;
	@Autowired
	private GroupeRadiusBusiness groupeRadiusBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<GroupeRadiusDto> create(@RequestBody Request<GroupeRadiusDto> request) {
    	log.info("start method /groupeRadius/create");
        Response<GroupeRadiusDto> response = controllerFactory.create(groupeRadiusBusiness, request, FunctionalityEnum.CREATE_GROUPE_RADIUS);
		log.info("end method /groupeRadius/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<GroupeRadiusDto> update(@RequestBody Request<GroupeRadiusDto> request) {
    	log.info("start method /groupeRadius/update");
        Response<GroupeRadiusDto> response = controllerFactory.update(groupeRadiusBusiness, request, FunctionalityEnum.UPDATE_GROUPE_RADIUS);
		log.info("end method /groupeRadius/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<GroupeRadiusDto> delete(@RequestBody Request<GroupeRadiusDto> request) {
    	log.info("start method /groupeRadius/delete");
        Response<GroupeRadiusDto> response = controllerFactory.delete(groupeRadiusBusiness, request, FunctionalityEnum.DELETE_GROUPE_RADIUS);
		log.info("end method /groupeRadius/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<GroupeRadiusDto> getByCriteria(@RequestBody Request<GroupeRadiusDto> request) {
    	log.info("start method /groupeRadius/getByCriteria");
        Response<GroupeRadiusDto> response = controllerFactory.getByCriteria(groupeRadiusBusiness, request, FunctionalityEnum.VIEW_GROUPE_RADIUS);
		log.info("end method /groupeRadius/getByCriteria");
        return response;
    }
}
