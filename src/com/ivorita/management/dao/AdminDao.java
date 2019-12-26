package com.ivorita.management.dao;

import com.ivorita.bean.Admin;
import com.ivorita.management.exception.AdminExistException;

import java.sql.SQLException;
import java.util.List;

public interface AdminDao {

    public boolean existAdmin(Admin admin) throws AdminExistException, SQLException;

    public boolean loginAdmin(String userName,String password) throws SQLException;

    public void addAdmin(Admin admin) throws SQLException;

    public List<Admin> queryAllAdmin() throws SQLException;

    public Admin queryAdminById(Integer id) throws SQLException;

    public void alertAdmin(Admin admin) throws SQLException;

    public void deleteAdmin(Integer id) throws SQLException;

    public Admin queryAdminByName(String name) throws SQLException;

    public void alertAdminPassword(Integer id,String password) throws SQLException;

}
