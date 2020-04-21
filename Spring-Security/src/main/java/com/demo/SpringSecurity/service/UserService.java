package com.demo.SpringSecurity.service;

import com.demo.SpringSecurity.entity.CRMUser;

public interface UserService {

    CRMUser save (CRMUser crmuser);
    //User convertFromCRMUser(CRMUSer crmuser);
}
