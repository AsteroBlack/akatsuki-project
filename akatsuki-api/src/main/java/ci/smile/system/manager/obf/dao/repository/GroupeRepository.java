package ci.smile.system.manager.obf.dao.repository;

import java.util.Date;
import java.util.List;
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
import ci.smile.system.manager.obf.dao.repository.customize._GroupeRepository;

/**
 * Repository : Groupe.
 */
@Repository
public interface GroupeRepository extends JpaRepository<Groupe, Integer>, _GroupeRepository {
	/**
	 * Finds Groupe by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Groupe whose id is equals to the given id. If
	 *         no Groupe is found, this method returns null.
	 */
	@Query("select e from Groupe e where e.id = :id and e.isDeleted = :isDeleted")
	Groupe findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Groupe by using libelle as a search criteria.
	 * 
	 * @param libelle
	 * @return An Object Groupe whose libelle is equals to the given libelle. If
	 *         no Groupe is found, this method returns null.
	 */
	@Query("select e from Groupe e where e.libelle = :libelle and e.isDeleted = :isDeleted")
	Groupe findByLibelle(@Param("libelle")String libelle, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Groupe by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Groupe whose createdAt is equals to the given createdAt. If
	 *         no Groupe is found, this method returns null.
	 */
	@Query("select e from Groupe e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Groupe> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Groupe by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Groupe whose updatedAt is equals to the given updatedAt. If
	 *         no Groupe is found, this method returns null.
	 */
	@Query("select e from Groupe e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Groupe> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Groupe by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Groupe whose deletedAt is equals to the given deletedAt. If
	 *         no Groupe is found, this method returns null.
	 */
	@Query("select e from Groupe e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Groupe> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Groupe by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Groupe whose deletedBy is equals to the given deletedBy. If
	 *         no Groupe is found, this method returns null.
	 */
	@Query("select e from Groupe e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Groupe> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Groupe by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Groupe whose updatedBy is equals to the given updatedBy. If
	 *         no Groupe is found, this method returns null.
	 */
	@Query("select e from Groupe e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Groupe> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Groupe by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Groupe whose createdBy is equals to the given createdBy. If
	 *         no Groupe is found, this method returns null.
	 */
	@Query("select e from Groupe e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Groupe> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Groupe by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Groupe whose isDeleted is equals to the given isDeleted. If
	 *         no Groupe is found, this method returns null.
	 */
	@Query("select e from Groupe e where e.isDeleted = :isDeleted")
	List<Groupe> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Groupe by using idPlateforme as a search criteria.
	 * 
	 * @param idPlateforme
	 * @return An Object Groupe whose idPlateforme is equals to the given idPlateforme. If
	 *         no Groupe is found, this method returns null.
	 */
	@Query("select e from Groupe e where e.plateforme.id = :idPlateforme and e.isDeleted = :isDeleted")
	List<Groupe> findByIdPlateforme(@Param("idPlateforme")Integer idPlateforme, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Groupe by using idOffre as a search criteria.
	 * 
	 * @param idOffre
	 * @return An Object Groupe whose idOffre is equals to the given idOffre. If
	 *         no Groupe is found, this method returns null.
	 */
	@Query("select e from Groupe e where e.offer.id = :idOffre and e.isDeleted = :isDeleted")
	List<Groupe> findByIdOffre(@Param("idOffre")Integer idOffre, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Groupe by using groupeDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Groupe
	 * @throws DataAccessException,ParseException
	 */
	default List<Groupe> getByCriteria(Request<GroupeDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Groupe e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Groupe> query = em.createQuery(req, Groupe.class);
		for (Map.Entry<String, java.lang.Object> entry : param.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		if (request.getIndex() != null && request.getSize() != null) {
			query.setFirstResult(request.getIndex() * request.getSize());
			query.setMaxResults(request.getSize());
		}
		return query.getResultList();
	}

	/**
	 * Finds count of Groupe by using groupeDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Groupe
	 * 
	 */
	default Long count(Request<GroupeDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Groupe e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by  e.id desc";
		javax.persistence.Query query = em.createQuery(req);
		for (Map.Entry<String, java.lang.Object> entry : param.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		Long count = (Long) query.getResultList().get(0);
		return count;
	}

	/**
	 * get where expression
	 * @param request
	 * @param param
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	default String getWhereExpression(Request<GroupeDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		GroupeDto dto = request.getData() != null ? request.getData() : new GroupeDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (GroupeDto elt : request.getDatas()) {
				elt.setIsDeleted(false);
				String eltReq = generateCriteria(elt, param, index, locale);
				if (request.getIsAnd() != null && request.getIsAnd()) {
					othersReq += "and (" + eltReq + ") ";
				} else {
					othersReq += "or (" + eltReq + ") ";
				}
				index++;
			}
		}
		String req = "";
		if (!mainReq.isEmpty()) {
			req += " and (" + mainReq + ") ";
		}
		req += othersReq;
		return req;
	}

	/**
	 * generate sql query for dto
	 * @param dto
	 * @param param
	 * @param index
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	default String generateCriteria(GroupeDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("libelle", dto.getLibelle(), "e.libelle", "String", dto.getLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getModeleBoxe())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("modeleBoxe", dto.getModeleBoxe(), "e.modeleBoxe", "String", dto.getModeleBoxeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCreatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getUpdatedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDeletedAt())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("deletedAt", dto.getDeletedAt(), "e.deletedAt", "Date", dto.getDeletedAtParam(), param, index, locale));
			}
			if (dto.getDeletedBy()!= null && dto.getDeletedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("deletedBy", dto.getDeletedBy(), "e.deletedBy", "Integer", dto.getDeletedByParam(), param, index, locale));
			}
			if (dto.getUpdatedBy()!= null && dto.getUpdatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedBy", dto.getUpdatedBy(), "e.updatedBy", "Integer", dto.getUpdatedByParam(), param, index, locale));
			}
			if (dto.getCreatedBy()!= null && dto.getCreatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdBy", dto.getCreatedBy(), "e.createdBy", "Integer", dto.getCreatedByParam(), param, index, locale));
			}
			if (dto.getIsDeleted()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDeleted", dto.getIsDeleted(), "e.isDeleted", "Boolean", dto.getIsDeletedParam(), param, index, locale));
			}
			if (dto.getIdPlateforme()!= null && dto.getIdPlateforme() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("idPlateforme", dto.getIdPlateforme(), "e.plateforme.id", "Integer", dto.getIdPlateformeParam(), param, index, locale));
			}
			if (dto.getIdOffre()!= null && dto.getIdOffre() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("idOffre", dto.getIdOffre(), "e.offer.id", "Integer", dto.getIdOffreParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPlateformeCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("plateformeCode", dto.getPlateformeCode(), "e.plateforme.code", "String", dto.getPlateformeCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPlateformeLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("plateformeLibelle", dto.getPlateformeLibelle(), "e.plateforme.libelle", "String", dto.getPlateformeLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getOfferLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("offerLibelle", dto.getOfferLibelle(), "e.offer.libelle", "String", dto.getOfferLibelleParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
