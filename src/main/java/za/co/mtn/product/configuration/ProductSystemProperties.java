package za.co.mtn.product.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties("product-system")
@Configuration
public class ProductSystemProperties extends AbstractProperties {

    private String activationEndpoint;

}
