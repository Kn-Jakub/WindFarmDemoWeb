package sk.fri.uniza.resources;

import io.dropwizard.auth.Auth;
import io.dropwizard.views.View;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import sk.fri.uniza.WindFarmDemoApplication;
import sk.fri.uniza.api.City;
import sk.fri.uniza.api.Paged;
import sk.fri.uniza.api.Person;
import sk.fri.uniza.api.WeatherRecord;
import sk.fri.uniza.auth.Role;
import sk.fri.uniza.auth.Session;
import sk.fri.uniza.auth.Sessions;
import sk.fri.uniza.core.User;
import sk.fri.uniza.views.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Path("/home")
public class CityResource {
    final Logger myLogger = LoggerFactory.getLogger(this.getClass());
    private Sessions sessionDao;
    private MyCitiesView myCitiesView = null;
    private NewCityView newCityView = null;

    public CityResource(Sessions sessionDao) {
        this.sessionDao = sessionDao;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @PermitAll
    public View homeView(@Auth User user, @Context UriInfo uriInfo, @Context HttpHeaders headers) {

        return new HomeView(uriInfo, user);
    }

    @GET
    @Path("/my-cities")
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({Role.ADMIN, Role.USER_READ_ONLY})
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


                Response<Paged<List<City>>> execute = WindFarmDemoApplication.getWindFarmServis().getPagedCities("Bearer " + session.getToken(),logedPerson.getId(), 10,  page).execute();
                if (execute.isSuccessful()) {
                    myCitiesView = new MyCitiesView(uriInfo, execute.body().getData(), execute.body(), logedPerson);
                    return myCitiesView;
                }
            }
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            throw new WebApplicationException(e);
        }
    }

    @GET
    @Path("/city")
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({Role.ADMIN,Role.USER_READ_ONLY})
    public View showCity(@Auth User user,
                         @Context UriInfo uriInfo,
                         @Context HttpHeaders headers,
                         @QueryParam("id") Long cityId
                         )
    {
        List<WeatherRecord> wheaterRecords = null;
        try{
            Response<List<WeatherRecord>> weatherRecordsResponse = WindFarmDemoApplication.getWindFarmServis().getRecords(sessionDao.getSession(headers).getBearerToken(),cityId).execute();
            if (!weatherRecordsResponse.isSuccessful()) {
                return null;
            }
            wheaterRecords = weatherRecordsResponse.body();

            return new GraphView(uriInfo,user,wheaterRecords);

        } catch (IOException e) {
            e.printStackTrace();
            throw new WebApplicationException(e);
        }
    }

    @GET
    @Path("/city-delete")
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({Role.USER_READ_ONLY, Role.ADMIN})
    public javax.ws.rs.core.Response cityDelete(@Auth User user,
                               @Context UriInfo uriInfo,
                               @Context HttpHeaders headers,
                               @QueryParam("cityId") Long cityID,
                               @QueryParam("page") Integer page)
    {

        if (cityID == null) return null;


        Session session = sessionDao.getSession(headers);

        Response<Void> response;
        try {

            response = WindFarmDemoApplication.getWindFarmServis().deleteCity(session.getBearerToken(), user.getId(), cityID).execute();
            if (response.isSuccessful()) {
                URI uri = UriBuilder.fromPath("home/my-cities")
                        .queryParam("page", page)
                        .build();
                return javax.ws.rs.core.Response.seeOther(uri)
                        .build();

            }
            throw new WebApplicationException(response.code());
        } catch (IOException e) {
            e.printStackTrace();
            throw new WebApplicationException(e);
        }



    }

    @GET
    @Path("/new-city")
    @Produces(MediaType.TEXT_HTML)
//    @RolesAllowed({Role.ADMIN,Role.USER_READ_ONLY})
    public View newCity(@Auth User user,
                         @Context UriInfo uriInfo,
                         @Context HttpHeaders headers)
    {
        Session session = sessionDao.getSession(headers);
        try {

            final Response<List<String>> countriesResponse = WindFarmDemoApplication.getWindFarmServis().getCountries(session.getBearerToken()).execute();
            if (countriesResponse.isSuccessful()) {
                List<String> countries = countriesResponse.body();
                newCityView = new NewCityView(uriInfo, user, countries,null);
                return newCityView;
            }
            throw new WebApplicationException(countriesResponse.code());
        } catch (IOException e) {
            e.printStackTrace();
            throw new WebApplicationException(e);
        }
    }

    @POST
    @Path("/new-city")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({Role.ADMIN,Role.USER_READ_ONLY})
    public View newCity(@Auth User user,
                        @Context UriInfo uriInfo,
                        @Context HttpHeaders headers,
                        @NotEmpty @FormParam("city") String sId) {
//        Response<Person> personResponse;
//        Person personLoggedIn = null;
        try {
            String sId2 = sId.replaceAll("[^\\d.]","");
            Long id = Long.parseLong(sId2);
            Integer page = 10;

            Response<Void> newCityResponse = WindFarmDemoApplication.getWindFarmServis().
                    saveCity(sessionDao.getSession(headers).getBearerToken(), user.getId(), id).execute();

            if (!newCityResponse.isSuccessful())
                throw new WebApplicationException(newCityResponse.errorBody().string(), newCityResponse.code());

//            personResponse = WindFarmDemoApplication.getWindFarmServis().getPerson(sessionDao.getSession(headers).getBearerToken(), user.getId()).execute();

//            if (personResponse.isSuccessful()) {
//                personLoggedIn = personResponse.body();
                URI uri = UriBuilder.fromPath("home/my-cities")
                        .queryParam("page", page)
                        .build();
                uriInfo.getAbsolutePathBuilder().uri(uri);

                return newCityView;
//            }



        }  catch (IOException e) {
            e.printStackTrace();
            throw new WebApplicationException(e);
        }

    }

    @POST
    @Path("/new-city/from-country")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({Role.ADMIN,Role.USER_READ_ONLY})
    public View newCityFromCountry(@Auth User user,
                                   @Context UriInfo uriInfo,
                                   @Context HttpHeaders headers,
                                   @NotEmpty  @FormParam("country") String country)
    {
        Session session = sessionDao.getSession(headers);
        try {

            final Response<List<City>> citiesResponse = WindFarmDemoApplication.getWindFarmServis().getCities(session.getBearerToken(),country).execute();
            if (citiesResponse.isSuccessful()) {
                List<City> cities = citiesResponse.body();
                newCityView.setCities(cities);
                newCityView.setSelectedCountry(country);
                return newCityView;
            }
            throw new WebApplicationException(citiesResponse.code());
        } catch (IOException e) {
            e.printStackTrace();
            throw new WebApplicationException(e);
        }


    }
}
