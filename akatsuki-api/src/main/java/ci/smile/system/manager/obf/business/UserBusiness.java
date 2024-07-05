
/*
 * Java business for entity table user 
 * Created on 2019-12-16 ( Time 11:38:54 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.business;

import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;

import ci.smile.system.manager.obf.dao.entity.Action;
import ci.smile.system.manager.obf.dao.entity.Menus;
import ci.smile.system.manager.obf.dao.entity.Module;
import ci.smile.system.manager.obf.dao.entity.RoleAction;
import ci.smile.system.manager.obf.dao.entity.RoleUser;
import ci.smile.system.manager.obf.dao.entity.User;
import ci.smile.system.manager.obf.dao.repository.MenusRepository;
import ci.smile.system.manager.obf.dao.repository.ModuleRepository;
import ci.smile.system.manager.obf.dao.repository.RoleActionRepository;
import ci.smile.system.manager.obf.dao.repository.RoleUserRepository;
import ci.smile.system.manager.obf.dao.repository.UserRepository;
import ci.smile.system.manager.obf.utils.ExceptionUtils;
import ci.smile.system.manager.obf.utils.FunctionalError;
import ci.smile.system.manager.obf.utils.HostingUtils;
import ci.smile.system.manager.obf.utils.ParamsUtils;
import ci.smile.system.manager.obf.utils.TechnicalError;
import ci.smile.system.manager.obf.utils.Utilities;
import ci.smile.system.manager.obf.utils.Validate;
import ci.smile.system.manager.obf.utils.contract.IBasicBusiness;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.contract.SearchParam;
import ci.smile.system.manager.obf.utils.dto.ActionDto;
import ci.smile.system.manager.obf.utils.dto.RoleDto;
import ci.smile.system.manager.obf.utils.dto.RoleUserDto;
import ci.smile.system.manager.obf.utils.dto.UserDto;
import ci.smile.system.manager.obf.utils.dto.transformer.RoleUserTransformer;
import ci.smile.system.manager.obf.utils.dto.transformer.UserTransformer;
import ci.smile.system.manager.obf.utils.properties.SmtpProperties;

/**
 * BUSINESS for table "user"
 * 
 * @author Geo
 *
 */
@Component
public class UserBusiness implements IBasicBusiness<Request<UserDto>, Response<UserDto>> {

	private Response<UserDto> response;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleBusiness roleBusiness;
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private RoleActionRepository roleActionRepository;

	@Autowired
	private RoleUserRepository roleUserRepository;

	@Autowired
	private RoleUserBusiness roleUserBusiness;


	@Autowired
	private FunctionalError functionalError;

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private MenusRepository menusRepository;


	@Autowired
	private HostingUtils hostingUtils;

	@Autowired
	private SmtpProperties smtpProperties;

	@Autowired
	private TechnicalError technicalError;
	@Autowired
	private ExceptionUtils exceptionUtils;
	@PersistenceContext
	private EntityManager em;

	private Context context;

	private Logger slf4jLogger;

	@Autowired
	private ParamsUtils paramsUtils;

	private SimpleDateFormat dateFormat;
	private SimpleDateFormat dateTimeFormat;

