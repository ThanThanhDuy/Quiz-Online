/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytt.daos;

import duytt.dtos.Answer;
import duytt.dtos.History;
import duytt.dtos.Quiz;
import duytt.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author thant
 */
public class QuizDAO {

	public List<Answer> getAnswerForTest(int quesId) throws ClassNotFoundException, SQLException {
		List<Answer> result = null;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {

				String sql = "select [ansId],[ansContent],[ansCorrect],[quesId] from [dbo].[answer] where [quesId]=?";
				pst = cn.prepareStatement(sql);
				pst.setInt(1, quesId);
				rs = pst.executeQuery();
				while (rs.next()) {
					int ansId = rs.getInt("ansId");
					String ansContent = rs.getString("ansContent");
					boolean ansCorrect = rs.getBoolean("ansCorrect");
					int quesIds = rs.getInt("quesId");
					Answer ans = new Answer(ansId, ansContent, ansCorrect, quesIds, true);
					if (result == null) {
						result = new ArrayList<>();
					}
					result.add(ans);
				}
//				if (result.size() > 0) {
//					Collections.shuffle(result);
//				}
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

	public HashMap<Integer, List<Answer>> getQuesForTest(String subId, List<String> list) throws ClassNotFoundException, SQLException {
		HashMap<Integer, List<Answer>> result = null;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				for (String st : list) {
					String sql = "with tblTmp as(SELECT ROW_NUMBER() over(order by [subId]) as num, [quesId] from [dbo].[question] where subId=? and status = 1)\n"
							+ "select [quesId] from tblTmp where num=?";
					pst = cn.prepareStatement(sql);
					pst.setString(1, subId);
					pst.setString(2, st);
					rs = pst.executeQuery();
					if (rs.next()) {
						int quesId = rs.getInt("quesId");
						List<Answer> listAns = getAnswerForTest(quesId);
						if (result == null) {
							result = new HashMap<>();
						}
						if (result != null) {
							if (!result.containsKey(quesId)) {
								result.put(quesId, listAns);
							}
						}

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

	public int createQuiz(String subId) throws SQLException, ClassNotFoundException {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int quizIDNext = 0;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String quizID = "";
				String sqlMaxQues = "select max([quizId]) as maxQ from [dbo].[quiz]";
				pst = cn.prepareStatement(sqlMaxQues);
				rs = pst.executeQuery();
				if (rs.next()) {
					quizID = rs.getString("maxQ");
				}
				if (quizID == null) {
					quizID = "24150";
				}
				quizIDNext = Integer.parseInt(quizID) + 1;
				Date date = new Date();
				SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd");
				String dat = smp.format(date);
				String sql = "insert into [dbo].[quiz]([quizId],[subId],[createQuiz]) values (?,?,?)";
				pst = cn.prepareStatement(sql);
				pst.setInt(1, quizIDNext);
				pst.setString(2, subId);
				pst.setString(3, dat);
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
		return quizIDNext;
	}

	public void createQuizDetail(int quizId, int quesId) throws SQLException, ClassNotFoundException {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "insert into [dbo].[quizDetail]([quizId],[quesId]) values(?,?)";
				pst = cn.prepareStatement(sql);
				pst.setInt(1, quizId);
				pst.setInt(2, quesId);
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

	public int createQuizTake(int quizId, String email) throws SQLException, ClassNotFoundException {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int quizTIDNext = 0;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String quizTID = "";
				String sqlMaxQues = "select max([quizTakeId]) as maxQT from [dbo].[quizTake]";
				pst = cn.prepareStatement(sqlMaxQues);
				rs = pst.executeQuery();
				if (rs.next()) {
					quizTID = rs.getString("maxQT");
				}
				if (quizTID == null) {
					quizTID = "45170";
				}
				quizTIDNext = Integer.parseInt(quizTID) + 1;
				Date date = new Date();
				SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dat = smp.format(date);
				String sql = "insert into [dbo].[quizTake]([quizTakeId],[quizId],[email],[total],[makeQuizDate],[finish]) values (?,?,?,?,?,?)";
				pst = cn.prepareStatement(sql);
				pst.setInt(1, quizTIDNext);
				pst.setInt(2, quizId);
				pst.setString(3, email);
				pst.setInt(4, 0);
				pst.setString(5, dat);
				pst.setBoolean(6, false);
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
		return quizTIDNext;
	}

	public HashMap<Integer, List<Answer>> getQuesForShowTest(int quizId) throws ClassNotFoundException, SQLException {
		HashMap<Integer, List<Answer>> result = null;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {

				String sql = "select [quesId] from [dbo].[quizDetail] where [quizId]=?";
				pst = cn.prepareStatement(sql);
				pst.setInt(1, quizId);
				rs = pst.executeQuery();
				while (rs.next()) {
					int quesId = rs.getInt("quesId");
					List<Answer> listAns = getAnswerForTest(quesId);
					if (result == null) {
						result = new HashMap<>();
					}
					if (result != null) {
						if (!result.containsKey(quesId)) {
							result.put(quesId, listAns);
						}
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

	public String getQuizNotFinish(String email, String subId) throws ClassNotFoundException, SQLException {
		String result = null;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {

				String sql = "select a.quizTakeId,a.quizId from [dbo].[quizTake] a, [dbo].[quiz] b where [email]=? and [finish]=? and subId=?";
				pst = cn.prepareStatement(sql);
				pst.setString(1, email);
				pst.setString(2, "false");
				pst.setString(3, subId);
				rs = pst.executeQuery();
				if (rs.next()) {
					result = rs.getString("quizTakeId") + ";" + rs.getString("quizId");
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

	public String getQuizTID(int quizTakeId, int quizId, int quesId) throws ClassNotFoundException, SQLException {
		String result = null;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {

				String sql = "select [quizTakeId] from [dbo].[quizTakeDetail] where [quizTakeId]=? and [quizId]=? and [quesId]=?";
				pst = cn.prepareStatement(sql);
				pst.setInt(1, quizTakeId);
				pst.setInt(2, quizId);
				pst.setInt(3, quesId);
				rs = pst.executeQuery();
				if (rs.next()) {
					result = rs.getString("quizTakeId");
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

	public void createQuizTakeDetail(int quizTID, int quizId, int quesId, int andsTmp) throws SQLException, ClassNotFoundException {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "insert into [dbo].[quizTakeDetail]([quizTakeId],[quizId],[quesId],[ansTemp]) values (?,?,?,?)";
				pst = cn.prepareStatement(sql);
				pst.setInt(1, quizTID);
				pst.setInt(2, quizId);
				pst.setInt(3, quesId);
				pst.setInt(4, andsTmp);
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

	public void updateQuizTakeDetail(int quizTID, int quizId, int quesId, int andsTmp) throws SQLException, ClassNotFoundException {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "update [dbo].[quizTakeDetail] set [ansTemp]=? where [quizTakeId]=? and [quizId]=? and [quesId]=?";
				pst = cn.prepareStatement(sql);
				pst.setInt(1, andsTmp);
				pst.setInt(2, quizTID);
				pst.setInt(3, quizId);
				pst.setInt(4, quesId);
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

	public List<Quiz> getQuizAnsTmp(int quizTakeId, int quizId) throws ClassNotFoundException, SQLException {
		List<Quiz> result = null;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {

				String sql = "select quesId,ansTemp from [dbo].[quizTakeDetail] where [quizTakeId]=? and [quizId]=?";
				pst = cn.prepareStatement(sql);
				pst.setInt(1, quizTakeId);
				pst.setInt(2, quizId);
				rs = pst.executeQuery();
				while (rs.next()) {
					if (result == null) {
						result = new ArrayList<>();
					}
					Quiz quiz = new Quiz(rs.getInt("quesId"), rs.getInt("ansTemp"));
					result.add(quiz);
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

	public String getTimeStart(String email, int quizId, int quizTakeId) throws ClassNotFoundException, SQLException, ParseException {
		String result = null;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {

				String sql = "select [makeQuizDate] from [dbo].[quizTake] where [email]=?  and [quizId]=? and [quizTakeId]=?";
				pst = cn.prepareStatement(sql);
				pst.setString(1, email);
				pst.setInt(2, quizId);
				pst.setInt(3, quizTakeId);

				rs = pst.executeQuery();
				if (rs.next()) {
					result = rs.getString("makeQuizDate");

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

	public void updateTotalQuiz(float total, String email, int quizId, int quizTakeId) throws SQLException, ClassNotFoundException {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "update [dbo].[quizTake] set [total]=?, [finish]='true' where [quizTakeId]=? and[quizId]=? and [email]=?";
				pst = cn.prepareStatement(sql);
				pst.setFloat(1, total);
				pst.setInt(2, quizTakeId);
				pst.setInt(3, quizId);
				pst.setString(4, email);
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

	public List<History> getHistory(String subId, String email, int index) throws ClassNotFoundException, SQLException, ParseException {
		List<History> result = null;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "with tblTmp as (select ROW_NUMBER() over ( order by [makeQuizDate] desc) as num,subId,[makeQuizDate],[total] from [dbo].[quizTake] a, [dbo].[quiz] b where a.quizId=b.quizId and subId=? and email=? and a.finish='true')\n"
						+ "select subId,[makeQuizDate],[total] from tblTmp where num between ? and ?";
				pst = cn.prepareStatement(sql);
				pst.setString(1, subId);
				pst.setString(2, email);
				pst.setInt(3, index * 20 - 19);
				pst.setInt(4, index * 20);
				rs = pst.executeQuery();
				while (rs.next()) {
					if (result == null) {
						result = new ArrayList<>();
					}
					String subIds=rs.getString("subId");
					String makeQuizDate=rs.getString("makeQuizDate");
					float mark=rs.getFloat("total");
					History his= new History(subIds, makeQuizDate, mark);
					result.add(his);
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
	public int countHistory(String subId,String email) throws ClassNotFoundException, SQLException {
		int result = 0;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "select count([quizTakeId]) as soluong from [dbo].[quizTake] a, [dbo].[quiz] b where a.quizId=b.quizId and subId=? and email=? and a.finish='true'";
				pst = cn.prepareStatement(sql);
				pst.setString(1, subId);
				pst.setString(2, email);
				rs = pst.executeQuery();
				if (rs.next()) {
					result = rs.getInt("soluong");
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
