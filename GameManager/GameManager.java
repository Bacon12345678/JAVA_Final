package GameManager;
import java.util.ArrayList;
import java.util.List;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import Battle.Battle;
import PlayerTeam.Player;
import Pokemon.Pokemon;
import Skills.Skill;
import GameManager.MessageManager;

public class GameManager {
    private Player player1;
    private Player player2;
    private Battle battle;
    private static JLabel player1HealthLabel;
    private static JLabel player2HealthLabel;
    private static JProgressBar player1HealthBar;
    private static JProgressBar player2HealthBar;
    private Pokemon player1Pokemon;
    private Pokemon player2Pokemon;
    private JLabel pokemonImageLabel;

    public static int currentPlayer;

    public void initializeGame() {
        MessageManager.log("初始化遊戲...");

        player1 = new Player("玩家1");
        player2 = new Player("玩家2");

        // 添加寶可夢給玩家1

        player1.addPokemon(Pokemon.getPokemonByName("No.001"));
        player1.addPokemon(Pokemon.getPokemonByName("No.003"));
        player1.addPokemon(Pokemon.getPokemonByName("No.005"));

        // 添加寶可夢給玩家2
        player2.addPokemon(Pokemon.getPokemonByName("No.002"));
        player2.addPokemon(Pokemon.getPokemonByName("No.004"));
        player2.addPokemon(Pokemon.getPokemonByName("No.006"));

        currentPlayer = 1;
        player1.setCurrentPokemon(0);
        player2.setCurrentPokemon(0);

        player1Pokemon = player1.getCurrentPokemon();
        player2Pokemon = player2.getCurrentPokemon();

        // 建立玩家
        MessageManager.log("遊戲初始化完成！");
        MessageManager.log("");

        startGame();        
    }



    public void startGame() {
        battle = new Battle(player1, player2);
        battle.startBattle();
        initailHealthBars();
    }

    public void initailHealthBars() {
        // 初始化玩家 1 的血條
        player1HealthBar = createHealthBar(10, 180, 200, 30, player1Pokemon.getCurrentHealth());
        player1HealthLabel = createHealthLabel(player1Pokemon.getName(), 10, 140, 100, 30);
        

        JPanel mainPanel = GameUI.getMainPanel();
        int windowWidth = mainPanel.getWidth();
        
        // 初始化玩家 2 的血條
        player2HealthBar = createHealthBar(windowWidth - 210, 180, 200, 30, player2Pokemon.getCurrentHealth());
        player2HealthLabel = createHealthLabel(player2Pokemon.getName(), windowWidth - 100, 140, 100, 30);
        setPokemonImage(mainPanel, player1Pokemon, 10, 140 * 2, 300, 300);
        setPokemonImage(mainPanel, player2Pokemon, windowWidth - 300, 140 * 2, 300, 300);

        // 將所有元件加入主面板
        
        mainPanel.add(player1HealthBar);
        mainPanel.add(player1HealthLabel);
        mainPanel.add(player2HealthBar);
        mainPanel.add(player2HealthLabel);

        mainPanel.revalidate();
        mainPanel.repaint();
    }


    public void setPokemonImage(JPanel mainPanel, Pokemon pokemon, int x, int y, int width, int height) {
    
        // 加載寶可夢圖片
        ImageIcon icon = new ImageIcon(pokemon.getPokePic());
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
    
        // 使用 JLabel 顯示圖片
        pokemonImageLabel = new JLabel(icon);
        pokemonImageLabel.setBounds(x, y, width, height); // 設置圖片的位置和大小
        mainPanel.add(pokemonImageLabel);
    
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // 通用方法：創建血條
    private JProgressBar createHealthBar(int x, int y, int width, int height, int maxHealth) {
        JProgressBar healthBar = new JProgressBar();
        healthBar.setMinimum(0);
        healthBar.setMaximum(maxHealth);
        healthBar.setValue(maxHealth); // 初始值為最大血量
        healthBar.setString(maxHealth + " / " + maxHealth); // 顯示實際數字
        healthBar.setStringPainted(true); // 顯示文字
        healthBar.setBounds(x, y, width, height);
        
        // 設置字體更大
        healthBar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        return healthBar;
    }
    

    // 通用方法：創建血量標籤
    private JLabel createHealthLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        return label;
    }

