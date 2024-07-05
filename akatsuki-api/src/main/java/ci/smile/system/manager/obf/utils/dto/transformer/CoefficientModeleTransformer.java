

/*
 * Java transformer for entity table coefficient_modele 
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

import ci.smile.system.manager.obf.dao.entity.CoefficientModele;
import ci.smile.system.manager.obf.dao.entity.Modele;
import ci.smile.system.manager.obf.dao.entity.TypeOffre;
import ci.smile.system.manager.obf.utils.contract.FullTransformerQualifier;
import ci.smile.system.manager.obf.utils.dto.CoefficientModeleDto;


/**
 * TRANSFORMER for table "coefficient_modele"
 * 
 * @author Geo
 *
 */
@Mapper
public interface CoefficientModeleTransformer {

	CoefficientModeleTransformer INSTANCE = Mappers.getMapper(CoefficientModeleTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.modele.id", target="modeleId"),
		@Mapping(source="entity.modele.code", target="modeleCode"),
		@Mapping(source="entity.modele.libelle", target="modeleLibelle"),
		@Mapping(source="entity.typeOffre.id", target="typeOffreId"),
		@Mapping(source="entity.typeOffre.code", target="typeOffreCode"),
		@Mapping(source="entity.typeOffre.libelle", target="typeOffreLibelle"),
	})
	CoefficientModeleDto toDto(CoefficientModele entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<CoefficientModeleDto> toDtos(List<CoefficientModele> entities) throws ParseException;

    default CoefficientModeleDto toLiteDto(CoefficientModele entity) {
		if (entity == null) {
			return null;
		}
		CoefficientModeleDto dto = new CoefficientModeleDto();
		dto.setId( entity.getId() );
		return dto;
    }

	default List<CoefficientModeleDto> toLiteDtos(List<CoefficientModele> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<CoefficientModeleDto> dtos = new ArrayList<CoefficientModeleDto>();
		for (CoefficientModele entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.coefMac", target="coefMac"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="modele", target="modele"),
		@Mapping(source="typeOffre", target="typeOffre"),
	})
    CoefficientModele toEntity(CoefficientModeleDto dto, Modele modele, TypeOffre typeOffre) throws ParseException;

    //List<CoefficientModele> toEntities(List<CoefficientModeleDto> dtos) throws ParseException;

}
