package core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    //Current date in YYYY-MM-DD
    public static String getCurrentDate(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        return formattedDate;
    }
}
