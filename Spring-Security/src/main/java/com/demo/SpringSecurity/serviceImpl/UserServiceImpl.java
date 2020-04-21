package com.demo.SpringSecurity.serviceImpl;

import com.demo.SpringSecurity.dao.UserRepository;
import com.demo.SpringSecurity.entity.CRMUser;
import com.demo.SpringSecurity.entity.Role;
import com.demo.SpringSecurity.entity.User;
import com.demo.SpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private User convertFromCRMUser(CRMUser crmuser) {
        User user = new User();

        user.setId(crmuser.getId());
        user.setFirstName(crmuser.getFirstName());
        user.setLastName(crmuser.getLastName());
        user.setUsername(crmuser.getUsername());
        user.setEmail(crmuser.getEmail());

        user.setPassword(hashPassword(crmuser.getPassword()));
        List<Role> role = new ArrayList<Role>();

        Role tempRole = new Role();
        for (String s : crmuser.getRoles()) {
            tempRole.setRoleName(s);
            role.add(tempRole);
        }
        user.setRoles(role);
        return user;
    }

    private String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    @Override
    @Transactional
    public CRMUser save(CRMUser crmuser) {
        User saveUser = convertFromCRMUser(crmuser);
        userRepository.save(saveUser);
        crmuser.setId(saveUser.getId());
        return crmuser;
    }
}
