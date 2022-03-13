package edu.gatech.seclass.jobcompare6300.database;

import android.provider.BaseColumns;

import java.io.Serializable;

public class Job implements Serializable {

    private String jobId;
    private String jobCompany;
    private String jobTitle;
    private String jobLocation;
    private Integer costOfLiving;
    private Double yearlySalary;
    private Double yearlyBonus;
    private Integer retirementBenefits;
    private Double relocationStipend;
    private Double restrictedStock;
    private Double JobScore;
    private Integer isCurrentJob;


    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobCompany() {
        return jobCompany;
    }

    public void setJobCompany(String jobCompany) {
        this.jobCompany = jobCompany;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public Integer getCostOfLiving() {
        return costOfLiving;
    }

    public void setCostOfLiving(Integer costOfLiving) {
        this.costOfLiving = costOfLiving;
    }

    public Double getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(Double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public Double getYearlyBonus() {
        return yearlyBonus;
    }

    public void setYearlyBonus(Double yearlyBonus) {
        this.yearlyBonus = yearlyBonus;
    }

    public Integer getRetirementBenefits() {
        return retirementBenefits;
    }

    public void setRetirementBenefits(Integer retirementBenefits) {
        this.retirementBenefits = retirementBenefits;
    }

    public Double getRelocationStipend() {
        return relocationStipend;
    }

    public void setRelocationStipend(Double relocationStipend) {
        this.relocationStipend = relocationStipend;
    }

    public Double getRestrictedStock() {
        return restrictedStock;
    }

    public void setRestrictedStock(Double restrictedStock) {
        this.restrictedStock = restrictedStock;
    }

    public Double getJobScore() {
        return JobScore;
    }

    public void setJobScore(Double jobScore) {
        JobScore = jobScore;
    }

    public int isCurrentJob() {
        return isCurrentJob;
    }

    public void setCurrentJob(int currentJob) {
        isCurrentJob = currentJob;
    }

    public static class JobEntry implements BaseColumns {
        public static final String TABLE_NAME = "job";
        public static final String JOB_COMPANY = "jobCompany";
        public static final String JOB_TITLE = "title";
        public static final String JOB_LOCATION = "jobLocation";
        public static final String COST_OF_LIVING = "livingCost";
        public static final String YEARLY_SALARY = "yearlySalary";
        public static final String YEARLY_BONUS = "yearlyBonus";
        public static final String RETIREMENT_BENEFITS = "retirementBenefit";
        public static final String RELOCATION_STIPEND = "relocationStipend";
        public static final String RESTRICTED_STOCK = "restrictedStock";
        public static final String JOB_SCORE = "jobScore";
        public static final String IS_CURRENT_JOB = "isCurrentJob";

    }
}
