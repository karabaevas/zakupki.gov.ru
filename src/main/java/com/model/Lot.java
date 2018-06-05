package com.model;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Lot {
    @Id
    String number;

    int version;

    String type;

    String step;

    String currency;

    String law;

    String owner;

    String topic;

    LocalDate startDate;

    @javax.persistence.Transient
    List<LocalDate> modificatitonDate = new ArrayList<>();

    public void addUpdateDate(LocalDate updateDate) {
        this.modificatitonDate.add(updateDate);
    }
}
