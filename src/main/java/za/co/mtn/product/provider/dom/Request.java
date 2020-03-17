package za.co.mtn.product.provider.dom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties({"headers", "requestMethod", "queryString"})
public abstract class Request {

    private Map<String, String> headers;
    private RequestMethod requestMethod;

}
