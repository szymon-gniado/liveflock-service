package raveneye.liveflock.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import raveneye.liveflock.repository.AccountRepository;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {
  private final AccountRepository accountRepository;

  public AccountController(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @GetMapping("/{username}")
  public ResponseEntity<raveneye.liveflock.entity.Account> findById(@PathVariable String username) {
    Optional<raveneye.liveflock.entity.Account> accountOptional = accountRepository.findAccountViewBy(username);
    return accountOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Void> createAccount(@RequestBody raveneye.liveflock.entity.Account newAccount, UriComponentsBuilder ucb) {
    raveneye.liveflock.entity.Account savedAccount = accountRepository.save(newAccount);
    URI locationOfNewAccount = ucb
            .path("/accounts/{username}")
            .buildAndExpand(savedAccount.username())
            .toUri();
    return ResponseEntity.created(locationOfNewAccount).build();
  }
}
