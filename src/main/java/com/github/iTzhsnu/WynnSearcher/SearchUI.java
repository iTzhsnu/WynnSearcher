package com.github.iTzhsnu.WynnSearcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SearchUI extends JFrame implements ActionListener {
    //private final JPanel panel;

    private final JButton searchB = new JButton("Search");
    private final JComboBox<String> idBox = new JComboBox<>();

    private final JTextField searchF = new JTextField(); //Search Text Field
    private final Container contentPane; //Content Pane
    private final JButton idAddNB; //ID Add Button (Need)
    //private final List<JButton> idRemoveNB; //ID Remove Button (Need)
    //private final List<JComboBox<String>> idBox;

    public SearchUI() {
        setTitle("Wynncraft Searcher");
        setBounds(100, 300, 1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = getContentPane();
        setLayout(null);

        //Search Text Field
        searchF.setBounds(10, 30, 200, 30);
        searchF.addActionListener(this);

        JLabel name = new JLabel("Name:");
        name.setBounds(10, 0, 60, 30);

        //Click to Search
        searchB.setBounds(400, 425, 80, 30);
        searchB.addActionListener(this);

        //IDs
        idBox.setBounds(30, 80, 160, 20);
        for (String s : IDBoxAdapter.idList) {
            idBox.addItem(s);
        }
        idBox.setEditable(true);

        JTextField textField = (JTextField) idBox.getEditor().getEditorComponent();
        textField.addKeyListener(new IDBoxAdapter(idBox));

        //Add Need ID Button
        idAddNB = new JButton();
        idAddNB.setIcon(new ImageIcon("./src/main/resources/icons/plus.png"));
        idAddNB.addActionListener(this);
        idAddNB.setToolTipText("Click Add Need ID");
        idAddNB.setBounds(400, 30, 20, 20);


        //Add Contents
        contentPane.add(name);
        contentPane.add(searchB);
        contentPane.add(idBox);
        contentPane.add(searchF);
        contentPane.add(idAddNB);

    }

    public static void main(String[] args) {
        new SearchUI().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //e.getSource() == (Button)
        if (e.getSource() == searchB) {
        }

    }

    public void setIDBoxAndIDField(List<JComboBox<String>> boxes, List<JTextField> min, List<JTextField> max, int baseX, int baseY, int length) {
        for (int i = 0; length > i; ++i) {
            //ID Box
            boxes.add(new JComboBox<>());
            boxes.get(i).setBounds(baseX, baseY, 160, 20);
            boxes.get(i).setEditable(true);


            contentPane.add(boxes.get(i));

            //Min


            //Max


            //Add
        }
    }

    public void setSearchableIDBox(JComboBox<String> box) {

    }

    public void setIntField(JTextField field) {
        //field.setBounds();
    }

}
