                                                                    															
/*
 * Java business for entity table assignation 
 * Created on 2023-01-26 ( Time 00:38:56 )
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
import ci.smile.system.manager.obf.dao.entity.Assignation;
import ci.smile.system.manager.obf.dao.entity.Qualificatif;
import ci.smile.system.manager.obf.dao.entity.*;
import ci.smile.system.manager.obf.dao.repository.*;

/**
BUSINESS for table "assignation"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class AssignationBusiness implements IBasicBusiness<Request<AssignationDto>, Response<AssignationDto>> {

	private Response<AssignationDto> response;
	@Autowired
	private AssignationRepository assignationRepository;
	@Autowired
	private QualificatifRepository qualificatifRepository;
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

	public AssignationBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Assignation by using AssignationDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AssignationDto> create(Request<AssignationDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Assignation-----");

		Response<AssignationDto> response = new Response<AssignationDto>();
		List<Assignation>        items    = new ArrayList<Assignation>();
			
		for (AssignationDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("libelle", dto.getLibelle());
			fieldsToVerify.put("code", dto.getCode());
			fieldsToVerify.put("qualificatif2", dto.getQualificatif2());
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			fieldsToVerify.put("qualificatifId", dto.getQualificatifId());
			fieldsToVerify.put("assignation", dto.getAssignation());
			fieldsToVerify.put("technologie", dto.getTechnologie());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if assignation to insert do not exist
			Assignation existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("assignation id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// verif unique libelle in db
			existingEntity = assignationRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("assignation libelle -> " + dto.getLibelle(), locale));
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
			existingEntity = assignationRepository.findByCode(dto.getCode(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("assignation code -> " + dto.getCode(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique code in items to save
			if (items.stream().anyMatch(a -> a.getCode().equalsIgnoreCase(dto.getCode()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" code ", locale));
				response.setHasError(true);
				return response;
			}

			// Verify if qualificatif exist
			Qualificatif existingQualificatif = null;
			if (dto.getQualificatifId() != null && dto.getQualificatifId() > 0){
				existingQualificatif = qualificatifRepository.findOne(dto.getQualificatifId(), false);
				if (existingQualificatif == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("qualificatif qualificatifId -> " + dto.getQualificatifId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				Assignation entityToSave = null;
			entityToSave = AssignationTransformer.INSTANCE.toEntity(dto, existingQualificatif);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Assignation> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = assignationRepository.saveAll((Iterable<Assignation>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("assignation", locale));
				response.setHasError(true);
				return response;
			}
			List<AssignationDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AssignationTransformer.INSTANCE.toLiteDtos(itemsSaved) : AssignationTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Assignation-----");
		return response;
	}

	/**
	 * update Assignation by using AssignationDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AssignationDto> update(Request<AssignationDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Assignation-----");

		Response<AssignationDto> response = new Response<AssignationDto>();
		List<Assignation>        items    = new ArrayList<Assignation>();
			
		for (AssignationDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la assignation existe
			Assignation entityToSave = null;
			entityToSave = assignationRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("assignation id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if qualificatif exist
			if (dto.getQualificatifId() != null && dto.getQualificatifId() > 0){
				Qualificatif existingQualificatif = qualificatifRepository.findOne(dto.getQualificatifId(), false);
				if (existingQualificatif == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("qualificatif qualificatifId -> " + dto.getQualificatifId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setQualificatif(existingQualificatif);
			}
			if (Utilities.notBlank(dto.getLibelle())) {
				entityToSave.setLibelle(dto.getLibelle());
			}
			if (Utilities.notBlank(dto.getCode())) {
				entityToSave.setCode(dto.getCode());
			}
			if (Utilities.notBlank(dto.getQualificatif2())) {
				entityToSave.setQualificatif2(dto.getQualificatif2());
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
			if (Utilities.notBlank(dto.getAssignation())) {
				entityToSave.setAssignation(dto.getAssignation());
			}
			if (Utilities.notBlank(dto.getTechnologie())) {
				entityToSave.setTechnologie(dto.getTechnologie());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Assignation> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = assignationRepository.saveAll((Iterable<Assignation>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("assignation", locale));
				response.setHasError(true);
				return response;
			}
			List<AssignationDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AssignationTransformer.INSTANCE.toLiteDtos(itemsSaved) : AssignationTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Assignation-----");
		return response;
	}

	/**
	 * delete Assignation by using AssignationDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AssignationDto> delete(Request<AssignationDto> request, Locale locale)  {
		log.info("----begin delete Assignation-----");

		Response<AssignationDto> response = new Response<AssignationDto>();
		List<Assignation>        items    = new ArrayList<Assignation>();
			
		for (AssignationDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la assignation existe
			Assignation existingEntity = null;
			existingEntity = assignationRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("assignation -> " + dto.getId(), locale));
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
			assignationRepository.saveAll((Iterable<Assignation>) items);

			response.setHasError(false);
		}

		log.info("----end delete Assignation-----");
		return response;
	}

	/**
	 * get Assignation by using AssignationDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AssignationDto> getByCriteria(Request<AssignationDto> request, Locale locale)  throws Exception {
		log.info("----begin get Assignation-----");

		Response<AssignationDto> response = new Response<AssignationDto>();
		List<Assignation> items 			 = assignationRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<AssignationDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AssignationTransformer.INSTANCE.toLiteDtos(items) : AssignationTransformer.INSTANCE.toDtos(items);

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
			response.setCount(assignationRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("assignation", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Assignation-----");
		return response;
	}

	/**
	 * get full AssignationDto by using Assignation as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private AssignationDto getFullInfos(AssignationDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
