
/*
 * Java business for entity table radius
 * Created on 2021-03-09 ( Time 11:10:42 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.business;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ci.smile.system.manager.obf.dao.entity.CoefficientModele;
import ci.smile.system.manager.obf.dao.entity.GroupeRadius;
import ci.smile.system.manager.obf.dao.entity.Modele;
import ci.smile.system.manager.obf.dao.entity.Radius;
import ci.smile.system.manager.obf.dao.entity.TypeOffre;
import ci.smile.system.manager.obf.dao.entity.User;
import ci.smile.system.manager.obf.dao.repository.CoefficientModeleRepository;
import ci.smile.system.manager.obf.dao.repository.GroupeRadiusRepository;
import ci.smile.system.manager.obf.dao.repository.ModeleRepository;
import ci.smile.system.manager.obf.dao.repository.RadiusRepository;
import ci.smile.system.manager.obf.dao.repository.TypeOffreRepository;
import ci.smile.system.manager.obf.dao.repository.UserRepository;
import ci.smile.system.manager.obf.utils.ExceptionUtils;
import ci.smile.system.manager.obf.utils.FunctionalError;
import ci.smile.system.manager.obf.utils.ParamsUtils;
import ci.smile.system.manager.obf.utils.TechnicalError;
import ci.smile.system.manager.obf.utils.Utilities;
import ci.smile.system.manager.obf.utils.Validate;
import ci.smile.system.manager.obf.utils.contract.IBasicBusiness;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.utils.dto.RadiusDto;
import ci.smile.system.manager.obf.utils.dto.transformer.RadiusTransformer;
import ci.smile.system.manager.obf.utils.enums.TypeOffreEnum;
import lombok.extern.java.Log;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * BUSINESS for table "radius"
 *
 * @author Geo
 *
 */
@Log
@Component
public class RadiusBusiness implements IBasicBusiness<Request<RadiusDto>, Response<RadiusDto>> {

	private Response<RadiusDto> response;
	@Autowired
	private RadiusRepository radiusRepository;
	@Autowired
	private GroupeRadiusRepository groupeRadiusRepository;
	@Autowired
	private TypeOffreRepository typeOffreRepository;
	@Autowired
	private FunctionalError functionalError;
	@Autowired
	private TechnicalError technicalError;
	@Autowired
	private ExceptionUtils exceptionUtils;
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModeleRepository modeleRepository;

	@Autowired
	private ParamsUtils paramsUtils;

	private SimpleDateFormat dateFormat;
	private SimpleDateFormat dateTimeFormat;
	@Autowired
	private CoefficientModeleRepository coefficientModeleRepository;

