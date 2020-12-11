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
	    + "	status, " 
	    + "	create_date, " 
	    + "	create_by " 
	    + " FROM electronic_wallet "
	    + " ORDER BY id ASC ";

    public static final String getActiveWallet = "" 
    + " SELECT " 
	    + "	id, " 
	    + "	wallet_name"
	    + " status, "
	    + " FROM electronic_wallet " 
	    + " WHERE " 
	    + "	status = 'ACTIVE' ";

    public static final String disableBankInfoByCode = ""
	    + " UPDATE electronic_wallet SET status = 'INACTIVE' WHERE id = :pId ";

    @Query(value = getAllElectronicWallet, nativeQuery = true)
    List<ElectronicWalletModel> getListWallet();

    @Query(value = getActiveWallet, nativeQuery = true)
    List<ElectronicWalletModel> getListWalletActive();

    @Transactional
    @Modifying
    @Query(value = disableBankInfoByCode, nativeQuery = true)
    void disableWalletById(@Param("pId") int id);
}
