package code88.oscar.bcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import code88.oscar.bcm.model.AccountUserLogModel;

/**
 * @FileName: AccountUserLogRepository.java
 * @since: 22/10/2020
 * */
@Repository
public interface AccountUserLogRepository extends JpaRepository<AccountUserLogModel, Integer> {
}
