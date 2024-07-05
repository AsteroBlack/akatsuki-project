

/*
 * Java transformer for entity table localisation 
 * Created on 2021-03-15 ( Time 12:39:07 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
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
 * TRANSFORMER for table "localisation"
 * 
 * @author Geo
 *
 */
@Mapper
public interface LocalisationTransformer {

	LocalisationTransformer INSTANCE = Mappers.getMapper(LocalisationTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
	})
	LocalisationDto toDto(Localisation entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<LocalisationDto> toDtos(List<Localisation> entities) throws ParseException;

    default LocalisationDto toLiteDto(Localisation entity) {
		if (entity == null) {
			return null;
		}
		LocalisationDto dto = new LocalisationDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	default List<LocalisationDto> toLiteDtos(List<Localisation> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<LocalisationDto> dtos = new ArrayList<LocalisationDto>();
		for (Localisation entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.code", target="code"),
		@Mapping(source="dto.libelle", target="libelle"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
	})
    Localisation toEntity(LocalisationDto dto) throws ParseException;

    //List<Localisation> toEntities(List<LocalisationDto> dtos) throws ParseException;

}
