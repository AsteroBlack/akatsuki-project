
/*
 * Java dto for entity table user 
 * Created on 2019-12-16 ( Time 11:38:54 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ci.smile.system.manager.obf.utils.contract.SearchParam;
import ci.smile.system.manager.obf.utils.dto.customize._UserDto;
import lombok.Data;

/**
 * DTO for table "user"
 *
 * @author Geo
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class UserDto extends _UserDto implements Cloneable {

	private Integer id; // Primary Key

	private String matricule;
	private String nom;
	private String prenom;
	private String email;
	private String login;
	private String password;
	private String contact;
	private Boolean isSuperAdmin;
	private Boolean isAutorize;
	private Boolean isDefaultPassword;
	private Boolean isLocked;
	private String createdAt;
	private String updatedAt;
	private String deletedAt;
	private Integer createdBy;
	private Integer updatedBy;
	private Integer deletedBy;
	private Boolean isDeleted;
	private Boolean isConnected;
	private String roleFull;
	private String roleSwap;
	private String roleChgeId;
	private String roleACQ;

	// ----------------------------------------------------------------------
	// ENTITY LINKS FIELD ( RELATIONSHIP )
	// ----------------------------------------------------------------------

	// Search param
	private SearchParam<Integer> idParam;
	private SearchParam<String> matriculeParam;
	private SearchParam<Boolean> isConnectedParam;
	private SearchParam<String> nomParam;
	private SearchParam<String> prenomParam;
	private SearchParam<String> emailParam;
	private SearchParam<String> loginParam;
	private SearchParam<String> passwordParam;
	private SearchParam<String> contactParam;
	private SearchParam<Boolean> isSuperAdminParam;
	private SearchParam<Boolean> isAutorizeParam;
	private SearchParam<Boolean> isDefaultPasswordParam;
	private SearchParam<Boolean> isLockedParam;
	private SearchParam<String> createdAtParam;
	private SearchParam<String> updatedAtParam;
	private SearchParam<String> deletedAtParam;
	private SearchParam<Integer> createdByParam;
	private SearchParam<Integer> updatedByParam;
	private SearchParam<Integer> deletedByParam;
	private SearchParam<Boolean> isDeletedParam;

	/**
	 * Default constructor
	 */
