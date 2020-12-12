package code88.oscar.bcm.services;

import java.util.List;

import code88.oscar.bcm.viewObjects.PaymentConfigurationVO;

/**
 * @FileName: PaymentConfigService.java
 * @since: 12/12/2020
 * */
public interface PaymentConfigService {

    List<PaymentConfigurationVO> getAllConfigs();
    
    void insertConfig ();
    
    void disableConfigById (int id);
    
    void enableConfigById (int id);
}
