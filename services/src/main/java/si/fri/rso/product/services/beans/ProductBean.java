package si.fri.rso.product.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;

import si.fri.rso.product.lib.Product;
import si.fri.rso.product.models.converters.ProductConverter;
import si.fri.rso.product.models.entities.ProductEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class ProductBean {

    @Inject
    private EntityManager em;

    public List<Product> getDeliverers() {
        TypedQuery<ProductEntity> query = em.createNamedQuery("ProductEntity.getAll", ProductEntity.class);

        List<ProductEntity> resultList = query.getResultList();

        return resultList.stream().map(ProductConverter::toDto).collect(Collectors.toList());
    }

    public List<Product> getDeliverersFilter(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0).build();

        return JPAUtils.queryEntities(em, ProductEntity.class, queryParameters).stream()
                .map(ProductConverter::toDto).collect(Collectors.toList());
    }

    public Product getDeliverers(Integer id) {

        ProductEntity ProductEntity = em.find(ProductEntity.class, id);

        if (ProductEntity == null) {
            throw new NotFoundException();
        }

        Product Product = ProductConverter.toDto(ProductEntity);

        return Product;
    }

    public Product createProduct(Product Product) {

        ProductEntity ProductEntity = ProductConverter.toEntity(Product);

        try {
            beginTx();
            em.persist(ProductEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (ProductEntity.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return ProductConverter.toDto(ProductEntity);
    }

    public Product putProduct(Integer id, Product Product) {

        ProductEntity person = em.find(ProductEntity.class, id);

        if (person == null) {
            return null;
        }

        ProductEntity updatedProductEntity = ProductConverter.toEntity(Product);

        try {
            beginTx();
            updatedProductEntity.setId(person.getId());
            updatedProductEntity = em.merge(updatedProductEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return ProductConverter.toDto(updatedProductEntity);
    }

    public boolean deleteProduct(Integer id) {

        ProductEntity Product = em.find(ProductEntity.class, id);

        if (Product != null) {
            try {
                beginTx();
                em.remove(Product);
                commitTx();
            }
            catch (Exception e) {
                rollbackTx();
            }
        }
        else {
            return false;
        }

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }

}
