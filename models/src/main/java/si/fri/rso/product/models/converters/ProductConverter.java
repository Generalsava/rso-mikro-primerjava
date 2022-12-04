package si.fri.rso.product.models.converters;

import si.fri.rso.product.lib.Product;
import si.fri.rso.product.models.entities.ProductEntity;

public class ProductConverter {

    public static Product toDto(ProductEntity entity) {

        Product dto = new Product();
        dto.setId(entity.getId());
        dto.setProductName(entity.getProductName());
        dto.setType(entity.getType());
        dto.setAvailabilityOnline(entity.getavailabilityOnline());
        dto.setNameBrand(entity.getNameBrand());
        dto.setPrice(entity.getPrice());

        return dto;
    }

    public static ProductEntity toEntity(Product dto) {

        ProductEntity entity = new ProductEntity();
        entity.setId(dto.getId());
        entity.setProductName(dto.getProductName());
        entity.setType(dto.getType());
        entity.setavailabilityOnline(dto.getAvailabilityOnline());
        entity.setNameBrand(dto.getNameBrand());
        entity.setPrice(dto.getPrice());

        return entity;
    }
}
