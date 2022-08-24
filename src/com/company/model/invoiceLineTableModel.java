package com.company.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class invoiceLineTableModel extends AbstractTableModel {
    private String []columns={"item","price","count","total"};
    private ArrayList<InvoiceLines> invoiceLines;

    public invoiceLineTableModel(ArrayList<InvoiceLines> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    @Override
    public int getRowCount() {
        return invoiceLines.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLines lines=invoiceLines.get(rowIndex);
        switch(columnIndex){
            case 0 : return lines.getItemName();
            case 1 : return lines.getItemPrice();
            case 2 : return lines.getCount();
            case 3 : return lines.totalSumOfItem();

        }
        return "";
    }
    @Override
    public String getColumnName(int column){
        return columns[column];
    }
}
