import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;


import java.awt.event.*;

public class ChangeFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* Panel */
	JPanel panel = new JPanel();

	/* Label */
	JLabel nmL = new JLabel("이름");
	JLabel idL = new JLabel("아이디");
	JLabel pwL = new JLabel("비밀번호");

	/* TextField */
	JTextField nm = new JTextField();
	JTextField id = new JTextField();
	JPasswordField pw = new JPasswordField();

	/* Button */
	JButton changeBtn = new JButton("변경");
	JButton cancelBtn = new JButton("변경취소");

	Operator o = null;

	ChangeFrame(Operator _o) {
		o = _o;

		setTitle("회원정보변경");

		/* Label 크기 작업 */
		nmL.setPreferredSize(new Dimension(50, 30));
		idL.setPreferredSize(new Dimension(50, 30));
		pwL.setPreferredSize(new Dimension(50, 30));

		/* TextField 크기 작업 */
		nm.setPreferredSize(new Dimension(140, 30));
		id.setPreferredSize(new Dimension(140, 30));
		pw.setPreferredSize(new Dimension(140, 30));

		/* Button 크기 작업 */
		changeBtn.setPreferredSize(new Dimension(95, 25));
		cancelBtn.setPreferredSize(new Dimension(95, 25));

		/* Panel 추가 작업 */
		setContentPane(panel);

		panel.add(nmL);
		panel.add(nm);

		panel.add(idL);
		panel.add(id);

		panel.add(pwL);
		panel.add(pw);

		panel.add(cancelBtn);
		panel.add(changeBtn);

		/* Button 이벤트 리스너 추가 */
		ButtonListener bl = new ButtonListener();

		cancelBtn.addActionListener(bl);
		changeBtn.addActionListener(bl);

		setSize(250, 190);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	/* Button 이벤트 리스너 */
	class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();

			/* TextField에 입력된 회원 정보들을 변수에 초기화 */
			String unm = nm.getText();
			String uid = id.getText();
			String upass = "";
			for(int i=0; i<pw.getPassword().length; i++) {
				upass = upass + pw.getPassword()[i];
			}		

			/* 변경취소 버튼 이벤트 */
			if(b.getText().equals("변경취소")) {
				dispose();
			}
			/* 변경취소 버튼 이벤트 */
			else if(b.getText().equals("변경")){
				if(!unm.equals("") && uid.equals("") || upass.equals(""))  {
					JOptionPane.showMessageDialog(null, "모든 정보를 입력해주세요", "변경 실패", JOptionPane.ERROR_MESSAGE);
					System.out.println("변경 실패 > 정보 미입력");
				}
				else if(!unm.equals("") &&!uid.equals("") && !upass.equals(""))  {
					if(o.db.updatePassword(unm,uid,upass)) { // 데이터베이스에서 확인
						System.out.println("성공");
						JOptionPane.showMessageDialog(null, "비밀번호 변경에 성공하였습니다");
						dispose();
					}
					else {
						System.out.println("실패");
						JOptionPane.showMessageDialog(null, "비밀번호 변경에 실패하였습니다");
						

					}

				}

			}

		}
	}

}

