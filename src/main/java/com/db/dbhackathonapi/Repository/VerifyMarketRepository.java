package com.db.dbhackathonapi.Repository;

public interface VerifyMarketRepository {

    float findBySymbolandHoursandMinutes(String symbol, int hours, int minutes);
}
