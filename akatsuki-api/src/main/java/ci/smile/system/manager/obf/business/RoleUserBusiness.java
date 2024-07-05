
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

import ci.smile.system.manager.obf.dao.entity.Role;
import ci.smile.system.manager.obf.dao.entity.RoleUser;
import ci.smile.system.manager.obf.dao.entity.User;
import ci.smile.system.manager.obf.dao.repository.MenusRepository;
import ci.smile.system.manager.obf.dao.repository.RoleRepository;
import ci.smile.system.manager.obf.dao.repository.RoleUserRepository;
import ci.smile.system.manager.obf.dao.repository.UserRepository;
import ci.smile.system.manager.obf.utils.ExceptionUtils;
import ci.smile.system.manager.obf.utils.FunctionalError;
import ci.smile.system.manager.obf.utils.TechnicalError;
import ci.smile.system.manager.obf.utils.Utilities;
import ci.smile.system.manager.obf.utils.Validate;
import ci.smile.system.manager.obf.utils.contract.IBasicBusiness;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.RoleUserDto;
import ci.smile.system.manager.obf.utils.dto.transformer.RoleUserTransformer;

/**
BUSINESS for table "role_menus_habilitation"
 * 
 * @author Geo
 *
 */
@Component
public class RoleUserBusiness implements IBasicBusiness<Request<RoleUserDto>, Response<RoleUserDto>> {

	private Response<RoleUserDto> response;
	@Autowired
	private RoleUserRepository roleUserRepository;
	@Autowired
	private RoleRepository roleRepository;
	

	@Autowired
	private UserRepository userRepository;
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

	private SimpleDateFormat dateFormat;
	private SimpleDateFormat dateTimeFormat;
	private Logger log = LoggerFactory.getLogger(getClass());

	public RoleUserBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create RoleUser by using RoleUserDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleUserDto> create(Request<RoleUserDto> request, Locale locale)  throws ParseException {
		log.info("----begin create RoleUser-----");

		Response<RoleUserDto> response = new Response<RoleUserDto>();
		List<RoleUser>        items    = new ArrayList<RoleUser>();

		for (RoleUserDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("roleId", dto.getRoleId());
			fieldsToVerify.put("userId", dto.getUserId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if RoleUser to insert do not exist
			RoleUser existingEntity = null;
			// Verify if habilitation exist
			Role existingRole = null;
			if (dto.getRoleId() != null && dto.getRoleId() > 0){
				existingRole = roleRepository.findOne(dto.getRoleId(), false);
				if (existingRole == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("role roleId -> " + dto.getRoleId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			// Verify if role exist
			User existingUser = null;
			if (dto.getUserId() != null && dto.getUserId() > 0){
				existingUser = userRepository.findOne(dto.getUserId(), false);
				if (existingRole == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("user userId -> " + dto.getUserId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			
			RoleUser entityToSave = null;
			entityToSave = RoleUserTransformer.INSTANCE.toEntity(dto, existingRole, existingUser);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<RoleUser> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = roleUserRepository.saveAll((Iterable<RoleUser>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("RoleUser", locale));
				response.setHasError(true);
				return response;
			}
			List<RoleUserDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoleUserTransformer.INSTANCE.toLiteDtos(itemsSaved) : RoleUserTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create RoleUser-----");
		return response;
	}

	/**
	 * update RoleUser by using RoleUserDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleUserDto> update(Request<RoleUserDto> request, Locale locale)  throws ParseException {
		log.info("----begin update RoleUser-----");

		Response<RoleUserDto> response = new Response<RoleUserDto>();
		List<RoleUser>        items    = new ArrayList<RoleUser>();

		for (RoleUserDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la RoleUser existe
			RoleUser entityToSave = null;
			entityToSave = roleUserRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("RoleUser id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if user exist
			if (dto.getUserId() != null && dto.getUserId() > 0){
				User existingUser = userRepository.findOne(dto.getUserId(), false);
				if (existingUser == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("user userId -> " + dto.getUserId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setUser(existingUser);
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
			List<RoleUser> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = roleUserRepository.saveAll((Iterable<RoleUser>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("RoleUser", locale));
				response.setHasError(true);
				return response;
			}
			List<RoleUserDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoleUserTransformer.INSTANCE.toLiteDtos(itemsSaved) : RoleUserTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update RoleUser-----");
		return response;
	}

	/**
	 * delete RoleUser by using RoleUserDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleUserDto> delete(Request<RoleUserDto> request, Locale locale)  {
		log.info("----begin delete RoleUser-----");

		Response<RoleUserDto> response = new Response<RoleUserDto>();
		List<RoleUser>        items    = new ArrayList<RoleUser>();

		for (RoleUserDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la RoleUser existe
			RoleUser existingEntity = null;
			existingEntity = roleUserRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("RoleUser -> " + dto.getId(), locale));
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
			roleUserRepository.saveAll((Iterable<RoleUser>) items);

			response.setHasError(false);
		}

		log.info("----end delete RoleUser-----");
		return response;
	}

	/**
	 * get RoleUser by using RoleUserDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleUserDto> getByCriteria(Request<RoleUserDto> request, Locale locale)  throws Exception {
		log.info("----begin get RoleUser-----");

		Response<RoleUserDto> response = new Response<RoleUserDto>();
		List<RoleUser> items 			 = roleUserRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<RoleUserDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoleUserTransformer.INSTANCE.toLiteDtos(items) : RoleUserTransformer.INSTANCE.toDtos(items);

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
			response.setCount(roleUserRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("RoleUser", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get RoleUser-----");
		return response;
	}

	/**
	 * get full RoleUserDto by using RoleUser as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private RoleUserDto getFullInfos(RoleUserDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
