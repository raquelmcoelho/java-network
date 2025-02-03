package chat_server;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.text.SimpleDateFormat;
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
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel with pseudo input and time labels
        JPanel topPanel = new JPanel(new FlowLayout());
        pseudoField = new JTextField(10);
        pseudoField.setText("Pseudo");
        clientTimeLabel = new JLabel("Client Time: " + getCurrentTime());
        serverTimeLabel = new JLabel("Server Time: --:--:--");

        topPanel.add(new JLabel("Pseudo:"));
        topPanel.add(pseudoField);
        topPanel.add(clientTimeLabel);
        topPanel.add(serverTimeLabel);

        add(topPanel, BorderLayout.NORTH);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        sendButton = new JButton("Enviar");

        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
    }

    public void receiveMessage(Message message) {
        chatArea.append(message.getPseudo() + ": " + message.getMessage() + "\n");
    }

    public void updateServerTime(String serverTime) {
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

    private String getCurrentTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public void launchUI(ChatClient backend) {
        this.configureGUI(backend);
        SwingUtilities.invokeLater(() -> this.setVisible(true));
    }
}
