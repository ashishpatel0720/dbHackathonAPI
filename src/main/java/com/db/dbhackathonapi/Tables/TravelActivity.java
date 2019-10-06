package com.db.dbhackathonapi.Tables;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;


@ToString
@Setter
@Getter
@Entity // This tells Hibernate to make a table out of this class
public class TravelActivity {
    @Id
    private int id;

    private String userEmail;
    private String medium;
    private String contributors;
    private String ghgFootprint;

}