package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.database.DatabaseHelper;
import edu.gatech.seclass.jobcompare6300.database.JobDAO;

public class MainActivity extends AppCompatActivity {

    private JobDAO jobDAO;
    private DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.myDb = new DatabaseHelper(this);
        this.jobDAO = new JobDAO(this);
    }


    public void onHandleClick(View view) {

        switch (view.getId()) {
            case R.id.jobInfoBtn: {
                Log.i("MAIN", "MAIN GET JOBINFO");
                Intent jobInfoIntent = new Intent(MainActivity.this, JobInfoActivity.class);
                startActivity(jobInfoIntent);
            }
            break;
            case R.id.jobOfferBtn: {
                Log.i("MAIN", "MAIN GET JOBOFFER");
                Intent jobOfferIntent = new Intent(MainActivity.this, JobOfferActivity.class);
                startActivity(jobOfferIntent);
            }
            break;
            case R.id.offerComparisonBtn: {
                Intent jobInfoIntent = new Intent(MainActivity.this, OfferComparisonActivity.class);
                startActivity(jobInfoIntent);
            }
            break;
            case R.id.comparisonSettingBtn: {
                Intent jobInfoIntent = new Intent(MainActivity.this, ComparisonSettingActivity.class);
                startActivity(jobInfoIntent);
            }
            break;
            default:
                break;
        }
    }



//    public void onSaveClick(View view) {
//
//        Job job = new Job();
//        job.setJobId("testId");
//        job.setJobCompany("testCompany");
//        job.setJobTitle("testScrore");
//        jobDAO.insert(job);
//        Log.i("MAIN", "Save to database");
//    }
}