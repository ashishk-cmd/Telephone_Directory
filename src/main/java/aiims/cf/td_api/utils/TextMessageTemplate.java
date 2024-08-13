package aiims.cf.td_api.utils;

public class TextMessageTemplate {

	public static String sendOTP(String otp, String purpose) {
		StringBuilder sb = new StringBuilder();
		sb.append(otp+" is the OTP to "+purpose+".");
		return sb.toString();
	}
	
}
