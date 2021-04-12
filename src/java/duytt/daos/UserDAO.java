/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytt.daos;

import duytt.dtos.User;
import duytt.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thant
 */
public class UserDAO {

	public User checkLogin(String userAccount, String password) throws ClassNotFoundException, SQLException {
		User result = null;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "select [email],[fullName],[roleID],[status] from [dbo].[user] where [email]=? and [password]=?";
				pst = cn.prepareStatement(sql);
				pst.setString(1, userAccount);
				pst.setString(2, password);
				rs = pst.executeQuery();
				if (rs.next()) {
					String email=rs.getString("email");
					String pass="";
					String fullName=rs.getString("fullName");
					String roleId=rs.getString("roleID");
					boolean status=rs.getBoolean("status");
					result = new User(email, pass, fullName, roleId, status);
				}
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (cn != null) {
				cn.close();
			}
		}

		return result;
	}
	public boolean checkUser(String userAccount) throws ClassNotFoundException, SQLException {
		boolean result = true;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "select [fullName] from [dbo].[user] where [email]=?";
				pst = cn.prepareStatement(sql);
				pst.setString(1, userAccount);
				rs = pst.executeQuery();
				if (rs.next()) {	
					String tmp =rs.getString("fullName");	
					if(tmp != null)
					{
						result=false;
					}
				}
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (cn != null) {
				cn.close();
			}
		}

		return result;
	}
	public void addUser(User user) throws ClassNotFoundException, SQLException {
		Connection cn = null;
		PreparedStatement pst = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "insert into [dbo].[user]([email],[password],[fullName],[roleID],[status]) values(?,?,?,?,?)";
				pst = cn.prepareStatement(sql);
				pst.setString(1, user.getEmail());
				pst.setString(3, user.getFullName());
				pst.setString(2, user.getPassword());
				pst.setString(4, user.getRoleId());
				pst.setBoolean(5, user.isStatus());
				pst.executeUpdate();
			}
		} finally {
			if (pst != null) {
				pst.close();
			}
			if (cn != null) {
				cn.close();
			}
		}
	}
}
