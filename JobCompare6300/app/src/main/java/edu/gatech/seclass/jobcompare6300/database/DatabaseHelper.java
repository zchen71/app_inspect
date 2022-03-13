package edu.gatech.seclass.jobcompare6300.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.gatech.seclass.jobcompare6300.database.ComparisonSetting.SettingsEntry;
import edu.gatech.seclass.jobcompare6300.database.Job.JobEntry;

public class  DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "cs6300team131.db";
    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + JobEntry.TABLE_NAME + " (" +
                    JobEntry._ID + " INTEGER PRIMARY KEY," +
                    JobEntry.JOB_COMPANY + " TEXT," +
                    JobEntry.JOB_TITLE + " TEXT," +
                    JobEntry.JOB_LOCATION + " TEXT," +
                    JobEntry.COST_OF_LIVING + " INTEGER," +
                    JobEntry.YEARLY_SALARY + " REAL," +
                    JobEntry.YEARLY_BONUS + " REAL," +
                    JobEntry.RETIREMENT_BENEFITS + " INTEGER," +
                    JobEntry.RELOCATION_STIPEND + " REAL," +
                    JobEntry.RESTRICTED_STOCK + " REAL," +
                    JobEntry.IS_CURRENT_JOB + " INTEGER," +
                    JobEntry.JOB_SCORE + " REAL)";

    private static final String SQL_CREATE_ENTRIES_Comparison_Settings =
            "CREATE TABLE " + SettingsEntry.TABLE_NAME + " (" +
                    SettingsEntry._ID + " INTEGER PRIMARY KEY," +
                    SettingsEntry.Year_Salary + " INTEGER," +
                    SettingsEntry.Year_Bonus + " INTEGER," +
                    SettingsEntry.Relocation_Stipend + " INTEGER," +
                    SettingsEntry.Retire_Benefit + " INTEGER," +
                    SettingsEntry.Stock_Award + " INTEGER)";



    private static final String SQL_DELETE_JOB_ENTRIES =
            "DROP TABLE IF EXISTS " + JobEntry.TABLE_NAME;

    private static final String SQL_DELETE_COMPARISON_SETTING_ENTRIES =
            "DROP TABLE IF EXISTS " + SettingsEntry.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_Comparison_Settings);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_JOB_ENTRIES);
        sqLiteDatabase.execSQL(SQL_DELETE_COMPARISON_SETTING_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    //IDK what is this for
    public  String[] getTableColumns() {
        String[] tableColumns = new String[]{ JobEntry.JOB_TITLE,
                JobEntry.JOB_COMPANY,JobEntry.JOB_LOCATION,JobEntry.COST_OF_LIVING,
                JobEntry.JOB_SCORE,JobEntry.RELOCATION_STIPEND, JobEntry.RESTRICTED_STOCK,
                JobEntry.RETIREMENT_BENEFITS, JobEntry.YEARLY_BONUS, JobEntry.YEARLY_SALARY};

        return tableColumns;
    }
}
