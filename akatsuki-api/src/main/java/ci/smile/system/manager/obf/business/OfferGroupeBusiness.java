                                                        												
/*
 * Java business for entity table offer_groupe 
 * Created on 2023-01-16 ( Time 14:17:29 )
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
import ci.smile.system.manager.obf.dao.entity.OfferGroupe;
import ci.smile.system.manager.obf.dao.entity.Plateforme;
import ci.smile.system.manager.obf.dao.entity.Offer;
import ci.smile.system.manager.obf.dao.entity.Groupe;
import ci.smile.system.manager.obf.dao.entity.*;
import ci.smile.system.manager.obf.dao.repository.*;

/**
BUSINESS for table "offer_groupe"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class OfferGroupeBusiness implements IBasicBusiness<Request<OfferGroupeDto>, Response<OfferGroupeDto>> {

	private Response<OfferGroupeDto> response;
	@Autowired
	private OfferGroupeRepository offerGroupeRepository;
	@Autowired
	private PlateformeRepository plateformeRepository;
	@Autowired
	private OfferRepository offerRepository;
	@Autowired
	private GroupeRepository groupeRepository;
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

	public OfferGroupeBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create OfferGroupe by using OfferGroupeDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<OfferGroupeDto> create(Request<OfferGroupeDto> request, Locale locale)  throws ParseException {
		log.info("----begin create OfferGroupe-----");

		Response<OfferGroupeDto> response = new Response<OfferGroupeDto>();
		List<OfferGroupe>        items    = new ArrayList<OfferGroupe>();
			
		for (OfferGroupeDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("idOffre", dto.getIdOffre());
			fieldsToVerify.put("idGroupe", dto.getIdGroupe());
//			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
//			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			fieldsToVerify.put("idPlateforme", dto.getIdPlateforme());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if plateforme exist
			Plateforme existingPlateforme = null;
			if (dto.getIdPlateforme() != null && dto.getIdPlateforme() > 0){
				existingPlateforme = plateformeRepository.findOne(dto.getIdPlateforme(), false);
				if (existingPlateforme == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("plateforme idPlateforme -> " + dto.getIdPlateforme(), locale));
					response.setHasError(true);
					return response;
				}
			}
			// Verify if offer exist
			Offer existingOffer = null;
			if (dto.getIdOffre() != null && dto.getIdOffre() > 0){
				existingOffer = offerRepository.findOne(dto.getIdOffre(), false);
				if (existingOffer == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("offer idOffre -> " + dto.getIdOffre(), locale));
					response.setHasError(true);
					return response;
				}
			}
			// Verify if groupe exist
			Groupe existingGroupe = null;
			if (dto.getIdGroupe() != null && dto.getIdGroupe() > 0){
				existingGroupe = groupeRepository.findOne(dto.getIdGroupe(), false);
				if (existingGroupe == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("groupe idGroupe -> " + dto.getIdGroupe(), locale));
					response.setHasError(true);
					return response;
				}
			}
				OfferGroupe entityToSave = null;
			entityToSave = OfferGroupeTransformer.INSTANCE.toEntity(dto, existingPlateforme, existingOffer, existingGroupe);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
//			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<OfferGroupe> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = offerGroupeRepository.saveAll((Iterable<OfferGroupe>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("offerGroupe", locale));
				response.setHasError(true);
				return response;
			}
			List<OfferGroupeDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? OfferGroupeTransformer.INSTANCE.toLiteDtos(itemsSaved) : OfferGroupeTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create OfferGroupe-----");
		return response;
	}

	/**
	 * update OfferGroupe by using OfferGroupeDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<OfferGroupeDto> update(Request<OfferGroupeDto> request, Locale locale)  throws ParseException {
		log.info("----begin update OfferGroupe-----");

		Response<OfferGroupeDto> response = new Response<OfferGroupeDto>();
		List<OfferGroupe>        items    = new ArrayList<OfferGroupe>();
			
		for (OfferGroupeDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la offerGroupe existe
			OfferGroupe entityToSave = null;
			entityToSave = offerGroupeRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("offerGroupe id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if plateforme exist
			if (dto.getIdPlateforme() != null && dto.getIdPlateforme() > 0){
				Plateforme existingPlateforme = plateformeRepository.findOne(dto.getIdPlateforme(), false);
				if (existingPlateforme == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("plateforme idPlateforme -> " + dto.getIdPlateforme(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setPlateforme(existingPlateforme);
			}
			// Verify if offer exist
			if (dto.getIdOffre() != null && dto.getIdOffre() > 0){
				Offer existingOffer = offerRepository.findOne(dto.getIdOffre(), false);
				if (existingOffer == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("offer idOffre -> " + dto.getIdOffre(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setOffer(existingOffer);
			}
			// Verify if groupe exist
			if (dto.getIdGroupe() != null && dto.getIdGroupe() > 0){
				Groupe existingGroupe = groupeRepository.findOne(dto.getIdGroupe(), false);
				if (existingGroupe == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("groupe idGroupe -> " + dto.getIdGroupe(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setGroupe(existingGroupe);
			}
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
			List<OfferGroupe> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = offerGroupeRepository.saveAll((Iterable<OfferGroupe>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("offerGroupe", locale));
				response.setHasError(true);
				return response;
			}
			List<OfferGroupeDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? OfferGroupeTransformer.INSTANCE.toLiteDtos(itemsSaved) : OfferGroupeTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update OfferGroupe-----");
		return response;
	}

	/**
	 * delete OfferGroupe by using OfferGroupeDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<OfferGroupeDto> delete(Request<OfferGroupeDto> request, Locale locale)  {
		log.info("----begin delete OfferGroupe-----");

		Response<OfferGroupeDto> response = new Response<OfferGroupeDto>();
		List<OfferGroupe>        items    = new ArrayList<OfferGroupe>();
			
		for (OfferGroupeDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la offerGroupe existe
			OfferGroupe existingEntity = null;
			existingEntity = offerGroupeRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("offerGroupe -> " + dto.getId(), locale));
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
			offerGroupeRepository.saveAll((Iterable<OfferGroupe>) items);

			response.setHasError(false);
		}

		log.info("----end delete OfferGroupe-----");
		return response;
	}

	/**
	 * get OfferGroupe by using OfferGroupeDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<OfferGroupeDto> getByCriteria(Request<OfferGroupeDto> request, Locale locale)  throws Exception {
		log.info("----begin get OfferGroupe-----");

		Response<OfferGroupeDto> response = new Response<OfferGroupeDto>();
		List<OfferGroupe> items 			 = offerGroupeRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<OfferGroupeDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? OfferGroupeTransformer.INSTANCE.toLiteDtos(items) : OfferGroupeTransformer.INSTANCE.toDtos(items);

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
			response.setCount(offerGroupeRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("offerGroupe", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get OfferGroupe-----");
		return response;
	}

	/**
	 * get full OfferGroupeDto by using OfferGroupe as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private OfferGroupeDto getFullInfos(OfferGroupeDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here

		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}

	public Response<OfferGroupeDto> custom(Request<OfferGroupeDto> request, Locale locale) {
		log.info("----begin custom OfferGroupeDto-----");
		Response<OfferGroupeDto> response = new Response<OfferGroupeDto>();
		
		response.setHasError(false);
		response.setCount(1L);
		response.setItems(Arrays.asList(new OfferGroupeDto()));
		log.info("----end custom OfferGroupeDto-----");
		return response;
	}
}
