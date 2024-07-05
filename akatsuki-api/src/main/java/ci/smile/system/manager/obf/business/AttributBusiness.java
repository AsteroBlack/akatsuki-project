
/*
 * Java business for entity table attribut 
 * Created on 2021-04-06 ( Time 17:52:21 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.business;

import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import ci.smile.system.manager.obf.utils.*;
import ci.smile.system.manager.obf.utils.dto.*;
import ci.smile.system.manager.obf.utils.enums.*;
import ci.smile.system.manager.obf.utils.contract.*;
import ci.smile.system.manager.obf.utils.contract.IBasicBusiness;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.transformer.*;
import ci.smile.system.manager.obf.dao.entity.Attribut;
import ci.smile.system.manager.obf.dao.entity.*;
import ci.smile.system.manager.obf.dao.repository.*;

/**
BUSINESS for table "attribut"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class AttributBusiness implements IBasicBusiness<Request<AttributDto>, Response<AttributDto>> {

	private Response<AttributDto> response;
	@Autowired
	private AttributRepository attributRepository;
	@Autowired
	private FunctionalError functionalError;
	@Autowired
	private TechnicalError technicalError;
	@Autowired
	private ExceptionUtils exceptionUtils;
	@PersistenceContext
	private EntityManager em;

	private SimpleDateFormat dateFormat;
	private SimpleDateFormat dateTimeFormat;

	public AttributBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create Attribut by using AttributDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AttributDto> create(Request<AttributDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Attribut-----");

		Response<AttributDto> response = new Response<AttributDto>();
		List<Attribut>        items    = new ArrayList<Attribut>();

		for (AttributDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("attribut", dto.getAttribut());
			fieldsToVerify.put("value", dto.getValue());
			fieldsToVerify.put("type", dto.getType());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			// Verify if attribut to insert do not exist
			Attribut existingEntity = null;
			existingEntity = attributRepository.findByAttributAndType(dto.getAttribut(), dto.getType(),false);
			if(existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST(" "+dto.getAttribut(), locale));
				response.setHasError(true);
				return response;
			}
			Attribut entityToSave = null;
			entityToSave = AttributTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setOp(":=");
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Attribut> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = attributRepository.saveAll((Iterable<Attribut>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("attribut", locale));
				response.setHasError(true);
				return response;
			}
			List<AttributDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AttributTransformer.INSTANCE.toLiteDtos(itemsSaved) : AttributTransformer.INSTANCE.toDtos(itemsSaved);

			final int size = itemsSaved.size();
			List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
			itemsDto.parallelStream().forEach(dto -> {
				try {
					dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
				} catch (Exception e) {
					listOfError.add(e.getMessage());
					e.printStackTrace();
				}
			});
			if (Utilities.isNotEmpty(listOfError)) {
				Object[] objArray = listOfError.stream().distinct().toArray();
				throw new RuntimeException(StringUtils.join(objArray, ", "));
			}
			response.setItems(itemsDto);
			response.setHasError(false);
		}

		log.info("----end create Attribut-----");
		return response;
	}

	/**
	 * update Attribut by using AttributDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AttributDto> update(Request<AttributDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Attribut-----");

		Response<AttributDto> response = new Response<AttributDto>();
		List<Attribut>        items    = new ArrayList<Attribut>();

		for (AttributDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la attribut existe
			Attribut entityToSave = null;
			entityToSave = attributRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("attribut id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}
			Attribut existingEntity = null;
			if (Utilities.notBlank(dto.getAttribut())) {
				existingEntity = attributRepository.findByAttributAndType(dto.getAttribut(), entityToSave.getType(),false);
				if(existingEntity != null && !existingEntity.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError.DATA_EXIST(" l'attribut "+dto.getAttribut() +" existe avec le type "+entityToSave.getType(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setAttribut(dto.getAttribut());
			}
			if (Utilities.notBlank(dto.getOp())) {
				entityToSave.setOp(dto.getOp());
			}
			if (dto.getValue() !=null) {
				entityToSave.setValue(dto.getValue());
			}
//			if (Utilities.notBlank(dto.getType())) {
//				existingEntity = attributRepository.findByAttributAndType(entityToSave.getAttribut(), dto.getType(),false);
//				if(existingEntity != null && !existingEntity.getId().equals(entityToSave.getId())) {
//					response.setStatus(functionalError.DATA_EXIST(" le type " +dto.getType()+" existe avec l'attribut"+dto.getAttribut(), locale));
//					response.setHasError(true);
//					return response;
//				}
//				entityToSave.setType(dto.getType());
//			}
			if (Utilities.notBlank(dto.getDeletedAt())) {
				entityToSave.setDeletedAt(dateFormat.parse(dto.getDeletedAt()));
			}
			if (dto.getCreatedBy() != null && dto.getCreatedBy() > 0) {
				entityToSave.setCreatedBy(dto.getCreatedBy());
			}
			if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) {
				entityToSave.setUpdatedBy(dto.getUpdatedBy());
			}
			if (dto.getDeletedBy() != null && dto.getDeletedBy() > 0) {
				entityToSave.setDeletedBy(dto.getDeletedBy());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Attribut> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = attributRepository.saveAll((Iterable<Attribut>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("attribut", locale));
				response.setHasError(true);
				return response;
			}
			List<AttributDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AttributTransformer.INSTANCE.toLiteDtos(itemsSaved) : AttributTransformer.INSTANCE.toDtos(itemsSaved);

			final int size = itemsSaved.size();
			List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
			itemsDto.parallelStream().forEach(dto -> {
				try {
					dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
				} catch (Exception e) {
					listOfError.add(e.getMessage());
					e.printStackTrace();
				}
			});
			if (Utilities.isNotEmpty(listOfError)) {
				Object[] objArray = listOfError.stream().distinct().toArray();
				throw new RuntimeException(StringUtils.join(objArray, ", "));
			}
			response.setItems(itemsDto);
			response.setHasError(false);
		}

		log.info("----end update Attribut-----");
		return response;
	}

	/**
	 * delete Attribut by using AttributDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AttributDto> delete(Request<AttributDto> request, Locale locale)  {
		log.info("----begin delete Attribut-----");

		Response<AttributDto> response = new Response<AttributDto>();
		List<Attribut>        items    = new ArrayList<Attribut>();

		for (AttributDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la attribut existe
			Attribut existingEntity = null;
			existingEntity = attributRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("attribut -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------



			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setDeletedBy(request.getUser());
			existingEntity.setIsDeleted(true);
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			attributRepository.saveAll((Iterable<Attribut>) items);

			response.setHasError(false);
		}

		log.info("----end delete Attribut-----");
		return response;
	}

	/**
	 * get Attribut by using AttributDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AttributDto> getByCriteria(Request<AttributDto> request, Locale locale)  throws Exception {
		log.info("----begin get Attribut-----");

		Response<AttributDto> response = new Response<AttributDto>();
		List<Attribut> items 			 = attributRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<AttributDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AttributTransformer.INSTANCE.toLiteDtos(items) : AttributTransformer.INSTANCE.toDtos(items);

			final int size = items.size();
			List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
			itemsDto.parallelStream().forEach(dto -> {
				try {
					dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
				} catch (Exception e) {
					listOfError.add(e.getMessage());
					e.printStackTrace();
				}
			});
			if (Utilities.isNotEmpty(listOfError)) {
				Object[] objArray = listOfError.stream().distinct().toArray();
				throw new RuntimeException(StringUtils.join(objArray, ", "));
			}
			response.setItems(itemsDto);
			response.setCount(attributRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("attribut", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Attribut-----");
		return response;
	}
	
	
	public Response<AttributDto> getFormulaire(Request<AttributDto> request, Locale locale) {
		Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
		Response<AttributDto> response = new Response<AttributDto>();
		AttributDto dto = request.getData();
		fieldsToVerify.put("code", dto.getCode());
		if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
			response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
			response.setHasError(true);
			return response;
		}
		if (dto.getCode().equalsIgnoreCase("prepaid")) {
			response = getRadiusAttributExpiration(request, locale);
		} else {
			if (dto.getCode().equalsIgnoreCase("postpaid")) {
				response = getRadiusAttribut(request, locale);
			}
		}
		return response;
	}
	
	public Response<AttributDto> getRadiusAttribut(Request<AttributDto> request, Locale locale) {
		log.info("----begin getRadiusAttribut-----");
		Response<AttributDto> response = new Response<AttributDto>();
		List<AttributDto> items = new ArrayList<AttributDto>();
		refactor(items);
		response.setItems(items);
		log.info("----end getRadiusAttribut-----");
		return response;
	}

	public Response<AttributDto> getRadiusAttributExpiration(Request<AttributDto> request, Locale locale) {
		log.info("----begin getRadiusAttribut-----");
		Response<AttributDto> response = new Response<AttributDto>();
		List<AttributDto> items = new ArrayList<AttributDto>();
		refactorExpiration(items);
		response.setItems(items);
		log.info("----end getRadiusAttribut-----");
		return response;
	}

	private void refactor(List<AttributDto> items) {
		AttributDto remoteId = new AttributDto();
		remoteId.setAttribut("ADSL-Agent-Remote-Id");
		remoteId.setType("check");
		remoteId.setValue("22657687");
		remoteId.setCode("postpaid");
		items.add(remoteId);

		AttributDto circuitId = new AttributDto();
		circuitId.setAttribut("ADSL-Agent-Circuit-Id");
		circuitId.setType("check");
		// OREP401 eth 1/1/01/01/7/000000000000ALCML332107C
		circuitId.setValue("");
		circuitId.setCode("postpaid");
		items.add(circuitId);

		AttributDto defautRouter = new AttributDto();
		defautRouter.setAttribut("Alc-Default-Router");
		defautRouter.setType("reply");
		defautRouter.setValue("192.168.1.8");
		defautRouter.setCode("postpaid");
		items.add(defautRouter);

		AttributDto framedIp = new AttributDto();
		framedIp.setAttribut("Framed-IP-Address");
		framedIp.setType("reply");
		framedIp.setValue("192.168.1.8");
		framedIp.setCode("postpaid");
		items.add(framedIp);

		AttributDto framedIpNetmask = new AttributDto();
		framedIpNetmask.setAttribut("Framed-IP-Netmask");
		framedIpNetmask.setType("reply");
		framedIpNetmask.setValue("255.255.255.0");
		framedIpNetmask.setCode("postpaid");
		items.add(framedIpNetmask);
	}

	private void refactorExpiration(List<AttributDto> items) {
		AttributDto remoteId = new AttributDto();
		remoteId.setAttribut("ADSL-Agent-Remote-Id");
		remoteId.setType("check");
		remoteId.setValue("22657687");
		remoteId.setCode("prepaid");
		items.add(remoteId);

		AttributDto circuitId = new AttributDto();
		circuitId.setAttribut("ADSL-Agent-Circuit-Id");
		circuitId.setType("check");
		circuitId.setValue("OREP401 eth 1/1/01/01/7/000000000000ALCML332107C");
		circuitId.setCode("prepaid");
		items.add(circuitId);

		AttributDto defautRouter = new AttributDto();
		defautRouter.setAttribut("Alc-Default-Router");
		defautRouter.setType("reply");
		defautRouter.setValue("192.168.1.8");
		defautRouter.setCode("prepaid");
		items.add(defautRouter);

		AttributDto framedIp = new AttributDto();
		framedIp.setAttribut("Framed-IP-Address");
		framedIp.setType("reply");
		framedIp.setValue("192.168.1.8");
		framedIp.setCode("prepaid");
		items.add(framedIp);

		AttributDto framedIpNetmask = new AttributDto();
		framedIpNetmask.setAttribut("Framed-IP-Netmask");
		framedIpNetmask.setType("reply");
		framedIpNetmask.setValue("255.255.255.0");
		framedIpNetmask.setCode("prepaid");

		AttributDto expiration = new AttributDto();
		expiration.setAttribut("Expiration");
		expiration.setType("check");
		// date ulterieure one day
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, +1);
		// expiration.setValue("06/09/2021");
		expiration.setValue(dateFormat.format(calendar.getTime()));
		expiration.setCode("prepaid");
		items.add(expiration);
	}


	
	

	/**
	 * get full AttributDto by using Attribut as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private AttributDto getFullInfos(AttributDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here

		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}
}
