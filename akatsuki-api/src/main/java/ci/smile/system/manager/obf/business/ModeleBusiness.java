
/*
 * Java business for entity table modele 
 * Created on 2021-03-09 ( Time 11:10:42 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import ci.smile.system.manager.obf.dao.entity.Modele;
import ci.smile.system.manager.obf.dao.repository.CoefficientModeleRepository;
import ci.smile.system.manager.obf.dao.repository.ModeleRepository;
import ci.smile.system.manager.obf.utils.ExceptionUtils;
import ci.smile.system.manager.obf.utils.FunctionalError;
import ci.smile.system.manager.obf.utils.TechnicalError;
import ci.smile.system.manager.obf.utils.Utilities;
import ci.smile.system.manager.obf.utils.Validate;
import ci.smile.system.manager.obf.utils.contract.IBasicBusiness;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.CoefficientModeleDto;
import ci.smile.system.manager.obf.utils.dto.ModeleDto;
import ci.smile.system.manager.obf.utils.dto.transformer.CoefficientModeleTransformer;
import ci.smile.system.manager.obf.utils.dto.transformer.ModeleTransformer;
import lombok.extern.java.Log;

/**
BUSINESS for table "modele"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class ModeleBusiness implements IBasicBusiness<Request<ModeleDto>, Response<ModeleDto>> {
	private Response<ModeleDto> response;
	@Autowired
	private ModeleRepository modeleRepository;
	@Autowired
	private CoefficientModeleRepository coefficientModeleRepository;
	@Autowired
	private CoefficientModeleBusiness coefficientModeleBusiness;
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

	public ModeleBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create Modele by using ModeleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ModeleDto> create(Request<ModeleDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Modele-----");

		Response<ModeleDto> response = new Response<ModeleDto>();
		List<Modele>        items    = new ArrayList<Modele>();

		for (ModeleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("libelle", dto.getLibelle());
			fieldsToVerify.put("coefMac", dto.getCoefMac());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if modele to insert do not exist
			Modele existingEntity = null;
			// verif unique code in db
			existingEntity = modeleRepository.findByCode(dto.getCode(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("modele code -> " + dto.getCode(), locale));
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
			existingEntity = modeleRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("modele libelle -> " + dto.getLibelle(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique libelle in items to save
			if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
				response.setHasError(true);
				return response;
			}

			Modele entityToSave = null;
			entityToSave = ModeleTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCode(dto.getLibelle());
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);

			Modele modeleToSave = modeleRepository.save(entityToSave);
			if (modeleToSave == null) {
				response.setStatus(functionalError.SAVE_FAIL("modele", locale));
				response.setHasError(true);
				return response;
			}
			Request<CoefficientModeleDto> subRequest = new Request<>();
			CoefficientModeleDto cDto = new CoefficientModeleDto();	
			cDto.setModeleId(modeleToSave.getId());
			cDto.setCoefMac(dto.getCoefMac());
			subRequest.setUser(request.getUser());
			subRequest.setDatas(Arrays.asList(cDto));
			Response<CoefficientModeleDto>  subResponse = coefficientModeleBusiness.create(subRequest, locale);
			if(subResponse != null && subResponse.isHasError()) {
				response.setStatus(subResponse.getStatus());
				response.setHasError(true);
				return response;
			}
			items.add(modeleToSave);
		}

		if (!items.isEmpty()) {
			//			List<Modele> itemsSaved = null;
			//			// inserer les donnees en base de donnees
			//			itemsSaved = modeleRepository.saveAll((Iterable<Modele>) items);
			//			if (itemsSaved == null) {
			//				response.setStatus(functionalError.SAVE_FAIL("modele", locale));
			//				response.setHasError(true);
			//				return response;
			//			}
			List<ModeleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ModeleTransformer.INSTANCE.toLiteDtos(items) : ModeleTransformer.INSTANCE.toDtos(items);

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
			response.setHasError(false);
		}

		log.info("----end create Modele-----");
		return response;
	}

	/**
	 * update Modele by using ModeleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ModeleDto> update(Request<ModeleDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Modele-----");

		Response<ModeleDto> response = new Response<ModeleDto>();
		List<Modele>        items    = new ArrayList<Modele>();

		for (ModeleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la modele existe
			Modele entityToSave = null;
			entityToSave = modeleRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("modele id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
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
			if(dto.getCoefMac() != null && dto.getCoefMac() >= 0) {
				CoefficientModele coefModel = coefficientModeleRepository.findByModeleIdUnique(entityToSave.getId(), false);
				CoefficientModeleDto cDto = CoefficientModeleTransformer.INSTANCE.toDto(coefModel);
				// maj de la table associative 
				Request<CoefficientModeleDto> subRequest = new Request<>();
				cDto.setModeleId(entityToSave.getId());
				cDto.setCoefMac(dto.getCoefMac());
				subRequest.setUser(request.getUser());
				subRequest.setDatas(Arrays.asList(cDto));
				Response<CoefficientModeleDto>  subResponse = coefficientModeleBusiness.update(subRequest, locale);
				if(subResponse != null && subResponse.isHasError()) {
					response.setStatus(subResponse.getStatus());
					response.setHasError(true);
					return response;
				}
			}

			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Modele> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = modeleRepository.saveAll((Iterable<Modele>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("modele", locale));
				response.setHasError(true);
				return response;
			}
			List<ModeleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ModeleTransformer.INSTANCE.toLiteDtos(itemsSaved) : ModeleTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Modele-----");
		return response;
	}

	/**
	 * delete Modele by using ModeleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ModeleDto> delete(Request<ModeleDto> request, Locale locale)  {
		log.info("----begin delete Modele-----");

		Response<ModeleDto> response = new Response<ModeleDto>();
		List<Modele>        items    = new ArrayList<Modele>();

		for (ModeleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la modele existe
			Modele existingEntity = null;
			existingEntity = modeleRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("modele -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// coefficientModele
			List<CoefficientModele> listOfCoefficientModele = coefficientModeleRepository.findByModeleId(existingEntity.getId(), false);
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
			modeleRepository.saveAll((Iterable<Modele>) items);

			response.setHasError(false);
		}

		log.info("----end delete Modele-----");
		return response;
	}

	/**
	 * get Modele by using ModeleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ModeleDto> getByCriteria(Request<ModeleDto> request, Locale locale)  throws Exception {
		log.info("----begin get Modele-----");

		Response<ModeleDto> response = new Response<ModeleDto>();
		List<Modele> items 			 = modeleRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<ModeleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ModeleTransformer.INSTANCE.toLiteDtos(items) : ModeleTransformer.INSTANCE.toDtos(items);

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
			response.setCount(modeleRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("modele", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Modele-----");
		return response;
	}

	/**
	 * get full ModeleDto by using Modele as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private ModeleDto getFullInfos(ModeleDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here
		CoefficientModele coefficientModele = coefficientModeleRepository.getByModeleId(dto.getId(), Boolean.FALSE);
		if(coefficientModele != null) {
			dto.setCoefficientModele(CoefficientModeleTransformer.INSTANCE.toDto(coefficientModele));
		}
		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}
		return dto;
	}
}
