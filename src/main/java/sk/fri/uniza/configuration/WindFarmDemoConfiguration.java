
package sk.fri.uniza.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "template",
    "defaultName",
    "serviceDbAuth"
})
public class WindFarmDemoConfiguration extends Configuration {

    @JsonProperty("template")
    private String template;
    @JsonProperty("defaultName")
    private String defaultName;
    @JsonProperty("serviceDbAuth")
    private ServiceDbAuth serviceDbAuth;
    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;

    @JsonProperty("template")
    public String getTemplate() {
        return template;
    }

    @JsonProperty("template")
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty("defaultName")
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty("defaultName")
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    @JsonProperty("serviceDbAuth")
    public ServiceDbAuth getServiceDbAuth() {
        return serviceDbAuth;
    }

    @JsonProperty("serviceDbAuth")
    public void setServiceDbAuth(ServiceDbAuth serviceDbAuth) {
        this.serviceDbAuth = serviceDbAuth;
    }

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
        return swaggerBundleConfiguration;
    }
    @JsonProperty("swagger")
    public void setSwaggerBundleConfiguration(SwaggerBundleConfiguration swaggerBundleConfiguration) {
        this.swaggerBundleConfiguration = swaggerBundleConfiguration;
    }
}
