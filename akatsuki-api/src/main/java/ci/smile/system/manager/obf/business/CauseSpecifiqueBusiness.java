                                                            													
/*
 * Java business for entity table cause_specifique 
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
import ci.smile.system.manager.obf.dao.entity.CauseSpecifique;
import ci.smile.system.manager.obf.dao.entity.CauseGenerale;
import ci.smile.system.manager.obf.dao.entity.*;
import ci.smile.system.manager.obf.dao.repository.*;

/**
BUSINESS for table "cause_specifique"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class CauseSpecifiqueBusiness implements IBasicBusiness<Request<CauseSpecifiqueDto>, Response<CauseSpecifiqueDto>> {

	private Response<CauseSpecifiqueDto> response;
	@Autowired
	private CauseSpecifiqueRepository causeSpecifiqueRepository;
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

	public CauseSpecifiqueBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create CauseSpecifique by using CauseSpecifiqueDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CauseSpecifiqueDto> create(Request<CauseSpecifiqueDto> request, Locale locale)  throws ParseException {
		log.info("----begin create CauseSpecifique-----");

		Response<CauseSpecifiqueDto> response = new Response<CauseSpecifiqueDto>();
		List<CauseSpecifique>        items    = new ArrayList<CauseSpecifique>();
			
		for (CauseSpecifiqueDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("code", dto.getCode());
			fieldsToVerify.put("libelle", dto.getLibelle());
			fieldsToVerify.put("description", dto.getDescription());
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			fieldsToVerify.put("causeGeneraleId", dto.getCauseGeneraleId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if causeSpecifique to insert do not exist
			CauseSpecifique existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("causeSpecifique id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// verif unique code in db
			existingEntity = causeSpecifiqueRepository.findByCode(dto.getCode(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("causeSpecifique code -> " + dto.getCode(), locale));
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
			existingEntity = causeSpecifiqueRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("causeSpecifique libelle -> " + dto.getLibelle(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique libelle in items to save
			if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
				response.setHasError(true);
				return response;
			}

			// Verify if causeGenerale exist
			CauseGenerale existingCauseGenerale = null;
			if (dto.getCauseGeneraleId() != null && dto.getCauseGeneraleId() > 0){
				existingCauseGenerale = causeGeneraleRepository.findOne(dto.getCauseGeneraleId(), false);
				if (existingCauseGenerale == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("causeGenerale causeGeneraleId -> " + dto.getCauseGeneraleId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				CauseSpecifique entityToSave = null;
			entityToSave = CauseSpecifiqueTransformer.INSTANCE.toEntity(dto, existingCauseGenerale);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<CauseSpecifique> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = causeSpecifiqueRepository.saveAll((Iterable<CauseSpecifique>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("causeSpecifique", locale));
				response.setHasError(true);
				return response;
			}
			List<CauseSpecifiqueDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CauseSpecifiqueTransformer.INSTANCE.toLiteDtos(itemsSaved) : CauseSpecifiqueTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create CauseSpecifique-----");
		return response;
	}

	/**
	 * update CauseSpecifique by using CauseSpecifiqueDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CauseSpecifiqueDto> update(Request<CauseSpecifiqueDto> request, Locale locale)  throws ParseException {
		log.info("----begin update CauseSpecifique-----");

		Response<CauseSpecifiqueDto> response = new Response<CauseSpecifiqueDto>();
		List<CauseSpecifique>        items    = new ArrayList<CauseSpecifique>();
			
		for (CauseSpecifiqueDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la causeSpecifique existe
			CauseSpecifique entityToSave = null;
			entityToSave = causeSpecifiqueRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("causeSpecifique id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if causeGenerale exist
			if (dto.getCauseGeneraleId() != null && dto.getCauseGeneraleId() > 0){
				CauseGenerale existingCauseGenerale = causeGeneraleRepository.findOne(dto.getCauseGeneraleId(), false);
				if (existingCauseGenerale == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("causeGenerale causeGeneraleId -> " + dto.getCauseGeneraleId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setCauseGenerale(existingCauseGenerale);
			}
			if (Utilities.notBlank(dto.getCode())) {
				entityToSave.setCode(dto.getCode());
			}
			if (Utilities.notBlank(dto.getLibelle())) {
				entityToSave.setLibelle(dto.getLibelle());
			}
			if (Utilities.notBlank(dto.getDescription())) {
				entityToSave.setDescription(dto.getDescription());
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
			List<CauseSpecifique> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = causeSpecifiqueRepository.saveAll((Iterable<CauseSpecifique>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("causeSpecifique", locale));
				response.setHasError(true);
				return response;
			}
			List<CauseSpecifiqueDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CauseSpecifiqueTransformer.INSTANCE.toLiteDtos(itemsSaved) : CauseSpecifiqueTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update CauseSpecifique-----");
		return response;
	}

	/**
	 * delete CauseSpecifique by using CauseSpecifiqueDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CauseSpecifiqueDto> delete(Request<CauseSpecifiqueDto> request, Locale locale)  {
		log.info("----begin delete CauseSpecifique-----");

		Response<CauseSpecifiqueDto> response = new Response<CauseSpecifiqueDto>();
		List<CauseSpecifique>        items    = new ArrayList<CauseSpecifique>();
			
		for (CauseSpecifiqueDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la causeSpecifique existe
			CauseSpecifique existingEntity = null;
			existingEntity = causeSpecifiqueRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("causeSpecifique -> " + dto.getId(), locale));
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
			causeSpecifiqueRepository.saveAll((Iterable<CauseSpecifique>) items);

			response.setHasError(false);
		}

		log.info("----end delete CauseSpecifique-----");
		return response;
	}

	/**
	 * get CauseSpecifique by using CauseSpecifiqueDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CauseSpecifiqueDto> getByCriteria(Request<CauseSpecifiqueDto> request, Locale locale)  throws Exception {
		log.info("----begin get CauseSpecifique-----");

		Response<CauseSpecifiqueDto> response = new Response<CauseSpecifiqueDto>();
		List<CauseSpecifique> items 			 = causeSpecifiqueRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<CauseSpecifiqueDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CauseSpecifiqueTransformer.INSTANCE.toLiteDtos(items) : CauseSpecifiqueTransformer.INSTANCE.toDtos(items);

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
			response.setCount(causeSpecifiqueRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("causeSpecifique", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get CauseSpecifique-----");
		return response;
	}

	/**
	 * get full CauseSpecifiqueDto by using CauseSpecifique as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private CauseSpecifiqueDto getFullInfos(CauseSpecifiqueDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
