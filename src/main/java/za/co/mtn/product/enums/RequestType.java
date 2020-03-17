package za.co.mtn.product.enums;

import lombok.Getter;

@Getter
public enum RequestType {

    SUBSCRIBE("Subscribe");

    RequestType(String requestType) {
        this.requestType = requestType;
    }

    private String requestType;

    public static RequestType getEnum(String value) {
        for (RequestType v : values())
            if (v.getRequestType().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }

}