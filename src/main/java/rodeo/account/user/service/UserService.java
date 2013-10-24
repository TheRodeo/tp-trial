package rodeo.account.user.service;

import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import rodeo.account.dao.pojo.Account;
import rodeo.account.dao.service.AccountRepository;

public class UserService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;

	private Authentication authenticate(final Account account) {
		return new UsernamePasswordAuthenticationToken(createUser(account), null, Collections.singleton(createAuthority(account)));
	}

	private GrantedAuthority createAuthority(final Account account) {
		return new SimpleGrantedAuthority(account.getRole());
	}

	private User createUser(final Account account) {
		return new User(account.getEmail(), account.getPassword(), Collections.singleton(createAuthority(account)));
	}

	@PostConstruct
	protected void initialize() {
		Account user = accountRepository.findByEmail("admin");
		if (user == null) {
			accountRepository.save(new Account("admin", "admin", "ROLE_ADMIN"));
		}
		user = accountRepository.findByEmail("sourabhse");
		if (user == null) {
			accountRepository.save(new Account("sourabhse", "guild@123", "ROLE_ADMIN"));
		}
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final Account account = accountRepository.findByEmail(username);
		if (account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return createUser(account);
	}

	public void signin(final Account account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}

}
