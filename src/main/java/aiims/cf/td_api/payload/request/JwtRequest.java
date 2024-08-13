package aiims.cf.td_api.payload.request;

import jakarta.validation.constraints.NotEmpty;

public class JwtRequest {

	@NotEmpty(message="Please provide a Employee Id.")
	String employeeId;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public JwtRequest(@NotEmpty(message = "Please provide a Employee Id.") String employeeId) {
		super();
		this.employeeId = employeeId;
	}

	public JwtRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
