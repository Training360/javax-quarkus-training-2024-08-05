package dogs;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/api/dogs")
public class DogsResource {

    // Mutiny: Uni, Multi - stream, pipeline
    // Uni - 0..1 elemet tartalmaz - Optional
    // Multi - 0..n elemet tartalmaz - Stream

    @GET
    @Produces("application/json")
    public Multi<Dog> listDogs() {
        Log.info("List all dogs");
        return Multi.createFrom().items(new Dog(1L, "Masni"), new Dog(2L, "Héra"));
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Uni<Dog> getDog(@PathParam("id") Long id) {
        return Uni.createFrom().item(new Dog(id, "Masni " + id));
    }
}
