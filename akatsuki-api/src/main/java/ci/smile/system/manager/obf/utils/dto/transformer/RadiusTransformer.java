

/*
 * Java transformer for entity table radius 
 * Created on 2021-03-09 ( Time 11:10:42 )
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

import ci.smile.system.manager.obf.dao.entity.GroupeRadius;
import ci.smile.system.manager.obf.dao.entity.Radius;
import ci.smile.system.manager.obf.dao.entity.TypeOffre;
import ci.smile.system.manager.obf.utils.contract.FullTransformerQualifier;
import ci.smile.system.manager.obf.utils.dto.RadiusDto;


/**
 * TRANSFORMER for table "radius"
 * 
 * @author Geo
 *
 */
@Mapper
public interface RadiusTransformer {

	RadiusTransformer INSTANCE = Mappers.getMapper(RadiusTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.groupeRadius.id", target="groupeRadiusId"),
		@Mapping(source="entity.groupeRadius.code", target="groupeRadiusCode"),
		@Mapping(source="entity.groupeRadius.libelle", target="groupeRadiusLibelle"),
		@Mapping(source="entity.groupeRadius.localisation.libelle", target="localisationLibelle"),
		@Mapping(source="entity.groupeRadius.localisation.code", target="localisationCode"),
		@Mapping(source="entity.groupeRadius.localisation.id", target="localisationId"),
		@Mapping(source="entity.typeOffre.id", target="typeOffreId"),
		@Mapping(source="entity.typeOffre.code", target="typeOffreCode"),
		@Mapping(source="entity.typeOffre.libelle", target="typeOffreLibelle"),
	})
	RadiusDto toDto(Radius entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<RadiusDto> toDtos(List<Radius> entities) throws ParseException;

    default RadiusDto toLiteDto(Radius entity) {
		if (entity == null) {
			return null;
		}
		RadiusDto dto = new RadiusDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<RadiusDto> toLiteDtos(List<Radius> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<RadiusDto> dtos = new ArrayList<RadiusDto>();
		for (Radius entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.offre", target="offre"),
		@Mapping(source="dto.localisation", target="localisation"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="groupeRadius", target="groupeRadius"),
		@Mapping(source="typeOffre", target="typeOffre"),
	})
    Radius toEntity(RadiusDto dto, GroupeRadius groupeRadius, TypeOffre typeOffre) throws ParseException;

    //List<Radius> toEntities(List<RadiusDto> dtos) throws ParseException;

}
