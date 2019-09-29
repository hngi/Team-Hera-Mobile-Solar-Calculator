package com.hera.hng.heralios;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Oluwajuwon on 27/09/2019.
 */

public class Calculations {

    DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public double calculateSolarPowerUsage(double time, double elecPowerUsage){
        double totalPowerUsage = time * elecPowerUsage;

        return Double.valueOf(decimalFormat.format(totalPowerUsage));
    }

    /*
    public double solarPanelSize(){

        return 0.0;
    }*/


    public double inverterPower(List<Entry> entries){
        int inverterPower =0;
        for(Entry e: entries){
            inverterPower += Double.parseDouble(e.getPowerUsage());
        }

        return Double.valueOf(decimalFormat.format(inverterPower));
    }

    public double batterySize(double totalPowerUsage){
        double kwPowerBatteryUsage = (totalPowerUsage*0.001)*2;
        //for 24v
        double minAh = (kwPowerBatteryUsage*1000)/24;

        return Double.valueOf(decimalFormat.format(minAh));
    }
}
