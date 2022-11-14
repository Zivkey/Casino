package Roullete.Threads;

import Roullete.Util.RoulleteUtil;
import javafx.animation.PathTransition;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

import java.util.List;

/**
 * Thread koji sluzi za pomeranje loptice
 */
public class FewSeconds implements Runnable {

    private PathTransition pathTransition;
    private List<Integer> rezults;
    private Circle circle;
    private Button winningButton;

    public FewSeconds(PathTransition pathTransition, List<Integer> rezults, Circle circle, Button winningButton) {
        this.pathTransition = pathTransition;
        this.rezults = rezults;
        this.circle = circle;
        this.winningButton = winningButton;
    }

    /**
     * Metoda koja broj x na svaku sekundu menja u broj od 0 do 5
     * ako broj bude 1 onda se putanja kuglice zaustavlja i metoda odobrava klik na dugme collect
     */
    @Override
    public void run() {
        int x = 0;
        try {
            while (x != 1) {
                long sleepTime = (long) (Math.random() * 1500 + 800);
                Thread.sleep(sleepTime);
                x = (int) (Math.random() * 4 + 0);
            }
            rezults.add(RoulleteUtil.winningNumber(circle.getTranslateX()));
            pathTransition.stop();
            winningButton.setDisable(false);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

