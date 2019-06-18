package sk.fri.uniza.views;

import sk.fri.uniza.api.Paged;
import sk.fri.uniza.api.Person;
import sk.fri.uniza.core.User;

import javax.ws.rs.core.UriInfo;
import java.util.List;

public class CitiesView extends MaterializePage<MaterializeHeader, MaterializeFooter> {
    private final List<Person> persons;
    private final Paged paged;
    private final User loginUser;
    public CitiesView(UriInfo uriInfo,
                      List<Person> persons,
                      Paged paged,
                      Person loginUser) {
        super("cities_table.ftl", uriInfo, new MaterializeHeader(loginUser, "Zoznam sledovaných miest", true), new MaterializeFooter());
        this.persons = persons;
        this.paged = paged;
        this.loginUser = loginUser;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public Paged getPaged() {
        return paged;
    }

    public User getLoginUser() {
        return loginUser;
    }
}
