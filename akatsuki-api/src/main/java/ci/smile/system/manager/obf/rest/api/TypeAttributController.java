

/*
 * Java controller for entity table type_attribut 
 * Created on 2021-04-22 ( Time 11:56:54 )
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
Controller for table "type_attribut"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/typeAttribut")
public class TypeAttributController {

	@Autowired
    private ControllerFactory<TypeAttributDto> controllerFactory;
	@Autowired
	private TypeAttributBusiness typeAttributBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TypeAttributDto> create(@RequestBody Request<TypeAttributDto> request) {
    	log.info("start method /typeAttribut/create");
        Response<TypeAttributDto> response = controllerFactory.create(typeAttributBusiness, request, FunctionalityEnum.CREATE_TYPE_ATTRIBUT);
		log.info("end method /typeAttribut/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TypeAttributDto> update(@RequestBody Request<TypeAttributDto> request) {
    	log.info("start method /typeAttribut/update");
        Response<TypeAttributDto> response = controllerFactory.update(typeAttributBusiness, request, FunctionalityEnum.UPDATE_TYPE_ATTRIBUT);
		log.info("end method /typeAttribut/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TypeAttributDto> delete(@RequestBody Request<TypeAttributDto> request) {
    	log.info("start method /typeAttribut/delete");
        Response<TypeAttributDto> response = controllerFactory.delete(typeAttributBusiness, request, FunctionalityEnum.DELETE_TYPE_ATTRIBUT);
		log.info("end method /typeAttribut/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TypeAttributDto> getByCriteria(@RequestBody Request<TypeAttributDto> request) {
    	log.info("start method /typeAttribut/getByCriteria");
        Response<TypeAttributDto> response = controllerFactory.getByCriteria(typeAttributBusiness, request, FunctionalityEnum.VIEW_TYPE_ATTRIBUT);
		log.info("end method /typeAttribut/getByCriteria");
        return response;
    }
}
