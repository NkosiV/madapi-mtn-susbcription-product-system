package za.co.mtn.product.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import za.co.mtn.product.configuration.ProductSystemProperties;
import za.co.mtn.product.enums.EnvironmentVariable;
import za.co.mtn.product.enums.SystemConstants;
import za.co.mtn.product.enums.SystemName;
import za.co.mtn.product.enums.response.IResponseCode;
import za.co.mtn.product.enums.response.ProductSystemResponseCode;
import za.co.mtn.product.models.subscriptions.view.CustomerSubscriptions;
import za.co.mtn.product.models.subscriptions.view.Subscription;
import za.co.mtn.product.provider.dom.mappers.SubscribeMapper;
import za.co.mtn.product.provider.dom.subscribe.ActivationResponse;
import za.co.mtn.product.provider.dom.subscribe.SubscribeResponse;
import za.co.mtn.product.services.HttpService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@Service
public class ProductSystemProvider implements ISubscriptionServiceProvider {

    private final ProductSystemProperties properties;
    private final HttpService httpService;
    private final Environment env;

    private String hostname;
    private ObjectMapper objectMapper;

    @Autowired
    public ProductSystemProvider(HttpService httpService, ProductSystemProperties properties,
                                 Environment env) {
        this.httpService = httpService;
        this.properties = properties;
        this.env = env;

        this.objectMapper = new ObjectMapper();

        String environment = this.env.getProperty(SystemConstants.ENVIRONMENT_KEY.getKey());

        if (environment == null) {
            log.error("SEVERE: Environment has not been set.");
        }

        if (EnvironmentVariable.PROD.name().equalsIgnoreCase(environment)) {
            hostname = this.properties.getProdHostname();
        } else {
            hostname = this.properties.getDevHostname();
        }

        log.debug("Environment [ " + environment + " ] hostname [ " + this.hostname + " ]");
    }

    @NewSpan(name = "Get Subscriptions")
    @Override
    public CustomerSubscriptions getSubscriptions(String id) {
        return null;
    }

    @NewSpan(name = "Subscribe")
    @Override
    public Subscription subscribe(String customerId, Subscription subscription) {
        IResponseCode responseCode = ProductSystemResponseCode.HTTP_CONNECTION_ERROR;

        SubscribeResponse response = new SubscribeResponse();
        response.setCustomerId(customerId);
        response.setSystemName(SystemName.PRODUCT);
        response.setSubscription(subscription);

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hhmmssSSS");
            LocalDateTime now = LocalDateTime.now();
            String transactionId = now.format(formatter);

            String activationEndpoint = properties.getActivationEndpoint()
                    .replace("{id}", customerId)
                    .replace("{transaction}", transactionId)
                    .replace("{package}", subscription.getSubscriptionId())
                    .replace("{amount}", subscription.getAmount().toString());
            String endpoint = hostname + activationEndpoint;

            log.debug("Activation endpoint: GET " + endpoint);

            ActivationResponse activationResponse =
                    (ActivationResponse) httpService.execute(endpoint, ActivationResponse.class);
            log.debug("Activation endpoint response: " + objectMapper.writeValueAsString(activationResponse));

            responseCode = ProductSystemResponseCode.OK;
            response.setResponse(activationResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            Optional<ProductSystemResponseCode> errorOptional = ProductSystemResponseCode.resolveForException(e);
            if (errorOptional.isPresent()) {
                responseCode = errorOptional.get();
            }
        }

        response.setResponseCode(responseCode);
        return SubscribeMapper.mapResult(response);
    }

}
