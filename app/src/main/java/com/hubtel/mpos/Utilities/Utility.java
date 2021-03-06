package com.hubtel.mpos.Utilities;

import com.hubtel.mpos.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import io.mpos.transactionprovider.TransactionProcessDetailsState;
import io.mpos.transactionprovider.TransactionProcessDetailsStateDetails;

/**
 * Created by apple on 22/06/16.
 */
public class Utility {

    public static String formatMoney(double money){

        NumberFormat df = NumberFormat.getCurrencyInstance();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setCurrencySymbol("Ghs ");
        dfs.setGroupingSeparator(',');
        dfs.setMonetaryDecimalSeparator('.');
        ((DecimalFormat) df).setDecimalFormatSymbols(dfs);

        return df.format(money);
    }

    public static String  prepareString4double(String string){

        String finalPreparedString=string.replace(",","");

        return finalPreparedString;
    }



}
