package com.db.dbhackathonapi.Tables;


import com.db.dbhackathonapi.interfaces.Activity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;


@ToString
@Setter
@Getter
@Entity // This tells Hibernate to make a table out of this class
public class TravelActivity extends Activity {
    @Id
    private int id;

    private String userEmail;
    private String medium;
    private int distance;
    private int contributors;
    private int ghgFootprint;
    private Timestamp timestamp;

    /*petrol - 2.322 kgCO2/ltre


    car - petrol
    distance  - 5000 km/yr
    avg 15 km/lt
            ltres = 5000/15 = 333.33 ltrs

    car disel
    avg 20 km/ltre


    petrol - 30 ltres
    emission - 300 * 2.322 = 696.6 kgCO2
    in tonnes = 696.6 /908 = 0.767 ----> ans

0.767 + 0.2 +0.7+ 0.9+10=red


            treesrequired = tonnes / 5
    mintu = 2.45 tonnes
    no of trees for mintu = 2.45/5 = 0.49 trees per year = green
*/
}