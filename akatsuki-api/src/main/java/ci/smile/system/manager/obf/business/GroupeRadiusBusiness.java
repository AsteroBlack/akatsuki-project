                                                            													
/*
 * Java business for entity table groupe_radius 
 * Created on 2021-03-15 ( Time 12:39:06 )
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
import ci.smile.system.manager.obf.dao.entity.GroupeRadius;
import ci.smile.system.manager.obf.dao.entity.Localisation;
import ci.smile.system.manager.obf.dao.entity.*;
import ci.smile.system.manager.obf.dao.repository.*;

/**
BUSINESS for table "groupe_radius"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class GroupeRadiusBusiness implements IBasicBusiness<Request<GroupeRadiusDto>, Response<GroupeRadiusDto>> {

	private Response<GroupeRadiusDto> response;
	@Autowired
	private GroupeRadiusRepository groupeRadiusRepository;
	@Autowired
	private RadiusRepository radiusRepository;
	@Autowired
	private LocalisationRepository localisationRepository;
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

	public GroupeRadiusBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create GroupeRadius by using GroupeRadiusDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<GroupeRadiusDto> create(Request<GroupeRadiusDto> request, Locale locale)  throws ParseException {
		log.info("----begin create GroupeRadius-----");

		Response<GroupeRadiusDto> response = new Response<GroupeRadiusDto>();
		List<GroupeRadius>        items    = new ArrayList<GroupeRadius>();
			
		for (GroupeRadiusDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("libelle", dto.getLibelle());
			fieldsToVerify.put("code", dto.getCode());
			fieldsToVerify.put("localisationId", dto.getLocalisationId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if groupeRadius to insert do not exist ,	// verif unique code in db
			GroupeRadius existingEntity = null;
			existingEntity = groupeRadiusRepository.findByCode(dto.getCode(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("groupeRadius code -> " + dto.getCode(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique code in items to save
			if (items.stream().anyMatch(a -> a.getCode().equalsIgnoreCase(dto.getCode()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" code ", locale));
				response.setHasError(true);
				return response;
			}

			// verif unique libelle in db
			existingEntity = groupeRadiusRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("groupeRadius libelle -> " + dto.getLibelle(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique libelle in items to save
			if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
				response.setHasError(true);
				return response;
			}

			// Verify if localisation exist
			Localisation existingLocalisation = null;
			if (dto.getLocalisationId() != null && dto.getLocalisationId() > 0){
				existingLocalisation = localisationRepository.findOne(dto.getLocalisationId(), false);
				if (existingLocalisation == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("localisation localisationId -> " + dto.getLocalisationId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			GroupeRadius entityToSave = null;
			entityToSave = GroupeRadiusTransformer.INSTANCE.toEntity(dto, existingLocalisation);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsSuspend(false);
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<GroupeRadius> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = groupeRadiusRepository.saveAll((Iterable<GroupeRadius>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("groupeRadius", locale));
				response.setHasError(true);
				return response;
			}
			List<GroupeRadiusDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? GroupeRadiusTransformer.INSTANCE.toLiteDtos(itemsSaved) : GroupeRadiusTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create GroupeRadius-----");
		return response;
	}

	/**
	 * update GroupeRadius by using GroupeRadiusDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<GroupeRadiusDto> update(Request<GroupeRadiusDto> request, Locale locale)  throws ParseException {
		log.info("----begin update GroupeRadius-----");

		Response<GroupeRadiusDto> response = new Response<GroupeRadiusDto>();
		List<GroupeRadius>        items    = new ArrayList<GroupeRadius>();
			
		for (GroupeRadiusDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la groupeRadius existe
			GroupeRadius entityToSave = null;
			entityToSave = groupeRadiusRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("groupeRadius id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if localisation exist
			if (dto.getLocalisationId() != null && dto.getLocalisationId() > 0){
				Localisation existingLocalisation = localisationRepository.findOne(dto.getLocalisationId(), false);
				if (existingLocalisation == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("localisation localisationId -> " + dto.getLocalisationId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setLocalisation(existingLocalisation);
			}
			if (Utilities.notBlank(dto.getCode())) {
				entityToSave.setCode(dto.getCode());
			}
			if (Utilities.notBlank(dto.getLibelle())) {
				entityToSave.setLibelle(dto.getLibelle());
			}
			if (dto.getIsSuspend() != null) {
				entityToSave.setIsSuspend(dto.getIsSuspend());
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
			List<GroupeRadius> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = groupeRadiusRepository.saveAll((Iterable<GroupeRadius>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("groupeRadius", locale));
				response.setHasError(true);
				return response;
			}
			List<GroupeRadiusDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? GroupeRadiusTransformer.INSTANCE.toLiteDtos(itemsSaved) : GroupeRadiusTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update GroupeRadius-----");
		return response;
	}

	/**
	 * delete GroupeRadius by using GroupeRadiusDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<GroupeRadiusDto> delete(Request<GroupeRadiusDto> request, Locale locale)  {
		log.info("----begin delete GroupeRadius-----");

		Response<GroupeRadiusDto> response = new Response<GroupeRadiusDto>();
		List<GroupeRadius>        items    = new ArrayList<GroupeRadius>();
			
		for (GroupeRadiusDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la groupeRadius existe
			GroupeRadius existingEntity = null;
			existingEntity = groupeRadiusRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("groupeRadius -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// radius
			List<Radius> listOfRadius = radiusRepository.findByGroupeRadiusId(existingEntity.getId(), false);
			if (listOfRadius != null && !listOfRadius.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfRadius.size() + ")", locale));
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
			groupeRadiusRepository.saveAll((Iterable<GroupeRadius>) items);

			response.setHasError(false);
		}

		log.info("----end delete GroupeRadius-----");
		return response;
	}

	/**
	 * get GroupeRadius by using GroupeRadiusDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<GroupeRadiusDto> getByCriteria(Request<GroupeRadiusDto> request, Locale locale)  throws Exception {
		log.info("----begin get GroupeRadius-----");

		Response<GroupeRadiusDto> response = new Response<GroupeRadiusDto>();
		List<GroupeRadius> items 			 = groupeRadiusRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<GroupeRadiusDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? GroupeRadiusTransformer.INSTANCE.toLiteDtos(items) : GroupeRadiusTransformer.INSTANCE.toDtos(items);

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
			response.setCount(groupeRadiusRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("groupeRadius", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get GroupeRadius-----");
		return response;
	}

	/**
	 * get full GroupeRadiusDto by using GroupeRadius as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private GroupeRadiusDto getFullInfos(GroupeRadiusDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
