/*
 * Java controller for entity table module 
 * Created on 2019-12-19 ( Time 23:54:47 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package ci.smile.system.manager.obf.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ci.smile.system.manager.obf.business.ModuleBusiness;
import ci.smile.system.manager.obf.rest.fact.ControllerFactory;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.ModuleDto;
import ci.smile.system.manager.obf.utils.enums.FunctionalityEnum;

/**
Controller for table "module"
 * 
 * @author SFL Back-End developper
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value="/module")
public class ModuleController {

	
	private Logger log = LoggerFactory.getLogger(getClass());

	
	@Autowired
    private ControllerFactory<ModuleDto> controllerFactory;
	@Autowired
	private ModuleBusiness moduleBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ModuleDto> create(@RequestBody Request<ModuleDto> request) {
    	log.info("start method /module/create");
        Response<ModuleDto> response = controllerFactory.create(moduleBusiness, request, FunctionalityEnum.CREATE_MODULE);
		log.info("end method /module/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ModuleDto> update(@RequestBody Request<ModuleDto> request) {
    	log.info("start method /module/update");
        Response<ModuleDto> response = controllerFactory.update(moduleBusiness, request, FunctionalityEnum.UPDATE_MODULE);
		log.info("end method /module/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ModuleDto> delete(@RequestBody Request<ModuleDto> request) {
    	log.info("start method /module/delete");
        Response<ModuleDto> response = controllerFactory.delete(moduleBusiness
        		, request, FunctionalityEnum.DELETE_MODULE);
		log.info("end method /module/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ModuleDto> getByCriteria(@RequestBody Request<ModuleDto> request) {
    	log.info("start method /module/getByCriteria");
        Response<ModuleDto> response = controllerFactory.getByCriteria(moduleBusiness, request, FunctionalityEnum.VIEW_MODULE);
		log.info("end method /module/getByCriteria");
        return response;
    }
}
