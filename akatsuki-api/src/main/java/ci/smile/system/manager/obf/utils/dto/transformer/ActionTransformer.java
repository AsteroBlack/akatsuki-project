

/*
 * Java transformer for entity table Action 
 * Created on 2019-12-19 ( Time 23:54:47 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
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

import ci.smile.system.manager.obf.dao.entity.Action;
import ci.smile.system.manager.obf.dao.entity.Menus;
import ci.smile.system.manager.obf.utils.contract.FullTransformerQualifier;
import ci.smile.system.manager.obf.utils.dto.ActionDto;


/**
 * TRANSFORMER for table "Action"
 * 
 * @author Geo
 *
 */
@Mapper
public interface ActionTransformer {

	ActionTransformer INSTANCE = Mappers.getMapper(ActionTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.libelle", target="text"),
		@Mapping(source="entity.menus.id", target="menusId"),
		@Mapping(source="entity.menus.libelle", target="menusLibelle"),
		@Mapping(source="entity.menus.code", target="menusCode"),
		@Mapping(source="entity.parent.id", target="parentActionId"),
		@Mapping(source="entity.parent.libelle", target="parentActionLibelle"),
		@Mapping(source="entity.parent.code", target="parentActionCode"),
	})
	ActionDto toDto(Action entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<ActionDto> toDtos(List<Action> entities) throws ParseException;

    default ActionDto toLiteDto(Action entity) {
		if (entity == null) {
			return null;
		}
		ActionDto dto = new ActionDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	default List<ActionDto> toLiteDtos(List<Action> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<ActionDto> dtos = new ArrayList<ActionDto>();
		for (Action entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.libelle", target="libelle"),
		@Mapping(source="dto.code", target="code"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isLocked", target="isLocked"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="menus", target="menus"),
		@Mapping(source="parent", target="parent"),
	})
    Action toEntity(ActionDto dto, Menus menus, Action parent) throws ParseException;

    //List<Action> toEntities(List<ActionDto> dtos) throws ParseException;

}
