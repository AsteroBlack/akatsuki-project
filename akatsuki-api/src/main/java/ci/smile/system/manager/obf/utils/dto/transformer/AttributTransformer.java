

/*
 * Java transformer for entity table attribut 
 * Created on 2021-04-22 ( Time 11:55:13 )
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
 * TRANSFORMER for table "attribut"
 * 
 * @author Geo
 *
 */
@Mapper
public interface AttributTransformer {

	AttributTransformer INSTANCE = Mappers.getMapper(AttributTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
	})
	AttributDto toDto(Attribut entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<AttributDto> toDtos(List<Attribut> entities) throws ParseException;

    default AttributDto toLiteDto(Attribut entity) {
		if (entity == null) {
			return null;
		}
		AttributDto dto = new AttributDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<AttributDto> toLiteDtos(List<Attribut> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<AttributDto> dtos = new ArrayList<AttributDto>();
		for (Attribut entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.attribut", target="attribut"),
		@Mapping(source="dto.op", target="op"),
		@Mapping(source="dto.value", target="value"),
		@Mapping(source="dto.type", target="type"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
	})
    Attribut toEntity(AttributDto dto) throws ParseException;

    //List<Attribut> toEntities(List<AttributDto> dtos) throws ParseException;

}
