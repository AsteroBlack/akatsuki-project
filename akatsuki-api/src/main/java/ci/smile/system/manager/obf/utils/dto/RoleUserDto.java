
/*
 * Java dto for entity table role_menus_habilitation 
 * Created on 2019-12-20 ( Time 00:51:50 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ci.smile.system.manager.obf.utils.dto.RoleUserDto;
import ci.smile.system.manager.obf.utils.contract.SearchParam;
import ci.smile.system.manager.obf.utils.dto.customize._RoleUserDto;
import lombok.Data;
import lombok.ToString;

/**
 * DTO for table "role_menus_habilitation"
 *
 * @author Geo
 */

@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class RoleUserDto extends _RoleUserDto implements Cloneable{

	private Integer    id                   ; // Primary Key

	private Integer    roleId               ;
	private String     createdAt            ;
	private String     updatedAt            ;
	private String     deletedAt            ;
	private Integer    createdBy            ;
	private Integer    updatedBy            ;
	private Integer    deletedBy            ;
	private Boolean    isDeleted            ;





	private Integer     userId            ;
	private String     userMatricule            ;
	private String     userNom                  ;
	private String    userPrenom               ;
	private String     userEmail              ;
	private String     userLogin                ;
	private String     passwordUser             ;
	private String     userContact              ;
	private Boolean    userIsSuperAdmin       ;
	private Boolean    isAutorizeUser           ;
	private Boolean    userUsDefaultPassword   ;
	private Boolean    userIsLocked            ;

	//----------------------------------------------------------------------
	// ENTITY LINKS FIELD ( RELATIONSHIP )
	//----------------------------------------------------------------------
	private String roleLibelle;
	private String roleCode;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<Integer>  roleIdParam           ;                     
	private SearchParam<Integer>  userIdParam   ;                     
	private SearchParam<Integer>  habilitationIdParam   ;                     
	private SearchParam<Integer>  menusIdParam          ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   habilitationLibelleParam;                     
	private SearchParam<String>   habilitationCodeParam ;                     
	private SearchParam<String>   roleLibelleParam      ;                     
	private SearchParam<String>   roleCodeParam         ; 

	private SearchParam<String>   userMatriculeParam;                     
	private SearchParam<String>   userNomParam ;
	private SearchParam<String>   userPrenomParam ;
	private SearchParam<String>   userEmailParam ;
	private SearchParam<String>   userContactParam ;
	private SearchParam<String>   userLoginParam ;
	private SearchParam<Boolean>   userIsLockedParam ;
	/**
	 * Default constructor
	 */
    public RoleUserDto()
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

