package com.example.demo.models;

import com.example.demo.models.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

public class PersonWithRoles extends Person implements PersonDetails {

    //Copy Contructor - for a Person Object
    public PersonWithRoles(Person person) { // takes an input argument of a 'Person'
        super(person); // this return a copy of the superclass, which is 'Person'
         }
         //Also want to override the getAuthorities() method from the PersonDetails interface
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles = "";
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
        //no roles will be returned.
    }

    @Override
    public boolean isAccountNonLocked() {return true; }

    @Override
    public boolean isCredenialsNonExpired() {return true; }

    @Override
    public boolean isEnabled() {return true; }

}