package projects.javasampleproject.commons;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import projects.javasampleproject.dto.UserDto;

@Service
public class AuthenticationCommons {
    private RestTemplate restTemplate;
    public AuthenticationCommons(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    public UserDto validateToken(String token){
        ResponseEntity<UserDto> responseEntity=restTemplate.postForEntity("http://localhost:8181/validate/"+token,null, UserDto.class);
        if (responseEntity.getBody()== null){
            return null;
        }
        return responseEntity.getBody();
    }
}
