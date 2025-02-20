package RegisterLoginSecurity.SecurityConfig;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import RegisterLoginSecurity.Entity.Users;
import RegisterLoginSecurity.Exception.UserEmailNotFoundException;
import RegisterLoginSecurity.Repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService 
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException 
	{
		// User = pre-define security class
		Users user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserEmailNotFoundException("userEmailNotFound with name: " + email));

		List<SimpleGrantedAuthority> authority = user.getRoles().stream()
				.map(roles -> new SimpleGrantedAuthority(roles.getRolesName())).collect(Collectors.toList());

		return new User(user.getUserName(), user.getPassword(), authority);

	}

}
