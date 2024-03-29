/*
 * This file is generated by jOOQ.
 */
package revolut.task.db.tables.pojos;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Transfers implements Serializable {

    private static final long serialVersionUID = 1342149605;

    private Long           id;
    private Long           fromAccount;
    private Long           toAccount;
    private BigDecimal     amount;
    private String         currency;
    private OffsetDateTime date;

    public Transfers() {}

    public Transfers(Transfers value) {
        this.id = value.id;
        this.fromAccount = value.fromAccount;
        this.toAccount = value.toAccount;
        this.amount = value.amount;
        this.currency = value.currency;
        this.date = value.date;
    }

    public Transfers(
        Long           id,
        Long           fromAccount,
        Long           toAccount,
        BigDecimal     amount,
        String         currency,
        OffsetDateTime date
    ) {
        this.id = id;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromAccount() {
        return this.fromAccount;
    }

    public void setFromAccount(Long fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Long getToAccount() {
        return this.toAccount;
    }

    public void setToAccount(Long toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public OffsetDateTime getDate() {
        return this.date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Transfers (");

        sb.append(id);
        sb.append(", ").append(fromAccount);
        sb.append(", ").append(toAccount);
        sb.append(", ").append(amount);
        sb.append(", ").append(currency);
        sb.append(", ").append(date);

        sb.append(")");
        return sb.toString();
    }
}
