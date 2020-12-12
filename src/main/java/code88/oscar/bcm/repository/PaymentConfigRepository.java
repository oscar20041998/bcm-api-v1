package code88.oscar.bcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import code88.oscar.bcm.model.PaymentConfigurationModel;

/**
 * @FileName: PaymentConfigRepository.java
 * @since: 12/12/2020
 * */

@Repository
public interface PaymentConfigRepository extends JpaRepository<PaymentConfigurationModel, Integer>{

}
