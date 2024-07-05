
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

import ci.smile.system.manager.obf.utils.dto.ActionDto;

/**
 * DTO customize for table "menus"
 * 
 * @author Geo
 *
 */

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _ActionDto {
	
	private List<ActionDto> datasAction;
    private String     moduleLibelle              ;
    private String     moduleCode              ;
    private Integer     moduleId              ;
    private String     libelleModule              ;
    private String     codeModule  ;
    private Boolean isParent;
	private List<ActionDto> datasModule;
	private List<ActionDto> children;
	private List<ActionDto> datasMenus;
	public List<ActionDto> getDatasAction() {
		return datasAction;
	}
	public void setDatasAction(List<ActionDto> datasAction) {
		this.datasAction = datasAction;
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
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public String getLibelleModule() {
		return libelleModule;
	}
	public void setLibelleModule(String libelleModule) {
		this.libelleModule = libelleModule;
	}
	public String getCodeModule() {
		return codeModule;
	}
	public void setCodeModule(String codeModule) {
		this.codeModule = codeModule;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	public List<ActionDto> getDatasModule() {
		return datasModule;
	}
	public void setDatasModule(List<ActionDto> datasModule) {
		this.datasModule = datasModule;
	}
	public List<ActionDto> getChildren() {
		return children;
	}
	public void setChildren(List<ActionDto> children) {
		this.children = children;
	}
	public List<ActionDto> getDatasMenus() {
		return datasMenus;
	}
	public void setDatasMenus(List<ActionDto> datasMenus) {
		this.datasMenus = datasMenus;
	}
	
	
	

	
	
}
