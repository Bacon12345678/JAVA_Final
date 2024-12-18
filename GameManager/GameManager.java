package GameManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import Battle.Battle;
import PlayerTeam.PlayerTestExample;
import Pokemon.PokemonTestExample;
import GameManager.MessageManager;

public class GameManager {
    private PlayerTestExample player1;
    private PlayerTestExample player2;
    private Battle battle;

    public void initializeGame() {
        MessageManager.log("初始化遊戲...");

        // 建立玩家
        player1 = new PlayerTestExample("玩家1");
        player2 = new PlayerTestExample("玩家2");

        // 添加寶可夢給玩家1
        player1.addPokemon(new PokemonTestExample("妙蛙種子",  100));
        player1.addPokemon(new PokemonTestExample("小火龍", 100));

        // 添加寶可夢給玩家2
        player2.addPokemon(new PokemonTestExample("傑尼龜", 100));
        player2.addPokemon(new PokemonTestExample("皮卡丘", 100));

        MessageManager.log("遊戲初始化完成！");
        MessageManager.log("");
        startGame();
    }

    public void startGame() {
        battle = new Battle(player1, player2);
        battle.startBattle();
    }

}
