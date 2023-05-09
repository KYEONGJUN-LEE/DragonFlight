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
	                System.out.println("�α��� ����");
	            } else {
	                flag = false;
	                System.out.println("�α��� ����");
	            }
	        }
	    } catch (Exception e) {
	        flag = false;
	        System.out.println("�α��� ���� > " + e.toString());
	    }

	    if (flag) {
	        try {
	            // member ���̺��� �ش� ���̵��� ������ �������� ���� SQL ������
	            String getScoreQuery = "SELECT id, score FROM member WHERE id = '" + id + "'";
	            ResultSet result = stmt.executeQuery(getScoreQuery);

	            // ����� ������ �ش� ���̵��� ������ ������ score ������ ����
	            int score = 0;
	            if (result.next()) {
	                score = result.getInt("score");
	            }
	         // �α��� ���� ��, GUI�� �����ݴϴ�.
	          

	            // ����ڷκ��� �Է� ���� ���� ���� ������ ���� ���� ������Ʈ�ϴ� SQL ������
	            Scanner sc = new Scanner(System.in);
	            System.out.println("���� ����: " + score);
	            System.out.print("�߰��� ���� �Է�: ");
	            int addedScore = sc.nextInt();
	            int updatedScore = score + addedScore;

	            String updateScoreQuery = "UPDATE member SET score = " + updatedScore + " WHERE id = '" + id + "'";
	            stmt.executeUpdate(updateScoreQuery);

	            System.out.println("������ ������Ʈ�Ǿ����ϴ�. ���� ����: " + updatedScore);
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
//	                System.out.println("�α��� ����");
//	                // �α��� ���� ��, �ٸ� GUI�� �����ִ� �ڵ带 �߰��մϴ�.
//	              
//	            } else {
//	                flag = false;
//	                System.out.println("�α��� ����");
//	            }
//	        }
//	    } catch (Exception e) {
//	        flag = false;
//	        System.out.println("�α��� ���� > " + e.toString());
//	    }
//
//	    if (flag) {
//	        try {
//	            // member ���̺��� �ش� ���̵��� ������ �������� ���� SQL ������
//	            String getScoreQuery = "SELECT id, score FROM member WHERE id = '" + id + "'";
//	            ResultSet result = stmt.executeQuery(getScoreQuery);
//
//	            // ����� ������ �ش� ���̵��� ������ ������ score ������ ����
//	            int score = 0;
//	            if (result.next()) {
//	                score = result.getInt("score");
//	            }
//
//	            // ����ڷκ��� �Է� ���� ���� ���� ������ ���� ���� ������Ʈ�ϴ� SQL ������
//	            Scanner sc = new Scanner(System.in);
//	            System.out.println("���� ����: " + score);
//	            System.out.print("�߰��� ���� �Է�: ");
//	            int addedScore = sc.nextInt();
//	            int updatedScore = score + addedScore;
//
//	            String updateScoreQuery = "UPDATE member SET score = " + updatedScore + " WHERE id = '" + id + "'";
//	            stmt.executeUpdate(updateScoreQuery);
//
//	            System.out.println("������ ������Ʈ�Ǿ����ϴ�. ���� ����: " + updatedScore);
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
//	                System.out.println("�α��� ����");
//	            } else {
//	                flag = false;
//	                System.out.println("�α��� ����");
//	            }
//	        }
//	    } catch (Exception e) {
//	        flag = false;
//	        System.out.println("�α��� ���� > " + e.toString());
//	    }
//
//	    if (flag) {
//	        try {
//	            // member ���̺��� �ش� ���̵��� ������ �������� ���� SQL ������
//	            String getScoreQuery = "SELECT id, score FROM member WHERE id = '" + id + "'";
//	            ResultSet result = stmt.executeQuery(getScoreQuery);
//
//	            // ����� ������ �ش� ���̵��� ������ ������ score ������ ����
//	            int score = 0;
//	            if (result.next()) {
//	                score = result.getInt("score");
//	            }
//
//	            // ����ڷκ��� �Է� ���� ���� ���� ������ ���� ���� ������Ʈ�ϴ� SQL ������
//	            Scanner sc = new Scanner(System.in);
//	            System.out.println("���� ����: " + score);
//	            System.out.print("�߰��� ���� �Է�: ");
//	            int addedScore = sc.nextInt();
//	            int updatedScore = score + addedScore;
//
//	            String updateScoreQuery = "UPDATE member SET score = " + updatedScore + " WHERE id = '" + id + "'";
//	            stmt.executeUpdate(updateScoreQuery);
//
//	            System.out.println("������ ������Ʈ�Ǿ����ϴ�. ���� ����: " + updatedScore);
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
			System.out.println("ȸ������ ����");

		} catch(Exception e) {
			flag = false;
			System.out.println("ȸ������ �����Ұ� > " + e.toString());
		}

		return flag;

	}
}




