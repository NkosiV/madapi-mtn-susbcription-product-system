package za.co.mtn.product.enums;

import lombok.Getter;

@Getter
public enum SubscriptionStatus {

    ACTIVE("ACTIVE"),
    PENDING("PENDING"),
    INACTIVE("INACTIVE");

    private String subscriptionStatus;

    SubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public static SubscriptionStatus getEnum(String value) {
        for (SubscriptionStatus v : values())
            if (v.getSubscriptionStatus().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }

}