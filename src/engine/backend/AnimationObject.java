package engine.backend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents an AnimationObject
 * @author
 */

public class AnimationObject {
	private String animationName;
	private String animationPath;
	private ImageView animationView;
	private Coordinate coordinate;

	/**
	 * Constructs an AnimationObject with given name and path
	 * @param name Animation name
	 * @param path Animation path
	 */
	public AnimationObject(String name, String path) {
		this.animationName = name;
		animationPath = path;
		setImage();
	}

	/**
	 * Constructs an AnimationObject with given name, path, and coordinate
	 * @param name Animation name
	 * @param path Animation path
	 * @param coordinateP Animation coordinate
	 */
	public AnimationObject(String name, String path, Coordinate coordinateP) {
		this(name, path);
		coordinate = coordinateP;
	}

	/**
	 * @return animationView
	 */
	public ImageView getAnimationView() {
		return animationView;
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
	 * Sets animation image
	 */
	public void setImage() {
		animationView = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(animationPath)));
	}
}
