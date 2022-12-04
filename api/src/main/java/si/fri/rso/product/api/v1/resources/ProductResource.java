package si.fri.rso.product.api.v1.resources;

import si.fri.rso.product.lib.Product;
import si.fri.rso.product.services.beans.ProductBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    private ProductBean ProductBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getDelivery() {

        List<Product> Product = ProductBean.getDeliverersFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(Product).build();
    }

    @GET
    @Path("/{id}")
    public Response getDelivery(@PathParam("id") Integer id) {

        Product Product = ProductBean.getDeliverers(id);

        if (Product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(Product).build();
    }

    @POST
    public Response createProduct(Product person) {

        if (person.getProductName() == null || person.getAvailabilityOnline() == null || person.getPrice() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            person = ProductBean.createProduct(person);
        }

        return Response.status(Response.Status.CONFLICT).entity(person).build();
    }

    @PUT
    @Path("{id}")
    public Response putProduct(@PathParam("id") Integer id, Product person) {

        person = ProductBean.putProduct(id, person);

        if (person == null) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
        return Response.status(Response.Status.OK).entity(person).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduct(@PathParam("id") Integer id, Product person) {

        boolean deleted = ProductBean.deleteProduct(id);

        if (deleted) {
            return  Response.status(Response.Status.OK).build();
        } else return Response.status(Response.Status.NOT_FOUND).entity(person).build();
    }
}

