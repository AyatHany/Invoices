package com.company.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ayat
 */
public class InvoiceHeaderTableModel extends AbstractTableModel {
    private String []columns={"Num.","Inv Date","customer Name","Total"};
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
    private ArrayList <InvoiceHeader> header;
    public InvoiceHeaderTableModel(ArrayList<InvoiceHeader> header){
        this.header=header;
    }

    @Override
    public int getRowCount() {
        return header.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader inv=header.get(rowIndex);
        switch(columnIndex){
            case 0 : return inv.getInvoiceNum();
            case 1 : return format.format(inv.getInvoiceDate());
            case 2 : return inv.getCustomerName();
            case 3 : return inv.getTotal();


        }
        return "";
    }
    @Override
    public String getColumnName(int column){
        return columns[column];
    }

}
