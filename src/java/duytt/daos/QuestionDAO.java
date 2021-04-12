/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytt.daos;

import duytt.dtos.Answer;
import duytt.dtos.QuesSearch;
import duytt.dtos.Question;
import duytt.dtos.Quiz;
import duytt.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author thant
 */
public class QuestionDAO {

	public int createQues(String quesContent, String subId) throws SQLException, ClassNotFoundException {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int quesIDNext = 0;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String quesID = "";
				String sqlMaxQues = "SELECT MAX([quesId]) as maxQues FROM [dbo].[question]";
				pst = cn.prepareStatement(sqlMaxQues);
				rs = pst.executeQuery();
				if (rs.next()) {
					quesID = rs.getString("maxQues");
				}
				if (quesID == null) {
					quesID = "34250";
				}
				quesIDNext = Integer.parseInt(quesID) + 1;
				Date date = new Date();
				SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd");
				String dat = smp.format(date);
				String sql = "insert into [dbo].[question]([quesId],[quesContent],[createDate],[subId],[status]) values(?,?,?,?,?)";
				pst = cn.prepareStatement(sql);
				pst.setInt(1, quesIDNext);
				pst.setString(2, quesContent);
				pst.setString(3, dat);
				pst.setString(4, subId);
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
		return quesIDNext;
	}

