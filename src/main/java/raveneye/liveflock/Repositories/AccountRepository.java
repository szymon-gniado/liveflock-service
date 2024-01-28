package raveneye.liveflock.Repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<raveneye.liveflock.Entities.Account, Long>  {
    @Query("select * from account_view where username = :username")
    Optional<raveneye.liveflock.Entities.Account> findAccountViewBy(String username);
}