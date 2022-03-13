package edu.gatech.seclass.jobcompare6300;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.gatech.seclass.jobcompare6300.database.JobAdapter;
import edu.gatech.seclass.jobcompare6300.database.JobDAO;

public class OfferComparisonActivity extends AppCompatActivity {

    private ListView jobListView;

    private JobDAO jobDAO;
    private JobAdapter jobAdapter;
    private ArrayList<ComparisonItemModel> jobModelItemList = new ArrayList<ComparisonItemModel>();

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context arg0, Intent intent) {
            String action = intent.getAction();
            if (action.equals(getString(R.string.jobCompareCloseActivity))) {
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_comparison);

        jobListView = (ListView) findViewById(R.id.jobListViewId);
        jobListView.setClickable(false);
        this.jobDAO = new JobDAO(this);
        jobAdapter = new JobAdapter(this, jobModelItemList);

        jobListView.setAdapter(jobAdapter);
        /*
        Job job = new Job();
        job.setJobId("1");
        job.setJobCompany("testCompany");
        job.setJobTitle("testScrore");
        jobModelItemList.add(new ComparisonItemModel(job));
        jobDAO.insert(job);
        Job job2 = new Job();
        job2.setJobId("2");
        job2.setJobCompany("testCompany2");
        job2.setJobTitle("testScrore2");
        jobModelItemList.add(new ComparisonItemModel(job2));
        jobDAO.insert(job2);
        Job job3 = new Job();
        job3.setJobId("3");
        job3.setJobCompany("testCompany3");
        job3.setJobTitle("testScrore3");
        jobDAO.insert(job3);
        jobModelItemList.add(new ComparisonItemModel(job3));
        */
        // TODO sort all jobs by job score prior to insertion to jobModelItemList

        jobDAO.selectAllJob().forEach(jobItem -> {
            jobModelItemList.add(new ComparisonItemModel(jobItem));
        });

        // Log.i("JobOffer", "the job list " + jobItemList.get(0).getJobId());
        jobAdapter.notifyDataSetChanged();

        registerReceiver(broadcastReceiver, new IntentFilter(getString(R.string.jobCompareCloseActivity)));
    }

    public void onCompareClick(View view) {
        // First validate selections
        ArrayList<Integer> selectedList = new ArrayList<Integer>();
        for (int i = 0; i < jobModelItemList.size(); ++i) {
            if (jobModelItemList.get(i).isSelected()) {
                if (selectedList.size() == 2) {
                    Context context = getApplicationContext();
                    Toast.makeText(context, "Error: only 2 jobs can be selected for comparison. Uncheck some jobs until only 2 are selected before pressing Compare again.", Toast.LENGTH_LONG).show();
                    return;
                }
                selectedList.add(i);
            }
        }
        if (selectedList.size() < 2) {
            Context context = getApplicationContext();
            Toast.makeText(context, "Error: 2 jobs must be selected for comparison. Select 2 jobs before pressing Compare again.", Toast.LENGTH_LONG).show();
            return;
        }

        // With selections provided, prepare intent message and send.

        Intent intent = new Intent(this, OfferComparisonDetailActivity.class);
//        intent.putExtra("leftJobId", jobModelItemList.get(selectedList.get(0)).getJob().getJobId());
//        intent.putExtra("rightJobItem", jobModelItemList.get(selectedList.get(1)).getJob().getJobId());
        startActivity(intent);
    }

    public void onMainMenuClick(View view) {
        this.finish();
    }
}