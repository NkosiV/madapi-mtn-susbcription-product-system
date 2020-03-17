package za.co.mtn.product.enums;

import lombok.Getter;

@Getter
public enum SystemConstants {

    ENVIRONMENT_KEY("SPRING_PROFILES_ACTIVE");

    private String key;

    SystemConstants(String key) {
        this.key = key;
    }

}