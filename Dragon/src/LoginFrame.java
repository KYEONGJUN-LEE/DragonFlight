import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
	/* Panel */
	JPanel basePanel = new JPanel(new BorderLayout());
	JPanel centerPanel = new JPanel(new BorderLayout());
	JPanel westPanel = new JPanel();
	JPanel eastPanel = new JPanel();
	JPanel southPanel = new JPanel();
	
	/* Label */
	JLabel idL = new JLabel("아이디");
	JLabel pwL = new JLabel("비밀번호");
	
	/* TextField */
	JTextField id = new JTextField();
	JPasswordField pw = new JPasswordField();
	
	/* Button */
	JButton loginBtn = new JButton("로그인");
	JButton joinBtn = new JButton("회원가입");
	JButton exitBtn = new JButton("프로그램 종료");
	JButton changeBtn = new JButton("회원정보변경");
	JButton deleteBtn = new JButton("회원정보삭제");
	
	Operator o = null;
	
	LoginFrame(Operator _o){
		o = _o;
		
		setTitle("로그인");
		
		/* Panel 크기 작업 */
		centerPanel.setPreferredSize(new Dimension(260, 80));
		westPanel.setPreferredSize(new Dimension(210, 75));
		eastPanel.setPreferredSize(new Dimension(90, 75));
		southPanel.setPreferredSize(new Dimension(290, 65));
		
		/* Label 크기 작업 */
		idL.setPreferredSize(new Dimension(50, 30));
		pwL.setPreferredSize(new Dimension(50, 30));
		
		/* TextField 크기 작업 */
		id.setPreferredSize(new Dimension(150, 30));
		pw.setPreferredSize(new Dimension(150, 30));
		
		/* Button 크기 작업 */
		loginBtn.setPreferredSize(new Dimension(75, 63));
		joinBtn.setPreferredSize(new Dimension(135, 25));
		exitBtn.setPreferredSize(new Dimension(135, 25));
		changeBtn.setPreferredSize(new Dimension(135, 25));
		deleteBtn.setPreferredSize(new Dimension(135, 25));
		
		/* Panel 추가 작업 */
		setContentPane(basePanel);	//panel을 기본 컨테이너로 설정
		
		basePanel.add(centerPanel, BorderLayout.CENTER);
		basePanel.add(southPanel, BorderLayout.SOUTH);
		centerPanel.add(westPanel, BorderLayout.WEST);
		centerPanel.add(eastPanel, BorderLayout.EAST);
		
		westPanel.setLayout(new FlowLayout());
		eastPanel.setLayout(new FlowLayout());
		southPanel.setLayout(new FlowLayout());
		
		/* westPanel 컴포넌트 */
		westPanel.add(idL);
		westPanel.add(id);
		westPanel.add(pwL);
		westPanel.add(pw);
		
		
		/* eastPanel 컴포넌트 */
		eastPanel.add(loginBtn);
		
		/* southPanel 컴포넌트 */
		southPanel.add(exitBtn);
		southPanel.add(joinBtn);
		southPanel.add(changeBtn);
		southPanel.add(deleteBtn);
		
		
		/* Button 이벤트 리스너 추가 */
		ButtonListener bl = new ButtonListener();
		ButtonListener b2 = new ButtonListener();
		ButtonListener b3 = new ButtonListener();
		ButtonListener b4 = new ButtonListener();
		ButtonListener b5 = new ButtonListener();
		
		loginBtn.addActionListener(bl);
		exitBtn.addActionListener(b2);
		joinBtn.addActionListener(b3);
		changeBtn.addActionListener(b4);
		deleteBtn.addActionListener(b5);
		
		
		setSize(320, 175);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/* Button 이벤트 리스너 */
	class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b1 = (JButton)e.getSource();
			JButton b2 = (JButton)e.getSource();
			JButton b3 = (JButton)e.getSource();
			JButton b4 = (JButton)e.getSource();
			JButton b5 = (JButton)e.getSource();
			
			
			
			/* TextField에 입력된 아이디와 비밀번호를 변수에 초기화 */
			String unm = id.getText();
			String uid = id.getText();
			String upass = "";
			for(int i=0; i<pw.getPassword().length; i++) {
				upass = upass + pw.getPassword()[i];
			}
			
			/* 게임종료 버튼 이벤트 */
			if(b2.getText().equals("프로그램 종료")) {
				System.out.println("프로그램 종료");
				System.exit(0);
			}
			
			/* 회원가입 버튼 이벤트 */
			else if(b3.getText().equals("회원가입")) {
				o.jf.setVisible(true);
			}
			/* 회원정보변경 버튼 이벤트 */
			else if(b4.getText().equals("회원정보변경")) {
				o.cf.setVisible(true);
			}
			else if(b5.getText().equals("회원정보삭제")) {
				o.df.setVisible(true);
			}
			
			/* 로그인 버튼 이벤트 */
			else if(b1.getText().equals("로그인")) {
				if(unm.equals("") || uid.equals("") || upass.equals("")) {
					JOptionPane.showMessageDialog(null, "아이디와 비밀번호 모두 입력해주세요", "로그인 실패", JOptionPane.ERROR_MESSAGE);
					System.out.println("로그인 실패 > 로그인 정보 미입력");
					setVisible(false);
				}
				
				else if(unm != null && uid != null && upass != null) {
					if(o.db.logincheck(unm, uid, upass)) {	//이 부분이 데이터베이스에 접속해 로그인 정보를 확인하는 부분이다.
//						System.out.println("로그인 성공");
						JOptionPane.showMessageDialog(null, "로그인에 성공하였습니다");
						new DragonFlight();
						setVisible(false);
						
						
					} else {
						System.out.println("로그인 실패 > 로그인 정보 불일치");
						JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다");
						setVisible(false);
					}
				}
			}
		}
	}
}