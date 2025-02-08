package chat_server;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.rmi.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class ChatClientGUI extends JFrame {
    private JTextArea chatArea;
    private JTextField messageField, pseudoField;
    private JLabel clientTimeLabel, serverTimeLabel;
    private JButton sendButton;
    private ChatClient backend;

    public ChatClientGUI() {}

    public void configureGUI(ChatClient backend) {
        this.backend = backend;

        setTitle("Chat Client");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel (pseudo + times)
        JPanel topPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setBackground(new Color(230, 230, 250)); // Lavanda

        pseudoField = new JTextField(10);
        pseudoField.setText(backend.getPseudo());
        pseudoField.setEditable(false);
        pseudoField.setFont(new Font("SansSerif", Font.BOLD, 14));

        clientTimeLabel = new JLabel("Client Time: " + formatTime(new Date()));
        clientTimeLabel.setFont(new Font("SansSerif", Font.BOLD, 12));

        serverTimeLabel = new JLabel("Server Time: --:--:--");
        serverTimeLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        serverTimeLabel.setForeground(new Color(50, 50, 150));

        topPanel.add(new JLabel("Pseudo:"));
        topPanel.add(pseudoField);
        topPanel.add(clientTimeLabel);
        topPanel.add(serverTimeLabel);

        add(topPanel, BorderLayout.NORTH);

        // Chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        chatArea.setBackground(new Color(245, 245, 245)); // Cinza claro
        chatArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        // Bottom panel (input + button)
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        messageField = new JTextField();
        messageField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        messageField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        sendButton = new JButton("Send");
        sendButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        sendButton.setBackground(new Color(50, 150, 50)); // Verde escuro
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        // Event Listeners
        sendButton.addActionListener(e -> sendMessage());
        messageField.addActionListener(e -> sendMessage());

        startServerTimeUpdater();
    }



    private void startServerTimeUpdater() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InterfaceTimeServer server = (InterfaceTimeServer) Naming.lookup(Config.getTimeServer());
                    String serverTime = server.getTime();
                    SwingUtilities.invokeLater(() -> updateTime(formatTime(new Date()), serverTime));
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        });
        timer.setRepeats(true);
        timer.start();
    }


    public void receiveMessage(Message message) {
        chatArea.append(message.getPseudo() + ": " + message.getMessage() + "\n");
    }

    public void updateTime(String clientTime, String serverTime) {
        clientTimeLabel.setText("Client Time: " + clientTime);
        serverTimeLabel.setText("Server Time: " + serverTime);
    }

    private void sendMessage() {
        try {
            String text = messageField.getText().trim();
            if (!text.isEmpty()) {
                backend.sendMessage(text);
                messageField.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String formatTime(Date date) {
        return new SimpleDateFormat("HH:mm:ss").format(date);
    }

    public void launchUI(ChatClient backend) {
        this.configureGUI(backend);
        SwingUtilities.invokeLater(() -> this.setVisible(true));
    }
}
