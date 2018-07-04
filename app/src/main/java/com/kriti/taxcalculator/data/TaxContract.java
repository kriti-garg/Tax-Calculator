package com.kriti.taxcalculator.data;

/**
 * Created by kriti on 3/7/18.
 */

import android.provider.BaseColumns;

/**
 * Created by kriti on 29/6/18.
 */

final class TaxContract {
    private TaxContract(){ }
    public final static class TaxEntry implements BaseColumns{
        public final static String TABLE_NAME ="T_detail";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_ITEMS ="items";
        public final static String COLUMN_TAX = "TAX%";
    }
}
