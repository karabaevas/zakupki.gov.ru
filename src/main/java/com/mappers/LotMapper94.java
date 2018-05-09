package com.mappers;

import com.model.Lot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class LotMapper94 extends AbstractLotMapper {
    public static final String LAW_CHECK_MESSAGE = "Закупки по:";
    public static final String TOPIC_CHECK_MESSAGE = "Наименование объекта закупки:";
    public static final String OWNER_CHECK_MESSAGE = "Наименование Заказчика:";
    public static final String PRICE_CHECK_MESSAGE = "Начальная цена контракта:";
    public static final String STEP_CHECK_MESSAGE = "Этап размещения:";
    private final Logger slf4jLogger = LoggerFactory.getLogger(LotMapper94.class);

    void fillLotUsingEntry(Lot lot, String[] info) {

        lot.setType(parseType(info[5]));
        lot.setNumber(parseNumber(info[5]));
        lot.setLaw(parseLaw(info[1]));
        lot.setTopic(parseTopic(info[6]));
        lot.setOwner(parseOwner(info[8]));
        lot.setCurrency(parseCurrency(info[9]));
        lot.setStartDate(parseStartTime(info[10]));
        lot.addUpdateDate(parseUpdateTime(info[11]));
        lot.setStep(parseStep(info[12]));
    }

    String parseType(String s) {
        String[] columns = parseColumns(s);
        String type = columns[0];
        return type;
    }

    String parseNumber(String s) {
        String[] columns = parseColumns(s);
        String number = columns[3];
        return number;
    }

    String parseLaw(String s) {
        if(s != null && (s.contains(LAW_CHECK_MESSAGE) || s.contains(LAW_CHECK_MESSAGE))){
            String[] columns = parseColumns(s);
            String law = columns[1];
            return law;
        }
        slf4jLogger.error("Law message error: " + s);
        return EMPTY_STRING;
    }

    String parseCurrency(String s) {
        if(s != null && s.contains(PRICE_CHECK_MESSAGE)){
            String[] columns = parseColumns(s);
            return columns[1];
        }

        slf4jLogger.error("Price message error: " + s);
        return EMPTY_STRING;
    }

    String parseStep(String s) {
        if(s != null && s.contains(STEP_CHECK_MESSAGE)){
            String[] columns = parseColumns(s);
            return columns[1];
        }
        slf4jLogger.error("Step message error: " + s);
        return EMPTY_STRING;
    }

    String parseOwner(String s) {
        if(s != null && s.contains(OWNER_CHECK_MESSAGE)){
            String[] columns = parseColumns(s);
            return columns.length < 2 ? "" : columns[1] ;
        }
        slf4jLogger.error("Owner message error: " + s);
        return EMPTY_STRING;

    }

    String parseTopic(String s) {
        if(s != null && s.contains(TOPIC_CHECK_MESSAGE)){
            String[] columns = parseColumns(s);
            String result = columns[1].replace("&quot;", "\"").replace("\r", "").replace("\n", "");
            return result;
        }
        slf4jLogger.error("Topic message error: " + s);
        return EMPTY_STRING;
    }

    LocalDate parseStartTime(String startTime) {
        if (startTime.contains(START_TIME)) {
            String[] columns = parseColumns(startTime);

            if (columns[0].contains(START_TIME)) {
                return LocalDate.parse(columns[1], formatter);
            } else {
                System.out.println("ParseCaution!");
                System.out.println("StartTime string = " + startTime);
            }

        } else {
            System.out.println("Something going wrong!");
            System.out.println("Starttime field contains: " + startTime);
        }
        return farFuture;
    }

    LocalDate parseUpdateTime(String updateTime) {
        if (updateTime.contains(UPDATE_TIME)) {
            String[] columns = parseColumns(updateTime);

            if (columns[0].contains(UPDATE_TIME)) {
                return LocalDate.parse(columns[1], formatter);
            } else {
                System.out.println("ParseCaution!");
                System.out.println("UpdateTime string = " + updateTime);
            }

        } else {
            System.out.println("Something going wrong!");
            System.out.println("Updatetime field contains: " + updateTime);
        }
        return farFuture;
    }
}
