package com.company;

import java.io.File;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        File f = File.listRoots()[0];
        imprimir(f);
        int userIndex = scn.nextInt();
        while (userIndex != -1) {

            if (userIndex == 0 && f.getParent() != null) {
                f = f.getParentFile();
                imprimir(f);
            } else {
                userIndex--;
                f = f.listFiles()[userIndex];
                imprimir(f);
            }
            userIndex = scn.nextInt();
        }
    }

    public static void imprimir(File f) {
        System.out.println("Lista de ficheros y derectorios del directorio: " + f);
        System.out.println("-------------------------------------------------------");
        System.out.println("0 - Directorio padre");
        for (int i = 0; i < f.listFiles().length; i++) {
            int userIndex = i + 1;
            String permisos = permisos(f, i);
            DateFormat formatter;
            formatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.getDefault());
            if (f.listFiles()[i].isDirectory())
                System.out.println(userIndex + ".- \tD" + permisos + "\t" +  String.format("%-15d", f.length()) + formatter.format(f.lastModified()) + "\t\t" + f.listFiles()[i].getName());
            else
                System.out.println(userIndex + ".- \t-" + permisos + "\t" +  String.format("%-15d", f.length()) + formatter.format(f.lastModified()) + "\t\t" + f.listFiles()[i].getName());

        }
        System.out.println("-1 para salir");
    }

    public static String permisos(File f, int index) {
        String r, w, x;
        if (f.listFiles()[index].canRead())
            r = "R";
        else r = "-";
        if (f.listFiles()[index].canWrite())
            w = "W";
        else w = "-";
        if (f.listFiles()[index].canExecute())
            x = "X";
        else x = "-";
        return r + w + x;


    }
}
