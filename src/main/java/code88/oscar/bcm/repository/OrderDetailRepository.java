package code88.oscar.bcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import code88.oscar.bcm.model.OrderDetailModel;

/**
 * @FileName: OrderDetailRepository.java
 * @since: 14/12/2020
 * */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailModel, Integer> {

}
