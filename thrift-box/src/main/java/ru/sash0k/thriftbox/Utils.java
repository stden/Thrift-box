package ru.sash0k.thriftbox;

import android.util.Log;

import java.util.Calendar;

/**
 * Вспомогательные методы
 */
public class Utils {
    public static final String TAG = "THRIFTBOX";
    public static final boolean DEBUG = false;

    public static void log(String msg) {
        if (DEBUG) Log.w(TAG, msg);
    }

    /**
     * Проверка введённых значений сумм.
     * Возвращает введённое значение в копейках или -1 при некорректном вводе
     */
    public static int parseCurrency(String value) {
        if (value.matches("\\d+(\\.\\d{1,2})?")) {
            final int dot = value.indexOf(".");
            if (dot == -1) return Integer.parseInt(value) * 100; // дробной части нет
            else {
                int rub = Integer.parseInt(value.substring(0, dot)); // целая часть
                // разбор копеек
                value = value.substring(dot + 1);
                if (value.length() == 1) value += "0";
                int cop = Integer.parseInt(value);
                return rub * 100 + cop;
            }
        } else return -1;
    }
    // ============================================================================

    /**
     * Отображение суммы в читаемом виде
     */
    public static String formatValue(long value) {
        return value / 100 + "." + String.format("%02d", value % 100);
    }
    // ============================================================================

    /**
     * Получение временных меток начала суток, недели, месяца
     */
    public static long[] getTimestamps() {
        final int MILLIS = 1000;
        long[] result = new long[3];
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        result[0] = c.getTimeInMillis() / MILLIS;
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        result[1] = c.getTimeInMillis() / MILLIS;
        c.set(Calendar.DAY_OF_MONTH, 1);
        result[2] = c.getTimeInMillis() / MILLIS;
        return result;
    }
    // ============================================================================

}
