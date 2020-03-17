package za.co.mtn.product.models.subscriptions.view;

import lombok.Data;
import org.springframework.http.HttpStatus;
import za.co.mtn.product.models.APIEntity;

import java.util.List;

@Data
public class SubscriptionService extends APIEntity {

    private HttpStatus statusCode;
    private List<Subscription> data;

}
