package org.service.accountservice.repository;

import jakarta.transaction.Transactional;
import org.service.accountservice.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

/**
 * @Author Prakash Ponali (@pponali)
 * @Date 10/12/23
 * @Description
 */
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    Accounts findByAccountNumber(Long accountNumber);
    Optional<Accounts> findByCustomerId(Long customerId);

    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);


}
