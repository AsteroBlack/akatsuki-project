package ci.smile.system.manager.obf.dao.repository.customize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ci.smile.system.manager.obf.dao.entity.CoefficientModele;
import ci.smile.system.manager.obf.utils.dto.CoefficientModeleDto;

/**
 * Repository customize : CoefficientModele.
 */
@Repository
public interface _CoefficientModeleRepository {
	default List<String> _generateCriteria(CoefficientModeleDto dto, HashMap<String, java.lang.Object> param,
			Integer index, Locale locale) throws Exception {
		List<String> listOfQuery = new ArrayList<String>();

		// PUT YOUR RIGHT CUSTOM CRITERIA HERE

		return listOfQuery;
	}

	@Query("select e from CoefficientModele e where e.modele.id = :modeleId and e.isDeleted = :isDeleted")
	CoefficientModele findByModeleIdUnique(@Param("modeleId") Integer modeleId, @Param("isDeleted") Boolean isDeleted);

	@Query("select e from CoefficientModele e where e.modele.id = :modeleId and e.isDeleted = :isDeleted")
	List<CoefficientModele> getByModeleId(@Param("modeleId") Integer modeleId, @Param("isDeleted") Boolean isDeleted,
			Pageable page);
	
	@Query("select distinct e.coefMac from CoefficientModele e where e.isDeleted = :isDeleted")
	List<Integer> getCoefficient(@Param("isDeleted") Boolean isDeleted);

}
