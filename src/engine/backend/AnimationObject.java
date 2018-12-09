package engine.backend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents an AnimationObject
 *
 * @author Michael Glushakov (mg367)
 * @author Max Bartlett (mmb70)
 */

public class AnimationObject {
	private String animationName;
	private String animationPath;
	private ImageView animationView;
	private Coordinate coordinate;
	private int spriteRows;
	private int spiteCols;

	/**
	 * Constructor for AnimationObject
	 * @param name name
	 * @param path file path
	 * @param spriteR sprite row
	 * @param spriteC sprite column
	 */
	public AnimationObject(String name, String path, int spriteR, int spriteC) {
		this.animationName = name;
		animationPath = path;
		setImage();
		spriteRows = spriteR;
		spiteCols = spriteC;
	}

	/**
	 * Constructor for AnimationObject
	 * @param name name
	 * @param path file path
	 * @param spriteR sprite row
	 * @param spriteC sprite column
	 * @param coordinateP coordinate
	 */
	public AnimationObject(String name, String path, int spriteR, int spriteC, Coordinate coordinateP) {
		this(name, path, spriteR, spriteC);
		coordinate = coordinateP;
	}

	/**
	 * @return animationView
	 */
	public ImageView getAnimationView() {
		return this.animationView;
	}

	/**
	 * @return animationName: animationPath
	 */
	public String getName() {
		return animationName + ": " + animationPath;
	}

	/**
	 * @return coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * sets image
	 */
	public void setImage() {
		animationView = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(animationPath)));
		System.out.println(animationView==null);
	}

	/**
	 * @return spriteRows
	 */
	public int getSpriteRows() {
		return spriteRows;
	}

	/**
	 * @return spriteCols
	 */
	public int getSpiteCols() {
		return spiteCols;
	}
}
