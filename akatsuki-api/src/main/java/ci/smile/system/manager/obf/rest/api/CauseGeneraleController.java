

/*
 * Java controller for entity table cause_generale 
 * Created on 2023-01-26 ( Time 00:38:57 )
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
Controller for table "cause_generale"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/causeGenerale")
public class CauseGeneraleController {

	@Autowired
    private ControllerFactory<CauseGeneraleDto> controllerFactory;
	@Autowired
	private CauseGeneraleBusiness causeGeneraleBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CauseGeneraleDto> create(@RequestBody Request<CauseGeneraleDto> request) {
    	log.info("start method /causeGenerale/create");
        Response<CauseGeneraleDto> response = controllerFactory.create(causeGeneraleBusiness, request, FunctionalityEnum.CREATE_CAUSE_GENERALE);
		log.info("end method /causeGenerale/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CauseGeneraleDto> update(@RequestBody Request<CauseGeneraleDto> request) {
    	log.info("start method /causeGenerale/update");
        Response<CauseGeneraleDto> response = controllerFactory.update(causeGeneraleBusiness, request, FunctionalityEnum.UPDATE_CAUSE_GENERALE);
		log.info("end method /causeGenerale/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CauseGeneraleDto> delete(@RequestBody Request<CauseGeneraleDto> request) {
    	log.info("start method /causeGenerale/delete");
        Response<CauseGeneraleDto> response = controllerFactory.delete(causeGeneraleBusiness, request, FunctionalityEnum.DELETE_CAUSE_GENERALE);
		log.info("end method /causeGenerale/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CauseGeneraleDto> getByCriteria(@RequestBody Request<CauseGeneraleDto> request) {
    	log.info("start method /causeGenerale/getByCriteria");
        Response<CauseGeneraleDto> response = controllerFactory.getByCriteria(causeGeneraleBusiness, request, FunctionalityEnum.VIEW_CAUSE_GENERALE);
		log.info("end method /causeGenerale/getByCriteria");
        return response;
    }
}
