package free.lunch.aDventure.Model;

import free.lunch.aDventure.Model.Entities.Enemies.Dragon;
import free.lunch.aDventure.Model.Entities.Enemies.Horse;
import free.lunch.aDventure.Model.Entities.Enemies.Snake;
import free.lunch.aDventure.Model.Entities.Enemies.TestEnemy;
import free.lunch.aDventure.Model.Entities.Player;

/**
 * A class that Jan Potma should implement properly
 */
public class LevelGenerator {

    public Level genLevel () {
        Level temp = new Level(9, 9);

        temp.addEnemy(new Dragon(1, 3));
        temp.addEnemy(new TestEnemy(8, 4));
        temp.addPlayer(new Player(0, 0));
        temp.addEnemy(new Snake(6, 0));
        temp.addEnemy(new Horse(8, 8));

        for (int i = 0; i < 8; i++)
            temp.addWall(i, i);

        return temp;
    }
}
