package ci.smile.system.manager.obf.dao.repository;

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
import ci.smile.system.manager.obf.dao.repository.customize._OfferGroupeRepository;

/**
 * Repository : OfferGroupe.
 */
@Repository
public interface OfferGroupeRepository extends JpaRepository<OfferGroupe, Integer>, _OfferGroupeRepository {
	/**
	 * Finds OfferGroupe by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object OfferGroupe whose id is equals to the given id. If
	 *         no OfferGroupe is found, this method returns null.
	 */
	@Query("select e from OfferGroupe e where e.id = :id and e.isDeleted = :isDeleted")
	OfferGroupe findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds OfferGroupe by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object OfferGroupe whose createdAt is equals to the given createdAt. If
	 *         no OfferGroupe is found, this method returns null.
	 */
	@Query("select e from OfferGroupe e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<OfferGroupe> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds OfferGroupe by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object OfferGroupe whose updatedAt is equals to the given updatedAt. If
	 *         no OfferGroupe is found, this method returns null.
	 */
	@Query("select e from OfferGroupe e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<OfferGroupe> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds OfferGroupe by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object OfferGroupe whose deletedAt is equals to the given deletedAt. If
	 *         no OfferGroupe is found, this method returns null.
	 */
	@Query("select e from OfferGroupe e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<OfferGroupe> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds OfferGroupe by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object OfferGroupe whose createdBy is equals to the given createdBy. If
	 *         no OfferGroupe is found, this method returns null.
	 */
	@Query("select e from OfferGroupe e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<OfferGroupe> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds OfferGroupe by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object OfferGroupe whose updatedBy is equals to the given updatedBy. If
	 *         no OfferGroupe is found, this method returns null.
	 */
	@Query("select e from OfferGroupe e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<OfferGroupe> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds OfferGroupe by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object OfferGroupe whose deletedBy is equals to the given deletedBy. If
	 *         no OfferGroupe is found, this method returns null.
	 */
	@Query("select e from OfferGroupe e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<OfferGroupe> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds OfferGroupe by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object OfferGroupe whose isDeleted is equals to the given isDeleted. If
	 *         no OfferGroupe is found, this method returns null.
	 */
	@Query("select e from OfferGroupe e where e.isDeleted = :isDeleted")
	List<OfferGroupe> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds OfferGroupe by using idPlateforme as a search criteria.
	 * 
	 * @param idPlateforme
	 * @return An Object OfferGroupe whose idPlateforme is equals to the given idPlateforme. If
	 *         no OfferGroupe is found, this method returns null.
	 */
	@Query("select e from OfferGroupe e where e.plateforme.id = :idPlateforme and e.isDeleted = :isDeleted")
	List<OfferGroupe> findByIdPlateforme(@Param("idPlateforme")Integer idPlateforme, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds OfferGroupe by using idOffre as a search criteria.
	 * 
	 * @param idOffre
	 * @return An Object OfferGroupe whose idOffre is equals to the given idOffre. If
	 *         no OfferGroupe is found, this method returns null.
	 */
	@Query("select e from OfferGroupe e where e.offer.id = :idOffre and e.isDeleted = :isDeleted")
	List<OfferGroupe> findByIdOffre(@Param("idOffre")Integer idOffre, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds OfferGroupe by using idGroupe as a search criteria.
	 * 
	 * @param idGroupe
	 * @return An Object OfferGroupe whose idGroupe is equals to the given idGroupe. If
	 *         no OfferGroupe is found, this method returns null.
	 */
	@Query("select e from OfferGroupe e where e.groupe.id = :idGroupe and e.isDeleted = :isDeleted")
	List<OfferGroupe> findByIdGroupe(@Param("idGroupe")Integer idGroupe, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of OfferGroupe by using offerGroupeDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of OfferGroupe
	 * @throws DataAccessException,ParseException
	 */
	default List<OfferGroupe> getByCriteria(Request<OfferGroupeDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from OfferGroupe e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<OfferGroupe> query = em.createQuery(req, OfferGroupe.class);
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
	 * Finds count of OfferGroupe by using offerGroupeDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of OfferGroupe
	 * 
	 */
	default Long count(Request<OfferGroupeDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from OfferGroupe e where e IS NOT NULL";
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
	default String getWhereExpression(Request<OfferGroupeDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		OfferGroupeDto dto = request.getData() != null ? request.getData() : new OfferGroupeDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (OfferGroupeDto elt : request.getDatas()) {
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
	default String generateCriteria(OfferGroupeDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
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
			if (dto.getCreatedBy()!= null && dto.getCreatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdBy", dto.getCreatedBy(), "e.createdBy", "Integer", dto.getCreatedByParam(), param, index, locale));
			}
			if (dto.getUpdatedBy()!= null && dto.getUpdatedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedBy", dto.getUpdatedBy(), "e.updatedBy", "Integer", dto.getUpdatedByParam(), param, index, locale));
			}
			if (dto.getDeletedBy()!= null && dto.getDeletedBy() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("deletedBy", dto.getDeletedBy(), "e.deletedBy", "Integer", dto.getDeletedByParam(), param, index, locale));
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
			if (dto.getIdGroupe()!= null && dto.getIdGroupe() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("idGroupe", dto.getIdGroupe(), "e.groupe.id", "Integer", dto.getIdGroupeParam(), param, index, locale));
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
			if (Utilities.notBlank(dto.getGroupeLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("groupeLibelle", dto.getGroupeLibelle(), "e.groupe.libelle", "String", dto.getGroupeLibelleParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
