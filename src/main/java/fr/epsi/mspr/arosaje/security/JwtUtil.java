package fr.epsi.mspr.arosaje.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtUtil {

    private String secret = "bearer";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(CustomUserDetails userDetails) {
        Set<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .claim("username", userDetails.getUsername())
                .claim("email", userDetails.getEmail())
                .claim("roles", roles)
                .claim("id", userDetails.getId())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 heures de validité
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Set<String> getRoleNames(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(authority -> {
                    // Supposant que l'autorité est sous la forme "ROLE_nomDuRole"
                    String authorityName = authority.getAuthority();
                    return authorityName.startsWith("ROLE_") ? authorityName.substring(5) : authorityName;
                })
                .collect(Collectors.toSet());
    }

private String extractId(String token) {
        return extractClaim(token, Claims::getId);
    }

    public String getId(String token) {
        return extractId(token);
    }
}
