package com.db.dbfrontrunner.Repository;

public interface VerifyMarketRepository {

    float findBySymbolandHoursandMinutes(String symbol, int hours, int minutes);
}
