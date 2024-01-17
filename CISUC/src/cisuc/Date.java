//João Filipe Domingues Fernandes
//2019220273
package cisuc;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Date Info<br>
 *
 * date - String of this form dd/mm/yy<br>
 */
public class Date implements Serializable {

    private String date;

    /**
     * Initializes all the date info
     *
     * @param day
     * @param month
     * @param year
     */
    public Date(String day, String month, String year) {
        confirmDate(day + "/" + month + "/" + year);
        date = day + "/" + month + "/" + year;
    }

    /**
     * verify the date given by the user using the pattern of the date defined
     * by the java.text.SimpleDateFormat object.
     *
     * @param expiryDate
     */
    private void confirmDate(String expiryDate) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Scanner input = new Scanner(System.in);
        format.setLenient(false);

        try {
            format.parse(expiryDate);
        } catch (ParseException e) {
            System.out.println("Data " + expiryDate + " nao e valida de acordo com " + ((SimpleDateFormat) format).toPattern());
            System.exit(0);
        }
    }

    @Override
    public String toString() {
        return date;
    }

}
