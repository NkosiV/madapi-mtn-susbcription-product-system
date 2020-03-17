package za.co.mtn.product.models;

import lombok.Data;
import za.co.mtn.product.enums.SystemName;

import java.io.Serializable;

@Data
public class SystemEndpoint implements Serializable {

    private SystemName systemId;
    private String description;
    private String timeout;

}
