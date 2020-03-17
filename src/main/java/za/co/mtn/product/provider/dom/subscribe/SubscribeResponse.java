package za.co.mtn.product.provider.dom.subscribe;

import lombok.Data;
import za.co.mtn.product.models.subscriptions.view.Subscription;
import za.co.mtn.product.provider.dom.Response;

@Data
public class SubscribeResponse extends Response {

    private String customerId;
    private ActivationResponse response;
    private Subscription subscription;

}
