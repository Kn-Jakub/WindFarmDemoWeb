package sk.fri.uniza.resources;

import io.dropwizard.auth.Auth;
import io.dropwizard.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import sk.fri.uniza.WindFarmDemoApplication;
import sk.fri.uniza.api.City;
import sk.fri.uniza.api.Paged;
import sk.fri.uniza.api.Person;
import sk.fri.uniza.auth.Role;
import sk.fri.uniza.auth.Session;
import sk.fri.uniza.auth.Sessions;
import sk.fri.uniza.core.User;
import sk.fri.uniza.views.HomeView;
import sk.fri.uniza.views.PersonsView;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Path("/home")
public class CityResource {
    final Logger myLogger = LoggerFactory.getLogger(this.getClass());
    private Sessions sessionDao;

    public CityResource(Sessions sessionDao) {
        this.sessionDao = sessionDao;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({Role.ADMIN})
    public View showUsersCities(@Auth User user,
                                @Context UriInfo uriInfo,
                                @Context HttpHeaders headers,
                                @QueryParam("page") Integer page)
    {

        Session session = sessionDao.getSession(headers);
        try {
            // Get user
            Person logedPerson = null;
            Response<Person> personResponse = WindFarmDemoApplication.getWindFarmServis().getPerson(session.getBearerToken(),  user.getId()).execute();
            if (personResponse.isSuccessful()) {
                logedPerson = personResponse.body();
            }

            Response<Paged<List<City>>> execute = WindFarmDemoApplication.getWindFarmServis().getPagedCities("Bearer " + session.getToken(), 10, user.getId(),page).execute();
            if (execute.isSuccessful()) {
                return new HomeView(uriInfo, execute.body().getData(), execute.body(),logedPerson);
            }
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            throw new WebApplicationException(e);
        }
    }

    @GET
    @Path("/city/")
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({Role.ADMIN})
    public View showCity(@Auth User user,
                         @Context UriInfo uriInfo,
                         @Context HttpHeaders headers)
    {
        return null;
    }
}
