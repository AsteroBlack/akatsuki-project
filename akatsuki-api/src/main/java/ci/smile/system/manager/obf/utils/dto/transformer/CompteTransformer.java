

/*
 * Java transformer for entity table compte 
 * Created on 2022-11-11 ( Time 16:19:56 )
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
 * TRANSFORMER for table "compte"
 * 
 * @author Geo
 *
 */
@Mapper
public interface CompteTransformer {

	CompteTransformer INSTANCE = Mappers.getMapper(CompteTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
	})
	CompteDto toDto(Compte entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<CompteDto> toDtos(List<Compte> entities) throws ParseException;

    default CompteDto toLiteDto(Compte entity) {
		if (entity == null) {
			return null;
		}
		CompteDto dto = new CompteDto();
		dto.setIdCompte( entity.getIdCompte() );
		return dto;
    }

	default List<CompteDto> toLiteDtos(List<Compte> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<CompteDto> dtos = new ArrayList<CompteDto>();
		for (Compte entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.idCompte", target="idCompte"),
		@Mapping(source="dto.userCompte", target="userCompte"),
		@Mapping(source="dto.securityPassword", target="securityPassword"),
		@Mapping(source="dto.isConnected", target="isConnected"),
		@Mapping(source="dto.isBlocked", target="isBlocked"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.securityToken", target="securityToken"),
		@Mapping(source="dto.salt", target="salt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.secretKey", target="secretKey"),
	})
    Compte toEntity(CompteDto dto) throws ParseException;

    //List<Compte> toEntities(List<CompteDto> dtos) throws ParseException;

}
