package edu.gatech.seclass.jobcompare6300.util;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.ComparisonSetting;
import edu.gatech.seclass.jobcompare6300.database.Job;

public class JobOfferUtil {

    /**
     * Requirement :-
     *
     * When choosing to compare job offers, a user will:
     * Be shown a list of job offers, displayed as Title and Company, ranked from best to worst
     * (see below for details), and including the current job (if present), clearly indicated.
     * Select two jobs to compare and trigger the comparison.
     * Be shown a table comparing the two jobs, displaying, for each job:
     * Title
     * Company
     * Location
     * Yearly salary adjusted for cost of living
     * Yearly bonus adjusted for cost of living
     * Retirement benefits
     * Relocation stipend
     * Restricted stock unit award
     *
     * Be offered to perform another comparison or go back to the main menu.
     *
     * @param job1
     * @param job2
     * @return Comparison of two jobs where both the job objects will be returned in the two tables.
     * We shall be returning both jobs in a comparison table which will be populated with the
     * values in the object and them populated in the respective table columns
     */
    public List<Job> compareJobs(Job job1, Job job2) {
        List<Job> comparedJobs = new ArrayList<>();

        comparedJobs.add(job1);
        comparedJobs.add(job2);

        return comparedJobs;
    }

    /**
     * Requirement :-
     * When adjusting the comparison settings, the user can assign integer weights to:
     * Yearly salary
     * Yearly bonus
     * Retirement benefits
     * Relocation stipend
     * Restricted stock unit award
     *
     * @param jobOfferToAdjust
     * @param comparisonSetting
     * @return adjusted job values based on the comparison setting provided by user
     */
    public Job adjustComparisonSettings(Job jobOfferToAdjust, ComparisonSetting comparisonSetting) {
        Job jobOffer = jobOfferToAdjust;

        jobOffer.setYearlySalary(jobOffer.getYearlySalary() * comparisonSetting.getYearlySalaryWeight());
        jobOffer.setYearlyBonus(jobOffer.getYearlyBonus() * comparisonSetting.getYearlyBonusWeight());
        jobOffer.setRetirementBenefits(jobOffer.getRetirementBenefits() *
                comparisonSetting.getRetirementBenefitsWeight());
        jobOffer.setRelocationStipend(jobOffer.getRelocationStipend() *
                comparisonSetting.getRelocationStipendWeight());
        jobOffer.setRestrictedStock(jobOffer.getRestrictedStock() *
                comparisonSetting.getRestrictedStockWeight());

        return jobOffer;
    }

    /**
     * Requirement :
     * When ranking jobs, a job’s score is computed as the weighted sum of:
     *
     * AYS + AYB + RS + (RPB * AYS / 100) + (RSUA / 4)
     *
     * where:
     * AYS = yearly salary adjusted for cost of living
     * AYB = yearly bonus adjusted for cost of living
     * RBP = retirement benefits percentage
     * RS = relocation stipend
     * RSUA = restricted stock unit award
     *
     * The rationale for the RSUA subformula is:
     * value of a restricted stock unit award vests over 4 years
     * average value of the restricted stock unit award per year (RSUA / 4)
     *
     * For example, if the weights are 2 for the yearly salary, 2 for relocation stipend,
     * and 1 for all other factors, the score would be computed as:
     *
     *
     * 2/7 * AYS + 1/7 * AYB + 2/7 * RS + 1/7 * (RPB * AYS / 100) + 1/7 * (RSUA / 4)
     * @param job
     * @return Computed job score of a job offer
     */
    public double computeScoreOfAJobOffer(Job job, ComparisonSetting comparisonSetting) {
        // job’s score is computed as the weighted sum so we would run the comparison on the job
        // first and if no weights are provided then we just keep them as 1

        Job weightedJob = adjustComparisonSettings(job,comparisonSetting);

        double jobOfferScore = weightedJob.getYearlySalary() + weightedJob.getYearlyBonus() +
                weightedJob.getRelocationStipend() +
                ((weightedJob.getRetirementBenefits() * weightedJob.getYearlySalary()) / 100)
                + weightedJob.getRestrictedStock()/4;

        return  jobOfferScore;
    }
}
