/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytt.daos;

import duytt.dtos.Subject;
import duytt.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thant
 */
public class SubjectDAO {
	public List<Subject> getSubject() throws ClassNotFoundException, SQLException {
		List<Subject> result = null;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "select [subId],[subName],[numQues],[numTime],[status] from [dbo].[subject]";
				pst = cn.prepareStatement(sql);
				rs = pst.executeQuery();
				while (rs.next()) {
					String subId=rs.getString("subId");
					String subName=rs.getString("subName");
					int numQues=rs.getInt("numQues");
					int numTime=rs.getInt("numTime");
					boolean status=rs.getBoolean("status");
					if(result==null)
					{
						result=new ArrayList<>();
					}
					result.add(new Subject(subId, subName, numQues, numTime, status));
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
	public String getTime(String subId) throws ClassNotFoundException, SQLException {
		String result = null;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "select [numTime] from [dbo].[subject] where [subId]=?";
				pst = cn.prepareStatement(sql);
				pst.setString(1, subId);
				rs = pst.executeQuery();
				if (rs.next()) {
					result=rs.getString("numTime");
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
	public String getNumQues(String subId) throws ClassNotFoundException, SQLException {
		String result = null;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "select [numQues]  from [dbo].[subject] where [subId]=?";
				pst = cn.prepareStatement(sql);
				pst.setString(1, subId);
				rs = pst.executeQuery();
				if (rs.next()) {
					result=rs.getString("numQues");
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
}
