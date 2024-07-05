


/*
 * Java controller for entity table role_menus_ACTION 
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

import ci.smile.system.manager.obf.business.RoleActionBusiness;
import ci.smile.system.manager.obf.rest.fact.ControllerFactory;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.RoleActionDto;
import ci.smile.system.manager.obf.utils.enums.FunctionalityEnum;

/**
Controller for table "role_menus_habilitation"
 * 
 * @author SFL Back-End developper
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value="/roleAction")
public class RoleActionController {

	
	
	private Logger log = LoggerFactory.getLogger(getClass());

	
	@Autowired
    private ControllerFactory<RoleActionDto> controllerFactory;
	@Autowired
	private RoleActionBusiness roleActionBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleActionDto> create(@RequestBody Request<RoleActionDto> request) {
    	log.info("start method /RoleAction/create");
        Response<RoleActionDto> response = controllerFactory.create(roleActionBusiness, request, FunctionalityEnum.CREATE_ROLE_ACTION);
		log.info("end method /RoleAction/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleActionDto> update(@RequestBody Request<RoleActionDto> request) {
    	log.info("start method /RoleAction/update");
        Response<RoleActionDto> response = controllerFactory.update(roleActionBusiness, request, FunctionalityEnum.UPDATE_ROLE_ACTION);
		log.info("end method /RoleAction/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleActionDto> delete(@RequestBody Request<RoleActionDto> request) {
    	log.info("start method /RoleAction/delete");
        Response<RoleActionDto> response = controllerFactory.delete(roleActionBusiness, request, FunctionalityEnum.DELETE_ROLE_ACTION);
		log.info("end method /roleMenusACTION/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleActionDto> getByCriteria(@RequestBody Request<RoleActionDto> request) {
    	log.info("start method /RoleAction/getByCriteria");
        Response<RoleActionDto> response = controllerFactory.getByCriteria(roleActionBusiness, request, FunctionalityEnum.VIEW_ROLE_ACTION);
		log.info("end method /RoleAction/getByCriteria");
        return response;
    }
}
