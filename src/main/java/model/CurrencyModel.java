package model;

import lombok.Data;

import java.util.Date;

@Data
public class CurrencyModel {
    String code;
    Date date;
    String cur_Abbreviation;
    String provider;
    String cur_name;
    Double buying_price;
    Double selling_price;

}
