import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Rocketship r;
	List<Projectile> projectiles;
	List<Alien> aliens;
	Random random;
	int score;

	ObjectManager(Rocketship r) {
		this.r = r;
		projectiles = new ArrayList<Projectile>();
		aliens = new ArrayList<Alien>();
		random = new Random();
		score = 0;
	}

	int getScore() {
		return score;
	}

	void addProjectile(Projectile p) {
		projectiles.add(p);
	}

	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
	}

	void draw(Graphics g) {
		r.draw(g);
		for (Alien a : aliens) {
			a.draw(g);
		}
		for (Projectile p : projectiles) {
			p.draw(g);
		}
	}

	void update() {
		for (Alien a : aliens) {
			a.update();
			if (a.y < 0 || a.y > LeagueInvaders.HEIGHT) {
				a.isActive = false;
			}
		}
		for (Projectile p : projectiles) {
			p.update();
			if (p.y < 0 || p.y > LeagueInvaders.HEIGHT) {
				p.isActive = false;
			}
		}
		checkCollision();
		purgeObjects();
	}

	void checkCollision() {
//		for (Alien a : aliens) {
//			if (r.collisionBox.intersects(a.collisionBox)) {
//				r.isActive = false;
//				a.isActive = false;
//				break;
//			}
//			for (Projectile p : projectiles) {
//				if (p.collisionBox.intersects(a.collisionBox)) {
//					p.isActive = false;
//					a.isActive = false;
//					score++;
//					break;
//				}
//			}
//			if (a.isActive == false) {
//				break;
//			}
//		}
		
//		use for loop, not for-each loop
	}

	void purgeObjects() {
		for (int i = 0; i < aliens.size(); i++) {
			if (aliens.get(i).isActive == false) {
				aliens.remove(aliens.get(i));
			}
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isActive == false) {
				projectiles.remove(projectiles.get(i));
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
	}
}
