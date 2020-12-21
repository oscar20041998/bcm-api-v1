package code88.oscar.bcm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import code88.oscar.bcm.model.UserModel;

/**
 * @FileName: UserRepository.java
 * @since: 10/10/2020
 */
@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {

    public static final String sql_getUserById = "" + " SELECT * FROM user WHERE user_id = :pUserId";

    @Query(value = sql_getUserById, nativeQuery = true)
    UserModel getUserById(@Param("pUserId") String userId);

    public static final String sql_searchUSerByCriteria = 
	    "" + 
    "SELECT * " + 
    "FROM user " + 
    "WHERE "
    + "	user_id LIKE %:pCriteria% OR " + 
    "	first_name LIKE %:pCriteria% OR "
    + "	midle_name LIKE %:pCriteria% OR "
    + " last_name LIKE %:pCriteria% OR "
    + " concat(first_name,' ',midle_name,' ',last_name) LIKE %:pCriteria% OR "
    + " email LIKE %:pCriteria% OR "
    + " phone_number LIKE %:pCriteria% OR"
    + " address LIKE %:pCriteria% OR"
    + " id_card LIKE %:pCriteria% ";

    public static final String sql_getEmailUserById= "SELECT email FROM user WHERE user_id = :pUserId";
    
    @Query(value = sql_searchUSerByCriteria, nativeQuery = true)
    List<UserModel> searchListUserByCriteria(@Param("pCriteria") String criteria);
    
    @Query(value = sql_getEmailUserById, nativeQuery = true)
    String getEmailUserById (@Param("pUserId") String userId);
}
