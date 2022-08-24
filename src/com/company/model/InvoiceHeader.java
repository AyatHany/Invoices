package com.company.model;
//import com.company.model.InvoiceLines;
import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeader {


    private int invoiceNum;
    private Date invoiceDate;
    private String customerName;

    public ArrayList<InvoiceLines> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(ArrayList<InvoiceLines> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }


    public ArrayList<InvoiceLines> invoiceLines = new ArrayList<InvoiceLines>();

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }
    //calculate total
    public double getTotal(){
        double sum =0;
        for(int i=0;i<invoiceLines.size();i++){
            sum =sum + getInvoiceLines().get(i).totalSumOfItem();
        }
        return sum;
    }


}
