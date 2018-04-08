package mappers;

import com.rometools.rome.feed.synd.SyndEntry;
import entities.Lot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public abstract class AbstractLotMapper {
    static final String START_TIME = "Размещено:";
    static final String UPDATE_TIME = "Обновлено:";
    static final String LAW = "Размещение выполняется по:";
    static final String CUSTOMER_WAS_NOT_SPECIFIED = "Заказчик не определен";

    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final String EMPTY_STRING = "";

    //Will help to determine wrong notes in DB
    LocalDate farFuture = LocalDate.of(4000, 12, 25);

    public Lot mapSyndEntryToLot(SyndEntry entry) {
        Lot lot = new Lot();

        String[] description = entry.getDescription().getValue().split("<br/>");
        fillLotUsingEntry(lot, description);
        System.out.println(lot.toString());
        return lot;
    }

    String[] parseColumns(String time) {
        String[] columns = time.replace("<", "").replace(">", "").replace("/", "").split("strong");
        columns = Arrays.stream(columns)
                .filter(value ->
                        !"".equals(value)
                )
                .toArray(String[]::new);
        return columns;
    }

    abstract void fillLotUsingEntry(Lot lot, String[] info);

    abstract String parseType(String s);

    abstract String parseNumber(String s);

    abstract String parseLaw(String s);

    abstract String parseCurrency(String s);

    abstract String parseStep(String s);

    abstract String parseOwner(String s);

    abstract String parseTopic(String s);

    abstract LocalDate parseStartTime(String startTime);

    abstract LocalDate parseUpdateTime(String updateTime);
}
