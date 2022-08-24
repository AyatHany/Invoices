package com.company.view;

import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;

public class HeaderDialog extends JDialog{
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
    private JTextField CustomerNameField;
    private JTextField DateField;
    private JLabel CustomerName;
    private JLabel Date;

    private JButton Ok;
    private JButton Cancel;

    public HeaderDialog(Frame2 frame2){
        super(frame2);
        CustomerName=new JLabel("Customer Name :");
        CustomerNameField=new JTextField(20);

        Date=new JLabel("Invoice Date :");
        DateField=new JTextField(20);
        Ok=new JButton("ok");
        Cancel=new JButton("cancel");

        Ok.setActionCommand("createInvOk");
        Cancel.setActionCommand("CancelInv");

        Ok.addActionListener(frame2.getListener());
        Cancel.addActionListener(frame2.getListener());
        setLayout(new GridLayout(3,2));

        add(Date);
        add(DateField);
        add(CustomerName);
        add(CustomerNameField);
        add(Ok);
        add(Cancel);
        setModal(true);

        pack();

    }

    public JTextField getCustomerNameField() {
        return CustomerNameField;
    }
    public JTextField getDateField() {
        return DateField;
    }
}
