import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Database {
	Connection con = null;
	Statement stmt = null;
	String url = "jdbc:mysql://localhost/login?serverTimezone=Asia/Seoul";	//login 스키마
	String user = "root";
	String passwd = "mite";		//본인이 설정한 root 계정의 비밀번호를 입력하면 된다.

	Database() {	//데이터베이스에 연결한다.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, passwd);
			stmt = con.createStatement();
			System.out.println("MySQL 서버 연동 성공");
		} catch(Exception e) {
			System.out.println("MySQL 서버 연동 실패 > " + e.toString());
		}
	}


	DragonFlight d = null;
	Operator o = null;
	
	boolean logincheck(String _n, String _i, String _p) {
	    boolean flag = false;

	    String id = _i;
	    String pw = _p;

	    try {
	        String checkingStr = "SELECT password FROM member WHERE id='" + id + "'";
	        ResultSet result = stmt.executeQuery(checkingStr);

	        while (result.next()) {
	            if (pw.equals(result.getString("password"))) {
	                flag = true;
	                System.out.println("로그인 성공");
	            } else {
	                flag = false;
	                System.out.println("로그인 실패");
	            }
	        }
	    } catch (Exception e) {
	        flag = false;
	        System.out.println("로그인 실패 > " + e.toString());
	    }

	    if (flag) {
	        try {
	            // member 테이블에서 해당 아이디의 점수를 가져오기 위한 SQL 쿼리문
	            String getScoreQuery = "SELECT id, score FROM member WHERE id = '" + id + "'";
	            ResultSet result = stmt.executeQuery(getScoreQuery);

	            // 결과가 있으면 해당 아이디의 점수를 가져와 score 변수에 저장
	            int score = 0;
	            if (result.next()) {
	                score = result.getInt("score");
	            }
	         // 로그인 성공 시, GUI를 열어줍니다.
	          

	            // 사용자로부터 입력 받은 값을 기존 점수에 더한 값을 업데이트하는 SQL 쿼리문
	            Scanner sc = new Scanner(System.in);
	            System.out.println("현재 점수: " + score);
	            System.out.print("추가할 점수 입력: ");
	            int addedScore = sc.nextInt();
	            int updatedScore = score + addedScore;

	            String updateScoreQuery = "UPDATE member SET score = " + updatedScore + " WHERE id = '" + id + "'";
	            stmt.executeUpdate(updateScoreQuery);

	            System.out.println("점수가 업데이트되었습니다. 현재 점수: " + updatedScore);
	        } catch (SQLException e) {
	            System.out.println("SQL Exception: " + e.getMessage());
	        }
	    }
	    return flag;
	}
