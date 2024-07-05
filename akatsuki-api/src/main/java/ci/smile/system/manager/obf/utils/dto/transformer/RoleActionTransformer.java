

/*
 * Java transformer for entity table user_role 
 * Created on 2019-12-19 ( Time 23:54:48 )
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
import ci.smile.system.manager.obf.dao.entity.Role;
import ci.smile.system.manager.obf.dao.entity.RoleAction;
import ci.smile.system.manager.obf.utils.contract.FullTransformerQualifier;
import ci.smile.system.manager.obf.utils.dto.RoleActionDto;


/**
 * TRANSFORMER for table "user_role"
 * 
 * @author Geo
 *
 */
@Mapper
public interface RoleActionTransformer {

	RoleActionTransformer INSTANCE = Mappers.getMapper(RoleActionTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.role.id", target="roleId"),
		@Mapping(source="entity.role.libelle", target="roleLibelle"),
		@Mapping(source="entity.role.code", target="roleCode"),
		@Mapping(source="entity.action.id", target="actionId"),
		@Mapping(source="entity.action.libelle", target="actionLibelle"),
		@Mapping(source="entity.action.code", target="actionCode"),
	})
	RoleActionDto toDto(RoleAction entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<RoleActionDto> toDtos(List<RoleAction> entities) throws ParseException;

    default RoleActionDto toLiteDto(RoleAction entity) {
		if (entity == null) {
			return null;
		}
		RoleActionDto dto = new RoleActionDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<RoleActionDto> toLiteDtos(List<RoleAction> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<RoleActionDto> dtos = new ArrayList<RoleActionDto>();
		for (RoleAction entity : entities) {
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
		@Mapping(source="role", target="role"),
		@Mapping(source="action", target="action"),
	})
    RoleAction toEntity(RoleActionDto dto, Role role, Action action) throws ParseException;

    //List<UserRole> toEntities(List<UserRoleDto> dtos) throws ParseException;

}
