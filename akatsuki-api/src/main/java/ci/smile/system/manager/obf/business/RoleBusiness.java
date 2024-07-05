
/*
 * Java business for entity table role 
 * Created on 2019-12-16 ( Time 11:38:54 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
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
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ci.smile.system.manager.obf.dao.entity.Role;
import ci.smile.system.manager.obf.dao.entity.RoleAction;
import ci.smile.system.manager.obf.dao.entity.User;
import ci.smile.system.manager.obf.dao.repository.ActionRepository;
import ci.smile.system.manager.obf.dao.repository.MenusRepository;
import ci.smile.system.manager.obf.dao.repository.ModuleRepository;
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
import ci.smile.system.manager.obf.utils.dto.ActionDto;
import ci.smile.system.manager.obf.utils.dto.RoleActionDto;
import ci.smile.system.manager.obf.utils.dto.RoleDto;
import ci.smile.system.manager.obf.utils.dto.transformer.RoleActionTransformer;
import ci.smile.system.manager.obf.utils.dto.transformer.RoleTransformer;

/**
 * BUSINESS for table "role"
 * 
 * @author Geo
 *
 */
@Component
public class RoleBusiness implements IBasicBusiness<Request<RoleDto>, Response<RoleDto>> {

	private Response<RoleDto> response;
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModuleRepository moduleRepository;


	private Logger log = LoggerFactory.getLogger(getClass());


	@Autowired
	private RoleActionRepository roleActionRepository;



	@Autowired
	private RoleActionBusiness roleActionBusiness;

	@Autowired
	private ActionBusiness 	actionBusiness;

	@Autowired
	private MenusRepository menusRepository;


	@Autowired
	private ActionRepository actionRepository;

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

