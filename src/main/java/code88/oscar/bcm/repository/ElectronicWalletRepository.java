package code88.oscar.bcm.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import code88.oscar.bcm.model.ElectronicWalletModel;

/**
 * @FileName: ElectronicWalletRepository.java
 * @since: 11/12/2020
 */
@Repository
public interface ElectronicWalletRepository extends JpaRepository<ElectronicWalletModel, Integer> {

    public static final String getAllElectronicWallet = "" 
    + "SELECT " 
	    + "	id, "
	    + "	wallet_name, "
	    + "	status,"
	    + " image,  " 
	    + "	create_date, " 
	    + "	create_by " 
	    + " FROM electronic_wallet "
	    + " ORDER BY id ASC ";

    public static final String getActiveWallet = "" 
    + " SELECT " 
	    + "	id, " 
	    + "	wallet_name, "
	    + " status, "
	    + " image, "
	    + " create_date,"
	    + " create_by "
	    + " FROM electronic_wallet " 
	    + " WHERE " 
	    + "	status = 'true' ";

    public static final String disableWalletById = ""
	    + " UPDATE electronic_wallet SET status = 'false' WHERE id = :pId ";
    
    public static final String enabledWalletById = ""
	    + " UPDATE electronic_wallet SET status = 'true' WHERE id = :pId ";

    @Query(value = getAllElectronicWallet, nativeQuery = true)
    List<ElectronicWalletModel> getListWallet();

    @Query(value = getActiveWallet, nativeQuery = true)
    List<ElectronicWalletModel> getListWalletActive();

    @Transactional
    @Modifying
    @Query(value = disableWalletById, nativeQuery = true)
    void disableWalletById(@Param("pId") int id);
    
    @Transactional
    @Modifying
    @Query(value = enabledWalletById, nativeQuery = true)
    void enableWalletById(@Param("pId") int id);
}
