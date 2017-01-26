package webconfig;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import persistence.config.AppPersistenceConfig;


public class AppWebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {
        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppPersistenceConfig.class);
       
        // Manage the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));

        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
        dispatcherServlet.register(AppWebConfig.class);

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        
        /*FilterRegistration.Dynamic filter1 = container.addFilter("securityFilter1", new DelegatingFilterProxy());
        filter1.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");*/
        
        FilterRegistration.Dynamic filter2 = container.addFilter("securityFilter2", new CORSFilter());
        filter2.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");
        filter2.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter2.setInitParameter("allowCredentials", "true");
        
    }
 }