

/*
 * Java controller for entity table type_offre 
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

import ci.smile.system.manager.obf.business.TypeOffreBusiness;
import ci.smile.system.manager.obf.rest.fact.ControllerFactory;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.TypeOffreDto;
import ci.smile.system.manager.obf.utils.enums.FunctionalityEnum;
import lombok.extern.java.Log;

/**
Controller for table "type_offre"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/typeOffre")
public class TypeOffreController {

	@Autowired
    private ControllerFactory<TypeOffreDto> controllerFactory;
	@Autowired
	private TypeOffreBusiness typeOffreBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TypeOffreDto> create(@RequestBody Request<TypeOffreDto> request) {
    	log.info("start method /typeOffre/create");
        Response<TypeOffreDto> response = controllerFactory.create(typeOffreBusiness, request, FunctionalityEnum.CREATE_TYPE_OFFRE);
		log.info("end method /typeOffre/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TypeOffreDto> update(@RequestBody Request<TypeOffreDto> request) {
    	log.info("start method /typeOffre/update");
        Response<TypeOffreDto> response = controllerFactory.update(typeOffreBusiness, request, FunctionalityEnum.UPDATE_TYPE_OFFRE);
		log.info("end method /typeOffre/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TypeOffreDto> delete(@RequestBody Request<TypeOffreDto> request) {
    	log.info("start method /typeOffre/delete");
        Response<TypeOffreDto> response = controllerFactory.delete(typeOffreBusiness, request, FunctionalityEnum.DELETE_TYPE_OFFRE);
		log.info("end method /typeOffre/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TypeOffreDto> getByCriteria(@RequestBody Request<TypeOffreDto> request) {
    	log.info("start method /typeOffre/getByCriteria");
        Response<TypeOffreDto> response = controllerFactory.getByCriteria(typeOffreBusiness, request, FunctionalityEnum.VIEW_TYPE_OFFRE);
		log.info("end method /typeOffre/getByCriteria");
        return response;
    }
}
