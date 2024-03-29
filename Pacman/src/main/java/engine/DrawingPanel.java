package engine;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * la clase chargee de Dessiner
	 */
	private GamePainter painter;

	/**
	 * image suivante est l'image cachee sur laquelle dessiner
	 */
	private BufferedImage nextImage;

	/**
	 * image en cours est l'image entrain d'etre affichee
	 */
	private BufferedImage currentImage;

	/**
	 * la taille des images
	 */
	private int width, height;

	/**
	 * constructeur Il construit les images pour doublebuffering ainsi que le
	 * Panel associe. Les images stockent le painter et on demande au panel la
	 * mise a jour quand le painter est fini
	 * 
	 * @param width
	 *            largeur de l'image
	 * @param height
	 *            hauteur de l'image
	 */
	public DrawingPanel(GamePainter painter) {
		super();
		this.width = painter.getWidth();
		this.height = painter.getHeight();
		this.setPreferredSize(new Dimension(this.width, this.height));
		this.painter=painter;

		// cree l'image buffer et son graphics
		this.nextImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		this.currentImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
	}

	/**
	 * demande de mettre a jour le rendu de l'image sur le Panel. Creer une
	 * nouvelle image vide sur laquelle dessiner
	 */
	public void drawGame() {
		// generer la nouvelle image
		this.painter.draw(this.nextImage);

		// l'image a dessiner est celle qu'on a construite
		this.currentImage = this.nextImage;

		// met a jour l'image a afficher sur le panel
		this.repaint();
	}

	/**
	 * redefinit la methode paint consiste a dessiner l'image en cours
	 * 
	 * @param g
	 *            graphics pour dessiner
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.currentImage, 0, 0, getWidth(), getHeight(), this);
	}
}
