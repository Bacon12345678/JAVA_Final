package GameManager;
import javax.swing.*; // 引入Swing元件
import java.awt.*;    // 用於圖形設定
import java.io.PrintStream;
import GameManager.GameManager;
import GameManager.MessageManager;

public class GameUI {

    public static JTextArea messageArea;
    public static JFrame gamPanel;
    public static JPanel backgroundPanel;

    public static void main(String[] args) {
        // 建立遊戲視窗
        
        JFrame gameFrame = new JFrame("Pokemon");

        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameFrame.setResizable(true); 

        backgroundPanel = new JPanel() {
            // 自訂繪製方法
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("pics/Backgrounds/BackgroundDay.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // 設定背景面板布局為空
        backgroundPanel.setLayout(null);

        // 新增一個按鈕
        JButton startButton = new JButton("PVP對戰");

        // 將背景面板設置為 JFrame 的內容
        gameFrame.setContentPane(backgroundPanel);

        startButton.setSize(200, 50); 

        // 添加按鈕到背景面板
        backgroundPanel.add(startButton);

        JTextArea actionLog = new JTextArea();
        actionLog.setEditable(false); // 設定文字區域不可編輯
        actionLog.setLineWrap(true);  // 自動換行
        actionLog.setWrapStyleWord(true); // 單詞換行

        // 包裝 JTextArea 到 JScrollPane
        JScrollPane scrollPane = new JScrollPane(actionLog);

        MessageManager.setMessageArea(actionLog);

        scrollPane.setSize(700, 150);

         // 調整按鈕與文字區域的位置
        backgroundPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                // 背景面板大小
                int panelWidth = backgroundPanel.getWidth();
                int panelHeight = backgroundPanel.getHeight();

                // 按鈕的中心位置
                int buttonWidth = startButton.getWidth();
                int buttonHeight = startButton.getHeight();
                int buttonX = (panelWidth - buttonWidth) / 2;
                int buttonY = (panelHeight - buttonHeight) / 2;
                startButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);

                // 文字區域的中心位置 (1/4 高度處)
                int scrollPaneWidth = scrollPane.getWidth();
                int scrollPaneHeight = scrollPane.getHeight();
                int scrollPaneX = (panelWidth - scrollPaneWidth) / 2;
                int scrollPaneY = panelHeight * 3 / 5; // 上方 1/4 高度
                scrollPane.setBounds(scrollPaneX, scrollPaneY, scrollPaneWidth, scrollPaneHeight);
            }
        });

        backgroundPanel.add(scrollPane);

        GameManager gameManager = new GameManager();
        startButton.addActionListener(e -> {
            gameManager.initializeGame();
            startButton.setVisible(false);
        });

        // 顯示視窗
        gameFrame.setVisible(true);
    }

    public static JPanel getMainPanel() {
        return backgroundPanel;
    }

    
}