	public HashMap<Integer, List<Answer>> getData(int index, String subIdT) throws ClassNotFoundException, SQLException {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		HashMap<Integer, List<Answer>> has = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "with tblTmp as (select  ROW_NUMBER() over ( order by [quesContent] asc) as num,\n"
						+ "b.[quesId],[quesContent],[createDate],[subId],[ansId],[ansContent],[ansCorrect] from [dbo].[question] a, [dbo].[answer] b where a.quesId=b.quesId and subId like ?)\n"
						+ "select  [quesId],[quesContent],[createDate],[subId],[ansId],[ansContent],[ansCorrect] from tblTmp where num between ? and ?";
				pst = cn.prepareStatement(sql);
				pst.setString(1, "%" + subIdT + "%");
				pst.setInt(2, index * 80 - 79);
				pst.setInt(3, index * 80);
				rs = pst.executeQuery();
				while (rs.next()) {
					int quesId = rs.getInt("quesId");
					String quesContent = rs.getString("quesContent");
					String createDate = rs.getString("createDate");
					String subId = rs.getString("subId");
					int ansId = rs.getInt("ansId");
					String ansContent = rs.getString("ansContent");
					boolean ansCorrect = rs.getBoolean("ansCorrect");
					Question ques = new Question(quesId, quesContent, createDate, subId, true);
					Answer ans = new Answer(ansId, ansContent, ansCorrect, quesId, true);
					if (has == null) {
						has = new HashMap<>();
					}
					if (has != null) {
						if (!has.containsKey(ques.getQuesId())) {
							List<Answer> listTmp = new ArrayList<>();
							listTmp.add(ans);
							has.put(ques.getQuesId(), listTmp);
						} else {
							List<Answer> listTmp = has.get(ques.getQuesId());
							listTmp.add(ans);
							has.put(ques.getQuesId(), listTmp);
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
		return has;

	}

	public List<Question> getQuestion() throws ClassNotFoundException, SQLException {
		List<Question> result = null;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "select [quesId],[quesContent],[createDate],[subId],[status] from [dbo].[question]";
				pst = cn.prepareStatement(sql);
				rs = pst.executeQuery();
				while (rs.next()) {
					int quesId = rs.getInt("quesId");
					String quesContent = rs.getString("quesContent");
					String createDate = rs.getString("createDate");
					String subId = rs.getString("subId");
					boolean status = rs.getBoolean("status");
					if (result == null) {
						result = new ArrayList<>();
					}
					result.add(new Question(quesId, quesContent, createDate, subId, status));
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

	public int countQues(String sub) throws ClassNotFoundException, SQLException {
		int result = 0;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "select count([quesId]) as soques from [dbo].[question] where subId like ?";
				pst = cn.prepareStatement(sql);
				pst.setString(1, "%" + sub + "%");
				rs = pst.executeQuery();
				if (rs.next()) {
					result = rs.getInt("soques");
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

	public List<String> getSubForSearch(String txtsearch, int index) throws ClassNotFoundException, SQLException {
		List<String> result = null;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "with tblTm as(select  ROW_NUMBER() over ( order by count([quesId]) ASC) as num,[subId] from [dbo].[question]\n"
						+ "where [quesContent]  like ? group by [subId] having count([quesId]) > 0) select subId from tblTm where num between ? and ?";
				pst = cn.prepareStatement(sql);
				pst.setString(1, "%" + txtsearch + "%");
				pst.setInt(2, index * 2 - 1);
				pst.setInt(3, index * 2);
				rs = pst.executeQuery();
				while (rs.next()) {
					if (result == null) {
						result = new ArrayList<>();
					}
					String tmp = rs.getString("subId");
					if (tmp != null) {
						result.add(tmp);
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

	private int checkInList(List<QuesSearch> list, String subId) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSubId().equals(subId)) {
				return i;
			}
		}
		return -1;
	}

	public List<QuesSearch> getDataSearch(int index, String txtSearch) throws ClassNotFoundException, SQLException {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<QuesSearch> result = null;
		List<String> listSub = getSubForSearch(txtSearch, index);
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				for (String sub : listSub) {
					String sql = "select b.[quesId],[quesContent],[createDate],[subId],[ansId],[ansContent],[ansCorrect] from [dbo].[question] a, [dbo].[answer] b where a.quesId=b.quesId and quesContent like ? and subId=?";
					pst = cn.prepareStatement(sql);
					pst.setString(1, "%" + txtSearch + "%");
					pst.setString(2, sub);
					rs = pst.executeQuery();
					while (rs.next()) {
						QuesSearch quesS = null;
						int quesId = rs.getInt("quesId");
						String quesContent = rs.getString("quesContent");
						String createDate = rs.getString("createDate");
						String subIds = rs.getString("subId");
						int ansId = rs.getInt("ansId");
						String ansContent = rs.getString("ansContent");
						boolean ansCorrect = rs.getBoolean("ansCorrect");
						Question ques = new Question(quesId, quesContent, createDate, subIds, true);
						Answer ans = new Answer(ansId, ansContent, ansCorrect, quesId, true);

						if (result == null) {
							result = new ArrayList<>();
							quesS = new QuesSearch(sub, null);
							quesS.add(ques.getQuesId(), ans);
							result.add(quesS);
						} else {
							if (checkInList(result, subIds) < 0) {
								quesS = new QuesSearch(subIds, null);
								quesS.add(ques.getQuesId(), ans);
								result.add(quesS);
							} else {
								int indexSub = checkInList(result, subIds);
								if (result.get(indexSub).getHas().containsKey(ques.getQuesId())) {
									List<Answer> listAnsTmp = result.get(indexSub).getHas().get(ques.getQuesId());
									listAnsTmp.add(ans);
									result.get(indexSub).getHas().replace(ques.getQuesId(), listAnsTmp);
								} else {
									List<Answer> listAnsTmp = new ArrayList<>();
									listAnsTmp.add(ans);
									result.get(indexSub).getHas().put(ques.getQuesId(), listAnsTmp);
								}
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

	public int countQuesSearch(String txtSearch) throws ClassNotFoundException, SQLException {
		int result = 0;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "with tblTm as(select  ROW_NUMBER() over ( order by count([quesId]) ASC) as num,[subId] from [dbo].[question]\n"
						+ "where [quesContent]  like ? group by [subId] having count([quesId]) > 0) select count([subId]) as soluong from tblTm";
				pst = cn.prepareStatement(sql);
				pst.setString(1, "%" + txtSearch + "%");
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

	public List<String> getSubForStatus(String status, int index) throws ClassNotFoundException, SQLException {
		List<String> result = null;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "with tblTm as(select  ROW_NUMBER() over ( order by count([quesId]) ASC) as num,[subId] from [dbo].[question]\n"
						+ "where status=? group by [subId] having count([quesId]) > 0) select subId from tblTm where num between ? and ?";
				pst = cn.prepareStatement(sql);
				pst.setString(1, status);
				pst.setInt(2, index * 2 - 1);
				pst.setInt(3, index * 2);
				rs = pst.executeQuery();
				while (rs.next()) {
					if (result == null) {
						result = new ArrayList<>();
					}
					String tmp = rs.getString("subId");
					if (tmp != null) {
						result.add(tmp);
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

	public List<QuesSearch> getDataStatus(int index, String status) throws ClassNotFoundException, SQLException {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<QuesSearch> result = null;
		List<String> listSub = getSubForStatus(status, index);
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				for (String sub : listSub) {
					String sql = "select b.[quesId],[quesContent],[createDate],[subId],[ansId],[ansContent],[ansCorrect] from [dbo].[question] a, [dbo].[answer] b where a.quesId=b.quesId and a.status=? and subId=?";
					pst = cn.prepareStatement(sql);
					pst.setString(1, status);
					pst.setString(2, sub);
					rs = pst.executeQuery();
					while (rs.next()) {
						QuesSearch quesS = null;
						int quesId = rs.getInt("quesId");
						String quesContent = rs.getString("quesContent");
						String createDate = rs.getString("createDate");
						String subIds = rs.getString("subId");
						int ansId = rs.getInt("ansId");
						String ansContent = rs.getString("ansContent");
						boolean ansCorrect = rs.getBoolean("ansCorrect");
						Question ques = new Question(quesId, quesContent, createDate, subIds, true);
						Answer ans = new Answer(ansId, ansContent, ansCorrect, quesId, true);

						if (result == null) {
							result = new ArrayList<>();
							quesS = new QuesSearch(sub, null);
							quesS.add(ques.getQuesId(), ans);
							result.add(quesS);
						} else {
							if (checkInList(result, subIds) < 0) {
								quesS = new QuesSearch(subIds, null);
								quesS.add(ques.getQuesId(), ans);
								result.add(quesS);
							} else {
								int indexSub = checkInList(result, subIds);
								if (result.get(indexSub).getHas().containsKey(ques.getQuesId())) {
									List<Answer> listAnsTmp = result.get(indexSub).getHas().get(ques.getQuesId());
									listAnsTmp.add(ans);
									result.get(indexSub).getHas().replace(ques.getQuesId(), listAnsTmp);
								} else {
									List<Answer> listAnsTmp = new ArrayList<>();
									listAnsTmp.add(ans);
									result.get(indexSub).getHas().put(ques.getQuesId(), listAnsTmp);
								}
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

	public int countQuesStatus(String status) throws ClassNotFoundException, SQLException {
		int result = 0;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "with tblTm as(select  ROW_NUMBER() over ( order by count([quesId]) ASC) as num,[subId] from [dbo].[question]\n"
						+ "where status=? group by [subId] having count([quesId]) > 0) select count([subId]) as soluong from tblTm";
				pst = cn.prepareStatement(sql);
				pst.setString(1, status);
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

	public void updateQuestion(Question ques) throws ClassNotFoundException, SQLException {
		Connection cn = null;
		PreparedStatement pst = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "update [dbo].[question] set [quesContent]=?,[createDate]=?,[subId]=?,[status]=? where [quesId]=?";
				pst = cn.prepareStatement(sql);
				pst.setString(1, ques.getQuesCont());
				pst.setString(2, ques.getCreatDate());
				pst.setString(3, ques.getSubId());
				pst.setBoolean(4, ques.isStatus());
				pst.setInt(5, ques.getQuesId());
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

	public void updateQuestionStatus(int txtQuesId, String status) throws ClassNotFoundException, SQLException {
		Connection cn = null;
		PreparedStatement pst = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {
				String sql = "update [dbo].[question] set [status]=? where [quesId] =?";
				pst = cn.prepareStatement(sql);
				pst.setString(1, status);
				pst.setInt(2, txtQuesId);
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
	public List<Quiz> getQuesCompareQuiz(String subId) throws ClassNotFoundException, SQLException{
		List<Quiz> result = null;
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			cn = MyConnection.getConnection();
			if (cn != null) {

				String sql = "select a.[quesId],[ansId] from [dbo].[answer] a,[dbo].[question] b where a.quesId=b.quesId and ansCorrect='true' and subId=?";
				pst = cn.prepareStatement(sql);
				pst.setString(1, subId);
				rs = pst.executeQuery();
				while (rs.next()) {
					if(result==null)
					{
						result=new ArrayList<>();
					}
					Quiz quiz = new Quiz(rs.getInt("quesId"), rs.getInt("ansId"));
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
	

	
}
