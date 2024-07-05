
/*
 * Java business for entity table coefficient_modele 
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
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ci.smile.system.manager.obf.dao.entity.CoefficientModele;
import ci.smile.system.manager.obf.dao.entity.Modele;
import ci.smile.system.manager.obf.dao.entity.TypeOffre;
import ci.smile.system.manager.obf.dao.repository.CoefficientModeleRepository;
import ci.smile.system.manager.obf.dao.repository.ModeleRepository;
import ci.smile.system.manager.obf.dao.repository.TypeOffreRepository;
import ci.smile.system.manager.obf.utils.ExceptionUtils;
import ci.smile.system.manager.obf.utils.FunctionalError;
import ci.smile.system.manager.obf.utils.ParamsUtils;
import ci.smile.system.manager.obf.utils.Status;
import ci.smile.system.manager.obf.utils.TechnicalError;
import ci.smile.system.manager.obf.utils.Utilities;
import ci.smile.system.manager.obf.utils.Validate;
import ci.smile.system.manager.obf.utils.contract.IBasicBusiness;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.CoefficientModeleDto;
import ci.smile.system.manager.obf.utils.dto.RadiusDto;
import ci.smile.system.manager.obf.utils.dto.customize._ProvisionningDto;
import ci.smile.system.manager.obf.utils.dto.transformer.CoefficientModeleTransformer;
import ci.smile.system.manager.obf.utils.enums.TypeOffreEnum;
import lombok.extern.java.Log;

/**
 * BUSINESS for table "coefficient_modele"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class CoefficientModeleBusiness
		implements IBasicBusiness<Request<CoefficientModeleDto>, Response<CoefficientModeleDto>> {

	private Response<CoefficientModeleDto> response;
	@Autowired
	private CoefficientModeleRepository coefficientModeleRepository;
	@Autowired
	private ModeleRepository modeleRepository;
	@Autowired
	private TypeOffreRepository typeOffreRepository;

	@Autowired
	private RadiusBusiness radiusBusiness;

	@Autowired
	private ParamsUtils paramsUtils;

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

	public CoefficientModeleBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create CoefficientModele by using CoefficientModeleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CoefficientModeleDto> create(Request<CoefficientModeleDto> request, Locale locale)
			throws ParseException {
		log.info("----begin create CoefficientModele-----");

		Response<CoefficientModeleDto> response = new Response<CoefficientModeleDto>();
		List<CoefficientModele> items = new ArrayList<CoefficientModele>();

		for (CoefficientModeleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("modeleId", dto.getModeleId());
			fieldsToVerify.put("coefMac", dto.getCoefMac());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			// Verify if modele exist
			Modele existingModele = null;
			if (dto.getModeleId() != null && dto.getModeleId() > 0) {
				existingModele = modeleRepository.findOne(dto.getModeleId(), false);
				if (existingModele == null) {
					response.setStatus(
							functionalError.DATA_NOT_EXIST("modele modeleId -> " + dto.getModeleId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			// Verify if typeOffre exist
			TypeOffre existingTypeOffre = typeOffreRepository.findByCode(TypeOffreEnum.DEFAULT.getValue(), false);
			if (existingTypeOffre == null) {
				response.setStatus(
						functionalError.DATA_NOT_EXIST("typeOffre code-> " + TypeOffreEnum.DEFAULT.getValue(), locale));
				response.setHasError(true);
				return response;
			}

			CoefficientModele entityToSave = null;
			entityToSave = CoefficientModeleTransformer.INSTANCE.toEntity(dto, existingModele, existingTypeOffre);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<CoefficientModele> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = coefficientModeleRepository.saveAll((Iterable<CoefficientModele>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("coefficientModele", locale));
				response.setHasError(true);
				return response;
			}
			List<CoefficientModeleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? CoefficientModeleTransformer.INSTANCE.toLiteDtos(itemsSaved)
					: CoefficientModeleTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create CoefficientModele-----");
		return response;
	}

	/**
	 * update CoefficientModele by using CoefficientModeleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CoefficientModeleDto> update(Request<CoefficientModeleDto> request, Locale locale)
			throws ParseException {
		log.info("----begin update CoefficientModele-----");

		Response<CoefficientModeleDto> response = new Response<CoefficientModeleDto>();
		List<CoefficientModele> items = new ArrayList<CoefficientModele>();

		for (CoefficientModeleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la coefficientModele existe
			CoefficientModele entityToSave = null;
			entityToSave = coefficientModeleRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("coefficientModele id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if modele exist
			if (dto.getModeleId() != null && dto.getModeleId() > 0) {
				Modele existingModele = modeleRepository.findOne(dto.getModeleId(), false);
				if (existingModele == null) {
					response.setStatus(
							functionalError.DATA_NOT_EXIST("modele modeleId -> " + dto.getModeleId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setModele(existingModele);
			}
			// Verify if typeOffre exist
			if (dto.getTypeOffreId() != null && dto.getTypeOffreId() > 0) {
				TypeOffre existingTypeOffre = typeOffreRepository.findOne(dto.getTypeOffreId(), false);
				if (existingTypeOffre == null) {
					response.setStatus(
							functionalError.DATA_NOT_EXIST("typeOffre typeOffreId -> " + dto.getTypeOffreId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setTypeOffre(existingTypeOffre);
			}
			if (dto.getCoefMac() != null && dto.getCoefMac() >= 0) {
				entityToSave.setCoefMac(dto.getCoefMac());
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
			List<CoefficientModele> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = coefficientModeleRepository.saveAll((Iterable<CoefficientModele>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("coefficientModele", locale));
				response.setHasError(true);
				return response;
			}
			List<CoefficientModeleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? CoefficientModeleTransformer.INSTANCE.toLiteDtos(itemsSaved)
					: CoefficientModeleTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update CoefficientModele-----");
		return response;
	}

	/**
	 * delete CoefficientModele by using CoefficientModeleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CoefficientModeleDto> delete(Request<CoefficientModeleDto> request, Locale locale) {
		log.info("----begin delete CoefficientModele-----");

		Response<CoefficientModeleDto> response = new Response<CoefficientModeleDto>();
		List<CoefficientModele> items = new ArrayList<CoefficientModele>();

		for (CoefficientModeleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la coefficientModele existe
			CoefficientModele existingEntity = null;
			existingEntity = coefficientModeleRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("coefficientModele -> " + dto.getId(), locale));
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
			coefficientModeleRepository.saveAll((Iterable<CoefficientModele>) items);

			response.setHasError(false);
		}

		log.info("----end delete CoefficientModele-----");
		return response;
	}

	/**
	 * get CoefficientModele by using CoefficientModeleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CoefficientModeleDto> getByCriteria(Request<CoefficientModeleDto> request, Locale locale)
			throws Exception {
		log.info("----begin get CoefficientModele-----");

		Response<CoefficientModeleDto> response = new Response<CoefficientModeleDto>();
		List<CoefficientModele> items = coefficientModeleRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<CoefficientModeleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? CoefficientModeleTransformer.INSTANCE.toLiteDtos(items)
					: CoefficientModeleTransformer.INSTANCE.toDtos(items);

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
			response.setCount(coefficientModeleRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("coefficientModele", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get CoefficientModele-----");
		return response;
	}

	@SuppressWarnings("unchecked")
	public Response<CoefficientModeleDto> getNdByMac(Request<CoefficientModeleDto> request, Locale locale) {
		Response<CoefficientModeleDto> response = new Response<CoefficientModeleDto>();
		try {
			CoefficientModeleDto dto = request.getData();
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("mac", dto.getMac());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			// controle sur le mac à faire
			RadiusDto radDto = Utilities.translateLabelIntoMAC(dto.getMac());
			if (radDto != null && radDto.isContainIllegalChar()) {
				response.setStatus(functionalError.DISALLOWED_OPERATION("adresse mac invalide", locale));
				response.setHasError(Boolean.TRUE);
				return response;
			}
			if (logicToSearch(request, locale) instanceof Map) {
				Map<String, Object> resp = (Map<String, Object>) logicToSearch(request, locale);
				if (resp != null && resp.get("items") != null) {
					List<Map<String, Object>> items = (List<Map<String, Object>>) resp.get("items");
					if (Utilities.isNotEmpty(items)) {
						Map<String, Object> onlyOne = items.get(0);
						if (onlyOne != null) {
							Map<String, Object> datasCheck = (Map<String, Object>) onlyOne.get("datasCheck");
							if (datasCheck != null) {
								List<Map<String, Object>> values = (List<Map<String, Object>>) datasCheck.get("value");
								if (Utilities.isNotEmpty(values)) {
									List<Map<String, Object>> agentId = values.stream().filter(
											o -> ((String) o.get("attribute")).equalsIgnoreCase("ADSL-Agent-Remote-Id"))
											.collect(Collectors.toList());
									if (Utilities.isNotEmpty(agentId)) {
										Map<String, Object> dataNd = agentId.get(0);
										if (dataNd != null) {
											CoefficientModeleDto resDto = new CoefficientModeleDto();
											String nd = (String) dataNd.get("value");
											resDto.setNd(nd);
											response.setItem(resDto);
											response.setStatus(functionalError.SUCCESS("", locale));
											response.setHasError(Boolean.FALSE);
										}
									}
								}
							}
						}
					}
				} else {
					response.setStatus(
							functionalError.DATA_NOT_EXIST("aucun nd associé à l'adresse mac renseigné", locale));
					response.setHasError(Boolean.TRUE);
					return response;
				}
			} else {
				response.setStatus(
						functionalError.DATA_NOT_EXIST("aucun nd associé à l'adresse mac renseigné", locale));
				response.setHasError(Boolean.TRUE);
				return response;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public Response<CoefficientModeleDto> getCoefficient() throws Exception {
		Response<CoefficientModeleDto> response = new Response<CoefficientModeleDto>();
		List<Integer> coefficients = coefficientModeleRepository.getCoefficient(Boolean.FALSE);
		if (Utilities.isNotEmpty(coefficients)) {
			response.setCoefficients(coefficients);
			response.setHasError(Boolean.FALSE);
			response.setStatus(functionalError.SUCCESS("", new Locale("fr")));
		} else {
			response.setCoefficients(coefficients);
			response.setHasError(Boolean.FALSE);
			response.setStatus(functionalError.SUCCESS("", new Locale("fr")));
		}
		return response;
	}

	public Object logicToSearch(Request<CoefficientModeleDto> request, Locale locale) throws Exception {
		Response<CoefficientModeleDto> response = new Response<CoefficientModeleDto>();
		CoefficientModeleDto dto = request.getData();
		if (Utilities.notBlank(dto.getMac()) && Utilities.notBlank(dto.getNd())) {
			response.setStatus(functionalError.DISALLOWED_OPERATION(
					"Veuillez s'il vous plaît faire la recherche sur une seule information à la fois (soit le nd, soit l'adtesse mac)",
					locale));
			response.setHasError(Boolean.TRUE);
			return response;
		}
		String username = null;
		if (Utilities.notBlank(dto.getMac())) {
			Response<CoefficientModeleDto> coefficients = getCoefficient();
			if (coefficients != null && Utilities.isNotEmpty(coefficients.getCoefficients())) {
				for (Integer c : coefficients.getCoefficients()) {
					Request<RadiusDto> req = new Request<RadiusDto>();
					RadiusDto rad = new RadiusDto();
					rad.setMac(dto.getMac());
					rad.setCoeff(c);
					req.setUser(request.getUser());
					req.setData(rad);
					try {
						Response<RadiusDto> resp = radiusBusiness.getMacAdresse(req, locale);
						if (resp != null && Utilities.isNotEmpty(resp.getItems())) {
							RadiusDto item = resp.getItems().get(0);
							item.getMac();
							Request<RadiusDto> reqVerify = new Request<RadiusDto>();
							reqVerify.setUsername(item.getMac());
							Map<String, Object> respMap = (Map<String, Object>) checkIfExist(reqVerify, locale);
							if (respMap != null) {
								Boolean isEx = (Boolean) respMap.get("isHere");
								if (isEx) {
									username = item.getMac();
									break;
								}
							}
						}
					} catch (Exception e) {
						return runtimeMessage(response, e);
					}
				}
			}
		} else {
			if (Utilities.notBlank(dto.getNd())) {
				Request<RadiusDto> reqVerify = new Request<RadiusDto>();
				_ProvisionningDto dataProv = new _ProvisionningDto();
				dataProv.setNd(dto.getNd());
				reqVerify.setDataProvisioning(dataProv);
				Map<String, Object> respMap = (Map<String, Object>) checkNd(reqVerify, locale);
				if (respMap != null) {
					Boolean isEx = (Boolean) respMap.get("isExist");
					if (isEx) {
						username = (String) respMap.get("username");
					}
				}
			}
		}
		if (Utilities.notBlank(username)) {
			// call api get info radius
			Request<RadiusDto> reqInfoRad = new Request<RadiusDto>();
			_ProvisionningDto dataProv = new _ProvisionningDto();
			dataProv.setUsername(username);
			reqInfoRad.setDataProvisioning(dataProv);
			return getInfoRadius(reqInfoRad, locale);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("adresse mac inexistante dans la base radius", locale));
			response.setHasError(Boolean.FALSE);
			return response;
		}
	}

	private Object runtimeMessage(Response<CoefficientModeleDto> response, Exception e) {
		e.printStackTrace();
		Status status = new Status();
		status.setCode("910");
		status.setMessage(e.getMessage());
		response.setStatus(status);
		response.setHasError(Boolean.TRUE);
		return response;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object checkIfExist(Request<RadiusDto> request, Locale locale) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		Response response = new Response();
		try {
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("username", request.getUsername());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			RestTemplate rt = new RestTemplate();
			rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			rt.getMessageConverters().add(new StringHttpMessageConverter());
			// radiusFtth
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
			requestHeaders.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
			HttpEntity<Request<RadiusDto>> requestEntity = new HttpEntity<Request<RadiusDto>>(request, requestHeaders);
			ResponseEntity<Map> responseEntity = rt.exchange(paramsUtils.getUrlCheckRadius(), HttpMethod.POST,
					requestEntity, Map.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				if (responseEntity.getBody() != null && responseEntity.getBody() != null) {
					map = responseEntity.getBody();
				}
			}
		} catch (Exception e) {
			Status status = new Status();
			status.setCode("1044");
			status.setMessage(e.getMessage());
			response.setStatus(status);
			response.setHasError(Boolean.TRUE);
			return response;
		}
		return map;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object getInfoRadius(Request<RadiusDto> request, Locale locale) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		Response response = new Response();
		try {
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("dataProvisionning", request.getDataProvisioning());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			RestTemplate rt = new RestTemplate();
			rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			rt.getMessageConverters().add(new StringHttpMessageConverter());
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
			requestHeaders.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
			HttpEntity<Request<RadiusDto>> requestEntity = new HttpEntity<Request<RadiusDto>>(request, requestHeaders);
			ResponseEntity<Map> responseEntity = rt.exchange(paramsUtils.getUrlCheckRadiusInfos(), HttpMethod.POST,
					requestEntity, Map.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				if (responseEntity.getBody() != null && responseEntity.getBody() != null) {
					map = responseEntity.getBody();
				}
			}
		} catch (Exception e) {
			Status status = new Status();
			status.setCode("1044");
			status.setMessage(e.getMessage());
			response.setStatus(status);
			response.setHasError(Boolean.TRUE);
			return response;
		}
		return map;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object checkNd(Request<RadiusDto> request, Locale locale) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		Response response = new Response();
		try {
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("dataProvisioning", request.getDataProvisioning());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			RestTemplate rt = new RestTemplate();
			rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			rt.getMessageConverters().add(new StringHttpMessageConverter());
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
			requestHeaders.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
			HttpEntity<Request<RadiusDto>> requestEntity = new HttpEntity<Request<RadiusDto>>(request, requestHeaders);
			ResponseEntity<Map> responseEntity = rt.exchange(paramsUtils.getUrlCheckNdRadius(), HttpMethod.POST,
					requestEntity, Map.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				if (responseEntity.getBody() != null && responseEntity.getBody() != null) {
					map = responseEntity.getBody();
				}
			}
		} catch (Exception e) {
			Status status = new Status();
			status.setCode("1044");
			status.setMessage(e.getMessage());
			response.setStatus(status);
			response.setHasError(Boolean.TRUE);
			return response;
		}
		return map;
	}

	/**
	 * get full CoefficientModeleDto by using CoefficientModele as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private CoefficientModeleDto getFullInfos(CoefficientModeleDto dto, Integer size, Boolean isSimpleLoading,
			Locale locale) throws Exception {
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
