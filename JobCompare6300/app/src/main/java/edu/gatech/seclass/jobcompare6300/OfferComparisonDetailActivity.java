package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.gatech.seclass.jobcompare6300.database.Job;
import edu.gatech.seclass.jobcompare6300.database.JobDAO;

public class OfferComparisonDetailActivity extends AppCompatActivity {
    private JobDAO jobDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_comparison_detail);
        this.jobDAO = new JobDAO(this);

        Job leftJob;
        Job rightJob;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String leftJobId = extras.getString("leftJobId");
            String rightJobId = extras.getString("rightJobId");

            leftJob = this.jobDAO.getAJobOfferById(Integer.parseInt(leftJobId));
            rightJob = this.jobDAO.getAJobOfferById(Integer.parseInt(rightJobId));

            //The key argument here must match that used in the other activity
        } else {
            throw new RuntimeException("Error: This activity MUST have extras provided in intent.");
        }

        ((TextView) findViewById(R.id.leftTitle)).setText(leftJob.getJobTitle());
        ((TextView) findViewById(R.id.leftCompany)).setText(leftJob.getJobCompany());
        ((TextView) findViewById(R.id.leftLocation)).setText(leftJob.getJobLocation());
        ((TextView) findViewById(R.id.leftSalary)).setText(String. format("%.2f", leftJob.getYearlySalary()));
        ((TextView) findViewById(R.id.leftBonus)).setText(String. format("%.2f", leftJob.getYearlyBonus()));
        ((TextView) findViewById(R.id.leftRetirementBenefits)).setText(String. format("%.2f", leftJob.getRetirementBenefits()));
        ((TextView) findViewById(R.id.leftRelocation)).setText(String. format("%.2f", leftJob.getRelocationStipend()));
        ((TextView) findViewById(R.id.leftStockAward)).setText(String. format("%.2f", leftJob.getRestrictedStock()));


        ((TextView) findViewById(R.id.rightTitle)).setText(rightJob.getJobTitle());
        ((TextView) findViewById(R.id.rightCompany)).setText(rightJob.getJobCompany());
        ((TextView) findViewById(R.id.rightLocation)).setText(rightJob.getJobLocation());
        ((TextView) findViewById(R.id.rightSalary)).setText(String. format("%.2f", rightJob.getYearlySalary()));
        ((TextView) findViewById(R.id.rightBonus)).setText(String. format("%.2f", rightJob.getYearlyBonus()));
        ((TextView) findViewById(R.id.rightRetirementBenefits)).setText(String. format("%.2f", rightJob.getRetirementBenefits()));
        ((TextView) findViewById(R.id.rightRelocation)).setText(String. format("%.2f", rightJob.getRelocationStipend()));
        ((TextView) findViewById(R.id.rightStockAward)).setText(String. format("%.2f", rightJob.getRestrictedStock()));

    }

    public void onCompareAnotherClick(View view) {
        this.finish();
    }

    public void onMainMenuClick(View view) {
        Intent intent = new Intent(getString(R.string.jobCompareCloseActivity));
        sendBroadcast(intent);
        this.finish();
    }
}