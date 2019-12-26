package com.ivorita.management.dao.impl;

import com.ivorita.bean.Admin;
import com.ivorita.management.dao.AdminDao;
import com.ivorita.management.exception.AdminExistException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    /**
     * 查询管理员姓名是否存在
     */
    @Override
    public boolean existAdmin(Admin admin) throws AdminExistException, SQLException {
        return false;
    }

    @Override
    public boolean loginAdmin(String userName, String password) throws SQLException {
        return false;
    }

    @Override
    public void addAdmin(Admin admin) throws SQLException {

    }

    @Override
    public List<Admin> queryAllAdmin() throws SQLException {
        return null;
    }

    @Override
    public Admin queryAdminById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public void alertAdmin(Admin admin) throws SQLException {

    }

    @Override
    public void deleteAdmin(Integer id) throws SQLException {

    }

    @Override
    public Admin queryAdminByName(String name) throws SQLException {
        return null;
    }

    @Override
    public void alertAdminPassword(Integer id, String password) throws SQLException {

    }
}
