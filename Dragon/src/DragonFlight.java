import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Pokemon.Pijeon;
import Pokemon.Mang2;
import Pokemon.Freeja;



public class DragonFlight extends JFrame {
	
	Database db = null;
	LoginFrame lf = null;
	JoinFrame jf = null;
	Operator o = null;
	
	DrawPanel panel;
	
	static String player_name; // �÷��̾� �̸� ����
	JLabel nameLabel; // ȭ�鳻�� �̸� ��
	JLabel scoreLabel; // ȭ�鳻�� ���� ��
	JLabel levelLabel; // ȭ�鳻�� ���� ��
	

	Timer t; // ����� Ÿ�̸�
	Timer at; // ���� Ÿ�̸�
	Timer at2;

	int INDEX = 3; // ����� ī��Ʈ
	int CountE = 0; // ����Ű Ƚ�� ī��Ʈ
	int item;

	int score = 1; // ���� ī��Ʈ
	int level = 1; // ���� ī��Ʈ

	int count = 0; // Ÿ�̸� ī��Ʈ

	// �̹�������
	Image mainScreen = new ImageIcon(getClass().getClassLoader().getResource("image/main_Screen.png")).getImage(); // ���ӽ���ȭ��	
	Image explainScreen = new ImageIcon(getClass().getClassLoader().getResource("image/loading_screen.png")).getImage(); // ����ȭ��
	Image explainScreen2 = new ImageIcon(getClass().getClassLoader().getResource("image/loading_screen2.png")).getImage(); // ����ȭ��
	Image gameScreen = new ImageIcon(getClass().getClassLoader().getResource("image/main_Screen1.png")).getImage(); // ����ȭ��		
	Image endScreen = new ImageIcon(getClass().getClassLoader().getResource("image/end_screen.png")).getImage(); // ���� ����
	Image endScreen2 = new ImageIcon(getClass().getClassLoader().getResource("image/game_clear.png")).getImage(); // ���� ����
	Image player = new ImageIcon(getClass().getClassLoader().getResource("image/Ree2.gif")).getImage(); // �������ڸ�
	Image player2 = new ImageIcon(getClass().getClassLoader().getResource("image/R2.png")).getImage(); // �������ڸ�
	Image Fire = new ImageIcon(getClass().getClassLoader().getResource("image/Fire.png")).getImage(); // ��
	Image Fire2 = new ImageIcon(getClass().getClassLoader().getResource("image/�Ķ���.png")).getImage(); // ��
	Image enemy1 = new ImageIcon(getClass().getClassLoader().getResource("image/����.gif")).getImage(); // ����
	Image enemy2 = new ImageIcon(getClass().getClassLoader().getResource("image/������.gif")).getImage(); // ������
	Image enemy3 = new ImageIcon(getClass().getClassLoader().getResource("image/������.gif")).getImage(); // ������
	Image heart1 = new ImageIcon(getClass().getClassLoader().getResource("image/HEART.png")).getImage(); // ��Ʈ
	Image[] itemList = { new ImageIcon(getClass().getClassLoader().getResource("image/HEART1.png")).getImage(), // ������ ��Ʈ
			new ImageIcon(getClass().getClassLoader().getResource("image/stone.png")).getImage() }; // ������ ����

	private Image bImage;
	private Graphics screenGraphics;

	private boolean isMainScreen, isExplainScreen, isExplainScreen2, isGameScreen, isFinishScreen, isFinishScreen2; // ȭ����ȯ�� ���� boolean �ڷ���
																													 
																													
																													
																													 
	private boolean isplayer, isplayer1; // �÷��̾� ������ ���� �ڷ��� boolean

	static int DRAGON_WIDTH = 100; // �巡�� ����ũ��
	static int DRAGON_HEIGHT = 120; // �巡�� ����ũ��
	static int PANEL_WIDTH = 800; // �г� ���� ũ��
	static int PANEL_HEIGHT = 900; // �г� ���� ũ��

	static int FRAME_WIDTH = PANEL_WIDTH; // ����������� �г�ũ��� ������ �ϱ�
	static int FRAME_HEIGHT = PANEL_HEIGHT;

