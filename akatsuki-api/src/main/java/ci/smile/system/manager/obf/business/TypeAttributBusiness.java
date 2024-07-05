
/*
 * Java business for entity table type_attribut 
 * Created on 2021-04-22 ( Time 11:56:53 )
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
import ci.smile.system.manager.obf.dao.entity.TypeAttribut;
import ci.smile.system.manager.obf.dao.entity.Attribut;
import ci.smile.system.manager.obf.dao.entity.*;
import ci.smile.system.manager.obf.dao.repository.*;

/**
BUSINESS for table "type_attribut"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class TypeAttributBusiness implements IBasicBusiness<Request<TypeAttributDto>, Response<TypeAttributDto>> {

	private Response<TypeAttributDto> response;
	@Autowired
	private TypeAttributRepository typeAttributRepository;
	@Autowired
	private AttributRepository attributRepository;
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

	public TypeAttributBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create TypeAttribut by using TypeAttributDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<TypeAttributDto> create(Request<TypeAttributDto> request, Locale locale)  throws ParseException {
		log.info("----begin create TypeAttribut-----");

		Response<TypeAttributDto> response = new Response<TypeAttributDto>();
		List<TypeAttribut>        items    = new ArrayList<TypeAttribut>();

		for (TypeAttributDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("code", dto.getCode());
			fieldsToVerify.put("attributId", dto.getAttributId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if typeAttribut to insert do not exist
			TypeAttribut existingEntity = null;
			// verif unique code in db
			existingEntity = typeAttributRepository.getCodeAndAttributId(dto.getCode(), dto.getAttributId(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("typeAttribut code avec attribut -> " + dto.getCode(), locale));
				response.setHasError(true);
				return response;
			}
			
			// verif unique code in items to save
			if (items.stream().anyMatch(a -> a.getCode().equalsIgnoreCase(dto.getCode()) && a.getAttribut().getId().equals(dto.getAttributId()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" code  avec atrribut ", locale));
				response.setHasError(true);
				return response;
			}

			// Verify if attribut exist
			Attribut existingAttribut = null;
			if (dto.getAttributId() != null && dto.getAttributId() > 0){
				existingAttribut = attributRepository.findOne(dto.getAttributId(), false);
				if (existingAttribut == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("attribut attributId -> " + dto.getAttributId(), locale));
					response.setHasError(true);
					return response;
				}
			}			
			TypeAttribut entityToSave = null;
			entityToSave = TypeAttributTransformer.INSTANCE.toEntity(dto, existingAttribut);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<TypeAttribut> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = typeAttributRepository.saveAll((Iterable<TypeAttribut>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("typeAttribut", locale));
				response.setHasError(true);
				return response;
			}
			List<TypeAttributDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TypeAttributTransformer.INSTANCE.toLiteDtos(itemsSaved) : TypeAttributTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create TypeAttribut-----");
		return response;
	}

	/**
	 * update TypeAttribut by using TypeAttributDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<TypeAttributDto> update(Request<TypeAttributDto> request, Locale locale)  throws ParseException {
		log.info("----begin update TypeAttribut-----");

		Response<TypeAttributDto> response = new Response<TypeAttributDto>();
		List<TypeAttribut>        items    = new ArrayList<TypeAttribut>();

		for (TypeAttributDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la typeAttribut existe
			TypeAttribut entityToSave = null;
			entityToSave = typeAttributRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("typeAttribut id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if attribut exist
			if (dto.getAttributId() != null && dto.getAttributId() > 0){
				Attribut existingAttribut = attributRepository.findOne(dto.getAttributId(), false);
				if (existingAttribut == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("attribut attributId -> " + dto.getAttributId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setAttribut(existingAttribut);
			}
			TypeAttribut existingEntity = null;
			if (Utilities.notBlank(dto.getCode())) {
				existingEntity = typeAttributRepository.getCodeAndAttributId(dto.getCode(), entityToSave.getAttribut().getId(),false);
				if(existingEntity != null && !existingEntity.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError.DATA_EXIST(" attribut avec code ", locale));
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
			List<TypeAttribut> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = typeAttributRepository.saveAll((Iterable<TypeAttribut>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("typeAttribut", locale));
				response.setHasError(true);
				return response;
			}
			List<TypeAttributDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TypeAttributTransformer.INSTANCE.toLiteDtos(itemsSaved) : TypeAttributTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update TypeAttribut-----");
		return response;
	}

	/**
	 * delete TypeAttribut by using TypeAttributDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<TypeAttributDto> delete(Request<TypeAttributDto> request, Locale locale)  {
		log.info("----begin delete TypeAttribut-----");

		Response<TypeAttributDto> response = new Response<TypeAttributDto>();
		List<TypeAttribut>        items    = new ArrayList<TypeAttribut>();

		for (TypeAttributDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la typeAttribut existe
			TypeAttribut existingEntity = null;
			existingEntity = typeAttributRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("typeAttribut -> " + dto.getId(), locale));
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
			typeAttributRepository.saveAll((Iterable<TypeAttribut>) items);

			response.setHasError(false);
		}

		log.info("----end delete TypeAttribut-----");
		return response;
	}

	/**
	 * get TypeAttribut by using TypeAttributDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<TypeAttributDto> getByCriteria(Request<TypeAttributDto> request, Locale locale)  throws Exception {
		log.info("----begin get TypeAttribut-----");

		Response<TypeAttributDto> response = new Response<TypeAttributDto>();
		List<TypeAttribut> items 			 = typeAttributRepository.getByCriteria(request, em, locale);

		
		if (items != null && !items.isEmpty()) {
			List<TypeAttributDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TypeAttributTransformer.INSTANCE.toLiteDtos(items) : TypeAttributTransformer.INSTANCE.toDtos(items);

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
			response.setCount(typeAttributRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("typeAttribut", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get TypeAttribut-----");
		return response;
	}

	/**
	 * get full TypeAttributDto by using TypeAttribut as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private TypeAttributDto getFullInfos(TypeAttributDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
