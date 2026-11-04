package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static List<Product> readProduct(){
        List<Product> productList = new ArrayList<>();
        try{
            FileReader fileReader = new FileReader("src/main/resources/Inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String itemRow;
            bufferedReader.readLine();
            while((itemRow = bufferedReader.readLine()) != null){
                String [] rowSplitter = itemRow.split("\\|");


                String sKU = rowSplitter[0];
                String productName = rowSplitter[1];
                double price = Double.parseDouble(rowSplitter[2]);
                String department = rowSplitter[3];

                Product product = new Product(sKU, productName,price,department);
                productList.add(product);
            }

        }
        catch(IOException ex){
            System.out.println("Could not read the file. Check the file. try again.");
            //ex.printStackTrace();
        }
        catch(Exception ex){
            System.out.println("Could not read data from the file. try again.");
            //ex.printStackTrace();
        }

        return productList;
    }

    public static void writeInventory(Product product){

        try{
            FileWriter fileWriter = new FileWriter("src/main/resources/Inventory.csv",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String line = String.format("%d|%s|%s|%.2f|%s",product.getSku(),product.getName(),product.getPrice(), product.getDepartment());

            bufferedWriter.newLine();
            bufferedWriter.write(line);

            System.out.println("Item added to Inventory.");
            bufferedWriter.close();
        }
        catch(IOException ex){
            System.out.println("Could not read the file. ");
        }
        catch(Exception ex){
            System.out.println("Error when handling the data.");
        }

    }
}