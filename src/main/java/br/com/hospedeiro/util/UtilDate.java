package br.com.hospedeiro.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilDate {

    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Calcula o número de dias entre duas datas.
     */
    public static long calcularIntervaloDeDias(Date dtInicial, Date dtFinal) throws ParseException {
        return (dtFinal.getTime() - dtInicial.getTime() + 3600000L) / 86400000L;
    }
}
