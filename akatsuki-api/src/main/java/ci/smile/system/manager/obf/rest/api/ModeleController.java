

/*
 * Java controller for entity table modele 
 * Created on 2021-03-09 ( Time 11:10:42 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package ci.smile.system.manager.obf.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ci.smile.system.manager.obf.business.ModeleBusiness;
import ci.smile.system.manager.obf.rest.fact.ControllerFactory;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.ModeleDto;
import ci.smile.system.manager.obf.utils.enums.FunctionalityEnum;
import lombok.extern.java.Log;

/**
Controller for table "modele"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/modele")
public class ModeleController {

	@Autowired
    private ControllerFactory<ModeleDto> controllerFactory;
	@Autowired
	private ModeleBusiness modeleBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ModeleDto> create(@RequestBody Request<ModeleDto> request) {
    	log.info("start method /modele/create");
        Response<ModeleDto> response = controllerFactory.create(modeleBusiness, request, FunctionalityEnum.CREATE_MODELE);
		log.info("end method /modele/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ModeleDto> update(@RequestBody Request<ModeleDto> request) {
    	log.info("start method /modele/update");
        Response<ModeleDto> response = controllerFactory.update(modeleBusiness, request, FunctionalityEnum.UPDATE_MODELE);
		log.info("end method /modele/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ModeleDto> delete(@RequestBody Request<ModeleDto> request) {
    	log.info("start method /modele/delete");
        Response<ModeleDto> response = controllerFactory.delete(modeleBusiness, request, FunctionalityEnum.DELETE_MODELE);
		log.info("end method /modele/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ModeleDto> getByCriteria(@RequestBody Request<ModeleDto> request) {
    	log.info("start method /modele/getByCriteria");
        Response<ModeleDto> response = controllerFactory.getByCriteria(modeleBusiness, request, FunctionalityEnum.VIEW_MODELE);
		log.info("end method /modele/getByCriteria");
        return response;
    }
}
