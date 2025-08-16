package util;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

final public class DateAndTime {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateAndTime(){}

    public static String getDate(){
        LocalDate date = LocalDate.now();
        return date.format(formatter);
    }

    public static boolean compareDate(String current_date , String return_date){
        boolean checkDate = true;
        try{
            LocalDate current = LocalDate.parse(current_date, formatter);
            LocalDate returned = LocalDate.parse(return_date, formatter);

            if(current.isBefore(returned)){
                return checkDate;
            }
            else if(current.isEqual(returned)){
                checkDate = false;
            }
            else{
                checkDate = false;
            }
        }
        catch (DateTimeParseException e){
            System.out.println("Invalid date format" + e.getMessage());
        }
        return checkDate;
    }

    public static boolean validateDate(String return_dates){
        boolean checkDate = false;
        try{
            LocalDate current = LocalDate.parse(getDate(), formatter);
            LocalDate returned = LocalDate.parse(return_dates, formatter);
            if(current.isAfter(returned) ){
                checkDate = true;
            }
        }
        catch (DateTimeParseException e){
            System.out.println("Invalid date format" + e.getMessage());
        }
        return checkDate;
    }

}
