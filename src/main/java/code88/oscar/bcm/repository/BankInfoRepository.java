package code88.oscar.bcm.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import code88.oscar.bcm.model.BankInfoModel;

/**
 * @FileName: BankInfoRepository.java
 * @since: 11/12/2020
 * */
@Repository
public interface BankInfoRepository extends JpaRepository<BankInfoModel, String> {

    public static final String getAllBanksInfo = ""
    	+ "SELECT "
    	+ "	bank_code, "
    	+ "	bank_name,"
    	+ "	image, "
    	+ "	status, "
    	+ "	create_by, "
    	+ "	create_date "
    	+ " FROM bank_info "
    	+ " ORDER BY bank_code ASC";
    
    public static final String getActiveBanks = ""
    	+ " SELECT "
    	+ "	bank_code, "
    	+ "	bank_name,"
    	+ "	image,  "
    	+ "	status,"
    	+ "	create_by,"
    	+ "	create_date "
    	+ " FROM bank_info "
    	+ " WHERE "
    	+ "	status = 'true' ";
    
    public static final String disableBankInfoByCode = ""
    	+ " UPDATE bank_info SET status = 'false' WHERE bank_code = :pBankCode ";
    
    public static final String enabledBankInfoByCode = ""
	    	+ " UPDATE bank_info SET status = 'true' WHERE bank_code = :pBankCode ";
    
    @Query(value = getAllBanksInfo, nativeQuery = true)
    List<BankInfoModel> getListBankInfo();
    
    @Query(value = getActiveBanks, nativeQuery = true)
    List<BankInfoModel> getListBankActive();
    
    @Transactional
    @Modifying
    @Query(value = disableBankInfoByCode, nativeQuery = true)
    void disableBankInfoByCode(@Param("pBankCode") String bankCode);
    
    @Transactional
    @Modifying
    @Query(value = enabledBankInfoByCode, nativeQuery = true)
    void enableBankInfoByCode(@Param("pBankCode") String bankCode);
}
