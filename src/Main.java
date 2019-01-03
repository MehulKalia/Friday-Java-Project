/*
ID: thealdo1
LANG: JAVA
TASK: friday
*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        //Setting up input and output
        BufferedReader inputFile = new BufferedReader(new FileReader("src\\friday.in.txt"));
        PrintWriter outputFile = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));

        //n represents how many years will we simulate
        String tempString=inputFile.readLine();
        int n=Integer.parseInt(tempString);

        int whichDayJan=0;
        int [] daysOfWeek=new int []{0,0,0,0,0,0,0};
        int year=1900;
        int [] tempList;
        for (int i=0; i<n; i++) {
            //Checking if year is leap year
            if (year%400==0||(year%100!=0&&year%4==0)) {
                tempList=howManyPerYear(true, whichDayJan);
                whichDayJan=(whichDayJan+1)%7;
            } else {
                tempList=howManyPerYear(false, whichDayJan);
            }
            year+=1;//Adds year
            whichDayJan=(whichDayJan+1)%7;
            for (int a=0; a<7; a++) {
                daysOfWeek[a]+=tempList[a];//Adding the 13ths calculated to the actual list
            }
        }


        outputFile.println(daysOfWeek[0]+" "+daysOfWeek[1]+" "+daysOfWeek[2]+" "+daysOfWeek[3]+" "+daysOfWeek[4]+" "+daysOfWeek[5]+" "+daysOfWeek[6]);
        outputFile.close();

    }

    //Method calculates how many friday 13ths in one year per day of week
    public static int[] howManyPerYear(boolean IsLeapYear, int whichDayJanP) {
        //daysOfWeek[] will represent how many times 13th of the month lands on each day of week (goes from saturday to friday)
        int[] daysOfWeekP = new int[]{0, 0, 0, 0, 0, 0, 0};

        //daysPerMonth[] and daysPerMonthLeapYear[] represent how many days are in each corresponding month
        int[] daysPerMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] daysPerMonthLeapYear = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};


        daysOfWeekP[whichDayJanP] += 1;
        int tempInt = whichDayJanP;
        int distance;
        for (int i = 1; i < 12; i++) {
            if (IsLeapYear==true) {
                distance = daysPerMonthLeapYear[i - 1];
            }
            else {
                distance = daysPerMonth[i - 1];
            }
            tempInt += distance;
            daysOfWeekP[tempInt % 7] += 1;
        }

        return (daysOfWeekP);


    }

}