	public UserBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		slf4jLogger = LoggerFactory.getLogger(getClass());
	}

	/**
	 * create User by using UserDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UserDto> create(Request<UserDto> request, Locale locale) throws ParseException {
		log.info("----begin create User-----");

		Response<UserDto> response = new Response<UserDto>();
		List<User> items = new ArrayList<User>();

		for (UserDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("nom", dto.getNom());
			fieldsToVerify.put("prenom", dto.getPrenom());
			fieldsToVerify.put("email", dto.getEmail());
			fieldsToVerify.put("login", dto.getLogin());
			fieldsToVerify.put("contact", dto.getContact());
			fieldsToVerify.put("datasRole", dto.getDatasRole());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			// Verify if user to insert do not exist
			User existingEntity = null;
			// verif unique matricule in db
			existingEntity = userRepository.findByMatricule(dto.getMatricule(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("user matricule -> " + dto.getMatricule(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique matricule in items to save
			if (items.stream().anyMatch(a -> a.getMatricule().equalsIgnoreCase(dto.getMatricule()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" matricule ", locale));
				response.setHasError(true);
				return response;
			}

			// verif unique email in db
			existingEntity = userRepository.findByEmail(dto.getEmail(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("user email -> " + dto.getEmail(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique email in items to save
			if (items.stream().anyMatch(a -> a.getEmail().equalsIgnoreCase(dto.getEmail()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" email ", locale));
				response.setHasError(true);
				return response;
			}

			// verif unique login in db
			existingEntity = userRepository.findByLogin(dto.getLogin(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("user login -> " + dto.getLogin(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique login in items to save
			if (items.stream().anyMatch(a -> a.getLogin().equalsIgnoreCase(dto.getLogin()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" login ", locale));
				response.setHasError(true);
				return response;
			}

			User entityToSave = null;
			entityToSave = UserTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			if(Utilities.notBlank(dto.getPassword())) {
				try {
					entityToSave.setPassword(Utilities.encrypt(dto.getPassword()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			entityToSave.setIsLocked(Boolean.FALSE);
			items.add(entityToSave);
			User entitySaved = userRepository.save(entityToSave);
			if (entitySaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("profil", locale));
				response.setHasError(true);
				return response;
			}

			List<RoleUserDto>  datasRoleUser = new ArrayList<>();
			System.out.println(dto.getDatasRole());
			dto.getDatasRole().forEach(f -> {
				RoleUserDto roleUserDto = new RoleUserDto();
				roleUserDto.setUserId(entitySaved.getId());
				roleUserDto.setRoleId(f.getId());
				datasRoleUser.add(roleUserDto);
			});
			

			Request<RoleUserDto> subRequest = new Request<>();
			subRequest.setDatas(datasRoleUser);
			subRequest.setUser(request.getUser());
			Response<RoleUserDto> subResponse = roleUserBusiness.create(subRequest, locale);
			if (subResponse.isHasError()) {
				response.setStatus(subResponse.getStatus());
				response.setHasError(true);
				return response;
			}

		}

		if (!items.isEmpty()) {
			List<User> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = userRepository.saveAll((Iterable<User>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("user", locale));
				response.setHasError(true);
				return response;
			}
			List<UserDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? UserTransformer.INSTANCE.toLiteDtos(itemsSaved)
							: UserTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create User-----");
		return response;
	}

	/**
	 * update User by using UserDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UserDto> update(Request<UserDto> request, Locale locale) throws ParseException {
		log.info("----begin update User-----");

		Response<UserDto> response = new Response<UserDto>();
		List<User> items = new ArrayList<User>();

		for (UserDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la user existe
			User entityToSave = null;
			entityToSave = userRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("user id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.notBlank(dto.getMatricule())) {
				entityToSave.setMatricule(dto.getMatricule());
			}
			if (Utilities.notBlank(dto.getNom())) {
				entityToSave.setNom(dto.getNom());
			}
			if (Utilities.notBlank(dto.getPrenom())) {
				entityToSave.setPrenom(dto.getPrenom());
			}
			if (Utilities.notBlank(dto.getEmail())) {
				entityToSave.setEmail(dto.getEmail());
			}
			if (Utilities.notBlank(dto.getLogin())) {
				entityToSave.setLogin(dto.getLogin());
			}
			if (Utilities.notBlank(dto.getPassword())) {
				try {
					entityToSave.setPassword(Utilities.encrypt(dto.getPassword()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (Utilities.notBlank(dto.getContact())) {
				entityToSave.setContact(dto.getContact());
			}
			if (dto.getIsSuperAdmin() != null) {
				entityToSave.setIsSuperAdmin(dto.getIsSuperAdmin());
			}
			if (dto.getIsAutorize() != null) {
				entityToSave.setIsAutorize(dto.getIsAutorize());
			}
			if (dto.getIsDefaultPassword() != null) {
				entityToSave.setIsDefaultPassword(dto.getIsDefaultPassword());
			}
			if (dto.getIsLocked() != null) {
				entityToSave.setIsLocked(dto.getIsLocked());
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
			User entitySaved = userRepository.save(entityToSave);
			if (entitySaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("profil", locale));
				response.setHasError(true);
				return response;
			}
			items.add(entitySaved);

			// List<RoleUser> currents =
			// roleUserRepository.findByUserId(entityToSave.getId(), false);
			// if (Utilities.isEmpty(currents)) {
			// response.setStatus(functionalError.DATA_NOT_EXIST("Impossible de retrouver
			// les roles liées a l'utilisateur "+entityToSave.getLogin(), locale));
			// response.setHasError(true);
			// return response;
			// }
			if (Utilities.isNotEmpty(dto.getDatasRole())) {
				List<RoleUser> currentsToDelete = new ArrayList<>();
				if (Utilities.isNotEmpty(currentsToDelete)) {
					Request<RoleUserDto> subRequest = new Request<>();
					subRequest.setUser(request.getUser());
					subRequest.setDatas(RoleUserTransformer.INSTANCE.toDtos(currentsToDelete));
					Response<RoleUserDto> subResponse = roleUserBusiness.delete(subRequest, locale);
					if (subResponse.isHasError()) {
						response.setStatus(subResponse.getStatus());
						response.setHasError(true);
						return response;
					}
				} else {
					List<RoleUser> currents = roleUserRepository.findByUserId(entityToSave.getId(), false);
					if (Utilities.isEmpty(currents)) {
						response.setStatus(functionalError.DATA_NOT_EXIST(
								"Impossible de retrouver les roles liées a l'utilisateur " + entityToSave.getLogin(),
								locale));
						response.setHasError(true);
						return response;
					}
					List<RoleDto> datasCreate = dto.getDatasRole().stream()
							.filter(df -> currents.stream().noneMatch(crf -> crf.getRole().getId().equals(df.getId())))
							.collect(Collectors.toList());
					List<RoleUser> datasDelete = currents.stream()
							.filter(crf -> dto.getDatasRole().stream()
									.noneMatch(df -> df.getId().equals(crf.getRole().getId())))
							.collect(Collectors.toList());
					Request<RoleUserDto> subRequest = new Request<RoleUserDto>();
					subRequest.setUser(request.getUser());
					// Suppression des roles de l'utilsateur à supprimer
					if (Utilities.isNotEmpty(datasDelete)) {
						subRequest.setDatas(RoleUserTransformer.INSTANCE.toDtos(datasDelete));
						Response<RoleUserDto> subResponse = roleUserBusiness.delete(subRequest, locale);
						if (subResponse.isHasError()) {
							response.setStatus(subResponse.getStatus());
							response.setHasError(true);
							return response;
						}
					}
					// Création des roles de l'utilisateur à créer
					if (Utilities.isNotEmpty(datasCreate)) {
						List<RoleUserDto> datasRoleUser = new ArrayList<RoleUserDto>();
						datasCreate.forEach(f -> {
							RoleUserDto roleUserDto = new RoleUserDto();
							roleUserDto.setUserId(entitySaved.getId());
							roleUserDto.setRoleId(f.getId());
							datasRoleUser.add(roleUserDto);
						});
						subRequest.setDatas(datasRoleUser);
						Response<RoleUserDto> subResponse = roleUserBusiness.create(subRequest, locale);
						if (subResponse.isHasError()) {
							response.setStatus(subResponse.getStatus());
							response.setHasError(true);
							return response;
						}
					}
				}
			}
		}

		if (!items.isEmpty()) {
			List<User> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = userRepository.saveAll((Iterable<User>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("user", locale));
				response.setHasError(true);
				return response;
			}
			List<UserDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? UserTransformer.INSTANCE.toLiteDtos(itemsSaved)
							: UserTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update User-----");
		return response;
	}

	/**
	 * delete User by using UserDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UserDto> delete(Request<UserDto> request, Locale locale) {
		log.info("----begin delete User-----");

		Response<UserDto> response = new Response<UserDto>();
		List<User> items = new ArrayList<User>();

		for (UserDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la user existe
			User existingEntity = null;
			existingEntity = userRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("user -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// userRole
//			List<UserRoleModule> listOfUserRole = userRoleModuleRepository.findByUserId(existingEntity.getId(), false);
//			if (listOfUserRole != null && !listOfUserRole.isEmpty()) {
//				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfUserRole.size() + ")", locale));
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
			userRepository.saveAll((Iterable<User>) items);

			response.setHasError(false);
		}

		log.info("----end delete User-----");
		return response;
	}

	/**
	 * get User by using UserDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UserDto> getByCriteria(Request<UserDto> request, Locale locale) throws Exception {
		log.info("----begin get User-----");

		Response<UserDto> response = new Response<UserDto>();
		List<User> items = userRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<UserDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? UserTransformer.INSTANCE.toLiteDtos(items)
							: UserTransformer.INSTANCE.toDtos(items);

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
					response.setCount(userRepository.count(request, em, locale));
					response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("user", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get User-----");
		return response;
	}

	/**
	 * deconnexion User by using UserDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Response<UserDto> deconnexion(Request<UserDto> request, Locale locale) {
		log.info("----begin deconnexion User-----");

		response = new Response<UserDto>();

		try {
			UserDto dto = request.getData();
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if user to authenc do not exist
			User existingEntity = userRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("User -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			response.setStatus(functionalError.SUCCESS("", locale));
			response.setHasError(false);

			log.info("----end deconnexion User-----");
		} catch (PermissionDeniedDataAccessException e) {
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
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	/**
	 * changerPassword User by using UserDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Response<UserDto> changerMotDePasse(Request<UserDto> request, Locale locale) {
		log.info("----begin changerMotDePasse-----");

		response = new Response<UserDto>();

		Map<String, Object> fieldsToVerifyUser = new HashMap<String, Object>();
		fieldsToVerifyUser.put("user", request.getUser());
		if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
			response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
			response.setHasError(true);
			return response;
		}
		try {

			UserDto dto = request.getData();
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("login", dto.getLogin());
			fieldsToVerify.put("password", dto.getPassword());
			fieldsToVerify.put("newPassword", dto.getNewPassword());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if user to authenc do not exist
			User existingEntity = userRepository.findByPasswordAndLogin(Utilities.encrypt(dto.getPassword()),
					dto.getLogin(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.LOGIN_FAIL(locale));
				response.setHasError(true);
				return response;
			}

			// verifier que le user connecter correspond à celui qui veut changer le
			// password
			if (!existingEntity.getId().equals(request.getUser())) {
				response.setStatus(functionalError
						.AUTH_FAIL("Vous ne pouvez pas changer le mot de passe d'un autre utilisateur.", locale));
				response.setHasError(true);
				return response;
			}

			existingEntity.setPassword(Utilities.encrypt(dto.getNewPassword()));
			existingEntity.setIsDefaultPassword(Boolean.TRUE);
			existingEntity = userRepository.save(existingEntity);

			response.setStatus(functionalError.SUCCESS("", locale));
			response.setHasError(false);

			log.info("----end changerMotDePasse-----");
		} catch (PermissionDeniedDataAccessException e) {
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
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	/**
	 * motDePasseOublie User by using UserDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Response<UserDto> motDePasseOublie(Request<UserDto> request, Locale locale) {
		log.info("----begin motDePasseOublie-----");

		response = new Response<UserDto>();

		try {

			UserDto dto = request.getData();
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			// fieldsToVerify.put("login", dto.getLogin());
			fieldsToVerify.put("email", dto.getEmail());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if user to insert do not exist
			User entityToSave = userRepository.findByEmail(dto.getEmail(), false);
			if (entityToSave == null) {
				response.setStatus(
						functionalError.DATA_NOT_EXIST("user-> " + dto.getEmail() + " n'existe pas.", locale));
				response.setHasError(true);
				return response;
			}
			// gener un login et verification de son existence en base
			String login = entityToSave.getLogin();
			String mdp = Utilities.generateAlphanumericCode(5);
			entityToSave.setPassword(Utilities.encrypt(mdp));
			entityToSave.setIsDefaultPassword(Boolean.TRUE);

			// --------------------------------------
			// SEND PASSWORD VIA MAIL
			// --------------------------------------
			// set mail to user
			Map<String, String> from = new HashMap<>();
			from.put("email", smtpProperties.getLogin());
			from.put("user", "SUNSHINE");
			List<Map<String, String>> toRecipients = new ArrayList<Map<String, String>>();
			Map<String, String> recipient = new HashMap<String, String>();
			recipient = new HashMap<String, String>();
			recipient.put("email", dto.getEmail());
			toRecipients.add(recipient);
			// choisir la vraie url
			// String appLink = paramsUtils.getUrlAccueil();

			// subject
			String subject = "Paramètres de réinitialisation du mot de passe !";
			String body = "";
			context = new Context();
			String template = "mail_new_mdp";

			context.setVariable("login", login);
			context.setVariable("password", mdp);
			// context.setVariable("appLink", appLink);
			Response<UserDto> responseEnvoiEmail = new Response<>();
			responseEnvoiEmail = hostingUtils.sendEmail(from, toRecipients, subject, body, null, context, template,
					locale);
			if (responseEnvoiEmail != null && responseEnvoiEmail.isHasError()) {
				response.setStatus(responseEnvoiEmail.getStatus());
				response.setHasError(true);
				return response;
			}
			log.info("End sending mail to user");
			// ----END----------------------------------

			// save le new login and mdp
			userRepository.save(entityToSave);

			response.setStatus(functionalError.SUCCESS("", locale));
			response.setHasError(false);

			log.info("----end motDePasseOublie -----");
		} catch (PermissionDeniedDataAccessException e) {
			e.printStackTrace();
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			e.printStackTrace();
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			e.printStackTrace();
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			e.printStackTrace();
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Response<UserDto> connexion(Request<UserDto> request, Locale locale) {
		response = new Response<>();

		try {
			log.info("----begin connexion Utilisateur-----");

			List<UserDto> itemsDto = new ArrayList<>();
			UserDto dto = request.getData();
			if (dto != null) {
				// champs obligatoires
				HashMap<String, Object> fieldsToVerify = new HashMap<>();
			//	fieldsToVerify.put(" key", request.getKey());
				fieldsToVerify.put("login", dto.getLogin());
				fieldsToVerify.put("password", dto.getPassword());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				User userToConnect = null;
				// test login et password concordants
				userToConnect = userRepository.findByPasswordAndLogin(Utilities.encrypt(dto.getPassword()),
						dto.getLogin(), false);
				if (userToConnect == null) {
					response.setStatus(functionalError.LOGIN_FAIL(locale));
					response.setHasError(true);
					return response;
				} else if (userToConnect.getIsLocked().equals(Boolean.TRUE)) {
					response.setStatus(functionalError.DATA_NOT_EXIST(
							"votre compte a été verrouillé, contactez l'administrateur.----ACCES REFUSE----", locale));
					response.setHasError(true);
					return response;

				}
				UserDto userDto = getFullInfos(UserTransformer.INSTANCE.toDto(userToConnect), 1, false, locale);
				userDto.setPassword(null);
//				String token = Utilities.generateCodeOld();
//				userDto.setToken(token);
//				userDto.setKey(request.getKey());
//				userDto.setPassword(null);
//				redisUser.save(token, userDto, true);

				response.setItems(Arrays.asList(userDto));
				response.setStatus(functionalError.SUCCESS("Utilisateur connecté", locale));
				response.setHasError(false);
			}
			log.info("----end connexion Utilisateur-----");
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}

		return response;
	}

	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Response<UserDto> connexionLdap(Request<UserDto> request, Locale locale) {
		response = new Response<>();
		try {
			log.info("----begin connexionLdap Utilisateur-----");
			UserDto dto = request.getData();
			// champs obligatoires
			HashMap<String, Object> fieldsToVerify = new HashMap<>();
			fieldsToVerify.put("login", dto.getLogin());
			//fieldsToVerify.put(" key", request.getKey());
			fieldsToVerify.put("password", dto.getPassword());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			User userToConnect = null;
			userToConnect = userRepository.findByLogin(dto.getLogin(), false);
			if (userToConnect == null) {
				response.setStatus(functionalError.LOGIN_FAIL(locale));
				response.setHasError(Boolean.TRUE);
				return response;
			}
			if (userToConnect.getIsLocked() != null && userToConnect.getIsLocked().equals(Boolean.TRUE)) {
				response.setStatus(functionalError.DATA_NOT_EXIST(
						"votre compte a été verrouillé, contactez l'administrateur.----ACCES REFUSE----", locale));
				response.setHasError(Boolean.TRUE);
				return response;
			}
		//	Boolean isAuthenticated = ldapService.authenticateUser(dto.getLogin(), dto.getPassword());
			if (true) {
				response.setStatus(functionalError.LOGIN_FAIL(locale));
				response.setHasError(Boolean.TRUE);
				return response;
			}
			UserDto userDto = getFullInfos(UserTransformer.INSTANCE.toDto(userToConnect), 1, false, locale);
			userDto.setPassword(null);
//			String token = Utilities.generateCodeOld();
//			userDto.setToken(token);
//			userDto.setKey(request.getKey());
			userDto.setPassword(null);

		//	redisUser.save(token, userDto, true);
			response.setItems(Arrays.asList(userDto));
			response.setStatus(functionalError.SUCCESS("Utilisateur connecté", locale));
			response.setHasError(false);

			log.info("----end connexionLdap Utilisateur-----");
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	/**
	 * sendMail Utilisateur by using UtilisateurDto as object.
	 *
	 * @param request
	 * @return response
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Response<UserDto> sendMail(Request<UserDto> request, Locale locale) {
		log.info("----begin create Utilisateur-----");

		response = new Response<UserDto>();

		try {

			List<User> items = new ArrayList<User>();

			for (UserDto dto : request.getDatas()) {
				// Definir les parametres obligatoires
				Map<String, Object> fieldsToVerify = new HashMap<String, Object>();

				fieldsToVerify.put("email", dto.getEmail());
				fieldsToVerify.put("login", dto.getLogin());
				fieldsToVerify.put("password", dto.getPassword());
				// fieldsToVerify.put("profilId", dto.getProfilId());
				if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
					response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
					response.setHasError(true);
					return response;
				}

				// Verify if utilisateur to insert do not exist
				User existingEntity = null;
				// TODO: add/replace by the best method
				existingEntity = userRepository.findByEmail(dto.getEmail(), false);
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("email -> " + dto.getEmail(), locale));
					response.setHasError(true);
					return response;
				}
				User existingLogin = null;
				existingLogin = userRepository.findByLogin(dto.getLogin(), false);
				if (existingLogin != null) {
					response.setStatus(functionalError.DATA_EXIST("login-> " + dto.getLogin(), locale));
					response.setHasError(true);
					return response;
				}

				dto.setPassword(dto.getPassword());

				// --------------------------------------
				// SEND PASSWORD VIA MAIL
				// --------------------------------------
				// set mail to user
				Map<String, String> from = new HashMap<>();
				from.put("email", smtpProperties.getLogin());
				from.put("user", "CUSTOMER-CARE-FIXE");
				List<Map<String, String>> toRecipients = new ArrayList<Map<String, String>>();
				Map<String, String> recipient = new HashMap<String, String>();
				recipient = new HashMap<String, String>();
				recipient.put("email", dto.getEmail());
				recipient.put("user", dto.getLogin());
				toRecipients.add(recipient);
				// choisir la vraie url
				String appLink = null;

				// subject
				String subject = "Paramètres de connexion !";
				String body = "";
				context = new Context();
				String template = "new_user";

				context.setVariable("login", dto.getLogin());
				context.setVariable("password", dto.getPassword());
				// context.setVariable("appLink", appLink);
				Response<UserDto> responseEnvoiEmail = new Response<>();
				responseEnvoiEmail = hostingUtils.sendEmailAsync(from, toRecipients, subject, body, null, context,
						template, locale);
				if (responseEnvoiEmail != null && responseEnvoiEmail.isHasError()) {
					response.setStatus(responseEnvoiEmail.getStatus());
					response.setHasError(true);
					return response;
				}
				log.info("End sending mail to user");
				// ----END----------------------------------

			}
			log.info("----end create Utilisateur-----");
		} catch (PermissionDeniedDataAccessException e) {
			e.printStackTrace();
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			e.printStackTrace();
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			e.printStackTrace();
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			e.printStackTrace();
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			e.printStackTrace();
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	/**
	 * verrouiller User by using UserDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Response<UserDto> verrouiller(Request<UserDto> request, Locale locale) {
		// slf4jLogger.info("----begin verrouiller User-----");

		response = new Response<UserDto>();

		try {
			Map<String, Object> fieldsToVerifyUser = new HashMap<String, Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			List<User> items = new ArrayList<User>();

			UserDto dto = request.getData();
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si le user existe
			User entityToSave = userRepository.findOne(dto.getId(), false);
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

			User useToSave = userRepository.save(entityToSave);
			if (useToSave == null) {
				response.setStatus(functionalError.SAVE_FAIL("user", locale));
				response.setHasError(true);
				return response;
			}

			response.setItems(Arrays.asList(UserTransformer.INSTANCE.toDto(useToSave)));
			response.setHasError(false);

			// slf4jLogger.info("----end update User-----");
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
				// slf4jLogger.info("Erreur| code: {} - message: {}",
				// response.getStatus().getCode(),
				// response.getStatus().getMessage());
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	/**
	 * verrouiller User by using UserDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Response<UserDto> getUserByLap(Request<UserDto> request, Locale locale) {
		slf4jLogger.info("----begin getUserByLap getUserByLap-----");

		response = new Response<UserDto>();

		try {
			List<User> items = new ArrayList<User>();
			UserDto dto = request.getData();
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("login", dto.getLogin());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			List<UserDto> userLdap = null;
			//ldapService.searchUser(dto.getLogin());
			if (Utilities.isNotEmpty(userLdap)) {
				UserDto userDto = new UserDto();
				userDto.setLogin(Utilities.notBlank(userLdap.get(0).getUid()) ? userLdap.get(0).getUid() : "");
				userDto.setNom(Utilities.notBlank(userLdap.get(0).getPrenom()) ? userLdap.get(0).getPrenom() : "");
				userDto.setPrenom(Utilities.notBlank(userLdap.get(0).getNom()) ? userLdap.get(0).getNom() : "");
				userDto.setEmail(Utilities.notBlank(userLdap.get(0).getEmail()) ? userLdap.get(0).getEmail() : "");
				userDto.setContact(
						Utilities.notBlank(userLdap.get(0).getContact()) ? userLdap.get(0).getContact() : "");
				userDto.setMatricule(
						Utilities.notBlank(userLdap.get(0).getMatricule()) ? userLdap.get(0).getMatricule() : "");
				response.setItems(Arrays.asList(userDto));
				response.setHasError(false);
				response.setStatus(functionalError.SUCCESS("", locale));
			} else {
				response.setStatus(functionalError.DATA_NOT_EXIST("login inexistant", locale));
				response.setHasError(true);
			}
			slf4jLogger.info("----end getUserByLap getUserByLap-----");
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
				// slf4jLogger.info("Erreur| code: {} - message: {}",
				// response.getStatus().getCode(),
				// response.getStatus().getMessage());
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	/**
	 * deverrouiller User by using UserDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Response<UserDto> deverrouiller(Request<UserDto> request, Locale locale) {
		// slf4jLogger.info("----begin verrouiller User-----");

		response = new Response<UserDto>();

		try {
			Map<String, Object> fieldsToVerifyUser = new HashMap<String, Object>();
			fieldsToVerifyUser.put("user", request.getUser());
			if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			List<User> items = new ArrayList<User>();

			UserDto dto = request.getData();
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si le user existe
			User entityToSave = userRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("user -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// entityToSave.setStatut(existingStatut);
			entityToSave.setIsLocked(Boolean.FALSE);
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			entityToSave.setIsDeleted(false);

			User useToSave = userRepository.save(entityToSave);
			if (useToSave == null) {
				response.setStatus(functionalError.SAVE_FAIL("user", locale));
				response.setHasError(true);
				return response;
			}

			response.setItems(Arrays.asList(UserTransformer.INSTANCE.toDto(useToSave)));
			response.setHasError(false);

			// slf4jLogger.info("----end update User-----");
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
				// slf4jLogger.info("Erreur| code: {} - message: {}",
				// response.getStatus().getCode(),
				// response.getStatus().getMessage());
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}



	/**
	 * get full UserDto by using User as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private UserDto getFullInfos(UserDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here
		dto.setPassword(null);
		List<RoleUser> listOfRoles = roleUserRepository.findByUserId(dto.getId(), Boolean.FALSE);
		if (Utilities.isNotEmpty(listOfRoles)) {
			List<ActionDto> datasAction = new ArrayList<>();
			List<RoleDto> datasRole = new ArrayList<>();
			listOfRoles.stream().forEach(s -> {
				if (s.getRole() != null && s.getRole().getIsLocked() != null && !s.getRole().getIsLocked()) {
					RoleDto rDto = new RoleDto();
					rDto.setId(s.getRole().getId());
					rDto.setLibelle(s.getRole().getLibelle());
					rDto.setCode(s.getRole().getCode());
					datasRole.add(rDto);
				}
				List<RoleAction> listOfAction = roleActionRepository.findByRoleId(s.getRole().getId(), Boolean.FALSE);
				if (Utilities.isNotEmpty(listOfAction)) {
					listOfAction.stream().forEach(a -> {
						if (a.getAction() != null && a.getAction().getIsLocked() != null
								&& !a.getAction().getIsLocked()) {
							ActionDto aDto = new ActionDto();
							aDto.setId(a.getAction().getId());
							aDto.setLibelle(a.getAction().getLibelle());
							aDto.setCode(a.getAction().getCode());
							if (a.getAction().getParent() != null) {
								aDto.setParentActionCode(a.getAction().getParent().getCode());
							}
							Menus menus = menusRepository.findOne(a.getAction().getMenus().getId(), Boolean.FALSE);
							if (menus != null && menus.getIsLocked() != null && !menus.getIsLocked()) {
								aDto.setMenusCode(menus.getCode());
								aDto.setMenusLibelle(menus.getLibelle());
								aDto.setMenusId(menus.getId());
								if (menus.getModule() != null) {
									aDto.setModuleId(menus.getModule().getId());
									aDto.setModuleLibelle(menus.getModule().getLibelle());
									aDto.setModuleCode(menus.getModule().getCode());
								}
							}

							// On rajoute le parent de l'action s'il n'existe pas
							if (!datasAction.stream().anyMatch(act -> act.getId().equals(aDto.getId()))) {
								datasAction.add(aDto);
							}
							if (a.getAction().getParent() != null) {
								Action parent = a.getAction().getParent();
								ActionDto parentDto = new ActionDto();
								parentDto.setId(parent.getId());
								parentDto.setLibelle(parent.getLibelle());
								parentDto.setCode(parent.getCode());
								if (parent.getParent() != null) {
									parentDto.setParentActionCode(parent.getParent().getCode());
								}
								Menus menusParent = menusRepository.findOne(parent.getMenus().getId(), Boolean.FALSE);
								if (menusParent != null && menusParent.getIsLocked() != null
										&& !menusParent.getIsLocked()) {
									parentDto.setMenusCode(menusParent.getCode());
									parentDto.setMenusLibelle(menusParent.getLibelle());
									parentDto.setMenusId(menusParent.getId());
									if (menusParent.getModule() != null) {
										parentDto.setModuleId(menusParent.getModule().getId());
										parentDto.setModuleLibelle(menusParent.getModule().getLibelle());
										parentDto.setModuleCode(menusParent.getModule().getCode());
									}
								}
								if (!datasAction.stream().anyMatch(act -> act.getId().equals(parentDto.getId()))) {
									datasAction.add(parentDto);
								}
							}
						}
					});
				}
			});

			dto.setDatasRole(datasRole);
			List<ActionDto> itemsDto = new ArrayList<>();

			List<ActionDto> itemsModuleDto = new ArrayList<>();

			Map<String, List<ActionDto>> groupMenus = datasAction.stream()
					.filter(m -> m.getMenusCode() != null && !m.getMenusCode().isEmpty())
					.collect(Collectors.groupingBy(ActionDto::getMenusCode));
			groupMenus.entrySet().stream().forEach(n -> {
				ActionDto acDto = new ActionDto();
				acDto.setMenusCode(n.getKey());
				Menus menus = menusRepository.findByCode(n.getKey(), Boolean.FALSE);
				if (menus != null) {
					acDto.setMenusLibelle(menus.getLibelle());
					acDto.setMenusId(menus.getId());
					acDto.setModuleLibelle(menus.getModule().getLibelle());
					acDto.setModuleCode(menus.getModule().getCode());
					acDto.setModuleId(menus.getModule().getId());
				}
				acDto.setDatasAction(n.getValue());
				itemsDto.add(acDto);
			});
			if (Utilities.isNotEmpty(itemsDto)) {
				Map<String, List<ActionDto>> groupModule = itemsDto.stream()
						.filter(m -> m.getModuleCode() != null && !m.getModuleCode().isEmpty())
						.collect(Collectors.groupingBy(ActionDto::getModuleCode));
				groupModule.entrySet().stream().forEach(c -> {
					ActionDto actionDto = new ActionDto();
					Module module = moduleRepository.findByCode(c.getKey(), Boolean.FALSE);
					if (module != null) {
						actionDto.setModuleId(module.getId());
					}
					actionDto.setModuleCode(c.getKey());
					actionDto.setDatasMenus(c.getValue());
					itemsModuleDto.add(actionDto);
				});
				dto.setDatasModule(itemsModuleDto);
			}

			if (Utilities.isTrue(isSimpleLoading)) {
				return dto;
			}
			if (size > 1) {
				return dto;
			}
		}
		return dto;

	}

	//
	// @SuppressWarnings({ "unused", "null" })
	// public Response<UserDto> getActiveSession(Locale locale) {
	// response = new Response<UserDto>();
	// try {
	//
	// String regex = "*";
	// Set<String> getAllKey = redisUser.getKeys(regex);
	// List<String> keys = new ArrayList<String>();
	// List<UserDto> datas = new ArrayList<UserDto>();
	// List<User> datasx = new ArrayList<User>();
	// if(getAllKey != null && !getAllKey.isEmpty()) {
	// getAllKey.stream().forEach(k ->{
	// if(Utilities.notBlank(k) && k.length()==16) {
	// keys.add(k);
	// }
	// });
	// }
	// if(keys != null && !keys.isEmpty()) {
	// keys.stream().forEach(item ->{
	// UserDto dto = this.redisUser.get(item);
	// if(dto != null) {
	// datas.add(dto);
	// }
	// });
	// }
	//
	// List<UserDto> datasUser = new ArrayList<UserDto>();
	// List<Date> itemsDate = null;
	// Map<String, List<UserDto>> groupByUserSession =
	// datas.stream().collect(Collectors.groupingBy(o->o.getEmail()));
	// for(Map.Entry<String, List<UserDto>> entry : groupByUserSession.entrySet()) {
	// itemsDate = new ArrayList<Date>();
	// UserDto userDto = new UserDto();
	// if(entry.getKey() != null) {
	// List<User> datasU = new ArrayList<User>();
	// for(UserDto usr : entry.getValue()) {
	// if(Utilities.notBlank(null)) {
	// itemsDate.add(dateTimeFormat.parse(null));
	// User u = UserTransformer.INSTANCE.toEntity(usr);
	// datasU.add(u);
	// }
	// }
	// if(Utilities.isNotEmpty(itemsDate)) {
	// Date latest = Collections.max(itemsDate);
	// String date =dateTimeFormat.format(latest);
	// datasU.stream().forEach(us ->{
	// String d = dateTimeFormat.format(null);
	// if(d.equals(date)) {
	// datasx.add(us);
	// }
	// });
	// }
	// }
	// }
	// if (datasx != null && !datasx.isEmpty()) {
	// response.setItems(UserTransformer.INSTANCE.toDtos(datasx));
	// response.setHasError(Boolean.FALSE);
	// response.setStatus(functionalError.SUCCESS("", locale));
	// response.setCount((long) datasx.size());
	// }else {
	// //response.setItems(datasUser);
	// response.setHasError(Boolean.FALSE);
	// response.setStatus(functionalError.SUCCESS("Liste vide", locale));
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// slf4jLogger.warn("getValue : " + e.getCause(), e.getMessage());
	//
	// }
	// return response;
	// }

//	public Response<UserDto> getPublicKey(Locale locale) {
//		slf4jLogger.info("----begin getPublicKey-----");
//		response = new Response<>();
//		Rsa rsa = new Rsa();
//		try {
//			RSAPublicKey publicKey = null;
//			publicKey = (RSAPublicKey) rsa.getPublicKey();
//			if (publicKey != null) {
//				response.setHasError(false);
//				response.setModulus(publicKey.getModulus().toString(16));
//				response.setExponent(publicKey.getPublicExponent().toString(16));
//				response.setHasError(Boolean.FALSE);
//			} else {
//				response.setStatus(functionalError.DATA_EMPTY("", locale));
//				response.setHasError(true);
//				return response;
//			}
//
//			slf4jLogger.info("----end getPublicKey-----");
//		} catch (PermissionDeniedDataAccessException e) {
//			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
//		} catch (DataAccessResourceFailureException e) {
//			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
//		} catch (DataAccessException e) {
//			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
//		} catch (RuntimeException e) {
//			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
//		} catch (Exception e) {
//			exceptionUtils.EXCEPTION(response, locale, e);
//		} finally {
//			if (response.isHasError() && response.getStatus() != null) {
//				slf4jLogger.info("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
//						response.getStatus().getMessage());
//				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
//			}
//		}
//		return response;
//	}

	








//	@SuppressWarnings({ "unused", "null" })
//	public Response<UserDto> getActiveSession(Locale locale) {
//		response = new Response<UserDto>();
//		try {
//
//			String regex = "*";
//			Set<String> getAllKey = redisUser.getKeys(regex);
//			//List<String> getAllKey = redisUser.getKeys();
//			List<String> keys = new ArrayList<String>();
//			List<UserDto> datas = new ArrayList<UserDto>();
//			List<User> datasx = new ArrayList<User>();
//			if(getAllKey != null && !getAllKey.isEmpty()) {
//				getAllKey.stream().forEach(k ->{
//					if(Utilities.notBlank(k) && k.length()==8) {
//						keys.add(k);
//					}
//				});
//
//				if(keys != null && !keys.isEmpty()) {
//					keys.stream().forEach(item ->{
//						UserDto dto = this.redisUser.get(item);
//						if(dto != null) {
//							datas.add(dto);
//						}
//					});
//				}
//				if (datas != null && !datas.isEmpty()) {
//					response.setItems(datas);
//					response.setHasError(Boolean.FALSE);
//					response.setStatus(functionalError.SUCCESS("", locale));
//					response.setCount((long) datas.size());
//				}else {
//					response.setItems(new ArrayList<>());
//					response.setHasError(Boolean.FALSE);
//					response.setStatus(functionalError.SUCCESS("Liste vide", locale));
//				}
//			}else {
//				response.setItems(new ArrayList<>());
//
//				response.setHasError(Boolean.FALSE);
//				response.setStatus(functionalError.SUCCESS("Liste vide", locale));
//			}
//
//
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			slf4jLogger.warn("getValue : " + e.getCause(), e.getMessage());
//
//		}
//		return response;
//	}




	


//	public Response<UserDto> getSessionUser(Request<UserDto> request,Locale locale) {
//		response = new Response<UserDto>();
//		try {
//			UserDto dto = request.getData();
//			// champs obligatoires
//			HashMap<String, Object> fieldsToVerify = new HashMap<>();
//			fieldsToVerify.put("login", dto.getLogin());
//			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
//				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
//				response.setHasError(true);
//				return response;
//			}
//			UserDto userDto = redisUser.get(dto.getLogin());
//			if(userDto !=null) {
//				response.setItems(Arrays.asList(userDto));
//				response.setStatus(functionalError.SUCCESS("Utilisateur connecté", locale));
//				response.setHasError(false);
//			}else {
//				response.setStatus(functionalError.DISALLOWED_OPERATION("Token indisponible", locale));
//				response.setHasError(false);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			slf4jLogger.warn("getValue : " + e.getCause(), e.getMessage());
//		}
//		return response;
//	}
//	
//	
//	
//	
//
//	
//
//	
//	
//	public Response<Map<String, Object>> deleteKeys(Locale locale) {
//		Response<Map<String, Object>> response = new Response<>();
//		try {
//			Set<String> keys = redisUser.getKeys("*node-");
//			if(keys != null && !keys.isEmpty()) {
//					keys.stream().forEach(item ->{
//						redisUser.delete(item);
//						log.info("delete key : "+item);
//					});
//					response.setStatus(functionalError.SUCCESS("", locale));
//					response.setHasError(true);
//			}else {
//				response.setStatus(functionalError.DATA_EMPTY("", locale));
//				response.setHasError(true);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			slf4jLogger.warn("getValue : " + e.getCause(), e.getMessage());
//		}
//		return response;
//	}

	

}
