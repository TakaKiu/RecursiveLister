import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RecursiveLister extends JFrame {

    private JLabel titleLabel;
    private JButton startButton, quitButton;
    private JTextArea resultTextArea;
    private JScrollPane scrollPane;

    public RecursiveLister() {
        setTitle("Recursive Lister");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        titleLabel = new JLabel("Recursive Lister");
        startButton = new JButton("Start");
        quitButton = new JButton("Quit");
        resultTextArea = new JTextArea();
        scrollPane = new JScrollPane(resultTextArea);

        setLayout(new BorderLayout());

        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFileChooser();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void showFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            displayFiles(selectedDirectory);
        }
    }

    private void displayFiles(File directory) {
        resultTextArea.setText(""); 

        if (directory.isDirectory()) {
            listFiles(directory);
        } else {
            resultTextArea.append(directory.getAbsolutePath() + "\n");
        }
    }

    private void listFiles(File directory) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listFiles(file); 
                } else {
                    resultTextArea.append(file.getAbsolutePath() + "\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }

                RecursiveLister recursiveLister = new RecursiveLister();
                recursiveLister.setVisible(true);
            }
        });
    }
}
