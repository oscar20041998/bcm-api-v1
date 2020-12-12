package code88.oscar.bcm.services;

import java.util.List;

import code88.oscar.bcm.viewObjects.BankInfoVO;

/**
 * @FileName: BankInfoService.java
 * @since: 11/12/2020
 */
public interface BankInfoService {

    List<BankInfoVO> getAllBanksInfo();

    List<BankInfoVO> getBankInfoActive();

    void disableBankInfoByCode(String bankCode);
    
    void enabledBankInfoByCode (String banCode);
}
