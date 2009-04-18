
//an area, battle, or menu that is the root to be displayed and receive key events
abstract class ScreenOwner {
	
	abstract void displayOn(Graphics2D g);
	abstract void keyPressed(char key);
	abstract void step();
}