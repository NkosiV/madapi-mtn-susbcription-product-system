package za.co.mtn.product.enums;

public enum SystemName {

    PRODUCT("PRODUCT", "PRODUCT Services");

    private String name;
    private String description;

    SystemName(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
