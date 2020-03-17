package za.co.mtn.product.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import za.co.mtn.product.enums.RequestType;
import za.co.mtn.product.helpers.LoggingHelper;
import za.co.mtn.product.models.APIEntity;
import za.co.mtn.product.models.logging.RequestLogItem;
import za.co.mtn.product.models.subscriptions.view.Subscription;
import za.co.mtn.product.provider.ISubscriptionServiceProvider;

@Slf4j
@RestController
public class ProductSystemServiceController {

    private final ISubscriptionServiceProvider serviceProvider;

    private LoggingHelper loggingHelper;

    @Autowired
    public ProductSystemServiceController(ISubscriptionServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
        this.loggingHelper = LoggingHelper.getInstance();
    }

    @ApiOperation(
            value = "Subscribes to a subscription.",
            notes = "Subscribes the customer id supplied to the subscription supplied.",
            response = Subscription.class
    )
    @PostMapping(value = "/customers/{id}/subscriptions", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<APIEntity> subscribe(@PathVariable String id, @RequestBody Subscription subscription) {
        RequestLogItem request = new RequestLogItem();
        request.setMsisdn(id);
        request.setOperation(RequestType.SUBSCRIBE);
        loggingHelper.requestObject(log, request);

        subscription = serviceProvider.subscribe(id, subscription);

        HttpStatus status = HttpStatus.OK;
        if (subscription == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        loggingHelper.responseObject(log, subscription);
        return new ResponseEntity<>(subscription, status);
    }

}
