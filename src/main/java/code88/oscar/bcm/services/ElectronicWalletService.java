package code88.oscar.bcm.services;

import java.util.List;

import code88.oscar.bcm.viewObjects.ElectronicWalletVO;

/**
 * @FileName: ElectronicWallet.java
 * @since: 11/12/2020
 * */
public interface ElectronicWalletService {

    List<ElectronicWalletVO> getListWallet();
    
    List<ElectronicWalletVO> getListWalletActive();
    
    void disableElectronicWalletById (int id);

}
