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
	JLabel nmL = new JLabel("�̸�");
	JLabel idL = new JLabel("���̵�");
	JLabel pwL = new JLabel("��й�ȣ");

	/* TextField */
	JTextField nm = new JTextField();
	JTextField id = new JTextField();
	JPasswordField pw = new JPasswordField();

	/* Button */
	JButton changeBtn = new JButton("����");
	JButton cancelBtn = new JButton("�������");

	Operator o = null;

	ChangeFrame(Operator _o) {
		o = _o;

		setTitle("ȸ����������");

		/* Label ũ�� �۾� */
		nmL.setPreferredSize(new Dimension(50, 30));
		idL.setPreferredSize(new Dimension(50, 30));
		pwL.setPreferredSize(new Dimension(50, 30));

		/* TextField ũ�� �۾� */
		nm.setPreferredSize(new Dimension(140, 30));
		id.setPreferredSize(new Dimension(140, 30));
		pw.setPreferredSize(new Dimension(140, 30));

		/* Button ũ�� �۾� */
		changeBtn.setPreferredSize(new Dimension(95, 25));
		cancelBtn.setPreferredSize(new Dimension(95, 25));

		/* Panel �߰� �۾� */
		setContentPane(panel);

		panel.add(nmL);
		panel.add(nm);

		panel.add(idL);
		panel.add(id);

		panel.add(pwL);
		panel.add(pw);

		panel.add(cancelBtn);
		panel.add(changeBtn);

		/* Button �̺�Ʈ ������ �߰� */
		ButtonListener bl = new ButtonListener();

		cancelBtn.addActionListener(bl);
		changeBtn.addActionListener(bl);

		setSize(250, 190);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	/* Button �̺�Ʈ ������ */
	class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();

			/* TextField�� �Էµ� ȸ�� �������� ������ �ʱ�ȭ */
			String unm = nm.getText();
			String uid = id.getText();
			String upass = "";
			for(int i=0; i<pw.getPassword().length; i++) {
				upass = upass + pw.getPassword()[i];
			}		

			/* ������� ��ư �̺�Ʈ */
			if(b.getText().equals("�������")) {
				dispose();
			}
			/* ������� ��ư �̺�Ʈ */
			else if(b.getText().equals("����")){
				if(!unm.equals("") && uid.equals("") || upass.equals(""))  {
					JOptionPane.showMessageDialog(null, "��� ������ �Է����ּ���", "���� ����", JOptionPane.ERROR_MESSAGE);
					System.out.println("���� ���� > ���� ���Է�");
				}
				else if(!unm.equals("") &&!uid.equals("") && !upass.equals(""))  {
					if(o.db.updatePassword(unm,uid,upass)) { // �����ͺ��̽����� Ȯ��
						System.out.println("����");
						JOptionPane.showMessageDialog(null, "��й�ȣ ���濡 �����Ͽ����ϴ�");
						dispose();
					}
					else {
						System.out.println("����");
						JOptionPane.showMessageDialog(null, "��й�ȣ ���濡 �����Ͽ����ϴ�");
						

					}

				}

			}

		}
	}

}

