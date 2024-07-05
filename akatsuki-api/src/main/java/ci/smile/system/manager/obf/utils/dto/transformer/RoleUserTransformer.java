

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

import ci.smile.system.manager.obf.dao.entity.Role;
import ci.smile.system.manager.obf.dao.entity.RoleUser;
import ci.smile.system.manager.obf.dao.entity.User;
import ci.smile.system.manager.obf.utils.contract.FullTransformerQualifier;
import ci.smile.system.manager.obf.utils.dto.RoleUserDto;


/**
 * TRANSFORMER for table "user_role"
 * 
 * @author Geo
 *
 */
@Mapper
public interface RoleUserTransformer {

	RoleUserTransformer INSTANCE = Mappers.getMapper(RoleUserTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.role.id", target="roleId"),
		@Mapping(source="entity.role.libelle", target="roleLibelle"),
		@Mapping(source="entity.role.code", target="roleCode"),
		@Mapping(source="entity.user.id", target="userId"),
		@Mapping(source="entity.user.nom", target="userNom"),
		@Mapping(source="entity.user.prenom", target="userPrenom"),
		@Mapping(source="entity.user.login", target="userLogin"),
	})
	RoleUserDto toDto(RoleUser entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<RoleUserDto> toDtos(List<RoleUser> entities) throws ParseException;

    default RoleUserDto toLiteDto(RoleUser entity) {
		if (entity == null) {
			return null;
		}
		RoleUserDto dto = new RoleUserDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<RoleUserDto> toLiteDtos(List<RoleUser> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<RoleUserDto> dtos = new ArrayList<RoleUserDto>();
		for (RoleUser entity : entities) {
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
		@Mapping(source="user", target="user"),
	})
    RoleUser toEntity(RoleUserDto dto, Role role, User user) throws ParseException;

    //List<UserRole> toEntities(List<UserRoleDto> dtos) throws ParseException;

}