	public RoleBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create Role by using RoleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@SuppressWarnings("null")
	@Override
	public Response<RoleDto> create(Request<RoleDto> request, Locale locale) throws ParseException {
		log.info("----begin create Role-----");

		Response<RoleDto> response = new Response<RoleDto>();
		List<Role> items = new ArrayList<Role>();

		for (RoleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("libelle", dto.getLibelle());
			fieldsToVerify.put("code", dto.getCode());
			fieldsToVerify.put("datasAction", dto.getDatasAction());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if role to insert do not exist
			Role existingEntity = null;
			// verif unique libelle in db
			existingEntity = roleRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("role libelle -> " + dto.getLibelle(), locale));
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
			existingEntity = roleRepository.findByCode(dto.getCode(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("role code -> " + dto.getCode(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique code in items to save
			if (items.stream().anyMatch(a -> a.getCode().equalsIgnoreCase(dto.getCode()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" code ", locale));
				response.setHasError(true);
				return response;
			}
			Role entityToSave = null;
			entityToSave = RoleTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			entityToSave.setIsLocked(Boolean.FALSE);

			items.add(entityToSave);

			Role roleToSave = roleRepository.save(entityToSave);

			List<RoleActionDto> datasRoleAction = new ArrayList<>();
			dto.getDatasAction().forEach(f->{
				RoleActionDto roleActionDto = new RoleActionDto();
				roleActionDto.setRoleId(roleToSave.getId());
				roleActionDto.setActionId(f.getId());
				datasRoleAction.add(roleActionDto);
			});

			Request<RoleActionDto> subRequest = new Request<>();
			subRequest.setDatas(datasRoleAction);
			subRequest.setUser(request.getUser());
			Response<RoleActionDto> subResponse = roleActionBusiness.create(subRequest, locale);
			if(subResponse.isHasError()){
				response.setStatus(subResponse.getStatus());
				response.setHasError(true);
				return response;
			}
		}

		if (!items.isEmpty()) {
			List<Role> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = roleRepository.saveAll((Iterable<Role>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("role", locale));
				response.setHasError(true);
				return response;
			}
			List<RoleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? RoleTransformer.INSTANCE.toLiteDtos(itemsSaved)
					: RoleTransformer.INSTANCE.toDtos(itemsSaved);

			final int size = itemsSaved.size();
			List<String> listOfError = Collections.synchronizedList(new ArrayList<String>());
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

		log.info("----end create Role-----");
		return response;
	}


	/**
	 * update Role by using RoleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleDto> update(Request<RoleDto> request, Locale locale) throws ParseException {
		log.info("----begin update Role-----");

		Response<RoleDto> response = new Response<RoleDto>();
		List<Role> items = new ArrayList<Role>();

		for (RoleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la role existe
			Role entityToSave = null;
			entityToSave = roleRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("role id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			Role existingEntity = null;
			if (Utilities.notBlank(dto.getLibelle())) {
				existingEntity = roleRepository.findByLibelle(dto.getLibelle(), Boolean.FALSE);
				if (existingEntity != null && !existingEntity.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError
							.DATA_DUPLICATE(" Tentative de duplication de libelle" + dto.getLibelle(), locale));
					response.setHasError(true);
				}
				entityToSave.setLibelle(dto.getLibelle());
			}
			if (Utilities.notBlank(dto.getCode())) {
				existingEntity = roleRepository.findByCode(dto.getCode(), Boolean.FALSE);
				if (existingEntity != null && !existingEntity.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError
							.DATA_DUPLICATE(" Tentative de duplication de code" + dto.getCode(), locale));
					response.setHasError(true);
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

			Role entitySaved = roleRepository.save(entityToSave);
			if (entitySaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("role", locale));
				response.setHasError(true);
				return response;
			}

			List<RoleAction> currents = roleActionRepository.findByRoleId(entityToSave.getId(), false);
			if (Utilities.isEmpty(currents)) {
				response.setStatus(functionalError.DATA_NOT_EXIST("Impossible de retrouver les fonctionalités liées au profil '"+entityToSave.getLibelle()+"'.", locale));
				response.setHasError(true);
				return response;
			}
			if (Utilities.isNotEmpty(dto.getDatasAction())) {
				List<RoleAction> currentsToDelete = new ArrayList<>();
				if(Utilities.isNotEmpty(currentsToDelete)) {
					Request<RoleActionDto> subRequest = new Request<>();
					subRequest.setUser(request.getUser());
					subRequest.setDatas(RoleActionTransformer.INSTANCE.toDtos(currentsToDelete));
					Response<RoleActionDto> subResponse = roleActionBusiness.delete(subRequest, locale);
					if(subResponse.isHasError()){
						response.setStatus(subResponse.getStatus());
						response.setHasError(true);
						return response;
					}
				}else{
					List<ActionDto> datasCreate = dto.getDatasAction().stream().filter(df->currents.stream().noneMatch(crf->crf.getAction().getId().equals(df.getId()))).collect(Collectors.toList());
					List<RoleAction> datasDelete = currents.stream().filter(crf->dto.getDatasAction().stream().noneMatch(df->df.getId().equals(crf.getAction().getId()))).collect(Collectors.toList());
					Request<RoleActionDto> subRequest = new Request<RoleActionDto>();
					subRequest.setUser(request.getUser());
					//Suppression des fonctionnalite du menu à supprimer
					if(Utilities.isNotEmpty(datasDelete)) {
						subRequest.setDatas(RoleActionTransformer.INSTANCE.toDtos(datasDelete));
						Response<RoleActionDto> subResponse = roleActionBusiness.delete(subRequest, locale);
						if(subResponse.isHasError()){
							response.setStatus(subResponse.getStatus());
							response.setHasError(true);
							return response;
						}
					}

					//Création des fonctionnalite du menu à créer
					if(Utilities.isNotEmpty(datasCreate)) {
						List<RoleActionDto> datasProfilFunctionality = new ArrayList<RoleActionDto>();
						datasCreate.forEach(f->{
							RoleActionDto roleFunctionalityDto = new RoleActionDto();
							roleFunctionalityDto.setRoleId(entitySaved.getId());
							roleFunctionalityDto.setActionId(f.getId());
							datasProfilFunctionality.add(roleFunctionalityDto);
							//populate(f,entitySaved.getId(), datasProfilFunctionality);
						});
						subRequest.setDatas(datasProfilFunctionality);
						Response<RoleActionDto> subResponse = roleActionBusiness.create(subRequest, locale);
						if(subResponse.isHasError()){
							response.setStatus(subResponse.getStatus());
							response.setHasError(true);
							return response;
						}
					}
				}
			}	
			items.add(entitySaved);
		}

		if (!items.isEmpty()) {
			List<Role> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = roleRepository.saveAll((Iterable<Role>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("role", locale));
				response.setHasError(true);
				return response;
			}
			List<RoleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? RoleTransformer.INSTANCE.toLiteDtos(itemsSaved)
					: RoleTransformer.INSTANCE.toDtos(itemsSaved);

			final int size = itemsSaved.size();
			List<String> listOfError = Collections.synchronizedList(new ArrayList<String>());
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

		log.info("----end update Role-----");
		return response;
	}

	
	@SuppressWarnings("unused")
	private void populate(ActionDto actionDto,Integer entitySavedId, List<RoleActionDto> datasAction) {
		RoleActionDto roleFunctionalityDto = new RoleActionDto();
		roleFunctionalityDto.setRoleId(entitySavedId);
		roleFunctionalityDto.setActionId(actionDto.getId());
		datasAction.add(roleFunctionalityDto);
		if(Utilities.isNotEmpty(actionDto.getDatasAction())) {
			actionDto.getDatasAction().stream().forEach(s->{
				populate(s, entitySavedId,datasAction);
			});
		}
	}
	
	
	/**
	 * delete Role by using RoleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleDto> delete(Request<RoleDto> request, Locale locale) {
		log.info("----begin delete Role-----");

		Response<RoleDto> response = new Response<RoleDto>();
		List<Role> items = new ArrayList<Role>();

		for (RoleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la role existe
			Role existingEntity = null;
			existingEntity = roleRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("role -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// userRole
			List<RoleAction> listOfRoleAction = roleActionRepository.findByRoleId(existingEntity.getId(), false);
			if (listOfRoleAction != null && !listOfRoleAction.isEmpty()) {
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfRoleAction.size() + ")", locale));
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
			roleRepository.saveAll((Iterable<Role>) items);

			response.setHasError(false);
		}

		log.info("----end delete Role-----");
		return response;
	}

	/**
	 * get Role by using RoleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleDto> getByCriteria(Request<RoleDto> request, Locale locale) throws Exception {
		log.info("----begin get Role-----");

		Response<RoleDto> response = new Response<RoleDto>();
		List<Role> items = roleRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<RoleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? RoleTransformer.INSTANCE.toLiteDtos(items)
					: RoleTransformer.INSTANCE.toDtos(items);

			final int size = items.size();
			List<String> listOfError = Collections.synchronizedList(new ArrayList<String>());
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
			response.setCount(roleRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("role", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Role-----");
		return response;
	}




	
	
	/**
	 * verrouiller Menus by using MenusDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Response<RoleDto> verrouiller(Request<RoleDto> request, Locale locale) {
	 log.info("----begin verrouiller Role-----");

		response = new Response<>();

		try {
			Map<String, Object> fieldsToVerifyUser = new HashMap<String, Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			List<User> items = new ArrayList<User>();

			RoleDto dto =request.getData();
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si le user existe
			Role entityToSave = roleRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("user -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			entityToSave.setIsLocked(Boolean.TRUE);
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			entityToSave.setIsDeleted(false);

			Role roleSave = roleRepository.save(entityToSave);
			if (roleSave == null) {
				response.setStatus(functionalError.SAVE_FAIL("user", locale));
				response.setHasError(true);
				return response;
			}
			response.setItems(Arrays.asList(RoleTransformer.INSTANCE.toDto(roleSave)));
			response.setHasError(false);
			log.info("----end verrouiller Role-----");
		} catch (PermissionDeniedDataAccessException e) {
			e.printStackTrace();
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				//				slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
				//						response.getStatus().getMessage());
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}
	
	
	/**
	 * verrouiller Role by using RoleDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Response<RoleDto> deverrouiller(Request<RoleDto> request, Locale locale) {
	 log.info("----begin deverrouiller Role-----");

		response = new Response<>();

		try {
			Map<String, Object> fieldsToVerifyUser = new HashMap<String, Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			List<User> items = new ArrayList<User>();

			RoleDto dto =request.getData();
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si le user existe
			Role entityToSave = roleRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("user -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			entityToSave.setIsLocked(Boolean.FALSE);
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			entityToSave.setIsDeleted(false);

			Role RoleSave = roleRepository.save(entityToSave);
			if (RoleSave == null) {
				response.setStatus(functionalError.SAVE_FAIL("user", locale));
				response.setHasError(true);
				return response;
			}
			response.setItems(Arrays.asList(RoleTransformer.INSTANCE.toDto(RoleSave)));
			response.setHasError(false);
			log.info("----end deverrouiller Role-----");
		} catch (PermissionDeniedDataAccessException e) {
			e.printStackTrace();
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				//				slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
				//						response.getStatus().getMessage());
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	


	/**
	 * get full RoleDto by using Role as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */

	public RoleDto getFullInfos(RoleDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here
		if (dto != null) {
			List<RoleAction> datasRoleAction = roleActionRepository.findByRoleId(dto.getId(), Boolean.FALSE);
			List<ActionDto> datas = new ArrayList<>();
			if(Utilities.isNotEmpty(datasRoleAction)) {
				datasRoleAction.stream().forEach(e->{
					ActionDto aDto = new ActionDto();
					aDto.setId(e.getAction().getId());
					aDto.setCode(e.getAction().getCode());
					aDto.setLibelle(e.getAction().getLibelle());
					
					aDto.setModuleId(e.getAction().getMenus().getModule().getId());
					aDto.setModuleLibelle(e.getAction().getMenus().getModule().getLibelle());
					aDto.setModuleCode(e.getAction().getMenus().getModule().getCode());
					
					aDto.setMenusId(e.getAction().getMenus().getId());
					aDto.setMenusLibelle(e.getAction().getMenus().getLibelle());
					aDto.setMenusCode(e.getAction().getMenus().getCode());					
					datas.add(aDto);
				});
			}
			dto.setDatasAction(Utilities.isNotEmpty(datas) ? datas : new ArrayList<>());

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
