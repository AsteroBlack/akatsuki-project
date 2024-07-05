


/*
 * Java controller for entity table role_menus_habilitation 
 * Created on 2019-12-20 ( Time 00:51:50 )
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

import ci.smile.system.manager.obf.business.RoleUserBusiness;
import ci.smile.system.manager.obf.rest.fact.ControllerFactory;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.RoleUserDto;
import ci.smile.system.manager.obf.utils.enums.FunctionalityEnum;

/**
Controller for table "role_menus_habilitation"
 * 
 * @author SFL Back-End developper
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value="/roleUser")
public class RoleUserController {

	@Autowired
    private ControllerFactory<RoleUserDto> controllerFactory;
	@Autowired
	private RoleUserBusiness RoleUserBusiness;

	
	private Logger log = LoggerFactory.getLogger(getClass());

	
	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleUserDto> create(@RequestBody Request<RoleUserDto> request) {
    	log.info("start method /RoleUser/create");
        Response<RoleUserDto> response = controllerFactory.create(RoleUserBusiness, request, FunctionalityEnum.CREATE_ROLE_USER);
		log.info("end method /RoleUser/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleUserDto> update(@RequestBody Request<RoleUserDto> request) {
    	log.info("start method /RoleUser/update");
        Response<RoleUserDto> response = controllerFactory.update(RoleUserBusiness, request, FunctionalityEnum.UPDATE_ROLE_USER);
		log.info("end method /RoleUser/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleUserDto> delete(@RequestBody Request<RoleUserDto> request) {
    	log.info("start method /RoleUser/delete");
        Response<RoleUserDto> response = controllerFactory.delete(RoleUserBusiness, request, FunctionalityEnum.DELETE_ROLE_USER);
		log.info("end method /RoleUser/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleUserDto> getByCriteria(@RequestBody Request<RoleUserDto> request) {
    	log.info("start method /RoleUser/getByCriteria");
        Response<RoleUserDto> response = controllerFactory.getByCriteria(RoleUserBusiness, request, FunctionalityEnum.VIEW_ROLE_USER);
		log.info("end method /RoleUser/getByCriteria");
        return response;
    }
}
