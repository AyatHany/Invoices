package com.company.controller;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.company.model.*;

import com.company.view.Frame2;
import com.company.view.HeaderDialog;
import com.company.view.LineDialog;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;




public class Listener extends Component implements ActionListener , ListSelectionListener {
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
    private HeaderDialog headerDialog;
    private LineDialog lineDialog;
    Frame2 f ;
    public Listener(Frame2 f) {
        this.f = f;

    }
    FileOperations ff=new FileOperations(this.f);
    private void deleteInvoice(){
        int selectedRows= f.getjTable1().getSelectedRow();
        InvoiceHeader inv = f.getHeader().get(selectedRows);
        for (int i=0;i<f.getHeader().size();i++){
            if (f.getHeader().get(i).getInvoiceNum()==inv.getInvoiceNum()){
                f.getHeader().remove(i);
            }
        }
        f.getInvoicetableheader().fireTableDataChanged();


    }
    private void createNewInvoice(){
        headerDialog=new HeaderDialog(f);
        headerDialog.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Load File":
                try {
                    ArrayList<InvoiceHeader> h=ff.readFile();
                    f.setHeader(h);

                    f.setInvoicetableheader(new InvoiceHeaderTableModel(f.getHeader()));
                    f.getjTable1().setModel(f.getInvoicetableheader());

                    for(int i = 0;i<f.getHeader().size();i++)
                    {
                        System.out.println("Invoice "+f.getHeader().get(i).getInvoiceNum()+"\n" +
                                "{\n"+ "  "+(format.format(f.getHeader().get(i).getInvoiceDate()))+", "
                                +f.getHeader().get(i).getCustomerName());

                        for(int j = 0;j<f.getHeader().get(i).getInvoiceLines().size();j++)
                        {
                            System.out.println("Invoice"+f.getHeader().get(i).getInvoiceLines().get(j).getItemName()+", "
                                    +f.getHeader().get(i).getInvoiceLines().get(j).getItemPrice() +", "
                                    +f.getHeader().get(i).getInvoiceLines().get(j).getCount());
                        }

                        System.out.println("}\n");


                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Save file":
                try {
                    ff.writeFile( f.getHeader());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Create New Item":
                createNewItem();
                break;
            case "Create new invoice":
                createNewInvoice();
                break;
            case "Delete invoice":
                deleteInvoice();
                break;
            case "Delete Item":
               deleteItem();
                break;
            case "createInvOk":
                try {
                    createInvoiceOk();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "CancelInv":
                createInvoiceCancel();
                break;
            case "createItemOk":
                createItemOk();
                break;
            case "createItemCancel":
                createItemCancel();
                break;

        }
    }

    private void createItemCancel() {
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
    }

    private void createItemOk() {

        String itemName=lineDialog.getItemNameField().getText();
        //int num=f.getNextNum();
        int itemCount= Integer.parseInt(lineDialog.getItemCountField().getText());
        int itemPrice= Integer.parseInt(lineDialog.getItemPriceField().getText());
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog=null;

        InvoiceLines l= new InvoiceLines();
        l.setItemName(itemName);
        l.setCount(itemCount);
        l.setItemPrice(itemPrice);
        int selectedRows=f.getjTable1().getSelectedRow();
        InvoiceHeader inv=f.getHeader().get(selectedRows) ;
        inv.getInvoiceLines().add(l);

        //f.getLine().add(l);
        f.getLineTableModel().fireTableDataChanged();
    }

    private void createNewItem() {
        lineDialog = new LineDialog(f);
        lineDialog.setVisible(true);
    }

    private void deleteItem() {
        int selectedRows=f.getjTable1().getSelectedRow();
        int selectedRows2= f.getjTable2().getSelectedRow();
        InvoiceHeader inv = f.getHeader().get(selectedRows);
        InvoiceLines lines=inv.getInvoiceLines().get(selectedRows2);

        for (int i=0;i<f.getHeader().size();i++){
            if (f.getHeader().get(i).getInvoiceNum()==inv.getInvoiceNum()){
                for (int j=0;j<f.getHeader().get(i).getInvoiceLines().size();j++){
                    if (f.getHeader().get(i).getInvoiceLines().get(j).equals(lines)){
                        f.getHeader().get(i).getInvoiceLines().remove(j);
                    }
                }
            }
        }
        f.getInvoicetableheader().fireTableDataChanged();
        f.getLineTableModel().fireTableDataChanged();

    }
    private void createInvoiceCancel() {
        headerDialog.setVisible(false);
        headerDialog.dispose();
        headerDialog=null;
    }
    private void createInvoiceOk() throws ParseException {
        String date=headerDialog.getDateField().getText();
        int num=f.getNextNum();
        String name=headerDialog.getCustomerNameField().getText();
        headerDialog.setVisible(false);
        headerDialog.dispose();
        headerDialog=null;

        InvoiceHeader header= new InvoiceHeader();
        header.setInvoiceNum(num);
        header.setInvoiceDate(format.parse(date));
        header.setCustomerName(name);
        f.getHeader().add(header);
        f.getInvoicetableheader().fireTableDataChanged();

    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedRows= f.getjTable1().getSelectedRow();

        if (selectedRows>=0) {
            InvoiceHeader inv = f.getHeader().get(selectedRows);
            f.getjLabel5().setText(String.valueOf(inv.getInvoiceNum()));
            f.getjLabel6().setText(inv.getCustomerName());
            f.getjLabel7().setText(format.format(inv.getInvoiceDate()));
            f.getjLabel8().setText(String.valueOf(inv.getTotal()));

            f.setLineTableModel(new invoiceLineTableModel(inv.getInvoiceLines()));
            f.getjTable2().setModel(f.getLineTableModel());

        }

    }
}
