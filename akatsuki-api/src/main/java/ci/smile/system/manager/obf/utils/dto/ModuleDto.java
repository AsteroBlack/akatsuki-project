
/*
 * Java dto for entity table module 
 * Created on 2019-12-19 ( Time 23:54:47 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ci.smile.system.manager.obf.utils.contract.SearchParam;
import ci.smile.system.manager.obf.utils.dto.customize._ModuleDto;

/**
 * DTO for table "module"
 *
 * @author Geo
 */

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class ModuleDto extends _ModuleDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private String     libelle              ;
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

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   libelleParam          ;                     
	private SearchParam<String>   codeParam             ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
    /**
     * Default constructor
     */
    public ModuleDto()
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






	//----------------------------------------------------------------------
    // clone METHOD
    //----------------------------------------------------------------------
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
