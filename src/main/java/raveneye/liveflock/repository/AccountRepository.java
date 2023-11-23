package raveneye.liveflock.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<raveneye.liveflock.entity.Account, Long>  {
    @Query("select * from account_view where username = :username")
    Optional<raveneye.liveflock.entity.Account> findAccountViewBy(String username);
}