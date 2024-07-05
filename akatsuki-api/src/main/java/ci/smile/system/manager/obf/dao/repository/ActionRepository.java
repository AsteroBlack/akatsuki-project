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

import ci.smile.system.manager.obf.dao.entity.Action;
import ci.smile.system.manager.obf.dao.repository.customize._ActionRepository;
import ci.smile.system.manager.obf.utils.CriteriaUtils;
import ci.smile.system.manager.obf.utils.Utilities;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.dto.ActionDto;

/**
 * Repository : Action.
 */
@Repository
public interface ActionRepository extends JpaRepository<Action, Integer>, _ActionRepository {
	/**
	 * Finds Action by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Action whose id is equals to the given id. If
	 *         no Action is found, this method returns null.
	 */
	@Query("select e from Action e where e.id = :id and e.isDeleted = :isDeleted")
	Action findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Action by using libelle as a search criteria.
	 * 
	 * @param libelle
	 * @return An Object Action whose libelle is equals to the given libelle. If
	 *         no Action is found, this method returns null.
	 */
	@Query("select e from Action e where e.libelle = :libelle and e.isDeleted = :isDeleted")
	Action findByLibelle(@Param("libelle")String libelle, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Action by using code as a search criteria.
	 * 
	 * @param code
	 * @return An Object Action whose code is equals to the given code. If
	 *         no Action is found, this method returns null.
	 */
	@Query("select e from Action e where e.code = :code and e.isDeleted = :isDeleted")
	Action findByCode(@Param("code")String code, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Action by using isLocked as a search criteria.
	 * 
	 * @param isLocked
	 * @return An Object Action whose isLocked is equals to the given isLocked. If
	 *         no Action is found, this method returns null.
	 */
	@Query("select e from Action e where e.isLocked = :isLocked and e.isDeleted = :isDeleted")
	List<Action> findByIsLocked(@Param("isLocked")Boolean isLocked, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Action by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Action whose createdAt is equals to the given createdAt. If
	 *         no Action is found, this method returns null.
	 */
	@Query("select e from Action e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Action> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Action by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Action whose updatedAt is equals to the given updatedAt. If
	 *         no Action is found, this method returns null.
	 */
	@Query("select e from Action e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Action> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Action by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Action whose deletedAt is equals to the given deletedAt. If
	 *         no Action is found, this method returns null.
	 */
	@Query("select e from Action e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Action> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Action by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Action whose createdBy is equals to the given createdBy. If
	 *         no Action is found, this method returns null.
	 */
	@Query("select e from Action e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Action> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Action by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Action whose updatedBy is equals to the given updatedBy. If
	 *         no Action is found, this method returns null.
	 */
	@Query("select e from Action e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Action> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Action by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Action whose deletedBy is equals to the given deletedBy. If
	 *         no Action is found, this method returns null.
	 */
	@Query("select e from Action e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Action> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Action by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Action whose isDeleted is equals to the given isDeleted. If
	 *         no Action is found, this method returns null.
	 */
	@Query("select e from Action e where e.isDeleted = :isDeleted")
	List<Action> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Action by using parentId as a search criteria.
	 * 
	 * @param parentId
	 * @return An Object Action whose parentId is equals to the given parentId. If
	 *         no Action is found, this method returns null.
	 */
	@Query("select e from Action e where e.parent.id = :parentId and e.isDeleted = :isDeleted")
	List<Action> findByParentId(@Param("parentId")Integer parentId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Action by using menusId as a search criteria.
	 * 
	 * @param menusId
	 * @return An Object Action whose menusId is equals to the given menusId. If
	 *         no Action is found, this method returns null.
	 */
	@Query("select e from Action e where e.menus.id = :menusId and e.isDeleted = :isDeleted")
	List<Action> findByMenusId(@Param("menusId")Integer menusId, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of Action by using actionDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Action
	 * @throws DataAccessException,ParseException
	 */
	default List<Action> getByCriteria(Request<ActionDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Action e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<Action> query = em.createQuery(req, Action.class);
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
	 * Finds count of Action by using actionDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Action
	 * 
	 */
	default Long count(Request<ActionDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Action e where e IS NOT NULL";
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
	default String getWhereExpression(Request<ActionDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		ActionDto dto = request.getData() != null ? request.getData() : new ActionDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (ActionDto elt : request.getDatas()) {
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
	default String generateCriteria(ActionDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("libelle", dto.getLibelle(), "e.libelle", "String", dto.getLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("code", dto.getCode(), "e.code", "String", dto.getCodeParam(), param, index, locale));
			}
			if (dto.getIsLocked()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isLocked", dto.getIsLocked(), "e.isLocked", "Boolean", dto.getIsLockedParam(), param, index, locale));
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
			if (dto.getParentActionId()!= null && dto.getParentActionId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("parentActionId", dto.getParentActionId(), "e.parent.id", "Integer", dto.getParentActionIdParam(), param, index, locale));
			}
			if (dto.getMenusId()!= null && dto.getMenusId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("menusId", dto.getMenusId(), "e.menus.id", "Integer", dto.getMenusIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getParentActionLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("parentActionlibelle", dto.getParentActionLibelle(), "e.parent.libelle", "String", dto.getParentActionLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getParentActionCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("parentActionCode", dto.getParentActionCode(), "e.parent.code", "String", dto.getParentActionCodeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getMenusLibelle())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("menusLibelle", dto.getMenusLibelle(), "e.menus.libelle", "String", dto.getMenusLibelleParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getMenusCode())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("menusCode", dto.getMenusCode(), "e.menus.code", "String", dto.getMenusCodeParam(), param, index, locale));
			}
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
