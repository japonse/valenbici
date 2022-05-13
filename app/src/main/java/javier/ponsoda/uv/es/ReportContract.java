package javier.ponsoda.uv.es;

import android.provider.BaseColumns;

public final class ReportContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private ReportContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLA_REPORTS = "Reports";
        public static final String COLUMN_NAME = "nombre";
        public static final String COLUMN_DESCRIPTION = "descripcion";
        public static final String COLUMN_STATION = "estacion";
        public static final String COLUMN_STATUS = "estado";
        public static final String COLUMN_TYPE = "tipo";
    }


}
