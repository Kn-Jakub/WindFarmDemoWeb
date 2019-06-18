package sk.fri.uniza.views;

import sk.fri.uniza.api.City;
import sk.fri.uniza.api.Paged;
import sk.fri.uniza.api.Person;
import sk.fri.uniza.core.User;

import javax.ws.rs.core.UriInfo;
import java.util.List;

public class MyCitiesView extends MaterializePage<MaterializeHeader, MaterializeFooter> {
    private final List<City> cities;
    private final Paged paged;
    private final Person loginUser;

    public MyCitiesView(UriInfo uriInfo,
                    List<City> cities,
                    Paged paged,
                    Person loginUser)
    {
        super("my_cities.ftl", uriInfo, new MaterializeHeader(loginUser, "Moje mestá", true), new MaterializeFooter());
        this.cities = cities;
        this.paged = paged;
        this.loginUser = loginUser;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public List<City> getCities() {
        return cities;
    }

    public Paged getPaged() {
        return paged;
    }
}
