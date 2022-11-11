package esprit.sw.Util;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import esprit.sw.Resources.AuthenticationEndPoint;
import esprit.sw.Resources.Employees;
import esprit.sw.filters.AuthenticationFilter;
import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("rest")
public class RestActivator extends Application {

	public RestActivator() {
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.2");
		beanConfig.setSchemes(new String[]{"http"});
		beanConfig.setHost("localhost:8085");
		beanConfig.setBasePath("RESTFUL/rest");
		beanConfig.setResourcePackage("io.swagger.resources");
		beanConfig.setResourcePackage("esprit.sw");
		beanConfig.setScan(true);
		}
	
	
	@Override
	public Set<Class<?>> getClasses() {
	Set<Class<?>> resources = new HashSet<>();
	resources.add(Employees.class);
	resources.add(AuthenticationEndPoint.class);
	resources.add(AuthenticationFilter.class);
	//resources.add(SecondResource.class);

	resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
	resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
	return resources;
	}
}
