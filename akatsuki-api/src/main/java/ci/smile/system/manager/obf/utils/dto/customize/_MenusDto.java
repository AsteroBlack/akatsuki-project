
/*
 * Java dto for entity table menus 
 * Created on 2019-12-19 ( Time 23:54:47 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils.dto.customize;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ci.smile.system.manager.obf.utils.dto.MenusDto;

/**
 * DTO customize for table "menus"
 * 
 * @author Geo
 *
 */

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _MenusDto {
	
	private List<MenusDto> datasMenu;
	//private List<HabilitationDto> datasHabilitation;
	private String habilitationLibelle;
	private List<MenusDto> children;

	private String habilitationCode;
	private Integer habilitationId;
	private String menusLibelle;
	private String menusCode;
	private Integer menusId;
	public List<MenusDto> getDatasMenu() {
		return datasMenu;
	}
	public void setDatasMenu(List<MenusDto> datasMenu) {
		this.datasMenu = datasMenu;
	}

	public String getHabilitationLibelle() {
		return habilitationLibelle;
	}
	public void setHabilitationLibelle(String habilitationLibelle) {
		this.habilitationLibelle = habilitationLibelle;
	}
	public List<MenusDto> getChildren() {
		return children;
	}
	public void setChildren(List<MenusDto> children) {
		this.children = children;
	}
	public String getHabilitationCode() {
		return habilitationCode;
	}
	public void setHabilitationCode(String habilitationCode) {
		this.habilitationCode = habilitationCode;
	}
	public Integer getHabilitationId() {
		return habilitationId;
	}
	public void setHabilitationId(Integer habilitationId) {
		this.habilitationId = habilitationId;
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
	public Integer getMenusId() {
		return menusId;
	}
	public void setMenusId(Integer menusId) {
		this.menusId = menusId;
	}
	
	
	


}
