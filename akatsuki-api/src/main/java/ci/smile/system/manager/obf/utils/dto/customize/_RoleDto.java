
/*
 * Java dto for entity table role 
 * Created on 2019-12-19 ( Time 23:54:47 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils.dto.customize;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ci.smile.system.manager.obf.utils.dto.ActionDto;
import ci.smile.system.manager.obf.utils.dto.MenusDto;
import ci.smile.system.manager.obf.utils.dto.RoleActionDto;
import lombok.Data;

/**
 * DTO customize for table "role"
 * 
 * @author Geo
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _RoleDto {
	
	private String habilitationLibelle;
	private String habilitationCode;
	private String roleLibelle;
	private String roleCode;
	private String menusLibelle;
	
	private List<ActionDto> datasModule;
	private List<ActionDto> datasMenu;
	private List<ActionDto> datasAction;
	private List<RoleActionDto> datasRoleAction;

	
	private String menusCode;
	private List<MenusDto> dataMenus;
	private Integer menusId;
	private List<MenusDto> datasMenus;
//	public String getHabilitationLibelle() {
//		return habilitationLibelle;
//	}
//	public void setHabilitationLibelle(String habilitationLibelle) {
//		this.habilitationLibelle = habilitationLibelle;
//	}
//	public String getHabilitationCode() {
//		return habilitationCode;
//	}
//	public void setHabilitationCode(String habilitationCode) {
//		this.habilitationCode = habilitationCode;
//	}
//	public String getRoleLibelle() {
//		return roleLibelle;
//	}
//	public void setRoleLibelle(String roleLibelle) {
//		this.roleLibelle = roleLibelle;
//	}
//	public String getRoleCode() {
//		return roleCode;
//	}
//	public void setRoleCode(String roleCode) {
//		this.roleCode = roleCode;
//	}
//	public String getMenusLibelle() {
//		return menusLibelle;
//	}
//	public void setMenusLibelle(String menusLibelle) {
//		this.menusLibelle = menusLibelle;
//	}
//	public List<ActionDto> getDatasModule() {
//		return datasModule;
//	}
//	public void setDatasModule(List<ActionDto> datasModule) {
//		this.datasModule = datasModule;
//	}
//	public List<ActionDto> getDatasMenu() {
//		return datasMenu;
//	}
//	public void setDatasMenu(List<ActionDto> datasMenu) {
//		this.datasMenu = datasMenu;
//	}
//	public List<ActionDto> getDatasAction() {
//		return datasAction;
//	}
//	public void setDatasAction(List<ActionDto> datasAction) {
//		this.datasAction = datasAction;
//	}
//	public List<RoleActionDto> getDatasRoleAction() {
//		return datasRoleAction;
//	}
//	public void setDatasRoleAction(List<RoleActionDto> datasRoleAction) {
//		this.datasRoleAction = datasRoleAction;
//	}
//	public String getMenusCode() {
//		return menusCode;
//	}
//	public void setMenusCode(String menusCode) {
//		this.menusCode = menusCode;
//	}
//	public List<MenusDto> getDataMenus() {
//		return dataMenus;
//	}
//	public void setDataMenus(List<MenusDto> dataMenus) {
//		this.dataMenus = dataMenus;
//	}
//	
//	public Integer getMenusId() {
//		return menusId;
//	}
//	public void setMenusId(Integer menusId) {
//		this.menusId = menusId;
//	}
//	public List<MenusDto> getDatasMenus() {
//		return datasMenus;
//	}
//	public void setDatasMenus(List<MenusDto> datasMenus) {
//		this.datasMenus = datasMenus;
//	}
	
	
	
	

}
