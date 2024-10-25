package hu.progmasters.repository;

import hu.progmasters.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select a from Account a WHERE a.user =: userId")
    Optional<Account> getAccountByUserId(@Param("userId") Long userId);
}

