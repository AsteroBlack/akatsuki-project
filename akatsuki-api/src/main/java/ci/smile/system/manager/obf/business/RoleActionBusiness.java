
/*
 * Java business for entity table role_menus_habilitation 
 * Created on 2019-12-20 ( Time 00:51:50 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ci.smile.system.manager.obf.dao.entity.Action;
import ci.smile.system.manager.obf.dao.entity.Role;
import ci.smile.system.manager.obf.dao.entity.RoleAction;
import ci.smile.system.manager.obf.dao.repository.ActionRepository;
import ci.smile.system.manager.obf.dao.repository.MenusRepository;
import ci.smile.system.manager.obf.dao.repository.RoleActionRepository;
import ci.smile.system.manager.obf.dao.repository.RoleRepository;
import ci.smile.system.manager.obf.utils.ExceptionUtils;
import ci.smile.system.manager.obf.utils.FunctionalError;
import ci.smile.system.manager.obf.utils.TechnicalError;
import ci.smile.system.manager.obf.utils.Utilities;
import ci.smile.system.manager.obf.utils.Validate;
import ci.smile.system.manager.obf.utils.contract.IBasicBusiness;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.RoleActionDto;
import ci.smile.system.manager.obf.utils.dto.transformer.RoleActionTransformer;

/**
BUSINESS for table "role_menus_habilitation"
 * 
 * @author Geo
 *
 */
@Component
public class RoleActionBusiness implements IBasicBusiness<Request<RoleActionDto>, Response<RoleActionDto>> {

	private Response<RoleActionDto> response;
	@Autowired
	private RoleActionRepository RoleActionRepository;
	@Autowired
	private ActionRepository actionRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private MenusRepository menusRepository;
	@Autowired
	private FunctionalError functionalError;
	@Autowired
	private TechnicalError technicalError;
	@Autowired
	private ExceptionUtils exceptionUtils;
	@PersistenceContext
	private EntityManager em;
	private Logger log = LoggerFactory.getLogger(getClass());

	private SimpleDateFormat dateFormat;
	private SimpleDateFormat dateTimeFormat;

	public RoleActionBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create RoleAction by using RoleActionDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleActionDto> create(Request<RoleActionDto> request, Locale locale)  throws ParseException {
		log.info("----begin create RoleAction-----");

		Response<RoleActionDto> response = new Response<RoleActionDto>();
		List<RoleAction>        items    = new ArrayList<RoleAction>();

		for (RoleActionDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("roleId", dto.getRoleId());
			fieldsToVerify.put("actionId", dto.getActionId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if habilitation exist
			Action existingAction = null;
			if (dto.getActionId() != null && dto.getActionId() > 0){
				existingAction = actionRepository.findOne(dto.getActionId(), false);
				if (existingAction == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("action actionId -> " + dto.getActionId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			// Verify if role exist
			Role existingRole = null;
			if (dto.getRoleId() != null && dto.getRoleId() > 0){
				existingRole = roleRepository.findOne(dto.getRoleId(), false);
				if (existingRole == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("role roleId -> " + dto.getRoleId(), locale));
					response.setHasError(true);
					return response;
				}
			}
		
			RoleAction entityToSave = null;
			entityToSave = RoleActionTransformer.INSTANCE.toEntity(dto, existingRole, existingAction);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<RoleAction> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = RoleActionRepository.saveAll((Iterable<RoleAction>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("RoleAction", locale));
				response.setHasError(true);
				return response;
			}
			List<RoleActionDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoleActionTransformer.INSTANCE.toLiteDtos(itemsSaved) : RoleActionTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create RoleAction-----");
		return response;
	}

	/**
	 * update RoleAction by using RoleActionDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleActionDto> update(Request<RoleActionDto> request, Locale locale)  throws ParseException {
		log.info("----begin update RoleAction-----");

		Response<RoleActionDto> response = new Response<RoleActionDto>();
		List<RoleAction>        items    = new ArrayList<RoleAction>();

		for (RoleActionDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la RoleAction existe
			RoleAction entityToSave = null;
			entityToSave = RoleActionRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("RoleAction id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if action exist
			if (dto.getActionId() != null && dto.getActionId() > 0){
				Action existingAction = actionRepository.findOne(dto.getActionId(), false);
				if (existingAction == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("habilitation habilitationId -> " + dto.getActionId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setAction(existingAction);
			}
			// Verify if role exist
			if (dto.getRoleId() != null && dto.getRoleId() > 0){
				Role existingRole = roleRepository.findOne(dto.getRoleId(), false);
				if (existingRole == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("role roleId -> " + dto.getRoleId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setRole(existingRole);
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
			List<RoleAction> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = RoleActionRepository.saveAll((Iterable<RoleAction>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("RoleAction", locale));
				response.setHasError(true);
				return response;
			}
			List<RoleActionDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoleActionTransformer.INSTANCE.toLiteDtos(itemsSaved) : RoleActionTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update RoleAction-----");
		return response;
	}

	/**
	 * delete RoleAction by using RoleActionDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleActionDto> delete(Request<RoleActionDto> request, Locale locale)  {
		log.info("----begin delete RoleAction-----");

		Response<RoleActionDto> response = new Response<RoleActionDto>();
		List<RoleAction>        items    = new ArrayList<RoleAction>();

		for (RoleActionDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la RoleAction existe
			RoleAction existingEntity = null;
			existingEntity = RoleActionRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("RoleAction -> " + dto.getId(), locale));
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
			RoleActionRepository.saveAll((Iterable<RoleAction>) items);

			response.setHasError(false);
		}

		log.info("----end delete RoleAction-----");
		return response;
	}

	/**
	 * get RoleAction by using RoleActionDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleActionDto> getByCriteria(Request<RoleActionDto> request, Locale locale)  throws Exception {
		log.info("----begin get RoleAction-----");

		Response<RoleActionDto> response = new Response<RoleActionDto>();
		List<RoleAction> items 			 = RoleActionRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<RoleActionDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoleActionTransformer.INSTANCE.toLiteDtos(items) : RoleActionTransformer.INSTANCE.toDtos(items);

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
			response.setCount(RoleActionRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("RoleAction", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get RoleAction-----");
		return response;
	}

	/**
	 * get full RoleActionDto by using RoleAction as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private RoleActionDto getFullInfos(RoleActionDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
