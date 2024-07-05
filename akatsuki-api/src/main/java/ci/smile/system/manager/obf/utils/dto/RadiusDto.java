
/*
 * Java dto for entity table radius 
 * Created on 2021-03-09 ( Time 11:10:42 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ci.smile.system.manager.obf.utils.contract.SearchParam;
import ci.smile.system.manager.obf.utils.dto.customize._RadiusDto;
import lombok.Data;
import lombok.ToString;

/**
 * DTO for table "radius"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class RadiusDto extends _RadiusDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private String     offre                ;
    private Integer    groupeRadiusId       ;
    private Integer    typeOffreId          ;
    private String     localisation         ;
	private String     createdAt            ;
	private String     updatedAt            ;
	private String     deletedAt            ;
    private Boolean    isDeleted            ;

    
	private String     localisationCode            ;
	private Integer     localisationId            ;
	private String     localisationLibelle            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	private String groupeRadiusCode;
	private String groupeRadiusLibelle;
	private String typeOffreCode;
	private String typeOffreLibelle;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   offreParam            ;                     
	private SearchParam<Integer>  groupeRadiusIdParam   ;                     
	private SearchParam<Integer>  typeOffreIdParam      ;                     
	private SearchParam<String>   localisationParam     ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   groupeRadiusCodeParam ;                     
	private SearchParam<String>   groupeRadiusLibelleParam;                     
	private SearchParam<String>   typeOffreCodeParam    ;                     
	private SearchParam<String>   typeOffreLibelleParam ;                     
    /**
     * Default constructor
     */
    public RadiusDto()
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
