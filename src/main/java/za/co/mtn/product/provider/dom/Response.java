package za.co.mtn.product.provider.dom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import za.co.mtn.product.enums.SystemName;
import za.co.mtn.product.enums.response.IResponseCode;

@Getter
@Setter
@JsonIgnoreProperties(value = {"responseCode", "systemName"})
public abstract class Response {

    private IResponseCode responseCode;
    private SystemName systemName;

}
