

/*
 * Java transformer for entity table menus 
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

import ci.smile.system.manager.obf.dao.entity.Menus;
import ci.smile.system.manager.obf.dao.entity.Module;
import ci.smile.system.manager.obf.utils.contract.FullTransformerQualifier;
import ci.smile.system.manager.obf.utils.dto.MenusDto;


/**
 * TRANSFORMER for table "menus"
 * 
 * @author Geo
 *
 */
@Mapper
public interface MenusTransformer {

	MenusTransformer INSTANCE = Mappers.getMapper(MenusTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.module.id", target="moduleId"),
		@Mapping(source="entity.module.libelle", target="moduleLibelle"),
		@Mapping(source="entity.module.code", target="moduleCode"),
		@Mapping(source="entity.parent.id", target="parentMenusId"),
		@Mapping(source="entity.parent.libelle", target="parentMenusLibelle"),
		@Mapping(source="entity.parent.code", target="parentMenusCode"),
	})
	MenusDto toDto(Menus entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<MenusDto> toDtos(List<Menus> entities) throws ParseException;

    default MenusDto toLiteDto(Menus entity) {
		if (entity == null) {
			return null;
		}
		MenusDto dto = new MenusDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	default List<MenusDto> toLiteDtos(List<Menus> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<MenusDto> dtos = new ArrayList<MenusDto>();
		for (Menus entity : entities) {
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
		@Mapping(source="dto.isLocked", target="isLocked"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="module", target="module"),
		@Mapping(source="parent", target="parent"),
	})
    Menus toEntity(MenusDto dto, Module module, Menus parent) throws ParseException;

    //List<Menus> toEntities(List<MenusDto> dtos) throws ParseException;

}
