package za.co.mtn.product.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import za.co.mtn.product.models.APIEntity;

import java.util.Collections;

@Service
@Slf4j
public class HttpService {
    @Bean
    public RestTemplate template() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    public APIEntity execute(String endpoint, Class<? extends APIEntity> responseClass) {
        ResponseEntity<? extends APIEntity> responseEntity = restTemplate.getForEntity(endpoint, responseClass);
        if (!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            throw new RestClientException("There was an error communicating with a microservice - Endpoint: "
                    + endpoint + ". Respnse Code: " + responseEntity.getStatusCodeValue());
        }

        return responseEntity.getBody();
    }

    public APIEntity[] executeArray(String endpoint, Class<? extends APIEntity[]> responseClass) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<HttpHeaders> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<? extends APIEntity[]> responseEntity = restTemplate.exchange(endpoint, HttpMethod.GET, entity, responseClass);
        if (!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            throw new RestClientException("There was an error communicating with a microservice - Endpoint: "
                    + endpoint + ". Respnse Code: " + responseEntity.getStatusCodeValue());
        }

        return responseEntity.getBody();
    }

}
