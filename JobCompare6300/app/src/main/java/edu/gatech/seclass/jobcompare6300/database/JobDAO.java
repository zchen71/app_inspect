package edu.gatech.seclass.jobcompare6300.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import edu.gatech.seclass.jobcompare6300.database.Job.JobEntry;

public class JobDAO {

    private Context context;
    private DatabaseHelper helper;
    private SQLiteDatabase db;
    private AtomicInteger jobId;


    public JobDAO(Context context) {
        helper = new DatabaseHelper(context);
    }


    public void insert(Job job) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(JobEntry.JOB_TITLE, job.getJobTitle());
        values.put(JobEntry.JOB_COMPANY, job.getJobCompany());
        values.put(JobEntry.JOB_LOCATION, job.getJobLocation());
        values.put(JobEntry.COST_OF_LIVING, job.getCostOfLiving());
        values.put(JobEntry.YEARLY_SALARY, job.getYearlySalary());
        values.put(JobEntry.YEARLY_BONUS, job.getYearlyBonus());
        values.put(JobEntry.RETIREMENT_BENEFITS, job.getRetirementBenefits());
        values.put(JobEntry.RELOCATION_STIPEND, job.getRelocationStipend());
        values.put(JobEntry.RESTRICTED_STOCK, job.getRestrictedStock());
        values.put(Job.JobEntry.IS_CURRENT_JOB, false);

