package com.company;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        String namefile = "Game_Bulls_and_Cows.log";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Игра Быки и коровы");
        int countAtt = 0;
        int bulls, cows;

        while (true) {
            System.out.println("Загадано 4х-значное число, введите свой вариант");
            Game g = new Game();
            g.incremNumGame();

            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy hh:mm");
            String text = String.format("Game №%s",
                    g.numGame) +" "+ formatForDateNow.format(dateNow) + " Загаданная строка " + g.getRandNumArr();
            appendUsingFileWriter(namefile, text);
            System.out.println(g.getRandNumArr());
            while (true) {
                bulls = g.bulls;
                cows = g.cows;
                countAtt++;
                if (bulls == 4) {
                    text = "Запрос: " + g.getInputNumArr() + " Ответ: " + getWordEndBulls(bulls) + " " + getWordEndCows(cows);
                    appendUsingFileWriter(namefile, text);
                    text = "Строка была отгадана за " + getWordEndAtt(countAtt);
                    appendUsingFileWriter(namefile, text);
                    System.out.println("Вы угадали!");
                    break;
                }
                System.out.println("Б " + bulls + " К " + cows);
                text = "Запрос: " + g.getInputNumArr() + " Ответ: " + getWordEndBulls(bulls) + " " + getWordEndCows(cows);
                appendUsingFileWriter(namefile, text);
                g.setInputNumArr();
                g.setBullsCows();
            }
            System.out.println("Чтобы выйти из игры намите 1 ");
            if (1 != scanner.nextInt()) {
                continue;
            }
            break;
        }
    }

    private static void appendUsingFileWriter(String filePath, String text) {
        File file = new File(filePath);
        FileWriter fr;
        BufferedWriter br = null;
        try {
            fr = new FileWriter(file, true);
            br = new BufferedWriter(fr);
            br.write(text);
            br.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getWordEndAtt(int countAtt) {
        if (countAtt % 100 / 10 == 1) {
            return countAtt+" попыток";
        }
        switch (countAtt % 10) {
            case 1:
                return countAtt+" попытку";
            case 2:
            case 3:
            case 4:
                return countAtt+" попытки";
            default:
                return countAtt+" попыток";

        }
    }

    public static String getWordEndBulls(int countBulls) {
        switch (countBulls % 10) {
            case 1:
                return countBulls + " бык";
            case 2:
            case 3:
            case 4:
                return countBulls + " быка";
            default:
                return countBulls + " быков";
        }
    }

    public static String getWordEndCows(int countCows) {
        switch (countCows % 10) {
            case 1:
                return countCows + " корова";
            case 2:
            case 3:
            case 4:
                return countCows + " коровы";
            default:
                return countCows + " коров";
        }
    }
}