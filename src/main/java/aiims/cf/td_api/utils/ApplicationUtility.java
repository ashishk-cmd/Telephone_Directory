package aiims.cf.td_api.utils;

import java.util.Random;

public class ApplicationUtility {

	public static int generateOTP(int length) {
        String numbers = "1234567890";
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        char number='0';
        for(int i = 0; i< length ; i++) {
        	if( i == 0 ) {
        		while(number=='0') {
        			  number = numbers.charAt(random.nextInt(numbers.length()));
        		}
        	}
        	else {
        		number = numbers.charAt(random.nextInt(numbers.length()));
        	}
        	otp.append(number);
        }
        return Integer.parseInt(otp.toString());
    }
	
	
	public static String generatePassword(int length) {
	      String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	      String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
	      String specialCharacters = "!@#$";
	      String numbers = "1234567890";
	      String combinedChars = lowerCaseLetters + capitalCaseLetters  + specialCharacters + numbers;
	      Random random = new Random();
	      StringBuilder password = new StringBuilder();
	      password.append(capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length())));
	      password.append(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
	      password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));
	      password.append(numbers.charAt(random.nextInt(numbers.length())));
      for(int i = 0; i< length-4 ; i++) {
	    	  password.append(combinedChars.charAt(random.nextInt(combinedChars.length())));
	      }
	      return password.toString() ;
	}
	
}
