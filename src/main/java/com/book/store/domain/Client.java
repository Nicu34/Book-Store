package com.book.store.domain;

/**
 * Created by MuresanN on 3/8/2017.
 */
public class Client extends BaseEntity<Long> {
    private String name;
    private Long moneyAmount;

    public Client(Long id, String name, Long moneyAmount) {
        super(id);
        this.name = name;
        this.moneyAmount = moneyAmount;
    }

    public String getName() {
        return name;
    }

    public Client setName(String name) {
        this.name = name;
        return this;
    }

    public Long getMoneyAmount() {
        return moneyAmount;
    }

    public Client setMoneyAmount(Long moneyAmount) {
        this.moneyAmount = moneyAmount;
        return this;
    }

    @Override
    public String toString() {
        return "Client{" + super.toString() +
                "name='" + name + '\'' +
                ", moneyAmount=" + moneyAmount +
                '}';
    }
}
