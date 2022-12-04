package si.fri.rso.product.lib;

public class Product {
    private Integer id;
    private String ProductName;
    private String Type;
    private Boolean availabilityOnline;
    private Boolean NameBrand;
    private Float Price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public Boolean getAvailabilityOnline() {
        return availabilityOnline;
    }

    public void setAvailabilityOnline(Boolean availabilityOnline) {
        this.availabilityOnline = availabilityOnline;
    }

    public Boolean getNameBrand() {
        return NameBrand;
    }

    public void setNameBrand(Boolean NameBrand) {
        this.NameBrand = NameBrand;
    }

    public Float getPrice() {
        return Price;
    }

    public void setPrice(Float Price) {
        this.Price = Price;
    }
}
