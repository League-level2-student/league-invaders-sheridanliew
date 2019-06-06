import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font bodyFont;
	Font captionFont;
	Timer frameDraw;
	Rocketship r;
	ObjectManager om;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	Timer alienSpawn;

	GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		bodyFont = new Font("Arial", Font.PLAIN, 36);
		captionFont = new Font("Arial", Font.PLAIN, 18);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		r = new Rocketship(250, 700, 50, 50);
		om = new ObjectManager(r);
		if (needImage) {
			loadImage("space-1.png");
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}
	}

	void startGame() {
		alienSpawn = new Timer(1000, om);
		alienSpawn.start();
	}

	void updateMenuState() {

	}

	void updateGameState() {
		om.update();
		if (r.isActive == false) {
			currentState = END;
		}
	}

	void updateEndState() {
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 20, 150);
		g.setFont(bodyFont);
		g.drawString("Press ENTER to start", 70, 400);
//		g.drawString("Press SPACE for intructions", 30, 600);
	}

	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		om.draw(g);
		g.setFont(captionFont);
		g.setColor(Color.WHITE);
		g.drawString("Enemies Killed: "+om.getScore(), 40, LeagueInvaders.HEIGHT-40);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER", 90, 150);
		g.setFont(bodyFont);
		g.drawString("You have killed " + om.getScore() + " enemies", 30, 400);
		g.drawString("Press ENTER to restart", 60, 600);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			System.out.println("menu on");
			updateMenuState();
		} else if (currentState == GAME) {
			System.out.println("game on");
			updateGameState();
		} else if (currentState == END) {
			System.out.println("end on");
			updateEndState();
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				currentState = MENU;
				r = new Rocketship(250, 700, 50, 50);
				om = new ObjectManager(r);
			} else if (currentState == MENU) {
				currentState++;
				startGame();
			} else if (currentState == GAME) {
				currentState++;
				alienSpawn.stop();
			}
//			else {
//				currentState++;
//			}
		}
		if (currentState == GAME) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				System.out.println("UP");
				if (r.y + (r.height / 2) >= r.height) {
					r.up();
				}
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				System.out.println("DOWN");
				if (LeagueInvaders.HEIGHT - (r.y + r.height) >= r.height) {
					r.down();
				}
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				System.out.println("LEFT");
				if (r.x + r.width / 2 >= r.width) {
					r.left();
				}
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				System.out.println("RIGHT");
				if (LeagueInvaders.WIDTH - (r.x + r.width) >= r.width / 2) {
					r.right();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				om.addProjectile(r.getProjectile());
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}
}