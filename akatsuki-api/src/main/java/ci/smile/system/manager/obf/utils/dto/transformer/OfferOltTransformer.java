

/*
 * Java transformer for entity table offer_olt 
 * Created on 2022-11-08 ( Time 19:23:44 )
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
 * TRANSFORMER for table "offer_olt"
 * 
 * @author Geo
 *
 */
@Mapper
public interface OfferOltTransformer {

	OfferOltTransformer INSTANCE = Mappers.getMapper(OfferOltTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
	})
	OfferOltDto toDto(OfferOlt entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<OfferOltDto> toDtos(List<OfferOlt> entities) throws ParseException;

    default OfferOltDto toLiteDto(OfferOlt entity) {
		if (entity == null) {
			return null;
		}
		OfferOltDto dto = new OfferOltDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<OfferOltDto> toLiteDtos(List<OfferOlt> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<OfferOltDto> dtos = new ArrayList<OfferOltDto>();
		for (OfferOlt entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.rx", target="rx"),
		@Mapping(source="dto.tx", target="tx"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.template", target="template"),
	})
    OfferOlt toEntity(OfferOltDto dto) throws ParseException;

    //List<OfferOlt> toEntities(List<OfferOltDto> dtos) throws ParseException;

}
