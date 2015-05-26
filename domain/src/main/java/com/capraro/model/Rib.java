package com.capraro.model;

/**
 * Created by rcapraro on 23/12/14.
 */
public class Rib {

    private Long id;
    private String iban;

    public Rib() {
    }

    public Rib(Long id, String iban) {
        this.id = id;
        this.iban = iban;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return "Rib{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                '}';
    }

}
