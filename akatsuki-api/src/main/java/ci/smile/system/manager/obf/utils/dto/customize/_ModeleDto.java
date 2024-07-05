
/*
 * Java dto for entity table modele 
 * Created on 2021-03-09 ( Time 11:10:42 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.smile.system.manager.obf.utils.dto.customize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ci.smile.system.manager.obf.utils.dto.CoefficientModeleDto;
import lombok.Data;

/**
 * DTO customize for table "modele"
 * 
 * @author Geo
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class _ModeleDto {	
    private Integer    coefMac      ;
    private CoefficientModeleDto coefficientModele;
}
