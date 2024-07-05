

/*
 * Java transformer for entity table cause_specifique 
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
 * TRANSFORMER for table "cause_specifique"
 * 
 * @author Geo
 *
 */
@Mapper
public interface CauseSpecifiqueTransformer {

	CauseSpecifiqueTransformer INSTANCE = Mappers.getMapper(CauseSpecifiqueTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.causeGenerale.id", target="causeGeneraleId"),
		@Mapping(source="entity.causeGenerale.code", target="causeGeneraleCode"),
		@Mapping(source="entity.causeGenerale.libelle", target="causeGeneraleLibelle"),
	})
	CauseSpecifiqueDto toDto(CauseSpecifique entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<CauseSpecifiqueDto> toDtos(List<CauseSpecifique> entities) throws ParseException;

    default CauseSpecifiqueDto toLiteDto(CauseSpecifique entity) {
		if (entity == null) {
			return null;
		}
		CauseSpecifiqueDto dto = new CauseSpecifiqueDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	default List<CauseSpecifiqueDto> toLiteDtos(List<CauseSpecifique> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<CauseSpecifiqueDto> dtos = new ArrayList<CauseSpecifiqueDto>();
		for (CauseSpecifique entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.code", target="code"),
		@Mapping(source="dto.libelle", target="libelle"),
		@Mapping(source="dto.description", target="description"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="causeGenerale", target="causeGenerale"),
	})
    CauseSpecifique toEntity(CauseSpecifiqueDto dto, CauseGenerale causeGenerale) throws ParseException;

    //List<CauseSpecifique> toEntities(List<CauseSpecifiqueDto> dtos) throws ParseException;

}
