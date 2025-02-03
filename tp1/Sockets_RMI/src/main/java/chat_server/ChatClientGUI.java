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

        // Top panel with pseudo input and time labels
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pseudoField = new JTextField(10);
        pseudoField.setText(backend.getPseudo());
        pseudoField.setEditable(false);
        clientTimeLabel = new JLabel("Client Time: " + formatTime(new Date()));
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
        sendButton = new JButton("Send");

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

        startServerTimeUpdater();
    }


    private void startServerTimeUpdater() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InterfaceTimeServer server = (InterfaceTimeServer) Naming.lookup("rmi://localhost/TimeServer");
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
        clientTimeLabel.setText("Client Time: " + serverTime);
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
