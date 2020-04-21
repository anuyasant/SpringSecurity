package com.demo.SpringSecurity.serviceImpl;

import com.demo.SpringSecurity.dao.UserRepository;
import com.demo.SpringSecurity.entity.Role;
import com.demo.SpringSecurity.entity.User;
import com.demo.SpringSecurity.entity.UserDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);
        try {
            user.orElseThrow(() -> new UsernameNotFoundException("User Not found with username " + username));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        for(Role roles : user.get().getRoles()){
            System.out.println(roles.getRoleName());
        }

        return new UserDetailsEntity(user.get());
    }
}
