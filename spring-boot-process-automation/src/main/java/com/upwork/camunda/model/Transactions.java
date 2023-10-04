package com.upwork.camunda.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    Payment payment;

    @Column(name = "date")
    private Date date;

    @Column(name = "money_laundering")
    private Boolean moneyLaundering;
}
