package edu.gatech.seclass.jobcompare6300;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.database.Job;
import edu.gatech.seclass.jobcompare6300.database.JobDAO;

public class JobInfoActivity extends AppCompatActivity {

    private EditText jobTitle;
    private EditText jobCompany;
    private EditText jobLocation;
    private EditText livingCostIndex;
    private EditText yearlySalary;
    private EditText yearlyBonus;
    private EditText retirementBenefits;
    private EditText relocationStipend;
    private EditText restrictedStockAward;

//    private Spinner spinner, spinnerCountry;
//    private TextView textView;
//    private List<String> data = null;
//    private ArrayAdapter<String> adapter;

    private JobDAO jobDAO;

    private Job currentJob;

    private static final String INVALID_INPUT = "Invalid Input";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info);
        this.jobDAO = new JobDAO(this);

        this.currentJob = jobDAO.getCurrentJobDetail();

        jobTitle = (EditText) findViewById(R.id.etJobTitleId);
        jobCompany = (EditText) findViewById(R.id.etJobCompanyId);
        jobLocation = (EditText) findViewById(R.id.etLocationId);
        livingCostIndex = (EditText) findViewById(R.id.etLivingCostId);
        yearlySalary = (EditText) findViewById(R.id.etSalaryId);
        yearlyBonus = (EditText) findViewById(R.id.etBonusId);
        retirementBenefits = (EditText) findViewById(R.id.etBenefitsId);
        relocationStipend = (EditText) findViewById(R.id.etRelocationId);
        restrictedStockAward = (EditText) findViewById(R.id.etStockAwardId);


        this.displayJobDetail();

        //todo: check the requirement for specific impl
//        spinnerCountry = (Spinner) findViewById(R.id.etLivingCostId);
//        spinnerCountry.setPrompt("");
//        List<String> da = this.getLivingCostDisplayList();
//        adapter=new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_dropdown_item,this.data);
//        spinnerCountry.setAdapter(adapter);
//        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
//
//            public void onItemSelected(AdapterView<?>parent,View view,
//                    int position,long id){
//                String[]countries=data.toArray(new String[data.size()]);
//                Toast.makeText(JobInfoActivity.this,"您的国籍是："+countries[position],Toast.LENGTH_SHORT).show();
//            }
//            public void onNothingSelected(AdapterView<?>parent){
////TODOAuto-generatedmethodstub
//            }
//        });

    }

    public void onSaveClick(View view) {
        if(isJobDetailInputValid()) {
            Job job = this.createJobFromForm();
            jobDAO.createOrUpdateCurrentJob(job);
            this.finish();
        } else {
            Toast.makeText(JobInfoActivity.this,"Please correct the invalid Information",Toast.LENGTH_SHORT).show();
        }
    }

    public void onCancelClick(View view) {
        this.finish();
    }

    private void displayJobDetail() {
        if(currentJob != null) {
            jobTitle.setText(currentJob.getJobTitle());
            jobCompany.setText(currentJob.getJobCompany());
            jobLocation.setText(currentJob.getJobLocation());
            livingCostIndex.setText(String.valueOf(currentJob.getCostOfLiving()));
            yearlySalary.setText(String.valueOf(currentJob.getYearlySalary()));
            yearlyBonus.setText(String.valueOf(currentJob.getYearlyBonus()));
            retirementBenefits.setText(String.valueOf(currentJob.getRetirementBenefits()));
            relocationStipend.setText(String.valueOf(currentJob.getRelocationStipend()));
            restrictedStockAward.setText(String.valueOf(currentJob.getRestrictedStock()));
        }
    }

    private Boolean isJobDetailInputValid() {
        Boolean isValid = true;
        if(jobTitle.getText() == null || jobTitle.getText().toString().isEmpty()) {
            jobTitle.setError(INVALID_INPUT);
            jobTitle.setError("Please Enter Correct Job Title");
            isValid = false;
        }
        if(jobCompany.getText() == null || jobCompany.getText().toString().isEmpty()) {
            jobCompany.setError(INVALID_INPUT);
            jobTitle.setError("Please Enter Correct Job Company");
            isValid = false;
        }
        if(jobLocation.getText() == null || jobLocation.getText().toString().isEmpty()) {
            jobLocation.setError(INVALID_INPUT);
            jobTitle.setError("Please Enter Correct Job Location");
            isValid = false;
        }
        if(livingCostIndex.getText() == null || livingCostIndex.getText().toString().isEmpty()) {
            livingCostIndex.setError(INVALID_INPUT);
            jobTitle.setError("Please Enter Correct Living cost Index");
            isValid = false;
        }
        if(yearlySalary.getText() == null || yearlySalary.getText().toString().isEmpty()) {
            yearlySalary.setError(INVALID_INPUT);
            jobTitle.setError("Please Enter Correct yearly Salary");
            isValid = false;
        }
        if(yearlyBonus.getText() == null || yearlyBonus.getText().toString().isEmpty()) {
            yearlyBonus.setError(INVALID_INPUT);
            jobTitle.setError("Please Enter Correct yearly bonus");
            isValid = false;
        }
        if(retirementBenefits.getText() == null || retirementBenefits.getText().toString().isEmpty()) {
            retirementBenefits.setError(INVALID_INPUT);
            jobTitle.setError("Please Enter Correct retirement benefits");
            isValid = false;
        }
        if(relocationStipend.getText() == null || relocationStipend.getText().toString().isEmpty()) {
            relocationStipend.setError(INVALID_INPUT);
            jobTitle.setError("Please Enter Correct relocation stipend");
            isValid = false;
        }
        if(restrictedStockAward.getText() == null || restrictedStockAward.getText().toString().isEmpty()) {
            restrictedStockAward.setError(INVALID_INPUT);
            jobTitle.setError("Please Enter correct restricted stock award");
            isValid = false;
        }
        return isValid;
    }

    private Job createJobFromForm() {
        Job job = new Job();
        job.setJobTitle(jobTitle.getText().toString());
        job.setJobCompany(jobCompany.getText().toString());
        job.setJobLocation(jobLocation.getText().toString());
        job.setCostOfLiving(Integer.parseInt(livingCostIndex.getText().toString()));
        job.setYearlySalary(Double.parseDouble(yearlySalary.getText().toString()));
        job.setYearlyBonus(Double.parseDouble(yearlyBonus.getText().toString()));
        job.setRetirementBenefits(Integer.parseInt(retirementBenefits.getText().toString()));
        job.setRelocationStipend(Double.parseDouble(relocationStipend.getText().toString()));
        job.setRestrictedStock(Double.parseDouble(restrictedStockAward.getText().toString()));
        return job;
    }




}