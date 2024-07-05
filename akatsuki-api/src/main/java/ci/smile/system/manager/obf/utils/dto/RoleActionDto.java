
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

import ci.smile.system.manager.obf.utils.contract.SearchParam;
import ci.smile.system.manager.obf.utils.dto.customize._RoleActionDto;

/**
 * DTO for table "role_menus_habilitation"
 *
 * @author Geo
 */

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class RoleActionDto extends _RoleActionDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Integer    roleId               ;
    private Integer    actionId       ;
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
	private String actionLibelle;
	private String actionCode;
	private String roleLibelle;
	private String roleCode;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<Integer>  roleIdParam           ;                     
	private SearchParam<Integer>  actionIdParam   ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   actionLibelleParam;                     
	private SearchParam<String>   actionCodeParam ;                     
	private SearchParam<String>   roleLibelleParam      ;                     
	private SearchParam<String>   roleCodeParam         ;                     
    /**
     * Default constructor
     */
    public RoleActionDto()
    {
        super();
    }
    
    
    
    
    
	public Integer getId() {
		return id;
	}





	public void setId(Integer id) {
		this.id = id;
	}





	public Integer getRoleId() {
		return roleId;
	}





	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}





	public Integer getActionId() {
		return actionId;
	}





	public void setActionId(Integer actionId) {
		this.actionId = actionId;
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





	public String getActionLibelle() {
		return actionLibelle;
	}





	public void setActionLibelle(String actionLibelle) {
		this.actionLibelle = actionLibelle;
	}





	public String getActionCode() {
		return actionCode;
	}





	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}





	public String getRoleLibelle() {
		return roleLibelle;
	}





	public void setRoleLibelle(String roleLibelle) {
		this.roleLibelle = roleLibelle;
	}





	public String getRoleCode() {
		return roleCode;
	}





	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}





	public SearchParam<Integer> getIdParam() {
		return idParam;
	}





	public void setIdParam(SearchParam<Integer> idParam) {
		this.idParam = idParam;
	}





	public SearchParam<Integer> getRoleIdParam() {
		return roleIdParam;
	}





	public void setRoleIdParam(SearchParam<Integer> roleIdParam) {
		this.roleIdParam = roleIdParam;
	}





	public SearchParam<Integer> getActionIdParam() {
		return actionIdParam;
	}





	public void setActionIdParam(SearchParam<Integer> actionIdParam) {
		this.actionIdParam = actionIdParam;
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





	public SearchParam<String> getActionLibelleParam() {
		return actionLibelleParam;
	}





	public void setActionLibelleParam(SearchParam<String> actionLibelleParam) {
		this.actionLibelleParam = actionLibelleParam;
	}





	public SearchParam<String> getActionCodeParam() {
		return actionCodeParam;
	}





	public void setActionCodeParam(SearchParam<String> actionCodeParam) {
		this.actionCodeParam = actionCodeParam;
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





	//----------------------------------------------------------------------
    // clone METHOD
    //----------------------------------------------------------------------
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
