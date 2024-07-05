
/*
 * Java dto for entity table menus 
 * Created on 2019-12-19 ( Time 23:54:47 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ci.smile.system.manager.obf.utils.contract.SearchParam;
import ci.smile.system.manager.obf.utils.dto.customize._ActionDto;

/**
 * DTO for table "menus"
 *
 * @author Geo
 */

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class ActionDto extends _ActionDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private String     libelle              ;
    private String     text              ;
    private Integer    menusId             ;
    private Integer    roleId             ;
    private Integer    parentActionId             ;
    private String     code                 ;
	private String     createdAt            ;
	private String     updatedAt            ;
	private String     deletedAt            ;
    private Integer    createdBy            ;
    private Integer    updatedBy            ;
    private Integer    deletedBy            ;
    private Boolean    isDeleted            ;
    

    private Boolean    isLocked     ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	private String roleLibelle;
	private String menusLibelle;
	private String menusCode;
	private String roleCode;
	
	private String parentActionLibelle;
	private String parentActionCode;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   libelleParam          ;                     
	private SearchParam<Integer>  roleIdParam         ;                     
	private SearchParam<String>   codeParam             ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<Boolean>  isLockedParam        ;                     
	private SearchParam<String>   roleLibelleParam    ;                     
	private SearchParam<String>   roleCodeParam       ;                     
	private SearchParam<Integer>   parentActionIdParam       ;                     
	private SearchParam<String>   parentActionLibelleParam       ;                     
	private SearchParam<String>   parentActionCodeParam       ;             
	private SearchParam<Integer>   menusIdParam       ;                     
	private SearchParam<String>   menusLibelleParam       ;                     
	private SearchParam<String>   menusCodeParam       ;                     
    /**
     * Default constructor
     */
    public ActionDto()
    {
        super();
    }
    
    
    
    
	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getLibelle() {
		return libelle;
	}




	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}




	public String getText() {
		return text;
	}




	public void setText(String text) {
		this.text = text;
	}




	public Integer getMenusId() {
		return menusId;
	}




	public void setMenusId(Integer menusId) {
		this.menusId = menusId;
	}




	public Integer getRoleId() {
		return roleId;
	}




	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}




	public Integer getParentActionId() {
		return parentActionId;
	}




	public void setParentActionId(Integer parentActionId) {
		this.parentActionId = parentActionId;
	}




	public String getCode() {
		return code;
	}




	public void setCode(String code) {
		this.code = code;
	}




	public String getCreatedAt() {
		return createdAt;
	}




	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}




	public String getUpdatedAt() {
		return updatedAt;
	}




	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}




	public String getDeletedAt() {
		return deletedAt;
	}




	public void setDeletedAt(String deletedAt) {
		this.deletedAt = deletedAt;
	}




	public Integer getCreatedBy() {
		return createdBy;
	}




	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}




	public Integer getUpdatedBy() {
		return updatedBy;
	}




	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}




	public Integer getDeletedBy() {
		return deletedBy;
	}




	public void setDeletedBy(Integer deletedBy) {
		this.deletedBy = deletedBy;
	}




	public Boolean getIsDeleted() {
		return isDeleted;
	}




	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}




	public Boolean getIsLocked() {
		return isLocked;
	}




	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}




	public String getRoleLibelle() {
		return roleLibelle;
	}




	public void setRoleLibelle(String roleLibelle) {
		this.roleLibelle = roleLibelle;
	}




	public String getMenusLibelle() {
		return menusLibelle;
	}




	public void setMenusLibelle(String menusLibelle) {
		this.menusLibelle = menusLibelle;
	}




	public String getMenusCode() {
		return menusCode;
	}




	public void setMenusCode(String menusCode) {
		this.menusCode = menusCode;
	}




	public String getRoleCode() {
		return roleCode;
	}




	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}




	public String getParentActionLibelle() {
		return parentActionLibelle;
	}




	public void setParentActionLibelle(String parentActionLibelle) {
		this.parentActionLibelle = parentActionLibelle;
	}




	public String getParentActionCode() {
		return parentActionCode;
	}




	public void setParentActionCode(String parentActionCode) {
		this.parentActionCode = parentActionCode;
	}




	public SearchParam<Integer> getIdParam() {
		return idParam;
	}




	public void setIdParam(SearchParam<Integer> idParam) {
		this.idParam = idParam;
	}




	public SearchParam<String> getLibelleParam() {
		return libelleParam;
	}




	public void setLibelleParam(SearchParam<String> libelleParam) {
		this.libelleParam = libelleParam;
	}




	public SearchParam<Integer> getRoleIdParam() {
		return roleIdParam;
	}




	public void setRoleIdParam(SearchParam<Integer> roleIdParam) {
		this.roleIdParam = roleIdParam;
	}




	public SearchParam<String> getCodeParam() {
		return codeParam;
	}




	public void setCodeParam(SearchParam<String> codeParam) {
		this.codeParam = codeParam;
	}




	public SearchParam<String> getCreatedAtParam() {
		return createdAtParam;
	}




	public void setCreatedAtParam(SearchParam<String> createdAtParam) {
		this.createdAtParam = createdAtParam;
	}




	public SearchParam<String> getUpdatedAtParam() {
		return updatedAtParam;
	}




	public void setUpdatedAtParam(SearchParam<String> updatedAtParam) {
		this.updatedAtParam = updatedAtParam;
	}




	public SearchParam<String> getDeletedAtParam() {
		return deletedAtParam;
	}




	public void setDeletedAtParam(SearchParam<String> deletedAtParam) {
		this.deletedAtParam = deletedAtParam;
	}




	public SearchParam<Integer> getCreatedByParam() {
		return createdByParam;
	}




	public void setCreatedByParam(SearchParam<Integer> createdByParam) {
		this.createdByParam = createdByParam;
	}




	public SearchParam<Integer> getUpdatedByParam() {
		return updatedByParam;
	}




	public void setUpdatedByParam(SearchParam<Integer> updatedByParam) {
		this.updatedByParam = updatedByParam;
	}




	public SearchParam<Integer> getDeletedByParam() {
		return deletedByParam;
	}




	public void setDeletedByParam(SearchParam<Integer> deletedByParam) {
		this.deletedByParam = deletedByParam;
	}




	public SearchParam<Boolean> getIsDeletedParam() {
		return isDeletedParam;
	}




	public void setIsDeletedParam(SearchParam<Boolean> isDeletedParam) {
		this.isDeletedParam = isDeletedParam;
	}




	public SearchParam<Boolean> getIsLockedParam() {
		return isLockedParam;
	}




	public void setIsLockedParam(SearchParam<Boolean> isLockedParam) {
		this.isLockedParam = isLockedParam;
	}




	public SearchParam<String> getRoleLibelleParam() {
		return roleLibelleParam;
	}




	public void setRoleLibelleParam(SearchParam<String> roleLibelleParam) {
		this.roleLibelleParam = roleLibelleParam;
	}




	public SearchParam<String> getRoleCodeParam() {
		return roleCodeParam;
	}




	public void setRoleCodeParam(SearchParam<String> roleCodeParam) {
		this.roleCodeParam = roleCodeParam;
	}




	public SearchParam<Integer> getParentActionIdParam() {
		return parentActionIdParam;
	}




	public void setParentActionIdParam(SearchParam<Integer> parentActionIdParam) {
		this.parentActionIdParam = parentActionIdParam;
	}




	public SearchParam<String> getParentActionLibelleParam() {
		return parentActionLibelleParam;
	}




	public void setParentActionLibelleParam(SearchParam<String> parentActionLibelleParam) {
		this.parentActionLibelleParam = parentActionLibelleParam;
	}




	public SearchParam<String> getParentActionCodeParam() {
		return parentActionCodeParam;
	}




	public void setParentActionCodeParam(SearchParam<String> parentActionCodeParam) {
		this.parentActionCodeParam = parentActionCodeParam;
	}




	public SearchParam<Integer> getMenusIdParam() {
		return menusIdParam;
	}




	public void setMenusIdParam(SearchParam<Integer> menusIdParam) {
		this.menusIdParam = menusIdParam;
	}




	public SearchParam<String> getMenusLibelleParam() {
		return menusLibelleParam;
	}




	public void setMenusLibelleParam(SearchParam<String> menusLibelleParam) {
		this.menusLibelleParam = menusLibelleParam;
	}




	public SearchParam<String> getMenusCodeParam() {
		return menusCodeParam;
	}




	public void setMenusCodeParam(SearchParam<String> menusCodeParam) {
		this.menusCodeParam = menusCodeParam;
	}




	//----------------------------------------------------------------------
    // clone METHOD
    //----------------------------------------------------------------------
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
