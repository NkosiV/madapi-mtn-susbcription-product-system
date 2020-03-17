package za.co.mtn.product.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractProperties implements Serializable {

    private static final long serialVersionUID = 8563442571331250556L;

    private String getCustomerProfileEndpointOperation;
    private String devHostname;
    private String prodHostname;
    private int timeout;

}
