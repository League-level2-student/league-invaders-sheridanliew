import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame gameFrame;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	GamePanel gp;

	public LeagueInvaders() {
		gameFrame = new JFrame();
		gp = new GamePanel();
	}

	void setup() {
		gameFrame.add(gp);
		gameFrame.setSize(WIDTH, HEIGHT);
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		LeagueInvaders li = new LeagueInvaders();
		li.setup();
	}
}
