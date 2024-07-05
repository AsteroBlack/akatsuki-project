

/*
 * Java transformer for entity table type_attribut 
 * Created on 2021-04-22 ( Time 11:56:53 )
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
 * TRANSFORMER for table "type_attribut"
 * 
 * @author Geo
 *
 */
@Mapper
public interface TypeAttributTransformer {

	TypeAttributTransformer INSTANCE = Mappers.getMapper(TypeAttributTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.attribut.id", target="attributId"),
		@Mapping(source="entity.attribut.attribut", target="attribut"),
		@Mapping(source="entity.attribut.op", target="op"),
		@Mapping(source="entity.attribut.value", target="value"),
		@Mapping(source="entity.attribut.type", target="type"),
	})
	TypeAttributDto toDto(TypeAttribut entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<TypeAttributDto> toDtos(List<TypeAttribut> entities) throws ParseException;

    default TypeAttributDto toLiteDto(TypeAttribut entity) {
		if (entity == null) {
			return null;
		}
		TypeAttributDto dto = new TypeAttributDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<TypeAttributDto> toLiteDtos(List<TypeAttribut> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<TypeAttributDto> dtos = new ArrayList<TypeAttributDto>();
		for (TypeAttribut entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.code", target="code"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="attribut", target="attribut"),
	})
    TypeAttribut toEntity(TypeAttributDto dto, Attribut attribut) throws ParseException;

    //List<TypeAttribut> toEntities(List<TypeAttributDto> dtos) throws ParseException;

}
