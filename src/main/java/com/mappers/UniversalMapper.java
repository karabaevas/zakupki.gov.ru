package com.mappers;

import com.model.Lot;
import com.rometools.rome.feed.synd.SyndEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class UniversalMapper {
    public static final String NUMBER = "<strong>№ </strong><strong>";
    private final Logger slf4jLogger = LoggerFactory.getLogger(UniversalMapper.class);
    private static final String LAW_CHECK_MESSAGE = "Закупки по:";
    private static final String TOPIC_CHECK_MESSAGE = "Наименование объекта закупки:";
    private static final String OWNER_CHECK_MESSAGE = "Наименование Заказчика:";
    private static final String OWNER_CHECK_MESSAGE_1 = "Организация, размещающая заказ:";
    private static final String PRICE_CHECK_MESSAGE = "Начальная цена контракта:";
    private static final String PRICE_CHECK_MESSAGE_1 = "Начальная цена:";
    private static final String STEP_CHECK_MESSAGE = "Этап размещения:";
    private static final String START_TIME = "Размещено:";
    private static final String UPDATE_TIME = "Обновлено:";
    private static final String TYPE1 = "Электронный аукцион";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String EMPTY_STRING = "";

    //Will help to determine wrong notes in DB
    private LocalDate farFuture = LocalDate.of(4000, 12, 25);

    private String[] parseColumns(String time) {
        String[] columns = time.replace("<", "").replace(">", "").replace("/", "").split("strong");
        columns = Arrays.stream(columns)
                .filter(value ->
                        !"".equals(value)
                )
                .toArray(String[]::new);
        return columns;
    }

    private void fillLotUsingEntry(Lot lot, String[] info) {
        lot.setType(parseType(info));
        lot.setNumber(parseNumber(info));
        lot.setLaw(parseLaw(info));
        lot.setTopic(parseTopic(info));
        lot.setOwner(parseOwner(info));
        lot.setCurrency(parseCurrency(info));
        lot.setStartDate(parseStartTime(info));
        lot.addUpdateDate(parseUpdateTime(info));
        lot.setStep(parseStep(info));
    }

    private String parseType(String[] data) {
        String result = "";
        for (String s : data) {
            if (s != null && (s.contains(NUMBER))) {
                String[] columns = parseColumns(s);
                result = columns[0];
                return result;
            }
        }
        slf4jLogger.error("Law message error: " + result);
        return EMPTY_STRING;
    }

    private String parseNumber(String[] data) {
        for (String s : data) {
            if (s != null && (s.contains(NUMBER))) {
                String[] columns = parseColumns(s);
                return columns[3];
            }
        }
        slf4jLogger.error("Number error: " + EMPTY_STRING);
        return EMPTY_STRING;
    }

    private String parseLaw(String[] data) {
        for (String s : data) {
            if (s != null && (s.contains(LAW_CHECK_MESSAGE) || s.contains(LAW_CHECK_MESSAGE))) {
                String[] columns = parseColumns(s);
                return columns[1];
            }
        }
        slf4jLogger.error("Law message error: " + EMPTY_STRING);
        return EMPTY_STRING;
    }

    private String parseCurrency(String[] data) {
        for (String s : data) {
            if (s.contains(PRICE_CHECK_MESSAGE) || s.contains(PRICE_CHECK_MESSAGE_1)) {
                String[] columns = parseColumns(s);
                return columns[1];
            }
        }
        slf4jLogger.error("Price message was not found");
        slf4jLogger.error("data: " + Arrays.toString(data));

        return EMPTY_STRING;
    }

    private String parseStep(String[] data) {
        for (String s : data) {
            if (s != null && s.contains(STEP_CHECK_MESSAGE)) {
                String[] columns = parseColumns(s);
                return columns[1];
            }
        }
        slf4jLogger.error("Step message error: " + EMPTY_STRING);
        return EMPTY_STRING;
    }

    private String parseOwner(String[] data) {
        for (String s : data) {
            if (s != null && (s.contains(OWNER_CHECK_MESSAGE) || s.contains(OWNER_CHECK_MESSAGE_1))) {
                String[] columns = parseColumns(s);
                if (columns.length > 1)
                    return columns[1];
            }
        }
        slf4jLogger.error("Owner message was not found");
        slf4jLogger.error("data: " + Arrays.toString(data));

        return EMPTY_STRING;
    }

    private String parseTopic(String[] data) {
        String result = EMPTY_STRING;
        for (String s : data) {
            if (s != null && s.contains(TOPIC_CHECK_MESSAGE)) {
                String[] columns = parseColumns(s);
                result = columns[1].replace("&quot;", "\"").replace("\r", "").replace("\n", "");
                return result;
            }
        }
        slf4jLogger.error("Topic message error: " + result);
        return EMPTY_STRING;
    }

    private LocalDate parseStartTime(String[] data) {
        for (String s : data) {
            if (s != null && (s.contains(START_TIME))) {
                String[] columns = parseColumns(s);
                return LocalDate.parse(columns[1], formatter);
            }
        }
        slf4jLogger.error("Start date was not found");
        slf4jLogger.error("data: " + Arrays.toString(data));

        return farFuture;
    }

    private LocalDate parseUpdateTime(String[] data) {
        for (String s : data) {
            if (s != null && (s.contains(START_TIME))) {
                String[] columns = parseColumns(s);
                return LocalDate.parse(columns[1], formatter);
            }
        }
        slf4jLogger.error("Update date was not found");
        slf4jLogger.error("data: " + Arrays.toString(data));

        return farFuture;
    }

    public Lot map(SyndEntry entry) {
        Lot lot = new Lot();

        String[] dataArrayFromSyndEntry = entry.getDescription().getValue().split("<br/>");

        slf4jLogger.info(Arrays.toString(dataArrayFromSyndEntry));

        fillLotUsingEntry(lot, dataArrayFromSyndEntry);
        return lot;
    }
}
