                                                											
/*
 * Java business for entity table offer_olt 
 * Created on 2022-11-07 ( Time 16:50:07 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
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
import ci.smile.system.manager.obf.dao.entity.OfferOlt;
import ci.smile.system.manager.obf.dao.entity.*;
import ci.smile.system.manager.obf.dao.repository.*;

/**
BUSINESS for table "offer_olt"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class OfferOltBusiness implements IBasicBusiness<Request<OfferOltDto>, Response<OfferOltDto>> {

	private Response<OfferOltDto> response;
	@Autowired
	private OfferOltRepository offerOltRepository;
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

	public OfferOltBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create OfferOlt by using OfferOltDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<OfferOltDto> create(Request<OfferOltDto> request, Locale locale)  throws ParseException {
		log.info("----begin create OfferOlt-----");

		Response<OfferOltDto> response = new Response<OfferOltDto>();
		List<OfferOlt>        items    = new ArrayList<OfferOlt>();
			
		for (OfferOltDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
//			fieldsToVerify.put("offer", dto.getOffer());
			fieldsToVerify.put("template", dto.getTemplate());
			fieldsToVerify.put("rx", dto.getRx());
			fieldsToVerify.put("tx", dto.getTx());
//			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
//			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			List<OfferOlt> itemsUnique = offerOltRepository.findByTemplate(dto.getTemplate(), Boolean.FALSE);
			// verif unique login in items to save
			if (itemsUnique.stream().anyMatch(a -> a.getTemplate().equalsIgnoreCase(dto.getTemplate()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(dto.getTemplate(), locale));
				response.setHasError(true);
				return response;
			}
				OfferOlt entityToSave = null;
			entityToSave = OfferOltTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<OfferOlt> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = offerOltRepository.saveAll((Iterable<OfferOlt>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("offerOlt", locale));
				response.setHasError(true);
				return response;
			}
			List<OfferOltDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? OfferOltTransformer.INSTANCE.toLiteDtos(itemsSaved) : OfferOltTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create OfferOlt-----");
		return response;
	}

	/**
	 * update OfferOlt by using OfferOltDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<OfferOltDto> update(Request<OfferOltDto> request, Locale locale)  throws ParseException {
		log.info("----begin update OfferOlt-----");

		Response<OfferOltDto> response = new Response<OfferOltDto>();
		List<OfferOlt>        items    = new ArrayList<OfferOlt>();
			
		for (OfferOltDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			// Verifier si la offerOlt existe
			OfferOlt entityToSave = null;
			entityToSave = offerOltRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("offerOlt id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.notBlank(dto.getTemplate())) {
				entityToSave.setTemplate(dto.getTemplate());
			}
			if (Utilities.notBlank(dto.getRx())) {
				entityToSave.setRx(dto.getRx());
			}
			if (Utilities.notBlank(dto.getTx())) {
				entityToSave.setTx(dto.getTx());
			}
			if (Utilities.notBlank(dto.getDeletedAt())) {
				entityToSave.setDeletedAt(dateFormat.parse(dto.getDeletedAt()));
			}
			if (dto.getDeletedBy() != null && dto.getDeletedBy() > 0) {
				entityToSave.setDeletedBy(dto.getDeletedBy());
			}
			entityToSave.setUpdatedBy(request.getUser());
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<OfferOlt> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = offerOltRepository.saveAll((Iterable<OfferOlt>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("offerOlt", locale));
				response.setHasError(true);
				return response;
			}
			List<OfferOltDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? OfferOltTransformer.INSTANCE.toLiteDtos(itemsSaved) : OfferOltTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update OfferOlt-----");
		return response;
	}

	/**
	 * delete OfferOlt by using OfferOltDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<OfferOltDto> delete(Request<OfferOltDto> request, Locale locale)  {
		log.info("----begin delete OfferOlt-----");

		Response<OfferOltDto> response = new Response<OfferOltDto>();
		List<OfferOlt>        items    = new ArrayList<OfferOlt>();
			
		for (OfferOltDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la offerOlt existe
			OfferOlt existingEntity = null;
			existingEntity = offerOltRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("offerOlt -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------



			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setIsDeleted(true);
			existingEntity.setDeletedBy(request.getUser());
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			offerOltRepository.saveAll((Iterable<OfferOlt>) items);

			response.setHasError(false);
		}

		log.info("----end delete OfferOlt-----");
		return response;
	}
	
	
	

	/**
	 * get OfferOlt by using OfferOltDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<OfferOltDto> getByCriteria(Request<OfferOltDto> request, Locale locale)  throws Exception {
		log.info("----begin get OfferOlt-----");

		Response<OfferOltDto> response = new Response<OfferOltDto>();
		List<OfferOlt> items 			 = offerOltRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<OfferOltDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? OfferOltTransformer.INSTANCE.toLiteDtos(items) : OfferOltTransformer.INSTANCE.toDtos(items);

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
			response.setCount(offerOltRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("offerOlt", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get OfferOlt-----");
		return response;
	}

	/**
	 * get full OfferOltDto by using OfferOlt as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private OfferOltDto getFullInfos(OfferOltDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here

		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}

	public Response<OfferOltDto> custom(Request<OfferOltDto> request, Locale locale) {
		log.info("----begin custom OfferOltDto-----");
		Response<OfferOltDto> response = new Response<OfferOltDto>();
		
		response.setHasError(false);
		response.setCount(1L);
		response.setItems(Arrays.asList(new OfferOltDto()));
		log.info("----end custom OfferOltDto-----");
		return response;
	}
}
