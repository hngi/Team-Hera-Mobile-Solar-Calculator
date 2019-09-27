package com.hera.hng.heralios;

/**
 * Created by Oluwajuwon on 26/09/2019.
 */

public class Session {
    private int id;
    private String total_power_usage;
    private String date;
    private String solar_panel_size;
    private String inverter_power;
    private String battery_size;

    public Session(int id, String total_power_usage, String date, String solar_panel_size, String inverter_power, String battery_size){
        this.id = id;
        this.total_power_usage = total_power_usage;
        this.date = date;
        this.solar_panel_size = solar_panel_size;
        this.inverter_power = inverter_power;
        this.battery_size = battery_size;
    }

    public int getId(){
        return id;
    }

    public String getTotalPowerUsage(){
        return total_power_usage;
    }

    public String getDate(){
        return date;
    }

    public String getSolarPanelSize(){
        return solar_panel_size;
    }

    public String getInverterPower(){
        return inverter_power;
    }

    public String getBatterySize(){
        return battery_size;
    }
}
