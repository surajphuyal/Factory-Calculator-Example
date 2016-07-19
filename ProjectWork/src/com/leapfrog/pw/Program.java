/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.pw;

import com.leapfrog.pw.command.MathCommand;
import com.leapfrog.pw.command.MathFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the file name:");
        String fileName = input.next();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = "";
            StringBuilder content= new StringBuilder();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length >= 3) {
                    double x = Double.parseDouble(tokens[0]);
                    String key = tokens[1];
                    double y = Double.parseDouble(tokens[2]);
                    MathCommand cmd = MathFactory.get(key);
                    if (cmd != null) {
                        double solution=cmd.calculate(x, y);
                       System.out.println(solution);
                        content.append(x+key+y+"="+solution+"\r\n");
                    } else {
                        System.out.println("Invalid command");
                    }
                }

            }
            reader.close();
            System.out.println("Enter the file name to write:");
            fileName=input.next();
            FileWriter writer= new FileWriter(fileName);
            writer.write(content.toString());
            writer.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }

}
