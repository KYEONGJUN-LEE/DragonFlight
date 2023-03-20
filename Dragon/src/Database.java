import java.sql.*;
public class Database {
	Connection con = null;
	Statement stmt = null;
	String url = "jdbc:mysql://localhost/login?serverTimezone=Asia/Seoul";	//login ��Ű��
	String user = "root";
	String passwd = "mite";		//������ ������ root ������ ��й�ȣ�� �Է��ϸ� �ȴ�.

	Database() {	//�����ͺ��̽��� �����Ѵ�.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, passwd);
			stmt = con.createStatement();
			System.out.println("MySQL ���� ���� ����");
		} catch(Exception e) {
			System.out.println("MySQL ���� ���� ���� > " + e.toString());
		}
	}

	/* �α��� ������ Ȯ�� */
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
					System.out.println("�α��� ����");
				}

				else {
					flag = false;
					System.out.println("�α��� ����");
				}
				count++;
			}
		} catch(Exception e) {
			flag = false;
			System.out.println("�α��� ���� > " + e.toString());
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
			System.out.println("ȸ������ ����");
		} catch(Exception e) {
			flag = false;
			System.out.println("ȸ������ ���� > " + e.toString());
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
			System.out.println("��й�ȣ ���� ����");
		} catch(Exception e) {
			flag = false;
			System.out.println("��й�ȣ ���� ���� > " + e.toString());
		}

		return flag;

	}
}