//    public UserDto()
//    {
//        super();
//    }
//    
//    
//    
//    
//    
//	public Integer getId() {
//		return id;
//	}
//
//
//
//
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//
//
//
//
//	public String getMatricule() {
//		return matricule;
//	}
//
//
//
//
//
//	public void setMatricule(String matricule) {
//		this.matricule = matricule;
//	}
//
//
//
//
//
//	public String getNom() {
//		return nom;
//	}
//
//
//
//
//
//	public void setNom(String nom) {
//		this.nom = nom;
//	}
//
//
//
//
//
//	public String getPrenom() {
//		return prenom;
//	}
//
//
//
//
//
//	public void setPrenom(String prenom) {
//		this.prenom = prenom;
//	}
//
//
//
//
//
//	public String getEmail() {
//		return email;
//	}
//
//
//
//
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//
//
//
//
//	public String getLogin() {
//		return login;
//	}
//
//
//
//
//
//	public void setLogin(String login) {
//		this.login = login;
//	}
//
//
//
//
//
//	public String getPassword() {
//		return password;
//	}
//
//
//
//
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//
//
//
//
//	public String getContact() {
//		return contact;
//	}
//
//
//
//
//
//	public void setContact(String contact) {
//		this.contact = contact;
//	}
//
//
//
//
//
//	public Boolean getIsSuperAdmin() {
//		return isSuperAdmin;
//	}
//
//
//
//
//
//	public void setIsSuperAdmin(Boolean isSuperAdmin) {
//		this.isSuperAdmin = isSuperAdmin;
//	}
//
//
//
//
//
//	public Boolean getIsAutorize() {
//		return isAutorize;
//	}
//
//
//
//
//
//	public void setIsAutorize(Boolean isAutorize) {
//		this.isAutorize = isAutorize;
//	}
//
//
//
//
//
//	public Boolean getIsDefaultPassword() {
//		return isDefaultPassword;
//	}
//
//
//
//
//
//	public void setIsDefaultPassword(Boolean isDefaultPassword) {
//		this.isDefaultPassword = isDefaultPassword;
//	}
//
//
//
//
//
//	public Boolean getIsLocked() {
//		return isLocked;
//	}
//
//
//
//
//
//	public void setIsLocked(Boolean isLocked) {
//		this.isLocked = isLocked;
//	}
//
//
//
//
//
//	public String getCreatedAt() {
//		return createdAt;
//	}
//
//
//
//
//
//	public void setCreatedAt(String createdAt) {
//		this.createdAt = createdAt;
//	}
//
//
//
//
//
//	public String getUpdatedAt() {
//		return updatedAt;
//	}
//
//
//
//
//
//	public void setUpdatedAt(String updatedAt) {
//		this.updatedAt = updatedAt;
//	}
//
//
//
//
//
//	public String getDeletedAt() {
//		return deletedAt;
//	}
//
//
//
//
//
//	public void setDeletedAt(String deletedAt) {
//		this.deletedAt = deletedAt;
//	}
//
//
//
//
//
//	public Integer getCreatedBy() {
//		return createdBy;
//	}
//
//
//
//
//
//	public void setCreatedBy(Integer createdBy) {
//		this.createdBy = createdBy;
//	}
//
//
//
//
//
//	public Integer getUpdatedBy() {
//		return updatedBy;
//	}
//
//
//
//
//
//	public void setUpdatedBy(Integer updatedBy) {
//		this.updatedBy = updatedBy;
//	}
//
//
//
//
//
//	public Integer getDeletedBy() {
//		return deletedBy;
//	}
//
//
//
//
//
//	public void setDeletedBy(Integer deletedBy) {
//		this.deletedBy = deletedBy;
//	}
//
//
//
//
//
//	public Boolean getIsDeleted() {
//		return isDeleted;
//	}
//
//
//
//
//
//	public void setIsDeleted(Boolean isDeleted) {
//		this.isDeleted = isDeleted;
//	}
//
//
//
//
//
//	public Boolean getIsConnected() {
//		return isConnected;
//	}
//
//
//
//
//
//	public void setIsConnected(Boolean isConnected) {
//		this.isConnected = isConnected;
//	}
//
//
//
//
//
//	public String getRoleFull() {
//		return roleFull;
//	}
//
//
//
//
//
//	public void setRoleFull(String roleFull) {
//		this.roleFull = roleFull;
//	}
//
//
//
//
//
//	public String getRoleSwap() {
//		return roleSwap;
//	}
//
//
//
//
//
//	public void setRoleSwap(String roleSwap) {
//		this.roleSwap = roleSwap;
//	}
//
//
//
//
//
//	public String getRoleChgeId() {
//		return roleChgeId;
//	}
//
//
//
//
//
//	public void setRoleChgeId(String roleChgeId) {
//		this.roleChgeId = roleChgeId;
//	}
//
//
//
//
//
//	public String getRoleACQ() {
//		return roleACQ;
//	}
//
//
//
//
//
//	public void setRoleACQ(String roleACQ) {
//		this.roleACQ = roleACQ;
//	}
//
//
//
//
//
//	public SearchParam<Integer> getIdParam() {
//		return idParam;
//	}
//
//
//
//
//
//	public void setIdParam(SearchParam<Integer> idParam) {
//		this.idParam = idParam;
//	}
//
//
//
//
//
//	public SearchParam<String> getMatriculeParam() {
//		return matriculeParam;
//	}
//
//
//
//
//
//	public void setMatriculeParam(SearchParam<String> matriculeParam) {
//		this.matriculeParam = matriculeParam;
//	}
//
//
//
//
//
//	public SearchParam<Boolean> getIsConnectedParam() {
//		return isConnectedParam;
//	}
//
//
//
//
//
//	public void setIsConnectedParam(SearchParam<Boolean> isConnectedParam) {
//		this.isConnectedParam = isConnectedParam;
//	}
//
//
//
//
//
//	public SearchParam<String> getNomParam() {
//		return nomParam;
//	}
//
//
//
//
//
//	public void setNomParam(SearchParam<String> nomParam) {
//		this.nomParam = nomParam;
//	}
//
//
//
//
//
//	public SearchParam<String> getPrenomParam() {
//		return prenomParam;
//	}
//
//
//
//
//
//	public void setPrenomParam(SearchParam<String> prenomParam) {
//		this.prenomParam = prenomParam;
//	}
//
//
//
//
//
//	public SearchParam<String> getEmailParam() {
//		return emailParam;
//	}
//
//
//
//
//
//	public void setEmailParam(SearchParam<String> emailParam) {
//		this.emailParam = emailParam;
//	}
//
//
//
//
//
//	public SearchParam<String> getLoginParam() {
//		return loginParam;
//	}
//
//
//
//
//
//	public void setLoginParam(SearchParam<String> loginParam) {
//		this.loginParam = loginParam;
//	}
//
//
//
//
//
//	public SearchParam<String> getPasswordParam() {
//		return passwordParam;
//	}
//
//
//
//
//
//	public void setPasswordParam(SearchParam<String> passwordParam) {
//		this.passwordParam = passwordParam;
//	}
//
//
//
//
//
//	public SearchParam<String> getContactParam() {
//		return contactParam;
//	}
//
//
//
//
//
//	public void setContactParam(SearchParam<String> contactParam) {
//		this.contactParam = contactParam;
//	}
//
//
//
//
//
//	public SearchParam<Boolean> getIsSuperAdminParam() {
//		return isSuperAdminParam;
//	}
//
//
//
//
//
//	public void setIsSuperAdminParam(SearchParam<Boolean> isSuperAdminParam) {
//		this.isSuperAdminParam = isSuperAdminParam;
//	}
//
//
//
//
//
//	public SearchParam<Boolean> getIsAutorizeParam() {
//		return isAutorizeParam;
//	}
//
//
//
//
//
//	public void setIsAutorizeParam(SearchParam<Boolean> isAutorizeParam) {
//		this.isAutorizeParam = isAutorizeParam;
//	}
//
//
//
//
//
//	public SearchParam<Boolean> getIsDefaultPasswordParam() {
//		return isDefaultPasswordParam;
//	}
//
//
//
//
//
//	public void setIsDefaultPasswordParam(SearchParam<Boolean> isDefaultPasswordParam) {
//		this.isDefaultPasswordParam = isDefaultPasswordParam;
//	}
//
//
//
//
//
//	public SearchParam<Boolean> getIsLockedParam() {
//		return isLockedParam;
//	}
//
//
//
//
//
//	public void setIsLockedParam(SearchParam<Boolean> isLockedParam) {
//		this.isLockedParam = isLockedParam;
//	}
//
//
//
//
//
//	public SearchParam<String> getCreatedAtParam() {
//		return createdAtParam;
//	}
//
//
//
//
//
//	public void setCreatedAtParam(SearchParam<String> createdAtParam) {
//		this.createdAtParam = createdAtParam;
//	}
//
//
//
//
//
//	public SearchParam<String> getUpdatedAtParam() {
//		return updatedAtParam;
//	}
//
//
//
//
//
//	public void setUpdatedAtParam(SearchParam<String> updatedAtParam) {
//		this.updatedAtParam = updatedAtParam;
//	}
//
//
//
//
//
//	public SearchParam<String> getDeletedAtParam() {
//		return deletedAtParam;
//	}
//
//
//
//
//
//	public void setDeletedAtParam(SearchParam<String> deletedAtParam) {
//		this.deletedAtParam = deletedAtParam;
//	}
//
//
//
//
//
//	public SearchParam<Integer> getCreatedByParam() {
//		return createdByParam;
//	}
//
//
//
//
//
//	public void setCreatedByParam(SearchParam<Integer> createdByParam) {
//		this.createdByParam = createdByParam;
//	}
//
//
//
//
//
//	public SearchParam<Integer> getUpdatedByParam() {
//		return updatedByParam;
//	}
//
//
//
//
//
//	public void setUpdatedByParam(SearchParam<Integer> updatedByParam) {
//		this.updatedByParam = updatedByParam;
//	}
//
//
//
//
//
//	public SearchParam<Integer> getDeletedByParam() {
//		return deletedByParam;
//	}
//
//
//
//
//
//	public void setDeletedByParam(SearchParam<Integer> deletedByParam) {
//		this.deletedByParam = deletedByParam;
//	}
//
//
//
//
//
//	public SearchParam<Boolean> getIsDeletedParam() {
//		return isDeletedParam;
//	}
//
//
//
//
//
//	public void setIsDeletedParam(SearchParam<Boolean> isDeletedParam) {
//		this.isDeletedParam = isDeletedParam;
//	}

	public UserDto() {
		super();
	}

	// ----------------------------------------------------------------------
	// clone METHOD
	// ----------------------------------------------------------------------
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
