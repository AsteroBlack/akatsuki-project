

/*
 * Java transformer for entity table groupe 
 * Created on 2023-01-18 ( Time 11:59:00 )
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
 * TRANSFORMER for table "groupe"
 * 
 * @author Geo
 *
 */
@Mapper
public interface GroupeTransformer {

	GroupeTransformer INSTANCE = Mappers.getMapper(GroupeTransformer.class);

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
	})
	GroupeDto toDto(Groupe entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<GroupeDto> toDtos(List<Groupe> entities) throws ParseException;

    default GroupeDto toLiteDto(Groupe entity) {
		if (entity == null) {
			return null;
		}
		GroupeDto dto = new GroupeDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	default List<GroupeDto> toLiteDtos(List<Groupe> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<GroupeDto> dtos = new ArrayList<GroupeDto>();
		for (Groupe entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.libelle", target="libelle"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.modeleBoxe", target="modeleBoxe"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="plateforme", target="plateforme"),
		@Mapping(source="offer", target="offer"),
	})
    Groupe toEntity(GroupeDto dto, Plateforme plateforme, Offer offer) throws ParseException;

    //List<Groupe> toEntities(List<GroupeDto> dtos) throws ParseException;

}
