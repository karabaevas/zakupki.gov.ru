package mappers;

import entities.Lot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class LotMapper223 extends AbstractLotMapper {
    private final Logger slf4jLogger = LoggerFactory.getLogger(LotMapper223.class);


    void fillLotUsingEntry(Lot lot, String[] info) {

        lot.setType(parseType(info[4]));
        lot.setNumber(parseNumber(info[4]));
        lot.setLaw(parseLaw(info[1]));
        lot.setTopic(parseTopic(info[5]));
        lot.setOwner(parseOwner(info[7]));
        lot.setCurrency(parseCurrency(info[8]));
        lot.setStartDate(parseStartTime(info[9]));
        lot.addUpdateDate(parseUpdateTime(info[10]));
        lot.setStep(parseStep(info[11]));
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
        String[] columns = parseColumns(s);
        String law = columns[1];
        return s.contains(LAW) ? law : EMPTY_STRING;

    }

    String parseCurrency(String s) {
        String[] columns = parseColumns(s);
        return columns[1];
    }

    String parseStep(String s) {
        String[] columns = parseColumns(s);
        return columns[1];
    }

    String parseOwner(String s) {
        String[] columns = parseColumns(s);
        return columns.length == 1 ? CUSTOMER_WAS_NOT_SPECIFIED : columns[1];
    }

    String parseTopic(String s) {
        String[] columns = parseColumns(s);
        String result = columns[1].replace("&quot;", "\"").replace("\r", "").replace("\n", "");
        return result;
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
