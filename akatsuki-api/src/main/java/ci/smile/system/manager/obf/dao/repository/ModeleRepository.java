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

import ci.smile.system.manager.obf.dao.entity.Modele;
import ci.smile.system.manager.obf.dao.repository.customize._ModeleRepository;
import ci.smile.system.manager.obf.utils.CriteriaUtils;
import ci.smile.system.manager.obf.utils.Utilities;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.dto.ModeleDto;

/**
 * Repository : Modele.
 */
@Repository
public interface ModeleRepository extends JpaRepository<Modele, Integer>, _ModeleRepository {
	/**
	 * Finds Modele by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Modele whose id is equals to the given id. If
	 *         no Modele is found, this method returns null.
	 */
	@Query("select e from Modele e where e.id = :id and e.isDeleted = :isDeleted")
	Modele findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Modele by using code as a search criteria.
	 * 
	 * @param code
	 * @return An Object Modele whose code is equals to the given code. If
	 *         no Modele is found, this method returns null.
	 */
	@Query("select e from Modele e where e.code = :code and e.isDeleted = :isDeleted")
	Modele findByCode(@Param("code")String code, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Modele by using libelle as a search criteria.
	 * 
	 * @param libelle
	 * @return An Object Modele whose libelle is equals to the given libelle. If
	 *         no Modele is found, this method returns null.
	 */
	@Query("select e from Modele e where e.libelle = :libelle and e.isDeleted = :isDeleted")
	Modele findByLibelle(@Param("libelle")String libelle, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Modele by using description as a search criteria.
	 * 
	 * @param description
	 * @return An Object Modele whose description is equals to the given description. If
	 *         no Modele is found, this method returns null.
	 */
	@Query("select e from Modele e where e.description = :description and e.isDeleted = :isDeleted")
	List<Modele> findByDescription(@Param("description")String description, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Modele by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Modele whose createdAt is equals to the given createdAt. If
	 *         no Modele is found, this method returns null.
	 */
	@Query("select e from Modele e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Modele> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Modele by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Modele whose updatedAt is equals to the given updatedAt. If
	 *         no Modele is found, this method returns null.
	 */
	@Query("select e from Modele e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Modele> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Modele by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Modele whose deletedAt is equals to the given deletedAt. If
	 *         no Modele is found, this method returns null.
	 */
	@Query("select e from Modele e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Modele> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Modele by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Modele whose createdBy is equals to the given createdBy. If
	 *         no Modele is found, this method returns null.
	 */
	@Query("select e from Modele e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Modele> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Modele by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Modele whose updatedBy is equals to the given updatedBy. If
	 *         no Modele is found, this method returns null.
	 */
	@Query("select e from Modele e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Modele> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Modele by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Modele whose deletedBy is equals to the given deletedBy. If
	 *         no Modele is found, this method returns null.
	 */
	@Query("select e from Modele e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Modele> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Modele by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Modele whose isDeleted is equals to the given isDeleted. If
	 *         no Modele is found, this method returns null.
	 */
	@Query("select e from Modele e where e.isDeleted = :isDeleted")
	List<Modele> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Modele by using modeleDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Modele
	 * @throws DataAccessException,ParseException
	 */
	default List<Modele> getByCriteria(Request<ModeleDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Modele e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Modele> query = em.createQuery(req, Modele.class);
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
	 * Finds count of Modele by using modeleDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Modele
	 * 
	 */
	default Long count(Request<ModeleDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Modele e where e IS NOT NULL";
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
	default String getWhereExpression(Request<ModeleDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		ModeleDto dto = request.getData() != null ? request.getData() : new ModeleDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (ModeleDto elt : request.getDatas()) {
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
	default String generateCriteria(ModeleDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("code", dto.getCode(), "e.code", "String", dto.getCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("libelle", dto.getLibelle(), "e.libelle", "String", dto.getLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getDescription())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("description", dto.getDescription(), "e.description", "String", dto.getDescriptionParam(), param, index, locale));
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
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
