package free.lunch.aDventure;

import free.lunch.aDventure.Model.Entities.Enemy;
import free.lunch.aDventure.Model.Entities.Player;
import free.lunch.aDventure.Model.Level;

/**
 * Created by jonathan on 1-6-15.
 */
public class EnemyController {

    private Level level;

    public EnemyController (Level level) {
        this.level = level;
    }

    public void moveEnemies (){
        int total = level.getEnemyCount();
        for (int i = 0; i < total; i++){
            Enemy enemy = level.getEnemy(i);
            Player player = level.getPlayer();
            for (int[] move: enemy.getMoves(player.getX(), player.getY())){
                if(level.isValid(move[0], move[1])) {
                    boolean test = (level.getEntity(move[0],move[1]) instanceof Player);
                    if (level.isFree(move[0], move[1]) || test) {
                        level.moveEntity(enemy.getX(), enemy.getY(), move[0], move[1]);

                        if (test) {
                            System.out.println("You lost!");
                        }
                        break;
                    }
                }
            }
        }
    }
}
