package com.ivorita.management.service;

import com.ivorita.bean.Admin;

public interface AdminService {

    public void register(Admin admin);

    public boolean login(String user, String password);

}
