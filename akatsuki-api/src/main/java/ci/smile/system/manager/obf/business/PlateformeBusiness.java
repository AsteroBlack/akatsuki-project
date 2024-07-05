                                                    											
/*
 * Java business for entity table plateforme 
 * Created on 2022-11-07 ( Time 16:50:07 )
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
import ci.smile.system.manager.obf.dao.entity.Plateforme;
import ci.smile.system.manager.obf.dao.entity.*;
import ci.smile.system.manager.obf.dao.repository.*;

/**
BUSINESS for table "plateforme"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class PlateformeBusiness implements IBasicBusiness<Request<PlateformeDto>, Response<PlateformeDto>> {

	private Response<PlateformeDto> response;
	@Autowired
	private PlateformeRepository plateformeRepository;
	@Autowired
	private OfferRepository offerRepository;
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

	public PlateformeBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Plateforme by using PlateformeDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PlateformeDto> create(Request<PlateformeDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Plateforme-----");

		Response<PlateformeDto> response = new Response<PlateformeDto>();
		List<Plateforme>        items    = new ArrayList<Plateforme>();
			
		for (PlateformeDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("code", dto.getCode());
			fieldsToVerify.put("libelle", dto.getLibelle());
//			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
//			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if plateforme to insert do not exist
			Plateforme existingEntity = null;
			// verif unique code in db
			existingEntity = plateformeRepository.findByCode(dto.getCode(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("plateforme code -> " + dto.getCode(), locale));
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
			existingEntity = plateformeRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("plateforme libelle -> " + dto.getLibelle(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique libelle in items to save
			if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
				response.setHasError(true);
				return response;
			}

				Plateforme entityToSave = null;
			entityToSave = PlateformeTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Plateforme> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = plateformeRepository.saveAll((Iterable<Plateforme>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("plateforme", locale));
				response.setHasError(true);
				return response;
			}
			List<PlateformeDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PlateformeTransformer.INSTANCE.toLiteDtos(itemsSaved) : PlateformeTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Plateforme-----");
		return response;
	}

	/**
	 * update Plateforme by using PlateformeDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PlateformeDto> update(Request<PlateformeDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Plateforme-----");

		Response<PlateformeDto> response = new Response<PlateformeDto>();
		List<Plateforme>        items    = new ArrayList<Plateforme>();
			
		for (PlateformeDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la plateforme existe
			Plateforme entityToSave = null;
			entityToSave = plateformeRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("plateforme id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.notBlank(dto.getCode())) {
				entityToSave.setCode(dto.getCode());
			}
			if (Utilities.notBlank(dto.getLibelle())) {
				entityToSave.setLibelle(dto.getLibelle());
			}
			if (dto.getCreatedBy() != null && dto.getCreatedBy() > 0) {
				entityToSave.setCreatedBy(dto.getCreatedBy());
			}
			if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) {
				entityToSave.setUpdatedBy(dto.getUpdatedBy());
			}
			if (Utilities.notBlank(dto.getDeletedAt())) {
				entityToSave.setDeletedAt(dateFormat.parse(dto.getDeletedAt()));
			}
			if (dto.getDeletedBy() != null && dto.getDeletedBy() > 0) {
				entityToSave.setDeletedBy(dto.getDeletedBy());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Plateforme> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = plateformeRepository.saveAll((Iterable<Plateforme>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("plateforme", locale));
				response.setHasError(true);
				return response;
			}
			List<PlateformeDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PlateformeTransformer.INSTANCE.toLiteDtos(itemsSaved) : PlateformeTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Plateforme-----");
		return response;
	}

	/**
	 * delete Plateforme by using PlateformeDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PlateformeDto> delete(Request<PlateformeDto> request, Locale locale)  {
		log.info("----begin delete Plateforme-----");

		Response<PlateformeDto> response = new Response<PlateformeDto>();
		List<Plateforme>        items    = new ArrayList<Plateforme>();
			
		for (PlateformeDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la plateforme existe
			Plateforme existingEntity = null;
			existingEntity = plateformeRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("plateforme -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}
			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

//			// offer
//			List<Offer> listOfOffer = offerRepository.findByIdPlateforme(existingEntity.getId(), false);
//			if (listOfOffer != null && !listOfOffer.isEmpty()){
//				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfOffer.size() + ")", locale));
//				response.setHasError(true);
//				return response;
//			}


			existingEntity.setIsDeleted(true);
			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setDeletedBy(request.getUser());
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			plateformeRepository.saveAll((Iterable<Plateforme>) items);

			response.setHasError(false);
		}

		log.info("----end delete Plateforme-----");
		return response;
	}

	/**
	 * get Plateforme by using PlateformeDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PlateformeDto> getByCriteria(Request<PlateformeDto> request, Locale locale)  throws Exception {
		log.info("----begin get Plateforme-----");

		Response<PlateformeDto> response = new Response<PlateformeDto>();
		List<Plateforme> items 			 = plateformeRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<PlateformeDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PlateformeTransformer.INSTANCE.toLiteDtos(items) : PlateformeTransformer.INSTANCE.toDtos(items);

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
			response.setCount(plateformeRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("plateforme", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Plateforme-----");
		return response;
	}

	/**
	 * get full PlateformeDto by using Plateforme as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private PlateformeDto getFullInfos(PlateformeDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here

		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}

	public Response<PlateformeDto> custom(Request<PlateformeDto> request, Locale locale) {
		log.info("----begin custom PlateformeDto-----");
		Response<PlateformeDto> response = new Response<PlateformeDto>();
		
		response.setHasError(false);
		response.setCount(1L);
		response.setItems(Arrays.asList(new PlateformeDto()));
		log.info("----end custom PlateformeDto-----");
		return response;
	}
}
