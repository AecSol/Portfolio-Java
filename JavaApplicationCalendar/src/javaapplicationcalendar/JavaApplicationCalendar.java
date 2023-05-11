package javaapplicationcalendar; 
import java.util.Scanner;
public class JavaApplicationCalendar {

    public static void main(String[] args) {
            // ввод значений с клавиатуры
       Scanner in = new Scanner(System.in);
       System.out.print("year = 2000 ");
       int year = in.nextInt();// присвоено значение текущего года
       System.out.print("day = 309 ");
       int dayNum = in.nextInt();// присвоено значение в диапазоне от 1 до 365
       
       // целочисленный массив с количеством дней в месяцах
       int []daysinMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
       // строчный массив с названиями месяцев
       String []month = {
            "January","February","March","April","May","June",
            "July","August","September",
            "October","November","December"};
       int monthNum = 0;
       // проверка високосного года
       boolean isLeapYear =(year%4==0)&&(year%100!=0 || year%400==0);
       
       // обход каждого элемента массива daysinMonth в цикле
       // для подсчета дней в месяцах
       for (int day : daysinMonth){
           // увеличение значения переменной day на единицу, при выполнении условия
           if(isLeapYear && day==28) ++day;
           // выход из цикла, при выполнении условия
           if(dayNum<=day) break;

           // уменьшение значения dayNum на величину значения day
           dayNum=dayNum-day;
           monthNum++;// увеличение значения переменной на единицу
       }
       // вывод месяца и дня
       System.out.println(month[monthNum] + ' ' + dayNum);
    }
    
}
    
    

