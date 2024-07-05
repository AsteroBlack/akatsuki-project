
/*
 * Java business for entity table offer 
 * Created on 2022-11-07 ( Time 17:41:42 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.business;

import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.security.acl.Group;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import ci.smile.system.manager.obf.dao.entity.Groupe;
import ci.smile.system.manager.obf.dao.entity.GroupeRadius;
import ci.smile.system.manager.obf.dao.entity.Offer;
import ci.smile.system.manager.obf.dao.entity.OfferGroupe;
import ci.smile.system.manager.obf.dao.entity.OfferOlt;
import ci.smile.system.manager.obf.dao.entity.Plateforme;
import ci.smile.system.manager.obf.dao.entity.TypeOffre;
import ci.smile.system.manager.obf.dao.repository.GroupeRadiusRepository;
import ci.smile.system.manager.obf.dao.repository.GroupeRepository;
import ci.smile.system.manager.obf.dao.repository.OfferGroupeRepository;
import ci.smile.system.manager.obf.dao.repository.OfferOltRepository;
import ci.smile.system.manager.obf.dao.repository.OfferRepository;
import ci.smile.system.manager.obf.dao.repository.PlateformeRepository;
import ci.smile.system.manager.obf.dao.repository.TypeOffreRepository;
import ci.smile.system.manager.obf.utils.*;
import ci.smile.system.manager.obf.utils.dto.*;
import ci.smile.system.manager.obf.utils.dto.customize._Parameter;
import ci.smile.system.manager.obf.utils.dto.transformer.GroupeTransformer;
import ci.smile.system.manager.obf.utils.dto.transformer.OfferGroupeTransformer;
import ci.smile.system.manager.obf.utils.dto.transformer.OfferOltTransformer;
import ci.smile.system.manager.obf.utils.dto.transformer.OfferTransformer;
import ci.smile.system.manager.obf.utils.enums.*;
import ci.smile.system.manager.obf.utils.contract.*;

/**
 * BUSINESS for table "offer"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class OfferBusiness implements IBasicBusiness<Request<OfferDto>, Response<OfferDto>> {
	private Response<OfferDto> response;
	@Autowired
	private OfferRepository offerRepository;
	@Autowired
	private GroupeRadiusRepository groupeRadiusRepository;
	@Autowired
	private TypeOffreRepository typeOffreRepository;
	@Autowired
	private GroupeRepository groupeRepository;
	@Autowired
	private PlateformeRepository plateformeRepository;
	@Autowired
	private OfferOltRepository offerOltRepository; 
	@Autowired
	private OfferGroupeRepository offerGroupeRepository;
	@Autowired
	private OfferGroupeBusiness offerGroupeBusiness;
	@Autowired
	private GroupeBusiness groupeBusiness;
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

	public OfferBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create Offer by using OfferDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<OfferDto> create(Request<OfferDto> request, Locale locale) throws ParseException {
		log.info("----begin create Offer-----");

		Response<OfferDto> response = new Response<OfferDto>();
		List<Offer> items = new ArrayList<Offer>();
		List<GroupeDto> groupeDtos = new ArrayList<>();
		List<Offer> itemsSaved = new ArrayList<Offer>();
		for (OfferDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("codeOffer", dto.getCodeOffer());
			fieldsToVerify.put("codeTypeClient ", dto.getCodeTypeClient());
//			fieldsToVerify.put("idTypeOffre", dto.getIdTypeOffre());
			fieldsToVerify.put("datasGroupe", dto.getDatasGroupe());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			List<Offer> existingEntity = null;
			// verif unique libelle in db
			existingEntity = offerRepository.findByCodeOffer(dto.getCodeOffer(), false);
			if (Utilities.isNotEmpty(existingEntity)) {
				response.setStatus(functionalError.DATA_EXIST("offer CodeOffer -> " + dto.getCodeOffer(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique libelle in items to save
			if (items.stream().anyMatch(a -> a.getCodeOffer().equalsIgnoreCase(dto.getCodeOffer()))) {
				response.setStatus(functionalError.DATA_DUPLICATE("CodeOffer ", locale));
				response.setHasError(true);
				return response;
			}

			Offer entityToSave = null;
			entityToSave = OfferTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			if (entityToSave != null) {
				Offer offerSaved = offerRepository.save(entityToSave);
				itemsSaved.add(offerSaved);
				if (Utilities.isNotEmpty(dto.getDatasGroupe())) {
					for (GroupeDto offer : dto.getDatasGroupe()) {
						Plateforme plateforme = plateformeRepository.findOne(offer.getIdPlateforme(), Boolean.FALSE);
						if (plateforme != null) {
							if (plateforme.getLibelle().equalsIgnoreCase("radius")) {
								if (offerSaved != null) {
									items.add(offerSaved);
									Groupe searchGroup = groupeRepository.findOneOffreAndPlateforme(offerSaved.getId(),
											plateforme.getId(), Boolean.FALSE);
									if (searchGroup == null) {
										GroupeDto groupeDto = new GroupeDto();
										groupeDto.setCreatedBy(request.getUser());
										groupeDto.setIsDeleted(Boolean.FALSE);
										groupeDto.setCreatedAt(dateTimeFormat.format(new Date()));
										groupeDto.setIdPlateforme(plateforme.getId());
										groupeDto.setLibelle(offer.getLibelle());
										groupeDto.setIdOffre(offerSaved.getId());
										groupeDtos.add(groupeDto);
									} else {
										response.setStatus(functionalError.DATA_EXIST(
												"Offre Deja Associé -> " + plateforme.getLibelle(), locale));
										response.setHasError(true);
										return response;
									}
								}
							} else {
								if (plateforme.getLibelle().equalsIgnoreCase("olt")) {
									if (offerSaved != null) {
										Groupe searchGroup = groupeRepository.findOneOffreAndPlateforme(
												offerSaved.getId(), plateforme.getId(), Boolean.FALSE);
										if (searchGroup == null) {
											GroupeDto groupeDto = new GroupeDto();
											groupeDto.setCreatedBy(request.getUser());
											groupeDto.setIsDeleted(Boolean.FALSE);
											groupeDto.setCreatedAt(dateTimeFormat.format(new Date()));
											groupeDto.setIdPlateforme(plateforme.getId());
											groupeDto.setLibelle(offer.getLibelle());
											groupeDto.setIdOffre(offerSaved.getId());
											groupeDto.setModeleBoxe("ZTE-F660");
											groupeDtos.add(groupeDto);
										} else {
											response.setStatus(functionalError.DATA_EXIST(
													"Offre Deja Associé -> " + plateforme.getLibelle(), locale));
											response.setHasError(true);
											return response;
										}
									}
								} else {
									response.setStatus(functionalError
											.DATA_NOT_EXIST("Aucune plateforme " + offer.getIdPlateforme(), locale));
									response.setHasError(true);
									return response;
								}
							}
						}
					}
				}
			}
		}
		if (Utilities.isNotEmpty(groupeDtos)) {
			Request<GroupeDto> requestGroupe = new Request<>();
			requestGroupe.setDatas(groupeDtos);
			groupeBusiness.create(requestGroupe, locale);
		}
		if (!itemsSaved.isEmpty()) {
			List<OfferDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? OfferTransformer.INSTANCE.toLiteDtos(itemsSaved)
					: OfferTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Offer-----");
		return response;
	}

	public Response<OfferOltDto> getTemplateByOffer(Request<OfferDto> request, Locale locale) throws ParseException {
		log.info("----begin create Offer-----");
		Response<OfferOltDto> response = new Response<OfferOltDto>();
		OfferDto dto = request.getData();
		// Definir les parametres obligatoires
		Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
		fieldsToVerify.put("codeOffer", dto.getCodeOffer());
		fieldsToVerify.put("modeleBoxe", dto.getModeleBoxe());
		if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
			response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
			response.setHasError(true);
			return response;
		}
		Offer offer = offerRepository.findByOneCodeOffer(dto.getCodeOffer(), Boolean.FALSE);
		Plateforme plateforme = plateformeRepository.findByLibelle("olt", Boolean.FALSE);
		if (plateforme != null) {
			if (offer != null) {
//				List<Groupe> groupe = groupeRepository.findByPlateformeModelOffre(offer.getId(), dto.getModeleBoxe(), 2,
//						Boolean.FALSE);
				Groupe groupetemplates = groupeRepository.findOneOffreAndPlateformeModele(offer.getId(),
						dto.getModeleBoxe(), plateforme.getId(), Boolean.FALSE);
				if (groupetemplates != null) {
					OfferOlt olt = offerOltRepository.findByOneTemplate(groupetemplates.getLibelle(), Boolean.FALSE);
					if (olt != null) {
						OfferOltDto offerOltDtos = OfferOltTransformer.INSTANCE.toDto(olt);
						offerOltDtos.setModeleBoxe(groupetemplates.getModeleBoxe());
						response.setItem(offerOltDtos);
						response.setHasError(false);
						response.setCount(Long.valueOf(1));
					} else {
						response.setStatus(functionalError.DATA_EMPTY(
								" Aucune Offre OLT" + groupetemplates.getLibelle() + dto.getModeleBoxe(), locale));
						response.setHasError(true);
						return response;
					}
				}
			} else {
				response.setStatus(
						functionalError.DATA_EMPTY("" + dto.getCodeOffer() + "  " + dto.getModeleBoxe(), locale));
				response.setHasError(true);
				return response;
			}
		}
		log.info("----end create Offer-----");
		return response;
	}

	public Response<OfferDto> getInfosRadius(Request<OfferDto> request, Locale locale) throws ParseException {
		log.info("----begin create Offer-----");
		List<_Parameter> _Parameters = new ArrayList<>();
		Response<OfferDto> response = new Response<OfferDto>();
		OfferDto dto = request.getData();
		// Definir les parametres obligatoires
		Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
		fieldsToVerify.put("codeOffer", dto.getCodeOffer());
//			fieldsToVerify.put("ontMacLabel", dto.getOntMacLabel());
//			fieldsToVerify.put("port", dto.getPort());
//			fieldsToVerify.put("sn", dto.getSn());
//			fieldsToVerify.put("nd", dto.getNd());
//			fieldsToVerify.put("expirationDate", dto.getExpirationDate());
		if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
			response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
			response.setHasError(true);
			return response;
		}
		Plateforme plateforme = plateformeRepository.findByLibelle("radius", Boolean.FALSE);
		OfferDto offerDto = new OfferDto();
		Offer offer = offerRepository.findByOneCodeOffer(dto.getCodeOffer(), Boolean.FALSE);
		if (offer == null) {
			response.setStatus(functionalError.DATA_EMPTY("Aucune Offre" + dto.getCodeOffer(), locale));
			response.setHasError(true);
			return response;
		}
		if (offer != null) {
			offerDto.setCodeTypeClient(offer.getCodeTypeClient());
		}
		if (plateforme != null) {
			if (offer != null) {
				Groupe groupeRadius = groupeRepository.findOneOffreAndPlateforme(offer.getId(), plateforme.getId(),
						Boolean.FALSE);
				if (groupeRadius != null) {
					offerDto.setGroupename(groupeRadius.getLibelle());
				}
//				List<Groupe> groupesValue = groupeRepository.findByPlateformeIdOffre(offer.getId(), 1);
//				List<Groupe> groupesValue = groupeRepository.findByPlateformeIdOffre(1);
//				if (Utilities.isNotEmpty(groupesValue)) {
//					Groupe groupeRadius = groupesValue.get(0);
//					offerDto.setGroupename(groupeRadius.getLibelle());
//				}
			}
		}

		if (Utilities.notBlank(dto.getExpirationDate())) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date = format.parse(dto.getExpirationDate());
			SimpleDateFormat formatAll = new SimpleDateFormat("MMM dd yyyy");
			String string = formatAll.format(date).replace(".", "");
			String s1 = string.substring(0, 1).toUpperCase();
			String s2 = string.substring(1);
			_Parameter parameter = new _Parameter();
			parameter.setKey("Expiration");
			parameter.setValue(s1 + s2);
			parameter.setType("check");
			_Parameters.add(parameter);
		}
		String username = "";
		if (dto.getOntMacLabel() != null) {
			int i = 0;
			for (String part : Utilities.getParts(dto.getOntMacLabel(), 2)) {
				i++;
				int size = Utilities.getParts(dto.getOntMacLabel(), 2).size();
				if (i < size) {
					if (part != null) {
						username += part.toLowerCase() + ":";
					}
				} else {
					username += part;
				}
			}
			offerDto.setUsername(username);
		}
		_Parameter parameter = new _Parameter();
		parameter.setKey("ADSL-Agent-Remote-Id");
		parameter.setValue(dto.getNd() != null ? dto.getNd() : "");
		parameter.setType("check");
		_Parameters.add(parameter);
		_Parameter parametertwo = new _Parameter();
		parametertwo.setKey("ADSL-Agent-Circuit-Id");
		String finalPort = null;
		if (dto.getPort() != null) {
			finalPort = dto.getPort();
			if (dto.getSn() != null) {
				finalPort = finalPort + "/" + dto.getSn();
			}
		}
		parametertwo.setValue(finalPort);
		parametertwo.setType("check");
		_Parameters.add(parametertwo);
		offerDto.setDatasParameter(_Parameters);
		if (offerDto != null) {
			response.setItem(offerDto);
			response.setHasError(false);
			response.setCount(Long.valueOf(1));
		}
		log.info("----end create Offer-----");
		return response;
	}

	/**
	 * update Offer by using OfferDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<OfferDto> update(Request<OfferDto> request, Locale locale) throws ParseException {
		log.info("----begin update Offer-----");
		List<GroupeDto> groupeDtos = new ArrayList<>();
		List<GroupeDto> groupeCreateDtos = new ArrayList<>();
		Response<OfferDto> response = new Response<OfferDto>();
		List<Offer> items = new ArrayList<Offer>();
		for (OfferDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
//			fieldsToVerify.put("datasGroupe", dto.getDatasGroupe());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}
			// Verifier si la offer existe
			Offer entityToSave = null;
			entityToSave = offerRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("offer id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}
			if (Utilities.notBlank(dto.getDescription())) {
				entityToSave.setDescription(dto.getDescription());
			}
			if (Utilities.notBlank(dto.getCodeOffer())) {
				entityToSave.setCodeOffer(dto.getCodeOffer());
			}
			if (Utilities.notBlank(dto.getCodeTypeClient())) {
				entityToSave.setCodeTypeClient(dto.getCodeTypeClient());
			}
			if (dto.getCreatedBy() != null && dto.getCreatedBy() > 0) {
				entityToSave.setCreatedBy(dto.getCreatedBy());
			}
//			if (Utilities.notBlank(dto.getDeletedAt())) {
//				entityToSave.setDeletedAt(dateFormat.parse(dto.getDeletedAt()));
//			}
			if (dto.getDeletedBy() != null && dto.getDeletedBy() > 0) {
				entityToSave.setDeletedBy(dto.getDeletedBy());
			}
			if (dto.getIsSuspend() != null) {
				entityToSave.setIsSuspend(dto.getIsSuspend());
			}
			if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) {
				entityToSave.setUpdatedBy(dto.getUpdatedBy());
			}
			if (Utilities.notBlank(dto.getLibelle())) {
				entityToSave.setLibelle(dto.getLibelle());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
			if (Utilities.isNotEmpty(dto.getDatasGroupe())) {
				if (dto.getDatasGroupe().size() > 2) {
					response.setStatus(functionalError.DISALLOWED_OPERATION(
							"Impossible de traiter cette situation pour " + entityToSave.getCodeOffer(), locale));
					response.setHasError(true);
					return response;
				}
				List<Groupe> oneOffer = groupeRepository.findByIdOffre(dto.getId(), Boolean.FALSE);

				for (GroupeDto offer : dto.getDatasGroupe()) {
					Plateforme plateforme = plateformeRepository.findOne(offer.getIdPlateforme(), Boolean.FALSE);
					if (plateforme != null) {
						if (Utilities.isNotEmpty(oneOffer)) {
							if (oneOffer.size() <= 2) {
								Plateforme searchPlateforme = plateformeRepository.findOne(offer.getIdPlateforme(),
										Boolean.FALSE);
								if (searchPlateforme != null) {
									if (offer.getId() != null) {
										Groupe groupe = groupeRepository.findOneGroupeOffreAndPlateforme(offer.getId(),
												dto.getId(), searchPlateforme.getId(), Boolean.FALSE);
										if (groupe != null) {
											GroupeDto groupeDto = new GroupeDto();
											groupeDto = GroupeTransformer.INSTANCE.toDto(groupe);
											if (offer.getLibelle() != null) {
												groupeDto.setLibelle(offer.getLibelle());
											}
											if (searchPlateforme.getLibelle().equalsIgnoreCase("olt")) {
												groupeDto.setModeleBoxe("ZTE-F660");
											}
											groupeDto.setIdPlateforme(searchPlateforme.getId());
											groupeDto.setUpdatedAt(dateTimeFormat.format(new Date()));
											groupeDtos.add(groupeDto);
										}
									} else {
										if (oneOffer.size() < 2) {
											Groupe groupe = groupeRepository.findOneOffreAndPlateforme(dto.getId(),
													offer.getIdPlateforme(), Boolean.FALSE);
											if (groupe != null) {
												response.setStatus(functionalError.DISALLOWED_OPERATION(
														"Plateforme " + plateforme.getLibelle() + " deja associé à "
																+ entityToSave.getCodeOffer(),
														locale));
												response.setHasError(true);
												return response;
											}
											GroupeDto groupeDto = new GroupeDto();
											groupeDto.setCreatedBy(request.getUser());
											groupeDto.setIsDeleted(Boolean.FALSE);
											if (plateforme.getLibelle().equalsIgnoreCase("olt")) {
												groupeDto.setModeleBoxe("ZTE-F660");
											}
											groupeDto.setCreatedAt(dateTimeFormat.format(new Date()));
											groupeDto.setIdPlateforme(plateforme.getId());
											groupeDto.setLibelle(offer.getLibelle());
											groupeDto.setIdOffre(dto.getId());
											groupeCreateDtos.add(groupeDto);
										}
									}
								}
							}

						} else {
							GroupeDto groupeDto = new GroupeDto();
							groupeDto.setCreatedBy(request.getUser());
							groupeDto.setIsDeleted(Boolean.FALSE);
							if (plateforme.getLibelle().equalsIgnoreCase("olt")) {
								groupeDto.setModeleBoxe("ZTE-F660");
							}
							groupeDto.setCreatedAt(dateTimeFormat.format(new Date()));
							groupeDto.setIdPlateforme(plateforme.getId());
							groupeDto.setLibelle(offer.getLibelle());
							groupeDto.setIdOffre(dto.getId());
							groupeCreateDtos.add(groupeDto);

						}

					}
				}
			}
		}
		if (Utilities.isNotEmpty(groupeDtos)) {
			Request<GroupeDto> requestGroupe = new Request<>();
			requestGroupe.setDatas(groupeDtos);
			groupeBusiness.update(requestGroupe, locale);
		}
		if (Utilities.isNotEmpty(groupeCreateDtos)) {
			Request<GroupeDto> requestGroupe = new Request<>();
			requestGroupe.setDatas(groupeCreateDtos);
			groupeBusiness.create(requestGroupe, locale);
		}
		if (!items.isEmpty()) {
			List<OfferDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? OfferTransformer.INSTANCE.toLiteDtos(items)
					: OfferTransformer.INSTANCE.toDtos(items);

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
			response.setHasError(false);
		}

		log.info("----end update Offer-----");
		return response;
	}

	/**
	 * delete Offer by using OfferDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<OfferDto> delete(Request<OfferDto> request, Locale locale) {
		log.info("----begin delete Offer-----");

		Response<OfferDto> response = new Response<OfferDto>();
		List<Offer> items = new ArrayList<Offer>();

		for (OfferDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la offer existe
			Offer existingEntity = null;
			existingEntity = offerRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("offer  -> " + dto.getId(), locale));
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
			List<Offer> itemsDelete = new ArrayList<>();
			// supprimer les donnees en base
			itemsDelete = offerRepository.saveAll((Iterable<Offer>) items);
			for (Offer offer : itemsDelete) {
				List<Groupe> offerGroupe = groupeRepository.findByIdOffre(offer.getId(), Boolean.FALSE);
				if (Utilities.isNotEmpty(offerGroupe)) {
					for (Groupe groupeDelete : offerGroupe) {
						groupeDelete.setIsDeleted(Boolean.TRUE);
						groupeRepository.save(groupeDelete);
					}
				}
			}

			response.setHasError(false);
		}

		log.info("----end delete Offer-----");
		return response;
	}

	/**
	 * get Offer by using OfferDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<OfferDto> getByCriteria(Request<OfferDto> request, Locale locale) throws Exception {
		log.info("----begin get Offer-----");

		Response<OfferDto> response = new Response<OfferDto>();
		List<Offer> items = offerRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<OfferDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? OfferTransformer.INSTANCE.toLiteDtos(items)
					: OfferTransformer.INSTANCE.toDtos(items);

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
			response.setCount(offerRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("offer", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Offer-----");
		return response;
	}

	/**
	 * get full OfferDto by using Offer as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private OfferDto getFullInfos(OfferDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		List<Groupe> groupes = new ArrayList<>();
		groupes = groupeRepository.findByIdOffre(dto.getId(), Boolean.FALSE);
		if (Utilities.isNotEmpty(groupes)) {
			dto.setDatasGroupe(GroupeTransformer.INSTANCE.toDtos(groupes));
		}
		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}
		return dto;
	}

	public Response<OfferDto> custom(Request<OfferDto> request, Locale locale) {
		log.info("----begin custom OfferDto-----");
		Response<OfferDto> response = new Response<OfferDto>();

		response.setHasError(false);
		response.setCount(1L);
		response.setItems(Arrays.asList(new OfferDto()));
		log.info("----end custom OfferDto-----");
		return response;
	}
}
