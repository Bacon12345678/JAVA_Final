package GameManager;
import javax.swing.JTextArea;

public class MessageManager {
    private static JTextArea messageArea; // 全域的訊息區域

    // 初始化 JTextArea
    public static void setMessageArea(JTextArea textArea) {
        messageArea = textArea;
    }

    // 訊息顯示方法
    public static void log(String message) {
        if (messageArea != null) {
            messageArea.append(message + "\n"); // 將訊息添加到 JTextArea
            messageArea.setCaretPosition(messageArea.getDocument().getLength()); // 自動滾動到底部
        } else {
            System.out.println(message); // 備用輸出到控制台
        }
    }
}
