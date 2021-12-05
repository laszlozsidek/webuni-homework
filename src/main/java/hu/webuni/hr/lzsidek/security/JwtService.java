package hu.webuni.hr.lzsidek.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import hu.webuni.hr.lzsidek.config.HrConfigProperties;
import hu.webuni.hr.lzsidek.model.Employee;
import hu.webuni.hr.lzsidek.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private static final String AUTH = "auth";
    private Algorithm algorithm;
    private String issuer;
    private Long expiresInMinutes;

    @Autowired
    HrConfigProperties config;

    @Autowired
    HrUserDetailsService hrUserDetailsService;

    @Autowired
    EmployeeRepository employeeRepository;

    @PostConstruct
    public void init() {
        algorithm = config.getJwt().getAlgorithm(config.getJwt().getSecret());
        issuer = config.getJwt().getIssuer();
        expiresInMinutes = config.getJwt().getExpiresInMinutes();
    }

    public String createJwtToken(UserDetails principal) {
        Map<String, Object> payload = new HashMap();

        HrUser hrUser = (HrUser) hrUserDetailsService.loadUserByUsername(principal.getUsername());

        payload.put("name", hrUser.getEmployee().getName());
        payload.put("username", hrUser.getEmployee().getUsername());
        payload.put("userId", hrUser.getEmployee().getId());
        payload.put("managerId", hrUser.getEmployee().getManager() == null ? "" : hrUser.getEmployee().getManager().getId());
        payload.put("managerUserName", hrUser.getEmployee().getManager() == null ? "" : hrUser.getEmployee().getManager().getUsername());

        List<Employee> employeeListByManager = employeeRepository.findByManager(hrUser.getEmployee().getId());
        for (int i = 0; i < employeeListByManager.size(); i++) {
            payload.put("underlyingEmployee" + (i + 1) + "Id", employeeListByManager.get(i).getId());
            payload.put("underlyingEmployee" + (i + 1) + "UserName", employeeListByManager.get(i).getUsername());
        }

        return JWT
                .create()
                .withSubject(principal.getUsername())
                .withPayload(payload)
                .withArrayClaim(AUTH, principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(expiresInMinutes)))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public UserDetails parse(String jwtToken) {
        DecodedJWT decodedJWT = JWT
                .require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(jwtToken);

        return new User(decodedJWT.getSubject(), "",
                decodedJWT
                        .getClaim(AUTH)
                        .asList(String.class)
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
    }
}