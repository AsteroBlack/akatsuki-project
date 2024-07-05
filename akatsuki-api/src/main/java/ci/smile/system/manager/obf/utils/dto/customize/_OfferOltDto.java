
/*
 * Java dto for entity table offer_olt 
 * Created on 2022-11-08 ( Time 19:23:44 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils.dto.customize;

import java.util.Date;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ci.smile.system.manager.obf.dao.entity.Groupe;
import ci.smile.system.manager.obf.utils.contract.*;
import ci.smile.system.manager.obf.utils.dto.GroupeDto;
import ci.smile.system.manager.obf.utils.dto.OfferOltDto;
import lombok.Data;
import lombok.ToString;

/**
 * DTO customize for table "offer_olt"
 * 
 * @author Geo
 *
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _OfferOltDto {
	private String groupeName;
	private OfferOltDto datasOfferOlt;
	private String modeleBoxe;
}
