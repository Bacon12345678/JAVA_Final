package Battle;

import java.util.Scanner;

import javax.swing.JButton;

import PlayerTeam.Player;
import Pokemon.PokemonTestExample;
import Skills.Skill;
import GameManager.MessageManager;

public class Battle {
    private Player player1;
    private Player player2;
    private Player currentTurn;
    private PokemonTestExample currentPlayer1Pokemon;
    private PokemonTestExample currentPlayer2Pokemon;
    private int playerTurn = 1;
    

    public Battle(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentTurn = player1; // 預設玩家1先手
    }

    public void startBattle() {
        MessageManager.log("對戰開始！");
        MessageManager.log("");
        // while (!isGameOver()) {
        //     playTurn(currentTurn);
        //     switchTurn();
        // }
        // determineWinner();
    }

    private void playTurn(Player attacker) {
        Player defender = (attacker == player1) ? player2 : player1;
        MessageManager.log(attacker.getName() + " 的回合！");

        PokemonTestExample attackerPokemon = attacker.getPokemon(0);

        MessageManager.log(attackerPokemon.getName() + "使出了攻擊！");
        MessageManager.log("");
        
        // 顯示選項：攻擊或切換寶可夢
        // System.out.println("1. 攻擊");
        // System.out.println("2. 切換寶可夢");
        // Scanner scanner = new Scanner(System.in);
        // int choice = scanner.nextInt();

        // if (choice == 1) {
        //     // 顯示技能列表
        //     System.out.println("選擇技能：");
        //     attacker.getCurrentPokemon().printSkills();
        //     int skillIndex = scanner.nextInt();
        //     Skill skill = attacker.getCurrentPokemon().getSkills().get(skillIndex);
            
        //     // 執行攻擊
        //     attacker.getCurrentPokemon().attack(defender.getCurrentPokemon(), skill);
        // } else if (choice == 2) {
        //     // 切換寶可夢
        //     System.out.println("選擇要切換的寶可夢：");
        //     attacker.printPokemonList();
        //     int pokemonIndex = scanner.nextInt();
        //     attacker.choosePokemon(pokemonIndex);
        // }

        PokemonTestExample defendPokemon =  defender.getPokemon(0);

        defendPokemon.takeDamage(30);

        MessageManager.log(defender.getName() + "的寶可夢：");
        MessageManager.log("名稱：" + defendPokemon.getName());
        MessageManager.log("剩餘血量：" + defendPokemon.getHealth());
        MessageManager.log("");

        // 判斷防禦方寶可夢是否倒下
        if (defender.getPokemon(0).isFainted()) {
            MessageManager.log(defender.getPokemon(0).getName() + " 倒下了！");
            MessageManager.log("");
        }
    }

    private void switchTurn() {
        currentTurn = (currentTurn == player1) ? player2 : player1;
    }

    private boolean isGameOver() {
        return player1.getPokemon(0).isFainted() || player2.getPokemon(0).isFainted();
    }

    private void determineWinner() {
        if (player1.getPokemon(0).isFainted()) {
            MessageManager.log(player2.getName() + " 獲勝！");
        } else {
            MessageManager.log(player1.getName() + " 獲勝！");
        }
    }

    public void onPlayerAction(PlayerTestExample player, Skill skill) {
        if ((playerTurn == 1 && player != player1) || (playerTurn == 2 && player != player2)) {
            MessageManager.log("不是你的回合！");
            return;
        }

        // 执行技能逻辑
        PlayerTestExample defender = (player == player1) ? player2 : player1;
        // executeSkill(player, defender, skill);

        // 切换回合
        switchTurn();
    }
}
