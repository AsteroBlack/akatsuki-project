
/*
 * Java dto for entity table groupe_radius 
 * Created on 2023-01-14 ( Time 17:48:14 )
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
import ci.smile.system.manager.obf.utils.dto.customize._GroupeRadiusDto;

/**
 * DTO for table "groupe_radius"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class GroupeRadiusDto extends _GroupeRadiusDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private String     code                 ;
	private String     createdAt            ;
    private Integer    createdBy            ;
	private String     deletedAt            ;
    private Integer    deletedBy            ;
    private Boolean    isDeleted            ;
    private Boolean    isSuspend            ;
    private String     libelle              ;
	private String     updatedAt            ;
    private Integer    updatedBy            ;
    private Integer    localisationId       ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	private String localisationCode;
	private String localisationLibelle;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   codeParam             ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<Boolean>  isSuspendParam        ;                     
	private SearchParam<String>   libelleParam          ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  localisationIdParam   ;                     
	private SearchParam<String>   localisationCodeParam ;                     
	private SearchParam<String>   localisationLibelleParam;                     
    /**
     * Default constructor
     */
    public GroupeRadiusDto()
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
