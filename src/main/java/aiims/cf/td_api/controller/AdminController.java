package aiims.cf.td_api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aiims.cf.td_api.modal.SubCategory;
import aiims.cf.td_api.modal.TelephoneDirectory;
import aiims.cf.td_api.modal.User;
import aiims.cf.td_api.payload.request.EmployeeIdRequest;
import aiims.cf.td_api.payload.response.EmployeeResponse;
import aiims.cf.td_api.repository.SubCategoryRepo;
import aiims.cf.td_api.service.AdminService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

	
	@Autowired
	private AdminService adminservice;
	@Autowired
	private SubCategoryRepo subCategoryRepo;
	
	@PostMapping("/getExternalEmployeeDetails")
	public ResponseEntity<Object> callExternalApi(@Valid @RequestBody EmployeeIdRequest employeeIdRequest) throws Exception 
	{
		try {
			ResponseEntity<Object> ret = this.adminservice.callExternalAPiFunc(employeeIdRequest);
			return ret;				
		} catch (Exception e) {
			return new ResponseEntity<Object>(e,HttpStatus.EXPECTATION_FAILED);
		}
    }
	
	@PostMapping("/registerEmployee")
	public ResponseEntity<Object> registerUser(@Valid @RequestBody EmployeeResponse employeeResponse) throws Exception 
	{
		try {
			User ret = this.adminservice.registerUser(employeeResponse);
			return new ResponseEntity<Object>(ret,HttpStatus.CREATED);				
		} catch (Exception e) {
			return new ResponseEntity<Object>(e,HttpStatus.EXPECTATION_FAILED);
		}
    }
	
	
	@GetMapping("/getSubCategory")
	public ResponseEntity<Object> getSubCategory() throws Exception 
	{
		try {
			List<SubCategory> allSubCategory = this.subCategoryRepo.findAll();
			return new ResponseEntity<Object>(allSubCategory,HttpStatus.OK);				
		} catch (Exception e) {
			return new ResponseEntity<Object>(e,HttpStatus.EXPECTATION_FAILED);
		}
    }
	
	
	@GetMapping("/getTelephoneDirectory")
	public ResponseEntity<Object> getGrievancesByDepartmentWise(){
		try {
			Map<String, Map<String,List<TelephoneDirectory>>> telephonedirectorylist = null;
			telephonedirectorylist = adminservice.getTelephoneDirectory();
			if(telephonedirectorylist != null) {
				return new ResponseEntity<Object>(telephonedirectorylist,HttpStatus.OK);									
			}
			else {
				return ResponseEntity.ok(telephonedirectorylist);
			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e,HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/addTelephoneRecord")
	public ResponseEntity<Object> addTelephoneRecord(@RequestBody TelephoneDirectory telephoneDirectory){
		try {
			TelephoneDirectory telephoneRecord = this.adminservice.addTelephoneRecord(telephoneDirectory);
			return new ResponseEntity<Object>(telephoneRecord,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e,HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/updateTelephoneRecord")
	public ResponseEntity<Object> updateTelephoneRecord(@RequestBody TelephoneDirectory telephoneDirectory){
		try {
			TelephoneDirectory telephoneRecord = this.adminservice.updateTelephoneRecord(telephoneDirectory);
			return new ResponseEntity<Object>(telephoneRecord,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e,HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/deleteTelephoneRecord/{telephoneDirectoryId}")
	public ResponseEntity<Object> deleteTelephoneRecord(@PathVariable("telephoneDirectoryId") Long telephoneDirectoryId){
		try {
			TelephoneDirectory telephoneRecord = this.adminservice.deleteTelephoneRecord(telephoneDirectoryId);
			return new ResponseEntity<Object>(telephoneRecord,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e,HttpStatus.EXPECTATION_FAILED);
		}
	}
}
