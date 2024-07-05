
/*
 * Java business for entity table compte 
 * Created on 2022-11-11 ( Time 16:19:56 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.business;

import lombok.extern.java.Log;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import ci.smile.system.manager.obf.utils.*;
import ci.smile.system.manager.obf.utils.dto.*;
import ci.smile.system.manager.obf.utils.contract.IBasicBusiness;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.transformer.*;
import ci.smile.system.manager.obf.dao.entity.Compte;
import ci.smile.system.manager.obf.dao.repository.*;

/**
 * BUSINESS for table "compte"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class CompteBusiness implements IBasicBusiness<Request<CompteDto>, Response<CompteDto>> {

	private Response<CompteDto> response; 
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private FunctionalError functionalError; 
	@Autowired
	private TechnicalError technicalError;
	@Autowired
	private ExceptionUtils exceptionUtils;
	@PersistenceContext
	private EntityManager em;

	private Logger slf4jLogger;

	private SimpleDateFormat dateFormat;
	private SimpleDateFormat dateTimeFormat;

	public CompteBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create Compte by using CompteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CompteDto> create(Request<CompteDto> request, Locale locale) throws ParseException {
		log.info("----begin create Compte-----");
		Date dateDay = new Date();
		Response<CompteDto> response = new Response<CompteDto>();
		List<Compte> items = new ArrayList<Compte>();
		CompteDto dto = request.getData();
		// Definir les parametres obligatoires
		Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
		fieldsToVerify.put("userCompte", dto.getUserCompte());
		fieldsToVerify.put("login", dto.getLogin());
		fieldsToVerify.put("password", dto.getPassword());
//			fieldsToVerify.put("isBlocked", dto.getIsBlocked());
//			fieldsToVerify.put("securityToken", dto.getSecurityToken());
//			fieldsToVerify.put("salt", dto.getSalt());
//			fieldsToVerify.put("secretKey", dto.getSecretKey());
		if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
			response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
			response.setHasError(true);
			return response;
		}
		Compte compte = null;
		compte = compteRepository.findByUserOneCompte(dto.getUserCompte(), Boolean.FALSE);
		if (compte != null) {
			response.setStatus(functionalError.DATA_EXIST("Cet compte existe deja", locale));
			response.setHasError(true);
			return response;
		}
		SimpleDateFormat MonthFormat = new SimpleDateFormat("MM");
		SimpleDateFormat YearFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat MinuteFormat = new SimpleDateFormat("mm");
		// definit le salt à partir de la concatenation du mois,de
		// l'annee et le minute courante
		String salt = MonthFormat.format(dateDay) + "-" + YearFormat.format(dateDay) + "-"
				+ MinuteFormat.format(dateDay) + "-" + dto.getUserCompte();
		compte = new Compte();
		compte.setUserCompte(dto.getUserCompte());
		compte.setSecurityToken(DigestUtils.sha1Hex(dto.getLogin()));
		compte.setSecurityPassword(DigestUtils.sha1Hex(dto.getPassword() + salt));
		compte.setSalt(salt);
		compte.setCreatedAt(dateDay);
		compte.setIsDeleted(Boolean.FALSE);

		if (compte != null) {
			Compte item = null;
			response.setHasError(false);
			item = compteRepository.save(compte);
			if (item == null) {
				response.setStatus(functionalError.SAVE_FAIL("compte", locale));
				response.setHasError(true);
				return response;
			}
			CompteDto itemDto = CompteTransformer.INSTANCE.toDto(item);
//					? CompteTransformer.INSTANCE.toDto(compte)
//					: CompteTransformer.INSTANCE.toLiteDto(compte);
			response.setItem(itemDto);
			response.setHasError(false);
		}
		log.info("----end create Compte-----");

		return response;
	}
	public Response<CompteDto> authentication(Request<CompteDto> request, Locale locale) throws ParseException {
		log.info("----begin create Compte-----");
		Response<CompteDto> response = new Response<CompteDto>();
		CompteDto dto = request.getData();
		try {
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("securityToken", dto.getSecurityToken());
			fieldsToVerify.put("securityPassword", dto.getSecurityPassword());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			List<Compte> verify = compteRepository.findBySecurityTokenAndSecurityPassword(dto.getSecurityToken(),dto.getSecurityPassword(), Boolean.FALSE);
			System.out.println(verify);
			if (Utilities.isNotEmpty(verify)) {
				Compte compte = verify.get(0); 
				if (compte.getIsBlocked() == Boolean.TRUE) {
					response.setHasError(Boolean.TRUE);
					response.setStatus(functionalError.AUTH_FAIL("Compte Desactivee", locale));
					return response;
				}
				CompteDto compteDto = new CompteDto();
				compteDto.setUserCompte(compte.getUserCompte());
				response.setItem(compteDto);
				response.setHasError(Boolean.FALSE);
				System.out.println(compte);
			}else {
				response.setHasError(Boolean.TRUE);
				response.setStatus(functionalError.AUTH_FAIL("Echec Connexion", locale));
			}

		} catch (PermissionDeniedDataAccessException e) {
			response.setHasError(true);
			response.setStatus(technicalError.DB_PERMISSION_DENIED("", locale));
			slf4jLogger.warn("Erreur| code: {} -  message: {} - cause: {}  - SysMessage: {}",
					StatusCode.TECH_DB_PERMISSION_DENIED, StatusMessage.TECH_DB_PERMISSION_DENIED, e.getCause(),
					e.getMessage());
		} catch (DataAccessResourceFailureException e) {
			// base de données indisponible
			response.setHasError(true);
			response.setStatus(technicalError.DB_FAIL("", locale));
			slf4jLogger.warn("Erreur| code: {} -  message: {} - cause: {}  - SysMessage: {}", StatusCode.TECH_DB_FAIL,
					StatusMessage.TECH_DB_FAIL, e.getCause(), e.getMessage());
		} catch (DataAccessException e) {
			// Serveur a refusé la requete
			response.setHasError(true);
			response.setStatus(technicalError.DB_QUERY_REFUSED("", locale));
			slf4jLogger.warn("Erreur| code: {} -  message: {} - cause: {}  - SysMessage: {}",
					StatusCode.TECH_DB_QUERY_REFUSED, StatusMessage.TECH_DB_QUERY_REFUSED, e.getCause(),
					e.getMessage());
		} catch (Exception e) {
			// Erreur interne
			response.setHasError(true);
			response.setStatus(technicalError.INTERN_ERROR("", locale));
			slf4jLogger.warn("Erreur| code: {} -  message: {} - cause: {}  - SysMessage: {}",
					StatusCode.TECH_INTERN_ERROR, StatusMessage.TECH_INTERN_ERROR, e.getCause(), e.getMessage());
		}

		log.info("----end authentificate-----");
		return response;
	}

	/**
	 * update Compte by using CompteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CompteDto> update(Request<CompteDto> request, Locale locale) throws ParseException {
		log.info("----begin update Compte-----");
		Response<CompteDto> response = new Response<CompteDto>();
		List<Compte> items = new ArrayList<Compte>();

		for (CompteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("idCompte", dto.getIdCompte());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			// Verifier si la compte existe
			Compte entityToSave = null;
			entityToSave = compteRepository.findOne(dto.getIdCompte(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("compte idCompte -> " + dto.getIdCompte(), locale));
				response.setHasError(true);
				return response;
			}
			if (Utilities.notBlank(dto.getUserCompte())) {
				Compte cpt = compteRepository.findByUserOneCompte(dto.getUserCompte(), Boolean.FALSE);
				if (cpt != null && !cpt.getIdCompte().equals(dto.getIdCompte())) {
					response.setStatus(functionalError.DATA_EXIST(" " + dto.getIdCompte(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setUserCompte(dto.getUserCompte());
			}
			if (Utilities.notBlank(dto.getLogin()) && Utilities.notBlank(dto.getPassword())) {
				entityToSave.setSecurityToken(DigestUtils.sha1Hex(dto.getLogin()));
			}
			if (Utilities.notBlank(dto.getPassword())) {
				entityToSave.setSecurityPassword(DigestUtils.sha1Hex(dto.getPassword() + entityToSave.getSalt()));
			}
			if (dto.getIsConnected() != null) {
				entityToSave.setIsConnected(dto.getIsConnected());
			}
			if (dto.getIsBlocked() != null) {
				entityToSave.setIsBlocked(dto.getIsBlocked());
			}
//			if (Utilities.notBlank(dto.getSecurityToken())) {
//				entityToSave.setSecurityToken(dto.getSecurityToken());
//			}
//			if (Utilities.notBlank(dto.getSalt())) {
//				entityToSave.setSalt(dto.getSalt());
//			}
			if (dto.getCreatedBy() != null && dto.getCreatedBy() > 0) {
				entityToSave.setCreatedBy(dto.getCreatedBy());
			}
			if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) {
				entityToSave.setUpdatedBy(dto.getUpdatedBy());
			}
//			if (Utilities.notBlank(dto.getSecretKey())) {
//				entityToSave.setSecretKey(dto.getSecretKey());
//			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Compte> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = compteRepository.saveAll((Iterable<Compte>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("compte", locale));
				response.setHasError(true);
				return response;
			}
			List<CompteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? CompteTransformer.INSTANCE.toLiteDtos(itemsSaved)
					: CompteTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Compte-----");
		return response;
	}

	/**
	 * delete Compte by using CompteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CompteDto> delete(Request<CompteDto> request, Locale locale) {
		log.info("----begin delete Compte-----");

		Response<CompteDto> response = new Response<CompteDto>();
		List<Compte> items = new ArrayList<Compte>();

		for (CompteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("idCompte", dto.getIdCompte());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la compte existe
			Compte existingEntity = null;
			existingEntity = compteRepository.findOne(dto.getIdCompte(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("compte -> " + dto.getIdCompte(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			existingEntity.setIsDeleted(true);
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			compteRepository.saveAll((Iterable<Compte>) items);

			response.setHasError(false);
		}

		log.info("----end delete Compte-----");
		return response;
	}
	
	public Response<CompteDto> lock(Request<CompteDto> request, Locale locale) throws ParseException {
		log.info("----begin delete Compte-----");
		Response<CompteDto> response = new Response<CompteDto>();
		List<Compte> items = new ArrayList<Compte>();
		for (CompteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("idCompte", dto.getIdCompte());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			// Verifier si la compte existe
			Compte existingEntity = null;
			existingEntity = compteRepository.findOne(dto.getIdCompte(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("compte -> " + dto.getIdCompte(), locale));
				response.setHasError(true);
				return response;
			}
			existingEntity.setIsBlocked(Boolean.TRUE);
			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------
			existingEntity.setIsDeleted(true);
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			List<Compte> compteSaved = compteRepository.saveAll((Iterable<Compte>) items);
			if (Utilities.isNotEmpty(compteSaved)) {
				response.setItems(CompteTransformer.INSTANCE.toDtos(compteSaved));
			}
			
			response.setHasError(false);
		}

		log.info("----end delete Compte-----");
		return response;
	}
	
	
	public Response<CompteDto> unlock(Request<CompteDto> request, Locale locale) throws ParseException {
		log.info("----begin delete Compte-----");

		Response<CompteDto> response = new Response<CompteDto>();
		List<Compte> items = new ArrayList<Compte>();

		for (CompteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("idCompte", dto.getIdCompte());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			// Verifier si la compte existe
			Compte existingEntity = null;
			existingEntity = compteRepository.findOne(dto.getIdCompte(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("compte -> " + dto.getIdCompte(), locale));
				response.setHasError(true);
				return response;
			}
			existingEntity.setIsBlocked(Boolean.FALSE);
			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------
			existingEntity.setIsDeleted(true);
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			List<Compte> compteSaved = compteRepository.saveAll((Iterable<Compte>) items);
			if (Utilities.isNotEmpty(compteSaved)) {
				response.setItems(CompteTransformer.INSTANCE.toDtos(compteSaved));
			}
			
			response.setHasError(false);
		}

		log.info("----end delete Compte-----");
		return response;
	}

	/**
	 * get Compte by using CompteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CompteDto> getByCriteria(Request<CompteDto> request, Locale locale) throws Exception {
		log.info("----begin get Compte-----");

		Response<CompteDto> response = new Response<CompteDto>();
		List<Compte> items = compteRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<CompteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? CompteTransformer.INSTANCE.toLiteDtos(items)
					: CompteTransformer.INSTANCE.toDtos(items);

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
			response.setCount(compteRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("compte", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Compte-----");
		return response;
	}

	/**
	 * get full CompteDto by using Compte as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private CompteDto getFullInfos(CompteDto dto, Integer size, Boolean isSimpleLoading, Locale locale)
			throws Exception {
		// put code here

		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}

	public Response<CompteDto> custom(Request<CompteDto> request, Locale locale) {
		log.info("----begin custom CompteDto-----");
		Response<CompteDto> response = new Response<CompteDto>();

		response.setHasError(false);
		response.setCount(1L);
		response.setItems(Arrays.asList(new CompteDto()));
		log.info("----end custom CompteDto-----");
		return response;
	}
}
