
/*
 * Created on 2021-03-09 ( Time 11:10:34 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils.contract;

import java.util.List;

import ci.smile.system.manager.obf.utils.Status;
import ci.smile.system.manager.obf.utils.dto.CompteDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Response Base
 * 
 * @author Geo
 *
 */
@Data
@ToString
@NoArgsConstructor
public class ResponseBase {

	protected Status	status = new Status();
	protected boolean	hasError;
	protected String	sessionUser;
	protected Long		count;
	protected List<CompteDto> itemsCompte;
}
