package hello.study.config;

import hello.study.security.CustomFailureHandler;
import hello.study.security.CustomSuccessHandler;
import hello.study.security.FilterA;
import hello.study.security.ProviderA;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http
            .addFilterBefore(filterA("/b"), UsernamePasswordAuthenticationFilter.class)
            .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new ProviderA());
    }

    public FilterA filterA(String url) throws Exception {
        FilterA filterA = new FilterA(url);

        filterA.setAuthenticationManager(authenticationManagerBean());
        filterA.setAuthenticationSuccessHandler(customSuccessHandler());
        filterA.setAuthenticationFailureHandler(customFailureHandler());

        return filterA;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CustomSuccessHandler customSuccessHandler() {
        return new CustomSuccessHandler();
    }

    @Bean
    public CustomFailureHandler customFailureHandler() {
        return new CustomFailureHandler();
    }
}
