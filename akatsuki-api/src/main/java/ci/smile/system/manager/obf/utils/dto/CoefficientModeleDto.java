
/*
 * Java dto for entity table coefficient_modele 
 * Created on 2021-03-09 ( Time 11:10:42 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ci.smile.system.manager.obf.utils.contract.SearchParam;
import ci.smile.system.manager.obf.utils.dto.customize._CoefficientModeleDto;
import lombok.Data;
import lombok.ToString;

/**
 * DTO for table "coefficient_modele"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class CoefficientModeleDto extends _CoefficientModeleDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Integer    modeleId             ;
    private Integer    coefMac              ;
    private Integer    typeOffreId          ;
	private String     createdAt            ;
	private String     updatedAt            ;
	private String     deletedAt            ;
    private Integer    createdBy            ;
    private Integer    updatedBy            ;
    private Integer    deletedBy            ;
    private Boolean    isDeleted            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	private String modeleCode;
	private String modeleLibelle;
	private String typeOffreCode;
	private String typeOffreLibelle;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<Integer>  modeleIdParam         ;                     
	private SearchParam<Integer>  coefMacParam          ;                     
	private SearchParam<Integer>  typeOffreIdParam      ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   modeleCodeParam       ;                     
	private SearchParam<String>   modeleLibelleParam    ;                     
	private SearchParam<String>   typeOffreCodeParam    ;                     
	private SearchParam<String>   typeOffreLibelleParam ;                     
    /**
     * Default constructor
     */
    public CoefficientModeleDto()
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
