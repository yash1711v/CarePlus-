package com.yashverma.oldeage;

public class medicinehelper {
    String medicine_id,medicine_name,medicine_type,medicine_stock_count,med_company;

    public medicinehelper() {
    }

    public medicinehelper(String medicine_id, String medicine_name, String medicine_type, String medicine_stock_count, String med_company) {
        this.medicine_id = medicine_id;
        this.medicine_name = medicine_name;
        this.medicine_type = medicine_type;
        this.medicine_stock_count = medicine_stock_count;
        this.med_company = med_company;
    }

    public String getMedicine_id() {
        return medicine_id;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public String getMedicine_type() {
        return medicine_type;
    }

    public String getMedicine_stock_count() {
        return medicine_stock_count;
    }

    public String getMed_company() {
        return med_company;
    }
}
