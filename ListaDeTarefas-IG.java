import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ListaDeTarefas {
    private DefaultListModel<String> listModel;
    private JList<String> listaTarefas;

    public ListaDeTarefas() {
        JFrame frame = new JFrame("Lista de Tarefas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        listModel = new DefaultListModel<>();
        listaTarefas = new JList<>(listModel);

        // Carregar tarefas do arquivo
        try (BufferedReader br = new BufferedReader(new FileReader("Tarefas.txt"))) {
            String tarefa;
            while ((tarefa = br.readLine()) != null) {
                listModel.addElement(tarefa);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JTextField tarefaField = new JTextField(10);
        JButton incluirButton = new JButton("Incluir");
        incluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.addElement(tarefaField.getText());
                tarefaField.setText("");
            }
        });

        JButton removerButton = new JButton("Remover");
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listaTarefas.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(tarefaField);
        panel.add(incluirButton);
        panel.add(removerButton);

        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(listaTarefas));

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
