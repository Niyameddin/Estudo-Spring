package config;

/**
 * Created by Guilherme on 28/06/2015.
 */

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebMvc
@EnableCaching
@ComponentScan(basePackages = {"controller", "entity", "persistence", "util"})
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

    /*
     * Configure View Resolver
     */
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /*
    * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
    */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    /*
     * Configure MessageSource to provide internationalized messages
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    /*
     * Configure caching manager
     */
    @Bean
    public CacheManager cacheManager() {
        GuavaCacheManager guavaCacheManager =  new GuavaCacheManager();
        guavaCacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterAccess(5, TimeUnit.MINUTES));
        return guavaCacheManager;
    }
}
