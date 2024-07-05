
/*
 * Java business for entity table menus 
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

import ci.smile.system.manager.obf.dao.entity.Menus;
import ci.smile.system.manager.obf.dao.entity.Module;
import ci.smile.system.manager.obf.dao.entity.User;
import ci.smile.system.manager.obf.dao.repository.MenusRepository;
import ci.smile.system.manager.obf.dao.repository.ModuleRepository;
import ci.smile.system.manager.obf.utils.ExceptionUtils;
import ci.smile.system.manager.obf.utils.FunctionalError;
import ci.smile.system.manager.obf.utils.TechnicalError;
import ci.smile.system.manager.obf.utils.Utilities;
import ci.smile.system.manager.obf.utils.Validate;
import ci.smile.system.manager.obf.utils.contract.IBasicBusiness;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.MenusDto;
import ci.smile.system.manager.obf.utils.dto.transformer.MenusTransformer;

/**
BUSINESS for table "menus"
 * 
 * @author Geo
 *
 */
@Component
public class MenusBusiness implements IBasicBusiness<Request<MenusDto>, Response<MenusDto>> {

	private Response<MenusDto> response;
	@Autowired
	private MenusRepository menusRepository;
	@Autowired
	private ModuleRepository moduleRepository;
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

	public MenusBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create Menus by using MenusDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<MenusDto> create(Request<MenusDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Menus-----");

		Response<MenusDto> response = new Response<MenusDto>();
		List<Menus>        items    = new ArrayList<Menus>();

		for (MenusDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("libelle", dto.getLibelle());
			fieldsToVerify.put("code", dto.getCode());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if menus to insert do not exist
			Menus existingEntity = null;
			// verif unique libelle in db
			existingEntity = menusRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("menus libelle -> " + dto.getLibelle(), locale));
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
			existingEntity = menusRepository.findByCode(dto.getCode(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("menus code -> " + dto.getCode(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique code in items to save
			if (items.stream().anyMatch(a -> a.getCode().equalsIgnoreCase(dto.getCode()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" code ", locale));
				response.setHasError(true);
				return response;
			}

			// Verify if module exist
			Module existingModule = null;
			if (dto.getModuleId() != null && dto.getModuleId() > 0){
				existingModule = moduleRepository.findOne(dto.getModuleId(), false);
				if (existingModule == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("module moduleId -> " + dto.getModuleId(), locale));
					response.setHasError(true);
					return response;
				}
			}

			// Verify if menus exist
			Menus existingMenus= null;
			if (dto.getParentMenusId() != null && dto.getParentMenusId() > 0){
				existingMenus = menusRepository.findOne(dto.getParentMenusId(), false);
				if (existingMenus == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("Menus MenusId -> " + dto.getParentMenusId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			Menus entityToSave = null;
			entityToSave = MenusTransformer.INSTANCE.toEntity(dto, existingModule,existingMenus );
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			entityToSave.setIsLocked(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Menus> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = menusRepository.saveAll((Iterable<Menus>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("menus", locale));
				response.setHasError(true);
				return response;
			}
			List<MenusDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? MenusTransformer.INSTANCE.toLiteDtos(itemsSaved) : MenusTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Menus-----");
		return response;
	}

	/**
	 * update Menus by using MenusDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<MenusDto> update(Request<MenusDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Menus-----");

		Response<MenusDto> response = new Response<MenusDto>();
		List<Menus>        items    = new ArrayList<Menus>();

		for (MenusDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la menus existe
			Menus entityToSave = null;
			entityToSave = menusRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("menus id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if module exist
			if (dto.getModuleId() != null && dto.getModuleId() > 0){
				Module existingModule = moduleRepository.findOne(dto.getModuleId(), false);
				if (existingModule == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("module moduleId -> " + dto.getModuleId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setModule(existingModule);
			}

			Menus existingMenus = null;
			if (Utilities.notBlank(dto.getLibelle())) {
				existingMenus = menusRepository.findByLibelle(dto.getLibelle(), Boolean.FALSE);
				if(existingMenus != null && !existingMenus.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError.DATA_DUPLICATE("Tentavtive de duplication de :" + dto.getLibelle(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setLibelle(dto.getLibelle());
			}
			if (Utilities.notBlank(dto.getCode())) {
				existingMenus = menusRepository.findByCode(dto.getCode(), Boolean.FALSE);
				if(existingMenus != null && !existingMenus.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError.DATA_DUPLICATE("Tentavtive de duplication de :" + dto.getCode(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setCode(dto.getCode());
			}
			
			
			

			Menus existingParentMenus = null;
			if (Utilities.notBlank(dto.getParentMenusLibelle())) {
				existingParentMenus = menusRepository.findByLibelle(dto.getParentMenusLibelle(), Boolean.FALSE);
				if(existingMenus == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("parent menus libelle" + dto.getParentMenusLibelle(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setParent(existingParentMenus);
			}
			if (dto.getParentMenusId() != null && dto.getParentMenusId()>0) {
				existingParentMenus = menusRepository.findOne(dto.getParentMenusId(), Boolean.FALSE);
				if(existingMenus == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("parent menus id " + dto.getParentMenusId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setParent(existingParentMenus);
			}
			if (Utilities.notBlank(dto.getParentMenusCode())) {
				existingParentMenus = menusRepository.findByCode(dto.getParentMenusCode(), Boolean.FALSE);
				if(existingMenus == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("parent menus code " + dto.getParentMenusCode(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setParent(existingParentMenus);
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
			List<Menus> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = menusRepository.saveAll((Iterable<Menus>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("menus", locale));
				response.setHasError(true);
				return response;
			}
			List<MenusDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? MenusTransformer.INSTANCE.toLiteDtos(itemsSaved) : MenusTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Menus-----");
		return response;
	}

	/**
	 * delete Menus by using MenusDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<MenusDto> delete(Request<MenusDto> request, Locale locale)  {
		log.info("----begin delete Menus-----");

		Response<MenusDto> response = new Response<MenusDto>();
		List<Menus>        items    = new ArrayList<Menus>();

		for (MenusDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la menus existe
			Menus existingEntity = null;
			existingEntity = menusRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("menus -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// roleMenus
//			List<RoleMenusHabilitation> listOfRoleMenus = roleMenusHabilitationRepository.findByMenusId(existingEntity.getId(), false);
//			if (listOfRoleMenus != null && !listOfRoleMenus.isEmpty()){
//				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfRoleMenus.size() + ")", locale));
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
			menusRepository.saveAll((Iterable<Menus>) items);

			response.setHasError(false);
		}

		log.info("----end delete Menus-----");
		return response;
	}

	/**
	 * get Menus by using MenusDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<MenusDto> getByCriteria(Request<MenusDto> request, Locale locale)  throws Exception {
		log.info("----begin get Menus-----");

		Response<MenusDto> response = new Response<MenusDto>();
		List<Menus> items 			 = menusRepository.getByCriteria(request, em, locale);

		Integer index= request.getIndex();
		
		if (items != null && !items.isEmpty()) {
			List<MenusDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? MenusTransformer.INSTANCE.toLiteDtos(items) : MenusTransformer.INSTANCE.toDtos(items);

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
				List<MenusDto> itemsUnique = hierarchicalFormatting(itemsDto);
				if (Utilities.isNotEmpty(itemsUnique)) {
					itemsUnique.sort((e1,e2) -> e2.getId().compareTo(e1.getId()));
					final int sizeUnique = itemsUnique.size();
					List<MenusDto> itemsPaginner = Utilities.paginner(itemsUnique, index, size);
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
			response.setCount(menusRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("menus", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Menus-----");
		return response;
	}

	
	
	public static List<MenusDto> hierarchicalFormatting(List<MenusDto> itemsMenusDto) {
		boolean allDone = false;
		List<MenusDto> singletons = new ArrayList<>();
		while (!allDone) {
			allDone = true;
			List<MenusDto> productTypesWhithoutChildren = new ArrayList<>();
			for (MenusDto productType : itemsMenusDto) {
				boolean hasChildren = false;
				for (MenusDto otherProductType : itemsMenusDto) {
					if (productType != otherProductType) {
						if (otherProductType.getParentMenusId() != null && otherProductType.getParentMenusId() == productType.getId()) {
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
				itemsMenusDto.removeAll(productTypesWhithoutChildren);
				// mettre checque élément sans enfant dans son eventuel parent
				for (MenusDto productType : productTypesWhithoutChildren) {
					boolean parentFounded = false;
					for (MenusDto parent : itemsMenusDto) {
						if (parent.getId() == productType.getParentMenusId()) {
							parentFounded = true;
							List<MenusDto> children;
							if (parent.getChildren() == null || parent.getChildren().isEmpty())
								children = new ArrayList<MenusDto>();
							else
								children = parent.getChildren();
							children.add(productType);
							parent.setChildren(children);
							break;
						}
					}
					if (!parentFounded)
						singletons.add(productType);
				}
			}
		}
		return singletons;
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
	public Response<MenusDto> verrouiller(Request<MenusDto> request, Locale locale) {
	 log.info("----begin verrouiller Menus-----");

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

			MenusDto dto =request.getData();
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si le user existe
			Menus entityToSave = menusRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("user -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			entityToSave.setIsLocked(Boolean.TRUE);
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			entityToSave.setIsDeleted(false);

			Menus menusSave = menusRepository.save(entityToSave);
			if (menusSave == null) {
				response.setStatus(functionalError.SAVE_FAIL("user", locale));
				response.setHasError(true);
				return response;
			}
			response.setItems(Arrays.asList(MenusTransformer.INSTANCE.toDto(menusSave)));
			response.setHasError(false);


			log.info("----end verrouiller Menus-----");
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
	 * verrouiller Menus by using MenusDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Response<MenusDto> deverrouiller(Request<MenusDto> request, Locale locale) {
	 log.info("----begin deverrouiller Menus-----");

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

			MenusDto dto =request.getData();
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si le user existe
			Menus entityToSave = menusRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("user -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			entityToSave.setIsLocked(Boolean.FALSE);
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			entityToSave.setIsDeleted(false);

			Menus menusSave = menusRepository.save(entityToSave);
			if (menusSave == null) {
				response.setStatus(functionalError.SAVE_FAIL("user", locale));
				response.setHasError(true);
				return response;
			}
			response.setItems(Arrays.asList(MenusTransformer.INSTANCE.toDto(menusSave)));
			response.setHasError(false);
			log.info("----end deverrouiller Menus-----");
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
	 * get full MenusDto by using Menus as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private MenusDto getFullInfos(MenusDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
