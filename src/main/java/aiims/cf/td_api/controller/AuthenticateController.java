package aiims.cf.td_api.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aiims.cf.td_api.config.CustomUserDetailsService;
import aiims.cf.td_api.config.JwtUtil;
import aiims.cf.td_api.exception.ResourceNotFoundException;
import aiims.cf.td_api.modal.User;
import aiims.cf.td_api.payload.request.JwtOtpRequest;
import aiims.cf.td_api.payload.request.JwtRequest;
import aiims.cf.td_api.payload.response.JwtOtpResponse;
import aiims.cf.td_api.payload.response.JwtResponse;
import aiims.cf.td_api.repository.UserRepo;
import aiims.cf.td_api.utils.ApplicationUtility;
import aiims.cf.td_api.utils.TextMessageServices;
import aiims.cf.td_api.utils.TextMessageTemplate;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
    private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private UserDetailsService  userdetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;	
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserRepo userRepo;
	@Value("${application.textmessage.active}")
	private boolean sendMessage ;
	@Autowired
	private TextMessageServices textMessageServices;
	
	//generate token
	@PostMapping("/generate-token-otp")
	public ResponseEntity<?> generateTokenOtp(@RequestBody JwtRequest jwtRequest) throws Exception{
		System.out.println("generate-token-otp");
		String randomKey = ApplicationUtility.generatePassword(6);
		JwtOtpResponse jor = null;
		
		try {			
			User user = this.userRepo.findByEmployeeId(jwtRequest.getEmployeeId().toUpperCase()).orElseThrow(()-> new ResourceNotFoundException("Employee","Employee Id",jwtRequest.getEmployeeId().toUpperCase()));
			user.setPassword(this.passwordEncoder.encode(randomKey));
			user.setPasswordSentOn(new Date());
			User saveUser = userRepo.save(user);
			if(saveUser != null) {
				if(sendMessage) {
					textMessageServices.sendMessage(user.getContactNo(),TextMessageTemplate.sendOTP(randomKey,"to login, valid for 5 minutes only. Please do not share your OTP with anyone."));
				}else {
					System.out.println("OTP Sent!");
					jor = new JwtOtpResponse(randomKey);
				}
			}
		} catch (UsernameNotFoundException e) {
			throw new Exception("Invalid EmployeeId" + e.getMessage());
		}
		
		return ResponseEntity.ok(jor);
	}
		
	
	//generate token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtOtpRequest jwtOtpRequest) throws Exception{
		System.out.println("generate-token-otp");
		try {
			User user = this.userRepo.findByEmployeeId(jwtOtpRequest.getEmployeeId().toUpperCase()).orElseThrow(()-> new ResourceNotFoundException("Employee","Employee Id",jwtOtpRequest.getEmployeeId().toUpperCase()));
			if(user != null) {					
				// existing date-time (java.util.Date)
		        Date existingDate = user.getPasswordSentOn(); 
		        // Convert java.util.Date to java.time.LocalDateTime
		        LocalDateTime existingDateTime = convertToLocalDateTimeViaInstant(existingDate);
		        // Get the current date-time
		        LocalDateTime currentDateTime = LocalDateTime.now();
		        // Calculate the difference between the two date-times
		        Duration duration = Duration.between(existingDateTime, currentDateTime);
		        // Check if the difference is less than 5 minutes (300 seconds)
		        if (duration.getSeconds() < 300) {
		            System.out.println("The difference is less than 5 minutes.");
		            this.authenticate(jwtOtpRequest.getEmployeeId().toUpperCase(),jwtOtpRequest.getOtp());
		        } 
			}
		} catch (UsernameNotFoundException e) {
			throw new Exception("Invalid username or password !!" + e.getMessage());
		}
		
		UserDetails userDetails = this.userdetailsService.loadUserByUsername(jwtOtpRequest.getEmployeeId().toUpperCase());
		String token = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	

	
	private void authenticate(String u,String o) throws Exception {	
		UsernamePasswordAuthenticationToken authenticationToken = new  UsernamePasswordAuthenticationToken(u,o);
		try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (DisabledException e) {
			throw new Exception("User Disabled " + e.getMessage());
		}catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentials " + e.getMessage());
	    }
	}
	
	
	// Helper method to convert java.util.Date to java.time.LocalDateTime
    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
	
}

