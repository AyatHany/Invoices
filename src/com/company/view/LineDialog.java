package com.company.view;

import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;

public class LineDialog extends JDialog {
    private JTextField itemNameField;
    private JTextField itemCountField;
    private JTextField itemPriceField;
    private JLabel itemName;
    private JLabel itemCount;
    private JLabel itemPrice;
    private JButton Ok;
    private JButton Cancel;

    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getItemCountField() {
        return itemCountField;
    }

    public JTextField getItemPriceField() {
        return itemPriceField;
    }

    public LineDialog(Frame2 frame2){
        super(frame2);
        itemNameField=new JTextField(20);
        itemName=new JLabel("Item Name");

        itemCountField=new JTextField(20);
        itemCount=new JLabel("Item Count");

        itemPriceField=new JTextField(20);
        itemPrice=new JLabel("Item Price");

        Ok=new JButton("ok");
        Cancel=new JButton("cancel");

        Ok.setActionCommand("createItemOk");
        Cancel.setActionCommand("createItemCancel");

        Ok.addActionListener(frame2.getListener());
        Cancel.addActionListener(frame2.getListener());
        setLayout(new GridLayout(4,2));

        add(itemName);
        add(itemNameField);
        add(itemCount);
        add(itemCountField);
        add(itemPrice);
        add(itemPriceField);
        add(Ok);
        add(Cancel);
        setModal(true);
        pack();
    }

}
