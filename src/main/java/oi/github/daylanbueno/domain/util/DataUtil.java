package oi.github.daylanbueno.domain.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataUtil {

    public static String converterDataEmStringBr(LocalDate data) {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
