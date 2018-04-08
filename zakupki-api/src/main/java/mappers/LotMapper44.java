package mappers;

import com.rometools.rome.feed.synd.SyndEntry;
import entities.Lot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LotMapper44 extends AbstractLotMapper{
    private final Logger slf4jLogger = LoggerFactory.getLogger(LotMapper44.class);

    void fillLotUsingEntry(Lot lot, String[] info) {

        lot.setType(parseType(info[6]));
        lot.setNumber(parseNumber(info[6]));
        lot.setLaw(parseLaw(info[1]));
        lot.setTopic(parseTopic(info[7]));
        lot.setOwner(parseOwner(info[9]));
        lot.setCurrency(parseCurrency(info[10]));
        lot.setStartDate(parseStartTime(info[11]));
        lot.addUpdateDate(parseUpdateTime(info[12]));
        lot.setStep(parseStep(info[13]));
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
        String result = columns[1];
        return result;
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
