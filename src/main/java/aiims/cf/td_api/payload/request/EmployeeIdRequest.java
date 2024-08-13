package aiims.cf.td_api.payload.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class EmployeeIdRequest {

	@NotEmpty(message="Please provide a Employee Id.")
	@Pattern(regexp = "^[a-zA-Z]{1}[0-9]{7}$",message = "Employee Id must be of 8 length with no special characters and first letter must be alphabet.")
	private String employeeId;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public EmployeeIdRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeIdRequest(
			@NotEmpty(message = "Please provide a Employee Id.") @Pattern(regexp = "^[a-zA-Z]{1}[0-9]{7}$", message = "Employee Id must be of 8 length with no special characters and first letter must be alphabet.") String employeeId) {
		super();
		this.employeeId = employeeId;
	}
	
	
}