	ArrayList<Dragon> listAP = new ArrayList<Dragon>(); // �巡�� ��ü
	ArrayList<Attack> listAT = new ArrayList<Attack>(); // ���� ��ü
	ArrayList<Attack2> listAT2 = new ArrayList<Attack2>(); // ���� ��ü �ι�° �Ҳ�
	ArrayList<MAttack2> listAM2 = new ArrayList<MAttack2>(); // ���� �����ϴ� ��ü
	ArrayList<MAttack3> listAM3 = new ArrayList<MAttack3>(); // ���� �����ϴ� ��ü
	ArrayList<Pijeon> listM1 = new ArrayList<Pijeon>();// ��1 ��ü
	ArrayList<Mang2> listM2 = new ArrayList<Mang2>();// ��2 ��ü
	ArrayList<Freeja> listM3 = new ArrayList<Freeja>();// ��3 ��ü

	ArrayList<Item> listI = new ArrayList<Item>(); // ������ ��ü
	ArrayList<Heart> listH = new ArrayList<Heart>(); // ��Ʈ ��ü


	// ���� ��ü
	ArrayList<Attack> ATRemove = new ArrayList<>();
	ArrayList<Attack2> AT2Remove = new ArrayList<>();
	ArrayList<Dragon> AP1Remove = new ArrayList<>();
	ArrayList<MAttack2> MARemove2 = new ArrayList<>();
	ArrayList<MAttack3> MARemove3 = new ArrayList<>();
	ArrayList<Pijeon> MRemove1 = new ArrayList<>();
	ArrayList<Mang2> MRemove2 = new ArrayList<>();
	ArrayList<Freeja> MRemove3 = new ArrayList<>();

	ArrayList<Item> IRemove = new ArrayList<>();
	ArrayList<Heart> HRemove = new ArrayList<>();

	// Ű���� ������ �ڿ������� �ϱ� ����
	boolean moveUp = false;
	boolean moveDown = false;
	boolean moveRight = false;
	boolean moveLeft = false;
	boolean checkSpace = false;
	boolean checkShift = false;

	// ������ - Frame �⺻����, �г� �߰�
	DragonFlight() {


		// Ÿ�̸� ����
		t = new Timer(10, new Draw());
		at = new Timer(100, new MakeA());
		at2 = new Timer(100, new MakeB());

		// �г�
		panel = new DrawPanel();
		panel.addKeyListener(panel);

		nameLabel = new JLabel("NAME: " + player_name);
		scoreLabel = new JLabel("SCORE: " + score);
		levelLabel = new JLabel("LEVEL: " + level);

		// �� �۾�ü
		nameLabel.setFont(new Font("FTLAB Hoony", Font.BOLD, 20));
		scoreLabel.setFont(new Font("FTLAB Hoony", Font.BOLD, 20));
		levelLabel.setFont(new Font("FTLAB Hoony", Font.BOLD, 20));

		panel.setLayout(null);

		// ��ġ ����//setBounds(x,y,����,����) ������ġ
		nameLabel.setBounds(600, 800, 150, 50); // �̸�
		scoreLabel.setBounds(450, 800, 150, 50); // ����
		levelLabel.setBounds(600, 750, 150, 50); // ����

		// ��� ����
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		levelLabel.setHorizontalAlignment(JLabel.CENTER);

		// �� ����
		nameLabel.setForeground(Color.white);
		scoreLabel.setForeground(Color.white);
		levelLabel.setForeground(Color.white);

		this.add(panel);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT); // �����ӻ����� ���ϱ�
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("REZAMONG");
		this.setResizable(false);// ũ������ ������� ���ϰ�

