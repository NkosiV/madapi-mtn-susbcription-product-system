package za.co.mtn.product.provider.dom.subscribe;

import lombok.Data;
import za.co.mtn.product.models.APIEntity;

@Data
public class ActivationResponse extends APIEntity {

    private long code;
    private String message;
    private String details;

}
