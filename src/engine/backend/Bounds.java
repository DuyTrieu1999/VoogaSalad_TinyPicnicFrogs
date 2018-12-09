package engine.backend;


/**
 * Represents the bounding box
 * The top left of bounding box is expressed relative
 * to the top of the Actor's coordinates
 */
public class Bounds {
	private int width;
	private int height;
	private int relX;
	private int relY;

	/**
	 * Constructs a bounding box with given parameters
	 * @param width width
	 * @param height height
	 * @param relX x relative to top left of Actor's coordinates
	 * @param relY y relative to top left of Actor's coordinates
	 */
	public Bounds(int width, int height, int relX, int relY) {
		this.width = width;
		this.height = height;
		this.relX = relX;
		this.relY = relY;
	}

	/**
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return width
	 */
	public int getWidth() { return width; }

	/**
	 * @return relX
	 */
	public int getRelX() {
		return relX;
	}

	/**
	 * @return relY
	 */
	public int getRelY() {
		return relY;
	}
}
