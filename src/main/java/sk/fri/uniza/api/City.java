package sk.fri.uniza.api;


import com.fasterxml.jackson.annotation.JsonProperty;


public class City {
    @JsonProperty
    private Long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String country;

    @JsonProperty
    private Double coord_lon;

    @JsonProperty
    private Double coord_lat;

    public City() {
        id = null;
        name = null;
        country = null;
        coord_lat = null;
        coord_lon = null;
    }

    public City(String name, String country, Double cord_lon, Double cord_lat) {
        this.name = name;
        this.country = country;
        this.coord_lon = cord_lon;
        this.coord_lat = cord_lat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getCoord_lon() {
        return coord_lon;
    }

    public void setCoord_lon(Double cord_lon) {
        this.coord_lon = cord_lon;
    }

    public Double getCoord_lat() {
        return coord_lat;
    }

    public void setCoord_lat(Double cord_lat) {
        this.coord_lat = cord_lat;
    }
}

