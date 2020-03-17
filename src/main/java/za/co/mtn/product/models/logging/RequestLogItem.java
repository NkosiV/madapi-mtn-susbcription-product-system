package za.co.mtn.product.models.logging;

import lombok.Data;
import za.co.mtn.product.enums.RequestType;

@Data
public class RequestLogItem {

    private String msisdn;
    private RequestType operation;
    private String transactionId;

}