
import java.awt.Color;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author peraa0837
 */
public class BoardExample {

    /**
     * @param args the command line arguments
     */
    public static void maifjhn(String[] args) {
        // TODO code application logic here
        Board b = new Board(8, 8);
        b.putPeg(Color.GREEN, 3, 5);
        b.putPeg(Color.BLUE, 1, 2);

        b.removePeg(3, 5);
        b.displayMessage("Hello");

        while (true) {
            Coordinate click = b.getClick();
            int clickRow = click.getRow();
            int clickCol = click.getCol();
            b.putPeg(Color.ORANGE, clickRow, clickCol);
        }
    }
}
