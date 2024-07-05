                                                        												
/*
 * Java business for entity table cause_generale 
 * Created on 2023-01-26 ( Time 00:38:57 )
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
import ci.smile.system.manager.obf.dao.entity.CauseGenerale;
import ci.smile.system.manager.obf.dao.entity.Corbeille;
import ci.smile.system.manager.obf.dao.entity.*;
import ci.smile.system.manager.obf.dao.repository.*;

/**
BUSINESS for table "cause_generale"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class CauseGeneraleBusiness implements IBasicBusiness<Request<CauseGeneraleDto>, Response<CauseGeneraleDto>> {

	private Response<CauseGeneraleDto> response;
	@Autowired
	private CauseGeneraleRepository causeGeneraleRepository;
	@Autowired
	private CorbeilleRepository corbeilleRepository;
	@Autowired
	private CauseSpecifiqueRepository causeSpecifiqueRepository;
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

	public CauseGeneraleBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create CauseGenerale by using CauseGeneraleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CauseGeneraleDto> create(Request<CauseGeneraleDto> request, Locale locale)  throws ParseException {
		log.info("----begin create CauseGenerale-----");

		Response<CauseGeneraleDto> response = new Response<CauseGeneraleDto>();
		List<CauseGenerale>        items    = new ArrayList<CauseGenerale>();
			
		for (CauseGeneraleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("code", dto.getCode());
			fieldsToVerify.put("libelle", dto.getLibelle());
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			fieldsToVerify.put("corbeilleId", dto.getCorbeilleId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if causeGenerale to insert do not exist
			CauseGenerale existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("causeGenerale id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// verif unique code in db
			existingEntity = causeGeneraleRepository.findByCode(dto.getCode(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("causeGenerale code -> " + dto.getCode(), locale));
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
			existingEntity = causeGeneraleRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("causeGenerale libelle -> " + dto.getLibelle(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique libelle in items to save
			if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
				response.setHasError(true);
				return response;
			}

			// Verify if corbeille exist
			Corbeille existingCorbeille = null;
			if (dto.getCorbeilleId() != null && dto.getCorbeilleId() > 0){
				existingCorbeille = corbeilleRepository.findOne(dto.getCorbeilleId(), false);
				if (existingCorbeille == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("corbeille corbeilleId -> " + dto.getCorbeilleId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				CauseGenerale entityToSave = null;
			entityToSave = CauseGeneraleTransformer.INSTANCE.toEntity(dto, existingCorbeille);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<CauseGenerale> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = causeGeneraleRepository.saveAll((Iterable<CauseGenerale>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("causeGenerale", locale));
				response.setHasError(true);
				return response;
			}
			List<CauseGeneraleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CauseGeneraleTransformer.INSTANCE.toLiteDtos(itemsSaved) : CauseGeneraleTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create CauseGenerale-----");
		return response;
	}

	/**
	 * update CauseGenerale by using CauseGeneraleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CauseGeneraleDto> update(Request<CauseGeneraleDto> request, Locale locale)  throws ParseException {
		log.info("----begin update CauseGenerale-----");

		Response<CauseGeneraleDto> response = new Response<CauseGeneraleDto>();
		List<CauseGenerale>        items    = new ArrayList<CauseGenerale>();
			
		for (CauseGeneraleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la causeGenerale existe
			CauseGenerale entityToSave = null;
			entityToSave = causeGeneraleRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("causeGenerale id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if corbeille exist
			if (dto.getCorbeilleId() != null && dto.getCorbeilleId() > 0){
				Corbeille existingCorbeille = corbeilleRepository.findOne(dto.getCorbeilleId(), false);
				if (existingCorbeille == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("corbeille corbeilleId -> " + dto.getCorbeilleId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setCorbeille(existingCorbeille);
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
			List<CauseGenerale> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = causeGeneraleRepository.saveAll((Iterable<CauseGenerale>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("causeGenerale", locale));
				response.setHasError(true);
				return response;
			}
			List<CauseGeneraleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CauseGeneraleTransformer.INSTANCE.toLiteDtos(itemsSaved) : CauseGeneraleTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update CauseGenerale-----");
		return response;
	}

	/**
	 * delete CauseGenerale by using CauseGeneraleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CauseGeneraleDto> delete(Request<CauseGeneraleDto> request, Locale locale)  {
		log.info("----begin delete CauseGenerale-----");

		Response<CauseGeneraleDto> response = new Response<CauseGeneraleDto>();
		List<CauseGenerale>        items    = new ArrayList<CauseGenerale>();
			
		for (CauseGeneraleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la causeGenerale existe
			CauseGenerale existingEntity = null;
			existingEntity = causeGeneraleRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("causeGenerale -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// causeSpecifique
			List<CauseSpecifique> listOfCauseSpecifique = causeSpecifiqueRepository.findByCauseGeneraleId(existingEntity.getId(), false);
			if (listOfCauseSpecifique != null && !listOfCauseSpecifique.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfCauseSpecifique.size() + ")", locale));
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
			causeGeneraleRepository.saveAll((Iterable<CauseGenerale>) items);

			response.setHasError(false);
		}

		log.info("----end delete CauseGenerale-----");
		return response;
	}

	/**
	 * get CauseGenerale by using CauseGeneraleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CauseGeneraleDto> getByCriteria(Request<CauseGeneraleDto> request, Locale locale)  throws Exception {
		log.info("----begin get CauseGenerale-----");

		Response<CauseGeneraleDto> response = new Response<CauseGeneraleDto>();
		List<CauseGenerale> items 			 = causeGeneraleRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<CauseGeneraleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CauseGeneraleTransformer.INSTANCE.toLiteDtos(items) : CauseGeneraleTransformer.INSTANCE.toDtos(items);

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
			response.setCount(causeGeneraleRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("causeGenerale", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get CauseGenerale-----");
		return response;
	}

	/**
	 * get full CauseGeneraleDto by using CauseGenerale as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private CauseGeneraleDto getFullInfos(CauseGeneraleDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
