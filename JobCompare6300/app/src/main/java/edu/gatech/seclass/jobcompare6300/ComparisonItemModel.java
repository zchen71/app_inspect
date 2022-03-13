package edu.gatech.seclass.jobcompare6300;

import edu.gatech.seclass.jobcompare6300.database.Job;

public class ComparisonItemModel {
    private boolean isSelected;
    private Job jobObject;

    public ComparisonItemModel(Job job) {
        this.setJob(job);
        this.isSelected=false;
    }


    public Job getJob() {
        return jobObject;
    }

    public String getCompany(){
        return this.jobObject.getJobCompany();
    }

    public String getJobTitle(){
        return this.jobObject.getJobTitle();
    }

    public void setJob(Job job) {
        this.jobObject = job;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
