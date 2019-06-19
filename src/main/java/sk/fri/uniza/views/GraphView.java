package sk.fri.uniza.views;

import sk.fri.uniza.api.WeatherRecord;
import sk.fri.uniza.core.User;

import javax.ws.rs.core.UriInfo;
import java.util.List;

public class GraphView extends MaterializePage<MaterializeHeader, MaterializeFooter> {
    private final User loginUser;
    private final List<WeatherRecord> weatherRecords;

    public GraphView(UriInfo uriInfo, User loginUser, List<WeatherRecord> records) {
        super("graphs.ftl", uriInfo, new MaterializeHeader(loginUser, "Prehlad", true), new MaterializeFooter());
        this.loginUser = loginUser;
        this.weatherRecords = records;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public List<WeatherRecord> getWeatherRecords() {
        return weatherRecords;
    }

    public String getTimeStampData(Integer countOfValues)
    {
        String retVal;
        retVal = "['";
        Integer counter = 0;

        for (WeatherRecord record: weatherRecords) {
            if(counter != 0)
                retVal += "', '";

//            retVal += record.getCreationTime().getMinutes()+":"+record.getCreationTime().getSeconds();
            retVal += record.getCreationTime().toLocaleString();
            counter ++;
            if(counter == countOfValues)
                break;
        }
        retVal += "']";
        System.out.println(retVal);
        return retVal;
    }

    public String getTemperature(Integer countOfValues)
    {
        String retVal;
        retVal = "[";
        Integer counter = 0;

        for(WeatherRecord record: weatherRecords) {
            if(counter != 0)
                retVal += ", ";
            retVal += record.getTemperature() ;
            counter ++;
            if(counter == countOfValues)
                break;
        }
        retVal += "]";

        System.out.println(retVal);

        return retVal;
    }

    public String getDataTempTime(Integer countOfValues)
    {
        String retVal;
        retVal = "[";
        Integer counter = 0;
        // Data = [{x: 12:50 ,y: 23.5},{},{}]
        for(WeatherRecord record: weatherRecords) {
            if(counter != 0)
                retVal += ",";

            retVal +="{x:"+ record.getCreationTime().getTime() +", y: "+ record.getTemperature() + "}";
            counter ++;
            if(counter == countOfValues)
                break;
        }
        retVal += "]";

        System.out.println(retVal);

        return retVal;
    }

    public String getDataHumTime(Integer countOfValues)
    {
        String retVal;
        retVal = "[";
        Integer counter = 0;
        // Data = [{x: 12:50 ,y: 23.5},{},{}]
        for(WeatherRecord record: weatherRecords) {
            if(counter != 0)
                retVal += ",";

            retVal +="{x:"+ record.getCreationTime().getTime() +", y: "+ record.getHumidity() + "}";
            counter ++;
            if(counter == countOfValues)
                break;
        }
        retVal += "]";

        System.out.println(retVal);

        return retVal;
    }

    public String getDataPressureTime(Integer countOfValues)
    {
        String retVal;
        retVal = "[";
        Integer counter = 0;
        // Data = [{x: 12:50 ,y: 23.5},{},{}]
        for(WeatherRecord record: weatherRecords) {
            if(counter != 0)
                retVal += ",";

            retVal +="{x:"+ record.getCreationTime().getTime() +", y: "+ record.getPressure() + "}";
            counter ++;
            if(counter == countOfValues)
                break;
        }
        retVal += "]";

        System.out.println(retVal);

        return retVal;
    }


}
