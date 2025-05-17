import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class TodoApp extends JFrame {
    private JPanel taskPanel;
    private JTextField taskInput;

    public TodoApp() {
        setTitle("To-Do List with Checkboxes");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        taskInput = new JTextField();
        JButton addButton = new JButton("Add Task");
        JButton deleteButton = new JButton("Delete Completed");

        // Top input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(taskInput, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        // Bottom button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add Task Action
        addButton.addActionListener(e -> {
            String taskText = taskInput.getText().trim();
            if (!taskText.isEmpty()) {
                JCheckBox taskCheckBox = new JCheckBox(taskText);
                taskPanel.add(taskCheckBox);
                taskPanel.revalidate();
                taskPanel.repaint();
                taskInput.setText("");
            }
        });

        // Delete Completed Tasks
        deleteButton.addActionListener(e -> {
            Component[] components = taskPanel.getComponents();
            ArrayList<Component> toRemove = new ArrayList<>();

            for (Component c : components) {
                if (c instanceof JCheckBox) {
                    JCheckBox cb = (JCheckBox) c;
                    if (cb.isSelected()) {
                        toRemove.add(cb);
                    }
                }
            }

            for (Component c : toRemove) {
                taskPanel.remove(c);
            }

            taskPanel.revalidate();
            taskPanel.repaint();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TodoApp::new);
    }
}
