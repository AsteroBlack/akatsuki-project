
/*
 * Java dto for entity table assignation 
 * Created on 2023-01-26 ( Time 00:38:56 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils.dto;

import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

import ci.smile.system.manager.obf.utils.contract.*;
import ci.smile.system.manager.obf.utils.dto.customize._AssignationDto;

/**
 * DTO for table "assignation"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class AssignationDto extends _AssignationDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private String     libelle              ;
    private String     code                 ;
    private String     qualificatif2        ;
	private String     createdAt            ;
	private String     updatedAt            ;
	private String     deletedAt            ;
    private Integer    createdBy            ;
    private Integer    updatedBy            ;
    private Integer    deletedBy            ;
    private Boolean    isDeleted            ;
    private Integer    qualificatifId       ;
    private String     assignation          ;
    private String     technologie          ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	private String qualificatifLibelle;
	private String qualificatifCode;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   libelleParam          ;                     
	private SearchParam<String>   codeParam             ;                     
	private SearchParam<String>   qualificatif2Param    ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<Integer>  qualificatifIdParam   ;                     
	private SearchParam<String>   assignationParam      ;                     
	private SearchParam<String>   technologieParam      ;                     
	private SearchParam<String>   qualificatifLibelleParam;                     
	private SearchParam<String>   qualificatifCodeParam ;                     
    /**
     * Default constructor
     */
    public AssignationDto()
    {
        super();
    }
    
	//----------------------------------------------------------------------
    // clone METHOD
    //----------------------------------------------------------------------
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
