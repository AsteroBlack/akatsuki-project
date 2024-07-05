/*
 * Java business for entity table Action 
 * Created on 2019-12-19 ( Time 23:54:47 )
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

import ci.smile.system.manager.obf.dao.entity.Action;
import ci.smile.system.manager.obf.dao.entity.Menus;
import ci.smile.system.manager.obf.dao.entity.Module;
import ci.smile.system.manager.obf.dao.entity.User;
import ci.smile.system.manager.obf.dao.repository.ActionRepository;
import ci.smile.system.manager.obf.dao.repository.MenusRepository;
import ci.smile.system.manager.obf.dao.repository.ModuleRepository;
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
import ci.smile.system.manager.obf.utils.dto.transformer.ActionTransformer;

/**
BUSINESS for table "Action"
 * 
 * @author Geo
 *
 */
@Component
public class ActionBusiness implements IBasicBusiness<Request<ActionDto>, Response<ActionDto>> {

	private Response<ActionDto> response;
	@Autowired
	private ActionRepository actionRepository;
	@Autowired
	private RoleRepository roleRepository;


	@Autowired
	private ModuleRepository moduleRepository;
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


	public ActionBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create Action by using ActionDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ActionDto> create(Request<ActionDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Action-----");

		Response<ActionDto> response = new Response<ActionDto>();
		List<Action>        items    = new ArrayList<Action>();

		for (ActionDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("libelle", dto.getLibelle());
			fieldsToVerify.put("code", dto.getCode());
			fieldsToVerify.put("menusId", dto.getMenusId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if Action to insert do not exist
			Action existingEntity = null;
			// verif unique libelle in db
			existingEntity = actionRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("Action libelle -> " + dto.getLibelle(), locale));
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
			existingEntity = actionRepository.findByCode(dto.getCode(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("Action code -> " + dto.getCode(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique code in items to save
			if (items.stream().anyMatch(a -> a.getCode().equalsIgnoreCase(dto.getCode()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" code ", locale));
				response.setHasError(true);
				return response;
			}

			// Verify if role exist
			Menus existingMenus = null;
			if (dto.getMenusId() != null && dto.getMenusId() > 0){
				existingMenus = menusRepository.findOne(dto.getMenusId(), false);
				if (existingMenus == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("menus menusId -> " + dto.getMenusId(), locale));
					response.setHasError(true);
					return response;
				}
			}

			// Verify if action exist
			Action existingAction = null;
			if (dto.getParentActionId() != null && dto.getParentActionId() > 0){
				existingAction = actionRepository.findOne(dto.getParentActionId(), false);
				if (existingAction == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("Action actionId -> " + dto.getParentActionId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			Action entityToSave = null;
			entityToSave = ActionTransformer.INSTANCE.toEntity(dto, existingMenus, existingAction);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			entityToSave.setIsLocked(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Action> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = actionRepository.saveAll((Iterable<Action>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("Action", locale));
				response.setHasError(true);
				return response;
			}
			List<ActionDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ActionTransformer.INSTANCE.toLiteDtos(itemsSaved) : ActionTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Action-----");
		return response;
	}

	/**
	 * update Action by using ActionDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ActionDto> update(Request<ActionDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Action-----");

		Response<ActionDto> response = new Response<ActionDto>();
		List<Action>        items    = new ArrayList<Action>();

		for (ActionDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la Action existe
			Action entityToSave = null;
			entityToSave = actionRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("Action id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if module exist
			if (dto.getMenusId() != null && dto.getMenusId() > 0){
				Menus existingMenus = menusRepository.findOne(dto.getMenusId(), false);
				if (existingMenus == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("menus menusId -> " + dto.getMenusId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setMenus(existingMenus);
			}

			Action existingAction = null;
			if (Utilities.notBlank(dto.getLibelle())) {
				existingAction = actionRepository.findByLibelle(dto.getLibelle(), Boolean.FALSE);
				if(existingAction != null && !existingAction.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError.DATA_DUPLICATE("Tentavtive de duplication de :" + dto.getLibelle(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setLibelle(dto.getLibelle());
			}
			if (Utilities.notBlank(dto.getCode())) {
				existingAction = actionRepository.findByCode(dto.getCode(), Boolean.FALSE);
				if(existingAction != null && !existingAction.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError.DATA_DUPLICATE("Tentavtive de duplication de :" + dto.getCode(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setCode(dto.getCode());
			}			

			if(dto.getParentActionId() !=null && dto.getParentActionId() >0) {
				Action action = actionRepository.findOne(dto.getParentActionId(), Boolean.FALSE);
				if(action == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("parent Action Id" + dto.getParentActionId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setParent(action);
			}
			if(Utilities.notBlank(dto.getRoleCode())) {
				Action action = actionRepository.findByCode(dto.getParentActionCode(), Boolean.FALSE);
				if(action == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("parent Action code" + dto.getParentActionCode(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setParent(action);
			}

			if(Utilities.notBlank(dto.getParentActionLibelle())) {
				Action action = actionRepository.findByLibelle(dto.getParentActionLibelle(), Boolean.FALSE);
				if(action == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("parent Action libelle" + dto.getParentActionLibelle(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setParent(action);
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
			List<Action> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = actionRepository.saveAll((Iterable<Action>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("Action", locale));
				response.setHasError(true);
				return response;
			}
			List<ActionDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ActionTransformer.INSTANCE.toLiteDtos(itemsSaved) : ActionTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Action-----");
		return response;
	}

	/**
	 * delete Action by using ActionDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ActionDto> delete(Request<ActionDto> request, Locale locale)  {
		log.info("----begin delete Action-----");

		Response<ActionDto> response = new Response<ActionDto>();
		List<Action>        items    = new ArrayList<Action>();

		for (ActionDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la Action existe
			Action existingEntity = null;
			existingEntity = actionRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("Action -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// roleAction
			//			List<Role> listOfRoleAction = roleActionHabilitationRepository.findByActionId(existingEntity.getId(), false);
			//			if (listOfRoleAction != null && !listOfRoleAction.isEmpty()){
			//				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfRoleAction.size() + ")", locale));
			//				response.setHasError(true);
			//				return response;
			//			}


			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setDeletedBy(request.getUser());
			existingEntity.setIsDeleted(true);
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			actionRepository.saveAll((Iterable<Action>) items);

			response.setHasError(false);
		}

		log.info("----end delete Action-----");
		return response;
	}

	/**
	 * get Action by using ActionDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ActionDto> getByCriteria(Request<ActionDto> request, Locale locale)  throws Exception {
		log.info("----begin get Action-----");

		Response<ActionDto> response = new Response<ActionDto>();
		List<Action> items 			 = actionRepository.getByCriteria(request, em, locale);

		Integer index= request.getIndex();

		if (items != null && !items.isEmpty()) {
			List<ActionDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ActionTransformer.INSTANCE.toLiteDtos(items) : ActionTransformer.INSTANCE.toDtos(items);

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

			if (request.getHierarchyFormat() != null && request.getHierarchyFormat()) {
				List<ActionDto> itemsUnique = hierarchicalFormatting(itemsDto);
				if (Utilities.isNotEmpty(itemsUnique)) {

					itemsUnique.sort((e1,e2) -> e2.getId().compareTo(e1.getId()));

					final int sizeUnique = itemsUnique.size();
					List<ActionDto> itemsPaginner = Utilities.paginner(itemsUnique, index, size);
					response.setItems(itemsPaginner);
					response.setCount((long)sizeUnique);
					response.setHasError(false);
					return response;
				}else {
					response.setStatus(functionalError.DATA_EMPTY("famille", locale));
					response.setHasError(false);
					return response;
				}
			}

			response.setItems(itemsDto);
			response.setCount(actionRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("Action", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Action-----");
		return response;
	}



	public static List<ActionDto> hierarchicalFormatting(List<ActionDto> itemsActionDto) {
		boolean allDone = false;
		List<ActionDto> singletons = new ArrayList<ActionDto>();
		while (!allDone) {
			allDone = true;
			List<ActionDto> productTypesWhithoutChildren = new ArrayList<ActionDto>();
			for (ActionDto productType : itemsActionDto) {
				boolean hasChildren = false;
				for (ActionDto otherProductType : itemsActionDto) {
					if (productType != otherProductType) {
						if (otherProductType.getParentActionId() != null && otherProductType.getParentActionId() == productType.getId()) {
							hasChildren = true;
							allDone = false;
							break;
						}
					}
				}
				if (!hasChildren) {
					productTypesWhithoutChildren.add(productType);
				}
			}
			if (!productTypesWhithoutChildren.isEmpty()) {
				itemsActionDto.removeAll(productTypesWhithoutChildren);
				// mettre checque élément sans enfant dans son eventuel parent
				for (ActionDto productType : productTypesWhithoutChildren) {
					boolean parentFounded = false;
					for (ActionDto parent : itemsActionDto) {
						if (parent.getId() == productType.getParentActionId()) {
							parentFounded = true;
							List<ActionDto> children;
							if (parent.getChildren() == null || parent.getChildren().isEmpty()) {
								children = new ArrayList<ActionDto>();
							}else {
								children = parent.getChildren();
							}
							children.add(productType);
							parent.setChildren(children);
							break;
						}
					}
					if (!parentFounded) {
						singletons.add(productType);
					}
				}
			}
		}
		return singletons;
	}



	/**
	 * verrouiller action by using actionDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Response<ActionDto> verrouiller(Request<ActionDto> request, Locale locale) {
		log.info("----begin verrouiller action-----");

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

			ActionDto dto =request.getData();
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si le user existe
			Action entityToSave = actionRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("user -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// entityToSave.setStatut(existingStatut);
			entityToSave.setIsLocked(Boolean.TRUE);
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			entityToSave.setIsDeleted(false);

			Action actionSave = actionRepository.save(entityToSave);
			if (actionSave == null) {
				response.setStatus(functionalError.SAVE_FAIL("user", locale));
				response.setHasError(true);
				return response;
			}


			response.setItems(Arrays.asList(ActionTransformer.INSTANCE.toDto(actionSave)));
			response.setHasError(false);


			log.info("----end update actionSave-----");
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
	 * verrouiller action by using actionDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Response<ActionDto> deverrouiller(Request<ActionDto> request, Locale locale) {
		log.info("----begin deverrouiller action-----");

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

			ActionDto dto =request.getData();
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si le user existe
			Action entityToSave = actionRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("user -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			entityToSave.setIsLocked(Boolean.FALSE);
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			entityToSave.setIsDeleted(false);

			Action actionSave = actionRepository.save(entityToSave);
			if (actionSave == null) {
				response.setStatus(functionalError.SAVE_FAIL("user", locale));
				response.setHasError(true);
				return response;
			}
			response.setItems(Arrays.asList(ActionTransformer.INSTANCE.toDto(actionSave)));
			response.setHasError(false);
			log.info("----end deverrouiller action-----");
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




	public Response<ActionDto> getAll(Request<ActionDto> request, Locale locale)  throws Exception {
		log.info("----begin getAll Action-----");
		Response<ActionDto> response = new Response<ActionDto>();
		try{
			request.getData().setIsLocked(Boolean.FALSE);
			List<Action> items 			 = actionRepository.getByCriteria(request, em, locale);
			if (items != null && !items.isEmpty()) {
				List<ActionDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ActionTransformer.INSTANCE.toLiteDtos(items) : ActionTransformer.INSTANCE.toDtos(items);

				List<ActionDto> datas = new ArrayList<>();
				if (request.getHierarchyFormat() != null && request.getHierarchyFormat()) {
					List<ActionDto> itemsUnique = hierarchicalFormatting(itemsDto);
					if (Utilities.isNotEmpty(itemsUnique)) {

						itemsUnique.sort((e1,e2) -> e2.getId().compareTo(e1.getId()));
						List<ActionDto> itemsModuleDto = new ArrayList<>();

						Map<String, List<ActionDto>> groupMenus = itemsUnique.stream().filter(m -> m.getMenusCode() != null && !m.getMenusCode().isEmpty()).collect(Collectors.groupingBy(ActionDto::getMenusCode));
						groupMenus.entrySet().stream().forEach(n -> {
							ActionDto acDto = new ActionDto();
							acDto.setMenusCode(n.getKey());
							Menus menus = menusRepository.findByCode(n.getKey(), Boolean.FALSE);
							if(menus != null && !menus.getIsLocked()) {
								acDto.setMenusLibelle(menus.getLibelle());
								acDto.setMenusId(menus.getId());
								acDto.setModuleLibelle(menus.getModule().getLibelle());
								acDto.setModuleCode(menus.getModule().getCode());
								acDto.setModuleId(menus.getModule().getId());
							}
							acDto.setDatasAction(n.getValue());
							datas.add(acDto);
						});
						if(Utilities.isNotEmpty(datas)) {
							Map<String, List<ActionDto>> groupModule = datas.stream().filter(m -> m.getModuleCode() != null && !m.getModuleCode().isEmpty()).collect(Collectors.groupingBy(ActionDto::getModuleCode));
							groupModule.entrySet().stream().forEach(c -> {
								ActionDto actionDto = new ActionDto();
								Module module = moduleRepository.findByCode(c.getKey(), Boolean.FALSE);
								if(module != null) {
									actionDto.setModuleId(module.getId());
								}
								actionDto.setModuleCode(c.getKey());
								actionDto.setText(c.getKey());
								actionDto.setDatasMenus(c.getValue());
								itemsModuleDto.add(actionDto);
							});
						}
						response.setItems(itemsModuleDto);
						response.setCount((long)itemsModuleDto.size());
						response.setHasError(false);
						return response;
					}else {
						response.setStatus(functionalError.DATA_EMPTY("famille", locale));
						response.setHasError(false);
						return response;
					}
				}
			}
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

		log.info("----end get Action-----");
		return response;
	}






	/**
	 * get full ActionDto by using Action as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private ActionDto getFullInfos(ActionDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here
		if(dto != null) {
			Menus menus = menusRepository.findOne(dto.getMenusId(), Boolean.FALSE);
			if(menus != null && menus.getModule() != null) {
				dto.setModuleId(menus.getModule().getId());
				dto.setModuleLibelle(menus.getModule().getLibelle());
				dto.setModuleCode(menus.getModule().getCode());
			}
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
