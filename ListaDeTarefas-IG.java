import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ListaDeTarefas {
    private DefaultListModel<String> listModel;
    private JList<String> listaTarefas;

    public ListaDeTarefas() {
        JFrame frame = new JFrame("Lista de Tarefas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        listModel = new DefaultListModel<>();
        listaTarefas = new JList<>(listModel);

        JTextField tarefaField = new JTextField(10);
        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listModel.addElement(tarefaField.getText());
                tarefaField.setText("");
            }
        });

        JButton removeButton = new JButton("Remover");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listaTarefas.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(tarefaField);
        panel.add(addButton);
        panel.add(removeButton);

        frame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(listaTarefas));
        frame.getContentPane().add(BorderLayout.SOUTH, panel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ListaDeTarefas();
            }
        });
    }
}
