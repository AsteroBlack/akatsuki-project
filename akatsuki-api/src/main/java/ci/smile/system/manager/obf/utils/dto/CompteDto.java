
/*
 * Java dto for entity table compte 
 * Created on 2022-11-11 ( Time 16:19:56 )
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
import ci.smile.system.manager.obf.utils.dto.customize._CompteDto;

/**
 * DTO for table "compte"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class CompteDto extends _CompteDto implements Cloneable{

    private Integer    idCompte             ; // Primary Key

    private String     userCompte           ;
    private String     securityPassword     ;
    private Boolean    isConnected          ;
    private Boolean    isBlocked            ;
	private String     createdAt            ;
    private String     securityToken        ;
    private String     salt                 ;
	private String     updatedAt            ;
    private Integer    createdBy            ;
    private Integer    updatedBy            ;
    private Boolean    isDeleted            ;
    private String     secretKey            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------

	// Search param
	private SearchParam<Integer>  idCompteParam         ;                     
	private SearchParam<String>   userCompteParam       ;                     
	private SearchParam<String>   securityPasswordParam ;                     
	private SearchParam<Boolean>  isConnectedParam      ;                     
	private SearchParam<Boolean>  isBlockedParam        ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   securityTokenParam    ;                     
	private SearchParam<String>   saltParam             ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   secretKeyParam        ;                     
    /**
     * Default constructor
     */
    public CompteDto()
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
