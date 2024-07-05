
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
import ci.smile.system.manager.obf.utils.dto.customize._MenusDto;

/**
 * DTO for table "menus"
 *
 * @author Geo
 */

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class MenusDto extends _MenusDto implements Cloneable{

	private Integer    id                   ; // Primary Key

	private String     libelle              ;
	private Integer    moduleId             ;
	private Integer    parentMenusId             ;
	private String     code                 ;
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
	private String moduleLibelle;
	private String moduleCode;

	private String parentMenusLibelle;
	private String parentMenusCode;

    private Boolean    isLocked     ;
	private SearchParam<Boolean>  isLockedParam        ; 
	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   libelleParam          ;                     
	private SearchParam<Integer>  moduleIdParam         ;                     
	private SearchParam<String>   codeParam             ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   moduleLibelleParam    ;                     
	private SearchParam<String>   moduleCodeParam       ;                     
	private SearchParam<Integer>  parentMenusIdParam    ;                     
	private SearchParam<String>   parentMenusLibelleParam    ;                     
	private SearchParam<String>   parentMenusCodeParam       ;                    
	/**
	 * Default constructor
	 */
	public MenusDto()
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





	public Integer getModuleId() {
		return moduleId;
	}





	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}





	public Integer getParentMenusId() {
		return parentMenusId;
	}





	public void setParentMenusId(Integer parentMenusId) {
		this.parentMenusId = parentMenusId;
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





	public String getModuleLibelle() {
		return moduleLibelle;
	}





	public void setModuleLibelle(String moduleLibelle) {
		this.moduleLibelle = moduleLibelle;
	}





	public String getModuleCode() {
		return moduleCode;
	}





	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}





	public String getParentMenusLibelle() {
		return parentMenusLibelle;
	}





	public void setParentMenusLibelle(String parentMenusLibelle) {
		this.parentMenusLibelle = parentMenusLibelle;
	}





	public String getParentMenusCode() {
		return parentMenusCode;
	}





	public void setParentMenusCode(String parentMenusCode) {
		this.parentMenusCode = parentMenusCode;
	}





	public Boolean getIsLocked() {
		return isLocked;
	}





	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}





	public SearchParam<Boolean> getIsLockedParam() {
		return isLockedParam;
	}





	public void setIsLockedParam(SearchParam<Boolean> isLockedParam) {
		this.isLockedParam = isLockedParam;
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





	public SearchParam<Integer> getModuleIdParam() {
		return moduleIdParam;
	}





	public void setModuleIdParam(SearchParam<Integer> moduleIdParam) {
		this.moduleIdParam = moduleIdParam;
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





	public SearchParam<String> getModuleLibelleParam() {
		return moduleLibelleParam;
	}





	public void setModuleLibelleParam(SearchParam<String> moduleLibelleParam) {
		this.moduleLibelleParam = moduleLibelleParam;
	}





	public SearchParam<String> getModuleCodeParam() {
		return moduleCodeParam;
	}





	public void setModuleCodeParam(SearchParam<String> moduleCodeParam) {
		this.moduleCodeParam = moduleCodeParam;
	}





	public SearchParam<Integer> getParentMenusIdParam() {
		return parentMenusIdParam;
	}





	public void setParentMenusIdParam(SearchParam<Integer> parentMenusIdParam) {
		this.parentMenusIdParam = parentMenusIdParam;
	}





	public SearchParam<String> getParentMenusLibelleParam() {
		return parentMenusLibelleParam;
	}





	public void setParentMenusLibelleParam(SearchParam<String> parentMenusLibelleParam) {
		this.parentMenusLibelleParam = parentMenusLibelleParam;
	}





	public SearchParam<String> getParentMenusCodeParam() {
		return parentMenusCodeParam;
	}





	public void setParentMenusCodeParam(SearchParam<String> parentMenusCodeParam) {
		this.parentMenusCodeParam = parentMenusCodeParam;
	}





	//----------------------------------------------------------------------
	// clone METHOD
	//----------------------------------------------------------------------
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
