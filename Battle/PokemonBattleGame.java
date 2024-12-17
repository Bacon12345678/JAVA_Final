package Battle;
import javax.swing.*; // 引入Swing元件
import java.awt.*;    // 用於圖形設定

public class PokemonBattleGame {
    public static void main(String[] args) {
        // 建立遊戲視窗
        JFrame gameFrame = new JFrame("Pokemon山寨");

        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameFrame.setResizable(true); 

        JPanel backgroundPanel = new JPanel() {
            // 自訂繪製方法
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("pics/background.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // 設定背景面板布局為空
        backgroundPanel.setLayout(null);

        // 新增一個按鈕
        JButton startButton = new JButton("開始遊戲");
        startButton.setBounds(100, 100, 200, 50); // 設定按鈕的位置和大小

        // 添加按鈕到背景面板
        backgroundPanel.add(startButton);

        // 將背景面板設置為 JFrame 的內容
        gameFrame.setContentPane(backgroundPanel);

        // 顯示視窗
        gameFrame.setVisible(true);
    }
}