        db.insert(JobEntry.TABLE_NAME, null, values);
    }

    public void createOrUpdateCurrentJob(Job job) {

        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Job.JobEntry.JOB_TITLE, job.getJobTitle());
        values.put(Job.JobEntry.JOB_COMPANY, job.getJobCompany());
        values.put(Job.JobEntry.JOB_LOCATION, job.getJobLocation());
        values.put(Job.JobEntry.COST_OF_LIVING, job.getCostOfLiving());
        values.put(Job.JobEntry.YEARLY_SALARY, job.getYearlySalary());
        values.put(Job.JobEntry.YEARLY_BONUS, job.getYearlyBonus());
        values.put(Job.JobEntry.RETIREMENT_BENEFITS, job.getRetirementBenefits());
        values.put(Job.JobEntry.RELOCATION_STIPEND, job.getRelocationStipend());
        values.put(Job.JobEntry.RESTRICTED_STOCK, job.getRestrictedStock());
        values.put(Job.JobEntry.IS_CURRENT_JOB, true);
        Job currentJob = this.getCurrentJobDetail();
        if(currentJob != null) {
            db.update(Job.JobEntry.TABLE_NAME,  values, "_id=?", new String[] {currentJob.getJobId()});
        } else {
            db.insert(Job.JobEntry.TABLE_NAME, null, values);
        }
    }


    @SuppressLint("Range")
    public List<Job> selectAllJob() {
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from job", null);

        List<Job> jobList = new ArrayList<>();

        while (cursor.moveToNext()) {
            Job job = new Job();
            job.setJobTitle(cursor.getString(cursor.getColumnIndex(JobEntry.JOB_TITLE)));
            jobList.add(job);
        }
        cursor.close();
        return jobList;
    }

    /**
     *
     * @param jobId
     * @return JobOffer fetched by respective Id
     */
    public Job getAJobOfferById(int jobId) {
        db = helper.getReadableDatabase();

        Cursor findJobByIdCursor = db.query(JobEntry.TABLE_NAME, helper.getTableColumns(), "id=?", new String[] {String.valueOf(jobId)}, null, null, null);

        // To increase performance first get the index of each column in the cursor
//        final int idIndex = findJobByIdCursor.getColumnIndex(JobEntry.JOB_ID);
        final int jobTitleIndex = findJobByIdCursor.getColumnIndex(JobEntry.JOB_TITLE);
        final int jobCompanyIndex = findJobByIdCursor.getColumnIndex(JobEntry.JOB_COMPANY);
        final int jobLocationIndex = findJobByIdCursor.getColumnIndex(JobEntry.JOB_LOCATION);
        final int costOfLivingIndex = findJobByIdCursor.getColumnIndex(JobEntry.COST_OF_LIVING);
        final int jobScoreIndex = findJobByIdCursor.getColumnIndex(JobEntry.JOB_SCORE);
        final int relocationStipendIndex = findJobByIdCursor.getColumnIndex(JobEntry.RELOCATION_STIPEND);
        final int restrictedStockIndex = findJobByIdCursor.getColumnIndex(JobEntry.RESTRICTED_STOCK);
        final int retirementBenefitsIndex = findJobByIdCursor.getColumnIndex(JobEntry.RETIREMENT_BENEFITS);
        final int yearlySalaryIndex = findJobByIdCursor.getColumnIndex(JobEntry.YEARLY_SALARY);
        final int yearlyBonusIndex = findJobByIdCursor.getColumnIndex(JobEntry.YEARLY_BONUS);
        final int isCurrentJob = findJobByIdCursor.getColumnIndex(JobEntry.IS_CURRENT_JOB);

        Job jobOffer = new Job();
        jobOffer.setJobTitle(findJobByIdCursor.getString(jobTitleIndex));
        jobOffer.setJobCompany(findJobByIdCursor.getString(jobCompanyIndex));
        jobOffer.setJobLocation(findJobByIdCursor.getString(jobLocationIndex));
        jobOffer.setCostOfLiving(findJobByIdCursor.getInt(costOfLivingIndex));
        jobOffer.setJobScore(findJobByIdCursor.getDouble(jobScoreIndex));
        jobOffer.setRelocationStipend(findJobByIdCursor.getDouble(relocationStipendIndex));
        jobOffer.setRestrictedStock(findJobByIdCursor.getDouble(restrictedStockIndex));
        jobOffer.setRetirementBenefits(findJobByIdCursor.getInt(retirementBenefitsIndex));
        jobOffer.setYearlySalary(findJobByIdCursor.getDouble(yearlySalaryIndex));
        jobOffer.setYearlyBonus(findJobByIdCursor.getDouble(yearlyBonusIndex));
        jobOffer.setCurrentJob(findJobByIdCursor.getInt(isCurrentJob));
        findJobByIdCursor.close();

        return jobOffer;
    }

    @SuppressLint("Range")
    public Job getCurrentJobDetail() {
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from job where isCurrentJob=?", new String[] {"1"});
        Job currentJob = null;
        if(cursor.getCount() != 0) {
            currentJob = new Job();
            cursor.moveToFirst();
            currentJob.setJobId(cursor.getString(cursor.getColumnIndex(JobEntry._ID)));
            currentJob.setJobCompany(cursor.getString(cursor.getColumnIndex(JobEntry.JOB_COMPANY)));
            currentJob.setJobCompany(cursor.getString(cursor.getColumnIndex(JobEntry.JOB_COMPANY)));
            currentJob.setJobTitle(cursor.getString(cursor.getColumnIndex(JobEntry.JOB_TITLE)));
            currentJob.setJobLocation(cursor.getString(cursor.getColumnIndex(JobEntry.JOB_LOCATION)));
            currentJob.setCostOfLiving(cursor.getInt(cursor.getColumnIndex(JobEntry.COST_OF_LIVING)));
            currentJob.setYearlySalary(cursor.getDouble(cursor.getColumnIndex(JobEntry.YEARLY_SALARY)));
            currentJob.setYearlyBonus(cursor.getDouble(cursor.getColumnIndex(JobEntry.YEARLY_BONUS)));
            currentJob.setRetirementBenefits(cursor.getInt(cursor.getColumnIndex(JobEntry.RETIREMENT_BENEFITS)));
            currentJob.setRelocationStipend(cursor.getDouble(cursor.getColumnIndex(JobEntry.RELOCATION_STIPEND)));
            currentJob.setRestrictedStock(cursor.getDouble(cursor.getColumnIndex(JobEntry.RESTRICTED_STOCK)));
        }
        return currentJob;
    }

}
