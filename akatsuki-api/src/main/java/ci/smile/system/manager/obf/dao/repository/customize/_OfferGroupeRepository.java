package ci.smile.system.manager.obf.dao.repository.customize;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ci.smile.system.manager.obf.utils.dto.*;
import ci.smile.system.manager.obf.dao.entity.*;

/**
 * Repository customize : OfferGroupe.
 */
@Repository
public interface _OfferGroupeRepository {
	@Query("select e from OfferGroupe e where e.offer.id = :idOffre and e.groupe.id = :idGroupe and  e.plateforme.id = :idPlateforme and  e.isDeleted = :isDeleted")
	OfferGroupe findByIdOffreIdGroupe(@Param("idOffre")Integer idOffre,@Param("idGroupe")Integer idGroupe,@Param("idPlateforme")Integer idPlateforme, @Param("isDeleted")Boolean isDeleted);
	@Query("select e from OfferGroupe e where e.offer.id = :idOffre and  e.plateforme.id = :idPlateforme and  e.isDeleted = :isDeleted")
	OfferGroupe findByIdOffreIdPlateForme(@Param("idOffre")Integer idOffre,@Param("idPlateforme")Integer idPlateforme, @Param("isDeleted")Boolean isDeleted);
	@Query("select e from OfferGroupe e where e.offer.id = :idOffre and  e.plateforme.id = :idPlateforme and  e.isDeleted = :isDeleted")
	OfferGroupe findByGroupeByIdOffre(@Param("idOffre")Integer idOffre,@Param("idPlateforme")Integer idPlateforme, @Param("isDeleted")Boolean isDeleted);
	default List<String> _generateCriteria(OfferGroupeDto dto, HashMap<String, java.lang.Object> param, Integer index, Locale locale) throws Exception {
		List<String> listOfQuery = new ArrayList<String>();

		// PUT YOUR RIGHT CUSTOM CRITERIA HERE

		return listOfQuery;
	}
}
