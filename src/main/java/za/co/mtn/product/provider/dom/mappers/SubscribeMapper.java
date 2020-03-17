package za.co.mtn.product.provider.dom.mappers;

import za.co.mtn.product.enums.response.ProductSystemResponseCode;
import za.co.mtn.product.models.subscriptions.view.Subscription;
import za.co.mtn.product.provider.dom.subscribe.ActivationResponse;
import za.co.mtn.product.provider.dom.subscribe.SubscribeResponse;

public class SubscribeMapper {

    public static Subscription mapResult(SubscribeResponse subscribeResponse) {
        ProductSystemResponseCode responseCode = (ProductSystemResponseCode) subscribeResponse.getResponseCode();

        ActivationResponse response = subscribeResponse.getResponse();
        if (!ProductSystemResponseCode.OK.equals(responseCode) || response == null) {
            return null;
        }

        return subscribeResponse.getSubscription();
    }

}
