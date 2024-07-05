
/*
 * Java dto for entity table user 
 * Created on 2019-12-16 ( Time 11:38:54 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils.dto.customize;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ci.smile.system.manager.obf.utils.dto.ActionDto;
import ci.smile.system.manager.obf.utils.dto.RoleDto;
import lombok.Data;
import lombok.ToString;

/**
 * DTO customize for table "user"
 * 
 * @author Geo
 *
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _UserDto {


	private Boolean isLdap;
	private String key;
	private String dateDebut;
	private String dateFin;
	private List<RoleDto> datasRole;
	private List<ActionDto> itemsMenus;
	private List<ActionDto> datasModule;
	//private Map<String, Object> itemsModule;
	private String token;
	private String newPassword;
	//	private String prenom;
	//	private String email;
	private Integer nbreCree;
	private String uid;
	private String commonName;
	private String fonction;
	private String department;
	private String telephone;
	//private List<ModuleDto> datasModule;
//	public Boolean getIsLdap() {
//		return isLdap;
//	}
//	public void setIsLdap(Boolean isLdap) {
//		this.isLdap = isLdap;
//	}
//	public String getKey() {
//		return key;
//	}
//	public void setKey(String key) {
//		this.key = key;
//	}
//	public String getDateDebut() {
//		return dateDebut;
//	}
//	public void setDateDebut(String dateDebut) {
//		this.dateDebut = dateDebut;
//	}
//	public String getDateFin() {
//		return dateFin;
//	}
//	public void setDateFin(String dateFin) {
//		this.dateFin = dateFin;
//	}
//	public List<RoleDto> getDatasRole() {
//		return datasRole;
//	}
//	public void setDatasRole(List<RoleDto> datasRole) {
//		this.datasRole = datasRole;
//	}
//	public List<ActionDto> getItemsMenus() {
//		return itemsMenus;
//	}
//	public void setItemsMenus(List<ActionDto> itemsMenus) {
//		this.itemsMenus = itemsMenus;
//	}
//	public List<ActionDto> getDatasModule() {
//		return datasModule;
//	}
//	public void setDatasModule(List<ActionDto> datasModule) {
//		this.datasModule = datasModule;
//	}
//	public String getToken() {
//		return token;
//	}
//	public void setToken(String token) {
//		this.token = token;
//	}
//	public String getNewPassword() {
//		return newPassword;
//	}
//	public void setNewPassword(String newPassword) {
//		this.newPassword = newPassword;
//	}
//	public Integer getNbreCree() {
//		return nbreCree;
//	}
//	public void setNbreCree(Integer nbreCree) {
//		this.nbreCree = nbreCree;
//	}
//	public String getUid() {
//		return uid;
//	}
//	public void setUid(String uid) {
//		this.uid = uid;
//	}
//	public String getCommonName() {
//		return commonName;
//	}
//	public void setCommonName(String commonName) {
//		this.commonName = commonName;
//	}
//	public String getFonction() {
//		return fonction;
//	}
//	public void setFonction(String fonction) {
//		this.fonction = fonction;
//	}
//	public String getDepartment() {
//		return department;
//	}
//	public void setDepartment(String department) {
//		this.department = department;	
//	}
//	public String getTelephone() {
//		return telephone;
//	}
//	public void setTelephone(String telephone) {
//		this.telephone = telephone;
//	}
//	
	
	
	
}
