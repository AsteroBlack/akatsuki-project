

/*
 * Java controller for entity table cause_specifique 
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
Controller for table "cause_specifique"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/causeSpecifique")
public class CauseSpecifiqueController {

	@Autowired
    private ControllerFactory<CauseSpecifiqueDto> controllerFactory;
	@Autowired
	private CauseSpecifiqueBusiness causeSpecifiqueBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CauseSpecifiqueDto> create(@RequestBody Request<CauseSpecifiqueDto> request) {
    	log.info("start method /causeSpecifique/create");
        Response<CauseSpecifiqueDto> response = controllerFactory.create(causeSpecifiqueBusiness, request, FunctionalityEnum.CREATE_CAUSE_SPECIFIQUE);
		log.info("end method /causeSpecifique/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CauseSpecifiqueDto> update(@RequestBody Request<CauseSpecifiqueDto> request) {
    	log.info("start method /causeSpecifique/update");
        Response<CauseSpecifiqueDto> response = controllerFactory.update(causeSpecifiqueBusiness, request, FunctionalityEnum.UPDATE_CAUSE_SPECIFIQUE);
		log.info("end method /causeSpecifique/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CauseSpecifiqueDto> delete(@RequestBody Request<CauseSpecifiqueDto> request) {
    	log.info("start method /causeSpecifique/delete");
        Response<CauseSpecifiqueDto> response = controllerFactory.delete(causeSpecifiqueBusiness, request, FunctionalityEnum.DELETE_CAUSE_SPECIFIQUE);
		log.info("end method /causeSpecifique/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CauseSpecifiqueDto> getByCriteria(@RequestBody Request<CauseSpecifiqueDto> request) {
    	log.info("start method /causeSpecifique/getByCriteria");
        Response<CauseSpecifiqueDto> response = controllerFactory.getByCriteria(causeSpecifiqueBusiness, request, FunctionalityEnum.VIEW_CAUSE_SPECIFIQUE);
		log.info("end method /causeSpecifique/getByCriteria");
        return response;
    }
}