	public RadiusBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create Radius by using RadiusDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@Override
	public Response<RadiusDto> create(Request<RadiusDto> request, Locale locale) throws ParseException {
		log.info("----begin create Radius-----");

		Response<RadiusDto> response = new Response<RadiusDto>();
		List<Radius> items = new ArrayList<Radius>();

		for (RadiusDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("offre", dto.getOffre());
			fieldsToVerify.put("groupeRadiusId", dto.getGroupeRadiusId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			// Verify if groupeRadius exist
			GroupeRadius existingGroupeRadius = null;
			if (dto.getGroupeRadiusId() != null && dto.getGroupeRadiusId() > 0) {
				existingGroupeRadius = groupeRadiusRepository.findOne(dto.getGroupeRadiusId(), false);
				if (existingGroupeRadius == null) {
					response.setStatus(functionalError
							.DATA_NOT_EXIST("groupeRadius groupeRadiusId -> " + dto.getGroupeRadiusId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			// Map<String, Object> data = new HashMap<>();
			// data.put("code", dto.getPort().substring(0,4));
			// Map<String, Object> mapReq = new HashMap<>();
			// mapReq.put("dataZone", data);
			// try {
			// Map<String, Object> map = CallAPi(mapReq, paramsUtils.getZone());
			// if(map != null && map.get("hasError").equals(false)) {
			// List<Map<String, Object>> itemsZone = (List<Map<String, Object>>)
			// map.get("itemsZone");
			// if(Utilities.isNotEmpty(itemsZone)) {
			// dto.setLocalisation(itemsZone.get(0).get("localisationLibelle").toString());
			// }
			// }
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// Verify if typeOffre exist
			TypeOffre existingTypeOffre = typeOffreRepository.findByCode(TypeOffreEnum.DEFAULT.getValue(), false);
			if (existingTypeOffre == null) {
				response.setStatus(
						functionalError.DATA_NOT_EXIST("typeOffre code-> " + TypeOffreEnum.DEFAULT.getValue(), locale));
				response.setHasError(true);
				return response;
			}
			Radius entityToSave = null;
			entityToSave = RadiusTransformer.INSTANCE.toEntity(dto, existingGroupeRadius, existingTypeOffre);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Radius> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = radiusRepository.saveAll((Iterable<Radius>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("radius", locale));
				response.setHasError(true);
				return response;
			}
			List<RadiusDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? RadiusTransformer.INSTANCE.toLiteDtos(itemsSaved)
					: RadiusTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Radius-----");
		return response;
	}

	/**
	 * update Radius by using RadiusDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@Override
	public Response<RadiusDto> update(Request<RadiusDto> request, Locale locale) throws ParseException {
		log.info("----begin update Radius-----");

		Response<RadiusDto> response = new Response<RadiusDto>();
		List<Radius> items = new ArrayList<Radius>();

		for (RadiusDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la radius existe
			Radius entityToSave = null;
			entityToSave = radiusRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("radius id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if groupeRadius exist
			if (dto.getGroupeRadiusId() != null && dto.getGroupeRadiusId() > 0) {
				GroupeRadius existingGroupeRadius = groupeRadiusRepository.findOne(dto.getGroupeRadiusId(), false);
				if (existingGroupeRadius == null) {
					response.setStatus(functionalError
							.DATA_NOT_EXIST("groupeRadius groupeRadiusId -> " + dto.getGroupeRadiusId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setGroupeRadius(existingGroupeRadius);
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
			if (Utilities.notBlank(dto.getOffre())) {
				entityToSave.setOffre(dto.getOffre());
			}

			// if( dto.getPort() != null && !dto.getPort().isEmpty()) {
			// Map<String, Object> data = new HashMap<>();
			// data.put("code", dto.getPort().substring(0,4));
			// Map<String, Object> mapReq = new HashMap<>();
			// mapReq.put("dataZone", data);
			// try {
			// Map<String, Object> map = CallAPi(mapReq, paramsUtils.getZone());
			// if(map != null && map.get("hasError").equals(false)) {
			// List<Map<String, Object>> itemsZone = (List<Map<String, Object>>)
			// map.get("itemsZone");
			// if(Utilities.isNotEmpty(itemsZone)) {
			// entityToSave.setLocalisation(itemsZone.get(0).get("localisationLibelle").toString());
			// }
			// }
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// }
			if (Utilities.notBlank(dto.getLocalisation())) {
				entityToSave.setLocalisation(dto.getLocalisation());
			}
			if (Utilities.notBlank(dto.getDeletedAt())) {
				entityToSave.setDeletedAt(dateFormat.parse(dto.getDeletedAt()));
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Radius> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = radiusRepository.saveAll((Iterable<Radius>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("radius", locale));
				response.setHasError(true);
				return response;
			}
			List<RadiusDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? RadiusTransformer.INSTANCE.toLiteDtos(itemsSaved)
					: RadiusTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Radius-----");
		return response;
	}

	/**
	 * delete Radius by using RadiusDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@Override
	public Response<RadiusDto> delete(Request<RadiusDto> request, Locale locale) {
		log.info("----begin delete Radius-----");

		Response<RadiusDto> response = new Response<RadiusDto>();
		List<Radius> items = new ArrayList<Radius>();

		for (RadiusDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la radius existe
			Radius existingEntity = null;
			existingEntity = radiusRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("radius -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setIsDeleted(true);
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			radiusRepository.saveAll((Iterable<Radius>) items);

			response.setHasError(false);
		}

		log.info("----end delete Radius-----");
		return response;
	}

	/**
	 * get Radius by using RadiusDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@Override
	public Response<RadiusDto> getByCriteria(Request<RadiusDto> request, Locale locale) throws Exception {
		log.info("----begin get Radius-----");

		Response<RadiusDto> response = new Response<RadiusDto>();
		List<Radius> items = radiusRepository.getByCriteria(request, em, locale);
		if (items != null && !items.isEmpty()) {
			List<RadiusDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? RadiusTransformer.INSTANCE.toLiteDtos(items)
					: RadiusTransformer.INSTANCE.toDtos(items);
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
			Map<String, List<RadiusDto>> groupByOffre = itemsDto.stream()
					.collect(Collectors.groupingBy(w -> w.getOffre()));
			List<RadiusDto> datas = new ArrayList<>();
			groupByOffre.entrySet().forEach(s -> {
				RadiusDto rDto = new RadiusDto();
				rDto.setOffre(s.getKey());
				List<RadiusDto> datasGroupeRadius = new ArrayList<>();
				s.getValue().stream().forEach(x -> {
					RadiusDto grDto = new RadiusDto();
					grDto.setGroupeRadiusCode(x.getGroupeRadiusCode());
					grDto.setGroupeRadiusId(x.getGroupeRadiusId());
					grDto.setGroupeRadiusLibelle(x.getGroupeRadiusLibelle());
					grDto.setTypeOffreCode(x.getTypeOffreCode());
					grDto.setLocalisationLibelle(x.getLocalisationLibelle());
					grDto.setLocalisationId(x.getLocalisationId());
					grDto.setTypeOffreId(x.getTypeOffreId());
					grDto.setTypeOffreLibelle(x.getTypeOffreLibelle());
					datasGroupeRadius.add(grDto);
				});
				rDto.setDatasGroupeRadius(datasGroupeRadius);
				datas.add(rDto);
			});
			response.setItems(datas);
			response.setCount((long) datas.size());
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("radius", locale));
			response.setHasError(false);
			response.setItems(new ArrayList<>());
			return response;
		}
		log.info("----end get Radius-----");
		return response;
	}

	public Map<String, Object> CallAPi(Map<String, Object> data, String url) throws IOException {
		OkHttpClient client = new OkHttpClient();
		String queryString = new Gson().toJson(data);
		RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), queryString);
		okhttp3.Request request = new okhttp3.Request.Builder().url(url).method("POST", body)
				.addHeader("Content-Type", "application/json").build();
		okhttp3.Response resp = client.newCall(request).execute();
		String outputString = resp.body().string();
		log.info(outputString);
		Map<String, Object> map = new Gson().fromJson(outputString, new TypeToken<Map<String, Object>>() {
		}.getType());
		return map;
	}

	public Response<RadiusDto> getMacAdresse(Request<RadiusDto> request, Locale locale) {
		log.info("----begin  getMacAdresse-----");

		Response<RadiusDto> response = new Response<>();

		RadiusDto dto = request.getData();
		try {
			User currentUser = userRepository.findOne(request.getUser(), false);
			if (currentUser == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("Utilisateur -> " + request.getUser(), locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.isTrue(currentUser.getIsLocked())) {
				response.setStatus(functionalError
						.REQUEST_FAIL("L'utilisateur " + currentUser.getLogin() + " est verouille(e)", locale));
				response.setHasError(true);
				return response;
			}
			Map<String, Object> FieldsController = new HashMap<String, Object>();
			FieldsController.put(" : Mac", dto.getMac());
			FieldsController.put(" : Coefficient ", dto.getCoeff());

			if (!Validate.RequiredValue(FieldsController).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			if (dto.getMac() != null && !dto.getMac().isEmpty()) {
				dto.setCoeff(dto.getCoeff() != null && dto.getCoeff() > 0 ? dto.getCoeff() : 0);
				if (dto.getMac().length() != paramsUtils.getMacAdressLength()) {
					response.setStatus(functionalError
							.INVALID_FORMAT("le format de l'adresse n'est pas correct -> " + dto.getMac(), locale));
					response.setHasError(true);
					return response;
				}
				RadiusDto MacDataResponse = Utilities.MacVoixData(dto.getMac(), dto.getCoeff());
				if (MacDataResponse.isContainIllegalChar()) {
					response.setStatus(functionalError
							.INVALID_FORMAT("le format de l'adresse n'est pas correct -> " + dto.getMac(), locale));
					response.setHasError(true);
					return response;
				}
				List<RadiusDto> itemsProvisionnings = new ArrayList<>();
				String MacData = MacDataResponse.getMac();
				RadiusDto rDto = new RadiusDto();
				rDto.setMac(MacData);
				rDto.setCoeff(dto.getCoeff());
				itemsProvisionnings.add(rDto);
				response.setHasError(false);
				response.setItems(itemsProvisionnings);
				response.setStatus(functionalError.SUCCESS("", locale));
			}
			log.info("----end getMacAdresse-----");

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

	public Response<RadiusDto> getSomeInfoForWorkflow(Request<RadiusDto> request, Locale locale) {
		log.info("----begin  getSomeInfoForWorkflow-----");
		Response<RadiusDto> response = new Response<>();
		RadiusDto dto = request.getData();
		try {
			Map<String, Object> FieldsController = new HashMap<String, Object>();
			FieldsController.put("mac", dto.getMac());
			FieldsController.put("offre ", dto.getOffre());
			FieldsController.put(" modeleId", dto.getModeleId());
			if (!Validate.RequiredValue(FieldsController).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			Modele existingModele = modeleRepository.findOne(Integer.parseInt(dto.getModeleId()), Boolean.FALSE);
			if (existingModele == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("modeleId -> " + dto.getModeleId(), locale));
				response.setHasError(Boolean.TRUE);
				return response;
			}
			List<User> users = userRepository.getByIsDeleted(Boolean.FALSE, PageRequest.of(0, 1));
			if (Utilities.isNotEmpty(users)) {
				User u = users.get(0);
				List<CoefficientModele> cm = coefficientModeleRepository.getByModeleId(existingModele.getId(),
						Boolean.FALSE, PageRequest.of(0, 1));
				if (Utilities.isNotEmpty(cm)) {
					Request<RadiusDto> req = new Request<RadiusDto>();
					req.setUser(u.getId());
					RadiusDto data = new RadiusDto();
					data.setMac(dto.getMac());
					data.setCoeff(cm.get(0).getCoefMac());
					req.setData(data);
					RadiusDto dataW4 = new RadiusDto();
					Response<RadiusDto> resp = getMacAdresse(req, locale);
					if (resp != null && !resp.isHasError() && Utilities.isNotEmpty(resp.getItems())) {
						RadiusDto respE = resp.getItems().get(0);
						dataW4.setUsername(respE.getMac());
					}
					// groupeName
					Request<RadiusDto> reqGroupe = new Request<RadiusDto>();
					reqGroupe.setUser(u.getId());
					RadiusDto dataGet = new RadiusDto();
					dataGet.setOffre(dto.getOffre());
					reqGroupe.setData(dataGet);
					Response<RadiusDto> respGroupe = getByCriteria(reqGroupe, locale);
					if (respGroupe != null && !respGroupe.isHasError() && Utilities.isNotEmpty(respGroupe.getItems())) {
						RadiusDto respGr = respGroupe.getItems().get(0);
						if (Utilities.isNotEmpty(respGr.getDatasGroupeRadius())) {
							dataW4.setGroupename(respGr.getDatasGroupeRadius().get(0).getGroupeRadiusLibelle());
						}
					}
					response.setHasError(Boolean.FALSE);
					response.setItem(dataW4);
					response.setStatus(functionalError.SUCCESS("", locale));
				} else {
					response.setHasError(Boolean.TRUE);
					response.setStatus(functionalError
							.DISALLOWED_OPERATION("le modele de box n'a aucun coefficent parametré", locale));
					return response;
				}
			}
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
		log.info("----end  getSomeInfoForWorkflow-----");
		return response;
	}

	/**
	 * get full RadiusDto by using Radius as object.
	 *
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private RadiusDto getFullInfos(RadiusDto dto, Integer size, Boolean isSimpleLoading, Locale locale)
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
}
