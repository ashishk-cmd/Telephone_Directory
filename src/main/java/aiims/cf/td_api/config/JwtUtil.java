package aiims.cf.td_api.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private static final String SECRET_KEY = "bdhgdfgvadufgaefgyauegfyaegfyeagfyaegyfgryewqyruqiopjjvfgagsfimsxngasuifjsakfbahgfisajfai";
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
	//retrieve username from jwt token
	//getUsernameFromToken
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    //getExpirationDateFromToken
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    //for retrieving any information from token we will need the secret key
    //getAllClaimsFromToken
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    //isTokenExpired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }
    
    //creating token
    //doGenerateToken
    private String createToken(Map<String, Object> claims, String subject) {
        JwtBuilder jwt = Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000));
        String signedJWT = jwt.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
        return signedJWT;
                
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token).toLowerCase();
        return (username.equals(userDetails.getUsername().toLowerCase()) && !isTokenExpired(token));
    }

	public String getUsernameFromToken(String jwtToken) {
		String username = extractUsername(jwtToken);
		return username.toLowerCase();
	}
	
}
