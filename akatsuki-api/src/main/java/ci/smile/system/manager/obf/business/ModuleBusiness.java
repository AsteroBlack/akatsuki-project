
/*
 * Java business for entity table module 
 * Created on 2019-12-19 ( Time 23:54:47 )
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

import ci.smile.system.manager.obf.dao.entity.Menus;
import ci.smile.system.manager.obf.dao.entity.Module;
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
import ci.smile.system.manager.obf.utils.dto.ModuleDto;
import ci.smile.system.manager.obf.utils.dto.transformer.ModuleTransformer;

/**
BUSINESS for table "module"
 * 
 * @author Geo
 *
 */
@Component
public class ModuleBusiness implements IBasicBusiness<Request<ModuleDto>, Response<ModuleDto>> {

	private Response<ModuleDto> response;
	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private MenusRepository menusRepository;
	@Autowired
	private MenusBusiness menusBusiness;
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

	public ModuleBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create Module by using ModuleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ModuleDto> create(Request<ModuleDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Module-----");

		Response<ModuleDto> response = new Response<ModuleDto>();
		List<Module>        items    = new ArrayList<Module>();

		for (ModuleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("libelle", dto.getLibelle());
			fieldsToVerify.put("code", dto.getCode());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if module to insert do not exist
			Module existingEntity = null;

			// verif unique libelle in db
			existingEntity = moduleRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("module libelle -> " + dto.getLibelle(), locale));
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
			existingEntity = moduleRepository.findByCode(dto.getCode(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("module code -> " + dto.getCode(), locale));
				response.setHasError(true);
				return response;
			}



			// verif unique code in items to save
			if (items.stream().anyMatch(a -> a.getCode().equalsIgnoreCase(dto.getCode()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" code ", locale));
				response.setHasError(true);
				return response;
			}

			Module entityToSave = null;
			entityToSave = ModuleTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Module> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = moduleRepository.saveAll((Iterable<Module>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("module", locale));
				response.setHasError(true);
				return response;
			}
			List<ModuleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ModuleTransformer.INSTANCE.toLiteDtos(itemsSaved) : ModuleTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Module-----");
		return response;
	}

	/**
	 * update Module by using ModuleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ModuleDto> update(Request<ModuleDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Module-----");

		Response<ModuleDto> response = new Response<ModuleDto>();
		List<Module>        items    = new ArrayList<Module>();

		for (ModuleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la module existe
			Module entityToSave = null;
			entityToSave = moduleRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("module id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			Integer moduleId = entityToSave.getId();
			Module existingModule = null;
			if (Utilities.notBlank(dto.getLibelle())) {
				existingModule = moduleRepository.findByLibelle(dto.getLibelle(), Boolean.FALSE);
				if(existingModule != null && !existingModule.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError.DATA_DUPLICATE("tentative de duplication de " + dto.getLibelle(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setLibelle(dto.getLibelle());
			}
			if (Utilities.notBlank(dto.getCode())) {
				existingModule = moduleRepository.findByCode(dto.getCode(), Boolean.FALSE);
				if(existingModule != null && !existingModule.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError.DATA_DUPLICATE("tentative de duplication de " + dto.getCode(), locale));
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
			List<Module> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = moduleRepository.saveAll((Iterable<Module>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("module", locale));
				response.setHasError(true);
				return response;
			}
			List<ModuleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ModuleTransformer.INSTANCE.toLiteDtos(itemsSaved) : ModuleTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Module-----");
		return response;
	}

	/**
	 * delete Module by using ModuleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ModuleDto> delete(Request<ModuleDto> request, Locale locale)  {
		log.info("----begin delete Module-----");

		Response<ModuleDto> response = new Response<ModuleDto>();
		List<Module>        items    = new ArrayList<Module>();

		for (ModuleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la module existe
			Module existingEntity = null;
			existingEntity = moduleRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("module -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// role
//			List<Role> listOfRole = roleRepository.findByModuleId(existingEntity.getId(), false);
//			if (listOfRole != null && !listOfRole.isEmpty()){
//				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfRole.size() + ")", locale));
//				response.setHasError(true);
//				return response;
//			}
			// menus
			List<Menus> listOfMenus = menusRepository.findByModuleId(existingEntity.getId(), false);
			if (listOfMenus != null && !listOfMenus.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfMenus.size() + ")", locale));
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
			moduleRepository.saveAll((Iterable<Module>) items);

			response.setHasError(false);
		}

		log.info("----end delete Module-----");
		return response;
	}



//	@SuppressWarnings("unused")
//	private void populateListMenu(List<MenusDto>  datasMenus, Module module, List<MenusDto>  datasToSave) throws Exception {
//		try {
//			if (Utilities.isNotEmpty(datasMenus)) {
//				datasMenus.stream().forEach(s -> {
//					MenusDto menusDto = new MenusDto();
//					menusDto.setModuleId(module.getId());
//					menusDto.setId(s.getId());
//					datasToSave.add(menusDto);
//					if(Utilities.isNotEmpty(s.getDatasMenu())) {
//						try {
//							populateListMenu(s.getDatasMenu(), module, datasToSave);
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							log.info("error recursive --------> :"+e.getMessage()); 
//							e.printStackTrace();
//						}
//					}
//				}); 
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			log.info("error --------> :"+e.getMessage());
//			e.printStackTrace();
//		}
//
//	}



	/**
	 * get Module by using ModuleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ModuleDto> getByCriteria(Request<ModuleDto> request, Locale locale)  throws Exception {
		log.info("----begin get Module-----");

		Response<ModuleDto> response = new Response<ModuleDto>();
		List<Module> items 			 = moduleRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<ModuleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ModuleTransformer.INSTANCE.toLiteDtos(items) : ModuleTransformer.INSTANCE.toDtos(items);

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
			response.setCount(moduleRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("module", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Module-----");
		return response;
	}

	/**
	 * get full ModuleDto by using Module as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private ModuleDto getFullInfos(ModuleDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
