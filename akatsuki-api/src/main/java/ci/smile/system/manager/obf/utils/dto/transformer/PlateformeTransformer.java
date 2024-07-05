

/*
 * Java transformer for entity table plateforme 
 * Created on 2022-11-07 ( Time 16:50:07 )
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
 * TRANSFORMER for table "plateforme"
 * 
 * @author Geo
 *
 */
@Mapper
public interface PlateformeTransformer {

	PlateformeTransformer INSTANCE = Mappers.getMapper(PlateformeTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
	})
	PlateformeDto toDto(Plateforme entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<PlateformeDto> toDtos(List<Plateforme> entities) throws ParseException;

    default PlateformeDto toLiteDto(Plateforme entity) {
		if (entity == null) {
			return null;
		}
		PlateformeDto dto = new PlateformeDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	default List<PlateformeDto> toLiteDtos(List<Plateforme> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<PlateformeDto> dtos = new ArrayList<PlateformeDto>();
		for (Plateforme entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.code", target="code"),
		@Mapping(source="dto.libelle", target="libelle"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
	})
    Plateforme toEntity(PlateformeDto dto) throws ParseException;

    //List<Plateforme> toEntities(List<PlateformeDto> dtos) throws ParseException;

}
