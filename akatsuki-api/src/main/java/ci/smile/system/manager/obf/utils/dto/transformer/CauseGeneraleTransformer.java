

/*
 * Java transformer for entity table cause_generale 
 * Created on 2023-01-26 ( Time 00:38:57 )
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
 * TRANSFORMER for table "cause_generale"
 * 
 * @author Geo
 *
 */
@Mapper
public interface CauseGeneraleTransformer {

	CauseGeneraleTransformer INSTANCE = Mappers.getMapper(CauseGeneraleTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.corbeille.id", target="corbeilleId"),
		@Mapping(source="entity.corbeille.code", target="corbeilleCode"),
		@Mapping(source="entity.corbeille.libelle", target="corbeilleLibelle"),
	})
	CauseGeneraleDto toDto(CauseGenerale entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<CauseGeneraleDto> toDtos(List<CauseGenerale> entities) throws ParseException;

    default CauseGeneraleDto toLiteDto(CauseGenerale entity) {
		if (entity == null) {
			return null;
		}
		CauseGeneraleDto dto = new CauseGeneraleDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	default List<CauseGeneraleDto> toLiteDtos(List<CauseGenerale> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<CauseGeneraleDto> dtos = new ArrayList<CauseGeneraleDto>();
		for (CauseGenerale entity : entities) {
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
		@Mapping(source="corbeille", target="corbeille"),
	})
    CauseGenerale toEntity(CauseGeneraleDto dto, Corbeille corbeille) throws ParseException;

    //List<CauseGenerale> toEntities(List<CauseGeneraleDto> dtos) throws ParseException;

}
