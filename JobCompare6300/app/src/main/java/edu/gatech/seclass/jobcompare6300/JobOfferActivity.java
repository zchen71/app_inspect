package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.database.Job;
import edu.gatech.seclass.jobcompare6300.database.JobDAO;

public class JobOfferActivity extends AppCompatActivity {

    private EditText jobTitle;
    private EditText jobCompany;
    private EditText jobLocation;
    private EditText livingCostIndex;
    private EditText yearlySalary;
    private EditText yearlyBonus;
    private EditText retirementBenefits;
    private EditText relocationStipend;
    private EditText restrictedStockAward;

    private JobDAO jobDAO;

    private Job currentJob;

    private static final String INVALID_INPUT = "Invalid Input";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer);
        this.jobDAO = new JobDAO(this);
        this.currentJob = jobDAO.getCurrentJobDetail();

        jobTitle = (EditText) findViewById(R.id.etTitleId);
        jobCompany = (EditText) findViewById(R.id.evCompany);
        jobLocation = (EditText) findViewById(R.id.evLocation);
        livingCostIndex = (EditText) findViewById(R.id.evLivingCost);
        yearlySalary = (EditText) findViewById(R.id.evSalary);
        yearlyBonus = (EditText) findViewById(R.id.evBonus);
        retirementBenefits = (EditText) findViewById(R.id.evBenefits);
        relocationStipend = (EditText) findViewById(R.id.evRelocation);
        restrictedStockAward = (EditText) findViewById(R.id.evStockAward);
    }

    public void onSaveClick(View view) {
        //todo: verification
        if(!isJobDetailInputInvalid()) {
            Job job = this.createJobFromForm();
            jobDAO.insert(job);
            Intent jobOffer_2_Intent = new Intent(JobOfferActivity.this, JobOfferActivity_p2.class);
            startActivity(jobOffer_2_Intent);
        }
    }

//    public void onHandleClick(View view) {
//
//        switch (view.getId()) {
//            case R.id.compareOfferButtonID: {
//                Intent jobInfoIntent = new Intent(JobOfferActivity.this, OfferComparisonActivity.class);
//                startActivity(jobInfoIntent);
//            }
//            break;
//            default:
//                break;
//        }
//    }

    public void onCancelClick(View view) {
        Intent mainMenuIntent = new Intent(JobOfferActivity.this, MainActivity.class);
        startActivity(mainMenuIntent);
    }

    private Boolean isJobDetailInputInvalid() {
        Boolean invalid = false;
        if(jobTitle.getText() == null || jobTitle.getText().toString().isEmpty()) {
            jobTitle.setError(INVALID_INPUT);
            invalid = true;
        }
        if(jobCompany.getText() == null || jobCompany.getText().toString().isEmpty()) {
            jobCompany.setError(INVALID_INPUT);
            invalid = true;
        }
        if(jobLocation.getText() == null || jobLocation.getText().toString().isEmpty()) {
            jobLocation.setError(INVALID_INPUT);
            invalid = true;
        }
        if(livingCostIndex.getText() == null || livingCostIndex.getText().toString().isEmpty()) {
            livingCostIndex.setError(INVALID_INPUT);
            invalid = true;
        }
        if(yearlySalary.getText() == null || yearlySalary.getText().toString().isEmpty()) {
            yearlySalary.setError(INVALID_INPUT);
            invalid = true;
        }
        if(yearlyBonus.getText() == null || yearlyBonus.getText().toString().isEmpty()) {
            yearlyBonus.setError(INVALID_INPUT);
            invalid = true;
        }
        if(retirementBenefits.getText() == null || retirementBenefits.getText().toString().isEmpty()) {
            retirementBenefits.setError(INVALID_INPUT);
            invalid = true;
        }
        if(relocationStipend.getText() == null || relocationStipend.getText().toString().isEmpty()) {
            relocationStipend.setError(INVALID_INPUT);
            invalid = true;
        }
        if(restrictedStockAward.getText() == null || restrictedStockAward.getText().toString().isEmpty()) {
            restrictedStockAward.setError(INVALID_INPUT);
            invalid = true;
        }
        return invalid;
    }

    private Job createJobFromForm() {
        Job job = new Job();
        job.setJobTitle(jobTitle.getText().toString());
        job.setJobCompany(jobTitle.getText().toString());
        job.setJobLocation(jobLocation.getText().toString());
        job.setCostOfLiving(Integer.parseInt(livingCostIndex.getText().toString()));
        job.setYearlySalary(Double.parseDouble(yearlySalary.getText().toString()));
        job.setYearlyBonus(Double.parseDouble(retirementBenefits.getText().toString()));
        job.setRetirementBenefits(Integer.parseInt(retirementBenefits.getText().toString()));
        job.setRelocationStipend(Double.parseDouble(relocationStipend.getText().toString()));
        job.setRestrictedStock(Double.parseDouble(restrictedStockAward.getText().toString()));
        return job;
    }
}