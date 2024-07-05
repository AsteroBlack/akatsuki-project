
/*
 * Java dto for entity table offer_groupe 
 * Created on 2023-01-16 ( Time 14:17:29 )
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
import ci.smile.system.manager.obf.utils.dto.customize._OfferGroupeDto;

/**
 * DTO for table "offer_groupe"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class OfferGroupeDto extends _OfferGroupeDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Integer    idOffre              ;
    private Integer    idGroupe             ;
	private String     createdAt            ;
	private String     updatedAt            ;
	private String     deletedAt            ;
    private Integer    createdBy            ;
    private Integer    updatedBy            ;
    private Integer    deletedBy            ;
    private Boolean    isDeleted            ;
    private Integer    idPlateforme         ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	private String plateformeCode;
	private String plateformeLibelle;
	private String offerLibelle;
	private String groupeLibelle;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<Integer>  idOffreParam          ;                     
	private SearchParam<Integer>  idGroupeParam         ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<Integer>  idPlateformeParam     ;                     
	private SearchParam<String>   plateformeCodeParam   ;                     
	private SearchParam<String>   plateformeLibelleParam;                     
	private SearchParam<String>   offerLibelleParam     ;                     
	private SearchParam<String>   groupeLibelleParam    ;                     
    /**
     * Default constructor
     */
    public OfferGroupeDto()
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
