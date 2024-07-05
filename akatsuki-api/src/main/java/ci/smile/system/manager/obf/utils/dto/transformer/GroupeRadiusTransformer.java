

/*
 * Java transformer for entity table groupe_radius 
 * Created on 2023-01-14 ( Time 17:48:14 )
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
 * TRANSFORMER for table "groupe_radius"
 * 
 * @author Geo
 *
 */
@Mapper
public interface GroupeRadiusTransformer {

	GroupeRadiusTransformer INSTANCE = Mappers.getMapper(GroupeRadiusTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.localisation.id", target="localisationId"),
		@Mapping(source="entity.localisation.code", target="localisationCode"),
		@Mapping(source="entity.localisation.libelle", target="localisationLibelle"),
	})
	GroupeRadiusDto toDto(GroupeRadius entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<GroupeRadiusDto> toDtos(List<GroupeRadius> entities) throws ParseException;

    default GroupeRadiusDto toLiteDto(GroupeRadius entity) {
		if (entity == null) {
			return null;
		}
		GroupeRadiusDto dto = new GroupeRadiusDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	default List<GroupeRadiusDto> toLiteDtos(List<GroupeRadius> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<GroupeRadiusDto> dtos = new ArrayList<GroupeRadiusDto>();
		for (GroupeRadius entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.code", target="code"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.isSuspend", target="isSuspend"),
		@Mapping(source="dto.libelle", target="libelle"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="localisation", target="localisation"),
	})
    GroupeRadius toEntity(GroupeRadiusDto dto, Localisation localisation) throws ParseException;

    //List<GroupeRadius> toEntities(List<GroupeRadiusDto> dtos) throws ParseException;

}
