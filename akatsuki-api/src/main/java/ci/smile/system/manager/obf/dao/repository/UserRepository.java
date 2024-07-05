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

import ci.smile.system.manager.obf.dao.entity.User;
import ci.smile.system.manager.obf.dao.repository.customize._UserRepository;
import ci.smile.system.manager.obf.utils.CriteriaUtils;
import ci.smile.system.manager.obf.utils.Utilities;
import ci.smile.system.manager.obf.utils.contract.Request;
import ci.smile.system.manager.obf.utils.dto.UserDto;

/**
 * Repository : User.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, _UserRepository {
	/**
	 * Finds User by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object User whose id is equals to the given id. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.id = :id and e.isDeleted = :isDeleted")
	User findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds User by using matricule as a search criteria.
	 * 
	 * @param matricule
	 * @return An Object User whose matricule is equals to the given matricule. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.matricule = :matricule and e.isDeleted = :isDeleted")
	User findByMatricule(@Param("matricule")String matricule, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using nom as a search criteria.
	 * 
	 * @param nom
	 * @return An Object User whose nom is equals to the given nom. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.nom = :nom and e.isDeleted = :isDeleted")
	List<User> findByNom(@Param("nom")String nom, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using prenom as a search criteria.
	 * 
	 * @param prenom
	 * @return An Object User whose prenom is equals to the given prenom. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.prenom = :prenom and e.isDeleted = :isDeleted")
	List<User> findByPrenom(@Param("prenom")String prenom, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using email as a search criteria.
	 * 
	 * @param email
	 * @return An Object User whose email is equals to the given email. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.email = :email and e.isDeleted = :isDeleted")
	User findByEmail(@Param("email")String email, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using login as a search criteria.
	 * 
	 * @param login
	 * @return An Object User whose login is equals to the given login. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.login = :login and e.isDeleted = :isDeleted")
	User findByLogin(@Param("login")String login, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using password as a search criteria.
	 * 
	 * @param password
	 * @return An Object User whose password is equals to the given password. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.password = :password and e.isDeleted = :isDeleted")
	List<User> findByPassword(@Param("password")String password, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using contact as a search criteria.
	 * 
	 * @param contact
	 * @return An Object User whose contact is equals to the given contact. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.contact = :contact and e.isDeleted = :isDeleted")
	List<User> findByContact(@Param("contact")String contact, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using isSuperAdmin as a search criteria.
	 * 
	 * @param isSuperAdmin
	 * @return An Object User whose isSuperAdmin is equals to the given isSuperAdmin. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.isSuperAdmin = :isSuperAdmin and e.isDeleted = :isDeleted")
	List<User> findByIsSuperAdmin(@Param("isSuperAdmin")Boolean isSuperAdmin, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using isAutorize as a search criteria.
	 * 
	 * @param isAutorize
	 * @return An Object User whose isAutorize is equals to the given isAutorize. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.isAutorize = :isAutorize and e.isDeleted = :isDeleted")
	List<User> findByIsAutorize(@Param("isAutorize")Boolean isAutorize, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using isDefaultPassword as a search criteria.
	 * 
	 * @param isDefaultPassword
	 * @return An Object User whose isDefaultPassword is equals to the given isDefaultPassword. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.isDefaultPassword = :isDefaultPassword and e.isDeleted = :isDeleted")
	List<User> findByIsDefaultPassword(@Param("isDefaultPassword")Boolean isDefaultPassword, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using isLocked as a search criteria.
	 * 
	 * @param isLocked
	 * @return An Object User whose isLocked is equals to the given isLocked. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.isLocked = :isLocked and e.isDeleted = :isDeleted")
	List<User> findByIsLocked(@Param("isLocked")Boolean isLocked, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object User whose createdAt is equals to the given createdAt. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<User> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object User whose updatedAt is equals to the given updatedAt. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<User> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object User whose deletedAt is equals to the given deletedAt. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<User> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object User whose createdBy is equals to the given createdBy. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<User> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object User whose updatedBy is equals to the given updatedBy. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<User> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object User whose deletedBy is equals to the given deletedBy. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<User> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds User by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object User whose isDeleted is equals to the given isDeleted. If
	 *         no User is found, this method returns null.
	 */
	@Query("select e from User e where e.isDeleted = :isDeleted")
	List<User> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds List of User by using userDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of User
	 * @throws DataAccessException,ParseException
	 */
	default List<User> getByCriteria(Request<UserDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from User e where e IS NOT NULL";
		HashMap<String, java.lang.Object> param = new HashMap<String, java.lang.Object>();
		req += getWhereExpression(request, param, locale);
		req += " order by e.id desc";
		TypedQuery<User> query = em.createQuery(req, User.class);
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
	 * Finds count of User by using userDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of User
	 * 
	 */
	default Long count(Request<UserDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from User e where e IS NOT NULL";
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
	default String getWhereExpression(Request<UserDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		UserDto dto = request.getData() != null ? request.getData() : new UserDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (UserDto elt : request.getDatas()) {
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
	default String generateCriteria(UserDto dto, HashMap<String, java.lang.Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId()!= null && dto.getId() > 0) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getMatricule())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("matricule", dto.getMatricule(), "e.matricule", "String", dto.getMatriculeParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getNom())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("nom", dto.getNom(), "e.nom", "String", dto.getNomParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPrenom())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("prenom", dto.getPrenom(), "e.prenom", "String", dto.getPrenomParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getEmail())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("email", dto.getEmail(), "e.email", "String", dto.getEmailParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getLogin())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("login", dto.getLogin(), "e.login", "String", dto.getLoginParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getPassword())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("password", dto.getPassword(), "e.password", "String", dto.getPasswordParam(), param, index, locale));
			}
			if (Utilities.notBlank(dto.getContact())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("contact", dto.getContact(), "e.contact", "String", dto.getContactParam(), param, index, locale));
			}
			if (dto.getIsSuperAdmin()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isSuperAdmin", dto.getIsSuperAdmin(), "e.isSuperAdmin", "Boolean", dto.getIsSuperAdminParam(), param, index, locale));
			}
			if (dto.getIsAutorize()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isAutorize", dto.getIsAutorize(), "e.isAutorize", "Boolean", dto.getIsAutorizeParam(), param, index, locale));
			}
			if (dto.getIsDefaultPassword()!= null) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDefaultPassword", dto.getIsDefaultPassword(), "e.isDefaultPassword", "Boolean", dto.getIsDefaultPasswordParam(), param, index, locale));
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
			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
