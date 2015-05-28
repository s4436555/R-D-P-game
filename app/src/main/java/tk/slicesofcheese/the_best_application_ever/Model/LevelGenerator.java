package tk.slicesofcheese.the_best_application_ever.Model;

import tk.slicesofcheese.the_best_application_ever.Model.Entities.Enemies.TestEnemy;
import tk.slicesofcheese.the_best_application_ever.Model.Entities.Enemy;
import tk.slicesofcheese.the_best_application_ever.Model.Entities.Player;

/**
 * Created by jonathan on 28-5-15.
 */
public class LevelGenerator {

    public Level genLevel () {
        Level temp = new Level(9, 9);

        temp.addEnemy(new Enemy(1, 3));
        temp.addEnemy(new TestEnemy(8, 4));
        temp.addPlayer(new Player(0, 0, 5));

        for (int i = 0; i < 8; i++)
            temp.addWall(i, i);

        return temp;
    }
}
