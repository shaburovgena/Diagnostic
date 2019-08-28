package webserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import webserver.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //    @Autowired
//    private DataSource dataSource;
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .requestMatcher(new AntPathRequestMatcher("/agent")).csrf().disable()
                .authorizeRequests()//Авторизовывать все запросы, кроме указанных ниже
                .antMatchers("/", "/registration", "/static/**", "/activate/*", "/agent"
                        , "/login**", "/js/**", "/error**", "/sensor**").permitAll()
                .anyRequest().authenticated()
                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/agent").anonymous()
//                .anyRequest().authenticated()
//                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .rememberMe()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll()
                .and()
                .csrf().disable();//csrf disabled temporarily
    }
//    @Bean
//    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerCustomizer (){
//        return container -> {
//            //Если страница не найдена будет возвращать адрес "/" главную страницу, но URL останется
//            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/"));
//        };
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);


// В случае поиска пользователя напрямую в базе
//  auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//                .usersByUsernameQuery("select username, password, active from usr where username=?")
//                .authoritiesByUsernameQuery("select u.username, ur.roles from usr u inner join user_role ur on u.id = ur.user_id where u.username=?");

    }
}
