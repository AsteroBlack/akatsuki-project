
/*
 * Java business for entity table type_offre 
 * Created on 2021-03-09 ( Time 11:10:42 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ci.smile.system.manager.obf.dao.entity.CoefficientModele;
import ci.smile.system.manager.obf.dao.entity.Radius;
import ci.smile.system.manager.obf.dao.entity.TypeOffre;
import ci.smile.system.manager.obf.dao.repository.CoefficientModeleRepository;
import ci.smile.system.manager.obf.dao.repository.RadiusRepository;
import ci.smile.system.manager.obf.dao.repository.TypeOffreRepository;
import ci.smile.system.manager.obf.utils.ExceptionUtils;
import ci.smile.system.manager.obf.utils.FunctionalError;
import ci.smile.system.manager.obf.utils.TechnicalError;
import ci.smile.system.manager.obf.utils.Utilities;
import ci.smile.system.manager.obf.utils.Validate;
import ci.smile.system.manager.obf.utils.contract.IBasicBusiness;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.TypeOffreDto;
import ci.smile.system.manager.obf.utils.dto.transformer.TypeOffreTransformer;
import lombok.extern.java.Log;

/**
BUSINESS for table "type_offre"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class TypeOffreBusiness implements IBasicBusiness<Request<TypeOffreDto>, Response<TypeOffreDto>> {

	private Response<TypeOffreDto> response;
	@Autowired
	private TypeOffreRepository typeOffreRepository;
	@Autowired
	private RadiusRepository radiusRepository;
	@Autowired
	private CoefficientModeleRepository coefficientModeleRepository;
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

	public TypeOffreBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create TypeOffre by using TypeOffreDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<TypeOffreDto> create(Request<TypeOffreDto> request, Locale locale)  throws ParseException {
		log.info("----begin create TypeOffre-----");

		Response<TypeOffreDto> response = new Response<TypeOffreDto>();
		List<TypeOffre>        items    = new ArrayList<TypeOffre>();

		for (TypeOffreDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("libelle", dto.getLibelle());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if typeOffre to insert do not exist
			TypeOffre existingEntity = null;
			// verif unique code in db
			existingEntity = typeOffreRepository.findByCode(dto.getCode(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("typeOffre code -> " + dto.getCode(), locale));
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
			existingEntity = typeOffreRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("typeOffre libelle -> " + dto.getLibelle(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique libelle in items to save
			if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
				response.setHasError(true);
				return response;
			}

			TypeOffre entityToSave = null;
			entityToSave = TypeOffreTransformer.INSTANCE.toEntity(dto);
		//	entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
		//	entityToSave.setCode(dto.getLibelle());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<TypeOffre> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = typeOffreRepository.saveAll((Iterable<TypeOffre>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("typeOffre", locale));
				response.setHasError(true);
				return response;
			}
			List<TypeOffreDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TypeOffreTransformer.INSTANCE.toLiteDtos(itemsSaved) : TypeOffreTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create TypeOffre-----");
		return response;
	}

	/**
	 * update TypeOffre by using TypeOffreDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<TypeOffreDto> update(Request<TypeOffreDto> request, Locale locale)  throws ParseException {
		log.info("----begin update TypeOffre-----");

		Response<TypeOffreDto> response = new Response<TypeOffreDto>();
		List<TypeOffre>        items    = new ArrayList<TypeOffre>();

		for (TypeOffreDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la typeOffre existe
			TypeOffre entityToSave = null;
			entityToSave = typeOffreRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("typeOffre id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			TypeOffre exisitingTypeOffre = null;
			if (Utilities.notBlank(dto.getLibelle())) {
				exisitingTypeOffre = typeOffreRepository.findByLibelle(dto.getLibelle(), Boolean.FALSE);
				if(exisitingTypeOffre != null && !exisitingTypeOffre.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError.DATA_DUPLICATE("fonctionnalite libelle -> " + dto.getLibelle(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setLibelle(dto.getLibelle());
			}
			if (Utilities.notBlank(dto.getCode())) {
				exisitingTypeOffre = typeOffreRepository.findByCode(dto.getCode(), Boolean.FALSE);
				if(exisitingTypeOffre != null && !exisitingTypeOffre.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError.DATA_DUPLICATE("fonctionnalite code -> " + dto.getCode(), locale));
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
			List<TypeOffre> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = typeOffreRepository.saveAll((Iterable<TypeOffre>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("typeOffre", locale));
				response.setHasError(true);
				return response;
			}
			List<TypeOffreDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TypeOffreTransformer.INSTANCE.toLiteDtos(itemsSaved) : TypeOffreTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update TypeOffre-----");
		return response;
	}

	/**
	 * delete TypeOffre by using TypeOffreDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<TypeOffreDto> delete(Request<TypeOffreDto> request, Locale locale)  {
		log.info("----begin delete TypeOffre-----");

		Response<TypeOffreDto> response = new Response<TypeOffreDto>();
		List<TypeOffre>        items    = new ArrayList<TypeOffre>();

		for (TypeOffreDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la typeOffre existe
			TypeOffre existingEntity = null;
			existingEntity = typeOffreRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("typeOffre -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// radius
			List<Radius> listOfRadius = radiusRepository.findByTypeOffreId(existingEntity.getId(), false);
			if (listOfRadius != null && !listOfRadius.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfRadius.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// coefficientModele
			List<CoefficientModele> listOfCoefficientModele = coefficientModeleRepository.findByTypeOffreId(existingEntity.getId(), false);
			if (listOfCoefficientModele != null && !listOfCoefficientModele.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfCoefficientModele.size() + ")", locale));
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
			typeOffreRepository.saveAll((Iterable<TypeOffre>) items);

			response.setHasError(false);
		}

		log.info("----end delete TypeOffre-----");
		return response;
	}

	/**
	 * get TypeOffre by using TypeOffreDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<TypeOffreDto> getByCriteria(Request<TypeOffreDto> request, Locale locale)  throws Exception {
		log.info("----begin get TypeOffre-----");

		Response<TypeOffreDto> response = new Response<TypeOffreDto>();
		List<TypeOffre> items 			 = typeOffreRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<TypeOffreDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TypeOffreTransformer.INSTANCE.toLiteDtos(items) : TypeOffreTransformer.INSTANCE.toDtos(items);

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
			response.setCount(typeOffreRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("typeOffre", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get TypeOffre-----");
		return response;
	}

	/**
	 * get full TypeOffreDto by using TypeOffre as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private TypeOffreDto getFullInfos(TypeOffreDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
