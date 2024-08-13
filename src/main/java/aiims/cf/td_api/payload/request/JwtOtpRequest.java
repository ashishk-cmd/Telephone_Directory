package aiims.cf.td_api.payload.request;

import jakarta.validation.constraints.NotEmpty;

public class JwtOtpRequest {

	@NotEmpty(message="Please provide a Employee Id.")
	String employeeId;
	@NotEmpty(message="Please provide a OTP.")
	String otp;
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public JwtOtpRequest(@NotEmpty(message = "Please provide a Employee Id.") String employeeId,
			@NotEmpty(message = "Please provide a OTP.") String otp) {
		super();
		this.employeeId = employeeId;
		this.otp = otp;
	}
	public JwtOtpRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
