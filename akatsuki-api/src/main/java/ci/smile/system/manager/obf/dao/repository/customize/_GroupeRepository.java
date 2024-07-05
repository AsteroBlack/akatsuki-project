package ci.smile.system.manager.obf.dao.repository.customize;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ci.smile.system.manager.obf.utils.*;
import ci.smile.system.manager.obf.utils.dto.*;
import ci.smile.system.manager.obf.utils.contract.*;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.contract.Response;
import ci.smile.system.manager.obf.dao.entity.*;

/**
 * Repository customize : Groupe.
 */
@Repository
public interface _GroupeRepository {
//	@Query("select e from Groupe e where e.offer.id = :idOffre and e.modeleBoxe = :modeleBoxe")
//	List<Groupe> findByPlateformeOffre(@Param("idOffre")Integer idOffre, @Param("modeleBoxe")String modeleBoxe);
	
	@Query("select e from Groupe e where e.plateforme.id = :idPlateforme")
	List<Groupe> findByPlateformeIdOffre(@Param("idPlateforme")Integer idPlateforme);
	
	@Query("select e from Groupe e where e.id = :id and e.plateforme.id = :idPlateforme and e.isDeleted = :isDeleted")
	Groupe findOneAndPlateforme(@Param("id")Integer id, @Param("idPlateforme")Integer idPlateforme, @Param("isDeleted")Boolean isDeleted);
	@Query("select e from Groupe e where e.offer.id = :idOffre and e.plateforme.id = :idPlateforme and e.isDeleted = :isDeleted")
	Groupe findOneOffreAndPlateforme(@Param("idOffre")Integer idOffre, @Param("idPlateforme")Integer idPlateforme, @Param("isDeleted")Boolean isDeleted);
	@Query("select e from Groupe e where e.offer.id = :idOffre and e.modeleBoxe = :modeleBoxe and e.plateforme.id = :idPlateforme and e.isDeleted = :isDeleted")
	Groupe findOneOffreAndPlateformeModele(@Param("idOffre")Integer idOffre,@Param("modeleBoxe")String modeleBoxe, @Param("idPlateforme")Integer idPlateforme, @Param("isDeleted")Boolean isDeleted);
	@Query("select e from Groupe e where e.id = :id and e.offer.id = :idOffre and e.plateforme.id = :idPlateforme and e.isDeleted = :isDeleted")
	Groupe findOneGroupeOffreAndPlateforme(@Param("id")Integer id,@Param("idOffre")Integer idOffre, @Param("idPlateforme")Integer idPlateforme, @Param("isDeleted")Boolean isDeleted);
	@Query("select e from Groupe e where e.id = :id and e.offer.id = :idOffre and e.plateforme.id = :idPlateforme and e.isDeleted = :isDeleted")
	Groupe findOneOffreGroupeAndPlateforme(@Param("id")Integer id, @Param("idOffre")Integer idOffre, @Param("idPlateforme")Integer idPlateforme, @Param("isDeleted")Boolean isDeleted);
	@Query("select e from Groupe e where e.libelle = :libelle and e.isDeleted = :isDeleted")
	List<Groupe> findByLibelles(@Param("libelle")String libelle, @Param("isDeleted")Boolean isDeleted);
	
//	@Query("select e from Groupe e where e.modeleBoxe = :modeleBoxe and e.plateforme.id = :idPlateforme and e.isDeleted = :isDeleted")
//	List<Groupe> findByPlateformeModelOffre(@Param("modeleBoxe")String modeleBoxe, @Param("idPlateforme")Integer idPlateforme, @Param("isDeleted")Boolean isDeleted);
	default List<String> _generateCriteria(GroupeDto dto, HashMap<String, java.lang.Object> param, Integer index, Locale locale) throws Exception {
		List<String> listOfQuery = new ArrayList<String>();

		// PUT YOUR RIGHT CUSTOM CRITERIA HERE

		return listOfQuery;
	}
}
