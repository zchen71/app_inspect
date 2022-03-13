package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class JobOfferActivity_p2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer_p2);
    }


    public void onHandleClick(View view) {

        switch (view.getId()) {
            case R.id.compareCurrentJob: {
                Log.i("MAIN", "MAIN GET JOBINFO");
                // Todo: compare with the current job
                Intent jobInfoIntent = new Intent(JobOfferActivity_p2.this, OfferComparisonActivity.class);
                startActivity(jobInfoIntent);
            }
            break;
            case R.id.enterNewJobOffer: {
                Log.i("MAIN", "MAIN GET JOBOFFER");
                Intent jobOfferIntent = new Intent(JobOfferActivity_p2.this, JobOfferActivity.class);
                startActivity(jobOfferIntent);
            }
            break;
            case R.id.returnMainMenu: {
                Intent jobInfoIntent = new Intent(JobOfferActivity_p2.this, MainActivity.class);
                startActivity(jobInfoIntent);
            }
            break;
            default:
                break;
        }
    }
}