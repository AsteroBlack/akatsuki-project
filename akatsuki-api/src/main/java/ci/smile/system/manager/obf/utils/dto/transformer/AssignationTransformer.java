

/*
 * Java transformer for entity table assignation 
 * Created on 2023-01-26 ( Time 00:38:56 )
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
 * TRANSFORMER for table "assignation"
 * 
 * @author Geo
 *
 */
@Mapper
public interface AssignationTransformer {

	AssignationTransformer INSTANCE = Mappers.getMapper(AssignationTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.qualificatif.id", target="qualificatifId"),
		@Mapping(source="entity.qualificatif.libelle", target="qualificatifLibelle"),
		@Mapping(source="entity.qualificatif.code", target="qualificatifCode"),
	})
	AssignationDto toDto(Assignation entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<AssignationDto> toDtos(List<Assignation> entities) throws ParseException;

    default AssignationDto toLiteDto(Assignation entity) {
		if (entity == null) {
			return null;
		}
		AssignationDto dto = new AssignationDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	default List<AssignationDto> toLiteDtos(List<Assignation> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<AssignationDto> dtos = new ArrayList<AssignationDto>();
		for (Assignation entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.libelle", target="libelle"),
		@Mapping(source="dto.code", target="code"),
		@Mapping(source="dto.qualificatif2", target="qualificatif2"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.assignation", target="assignation"),
		@Mapping(source="dto.technologie", target="technologie"),
		@Mapping(source="qualificatif", target="qualificatif"),
	})
    Assignation toEntity(AssignationDto dto, Qualificatif qualificatif) throws ParseException;

    //List<Assignation> toEntities(List<AssignationDto> dtos) throws ParseException;

}
