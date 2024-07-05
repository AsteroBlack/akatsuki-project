                                                    											
/*
 * Java business for entity table corbeille 
 * Created on 2023-01-26 ( Time 00:38:58 )
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
import ci.smile.system.manager.obf.dao.entity.Corbeille;
import ci.smile.system.manager.obf.dao.entity.*;
import ci.smile.system.manager.obf.dao.repository.*;

/**
BUSINESS for table "corbeille"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class CorbeilleBusiness implements IBasicBusiness<Request<CorbeilleDto>, Response<CorbeilleDto>> {

	private Response<CorbeilleDto> response;
	@Autowired
	private CorbeilleRepository corbeilleRepository;
	@Autowired
	private CauseGeneraleRepository causeGeneraleRepository;
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

	public CorbeilleBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Corbeille by using CorbeilleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CorbeilleDto> create(Request<CorbeilleDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Corbeille-----");

		Response<CorbeilleDto> response = new Response<CorbeilleDto>();
		List<Corbeille>        items    = new ArrayList<Corbeille>();
			
		for (CorbeilleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("code", dto.getCode());
			fieldsToVerify.put("libelle", dto.getLibelle());
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if corbeille to insert do not exist
			Corbeille existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("corbeille id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// verif unique code in db
			existingEntity = corbeilleRepository.findByCode(dto.getCode(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("corbeille code -> " + dto.getCode(), locale));
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
			existingEntity = corbeilleRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("corbeille libelle -> " + dto.getLibelle(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique libelle in items to save
			if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
				response.setHasError(true);
				return response;
			}

				Corbeille entityToSave = null;
			entityToSave = CorbeilleTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Corbeille> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = corbeilleRepository.saveAll((Iterable<Corbeille>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("corbeille", locale));
				response.setHasError(true);
				return response;
			}
			List<CorbeilleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CorbeilleTransformer.INSTANCE.toLiteDtos(itemsSaved) : CorbeilleTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Corbeille-----");
		return response;
	}

	/**
	 * update Corbeille by using CorbeilleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CorbeilleDto> update(Request<CorbeilleDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Corbeille-----");

		Response<CorbeilleDto> response = new Response<CorbeilleDto>();
		List<Corbeille>        items    = new ArrayList<Corbeille>();
			
		for (CorbeilleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la corbeille existe
			Corbeille entityToSave = null;
			entityToSave = corbeilleRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("corbeille id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.notBlank(dto.getCode())) {
				entityToSave.setCode(dto.getCode());
			}
			if (Utilities.notBlank(dto.getLibelle())) {
				entityToSave.setLibelle(dto.getLibelle());
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
			List<Corbeille> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = corbeilleRepository.saveAll((Iterable<Corbeille>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("corbeille", locale));
				response.setHasError(true);
				return response;
			}
			List<CorbeilleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CorbeilleTransformer.INSTANCE.toLiteDtos(itemsSaved) : CorbeilleTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Corbeille-----");
		return response;
	}

	/**
	 * delete Corbeille by using CorbeilleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CorbeilleDto> delete(Request<CorbeilleDto> request, Locale locale)  {
		log.info("----begin delete Corbeille-----");

		Response<CorbeilleDto> response = new Response<CorbeilleDto>();
		List<Corbeille>        items    = new ArrayList<Corbeille>();
			
		for (CorbeilleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la corbeille existe
			Corbeille existingEntity = null;
			existingEntity = corbeilleRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("corbeille -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// causeGenerale
			List<CauseGenerale> listOfCauseGenerale = causeGeneraleRepository.findByCorbeilleId(existingEntity.getId(), false);
			if (listOfCauseGenerale != null && !listOfCauseGenerale.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfCauseGenerale.size() + ")", locale));
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
			corbeilleRepository.saveAll((Iterable<Corbeille>) items);

			response.setHasError(false);
		}

		log.info("----end delete Corbeille-----");
		return response;
	}

	/**
	 * get Corbeille by using CorbeilleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CorbeilleDto> getByCriteria(Request<CorbeilleDto> request, Locale locale)  throws Exception {
		log.info("----begin get Corbeille-----");

		Response<CorbeilleDto> response = new Response<CorbeilleDto>();
		List<Corbeille> items 			 = corbeilleRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<CorbeilleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CorbeilleTransformer.INSTANCE.toLiteDtos(items) : CorbeilleTransformer.INSTANCE.toDtos(items);

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
			response.setCount(corbeilleRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("corbeille", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Corbeille-----");
		return response;
	}

	/**
	 * get full CorbeilleDto by using Corbeille as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private CorbeilleDto getFullInfos(CorbeilleDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
