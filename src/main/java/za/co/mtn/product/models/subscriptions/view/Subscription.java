package za.co.mtn.product.models.subscriptions.view;

import lombok.Data;
import za.co.mtn.product.enums.SubscriptionStatus;
import za.co.mtn.product.enums.SubscriptionType;
import za.co.mtn.product.models.APIEntity;

import java.math.BigDecimal;

@Data
public class Subscription extends APIEntity {

    private static final long serialVersionUID = -5649441344121532493L;

    private String subscriptionId;
    private String systemId;
    private String subscriptionName;
    private String subscriptionDescription;
    private SubscriptionStatus subscriptionStatus;
    private SubscriptionType subscriptionType;
    private String subscriptionLength;
    private String registrationChannel;
    private String startDate;
    private String endDate;
    private String email;
    private BigDecimal amount;

}