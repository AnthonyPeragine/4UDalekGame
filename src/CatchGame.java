
import java.awt.Color;
import java.util.ArrayList;

/**
 * This class manages the interactions between the different pieces of the game:
 * the Board, the Daleks, and the Doctor. It determines when the game is over
 * and whether the Doctor won or lost.
 */
public class CatchGame {

    /**
     * Instance variables go up here Make sure to create a Board, 3 Daleks, and
     * a Doctor
     */
    Board b;
    Doctor doctor;
    int win = 3;
    int lose = 1;
    ArrayList<Dalek> dal = new ArrayList<Dalek>();

    /**
     * The constructor for the game. Use it to initialize your game variables.
     * (create people, set positions, etc.)
     */
    public CatchGame() {
        //initializes the board, doctor and the daleks
        b = new Board(12, 12);

        doctor = new Doctor((int) (Math.random() * 12), (int) (Math.random() * 12));

        for (int i = 0; i < 3; i++) {
            dal.add(new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12)));

        }


    }

    /**
     * The playGame method begins and controls a game: deals with when the user
     * selects a square, when the Daleks move, when the game is won/lost.
     */
    public void playGame() {
        //INITIAL SPAWN
        b.putPeg(Color.GREEN, doctor.getRow(), doctor.getCol());

        for (Dalek dalek : dal) {
            b.putPeg(Color.BLACK, dalek.getRow(), dalek.getCol());
        }

        //win/lose condition to lopo the game logic
        while (win > 0 && lose > 0) {


            //get a coordinate from the player to mvoe the doctor later
            Coordinate c = b.getClick();

            //MOVE THE DOCTOR
            b.removePeg(doctor.getRow(), doctor.getCol());
            doctor.move(c.getRow(), c.getCol());
            b.putPeg(Color.GREEN, doctor.getRow(), doctor.getCol());

            //for loop to advanced towards the doctor
            for (Dalek dalek : dal) {
                if (dalek.hasCrashed() == false) {
                    for (int i = 0; i < dal.size(); i++) {
                        b.removePeg(dal.get(i).getRow(), dal.get(i).getCol());
                    }
                    dalek.advanceTowards(doctor);
                    for (int i = 0; i < dal.size(); i++) {
                        b.putPeg(Color.BLACK, dal.get(i).getRow(), dal.get(i).getCol());
                    }
                } else {
                    //else put a red peg on the daleks (backup red peg)
                    b.putPeg(Color.RED, dalek.getRow(), dalek.getCol());
                }
            }

            //check if player lost
            for (int x = 0; x < 3; x++) {
                if (dal.get(x).getRow() == doctor.getRow()
                        && dal.get(x).getCol() == doctor.getCol()) {
                    b.putPeg(Color.YELLOW, doctor.getRow(), doctor.getCol());
                    lose--;
                }
            }

            //for loop to determine if dalek has crashed
            //checks dalek at i with the dalek at j, which is one ahead in the list
            //if positions are equal, they crash
            for (int i = 0; i < dal.size(); i++) {
                for (int j = i + 1; j < dal.size(); j++) {
                    if (dal.get(i).getRow() == dal.get(j).getRow()
                            && dal.get(i).getCol() == dal.get(j).getCol()) {
                        if (dal.get(i).hasCrashed() == false || dal.get(j).hasCrashed() == false) {
                            win = win - 2;
                        }
                        dal.get(i).crash();
                        dal.get(j).crash();
                        b.putPeg(Color.RED, dal.get(i).getRow(), dal.get(i).getCol());
                        b.putPeg(Color.RED, dal.get(j).getRow(), dal.get(j).getCol());
                    }
                }
            }

        }
        //end game messages
        if (win <= 0) {
            b.displayMessage("Congrats, you won!");
        } else {
            b.displayMessage("Better luck next time!");
        }

    }
}
