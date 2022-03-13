package edu.gatech.seclass.jobcompare6300.database;

import android.provider.BaseColumns;

import java.io.Serializable;

public class ComparisonSetting implements Serializable {

    private Integer yearBonus;
    private Integer yearSalary;
    private Integer retirementBenefits;
    private Integer relocationStipend;
    private Integer stockAward;

    public ComparisonSetting() {
        yearBonus = 1;
        yearSalary = 1;
        retirementBenefits = 1;
        relocationStipend = 1;
        stockAward = 1;
    }

    public Integer getYearBonus() {
        return yearBonus;
    }

    public void setYearBonus(Integer yearBonus) {
        this.yearBonus = yearBonus;
    }

    public Integer getYearSalary() {
        return yearSalary;
    }

    public void setYearSalary(Integer yearSalary) {
        this.yearSalary = yearSalary;
    }

    public Integer getRetirementBenefit() {
        return retirementBenefits;
    }

    public void setRetirementBenefit(Integer retirementBenefits) {
        this.retirementBenefits = retirementBenefits;
    }

    public Integer getRelocationStipend() {
        return relocationStipend;
    }

    public void setRelocationStipend(Integer relocationStipend) {
        this.relocationStipend = relocationStipend;
    }

    public Integer getStockAward() {
        return stockAward;
    }

    public void setStockAward(Integer stockAward) {
        this.stockAward = stockAward;
    }

    public static class SettingsEntry implements BaseColumns {

        public static final String TABLE_NAME = "comparisonSetting";
        public static final String Year_Salary = "yearSalary";
        public static final String Year_Bonus = "yearBonus";
        public static final String Retire_Benefit = "retireBenefit";
        public static final String Relocation_Stipend = "relocationStipend";
        public static final String Stock_Award = "stockAward";

    }
}

