

/*
 * Java transformer for entity table offer_groupe 
 * Created on 2023-01-16 ( Time 14:17:29 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils.dto.transformer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import ci.smile.system.manager.obf.utils.contract.*;
import ci.smile.system.manager.obf.utils.dto.*;
import ci.smile.system.manager.obf.dao.entity.*;


/**
 * TRANSFORMER for table "offer_groupe"
 * 
 * @author Geo
 *
 */
@Mapper
public interface OfferGroupeTransformer {

	OfferGroupeTransformer INSTANCE = Mappers.getMapper(OfferGroupeTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.plateforme.id", target="idPlateforme"),
		@Mapping(source="entity.plateforme.code", target="plateformeCode"),
		@Mapping(source="entity.plateforme.libelle", target="plateformeLibelle"),
		@Mapping(source="entity.offer.id", target="idOffre"),
		@Mapping(source="entity.offer.libelle", target="offerLibelle"),
		@Mapping(source="entity.groupe.id", target="idGroupe"),
		@Mapping(source="entity.groupe.libelle", target="groupeLibelle"),
	})
	OfferGroupeDto toDto(OfferGroupe entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<OfferGroupeDto> toDtos(List<OfferGroupe> entities) throws ParseException;

    default OfferGroupeDto toLiteDto(OfferGroupe entity) {
		if (entity == null) {
			return null;
		}
		OfferGroupeDto dto = new OfferGroupeDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<OfferGroupeDto> toLiteDtos(List<OfferGroupe> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<OfferGroupeDto> dtos = new ArrayList<OfferGroupeDto>();
		for (OfferGroupe entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="plateforme", target="plateforme"),
		@Mapping(source="offer", target="offer"),
		@Mapping(source="groupe", target="groupe"),
	})
    OfferGroupe toEntity(OfferGroupeDto dto, Plateforme plateforme, Offer offer, Groupe groupe) throws ParseException;

    //List<OfferGroupe> toEntities(List<OfferGroupeDto> dtos) throws ParseException;

}
