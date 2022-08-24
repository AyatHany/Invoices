package com.company.model;

import java.awt.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import javax.swing.*;


public class FileOperations extends Component {
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
    Frame f;
    public FileOperations(Frame f) {
        this.f = f;
    }
    public ArrayList<InvoiceHeader> readFile() throws IOException, ParseException {
        ArrayList<InvoiceHeader> invoiceHeader=new ArrayList<InvoiceHeader>();
        String s=null;

        FileInputStream fs = null;
        JFileChooser fc=new JFileChooser();
        fc.setMultiSelectionEnabled(true);
        int result= fc.showOpenDialog(this.f);
        if(result== JFileChooser.APPROVE_OPTION){
            String path =fc.getSelectedFile().getPath();
            fs = new FileInputStream(path);
            int size =fs.available();
            byte[]b = new byte[size];
            fs.read(b);
            s=new String(b);
            fs.close();
        }
        String section="";
        ArrayList<String> array=new ArrayList<String>();
            for(int i=0;i<s.length();i++)
            {
                if(s.charAt(i)!=',' && i<s.length()-1 && s.charAt(i)!='\n' )
                    section+=s.charAt(i);
                else if(i==s.length()-1)
                {
                    section+=s.charAt(i);
                    array.add(section);
                    section="";
                }
                else
                {
                    array.add(section);
                    section="";
                }
            }

            for(int i=0;i<array.size();i+=3){
                InvoiceHeader header= new InvoiceHeader();
                header.setInvoiceNum(Integer.parseInt(array.get(i)));
                //String str= format.format(array.get(i+1));
                header.setInvoiceDate(new SimpleDateFormat("dd-MM-yyyy").parse(array.get(i+1)));
                header.setCustomerName(array.get(i+2));
                invoiceHeader.add(header);


            }

        ArrayList<InvoiceLines> invoiceLines=new ArrayList<InvoiceLines>();
        String s2=null;
        FileInputStream fs2 = null;
        int result2= fc.showOpenDialog(this.f);
        if(result2== JFileChooser.APPROVE_OPTION){
            String path =fc.getSelectedFile().getPath();
            fs2 = new FileInputStream(path);
            int size =fs2.available();
            byte[]b2 = new byte[size];
            fs2.read(b2);
            s2=new String(b2);
            fs2.close();
        }
        String section2="";
        ArrayList<String> array2=new ArrayList<String>();
        for(int i=0;i<s2.length();i++)
        {
            if(s2.charAt(i)!=',' && i<s2.length()-1 && s2.charAt(i)!='\n' )
                section2+=s2.charAt(i);
            else if(i==s2.length()-1)
            {
                section2+=s2.charAt(i);
                array2.add(section2);
                section2="";
            }
            else
            {
                array2.add(section2);
                section2="";
            }
        }
        for(int i=0;i<invoiceHeader.size();i++) {
            for (int j = 0; j < array2.size(); j += 4) {
                if(Integer.parseInt(array2.get(j))== invoiceHeader.get(i).getInvoiceNum()) {
                    InvoiceLines lines = new InvoiceLines();
                    lines.setItemName(array2.get(j+1));
                    double d=Double.parseDouble(array2.get(j + 2));
                    lines.setItemPrice((int) (d));
                    double doc= Double.parseDouble(array2.get(j + 3));
                    lines.setCount((int) (doc));
                    invoiceLines.add(lines);
                }

            }
            invoiceHeader.get(i).setInvoiceLines(invoiceLines);
            invoiceLines = new ArrayList<>();
        }
        return invoiceHeader;
    }
    public void writeFile(ArrayList<InvoiceHeader> invoiceHeaders) throws IOException {
        JFileChooser fc = new JFileChooser();
        FileOutputStream fo = null;
        fc.setMultiSelectionEnabled(true);
        int result = fc.showSaveDialog(this);
        if(result== JFileChooser.APPROVE_OPTION) {
            String path =fc.getSelectedFile().getPath();
            fo = new FileOutputStream(path);

        }
        String string="";
        for (int i=0;i<invoiceHeaders.size();i++) {
            string=invoiceHeaders.get(i).getInvoiceNum()+",";
            fo.write(string.getBytes());
            string="";
            string=format.format(invoiceHeaders.get(i).getInvoiceDate())+",";
            fo.write(string.getBytes());
            string="";
            string=invoiceHeaders.get(i).getCustomerName();
            fo.write(string.getBytes());
            string="\n";
            fo.write(string.getBytes());

            }

        FileOutputStream fo2 = null;
        int result2 = fc.showSaveDialog(this);
        if(result2== JFileChooser.APPROVE_OPTION) {
            String path2 =fc.getSelectedFile().getPath();
            fo2 = new FileOutputStream(path2);

        }

        for (int i=0;i<invoiceHeaders.size();i++){
            for(int j=0;j<invoiceHeaders.get(i).getInvoiceLines().size();j++){
                String s="";
                s=invoiceHeaders.get(i).getInvoiceLines().get(j).getItemName()+",";
                fo2.write(s.getBytes());
                s="";
                s=invoiceHeaders.get(i).getInvoiceLines().get(j).getItemPrice()+",";
                fo2.write(s.getBytes());
                s="";
                s= String.valueOf(invoiceHeaders.get(i).getInvoiceLines().get(j).getCount());
                fo2.write(s.getBytes());

                s="";
                s="\n";
                fo2.write(s.getBytes());
            }
        }

        fo.close();

    }
}

class Main {
    public static void main(String[] args){

    }
}