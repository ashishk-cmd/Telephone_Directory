package aiims.cf.td_api.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import aiims.cf.td_api.config.AppConstants;
import aiims.cf.td_api.exception.AlreadyExistsException;
import aiims.cf.td_api.exception.ResourceNotFoundException;
import aiims.cf.td_api.modal.Role;
import aiims.cf.td_api.modal.TelephoneDirectory;
import aiims.cf.td_api.modal.User;
import aiims.cf.td_api.payload.request.EmployeeIdRequest;
import aiims.cf.td_api.payload.request.MyRequestBody;
import aiims.cf.td_api.payload.response.EmployeeResponse;
import aiims.cf.td_api.repository.RoleRepo;
import aiims.cf.td_api.repository.TelephoneDirectoryRepo;
import aiims.cf.td_api.repository.UserRepo;
import aiims.cf.td_api.service.AdminService;
import aiims.cf.td_api.utils.ApplicationUtility;
import aiims.cf.td_api.utils.TextMessageServices;
import aiims.cf.td_api.utils.TextMessageTemplate;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RestTemplate restTemplate;

	@Value("${application.textmessage.active}")
	private boolean sendMessage;
	@Autowired
	private TextMessageServices textMessageServices;
	@Autowired
	private TelephoneDirectoryRepo telephoneDirectoryRepo;
	
	@Override
	public User getUser(String employeeId) {
		User user = this.userRepo.findByEmployeeId(employeeId).orElse(null);
		return user;
	}

	@Override
	public void createAdminUser(User userRequest) throws Exception {
		User existingUserEmpId = userRepo.findByEmployeeId(userRequest.getEmployeeId().toUpperCase()).orElse(null);
		User existingUserContactNo = userRepo.findByContactNo(userRequest.getContactNo()).orElse(null);
		Role existingRole = roleRepository.findByName(AppConstants.ADMIN_USER).orElse(null);
		Role saveRole =null;
		if(existingRole == null) {
			Role role = new Role();
			role.setName(AppConstants.ADMIN_USER);
			saveRole = roleRepository.save(role);
		}
		
		if(existingUserEmpId == null && existingUserContactNo == null) {
			String randomKey = ApplicationUtility.generatePassword(6);
			User user = new User();
			user.setFullName(userRequest.getFullName());
			user.setEmployeeId(userRequest.getEmployeeId().toUpperCase());
			user.setContactNo(userRequest.getContactNo());
			user.getRoles().add(saveRole);	
			user.setRegisteredOn(new Date());
			user.setEnabled(true);
			user.setStatus(AppConstants.NEW_USER_STATUS);
			user.setPassword(this.passwordEncoder.encode(randomKey));
			user.setPasswordSentOn(new Date());
			User saveUser = userRepo.save(user);
			if(saveUser != null) {
				if(sendMessage) {
					textMessageServices.sendMessage(
							saveUser.getContactNo(),
							"Your account has been successfully registered." +
    						" Your UserId is:" + saveUser.getEmployeeId() +
    						".Please login using OTP.");
				}else {
					System.out.println("\033[0;32m" + "Admin created!" + "with OTP " + randomKey +"\033[0m");					
				}
			}
		}
		if(existingUserEmpId != null) {
			System.err.println("Employee/Admin already exists with Employee Id " + userRequest.getEmployeeId());
		}
		if(existingUserContactNo != null) {
			System.err.println("Contact/Admin already exists with Contact No " + userRequest.getContactNo());
		}
	}

	@Override
	public ResponseEntity<Object> callExternalAPiFunc(@Valid EmployeeIdRequest employeeIdRequest) {


		 ResponseEntity<Object> ret = null;
	        
	        MyRequestBody requestBody = new MyRequestBody();
	        requestBody.setClient_id(AppConstants.CLIENTID);
	        requestBody.setClient_secret(AppConstants.CLIENTSECRET);
	        requestBody.setClient_serID(AppConstants.CLIENTSERID);

	        // Set headers
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        // Create the HttpEntity
	        HttpEntity<MyRequestBody> request = new HttpEntity<>(requestBody, headers);

	        // Make the POST request
	        ResponseEntity<String> response = restTemplate.exchange(
	        	AppConstants.AUTHENTICATEURL,
	            HttpMethod.POST,
	            request,
	            String.class
	        );
	        
	        // Assuming response.getBody() returns a JSON string
	        String authenticate = response.getBody();
	        
	        // Create a JSONParser instance
	        JSONParser parser = new JSONParser();
	        EmployeeResponse er = new EmployeeResponse();
	        try {
	        	//if authenticate is not null
	        	if(authenticate != null) {
	        		// Parse the JSON string into an Object
		            Object obj = parser.parse(authenticate);
		            // Cast the Object to a JSONObject
		            JSONObject jsonObject = (JSONObject) obj;		            
		            // Extract the access_token
		            String access_token = (String) jsonObject.get("access_token");
		            //if access_token is not null
		            if(access_token != null) {
//		            	EmployeeIdRequest empId = new EmployeeIdRequest();
		            	ResponseEntity<String> res = callApi(access_token,AppConstants.SEARCHMODE,employeeIdRequest.getEmployeeId(),AppConstants.ORGCODE);
		            	System.out.println(res.getBody());
		            	if(res.getBody() != null && res.getStatusCodeValue() == 200) {
							
		    				String empDetails = res.getBody();
		    				
		    	            JSONObject jsonObj = (JSONObject) parser.parse(empDetails);
		    	            		    	            
		    	            JSONArray dataArray = (JSONArray) jsonObj.get("Data");		    	            
		    	            
		    	            // Get the first object in the array
		    	            if (dataArray != null & dataArray.size() > 0) {
		    	                JSONObject firstObject = (JSONObject) dataArray.get(0);
		    	                String name = (String) firstObject.get("name");
		    	                String email = (String) firstObject.get("email_address");
		    	                String designation = (String) firstObject.get("designation");
		    	                String department = (String) firstObject.get("department");
		    	                String employeeGroup = (String) firstObject.get("employee_group");
		    	                String establishment = (String) firstObject.get("establishment");
		    	                String mobileNumber = "";
		    	                try
		    	                {
		    	                	mobileNumber = ((Long) firstObject.get("mobile_number")).toString();
		    	                }
		    	                catch(Exception e)
		    	                {
		    	                	if(e.toString().contains("String cannot be cast to class java.lang.Long"))
		    	                	{
		    	                		mobileNumber = "";
		    	                	} 		
		    	                }
		    	                String employeeId = (String) firstObject.get("employee_id");
		    	                
		    	                
		    	                er.setEmployeeId(employeeId);
		    	                er.setDepartment(department);
		    	                er.setDesignation(designation);
		    	                er.setEmailAddress(email);
		    	                er.setName(name);
		    	                er.setMobileNumber(mobileNumber);
		    	                er.setEstablishment(establishment);
		    	                er.setEmployeeGroup(employeeGroup);
		    	                ret = new ResponseEntity<Object>(er, HttpStatus.OK);
		    	            }
		    	            else
		    	            	ret = new ResponseEntity<Object>("Not Found", HttpStatus.NOT_FOUND);
		            	}
		            	else
		            		ret = new ResponseEntity<Object>("Not Found", HttpStatus.NOT_FOUND);
		            }
		            else
		            	ret = new ResponseEntity<Object>("ERROR", HttpStatus.BAD_REQUEST);
		            
	        	}else 
	        		ret = new ResponseEntity<Object>("ERROR", HttpStatus.BAD_REQUEST);
	        	
			} catch (Exception e) {
	            e.printStackTrace();
	        }
	        
		return ret;
	}

	public ResponseEntity<String> callApi(String accessToken, String searchMode, String searchKey, String orgCode) 
	{
		ResponseEntity<String> ret = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(accessToken);
		headers.set("searchMode", searchMode);
		headers.set("searchKey", searchKey);
		headers.set("orgCode", orgCode);
		headers.set("Content-Type", "application/json");
		   
		HttpEntity<String> entity = new HttpEntity<>(headers);
		System.out.println(accessToken);
		try {
			ResponseEntity<String> response = restTemplate.exchange(AppConstants.EMPLOYEEDATAURL, HttpMethod.POST, entity, String.class);
			if(response.getBody() != null && response.getStatusCodeValue() == 200) {
				ret = response;
			}else {
				ret = new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);;
			}
		} catch (HttpClientErrorException e) {
	        ret = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
	    }
		return ret;
	}

	
	@Override
	public User registerUser(@Valid EmployeeResponse employeeResponse) throws Exception {
		
		User existingUserEmpId = userRepo.findByEmployeeId(employeeResponse.getEmployeeId().toUpperCase()).orElse(null);
		User existingUserContactNo = userRepo.findByContactNo(employeeResponse.getMobileNumber()).orElse(null);
		Role existingRole = roleRepository.findByName(AppConstants.NORMAL_USER).orElse(null);
		if(existingRole == null) {
			Role role = new Role();
			role.setName(AppConstants.NORMAL_USER);
			existingRole = roleRepository.save(role);
		}
		
		User saveUser = null;
		if(existingUserEmpId == null && existingUserContactNo == null) {
			String randomKey = ApplicationUtility.generatePassword(6);
			User user = new User();
			user.setFullName(employeeResponse.getName());
			user.setEmployeeId(employeeResponse.getEmployeeId().toUpperCase());
			user.setContactNo(employeeResponse.getMobileNumber());
			user.setEmail(employeeResponse.getEmailAddress());
			user.setDesignation(employeeResponse.getDesignation());
			user.setDepartment(employeeResponse.getDepartment());
			user.setEmployeeGroup(employeeResponse.getEmployeeGroup());
			user.setEstablishment(employeeResponse.getEstablishment());
			user.getRoles().add(existingRole);	
			user.setRegisteredOn(new Date());
			user.setEnabled(true);
			user.setStatus(AppConstants.NEW_USER_STATUS);
			user.setPassword(this.passwordEncoder.encode(randomKey));
			user.setPasswordSentOn(new Date());
			saveUser = userRepo.save(user);
			if(saveUser != null) {
				if(sendMessage) {
					textMessageServices.sendMessage(
							saveUser.getContactNo(),
							TextMessageTemplate.sendOTP(randomKey,
									"Please verify your details using provided OTP. "
									+ "Please do not share your OTP with anyone. "
									+ "Your account has been successfully registered with EmployeeId " 
									+ saveUser.getEmployeeId()));
				}else {
					System.out.println("\033[0;32m" + "Employee created ! " + "with OTP " + randomKey + "\033[0m");
				}
			}
		}
		if(existingUserEmpId != null) {
			throw new AlreadyExistsException("Employee","Employee Id",employeeResponse.getEmployeeId());
		}
		if(existingUserContactNo != null) {
			throw new AlreadyExistsException("Contact","Contact No",employeeResponse.getMobileNumber());
		}
		return saveUser;
	}

	@Override
	public Map<String, Map<String, List<TelephoneDirectory>>> getTelephoneDirectory() {
		
//		Map<String, Map<String, List<TelephoneDirectory>>> tdlist = null;
		List<TelephoneDirectory> tdl = telephoneDirectoryRepo.findAllByStatus("ACTIVE");
					
		return tdl.stream()
	            .filter(directory -> directory.getSubCategory() != null 
	                    && directory.getSubCategory().getStatus().equals("ACTIVE")
	                    && directory.getSubCategory().getCategory() != null
	                    && directory.getSubCategory().getCategory().getStatus().equals("ACTIVE"))
	            .collect(Collectors.groupingBy(
	                directory -> directory.getSubCategory().getCategory().getName(),
	                Collectors.groupingBy(directory -> directory.getSubCategory().getName())
	            ));
	}

	@Override
	public TelephoneDirectory addTelephoneRecord(TelephoneDirectory telephoneDirectory) {
		return this.telephoneDirectoryRepo.save(telephoneDirectory);
	}

	@Override
	public TelephoneDirectory updateTelephoneRecord(TelephoneDirectory telephoneDirectory) {
		TelephoneDirectory td = this.telephoneDirectoryRepo.findById(telephoneDirectory.getId()).orElseThrow(()-> new ResourceNotFoundException("TelephoneDirectory","TelephoneDirectory ID","You are trying to update."));
		if (td != null) {
	        BeanUtils.copyProperties(telephoneDirectory, td, "id"); // This will copy all properties except 'id'
	    }
		return this.telephoneDirectoryRepo.save(telephoneDirectory);
	}

	@Override
	public TelephoneDirectory deleteTelephoneRecord(Long telephoneDirectoryId) {
		TelephoneDirectory td = this.telephoneDirectoryRepo.findById(telephoneDirectoryId).orElseThrow(()-> new ResourceNotFoundException("TelephoneDirectory","TelephoneDirectory ID",telephoneDirectoryId));
		if(td != null) {
			td.setStatus("INACTIVE");			
		}
		return this.telephoneDirectoryRepo.save(td);
	}
	
	
	
}
