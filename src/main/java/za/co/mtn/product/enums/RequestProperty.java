package za.co.mtn.product.enums;


public enum RequestProperty {

    TRANSCTION_ID("transactionId");

    private String value;

    RequestProperty(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

}