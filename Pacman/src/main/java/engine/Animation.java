package engine;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * Classe permettant de gérer les animations GIF (suite de BufferedImage)
 * @author Raphaël
 *
 */
public class Animation {
	
	private Timer counter;
	private BufferedImage[] frames;
	private int index = 0;
	private boolean started;
	private boolean pause;
	private int framesFps;

	/**
	 * Constructeur privé de Animation
	 * @author Raphaël
	 * @param path Chemin de l'image
	 * @param fps Nombre d'images par seconde de l'animation. La fluidité de l'animation est corrélée avec celle du jeu (i.e. si le jeu se rafraîchit toutes les 120ms, l'animation ne peut changer d'image que toutes les 120ms).
	 */
	public Animation(String path, int fps) {		
		try {
			InputStream image = Animation.class.getClassLoader().getResourceAsStream(path);
			int slash = Math.max(path.lastIndexOf("/"), path.lastIndexOf("\\"));
			int point = path.lastIndexOf(".");
			
			String format = "";
			if (point > slash) {
				format = path.substring(point+1);
			}
			
	    	ImageReader reader = (ImageReader)ImageIO.getImageReadersByFormatName(format).next();    	
	    	ImageInputStream is = ImageIO.createImageInputStream(image);    	
	    	reader.setInput(is, false);
	    	
	    	int frames = reader.getNumImages(true);
	    	this.frames = new BufferedImage[frames];
	    	this.framesFps = fps >= 0 ? fps : 0;
	    		
	    	for (int i = 0; i < frames; i++) {
				this.frames[i] = reader.read(i);
			}
	    	
	    	is.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	/**
	 * Démarrer l'animation des frames du tableau d'images
	 * @author Raphaël
	 */
	public void animate() {
		if (!this.started) {
			this.started = true;
			
			int refreshRate = this.framesFps > 0 ? 1000/this.framesFps : 1000;
			this.counter = new Timer();
			this.counter.schedule(new TimerTask() {
				@Override
				public void run() {
					if (!pause && framesFps > 0) {
						index = index < frames.length - 1 ? index + 1 : 0;
					}
				}
			}, 0, refreshRate);
		}
	}
	
	/**
	 * Arrête l'animation en cours
	 * @author Raphaël
	 * @param p true si à mettre en pause, false sinon
	 */
	public void pause(boolean p) {
		if (this.started) {
			this.pause = p;
		}
	}
	
	/**
	 * Termine l'animation en cours
	 * @author Raphaël
	 */
	public void kill() {
		if (this.started) {
			this.counter.cancel();
			this.started = false;
			this.pause = false;
			this.index = 0;
		}
	}
	
	/**
	 * Permet de changer le nombre d'images par secondes de l'animation
	 * @author Raphaël
	 * @param fps Nombre d'images par seconde
	 */
	public void setFramesFps(int fps) {
		this.counter.cancel();
		this.started = false;
		this.framesFps = 0;
		
		if (this.framesFps >= 0) {
			this.framesFps = fps;
		}
		
		this.animate();
	}
	
	/**
	 * Dessiner l'animation actuelle
	 * @author Raphaël
	 * @param x Position de l'image en abscisse 
	 * @param y Position de l'image en ordonnée
	 * @param observer Observeur de l'image
	 * @param context Contexte graphique de dessin
	 * @return true si tous les pixels sont déjà chargés, false sinon
	 */
	public boolean drawImage(int x, int y, ImageObserver observer, Graphics2D context) {
		this.animate();
		context.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
		context.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		return context.drawImage(this.frames[this.index], x, y, observer);
	}
	
	/**
	 * Dessiner l'animation actuelle
	 * @author Raphaël
	 * @param x Position de l'image en abscisse 
	 * @param y Position de l'image en ordonnée
	 * @param width Taille en largeur de l'image
	 * @param height Taille en hauteur de l'image
	 * @param observer Observeur de l'image
	 * @param context Contexte graphique de dessin
	 * @return true si tous les pixels sont déjà chargés, false sinon
	 */
	public boolean drawImage(int x, int y, int width, int height, ImageObserver observer, Graphics2D context) {
		this.animate();
		context.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
		context.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		return context.drawImage(this.frames[this.index], x, y, width, height, observer);
	}

	/**
	 * Retourne si l'animation est démarrée
	 * @author Raphaël
	 * @return true si démarrée, false sinon
	 */
	public boolean isStarted() {
		return started;
	}

	/**
	 * Retourne si l'animation est en pause
	 * @author Raphaël
	 * @return true si en pause, false sinon
	 */
	public boolean isPaused() {
		return pause;
	}
	
	/**
	 * Retourne le nombre d'images par secondes pouvant être chargées au maximum
	 * @author Raphaël
	 * @return Nombre d'images par secondes au maximum de l'animation
	 */
	public int getFps() {
		return this.framesFps;
	}
	
	/**
	 * Retourne l'indice actuel de l'image de l'animation
	 * @author Raphaël
	 * @return Indice actuel de l'image de l'animation
	 */
	public int getIndex() {
		return this.index;
	}

	/**
	 * Retourne le nombre d'images de l'animation
	 * @author Raphaël
	 * @return Nombre d'images de l'animation
	 */
	public int getNumberOfFrames() {
		return this.frames.length;
	}
}
