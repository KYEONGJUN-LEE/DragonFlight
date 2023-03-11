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
	
	static String player_name; // 플레이어 이름 변수
	JLabel nameLabel; // 화면내에 이름 라벨
	JLabel scoreLabel; // 화면내에 점수 라벨
	JLabel levelLabel; // 화면내에 점수 라벨
	

	Timer t; // 비행기 타이머
	Timer at; // 공격 타이머
	Timer at2;

	int INDEX = 3; // 생명력 카운트
	int CountE = 0; // 엔터키 횟수 카운트
	int item;

	int score = 1; // 점수 카운트
	int level = 1; // 레벨 카운트

	int count = 0; // 타이머 카운트

	// 이미지파일
	Image mainScreen = new ImageIcon(getClass().getClassLoader().getResource("image/main_Screen.png")).getImage(); // 게임시작화면	
	Image explainScreen = new ImageIcon(getClass().getClassLoader().getResource("image/loading_screen.png")).getImage(); // 설명화면
	Image explainScreen2 = new ImageIcon(getClass().getClassLoader().getResource("image/loading_screen2.png")).getImage(); // 설명화면
	Image gameScreen = new ImageIcon(getClass().getClassLoader().getResource("image/main_Screen1.png")).getImage(); // 게임화면		
	Image endScreen = new ImageIcon(getClass().getClassLoader().getResource("image/end_screen.png")).getImage(); // 게임 종료
	Image endScreen2 = new ImageIcon(getClass().getClassLoader().getResource("image/game_clear.png")).getImage(); // 게임 종료
	Image player = new ImageIcon(getClass().getClassLoader().getResource("image/Ree2.gif")).getImage(); // 붉은리자몽
	Image player2 = new ImageIcon(getClass().getClassLoader().getResource("image/R2.png")).getImage(); // 검은리자몽
	Image Fire = new ImageIcon(getClass().getClassLoader().getResource("image/Fire.png")).getImage(); // 불
	Image Fire2 = new ImageIcon(getClass().getClassLoader().getResource("image/파란불.png")).getImage(); // 불
	Image enemy1 = new ImageIcon(getClass().getClassLoader().getResource("image/피죤.gif")).getImage(); // 피죤
	Image enemy2 = new ImageIcon(getClass().getClassLoader().getResource("image/망나뇽.gif")).getImage(); // 망나뇽
	Image enemy3 = new ImageIcon(getClass().getClassLoader().getResource("image/프리져.gif")).getImage(); // 프리져
	Image heart1 = new ImageIcon(getClass().getClassLoader().getResource("image/HEART.png")).getImage(); // 하트
	Image[] itemList = { new ImageIcon(getClass().getClassLoader().getResource("image/HEART1.png")).getImage(), // 아이템 하트
			new ImageIcon(getClass().getClassLoader().getResource("image/stone.png")).getImage() }; // 아이템 스톤

	private Image bImage;
	private Graphics screenGraphics;

	private boolean isMainScreen, isExplainScreen, isExplainScreen2, isGameScreen, isFinishScreen, isFinishScreen2; // 화면전환을 위한 boolean 자료형
																													 
																													
																													
																													 
	private boolean isplayer, isplayer1; // 플레이어 변경을 위한 자료형 boolean

	static int DRAGON_WIDTH = 100; // 드래곤 가로크기
	static int DRAGON_HEIGHT = 120; // 드래곤 세로크기
	static int PANEL_WIDTH = 800; // 패널 가로 크기
	static int PANEL_HEIGHT = 900; // 패널 세로 크기

	static int FRAME_WIDTH = PANEL_WIDTH; // 프레임프기랑 패널크기랑 같도록 하기
	static int FRAME_HEIGHT = PANEL_HEIGHT;

	ArrayList<Dragon> listAP = new ArrayList<Dragon>(); // 드래곤 객체
	ArrayList<Attack> listAT = new ArrayList<Attack>(); // 공격 객체
	ArrayList<Attack2> listAT2 = new ArrayList<Attack2>(); // 공격 객체 두번째 불꽃
	ArrayList<MAttack2> listAM2 = new ArrayList<MAttack2>(); // 적이 공격하는 객체
	ArrayList<MAttack3> listAM3 = new ArrayList<MAttack3>(); // 적이 공격하는 객체
	ArrayList<Pijeon> listM1 = new ArrayList<Pijeon>();// 적1 객체
	ArrayList<Mang2> listM2 = new ArrayList<Mang2>();// 적2 객체
	ArrayList<Freeja> listM3 = new ArrayList<Freeja>();// 적3 객체

	ArrayList<Item> listI = new ArrayList<Item>(); // 아이템 객체
	ArrayList<Heart> listH = new ArrayList<Heart>(); // 하트 객체


	// 삭제 객체
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

	// 키보드 움직임 자연스럽게 하기 위함
	boolean moveUp = false;
	boolean moveDown = false;
	boolean moveRight = false;
	boolean moveLeft = false;
	boolean checkSpace = false;
	boolean checkShift = false;

	// 생성자 - Frame 기본설정, 패널 추가
	DragonFlight() {


		// 타이머 생성
		t = new Timer(10, new Draw());
		at = new Timer(100, new MakeA());
		at2 = new Timer(100, new MakeB());

		// 패널
		panel = new DrawPanel();
		panel.addKeyListener(panel);

		nameLabel = new JLabel("NAME: " + player_name);
		scoreLabel = new JLabel("SCORE: " + score);
		levelLabel = new JLabel("LEVEL: " + level);

		// 라벨 글씨체
		nameLabel.setFont(new Font("FTLAB Hoony", Font.BOLD, 20));
		scoreLabel.setFont(new Font("FTLAB Hoony", Font.BOLD, 20));
		levelLabel.setFont(new Font("FTLAB Hoony", Font.BOLD, 20));

		panel.setLayout(null);

		// 위치 선정//setBounds(x,y,가로,세로) 절대위치
		nameLabel.setBounds(600, 800, 150, 50); // 이름
		scoreLabel.setBounds(450, 800, 150, 50); // 점수
		levelLabel.setBounds(600, 750, 150, 50); // 레벨

		// 가운데 정렬
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		levelLabel.setHorizontalAlignment(JLabel.CENTER);

		// 색 선정
		nameLabel.setForeground(Color.white);
		scoreLabel.setForeground(Color.white);
		levelLabel.setForeground(Color.white);

		this.add(panel);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT); // 프레임사이즈 정하기
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("REZAMONG");
		this.setResizable(false);// 크기조절 마음대로 못하게

		init(); // boolean형을 통해 mainScreen그려주기
