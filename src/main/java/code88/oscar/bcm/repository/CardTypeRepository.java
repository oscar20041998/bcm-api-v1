package code88.oscar.bcm.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import code88.oscar.bcm.model.CardTypeModel;

/**
 * @FileName: CardTypeRepository.java
 * @since: 11/12/2020
 * */
@Repository
public interface CardTypeRepository extends JpaRepository<CardTypeModel, Integer>{

    public static String getAllCard = ""
    	+ "SELECT "
    	+ "	id,"
    	+ "	card_type, "
    	+ "	card_name, "
    	+ "	status, "
    	+ "	create_date, "
    	+ "	create_by "
    	+ " FROM card_type"
    	+ " ORDER BY card_name ASC ";
    
    public static String getActiveCard = ""
	    + "SELECT "
	    	+ "	id,"
	    	+ "	card_type, "
	    	+ "	card_name, "
	    	+ "	status, "
	    	+ "	create_date, "
	    	+ "	create_by "
	    	+ " FROM card_type "
	    	+ " WHERE status = 'true' "
	    	+ " ORDER BY card_name ASC ";
    
    public static final String disabeCardById = ""
    	+ " UPDATE card_type SET status = 'false' WHERE id = :pId ";
    
    public static final String enableCardById = ""
	    	+ " UPDATE card_type SET status = 'true' WHERE id = :pId ";
    
    @Query(value = getAllCard, nativeQuery = true)
    List<CardTypeModel> getAllCards();
    
    @Query(value = getActiveCard, nativeQuery = true)
    List<CardTypeModel> getActiveCards();
    
    @Transactional
    @Modifying
    @Query(value = disabeCardById, nativeQuery = true)
    void disableCardById(@Param("pId") int id);

    @Transactional
    @Modifying
    @Query(value = enableCardById, nativeQuery = true)
    void enableCardById(@Param("pId") int id);
}
