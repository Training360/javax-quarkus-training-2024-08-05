package books;

import io.quarkus.logging.Log;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/api/books")
public class BooksResource {

    @POST
    @Produces("application/json")
    public BookDto create(@Valid BookDto book) {
        Log.debug("Create book: " + book);
        return book;
    }
}
