package aiims.cf.td_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import aiims.cf.td_api.modal.User;
import aiims.cf.td_api.service.AdminService;

@Configuration
public class AdminUserConfig {
    
	 @Value("${admin.fullname}")
	 private String adminFullname;
	 
    @Value("${admin.username}")
    private String adminUsername;
    

    @Value("${admin.contactNo}")
    private String adminContactNo;

    @Bean
    public ApplicationRunner adminUserInitializer(AdminService adminService, PasswordEncoder passwordEncoder) {
        System.out.println("creating admin");
    	return args -> {
            if (adminService.getUser(adminUsername) == null) {
                User admin = new User();
                admin.setEmployeeId(adminUsername);
                admin.setContactNo(adminContactNo);
                admin.setFullName(adminFullname);
                // Set other properties as needed

                adminService.createAdminUser(admin);
            }
        };
    }
}