    // 更新血量條的方法
    public static void updateLifeBars(Pokemon player1, Pokemon player2) {

        // 更新玩家 1 的血條與標籤
        player1HealthBar.setValue(player1.getCurrentHealth());
        player1HealthBar.setString(player1.getCurrentHealth() + " / " + player1.getMaxHealth()); // 更新顯示文字
        player1HealthLabel.setText(player1.getName() );

        // // 更新玩家 2 的血條與標籤
        player2HealthBar.setValue(player2.getCurrentHealth());
        player2HealthBar.setString(player2.getCurrentHealth() + " / " + player2.getMaxHealth()); // 更新顯示文字
        player2HealthLabel.setText(player2.getName());
    }

    

    // public void setSkillBtn(Player player, JPanel mainPanel) {
    //     // 獲取當前寶可夢的技能列表
    //     List<Skill> skills = player.getCurrentPokemon().getSkills();
    //     int yPosition = 100; // 起始位置

    //     for (int i = 0; i < skills.size(); i++) {
    //         Skill skill = skills.get(i);
    //         JButton skillButton = new JButton(skill.getName()); // 按鈕名稱為技能名稱
    //         skillButton.setBounds(10, yPosition, 100, 50); // 動態設置位置
    //         yPosition += 60; // 每個按鈕之間間隔 60px

    //         // 綁定技能使用事件
    //         skillButton.addActionListener(e -> useSkill(player, skill, mainPanel));
    //         mainPanel.add(skillButton);
    //     }

    //     // 撤退按鈕
    //     JButton switchButton = new JButton("撤退");
    //     switchButton.setBounds(10, yPosition, 100, 50);
    //     switchButton.addActionListener(e -> {
    //         MessageManager.log(player.getName() + " 撤退，切換寶可夢！");
    //         player.choosePokemon(nextPokemonIndex()); // 切換到下一個寶可夢（需實現選擇邏輯）
    //         mainPanel.removeAll(); // 清空按鈕
    //         setSkillBtn(player, mainPanel); // 更新按鈕
    //         mainPanel.revalidate();
    //         mainPanel.repaint();
    //     });
    //     mainPanel.add(switchButton);

    //     mainPanel.revalidate();
    //     mainPanel.repaint();
    // }
    // public void setSkillBtn(Player player1, Player player2, JPanel mainPanel) {
    //     // 設置玩家 1 的按鈕
    //     int player1X = 100; // 玩家 1 的按鈕 X 坐標
    //     int player1Y = mainPanel.getHeight() * 3 / 5; // 玩家 1 的起始 Y 坐標
    //     setPlayerSkillButtons(player1, mainPanel, player1X, player1Y);
    
    //     // 設置玩家 2 的按鈕
    //     int player2X = mainPanel.getWidth() - 200; // 玩家 2 的按鈕 X 坐標 (右側)
    //     int player2Y = mainPanel.getHeight() * 3 / 5; // 玩家 2 的起始 Y 坐標
    //     setPlayerSkillButtons(player2, mainPanel, player2X, player2Y);
    // }
    
    // private void setPlayerSkillButtons(Player player, JPanel mainPanel, int xPosition, int yStart) {
    //     List<Skill> skills = player.getCurrentPokemon().getSkills();
    //     int yPosition = yStart;
    
    //     for (int i = 0; i < skills.size(); i++) {
    //         Skill skill = skills.get(i);
    //         JButton skillButton = new JButton(skill.getName());
    //         skillButton.setBounds(xPosition, yPosition, 100, 50);
    //         yPosition += 60; // 每個按鈕之間間隔 60px
    
    //         // 綁定技能事件
    //         skillButton.addActionListener(e -> useSkill(player, skill, mainPanel));
    //         mainPanel.add(skillButton);
    //     }
    
    //     // 撤退按鈕
    //     JButton switchButton = new JButton("撤退");
    //     switchButton.setBounds(xPosition, yPosition, 100, 50);
    //     switchButton.addActionListener(e -> {
    //         MessageManager.log(player.getName() + " 撤退，切換寶可夢！");
    //         player.choosePokemon(nextPokemonIndex(player)); // 切換寶可夢
    //         mainPanel.removeAll(); // 清空按鈕
    //         setSkillBtn(GameManager.getPlayer1(), GameManager.getPlayer2(), mainPanel); // 更新按鈕
    //         mainPanel.revalidate();
    //         mainPanel.repaint();
    //     });
    //     mainPanel.add(switchButton);
    
    //     mainPanel.revalidate();
    //     mainPanel.repaint();
    // }
    
    // private void updateButtonVisibility() {
    //     mainPanel.removeAll(); // 清空按鈕
    //     setSkillBtn(player1, player2, mainPanel, playerTurn); // 根據當前回合重新設置按鈕
    //     mainPanel.revalidate();
    //     mainPanel.repaint();
    // }




}
