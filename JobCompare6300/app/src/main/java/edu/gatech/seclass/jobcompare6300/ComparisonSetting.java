package edu.gatech.seclass.jobcompare6300;

import java.io.Serializable;

public class ComparisonSetting implements Serializable {
    private int yearlySalaryWeight;
    private int yearlyBonusWeight;
    private int retirementBenefitsWeight;
    private int relocationStipendWeight;
    private int restrictedStockWeight;

    //Class initialized with equal weights initially i.e 1
    public ComparisonSetting() {
        this.yearlySalaryWeight = 1;
        this.yearlyBonusWeight = 1;
        this.relocationStipendWeight=1;
        this.restrictedStockWeight=1;
        this.retirementBenefitsWeight=1;
    }

    public int getYearlySalaryWeight() {
        return yearlySalaryWeight;
    }

    public void setYearlySalaryWeight(int yearlySalaryWeight) {
        this.yearlySalaryWeight = yearlySalaryWeight;
    }

    public int getYearlyBonusWeight() {
        return yearlyBonusWeight;
    }

    public void setYearlyBonusWeight(int yearlyBonusWeight) {
        this.yearlyBonusWeight = yearlyBonusWeight;
    }

    public int getRetirementBenefitsWeight() {
        return retirementBenefitsWeight;
    }

    public void setRetirementBenefitsWeight(int retirementBenefitsWeight) {
        this.retirementBenefitsWeight = retirementBenefitsWeight;
    }

    public int getRelocationStipendWeight() {
        return relocationStipendWeight;
    }

    public void setRelocationStipendWeight(int relocationStipendWeight) {
        this.relocationStipendWeight = relocationStipendWeight;
    }

    public int getRestrictedStockWeight() {
        return restrictedStockWeight;
    }

    public void setRestrictedStockWeight(int restrictedStockWeight) {
        this.restrictedStockWeight = restrictedStockWeight;
    }
}
