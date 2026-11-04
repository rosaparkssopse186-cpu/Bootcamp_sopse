package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class FileManager {

    public static final String FILE_NAME = "src/main/resources/Transaction.csv";

    public static void saveTransaction( Transaction transaction) {

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(FILE_NAME, true))){

              writer.write(
                      transaction.getDate() + "|" +
                      transaction.getTime() + "|" +
                      transaction.getDescription() + "|" +
                      transaction.getVendor() + "|" +
                      transaction.getAmount()
              );

              writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving transaction.");
        }
    }

    public static ArrayList<Transaction> loadTransaction() {

        ArrayList<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                Transaction transaction =
                        new Transaction(
                                LocalDate.parse(data[0]),
                                LocalTime.parse(data[1]),
                                data[2],
                                data[3],
                                Double.parseDouble(data[4])
                        );
                transactions.add(transaction);
            }

            } catch (IOException e){
                System.out.println("Transaction Issues");
                e.printStackTrace();
            }
            return transactions;
        }

    }




