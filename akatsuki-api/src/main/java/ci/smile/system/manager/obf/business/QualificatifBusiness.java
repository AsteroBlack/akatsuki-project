                                                        												
/*
 * Java business for entity table qualificatif 
 * Created on 2023-01-26 ( Time 00:38:59 )
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
import ci.smile.system.manager.obf.dao.entity.Qualificatif;
import ci.smile.system.manager.obf.dao.entity.*;
import ci.smile.system.manager.obf.dao.repository.*;

/**
BUSINESS for table "qualificatif"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class QualificatifBusiness implements IBasicBusiness<Request<QualificatifDto>, Response<QualificatifDto>> {

	private Response<QualificatifDto> response;
	@Autowired
	private QualificatifRepository qualificatifRepository;
	@Autowired
	private AssignationRepository assignationRepository;
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

	public QualificatifBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Qualificatif by using QualificatifDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<QualificatifDto> create(Request<QualificatifDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Qualificatif-----");

		Response<QualificatifDto> response = new Response<QualificatifDto>();
		List<Qualificatif>        items    = new ArrayList<Qualificatif>();
			
		for (QualificatifDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("libelle", dto.getLibelle());
			fieldsToVerify.put("code", dto.getCode());
			fieldsToVerify.put("source", dto.getSource());
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if qualificatif to insert do not exist
			Qualificatif existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("qualificatif id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// verif unique libelle in db
			existingEntity = qualificatifRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("qualificatif libelle -> " + dto.getLibelle(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique libelle in items to save
			if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
				response.setHasError(true);
				return response;
			}

			// verif unique code in db
			existingEntity = qualificatifRepository.findByCode(dto.getCode(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("qualificatif code -> " + dto.getCode(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique code in items to save
			if (items.stream().anyMatch(a -> a.getCode().equalsIgnoreCase(dto.getCode()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" code ", locale));
				response.setHasError(true);
				return response;
			}

				Qualificatif entityToSave = null;
			entityToSave = QualificatifTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Qualificatif> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = qualificatifRepository.saveAll((Iterable<Qualificatif>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("qualificatif", locale));
				response.setHasError(true);
				return response;
			}
			List<QualificatifDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? QualificatifTransformer.INSTANCE.toLiteDtos(itemsSaved) : QualificatifTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Qualificatif-----");
		return response;
	}

	/**
	 * update Qualificatif by using QualificatifDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<QualificatifDto> update(Request<QualificatifDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Qualificatif-----");

		Response<QualificatifDto> response = new Response<QualificatifDto>();
		List<Qualificatif>        items    = new ArrayList<Qualificatif>();
			
		for (QualificatifDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la qualificatif existe
			Qualificatif entityToSave = null;
			entityToSave = qualificatifRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("qualificatif id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.notBlank(dto.getLibelle())) {
				entityToSave.setLibelle(dto.getLibelle());
			}
			if (Utilities.notBlank(dto.getCode())) {
				entityToSave.setCode(dto.getCode());
			}
			if (Utilities.notBlank(dto.getSource())) {
				entityToSave.setSource(dto.getSource());
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
			List<Qualificatif> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = qualificatifRepository.saveAll((Iterable<Qualificatif>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("qualificatif", locale));
				response.setHasError(true);
				return response;
			}
			List<QualificatifDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? QualificatifTransformer.INSTANCE.toLiteDtos(itemsSaved) : QualificatifTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Qualificatif-----");
		return response;
	}

	/**
	 * delete Qualificatif by using QualificatifDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<QualificatifDto> delete(Request<QualificatifDto> request, Locale locale)  {
		log.info("----begin delete Qualificatif-----");

		Response<QualificatifDto> response = new Response<QualificatifDto>();
		List<Qualificatif>        items    = new ArrayList<Qualificatif>();
			
		for (QualificatifDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la qualificatif existe
			Qualificatif existingEntity = null;
			existingEntity = qualificatifRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("qualificatif -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// assignation
			List<Assignation> listOfAssignation = assignationRepository.findByQualificatifId(existingEntity.getId(), false);
			if (listOfAssignation != null && !listOfAssignation.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfAssignation.size() + ")", locale));
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
			qualificatifRepository.saveAll((Iterable<Qualificatif>) items);

			response.setHasError(false);
		}

		log.info("----end delete Qualificatif-----");
		return response;
	}

	/**
	 * get Qualificatif by using QualificatifDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<QualificatifDto> getByCriteria(Request<QualificatifDto> request, Locale locale)  throws Exception {
		log.info("----begin get Qualificatif-----");

		Response<QualificatifDto> response = new Response<QualificatifDto>();
		List<Qualificatif> items 			 = qualificatifRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<QualificatifDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? QualificatifTransformer.INSTANCE.toLiteDtos(items) : QualificatifTransformer.INSTANCE.toDtos(items);

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
			response.setCount(qualificatifRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("qualificatif", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Qualificatif-----");
		return response;
	}

	/**
	 * get full QualificatifDto by using Qualificatif as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private QualificatifDto getFullInfos(QualificatifDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