		init(); // boolean���� ���� mainScreen�׷��ֱ�
//		name();// �̸� ����
		Dragoninit(); // �巡�� ����

	}

	// boolean���� ���� ȭ��׷��ֱ�
	public void init() {
		isMainScreen = true;
		isExplainScreen = false;
		isExplainScreen2 = false;
		isGameScreen = false;
		isFinishScreen = false;
		isFinishScreen2 = false;
	}
	// �巡���� ��ü ��ȯ�� ���� ó�� ��ü�� true, �ι�° ��ü�� false
	public void Dragoninit() {
		isplayer = true;
		isplayer1 = false;

	}


	// ȭ�� ��ȯ
	public void screenDraw(Graphics g) {
		if (isMainScreen == true) {
			g.drawImage(mainScreen, 0, 0, null);
		}

		if (isExplainScreen == true) {
			g.drawImage(explainScreen, 0, 0, null);
		}
		if (isExplainScreen2 == true) {
			g.drawImage(explainScreen2, 0, 0, null);
		}

		if (isGameScreen == true) {
			g.drawImage(gameScreen, 0, 0, null);
		}
		if (isFinishScreen == true) {
			g.drawImage(endScreen, 0, 0, null);
		}

		if (isFinishScreen2 == true) {
			g.drawImage(endScreen2, 0, 0, null);
		}
	}



	class Draw implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			repaint(); // �ٽ� �׷��ٶ�����
			count++;// count++
			int x;
			int y;

			// �巡���� ������
			for (Dragon ap : listAP) {
				// ����Ű�� ������ ����
				if (moveUp == true) {
					if (ap.y - 10 > 0)
						ap.moveUP();
				} else if (moveDown == true) {
					if (ap.y + DRAGON_HEIGHT + 68 < PANEL_HEIGHT)
						ap.moveDOWN();
				} else if (moveRight == true) {
					if (ap.x + DRAGON_WIDTH + 55 < PANEL_WIDTH)
						ap.moveRIGHT();
				} else if (moveLeft == true) {
					if (ap.x > 0)
						ap.moveLEFT();
				}

				if (checkSpace == true) { // �����̽��ٸ� ���� ����
					at.start();
					
				} else if (checkSpace == false) {
					at.stop();

				}
				if (checkShift == true) { // �����̽��ٸ� ���� ����
					at2.start();

				} else if (checkShift == false) {
					at2.stop();

				}

			}

			// 3���̻� ���� 2, 7���̻� ����3
			if (score >= 0)
				level = 1;
			if (score >= 3)
				level = 2;
			if (score >= 7)
				level = 3;
			
			// �����߰�
			if (count % 100 == 0) {
				x = (int) (Math.random() * 700);
				listM1.add(new Pijeon(x, 0, enemy1));
			} 
			//�������߰�
			if (count % 200 == 0 && level > 1) {
				x = (int) (Math.random() * PANEL_WIDTH);
				y = (int) (Math.random() * PANEL_HEIGHT);
				listM2.add(new Mang2(x, y, enemy2));
			}
			//�������߰�
			if (count % 250 == 0 && level > 2) {
				x = (int) (Math.random() * PANEL_HEIGHT);
				y = (int) (Math.random() * PANEL_WIDTH);
				listM3.add(new Freeja(x, y, enemy3));
			}

			// ���Ͱ� �ϴ� ���� list�߰�
			if (count % 200 == 0 && CountE > 1) {
				
				if (level > 1) { // ����2�̸� ������ �߰�
					for (Mang2 M2 : listM2) {
						x = M2.getX();
						y = M2.getY();
						listAM2.add(new MAttack2(x, y, Color.yellow));
					}

				}
				if (level > 2) { // ����3�̸� ������ �߰�
					for (Freeja M3 : listM3) {
						x = M3.getX();
						y = M3.getY();
						listAM3.add(new MAttack3(x, y, Color.blue));
					}

				}
			}
		}
	}

	class MakeA implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int gX;
			int gY;
			count++;

			// ���ڸ� �ϴ� ���� list�߰�
			for (Dragon AP : listAP) {
				gX = AP.getX();
				gY = AP.getY();
				listAT.add(new Attack(gX + 10, gY + 39));

			}

		}
	}

	class MakeB implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int gX;
			int gY;
			count++;

			// ���ڸ� �ϴ� ���� list�߰�
			for (Dragon AP : listAP) {
				gX = AP.getX();
				gY = AP.getY();
				listAT2.add(new Attack2(gX + 33, gY - 20));
				

			}

		}
	}

	class DrawPanel extends JPanel implements KeyListener {
		public void paintComponent(Graphics g) {

			bImage = createImage(PANEL_WIDTH, PANEL_HEIGHT); // ȭ�� ä���
			screenGraphics = bImage.getGraphics();
			screenDraw(screenGraphics);
			g.drawImage(bImage, 0, 0, null);

			// ������� 0�� �Ǿ����� ���� ���� ȭ��
			if (INDEX == 0 || score == 0) {
				isGameScreen = false;
				isFinishScreen = true;

				for (Attack a : listAT) {
					ATRemove.add(a);
				}
				for (Attack2 a : listAT2) {
					AT2Remove.add(a);
				}

				for (MAttack2 a : listAM2) {
					MARemove2.add(a);
				}
				for (MAttack3 a : listAM3) {
					MARemove3.add(a);
				}
				for (Pijeon a : listM1) {
					MRemove1.add(a);
				}
				for (Mang2 a : listM2) {
					MRemove2.add(a);
				}
				for (Freeja a : listM3) {
					MRemove3.add(a);
				}
				for (Heart a : listH) {
					HRemove.add(a);
				}
				for (Item a : listI) {
					IRemove.add(a);
				}
				for (Dragon a : listAP) {
					AP1Remove.add(a);
				}

				for (Attack a : ATRemove)
					listAT.remove(a);
				for (Attack2 a : AT2Remove)
					listAT2.remove(a);

				for (Pijeon a : MRemove1)
					listM1.remove(a);
				for (Mang2 a : MRemove2)
					listM2.remove(a);
				for (Freeja a : MRemove3)
					listM3.remove(a);

				for (Item a : IRemove)
					listI.remove(a);

				for (Heart a : HRemove)
					listH.remove(a);
				for (Dragon a : AP1Remove)
					listAP.remove(a);

				nameLabel.setFont(new Font("FTLAB Hoony", Font.BOLD, 30));
				scoreLabel.setFont(new Font("FTLAB Hoony", Font.BOLD, 30));
				levelLabel.setFont(new Font("FTLAB Hoony", Font.BOLD, 30));

				// ��ġ ������ // setBounds(x,y,����,����) ������ġ
				nameLabel.setBounds(450, 600, 250, 100);
				scoreLabel.setBounds(450, 650, 250, 100); 
				levelLabel.setBounds(450, 700, 250, 100);

				nameLabel.setHorizontalAlignment(JLabel.CENTER);
				scoreLabel.setHorizontalAlignment(JLabel.CENTER);
				levelLabel.setHorizontalAlignment(JLabel.CENTER);

				panel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

				t.stop();

				// finishScreen ȭ�� �׷��ֱ�
				g.drawImage(endScreen, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);

			}
			// score15�� �Ǹ� ���� ���� ȭ��
			if (score == 15) {
				isGameScreen = false;
				isFinishScreen2 = true;

				for (Attack a : listAT) {
					ATRemove.add(a);
				}
				for (Attack2 a : listAT2) {
					AT2Remove.add(a);
				}

				for (MAttack2 a : listAM2) {
					MARemove2.add(a);
				}
				for (MAttack3 a : listAM3) {
					MARemove3.add(a);
				}
				for (Pijeon a : listM1) {
					MRemove1.add(a);
				}
				for (Mang2 a : listM2) {
					MRemove2.add(a);
				}
				for (Freeja a : listM3) {
					MRemove3.add(a);
				}
				for (Heart a : listH) {
					HRemove.add(a);
				}
				for (Item a : listI) {
					IRemove.add(a);
				}
				for (Dragon a : listAP) {
					AP1Remove.add(a);
				}

				for (Attack a : ATRemove)
					listAT.remove(a);
				for (Attack2 a : AT2Remove)
					listAT2.remove(a);

				for (Pijeon a : MRemove1)
					listM1.remove(a);
				for (Mang2 a : MRemove2)
					listM2.remove(a);
				for (Freeja a : MRemove3)
					listM3.remove(a);

				for (Item a : IRemove)
					listI.remove(a);

				for (Heart a : HRemove)
					listH.remove(a);
				for (Dragon a : AP1Remove)
					listAP.remove(a);

				nameLabel.setFont(new Font("FTLAB Hoony", Font.BOLD, 30));
				scoreLabel.setFont(new Font("FTLAB Hoony", Font.BOLD, 30));
				levelLabel.setFont(new Font("FTLAB Hoony", Font.BOLD, 30));

				// ��ġ ������
				nameLabel.setBounds(450, 600, 250, 100);
				scoreLabel.setBounds(450, 650, 250, 100); // setBounds(x,y,����,����) ������ġ
				levelLabel.setBounds(450, 700, 250, 100);

				nameLabel.setHorizontalAlignment(JLabel.CENTER);
				scoreLabel.setHorizontalAlignment(JLabel.CENTER);
				levelLabel.setHorizontalAlignment(JLabel.CENTER);
				
				nameLabel.setForeground(Color.black);
				scoreLabel.setForeground(Color.black);
				levelLabel.setForeground(Color.black);

				panel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

				t.stop();

				// finishScreen ȭ�� �׷��ֱ�
				g.drawImage(endScreen2, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);

			}

			// ��ġ ������

			// ��ü ����
			// ��Ʈ �׸� �׸���
			for (Heart h : listH) {
				h.drawH(g);
			}

			for (Attack2 a : listAT2) {
				a.drawFire(g);
			}

			// �巡�� �׸� �׸���
			for (Dragon ap1 : listAP) {
				ap1.draw(g);
			}
			// �巡�� 2���� �׸���
			for (Dragon ap1 : listAP) {
				ap1.draw2(g);
			}

			// ���� �׸��׸���
			for (Attack a : listAT) {
				if (a.getY() < 0)
					ATRemove.add(a);

				if (listAT.isEmpty() != true) {
					// listAT2.clear();
					a.drawFire(g);
					a.moveA();
				}
			}
			for (Attack2 a : listAT2) {
				if (a.getY() < 0)
					AT2Remove.add(a);

				if (listAT2.isEmpty() != true) {
					a.drawFire(g);
					a.moveA();
				}
			}
			// ����������
			if (listAM2.isEmpty() != true)
				for (MAttack2 ma : listAM2) {
					ma.drawMA2(g);
					ma.moveA();
				}
			// ����������
			if (listAM3.isEmpty() != true)
				for (MAttack3 ma : listAM3) {
					ma.drawMA3(g);
					ma.moveA();
				}

			//
			for (Dragon ap1 : listAP) {

				for (MAttack2 ma : listAM2) {// 2��° ���ϸ�
					if (ma.getY() >= ap1.getY() && ma.getX() >= ap1.getX() && ma.getX() <= ap1.getX() + 60
							&& ma.getY() < ap1.getY() + 60) {
						MARemove2.add(ma);
						HRemove.add(listH.get(INDEX - 1));
						INDEX--;
					}

				}
				for (MAttack3 ma : listAM3) {// 3��° ���ϸ�
					if (ma.getY() >= ap1.getY() && ma.getX() >= ap1.getX() && ma.getX() <= ap1.getX() + 50
							&& ma.getY() < ap1.getY() + 50) {
						MARemove3.add(ma);
						HRemove.add(listH.get(INDEX - 1));
						INDEX--;
					}
				}

				// ������ ���ڸ��� ������ �޾�����, hp--, hp=0 -> 3���� Ȯ�� ������ ����, �������� ������ 2����
				for (Pijeon m : listM1) {
					for (Attack a : listAT) {

						if (m.distance(a.pX + 2, a.pY) < 25) {
							ATRemove.add(a);
							m.reduceHp();

							if (m.getHp() <= 0) {
								score++;
								MRemove1.add(m);

								// ������ 3���� 1�� Ȯ���� ����
								int rand = (int) (Math.random() * 3);
								if (rand == 2) {
									int rand2 = (int) (Math.random() * 2);
									if (rand2 == 0)
										listI.add(new Item(m.getX(), m.getY(), rand2, itemList[rand2])); // ��Ʈ �߰�
									else if (rand2 == 1)
										listI.add(new Item(m.getX(), m.getY(), rand2, itemList[rand2])); // ����
								}
							}
						}
					}

				} // �������� ���ڸ��� ������ �޾�����, hp--, hp=0 -> 3���� Ȯ�� ������ ����, �������� ������ 2����
				for (Mang2 m2 : listM2) {
					for (Attack a : listAT) {

						if (m2.distance(a.pX + 2, a.pY) < 18.5) {
							ATRemove.add(a);
							m2.reduceHp();

							if (m2.getHp() <= 0) {
								score++;
								MRemove2.add(m2);

								int rand = (int) (Math.random() * 3); // 0~4
								if (rand == 2) {
									int rand2 = (int) (Math.random() * 3);
									if (rand2 == 0)
										listI.add(new Item(m2.getX(), m2.getY(), rand2, itemList[rand2]));
									else if (rand2 == 1)
										listI.add(new Item(m2.getX(), m2.getY(), rand2, itemList[rand2]));

								}
							}
						}
					}
				} // �������� ���ڸ��� ������ �޾�����, hp--, hp=0 -> 3���� Ȯ�� ������ ����, �������� ������ 2����

				for (Freeja m2 : listM3) {
					for (Attack a : listAT) {

						if (m2.distance(a.pX + 2, a.pY) < 18.5) {
							ATRemove.add(a);
							m2.reduceHp();

							if (m2.getHp() <= 0) {
								score++;
								MRemove3.add(m2);

								int rand = (int) (Math.random() * 3); // 0~4
								if (rand == 2) {
									int rand2 = (int) (Math.random() * 3);
									if (rand2 == 0)
										listI.add(new Item(m2.getX(), m2.getY(), rand2, itemList[rand2]));
									else if (rand2 == 1)
										listI.add(new Item(m2.getX(), m2.getY(), rand2, itemList[rand2]));

								}
							}
						}
					}
				}
				// ������ ���ڸ��� ������ �޾�����, hp--, hp=0 -> 3���� Ȯ�� ������ ����, �������� ������ 2����
				for (Pijeon m : listM1) {
					for (Attack2 a : listAT2) {

						if (m.distance(a.pX + 2, a.pY) < 25) {
							AT2Remove.add(a);
							m.reduceHp();

							if (m.getHp() <= 0) {
								score++;
								MRemove1.add(m);

								// ������ 3���� 1�� Ȯ���� ����
								int rand = (int) (Math.random() * 3);
								if (rand == 2) {
									int rand2 = (int) (Math.random() * 2);
									if (rand2 == 0)
										listI.add(new Item(m.getX(), m.getY(), rand2, itemList[rand2])); // ��Ʈ �߰�
									else if (rand2 == 1)
										listI.add(new Item(m.getX(), m.getY(), rand2, itemList[rand2])); // ����
								}
							}
						}
					}

				} // �������� ���ڸ��� ������ �޾�����, hp--, hp=0 -> 3���� Ȯ�� ������ ����, �������� ������ 2����
				for (Mang2 m2 : listM2) {
					for (Attack2 a : listAT2) {

						if (m2.distance(a.pX + 2, a.pY) < 18.5) {
							AT2Remove.add(a);
							m2.reduceHp();

							if (m2.getHp() <= 0) {
								score=score+2; //�ι�° ���ϸ��� ���ָ� +2��
								MRemove2.add(m2);

								int rand = (int) (Math.random() * 3); // 0~3
								if (rand == 2) {
									int rand2 = (int) (Math.random() * 2);
									if (rand2 == 0)
										listI.add(new Item(m2.getX(), m2.getY(), rand2, itemList[rand2]));
									else if (rand2 == 1)
										listI.add(new Item(m2.getX(), m2.getY(), rand2, itemList[rand2]));

								}
							}
						}
					}
				} // �������� ���ڸ��� ������ �޾�����, hp--, hp=0 -> 3���� Ȯ�� ������ ����, �������� ������ 2����

				for (Freeja m2 : listM3) {
					for (Attack2 a : listAT2) {

						if (m2.distance(a.pX + 2, a.pY) < 18.5) {
							AT2Remove.add(a);
							m2.reduceHp();

							if (m2.getHp() <= 0) {
								score=score+3; // ����° ���ϸ��� ���ָ� +3��
								MRemove3.add(m2);

								int rand = (int) (Math.random() * 3); // 0~3
								if (rand == 2) {
									int rand2 = (int) (Math.random() * 2);
									if (rand2 == 0)
										listI.add(new Item(m2.getX(), m2.getY(), rand2, itemList[rand2]));
									else if (rand2 == 1)
										listI.add(new Item(m2.getX(), m2.getY(), rand2, itemList[rand2]));

								}
							}
						}
					}
				}

				// ������ ����
				for (Item item : listI) {
					item.draw(g);
					item.move();

					// �Ÿ� ���
					if (ap1.distance(item.getXI(), item.getYI()) <= 40) {
						IRemove.add(item);
						if (item.getType() == 0) { // ù��° �������� ����� �߰��ϱ�
							listH.add(new Heart(INDEX));
							if (INDEX < 5)
								INDEX++; // ����� �߰�
						}
					}

					if (ap1.distance(item.getXI(), item.getYI()) <= 40 && item.getType() == 1) {
						isplayer = false; //2�� ������ ������ �÷��̾�(��ü�� �ٲ�.)
						isplayer1 = true;
			
					}
				}

			}

			// �巡��� ���Ͱ� �´����� ���� ����
			for (Dragon AP : listAP) {
				for (Pijeon m : listM1) {
					m.draw(g);
					m.move();
					if (AP.distance(m.getX() + 15, m.getY() + 15) <= 50) {
						MRemove1.add(m);
						score--;
					}
				}
				for (Mang2 m2 : listM2) {
					m2.draw(g);
					m2.move();

					if (AP.distance(m2.getX() + 18, m2.getY() + 18) <= 50) {
						MRemove2.add(m2);
						score=score-2; // 2��° ���ϸ�� ������ -2
					}
				}
				for (Freeja m3 : listM3) {
					m3.draw(g);
					m3.move();

					if (AP.distance(m3.getX() + 20, m3.getY() + 20) <= 50) {
						MRemove3.add(m3);
						score=score-3; // 3��° ���ϸ�� ������ -3
					}
				}
			}

			// score�� level ���ΰ�ħ
			scoreLabel.setText("SCORE: " + score);
			levelLabel.setText("LEVEL: " + level);

			// ����
			for (Attack a : ATRemove)
				listAT.remove(a);
			for (Attack2 a : AT2Remove)
				listAT2.remove(a);
			for (MAttack2 a : MARemove2)
				listAM2.remove(a);
			for (MAttack3 a : MARemove3)
				listAM3.remove(a);
			for (Pijeon a : MRemove1)
				listM1.remove(a);
			for (Mang2 a : MRemove2)
				listM2.remove(a);
			for (Freeja a : MRemove3)
				listM3.remove(a);

			for (Item a : IRemove)
				listI.remove(a);

			for (Heart a : HRemove)
				listH.remove(a);
			for (Dragon a : AP1Remove)
				listAP.remove(a);

			setFocusable(true);
			requestFocus();
		}

		

		@Override
		public void keyTyped(KeyEvent e) {
		}

		// �巡�︦ ����Ű�� �̵�
		@Override
		public void keyPressed(KeyEvent e) {
			int keycode = e.getKeyCode();

			// ���� Ű���带 ���� ������ ����
			if (keycode == KeyEvent.VK_UP) {
				moveUp = true;
			} else if (keycode == KeyEvent.VK_DOWN) {
				moveDown = true;
			} else if (keycode == KeyEvent.VK_RIGHT) {
				moveRight = true;
			} else if (keycode == KeyEvent.VK_LEFT) {
				moveLeft = true;
			}
			if (keycode == KeyEvent.VK_SPACE) {
				checkSpace = true;

			}
			if (keycode == KeyEvent.VK_SHIFT) {
				checkShift = true;

			}


			// ����Ű�� ���� ȭ�� �ٲ�
			if (keycode == KeyEvent.VK_ENTER) {
				CountE++;

				// ���� ȭ�� -> ���� ȭ��
				if (CountE == 1) {
					isMainScreen = false;
					isExplainScreen = true;
					isExplainScreen2 = false;
					isGameScreen = false;
					isFinishScreen = false;
					isFinishScreen2 = false;
					t.start();
				} // ���� ȭ�� �ι�°
				if (CountE == 2) {
					isMainScreen = false;
					isExplainScreen = false;
					isExplainScreen2 = true;
					isGameScreen = false;
					isFinishScreen = false;
					isFinishScreen2 = false;
				}
				// ���� ȭ�� -> ���� ȭ��
				if (CountE == 3) {
					isMainScreen = false;
					isExplainScreen = false;
					isExplainScreen2 = false;
					isGameScreen = true;
					isFinishScreen = false;
					isFinishScreen2 = false;
					
					panel.add(nameLabel);
					panel.add(scoreLabel);
					panel.add(levelLabel);

					if (listAP.isEmpty()) // �巡�� ���� ��ġ
						listAP.add(new Dragon(340, 720));

					for (int i = 0; i < INDEX; i++) { // �⺻ ����� = 3��
						listH.add(new Heart(i));
					}
				}
			} else if (keycode == KeyEvent.VK_ESCAPE) { // ESC Ű�� ���� ����
				System.exit(0);

			}
		}

		// Ű���带 ������ ������ �� �۵� ����
		@Override
		public void keyReleased(KeyEvent e) {
			int keycode = e.getKeyCode();

			if (keycode == KeyEvent.VK_UP) {
				moveUp = false;
			}
			if (keycode == KeyEvent.VK_DOWN) {
				moveDown = false;
			}
			if (keycode == KeyEvent.VK_RIGHT) {
				moveRight = false;
			}
			if (keycode == KeyEvent.VK_LEFT) {
				moveLeft = false;
			}

			if (keycode == KeyEvent.VK_SPACE) {
				checkSpace = false;

			}if (keycode == KeyEvent.VK_SHIFT) {
				checkShift = false;

			}

		}

	}

	// �巡��
	class Dragon extends ImageIcon {
		int x;
		int y;
		int w;
		int h;

		int moveX = 9;
		int moveY = 9;

		Dragon(int posX, int posY) {
			x = posX;
			y = posY;
			w = DRAGON_WIDTH;
			h = DRAGON_HEIGHT;
		}

		public void draw(Graphics g) {
			if (isplayer == true) {
				g.drawImage(player, x, y, 150, 150, null); // ���� ���ڸ�
			}
		}

		public void draw2(Graphics g) {
			if (isplayer1 == true) {
				g.drawImage(player2, x, y, 140, 140, null); // ���� ���ڸ�
			}
		}

		public void moveUP() {
			y -= moveY;
		}

		public void moveDOWN() {
			y = y + moveY;
		}

		public void moveRIGHT() {
			x += moveX;
		}

		public void moveLEFT() {
			x -= moveX;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public double distance(int x, int y) { // �������� �Ÿ�
			return Math.sqrt(Math.pow((this.x + w / 2) - x, 2) + Math.pow((this.y + h / 2) - y, 2));
		}

	}

	// ���ڸ��� ����
	class Attack {
		int pX;
		int pY;
		int width;
		int height;

		Attack(int x, int y) {
			pX = x;
			pY = y;
			width = 50;
			height = 55;
		}

		public void moveA() {

			pY -= 10;
		}

		public void drawFire(Graphics g) {
			g.drawImage(Fire, pX, pY, width, height, null);
		}

		public int getY() {
			return pY;
		}

		public int getX() {
			return pX;
		}

	}

	// �ι�° ���ڸ� ����
	class Attack2 {
		int pX;
		int pY;
		int width;
		int height;

		Attack2(int x, int y) {
			pX = x;
			pY = y;
			width = 45;
			height = 50;
		}

		public void moveA() {
			pY -= 10;
		}

		public void drawFire(Graphics g) { // �ι��� ���ڸ� ����
			g.drawImage(Fire2, pX, pY, width, height, null);
		}

		public int getY() {
			return pY;
		}

		public int getX() {
			return pX;
		}

	}

	// �ι�° ���ϸ� ���� ������ ���� X
	class MAttack1 {
		int pX;
		int pY;
		int wid;
		int hei;
		Color color;

		public void moveA() {
			pY += 10;
		}

		public int getY() {
			return pY;
		}

		public int getX() {
			return pX;
		}
	}

	// �������� �ϴ� ����
	class MAttack2 {
		int pX;
		int pY;
		int wid = 4;
		int hei = 20;
		Color color;

		MAttack2(int x, int y, Color color) {
			pX = x + 30;
			pY = y + 35;

			this.color = color;
		}

		public void moveA() {
			pY += 10;
		}

		public void drawMA2(Graphics g) {
			g.setColor(color);
			g.fillRect(pX, pY, wid, hei);
		}

		public int getY() {
			return pY;
		}

		public int getX() {
			return pX;
		}
	}

	// �������� �ϴ� ����
	class MAttack3 {
		int pX;
		int pY;
		int wid = 4;
		int hei = 20;
		Color color;

		MAttack3(int x, int y, Color color) {
			pX = x + 35;
			pY = y + 40;

			this.color = color;
		}

		public void moveA() {
			pY += 10;
		}

		public void drawMA3(Graphics g) {
			g.setColor(color);
			g.fillRect(pX, pY, wid, hei);
		}

		public int getY() {
			return pY;
		}

		public int getX() {
			return pX;
		}
	}

	// ������� ��Ÿ���� ��Ʈ
	class Heart {
		int index;

		Heart(int i) {
			index = i;
	
			
		}

		public void drawH(Graphics g) {
			g.drawImage(heart1, 600 + index * 30, 10, 27, 21, null);
		}
	}

	// �÷��̾� �̸�
//	class players {
//		String name;
//
//		players(String player_name) {
//			this.name = player_name;
//		}
//
//		public String name() {
//			return this.name;
//		}
//	}

	public static void main(String[] args) {
		
		Operator opt = new Operator();
		opt.db = new Database();
		opt.lf = new LoginFrame(opt);
		opt.jf = new JoinFrame(opt);
	}

}
