
/*
 * Java dto for entity table offer 
 * Created on 2023-01-14 ( Time 17:24:38 )
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
import ci.smile.system.manager.obf.utils.dto.customize._OfferDto;

/**
 * DTO for table "offer"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class OfferDto extends _OfferDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private String     codeOffer            ;
	private String     createdAt            ;
    private Integer    createdBy            ;
	private String     deletedAt            ;
    private Integer    deletedBy            ;
    private Boolean    isDeleted            ;
    private Boolean    isSuspend            ;
	private String     updatedAt            ;
    private Integer    updatedBy            ;
    private String     libelle              ;
    private String     description          ;
    private String     codeTypeClient       ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   codeOfferParam        ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<String>   deletedAtParam        ;   
	private SearchParam<String>   descriptionParam      ;
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<Boolean>  isSuspendParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  idTypeOffreParam      ;                     
	private SearchParam<String>   libelleParam          ;                     
	private SearchParam<String>   codeTypeClientParam   ;                                        
    /**
     * Default constructor
     */
    public OfferDto()
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