//	boolean logincheck(String _n, String _i, String _p) {
//	    boolean flag = false;
//
//	    String id = _i;
//	    String pw = _p;
//
//	    try {
//	        String checkingStr = "SELECT password FROM member WHERE id='" + id + "'";
//	        ResultSet result = stmt.executeQuery(checkingStr);
//
//	        while (result.next()) {
//	            if (pw.equals(result.getString("password"))) {
//	                flag = true;
//	                System.out.println("로그인 성공");
//	                // 로그인 성공 시, 다른 GUI를 열어주는 코드를 추가합니다.
//	              
//	            } else {
//	                flag = false;
//	                System.out.println("로그인 실패");
//	            }
//	        }
//	    } catch (Exception e) {
//	        flag = false;
//	        System.out.println("로그인 실패 > " + e.toString());
//	    }
//
//	    if (flag) {
//	        try {
//	            // member 테이블에서 해당 아이디의 점수를 가져오기 위한 SQL 쿼리문
//	            String getScoreQuery = "SELECT id, score FROM member WHERE id = '" + id + "'";
//	            ResultSet result = stmt.executeQuery(getScoreQuery);
//
//	            // 결과가 있으면 해당 아이디의 점수를 가져와 score 변수에 저장
//	            int score = 0;
//	            if (result.next()) {
//	                score = result.getInt("score");
//	            }
//
//	            // 사용자로부터 입력 받은 값을 기존 점수에 더한 값을 업데이트하는 SQL 쿼리문
//	            Scanner sc = new Scanner(System.in);
//	            System.out.println("현재 점수: " + score);
//	            System.out.print("추가할 점수 입력: ");
//	            int addedScore = sc.nextInt();
//	            int updatedScore = score + addedScore;
//
//	            String updateScoreQuery = "UPDATE member SET score = " + updatedScore + " WHERE id = '" + id + "'";
//	            stmt.executeUpdate(updateScoreQuery);
//
//	            System.out.println("점수가 업데이트되었습니다. 현재 점수: " + updatedScore);
//	        } catch (SQLException e) {
//	            System.out.println("SQL Exception: " + e.getMessage());
//	        }
//	    }
//	    return flag;
//	}
//	boolean logincheck(String _n, String _i, String _p) {
//	    boolean flag = false;
//
//	    String id = _i;
//	    String pw = _p;
//
//	    try {
//	        String checkingStr = "SELECT password FROM member WHERE id='" + id + "'";
//	        ResultSet result = stmt.executeQuery(checkingStr);
//
//	        while (result.next()) {
//	            if (pw.equals(result.getString("password"))) {
//	                flag = true;
//	                System.out.println("로그인 성공");
//	            } else {
//	                flag = false;
//	                System.out.println("로그인 실패");
//	            }
//	        }
//	    } catch (Exception e) {
//	        flag = false;
//	        System.out.println("로그인 실패 > " + e.toString());
//	    }
//
//	    if (flag) {
//	        try {
//	            // member 테이블에서 해당 아이디의 점수를 가져오기 위한 SQL 쿼리문
//	            String getScoreQuery = "SELECT id, score FROM member WHERE id = '" + id + "'";
//	            ResultSet result = stmt.executeQuery(getScoreQuery);
//
//	            // 결과가 있으면 해당 아이디의 점수를 가져와 score 변수에 저장
//	            int score = 0;
//	            if (result.next()) {
//	                score = result.getInt("score");
//	            }
//
//	            // 사용자로부터 입력 받은 값을 기존 점수에 더한 값을 업데이트하는 SQL 쿼리문
//	            Scanner sc = new Scanner(System.in);
//	            System.out.println("현재 점수: " + score);
//	            System.out.print("추가할 점수 입력: ");
//	            int addedScore = sc.nextInt();
//	            int updatedScore = score + addedScore;
//
//	            String updateScoreQuery = "UPDATE member SET score = " + updatedScore + " WHERE id = '" + id + "'";
//	            stmt.executeUpdate(updateScoreQuery);
//
//	            System.out.println("점수가 업데이트되었습니다. 현재 점수: " + updatedScore);
//	        } catch (SQLException e) {
//	            System.out.println("SQL Exception: " + e.getMessage());
//	        }
//	    }
//	    return flag;
//	}




	boolean joinCheck(String _n, String _i, String _p, String _s) {
		boolean flag = false;

		String nm = _n;
		String id = _i;
		String pw = _p;
		String sc = _s;

		try {
			String insertStr = "INSERT INTO member VALUES('" + nm + "','" + id + "', '" + pw + "', '" + sc + "')";
			stmt.executeUpdate(insertStr);

			flag = true;
			System.out.println("회원가입 성공");
		} catch(Exception e) {
			flag = false;
			System.out.println("회원가입 실패 > " + e.toString());
		}

		return flag;
	}






	boolean updatePassword(String _n,String _i,String _p) {
		boolean flag = false;

		String nm = _n;
		String id = _i;
		String pw = _p;

		try {
			stmt = con.prepareStatement("UPDATE member SET password =? WHERE name = ?");

			((PreparedStatement) stmt).setString(1,pw);
			((PreparedStatement) stmt).setString(2,nm);
			((PreparedStatement) stmt).executeUpdate();

			flag = true;
			System.out.println("비밀번호 변경 성공");
		} catch(Exception e) {
			flag = false;
			System.out.println("비밀번호 변경 실패 > " + e.toString());
		}

		return flag;

	}



	boolean deleteInformation(String _n,String _i,String _p) {
		boolean flag = false;

		String nm = _n;
		String id = _i;
		String pw = _p;

		try {
			stmt = con.prepareStatement("Delete From member WHERE name = ? AND id =? AND password =?" );

			((PreparedStatement) stmt).setString(1,nm);
			((PreparedStatement) stmt).setString(2,id);
			((PreparedStatement) stmt).setString(3,pw);
			((PreparedStatement) stmt).executeUpdate();

			flag = true;
			System.out.println("회원정보 삭제");

		} catch(Exception e) {
			flag = false;
			System.out.println("회원정보 삭제불가 > " + e.toString());
		}

		return flag;

	}
}




