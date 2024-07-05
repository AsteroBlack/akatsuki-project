                                                    											
/*
 * Java business for entity table localisation 
 * Created on 2021-03-15 ( Time 12:39:07 )
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
import ci.smile.system.manager.obf.dao.entity.*;
import ci.smile.system.manager.obf.dao.repository.*;

/**
BUSINESS for table "localisation"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class LocalisationBusiness implements IBasicBusiness<Request<LocalisationDto>, Response<LocalisationDto>> {

	private Response<LocalisationDto> response;
	@Autowired
	private LocalisationRepository localisationRepository;
	@Autowired
	private GroupeRadiusRepository groupeRadiusRepository;
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

	public LocalisationBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Localisation by using LocalisationDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<LocalisationDto> create(Request<LocalisationDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Localisation-----");

		Response<LocalisationDto> response = new Response<LocalisationDto>();
		List<Localisation>        items    = new ArrayList<Localisation>();
			
		for (LocalisationDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("code", dto.getCode());
			fieldsToVerify.put("libelle", dto.getLibelle());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if localisation to insert do not exist, 	// verif unique code in db
			Localisation existingEntity = null;
			existingEntity = localisationRepository.findByCode(dto.getCode(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("localisation code -> " + dto.getCode(), locale));
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
			existingEntity = localisationRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("localisation libelle -> " + dto.getLibelle(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique libelle in items to save
			if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
				response.setHasError(true);
				return response;
			}

				Localisation entityToSave = null;
			entityToSave = LocalisationTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Localisation> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = localisationRepository.saveAll((Iterable<Localisation>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("localisation", locale));
				response.setHasError(true);
				return response;
			}
			List<LocalisationDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? LocalisationTransformer.INSTANCE.toLiteDtos(itemsSaved) : LocalisationTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Localisation-----");
		return response;
	}

	/**
	 * update Localisation by using LocalisationDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<LocalisationDto> update(Request<LocalisationDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Localisation-----");

		Response<LocalisationDto> response = new Response<LocalisationDto>();
		List<Localisation>        items    = new ArrayList<Localisation>();
			
		for (LocalisationDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la localisation existe
			Localisation entityToSave = null;
			entityToSave = localisationRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("localisation id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			Localisation existingLocalisation = null;
			if (Utilities.notBlank(dto.getLibelle())) {
				existingLocalisation = localisationRepository.findByLibelle(dto.getLibelle(), Boolean.FALSE);
				if(existingLocalisation != null && !existingLocalisation.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError.DATA_DUPLICATE("Tentavtive de duplication de :" + dto.getLibelle(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setLibelle(dto.getLibelle());
			}
			if (Utilities.notBlank(dto.getCode())) {
				existingLocalisation = localisationRepository.findByCode(dto.getCode(), Boolean.FALSE);
				if(existingLocalisation != null && !existingLocalisation.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError.DATA_DUPLICATE("Tentavtive de duplication de :" + dto.getCode(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setCode(dto.getCode());
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
			List<Localisation> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = localisationRepository.saveAll((Iterable<Localisation>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("localisation", locale));
				response.setHasError(true);
				return response;
			}
			List<LocalisationDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? LocalisationTransformer.INSTANCE.toLiteDtos(itemsSaved) : LocalisationTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Localisation-----");
		return response;
	}

	/**
	 * delete Localisation by using LocalisationDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<LocalisationDto> delete(Request<LocalisationDto> request, Locale locale)  {
		log.info("----begin delete Localisation-----");

		Response<LocalisationDto> response = new Response<LocalisationDto>();
		List<Localisation>        items    = new ArrayList<Localisation>();
			
		for (LocalisationDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la localisation existe
			Localisation existingEntity = null;
			existingEntity = localisationRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("localisation -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// groupeRadius
			List<GroupeRadius> listOfGroupeRadius = groupeRadiusRepository.findByLocalisationId(existingEntity.getId(), false);
			if (listOfGroupeRadius != null && !listOfGroupeRadius.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfGroupeRadius.size() + ")", locale));
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
			localisationRepository.saveAll((Iterable<Localisation>) items);

			response.setHasError(false);
		}

		log.info("----end delete Localisation-----");
		return response;
	}

	/**
	 * get Localisation by using LocalisationDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<LocalisationDto> getByCriteria(Request<LocalisationDto> request, Locale locale)  throws Exception {
		log.info("----begin get Localisation-----");

		Response<LocalisationDto> response = new Response<LocalisationDto>();
		List<Localisation> items 			 = localisationRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<LocalisationDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? LocalisationTransformer.INSTANCE.toLiteDtos(items) : LocalisationTransformer.INSTANCE.toDtos(items);

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
			response.setCount(localisationRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("localisation", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Localisation-----");
		return response;
	}

	/**
	 * get full LocalisationDto by using Localisation as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private LocalisationDto getFullInfos(LocalisationDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
