
/*
 * Java dto for entity table groupe 
 * Created on 2023-01-18 ( Time 11:59:00 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils.dto;

import java.util.Date;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

import ci.smile.system.manager.obf.utils.contract.*;
import ci.smile.system.manager.obf.utils.dto.customize._GroupeDto;

/**
 * DTO for table "groupe"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class GroupeDto extends _GroupeDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private String     libelle              ;
    private Integer    idPlateforme         ;
    private Integer    idOffre              ;
	private String     createdAt            ;
	private String     updatedAt            ;
	private String     deletedAt            ;
	private String     modeleBoxe           ;
    private Integer    deletedBy            ;
    private Integer    updatedBy            ;
    private Integer    createdBy            ;
    private Boolean    isDeleted            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	private String plateformeCode;
	private String plateformeLibelle;
	private String offerLibelle;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   libelleParam          ;                     
	private SearchParam<Integer>  idPlateformeParam     ;                     
	private SearchParam<Integer>  idOffreParam          ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;  
	private SearchParam<String>   modeleBoxeParam       ;
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   plateformeCodeParam   ;                     
	private SearchParam<String>   plateformeLibelleParam;                     
	private SearchParam<String>   offerLibelleParam     ;                     
    /**
     * Default constructor
     */
    public GroupeDto()
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
