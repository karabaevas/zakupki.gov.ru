package entities;

import com.rometools.rome.feed.synd.SyndEntry;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

//Main goal of class: keep info about Entity

public class Lot {
    String type;
    String number;

    String step;

    String currency;

    String law;

    String owner;

    String topic;

    public String getLaw() {
        return law;
    }

    public void setLaw(String law) {
        this.law = law;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setModificatitonDate(List<LocalDate> modificatitonDate) {
        this.modificatitonDate = modificatitonDate;
    }

    LocalDate startDate;

    List<LocalDate> modificatitonDate = new ArrayList<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public List<LocalDate> getModificatitonDate() {
        return modificatitonDate;
    }

    public void addUpdateDate(LocalDate updateDate) {
        this.modificatitonDate.add(updateDate);
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Lot{" +
                "type='" + type + '\'' +
                ", number='" + number + '\'' +
                ", step='" + step + '\'' +
                ", currency='" + currency + '\'' +
                ", law='" + law + '\'' +
                ", owner='" + owner + '\'' +
                ", topic='" + topic + '\'' +
                ", startDate=" + startDate +
                ", modificatitonDate=" + modificatitonDate +
                '}';
    }
}
