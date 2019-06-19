package sk.fri.uniza.views;

import sk.fri.uniza.api.City;
import sk.fri.uniza.core.User;

import javax.ws.rs.core.UriInfo;
import java.util.List;

public class NewCityView extends MaterializePage<MaterializeHeader, MaterializeFooter>  {
    private final User loginUser;
    private final String toastMsg;
    private final List<String> countries;
    private List<City> cities;
    private String selectedCountry;

    public NewCityView(UriInfo uriInfo,
                       User loginUser,
                       List<String> countries,
                       String toastMsg) {
        super("new_city.ftl", uriInfo, new MaterializeHeader(loginUser, "Pridaj mesto", true), new MaterializeFooter());
        this.loginUser = loginUser;
        this.toastMsg = toastMsg;
        this.countries = countries;
        this.cities = null;
        this.selectedCountry ="null";
    }

    public User getLoginUser() {
        return loginUser;
    }

    public String getToastMsg() {
        return toastMsg;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<City> getCities()
    {
        return cities;
    }

    public String getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }
}
