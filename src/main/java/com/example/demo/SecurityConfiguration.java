package com.example.demo;


import com.example.demo.services.PersonDetailsLoader;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    // Which pages will require authentication?
    // Which pages are available to everyone?
    // What is the path to the login page?
    // What hashing algorithm will we use to store passwords?
    //(Remember we didn't specify Bcrypt in our UserController? Now we can specify which algorithm to use!)

    public PersonDetailsLoader personsLoader;
    // Fancy way of saying now we have supercharged PersonRepository, with way more functionality than a plain PersonRepository has

    // Set up our constructor for this configuration
    public SecurityConfiguration(PersonDetailsLoader personsLoader){this.personsLoader = personsLoader;}

    //This is like a miniature class. but so small that it doesn't warrant its own file, so we can just annotate it is a  @Bean here, for elsewhere in our code
    @Bean
    public PasswordEncoder passwordEncoder() {  return new BCryptPasswordEncoder();}

    //Now let's really ramp this thing up

    //This is the override that just sets up the Authentication process for our app (i.e super-charged PersonRepository and Password Encoder. "Authentication process"

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .personDetailsService(personsLoader) // How to find users by their username
                .passwordEncoder(passwordEncoder()) // How to encode and decode/verify passwords
        ;
    }
    //Override what happens when specific HTTP pages are loaded / requests are made / etc
    @Override
    protected void Configure(HttpSecurity http) throws Exception {
        http
                //Login Configuration//
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/profile") //when user successfully logs in, redirects to //jobs
                .permitAll() // Anyone can to go the Login Page
                //Logout Configuration//
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")  //parameter set to "logout", so message is display to the user when they log out
                //Pages that can be viewed by anyone //
                .and()
                .authorizeRequests() // let visitors view pages, based on their next argument
                .antMatchers("/", "/jobs", "/profile/bio") // Tool matcher - if someone hits these URLs in their browser (i.e. http://localhost:8080/ads) they are allowed to view, even if not logged in
                .permitAll() // like a catch-all
                // Pages that DO require authentication //
                .and()
                .authorizeRequests()
                .antMatchers("/jobs/*","/admin/delete", "admin")
                //pages that we DO want users to be logged in to view/access
                .authenticated()
                // for the previously mentioned Another Tool Matched URL Patters, users should be authenticated (logged in) to access them
                .and()
                .cors()
                .and()
                .csrf()
                .disable()
                ;
    }
}