//		name();// 이름 저장
		Dragoninit(); // 드래곤 저장

	}

	// boolean형을 통해 화면그려주기
	public void init() {
		isMainScreen = true;
		isExplainScreen = false;
		isExplainScreen2 = false;
		isGameScreen = false;
		isFinishScreen = false;
		isFinishScreen2 = false;
	}
	// 드래곤의 객체 변환을 위해 처음 객체를 true, 두번째 객체를 false
	public void Dragoninit() {
		isplayer = true;
		isplayer1 = false;

	}


	// 화면 전환
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
			repaint(); // 다시 그려줄때마다
			count++;// count++
			int x;
			int y;

			// 드래곤의 움직임
			for (Dragon ap : listAP) {
				// 방향키로 움직임 제어
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

				if (checkSpace == true) { // 스페이스바를 통한 공격
					at.start();
					
				} else if (checkSpace == false) {
					at.stop();

				}
				if (checkShift == true) { // 스페이스바를 통한 공격
					at2.start();

				} else if (checkShift == false) {
					at2.stop();

				}

			}

			// 3점이상 레벨 2, 7점이상 레벨3
			if (score >= 0)
				level = 1;
			if (score >= 3)
				level = 2;
			if (score >= 7)
				level = 3;
			
			// 피죤추가
			if (count % 100 == 0) {
				x = (int) (Math.random() * 700);
				listM1.add(new Pijeon(x, 0, enemy1));
			} 
			//망나뇽추가
			if (count % 200 == 0 && level > 1) {
				x = (int) (Math.random() * PANEL_WIDTH);
				y = (int) (Math.random() * PANEL_HEIGHT);
				listM2.add(new Mang2(x, y, enemy2));
			}
			//프리져추가
			if (count % 250 == 0 && level > 2) {
				x = (int) (Math.random() * PANEL_HEIGHT);
				y = (int) (Math.random() * PANEL_WIDTH);
				listM3.add(new Freeja(x, y, enemy3));
			}

			// 몬스터가 하는 공격 list추가
			if (count % 200 == 0 && CountE > 1) {
				
				if (level > 1) { // 레벨2이면 망나뇽 추가
					for (Mang2 M2 : listM2) {
						x = M2.getX();
						y = M2.getY();
						listAM2.add(new MAttack2(x, y, Color.yellow));
					}

				}
				if (level > 2) { // 레벨3이면 프리져 추가
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

			// 리자몽 하는 공격 list추가
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

			// 리자몽 하는 공격 list추가
			for (Dragon AP : listAP) {
				gX = AP.getX();
				gY = AP.getY();
				listAT2.add(new Attack2(gX + 33, gY - 20));
				

			}

		}
	}

	class DrawPanel extends JPanel implements KeyListener {
		public void paintComponent(Graphics g) {

			bImage = createImage(PANEL_WIDTH, PANEL_HEIGHT); // 화면 채우기
			screenGraphics = bImage.getGraphics();
			screenDraw(screenGraphics);
			g.drawImage(bImage, 0, 0, null);

			// 생명력이 0이 되었을때 게임 종료 화면
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

				// 위치 재정렬 // setBounds(x,y,가로,세로) 절대위치
				nameLabel.setBounds(450, 600, 250, 100);
				scoreLabel.setBounds(450, 650, 250, 100); 
				levelLabel.setBounds(450, 700, 250, 100);

				nameLabel.setHorizontalAlignment(JLabel.CENTER);
				scoreLabel.setHorizontalAlignment(JLabel.CENTER);
				levelLabel.setHorizontalAlignment(JLabel.CENTER);

				panel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

				t.stop();

				// finishScreen 화면 그려주기
				g.drawImage(endScreen, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);

			}
			// score15가 되면 게임 종료 화면
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

				// 위치 재정렬
				nameLabel.setBounds(450, 600, 250, 100);
				scoreLabel.setBounds(450, 650, 250, 100); // setBounds(x,y,가로,세로) 절대위치
				levelLabel.setBounds(450, 700, 250, 100);

				nameLabel.setHorizontalAlignment(JLabel.CENTER);
				scoreLabel.setHorizontalAlignment(JLabel.CENTER);
				levelLabel.setHorizontalAlignment(JLabel.CENTER);
				
				nameLabel.setForeground(Color.black);
				scoreLabel.setForeground(Color.black);
				levelLabel.setForeground(Color.black);

				panel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

				t.stop();

				// finishScreen 화면 그려주기
				g.drawImage(endScreen2, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);

			}

			// 위치 재정렬

			// 객체 생성
			// 하트 그림 그리기
			for (Heart h : listH) {
				h.drawH(g);
			}

			for (Attack2 a : listAT2) {
				a.drawFire(g);
			}

			// 드래곤 그림 그리기
			for (Dragon ap1 : listAP) {
				ap1.draw(g);
			}
			// 드래곤 2번쨰 그리기
			for (Dragon ap1 : listAP) {
				ap1.draw2(g);
			}

			// 공격 그림그리기
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
			// 망나뇽공격
			if (listAM2.isEmpty() != true)
				for (MAttack2 ma : listAM2) {
					ma.drawMA2(g);
					ma.moveA();
				}
			// 프리져공격
			if (listAM3.isEmpty() != true)
				for (MAttack3 ma : listAM3) {
					ma.drawMA3(g);
					ma.moveA();
				}

			//
			for (Dragon ap1 : listAP) {

				for (MAttack2 ma : listAM2) {// 2번째 포켓몬
					if (ma.getY() >= ap1.getY() && ma.getX() >= ap1.getX() && ma.getX() <= ap1.getX() + 60
							&& ma.getY() < ap1.getY() + 60) {
						MARemove2.add(ma);
						HRemove.add(listH.get(INDEX - 1));
						INDEX--;
					}

				}
				for (MAttack3 ma : listAM3) {// 3번째 포켓몬
					if (ma.getY() >= ap1.getY() && ma.getX() >= ap1.getX() && ma.getX() <= ap1.getX() + 50
							&& ma.getY() < ap1.getY() + 50) {
						MARemove3.add(ma);
						HRemove.add(listH.get(INDEX - 1));
						INDEX--;
					}
				}

				// 피죤이 리자몽의 공격을 받았을때, hp--, hp=0 -> 3분의 확률 아이템 생성, 아이템의 종류는 2가지
				for (Pijeon m : listM1) {
					for (Attack a : listAT) {

						if (m.distance(a.pX + 2, a.pY) < 25) {
							ATRemove.add(a);
							m.reduceHp();

							if (m.getHp() <= 0) {
								score++;
								MRemove1.add(m);

								// 아이템 3분의 1의 확률로 생성
								int rand = (int) (Math.random() * 3);
								if (rand == 2) {
									int rand2 = (int) (Math.random() * 2);
									if (rand2 == 0)
										listI.add(new Item(m.getX(), m.getY(), rand2, itemList[rand2])); // 하트 추가
									else if (rand2 == 1)
										listI.add(new Item(m.getX(), m.getY(), rand2, itemList[rand2])); // 스톤
								}
							}
						}
					}

				} // 망나뇽이 리자몽의 공격을 받았을때, hp--, hp=0 -> 3분의 확률 아이템 생성, 아이템의 종류는 2가지
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
				} // 프리져가 리자몽의 공격을 받았을때, hp--, hp=0 -> 3분의 확률 아이템 생성, 아이템의 종류는 2가지

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
				// 피죤이 리자몽의 공격을 받았을때, hp--, hp=0 -> 3분의 확률 아이템 생성, 아이템의 종류는 2가지
				for (Pijeon m : listM1) {
					for (Attack2 a : listAT2) {

						if (m.distance(a.pX + 2, a.pY) < 25) {
							AT2Remove.add(a);
							m.reduceHp();

							if (m.getHp() <= 0) {
								score++;
								MRemove1.add(m);

								// 아이템 3분의 1의 확률로 생성
								int rand = (int) (Math.random() * 3);
								if (rand == 2) {
									int rand2 = (int) (Math.random() * 2);
									if (rand2 == 0)
										listI.add(new Item(m.getX(), m.getY(), rand2, itemList[rand2])); // 하트 추가
									else if (rand2 == 1)
										listI.add(new Item(m.getX(), m.getY(), rand2, itemList[rand2])); // 스톤
								}
							}
						}
					}

				} // 망나뇽이 리자몽의 공격을 받았을때, hp--, hp=0 -> 3분의 확률 아이템 생성, 아이템의 종류는 2가지
				for (Mang2 m2 : listM2) {
					for (Attack2 a : listAT2) {

						if (m2.distance(a.pX + 2, a.pY) < 18.5) {
							AT2Remove.add(a);
							m2.reduceHp();

							if (m2.getHp() <= 0) {
								score=score+2; //두번째 포켓몬을 없애면 +2점
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
				} // 프리져가 리자몽의 공격을 받았을때, hp--, hp=0 -> 3분의 확률 아이템 생성, 아이템의 종류는 2가지

				for (Freeja m2 : listM3) {
					for (Attack2 a : listAT2) {

						if (m2.distance(a.pX + 2, a.pY) < 18.5) {
							AT2Remove.add(a);
							m2.reduceHp();

							if (m2.getHp() <= 0) {
								score=score+3; // 세번째 포켓몬을 없애면 +3점
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

				// 아이템 생성
				for (Item item : listI) {
					item.draw(g);
					item.move();

					// 거리 계산
					if (ap1.distance(item.getXI(), item.getYI()) <= 40) {
						IRemove.add(item);
						if (item.getType() == 0) { // 첫번째 아이템인 생명력 추가하기
							listH.add(new Heart(INDEX));
							if (INDEX < 5)
								INDEX++; // 생명력 추가
						}
					}

					if (ap1.distance(item.getXI(), item.getYI()) <= 40 && item.getType() == 1) {
						isplayer = false; //2번 스톤을 먹으면 플레이어(객체가 바뀜.)
						isplayer1 = true;
			
					}
				}

			}

			// 드래곤과 몬스터가 맞닿으면 점수 감점
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
						score=score-2; // 2번째 포켓몬과 닿으면 -2
					}
				}
				for (Freeja m3 : listM3) {
					m3.draw(g);
					m3.move();

					if (AP.distance(m3.getX() + 20, m3.getY() + 20) <= 50) {
						MRemove3.add(m3);
						score=score-3; // 3번째 포켓몬과 닿으면 -3
					}
				}
			}

			// score과 level 새로고침
			scoreLabel.setText("SCORE: " + score);
			levelLabel.setText("LEVEL: " + level);

			// 제거
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

		// 드래곤를 방향키로 이동
		@Override
		public void keyPressed(KeyEvent e) {
			int keycode = e.getKeyCode();

			// 방향 키보드를 통해 움직임 조정
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


			// 엔터키를 통해 화면 바꿈
			if (keycode == KeyEvent.VK_ENTER) {
				CountE++;

				// 시작 화면 -> 설명 화면
				if (CountE == 1) {
					isMainScreen = false;
					isExplainScreen = true;
					isExplainScreen2 = false;
					isGameScreen = false;
					isFinishScreen = false;
					isFinishScreen2 = false;
					t.start();
				} // 설명 화면 두번째
				if (CountE == 2) {
					isMainScreen = false;
					isExplainScreen = false;
					isExplainScreen2 = true;
					isGameScreen = false;
					isFinishScreen = false;
					isFinishScreen2 = false;
				}
				// 설명 화면 -> 게임 화면
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

					if (listAP.isEmpty()) // 드래곤 생성 위치
						listAP.add(new Dragon(340, 720));

					for (int i = 0; i < INDEX; i++) { // 기본 생명력 = 3개
						listH.add(new Heart(i));
					}
				}
			} else if (keycode == KeyEvent.VK_ESCAPE) { // ESC 키를 통해 종료
				System.exit(0);

			}
		}

		// 키보드를 눌렀다 떼었을 때 작동 중지
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

	// 드래곤
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
				g.drawImage(player, x, y, 150, 150, null); // 빨간 리자몽
			}
		}

		public void draw2(Graphics g) {
			if (isplayer1 == true) {
				g.drawImage(player2, x, y, 140, 140, null); // 검은 리자몽
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

		public double distance(int x, int y) { // 중점과의 거리
			return Math.sqrt(Math.pow((this.x + w / 2) - x, 2) + Math.pow((this.y + h / 2) - y, 2));
		}

	}

	// 리자몽이 공격
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

	// 두번째 리자몽 공격
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

		public void drawFire(Graphics g) { // 두번쨰 리자몽 공격
			g.drawImage(Fire2, pX, pY, width, height, null);
		}

		public int getY() {
			return pY;
		}

		public int getX() {
			return pX;
		}

	}

	// 두번째 포켓몬 공격 피죤은 공격 X
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

	// 망나용이 하는 공격
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

	// 프리져가 하는 공격
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

	// 생명력을 나타내는 하트
	class Heart {
		int index;

		Heart(int i) {
			index = i;
	
			
		}

		public void drawH(Graphics g) {
			g.drawImage(heart1, 600 + index * 30, 10, 27, 21, null);
		}
	}

	// 플레이어 이름
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
