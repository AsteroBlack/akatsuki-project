
/*
 * Java dto for entity table module 
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
import ci.smile.system.manager.obf.utils.dto.RoleDto;

/**
 * DTO customize for table "module"
 * 
 * @author Geo
 *
 */

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _ModuleDto {

	private List<RoleDto> datasRole;
	private List<MenusDto> datasMenus;

	public List<RoleDto> getDatasRole() {
		return datasRole;
	}
	public void setDatasRole(List<RoleDto> datasRole) {
		this.datasRole = datasRole;
	}
	public List<MenusDto> getDatasMenus() {
		return datasMenus;
	}
	public void setDatasMenus(List<MenusDto> datasMenus) {
		this.datasMenus = datasMenus;
	}
	
	
	

}
