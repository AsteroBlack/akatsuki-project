package ci.smile.system.manager.obf.dao.repository.customize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ci.smile.system.manager.obf.dao.entity.User;
import ci.smile.system.manager.obf.utils.dto.UserDto;

/**
 * Repository customize : User.
 */
@Repository
public interface _UserRepository {
	default List<String> _generateCriteria(UserDto dto, HashMap<String, java.lang.Object> param, Integer index,
			Locale locale) throws Exception {
		List<String> listOfQuery = new ArrayList<String>();

		// PUT YOUR RIGHT CUSTOM CRITERIA HERE

		return listOfQuery;
	}

	@Query("select e from User e where e.password = :password and e.login = :login and e.isDeleted = :isDeleted")
	User findByPasswordAndLogin(@Param("password") String password, @Param("login") String login,
			@Param("isDeleted") Boolean isDeleted);

	@Query("select e from User e where e.isDeleted = :isDeleted")
	List<User> getByIsDeleted(@Param("isDeleted") Boolean isDeleted, Pageable pageable);
}
