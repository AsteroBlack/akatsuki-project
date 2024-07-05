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

import ci.smile.system.manager.obf.dao.entity.RoleAction;
import ci.smile.system.manager.obf.dao.repository.customize._RoleActionRepository;
import ci.smile.system.manager.obf.utils.CriteriaUtils;
import ci.smile.system.manager.obf.utils.Utilities;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.dto.RoleActionDto;

/**
 * Repository : RoleAction.
 */
@Repository
public interface RoleActionRepository extends JpaRepository<RoleAction, Integer>, _RoleActionRepository {
	/**
	 * Finds RoleAction by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object RoleAction whose id is equals to the given id. If
	 *         no RoleAction is found, this method returns null.
	 */
	@Query("select e from RoleAction e where e.id = :id and e.isDeleted = :isDeleted")
	RoleAction findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds RoleAction by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object RoleAction whose createdAt is equals to the given createdAt. If
	 *         no RoleAction is found, this method returns null.
	 */
	@Query("select e from RoleAction e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<RoleAction> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleAction by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object RoleAction whose updatedAt is equals to the given updatedAt. If
	 *         no RoleAction is found, this method returns null.
	 */
	@Query("select e from RoleAction e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<RoleAction> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleAction by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object RoleAction whose deletedAt is equals to the given deletedAt. If
	 *         no RoleAction is found, this method returns null.
	 */
	@Query("select e from RoleAction e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<RoleAction> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleAction by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object RoleAction whose createdBy is equals to the given createdBy. If
	 *         no RoleAction is found, this method returns null.
	 */
	@Query("select e from RoleAction e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<RoleAction> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleAction by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object RoleAction whose updatedBy is equals to the given updatedBy. If
	 *         no RoleAction is found, this method returns null.
	 */
	@Query("select e from RoleAction e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<RoleAction> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleAction by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object RoleAction whose deletedBy is equals to the given deletedBy. If
	 *         no RoleAction is found, this method returns null.
	 */
	@Query("select e from RoleAction e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<RoleAction> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleAction by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object RoleAction whose isDeleted is equals to the given isDeleted. If
	 *         no RoleAction is found, this method returns null.
	 */
	@Query("select e from RoleAction e where e.isDeleted = :isDeleted")
	List<RoleAction> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds RoleAction by using roleId as a search criteria.
	 * 
	 * @param roleId
	 * @return An Object RoleAction whose roleId is equals to the given roleId. If
	 *         no RoleAction is found, this method returns null.
	 */
	@Query("select e from RoleAction e where e.role.id = :roleId and e.isDeleted = :isDeleted")
	List<RoleAction> findByRoleId(@Param("roleId")Integer roleId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds RoleAction by using actionId as a search criteria.
	 * 
	 * @param actionId
	 * @return An Object RoleAction whose actionId is equals to the given actionId. If
	 *         no RoleAction is found, this method returns null.
	 */
	@Query("select e from RoleAction e where e.action.id = :actionId and e.isDeleted = :isDeleted")
	List<RoleAction> findByActionId(@Param("actionId")Integer actionId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of RoleAction by using roleActionDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of RoleAction
	 * @throws DataAccessException,ParseException
	 */
	default List<RoleAction> getByCriteria(Request<RoleActionDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from RoleAction e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<RoleAction> query = em.createQuery(req, RoleAction.class);
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
	 * Finds count of RoleAction by using roleActionDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of RoleAction
	 * 
	 */
	default Long count(Request<RoleActionDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from RoleAction e where e IS NOT NULL";
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
	default String getWhereExpression(Request<RoleActionDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		RoleActionDto dto = request.getData() != null ? request.getData() : new RoleActionDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (RoleActionDto elt : request.getDatas()) {
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
	default String generateCriteria(RoleActionDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
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
			if (dto.getRoleId()!= null && dto.getRoleId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("roleId", dto.getRoleId(), "e.role.id", "Integer", dto.getRoleIdParam(), param, index, locale));
			}
			if (dto.getActionId()!= null && dto.getActionId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("actionId", dto.getActionId(), "e.action.id", "Integer", dto.getActionIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getRoleLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("roleLibelle", dto.getRoleLibelle(), "e.role.libelle", "String", dto.getRoleLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getRoleCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("roleCode", dto.getRoleCode(), "e.role.code", "String", dto.getRoleCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getActionLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("actionLibelle", dto.getActionLibelle(), "e.action.libelle", "String", dto.getActionLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getActionCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("actionCode", dto.getActionCode(), "e.action.code", "String", dto.getActionCodeParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
