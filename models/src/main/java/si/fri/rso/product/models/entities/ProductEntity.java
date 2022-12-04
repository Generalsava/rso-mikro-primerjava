package si.fri.rso.product.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "products")
@NamedQueries(value =
        {
                @NamedQuery(name = "ProductEntity.getAll", query = "SELECT person FROM ProductEntity person"),
                @NamedQuery(name = "ProductEntity.getById", query = "SELECT person FROM ProductEntity person WHERE person.id=:id")
        })
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ProductName")
    private String ProductName;

    @Column(name = "Type")
    private String Type;

    @Column(name = "availabilityOnline")
    private Boolean availabilityOnline;

    @Column(name = "NameBrand")
    private Boolean NameBrand;

    @Column(name = "Price")
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

    public Boolean getavailabilityOnline() {
        return availabilityOnline;
    }

    public void setavailabilityOnline(Boolean availabilityOnline) {
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
