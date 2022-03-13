package edu.gatech.seclass.jobcompare6300.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import edu.gatech.seclass.jobcompare6300.database.ComparisonSetting.SettingsEntry;

public class ComparisonSettingDetail {
    private Context context;
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    private static final String COMPARISON_SETTING_UNIQUE_ID = "1";

    public ComparisonSettingDetail(Context context) {
        helper = new DatabaseHelper(context);
    }

    public ComparisonSetting initEntry() {
        ComparisonSetting currentSetting = getComparisonSetting();

        if (currentSetting == null) {
            currentSetting = insertComparisonSetting(new ComparisonSetting());
        }
        return currentSetting;
    }

    public ComparisonSetting insertComparisonSetting(ComparisonSetting comparisonSetting) {

        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SettingsEntry._ID, COMPARISON_SETTING_UNIQUE_ID);
        values.put(SettingsEntry.Year_Salary, comparisonSetting.getYearSalary());
        values.put(SettingsEntry.Year_Bonus, comparisonSetting.getYearBonus());
        values.put(SettingsEntry.Relocation_Stipend, comparisonSetting.getRelocationStipend());
        values.put(SettingsEntry.Retire_Benefit, comparisonSetting.getRetirementBenefit());
        values.put(SettingsEntry.Stock_Award, comparisonSetting.getStockAward());

        db.insert(SettingsEntry.TABLE_NAME, null, values);

        return comparisonSetting;
    }

    public void update(ComparisonSetting comparisonSetting) {

        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SettingsEntry.Year_Salary, comparisonSetting.getYearSalary());
        values.put(SettingsEntry.Year_Bonus, comparisonSetting.getYearBonus());
        values.put(SettingsEntry.Relocation_Stipend, comparisonSetting.getRelocationStipend());
        values.put(SettingsEntry.Retire_Benefit, comparisonSetting.getRetirementBenefit());
        values.put(SettingsEntry.Stock_Award, comparisonSetting.getStockAward());
        db.update(SettingsEntry.TABLE_NAME, values, "_id = ?", new String[] { COMPARISON_SETTING_UNIQUE_ID });
    }


    @SuppressLint("Range")
    public ComparisonSetting getComparisonSetting() {
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from comparisonSetting where _id = 1" , null);

        ComparisonSetting setting = null;
        while(cursor.moveToNext()) {
            setting = new ComparisonSetting();
            setting.setRelocationStipend(cursor.getInt(cursor.getColumnIndex(SettingsEntry.Relocation_Stipend)));
            setting.setYearBonus(cursor.getInt(cursor.getColumnIndex(SettingsEntry.Year_Bonus)));
            setting.setYearSalary(cursor.getInt(cursor.getColumnIndex(SettingsEntry.Year_Salary)));
            setting.setRetirementBenefit(cursor.getInt(cursor.getColumnIndex(SettingsEntry.Retire_Benefit)));
            setting.setStockAward(cursor.getInt(cursor.getColumnIndex(SettingsEntry.Stock_Award)));
        }
        cursor.close();

        return setting;
    }

}
