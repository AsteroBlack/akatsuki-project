package ci.smile.system.manager.obf.dao.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ci.smile.system.manager.obf.dao.entity.Radius;
import ci.smile.system.manager.obf.dao.repository.customize._RadiusRepository;
import ci.smile.system.manager.obf.utils.CriteriaUtils;
import ci.smile.system.manager.obf.utils.Utilities;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.dto.RadiusDto;

/**
 * Repository : Radius.
 */
@Repository
public interface RadiusRepository extends JpaRepository<Radius, Integer>, _RadiusRepository {
	/**
	 * Finds Radius by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Radius whose id is equals to the given id. If
	 *         no Radius is found, this method returns null.
	 */
	@Query("select e from Radius e where e.id = :id and e.isDeleted = :isDeleted")
	Radius findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Radius by using offre as a search criteria.
	 * 
	 * @param offre
	 * @return An Object Radius whose offre is equals to the given offre. If
	 *         no Radius is found, this method returns null.
	 */
	@Query("select e from Radius e where e.offre = :offre and e.isDeleted = :isDeleted")
	List<Radius> findByOffre(@Param("offre")String offre, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Radius by using localisation as a search criteria.
	 * 
	 * @param localisation
	 * @return An Object Radius whose localisation is equals to the given localisation. If
	 *         no Radius is found, this method returns null.
	 */
	@Query("select e from Radius e where e.localisation = :localisation and e.isDeleted = :isDeleted")
	List<Radius> findByLocalisation(@Param("localisation")String localisation, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Radius by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Radius whose createdAt is equals to the given createdAt. If
	 *         no Radius is found, this method returns null.
	 */
	@Query("select e from Radius e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Radius> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Radius by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Radius whose updatedAt is equals to the given updatedAt. If
	 *         no Radius is found, this method returns null.
	 */
	@Query("select e from Radius e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Radius> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Radius by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Radius whose deletedAt is equals to the given deletedAt. If
	 *         no Radius is found, this method returns null.
	 */
	@Query("select e from Radius e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Radius> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Radius by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Radius whose isDeleted is equals to the given isDeleted. If
	 *         no Radius is found, this method returns null.
	 */
	@Query("select e from Radius e where e.isDeleted = :isDeleted")
	List<Radius> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Radius by using groupeRadiusId as a search criteria.
	 * 
	 * @param groupeRadiusId
	 * @return An Object Radius whose groupeRadiusId is equals to the given groupeRadiusId. If
	 *         no Radius is found, this method returns null.
	 */
	@Query("select e from Radius e where e.groupeRadius.id = :groupeRadiusId and e.isDeleted = :isDeleted")
	List<Radius> findByGroupeRadiusId(@Param("groupeRadiusId")Integer groupeRadiusId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Radius by using typeOffreId as a search criteria.
	 * 
	 * @param typeOffreId
	 * @return An Object Radius whose typeOffreId is equals to the given typeOffreId. If
	 *         no Radius is found, this method returns null.
	 */
	@Query("select e from Radius e where e.typeOffre.id = :typeOffreId and e.isDeleted = :isDeleted")
	List<Radius> findByTypeOffreId(@Param("typeOffreId")Integer typeOffreId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Radius by using radiusDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Radius
	 * @throws DataAccessException,ParseException
	 */
	default List<Radius> getByCriteria(Request<RadiusDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Radius e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Radius> query = em.createQuery(req, Radius.class);
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
	 * Finds count of Radius by using radiusDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Radius
	 * 
	 */
	default Long count(Request<RadiusDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Radius e where e IS NOT NULL";
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
	default String getWhereExpression(Request<RadiusDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		RadiusDto dto = request.getData() != null ? request.getData() : new RadiusDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (RadiusDto elt : request.getDatas()) {
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
	default String generateCriteria(RadiusDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getOffre())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("offre", dto.getOffre(), "e.offre", "String", dto.getOffreParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLocalisation())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("localisation", dto.getLocalisation(), "e.localisation", "String", dto.getLocalisationParam(), param, index, locale));
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
			if (dto.getIsDeleted()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDeleted", dto.getIsDeleted(), "e.isDeleted", "Boolean", dto.getIsDeletedParam(), param, index, locale));
			}
			if (dto.getGroupeRadiusId()!= null && dto.getGroupeRadiusId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("groupeRadiusId", dto.getGroupeRadiusId(), "e.groupeRadius.id", "Integer", dto.getGroupeRadiusIdParam(), param, index, locale));
			}
			if (dto.getTypeOffreId()!= null && dto.getTypeOffreId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("typeOffreId", dto.getTypeOffreId(), "e.typeOffre.id", "Integer", dto.getTypeOffreIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getGroupeRadiusCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("groupeRadiusCode", dto.getGroupeRadiusCode(), "e.groupeRadius.code", "String", dto.getGroupeRadiusCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getGroupeRadiusLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("groupeRadiusLibelle", dto.getGroupeRadiusLibelle(), "e.groupeRadius.libelle", "String", dto.getGroupeRadiusLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTypeOffreCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("typeOffreCode", dto.getTypeOffreCode(), "e.typeOffre.code", "String", dto.getTypeOffreCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getTypeOffreLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("typeOffreLibelle", dto.getTypeOffreLibelle(), "e.typeOffre.libelle", "String", dto.getTypeOffreLibelleParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
