package com.company;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    //int inputNum;
    int numGame = 0;
    int[] inputNumArr = new int[4];
    int[] randNumArr = new int[4];
    int bulls;
    int cows;
    List<String> listInputNumArr = new ArrayList<>();

    public Game() {
        setRandNumArr();
        setInputNumArr();
        setNumGame();
        setBullsCows();
    }

    public void setBullsCows() {
        this.bulls = 0;
        this.cows = 0;
        int[] mas1;
        int[] mas2;
        mas1 = inputNumArr.clone();
        mas2 = randNumArr.clone();
        for (int i = 0; i < mas1.length; i++) {
            if (mas1[i] == mas2[i]) {
                this.bulls++;
                mas1[i] = -1;
                mas2[i] = -11;
            }
        }
        for (int k : mas1) {
            for (int j = 0; j < mas2.length; j++) {
                if (k == mas2[j]) {
                    this.cows++;
                    mas2[j] = -11;
                    break;
                }
            }
        }
    }

    public void setNumGame() {
        File file = new File("Game_Bulls_and_Cows.log");
        try (FileReader reader = new FileReader(file)) {
            BufferedReader bufread = new BufferedReader(reader);

            String line = bufread.readLine();
            while (line != null) {
                if (line.matches("Game.*")) {
                    Pattern p = Pattern.compile("(№)(\\d+)");
                    Matcher m = p.matcher(line);
                    if (m.find()) {
                        this.numGame = Integer.parseInt(m.group(2));
                    }
                }
                line = bufread.readLine();
            }
        } catch (IOException e) {
            this.numGame = 0;
        }

    }

    public void incremNumGame() {
        this.numGame++;
    }

    public void setInputNumArr() {
        Scanner scanner = new Scanner(System.in);
        String inputNum;
        do {
            System.out.println("Введите 4х-значное число:");
            inputNum = scanner.next();
        } while (inputNum.length() != 4);
        //scanner.close();
        listInputNumArr.add(inputNum);
        int num = Integer.parseInt(inputNum);
        for (int i = this.inputNumArr.length-1; i>=0; i--){
            this.inputNumArr[i] = num % 10;
            num /= 10;
        }
    }

    public void setRandNumArr() {
        Random random = new Random();
        for (int i=0; i < this.randNumArr.length; i++){
            this.randNumArr[i] = random.nextInt(10);
        }
    }

    public String getInputNumArr() {
        return listInputNumArr.get(listInputNumArr.size()-1);
    }

    public String getRandNumArr(){
        String randNum = "";
        for (int j : this.randNumArr) {
            randNum += String.valueOf(j);
        }
        return randNum;
    }
}
