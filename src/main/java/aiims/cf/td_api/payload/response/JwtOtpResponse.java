package aiims.cf.td_api.payload.response;

public class JwtOtpResponse {

	String otp;

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public JwtOtpResponse(String otp) {
		super();
		this.otp = otp;
	}

	public JwtOtpResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
