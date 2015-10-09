package utility;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


/**
 * Classe utility per convertire le date.
 * */
public final class DateUtil {

    public DateUtil() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Converte da string a data per salvare in sql.
     * @param data
     *      Stringa con la data da convertire.
     * @return
     *      Data convertita.
     * */
    public static Date toSqlDate(String data) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate localData = LocalDate.parse(data,
                formatter);

        return Date.valueOf(localData);
    }

    /**
     * Converte da formato sql a formato di stampa.
     * @param data
     *      Data da convertire.
     * */
    public static String toPrintDate(String data) {

            String tempo[] = data.split("-");

            String a = tempo[2]+"/"+tempo[1]+"/"+tempo[0];

            return a;
    }

    /**
     * Confronta due date.
     * @param data1
     *      Prima data.
     * @param data2
     *      Seconda data.
     * @return
     *      Risultato del confronto.
     *      1 se data1 > data2
     *      0 se data1 == data 2
     *      -1 se data1 < data2
     * */
    public static Integer compareDates(String data1, String data2) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate localData1 = LocalDate.parse(data1,
                formatter);

        LocalDate localData2 = LocalDate.parse(data2,
                formatter);

        if (localData1.compareTo(localData2) > 0) {
            return 1;
        } else if (localData1.compareTo(localData2) < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Restituisce quanti giorni ci sono tra due date.
     * @param data1
     *      Prima data.
     * @param data2
     *      Seconda data.
     *      */
    public static Integer getGiorniTraDate(String startDate, String endDate) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate start = LocalDate.parse(startDate,
                formatter);

        LocalDate end = LocalDate.parse(endDate,
                formatter);

        Long days = ChronoUnit.DAYS.between(start, end);

        //questo per far si che sia compreso anche il primo giorno
        days++;
        return days.intValue();
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.getGiorniTraDate("12/12/2012",
                "12/01/2011"));
    }
}
