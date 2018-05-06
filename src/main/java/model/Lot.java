package model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Lot {
    @Id
    String number;

    String type;

    String step;

    String currency;

    String law;

    String owner;

    String topic;

    LocalDate startDate;

    List<LocalDate> modificatitonDate = new ArrayList<>();

    public void addUpdateDate(LocalDate updateDate) {
        this.modificatitonDate.add(updateDate);
    }
}
