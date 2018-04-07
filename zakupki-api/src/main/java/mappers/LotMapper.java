package mappers;

import com.rometools.rome.feed.synd.SyndEntry;
import entities.Lot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class LotMapper {
    private static final String START_TIME = "Размещено:";
    private static final String UPDATE_TIME = "Обновлено:";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final String EMPTY_STRING = "";

    //Will help to determine wrong notes in DB
    LocalDate farFuture = LocalDate.of(4000, 12, 25);

    public Lot mapSyndEntryToLot(SyndEntry entry) {
        Lot lot = new Lot();

        String title = entry.getTitle();
        int delimeterIndex = title.indexOf("№");
        lot.setType(title.substring(0, delimeterIndex - 1));
        lot.setNumber(title.substring(delimeterIndex, title.length() - 1));

        String[] description = entry.getDescription().getValue().split("<br/>");
        fillLotUsingEntry(lot, description);

        return lot;
    }


    void fillLotUsingEntry(Lot lot, String[] info) {

        lot.setLaw(parseLaw(info[1]));
        lot.setOwner(parseOwner(info[7]));
        lot.setCurrency(parseCurrency(info[10]));
        lot.setStartDate(parseStartTime(info[11]));
        lot.addUpdateDate(parseUpdateTime(info[12]));
        lot.setStep(parseStep(info[13]));
    }

    private String parseLaw(String s) {
        String[] columns = parseColumns(s);
        String law = columns[1];
        return law.contains("Размещение выполняется по:") ? law : EMPTY_STRING;

    }

    private String parseCurrency(String s) {
        String[] columns = parseColumns(s);
        return columns[1];
    }

    private String parseStep(String s) {
        String[] columns = parseColumns(s);
        return columns[1];
    }

    private String parseOwner(String s) {
        String[] columns = parseColumns(s);
        return columns[1].replace("&quot;", "\"");
    }

    private String[] parseColumns(String time) {
        String[] columns = time.replace("<", "").replace(">", "").replace("/", "").split("strong");
        columns = Arrays.stream(columns)
                .filter(value ->
                        !"".equals(value)
                )
                .toArray(String[]::new);
        return columns;
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
