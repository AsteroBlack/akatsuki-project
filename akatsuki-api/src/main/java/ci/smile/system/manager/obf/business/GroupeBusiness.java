                                                        												
/*
 * Java business for entity table groupe 
 * Created on 2023-01-18 ( Time 11:59:00 )
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
import ci.smile.system.manager.obf.dao.entity.Groupe;
import ci.smile.system.manager.obf.dao.entity.Plateforme;
import ci.smile.system.manager.obf.dao.entity.Offer;
import ci.smile.system.manager.obf.dao.entity.*;
import ci.smile.system.manager.obf.dao.repository.*;

/**
BUSINESS for table "groupe"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class GroupeBusiness implements IBasicBusiness<Request<GroupeDto>, Response<GroupeDto>> {

	private Response<GroupeDto> response;
	@Autowired
	private GroupeRepository groupeRepository;
	@Autowired
	private OfferGroupeRepository offerGroupeRepository;
	@Autowired
	private PlateformeRepository plateformeRepository;
	@Autowired
	private OfferRepository offerRepository;
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

	public GroupeBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Groupe by using GroupeDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<GroupeDto> create(Request<GroupeDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Groupe-----");

		Response<GroupeDto> response = new Response<GroupeDto>();
		List<Groupe>        items    = new ArrayList<Groupe>();
			
		for (GroupeDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("libelle", dto.getLibelle());
			fieldsToVerify.put("idPlateforme", dto.getIdPlateforme());
			fieldsToVerify.put("idOffre", dto.getIdOffre());
//			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
//			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

//			// Verify if groupe to insert do not exist
//			Groupe existingEntity = groupeRepository.findByLibelle(dto.getLibelle(), false);
//			if (existingEntity != null) {
//				response.setStatus(functionalError.DATA_EXIST("groupe libelle -> " + dto.getLibelle(), locale));
//				response.setHasError(true);
//				return response;
//			}
//			// verif unique libelle in items to save
//			if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
//				response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
//				response.setHasError(true);
//				return response;
//			}

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
				Groupe entityToSave = null;
			entityToSave = GroupeTransformer.INSTANCE.toEntity(dto, existingPlateforme, existingOffer);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
//			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Groupe> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = groupeRepository.saveAll((Iterable<Groupe>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("groupe", locale));
				response.setHasError(true);
				return response;
			}
			List<GroupeDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? GroupeTransformer.INSTANCE.toLiteDtos(itemsSaved) : GroupeTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Groupe-----");
		return response;
	}

	/**
	 * update Groupe by using GroupeDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<GroupeDto> update(Request<GroupeDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Groupe-----");

		Response<GroupeDto> response = new Response<GroupeDto>();
		List<Groupe>        items    = new ArrayList<Groupe>();
			
		for (GroupeDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la groupe existe
			Groupe entityToSave = null;
			entityToSave = groupeRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("groupe id -> " + dto.getId(), locale));
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
			if (Utilities.notBlank(dto.getLibelle())) {
				entityToSave.setLibelle(dto.getLibelle());
			}
			if (Utilities.notBlank(dto.getModeleBoxe())) {
				entityToSave.setModeleBoxe(dto.getModeleBoxe());
			}
			if (Utilities.notBlank(dto.getDeletedAt())) {
				entityToSave.setDeletedAt(dateFormat.parse(dto.getDeletedAt()));
			}
			if (dto.getDeletedBy() != null && dto.getDeletedBy() > 0) {
				entityToSave.setDeletedBy(dto.getDeletedBy());
			}
			if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) {
				entityToSave.setUpdatedBy(dto.getUpdatedBy());
			}
			if (dto.getCreatedBy() != null && dto.getCreatedBy() > 0) {
				entityToSave.setCreatedBy(dto.getCreatedBy());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Groupe> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = groupeRepository.saveAll((Iterable<Groupe>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("groupe", locale));
				response.setHasError(true);
				return response;
			}
			List<GroupeDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? GroupeTransformer.INSTANCE.toLiteDtos(itemsSaved) : GroupeTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Groupe-----");
		return response;
	}

	/**
	 * delete Groupe by using GroupeDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<GroupeDto> delete(Request<GroupeDto> request, Locale locale)  {
		log.info("----begin delete Groupe-----");

		Response<GroupeDto> response = new Response<GroupeDto>();
		List<Groupe>        items    = new ArrayList<Groupe>();
			
		for (GroupeDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la groupe existe
			Groupe existingEntity = null;
			existingEntity = groupeRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("groupe -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// offerGroupe
			List<OfferGroupe> listOfOfferGroupe = offerGroupeRepository.findByIdGroupe(existingEntity.getId(), false);
			if (listOfOfferGroupe != null && !listOfOfferGroupe.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfOfferGroupe.size() + ")", locale));
				response.setHasError(true);
				return response;
			}


			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setDeletedBy(request.getUser());
			existingEntity.setIsDeleted(true);
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			groupeRepository.saveAll((Iterable<Groupe>) items);

			response.setHasError(false);
		}

		log.info("----end delete Groupe-----");
		return response;
	}

	/**
	 * get Groupe by using GroupeDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<GroupeDto> getByCriteria(Request<GroupeDto> request, Locale locale)  throws Exception {
		log.info("----begin get Groupe-----");

		Response<GroupeDto> response = new Response<GroupeDto>();
		List<Groupe> items 			 = groupeRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<GroupeDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? GroupeTransformer.INSTANCE.toLiteDtos(items) : GroupeTransformer.INSTANCE.toDtos(items);

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
			response.setCount(groupeRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("groupe", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Groupe-----");
		return response;
	}

	/**
	 * get full GroupeDto by using Groupe as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private GroupeDto getFullInfos(GroupeDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here

		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}

	public Response<GroupeDto> custom(Request<GroupeDto> request, Locale locale) {
		log.info("----begin custom GroupeDto-----");
		Response<GroupeDto> response = new Response<GroupeDto>();
		
		response.setHasError(false);
		response.setCount(1L);
		response.setItems(Arrays.asList(new GroupeDto()));
		log.info("----end custom GroupeDto-----");
		return response;
	}
}
