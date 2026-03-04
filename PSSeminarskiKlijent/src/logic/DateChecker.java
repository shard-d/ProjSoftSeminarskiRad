
package logic;

public class DateChecker {
    private static final int[] daysInMonth = new int[]{
        31, // januar
        28, // februar (29 u prestupnoj)
        31, // mart
        30, // april
        31, // maj
        30, // jun
        31, // jul
        31, // avgust
        30, // septembar
        31, // oktobar
        30, // novembar
        31  // decembar
    };
    
    private static int getDaysInMonth(int year, int month) {
        if (month == 2) { // februar
            return isLeapYear(year) ? 29 : 28;
        }
        return daysInMonth[month - 1];
    }
    
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
    }
    
    public static boolean checkDate(int day, int month, int year){
        int dim = getDaysInMonth(year, month);
        if(day > dim)
            return false;
        return true;
    }
}
