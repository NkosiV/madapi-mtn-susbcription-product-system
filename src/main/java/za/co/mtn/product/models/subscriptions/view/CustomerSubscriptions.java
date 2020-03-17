package za.co.mtn.product.models.subscriptions.view;

import lombok.Data;
import za.co.mtn.product.models.APIEntity;

import java.util.Map;

@Data
public class CustomerSubscriptions extends APIEntity {

    private String customerId;
    private Map<String, SubscriptionService> services;

}
