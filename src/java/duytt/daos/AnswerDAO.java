/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytt.daos;

import duytt.dtos.Answer;
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
public class AnswerDAO {
	public void createAnswer(Answer ans) throws SQLException, ClassNotFoundException {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String ansIDNext = "";
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String ansId = "";
				String sqlMaxOrder = "SELECT MAX([ansId]) as maxAns FROM [dbo].[answer]";
				pst = cn.prepareStatement(sqlMaxOrder);
				rs = pst.executeQuery();
				if (rs.next()) {
					ansId = rs.getString("maxAns");
				}
				if (ansId == null) {
					ansId = "15750";
				}
				ansIDNext = String.valueOf(Integer.parseInt(ansId) + 1);
				String sql = "insert into [dbo].[answer]([ansId],[ansContent],[ansCorrect],[quesId],[status]) values (?,?,?,?,?)";
				pst = cn.prepareStatement(sql);
				pst.setInt(1, Integer.parseInt(ansIDNext));
				pst.setString(2, ans.getAnsContent());
				pst.setBoolean(3, ans.isAnsCorrect());
				pst.setInt(4, ans.getQuesId());
				pst.setBoolean(5, true);
				pst.executeUpdate();
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
	}
	public List<Answer> getAnswer() throws ClassNotFoundException, SQLException {
		List<Answer> result = null;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "select [ansId],[ansContent],[ansCorrect],[quesId],[status] from [dbo].[answer]";
				pst = cn.prepareStatement(sql);
				rs = pst.executeQuery();
				while (rs.next()) {
					int ansId = rs.getInt("ansId");
					String ansContent = rs.getString("ansContent");
					boolean ansCorrect = rs.getBoolean("ansCorrect");
					int quesId = rs.getInt("quesId");
					boolean status = rs.getBoolean("status");
					if (result == null) {
						result = new ArrayList<>();
					}
					result.add(new Answer(ansId, ansContent, ansCorrect, quesId, status));
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
	
	public void updateQuestion(Answer ans) throws ClassNotFoundException, SQLException {
		Connection cn = null;
		PreparedStatement pst = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "update [dbo].[answer] set [ansContent]=?,[ansCorrect]=?,[quesId]=?,[status]=? where [ansId]=?";
				pst = cn.prepareStatement(sql);
				pst.setString(1, ans.getAnsContent());
				pst.setBoolean(2, ans.isAnsCorrect());
				pst.setInt(3, ans.getQuesId());
				pst.setBoolean(4, ans.isStatus());
				pst.setInt(5, ans.getAnsId());			
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
