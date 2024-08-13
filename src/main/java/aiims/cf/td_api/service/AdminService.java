package aiims.cf.td_api.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import aiims.cf.td_api.modal.Category;
import aiims.cf.td_api.modal.SubCategory;
import aiims.cf.td_api.modal.TelephoneDirectory;
import aiims.cf.td_api.modal.User;
import aiims.cf.td_api.payload.request.EmployeeIdRequest;
import aiims.cf.td_api.payload.response.EmployeeResponse;
import jakarta.validation.Valid;


public interface AdminService {

	public User getUser(String employeeId);

	public void createAdminUser(User admin) throws Exception;

	public ResponseEntity<Object> callExternalAPiFunc(@Valid EmployeeIdRequest employeeIdRequest);

	public User registerUser(@Valid EmployeeResponse employeeResponse) throws Exception;

	// FOR TelephoneDirectory --------------------------------------------------------------------------------
	
	public Map<String, Map<String, List<TelephoneDirectory>>> getTelephoneDirectory();
	
	public TelephoneDirectory addTelephoneRecord(TelephoneDirectory telephoneDirectory);
	
	public TelephoneDirectory updateTelephoneRecord(TelephoneDirectory telephoneDirectory);
	
	public TelephoneDirectory deleteTelephoneRecord(Long telephoneDirectoryId);
	
	// FOR SubCategory ----------------------------------------------------------------------------------------
	
	/*public SubCategory addSubCategory(SubCategory subCategory);
	
	public SubCategory updateSubCategory(SubCategory subCategory);

	public SubCategory deleteSubCategory(Long subCategoryId);
	
	public SubCategory getSubCategory();
	
	// FOR Category -------------------------------------------------------------------------------------------
	
	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);

	public Category deleteCategory(Long categoryId);
	
	public Category getCategory();*/
}
