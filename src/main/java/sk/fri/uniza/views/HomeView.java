package sk.fri.uniza.views;

import sk.fri.uniza.api.City;
import sk.fri.uniza.api.Paged;
import sk.fri.uniza.api.Person;
import sk.fri.uniza.core.User;

import javax.ws.rs.core.UriInfo;
import java.util.List;

public class HomeView extends MaterializePage<MaterializeHeader, MaterializeFooter> {

    public HomeView(UriInfo uriInfo,
                    User loginUser)
    {
        super("home.ftl", uriInfo, new MaterializeHeader(loginUser, "Uvodna obrazovka", true), new MaterializeFooter());

    }
}
