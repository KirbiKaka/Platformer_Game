import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/** A solid, interactable object that becomes non-solid when a puzzle is solved. Does not open and close on subsequent interactions. */
public class PuzzleWall extends InteractableEntity{

    private boolean isOpen;
    private final String imageDir2;
    private Image image2;
    private final String puzzle;
    private final String locked, open, closed;

    public PuzzleWall(int coordX, int coordY, String imageDir, String imageDir2, String puzzle) {
        super(50, 67, coordX, coordY, imageDir);
        this.imageDir2 = imageDir2;
        this.puzzle = puzzle;
        locked = "Locked... Maybe there's a key?\n                                   \n...nah. Probably not.";
        open = "Jeepers, it's open now!";
        closed = "Let's block out the draft, eh?";
    }

    public PuzzleWall(int sizeX, int sizeY, int coordX, int coordY, String imageDir, String imageDir2, String puzzle) {
        super(sizeX, sizeY, coordX, coordY, imageDir);
        this.imageDir2 = imageDir2;
        this.puzzle = puzzle;
        locked = "Locked... Maybe there's a key?\n                                   \n...nah. Probably not.";
        open = "Jeepers, it's open now!";
        closed = "Let's block out the draft, eh?";
    }

    public PuzzleWall(int sizeX, int sizeY, int coordX, int coordY, String imageDir, String imageDir2, String puzzle, String locked, String open, String closed) {
        super(sizeX, sizeY, coordX, coordY, imageDir);
        this.imageDir2 = imageDir2;
        this.puzzle = puzzle;
        this.locked = locked;
        this.open = open;
        this.closed = closed;
    }

    @Override
    void interact() {
        if (Inventory.puzzles.contains(puzzle)) {
            if (isOpen) {
                TextBox.writeText(open);
            } else {
                TextBox.writeText(closed);
            }
            isOpen = true;
        } else {
            TextBox.writeText(locked);
        }
    }

    @Override
    boolean isInteractable() {
        return true;
    }

    @Override
    boolean isSolid() {
        return !isOpen;
    }

    @Override
    void init() {

    }

    @Override
    void update(int delta) {

    }

    @Override
    void draw(Graphics g, int offset) {
        if (!imageCreated) {
            try {
                image = new Image(imageDir);
                image2 = new Image(imageDir2);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            imageCreated = true;
        }
        if (isVisible) {
            if (!isOpen) {
                g.drawImage(image, coordX - offset, coordY);
            } else {
                g.drawImage(image2, coordX - offset, coordY);
            }
        }
    }

}
