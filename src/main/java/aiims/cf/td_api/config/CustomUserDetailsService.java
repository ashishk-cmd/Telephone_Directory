package aiims.cf.td_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import aiims.cf.td_api.exception.ResourceNotFoundException;
import aiims.cf.td_api.modal.User;
import aiims.cf.td_api.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("CUDS : " + username);
		User user = this.userRepository.findByEmployeeId(username.toUpperCase()).orElseThrow(()-> new ResourceNotFoundException("User", "Employee Id : ", username.toUpperCase()));
		if(user.getStatus().equalsIgnoreCase("NEW")) {
			user.setStatus(AppConstants.ACTIVE_USER_STATUS);
			this.userRepository.save(user);
		}
		if(user.getEnabled().equals(true)) {
			return user;			
		}else {
			throw new UsernameNotFoundException("Invalid username or password. Contact to your Admin!!");
		}
	}

}
