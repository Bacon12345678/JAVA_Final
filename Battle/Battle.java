package Battle;

import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;

import PlayerTeam.Player;
import Pokemon.Pokemon;
import Skills.Skill;
import GameManager.GameManager;
import GameManager.GameUI;
import GameManager.MessageManager;

public class Battle {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private GameManager gameManager;
    

    public Battle(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1; // 預設玩家1先手
    }

    public void startBattle() {
        gameManager = new GameManager();

        MessageManager.log("對戰開始！");
        MessageManager.log("");

        playTurn(currentPlayer);
    }

    public void playTurn(Player attacker) {
        Player defender = (GameManager.currentPlayer == 1) ? player2 : player1;
        
        MessageManager.log(attacker.getName() + " 的回合！");

        Pokemon attackerPokemon = attacker.getCurrentPokemon();

        Pokemon defendPokemon =  defender.getCurrentPokemon();

        JPanel mainPanel = GameUI.getMainPanel();

        
        setSkillBtn(mainPanel, attackerPokemon, defendPokemon);

        // MessageManager.log(attackerPokemon.getName() + "使出了攻擊！");
        // MessageManager.log("");

        // MessageManager.log(defender.getName() + "的寶可夢：");
        // MessageManager.log("名稱：" + defendPokemon.getName());
        // MessageManager.log("");

        // 判斷防禦方寶可夢是否倒下
    }

    public void switchTurn() {
        currentPlayer = (GameManager.currentPlayer == 1) ? player2 : player1;
        GameManager.currentPlayer = (GameManager.currentPlayer == 1) ? 2 : 1;
        playTurn(currentPlayer);
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

    public void setSkillBtn(JPanel mainPanel, Pokemon attackerPokemon, Pokemon defenderPokemon) {
        removeButtons(mainPanel);
        if (GameManager.currentPlayer == 1) {
            int player1X = 100; // 玩家 1 的按鈕 X 坐標
            int player1Y = mainPanel.getHeight() / 5; // 玩家 1 的起始 Y 坐標
            setPlayerSkillButtons(mainPanel, player1X, player1Y * 3, attackerPokemon, defenderPokemon);

        } else {
            int player2X = mainPanel.getWidth() - 200; // 玩家 2 的按鈕 X 坐標 (右側)
            int player2Y = mainPanel.getHeight() / 5; // 玩家 2 的起始 Y 坐標
            setPlayerSkillButtons(mainPanel, player2X, player2Y * 3, attackerPokemon, defenderPokemon);
        }
    }
    
    private void setPlayerSkillButtons(JPanel mainPanel, int xPosition, int yStart, Pokemon attackerPokemon, Pokemon defenderPokemon) {
        int yPosition = yStart;

        // boolean isPlayerTurn = (currentPlayerTurn == player.getId());
        for (int i = 0; i < 3; i++) {
            Skill skill = attackerPokemon.getSkill(i);
            JButton skillButton = new JButton(skill.getName());
            skillButton.setBounds(xPosition, yPosition, 100, 50);
            yPosition += 60; // 每個按鈕之間間隔 60px

            skillButton.addActionListener(e -> {

                Skill.normalAttack(attackerPokemon, defenderPokemon).run();

                if( GameManager.currentPlayer == 1 ) {
                    GameManager.updateLifeBars(attackerPokemon, defenderPokemon);
                    
                } else {
                    GameManager.updateLifeBars(defenderPokemon, attackerPokemon);
                }

                if (defenderPokemon.isFainted()) {
                    MessageManager.log(defenderPokemon.getName() + " 倒下了！");
                }

                MessageManager.log("按按鈕");
                switchTurn();
  
            });
    
            mainPanel.add(skillButton);
        }
    
        // 撤退按鈕
        JButton switchButton = new JButton("撤退");
        switchButton.setBounds(xPosition, yPosition, 100, 50);
        switchButton.addActionListener(e -> {
            MessageManager.log(" 撤退，切換寶可夢！");
            mainPanel.removeAll(); // 清空按鈕
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        mainPanel.add(switchButton);
    
        mainPanel.revalidate();
        mainPanel.repaint();
    }      

    public void removeButtons(JPanel mainPanel) {
        for (int i = mainPanel.getComponentCount() - 1; i >= 0; i--) {
            if (mainPanel.getComponent(i) instanceof JButton) {
                mainPanel.remove(i);
            }
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
