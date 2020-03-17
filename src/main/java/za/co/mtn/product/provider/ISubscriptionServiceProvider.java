package za.co.mtn.product.provider;

import za.co.mtn.product.models.subscriptions.view.CustomerSubscriptions;
import za.co.mtn.product.models.subscriptions.view.Subscription;

public interface ISubscriptionServiceProvider {

    CustomerSubscriptions getSubscriptions(String id);

    Subscription subscribe(String customerId, Subscription subscription);

}
