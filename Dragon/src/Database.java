import java.sql.*;
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

	/* 로그인 정보를 확인 */
	boolean logincheck(String _n,String _i, String _p) {
		boolean flag = false;

		String nm = _n;
		String id = _i;
		String pw = _p;

		try {
			String checkingStr = "SELECT password FROM member WHERE id='" + id + "'";
			ResultSet result = stmt.executeQuery(checkingStr);

			int count = 0;
			while(result.next()) {
				if(pw.equals(result.getString("password"))) {
					flag = true;
					System.out.println("로그인 성공");
				}

				else {
					flag = false;
					System.out.println("로그인 실패");
				}
				count++;
			}
		} catch(Exception e) {
			flag = false;
			System.out.println("로그인 실패 > " + e.toString());
		}

		return flag;
	}
	boolean joinCheck(String _n, String _i, String _p) {
		boolean flag = false;

		String nm = _n;
		String id = _i;
		String pw = _p;

		try {
			String insertStr = "INSERT INTO member VALUES('" + nm + "','" + id + "', '" + pw + "')";
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
}




