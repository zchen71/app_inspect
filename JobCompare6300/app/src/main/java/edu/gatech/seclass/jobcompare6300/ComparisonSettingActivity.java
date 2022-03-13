package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import edu.gatech.seclass.jobcompare6300.database.ComparisonSetting;
import edu.gatech.seclass.jobcompare6300.database.ComparisonSettingDetail;



public class ComparisonSettingActivity extends AppCompatActivity {


    private EditText yearSalary;
    private EditText yearBonus;
    private EditText retirementBenefits;
    private EditText relocationStipend;
    private EditText stockAward;

    private ComparisonSettingDetail comparisonSettingDetail;

    private ComparisonSetting currentSetting;

    private static final String INVALID_INPUT = "Invalid Input";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison_setting);
        this.comparisonSettingDetail = new ComparisonSettingDetail(this);
        currentSetting = comparisonSettingDetail.initEntry();

        yearSalary = findViewById(R.id.evCompareSalary);
        yearBonus = findViewById(R.id.evCompareBonus);
        retirementBenefits = findViewById(R.id.evCompareBenefits);
        relocationStipend = findViewById(R.id.evCompareRelocation);
        stockAward = findViewById(R.id.evCompareStockAward);

        displayCurrentSetting();
    }


    public void onSaveClick_C(View view) {
        if (isInputValid()){
            ComparisonSetting comparisonSetting = this.createSettingsFromForm();
            comparisonSettingDetail.update(comparisonSetting);
            // Todo: update job score for each job
            this.finish();
        }else{
            Toast.makeText(ComparisonSettingActivity.this,"Please correct the invalid Information",Toast.LENGTH_SHORT).show();
        }

    }

    public void onCancelClick_C(View view) {
        Intent mainMenuIntent = new Intent( ComparisonSettingActivity.this, MainActivity.class);
        startActivity(mainMenuIntent);
        this.finish();
    }

    private ComparisonSetting createSettingsFromForm() {
        ComparisonSetting comparisonSetting = new ComparisonSetting();
        comparisonSetting.setYearBonus(Integer.parseInt(yearBonus.getText().toString()));
        comparisonSetting.setYearSalary(Integer.parseInt(yearSalary.getText().toString()));
        comparisonSetting.setStockAward(Integer.parseInt(stockAward.getText().toString()));
        comparisonSetting.setRelocationStipend(Integer.parseInt(relocationStipend.getText().toString()));
        comparisonSetting.setRetirementBenefit(Integer.parseInt(retirementBenefits.getText().toString()));

        return comparisonSetting;
    }

    private void displayCurrentSetting() {
        if(currentSetting != null) {
            yearSalary.setText(String.valueOf(currentSetting.getYearSalary()));
            yearBonus.setText(String.valueOf(currentSetting.getYearBonus()));
            retirementBenefits.setText(String.valueOf(currentSetting.getRetirementBenefit()));
            relocationStipend.setText(String.valueOf(currentSetting.getRelocationStipend()));
            stockAward.setText(String.valueOf(currentSetting.getStockAward()));
        }

    }


    private Boolean isInputValid() {
        Boolean isValid = true;
        char[] txt = yearBonus.getText().toString().toCharArray();
        for (int i=0; i<txt.length; i++){
            if ((int)txt[i]<48 || (int)txt[i]>57){
                yearBonus.setError(INVALID_INPUT);
                yearBonus.setError("Please Enter Integer");
                isValid = false;
            }
        }
        if(yearBonus.getText() == null || yearBonus.getText().toString().isEmpty()) {
            yearBonus.setError(INVALID_INPUT);
            yearBonus.setError("Please Enter Integer");
            isValid = false;
        }
        char[] yS = yearSalary.getText().toString().toCharArray();
        for (int i=0; i<yS.length; i++){
            if ((int)yS[i]<48 || (int)yS[i]>57){
                yearSalary.setError(INVALID_INPUT);
                yearSalary.setError("Please Enter Integer");
                isValid = false;
            }
        }
        if(yearSalary.getText() == null || yearSalary.getText().toString().isEmpty()) {
            yearSalary.setError(INVALID_INPUT);
            yearSalary.setError("Please Enter Integer");
            isValid = false;
        }
        char[] sA = stockAward.getText().toString().toCharArray();
        for (int i=0; i<sA.length; i++){
            if ((int)sA[i]<48 || (int)sA[i]>57){
                stockAward.setError(INVALID_INPUT);
                stockAward.setError("Please Enter Integer");
                isValid = false;
            }
        }
        if(stockAward.getText() == null || stockAward.getText().toString().isEmpty()) {
            stockAward.setError(INVALID_INPUT);
            stockAward.setError("Please Enter Integer");
            isValid = false;
        }
        char[] rS = relocationStipend.getText().toString().toCharArray();
        for (int i=0; i<rS.length; i++){
            if ((int)rS[i]<48 || (int)rS[i]>57){
                relocationStipend.setError(INVALID_INPUT);
                relocationStipend.setError("Please Enter Integer");
                isValid = false;
            }
        }
        if(relocationStipend.getText() == null || relocationStipend.getText().toString().isEmpty()) {
            relocationStipend.setError(INVALID_INPUT);
            relocationStipend.setError("Please Enter Integer");
            isValid = false;
        }

        char[] rB = retirementBenefits.getText().toString().toCharArray();
        for (int i=0; i<rB.length; i++){
            if ((int)rB[i]<48 || (int)rB[i]>57){
                retirementBenefits.setError(INVALID_INPUT);
                retirementBenefits.setError("Please Enter Integer");
                isValid = false;
            }
        }
        if(retirementBenefits.getText() == null || retirementBenefits.getText().toString().isEmpty()) {
            retirementBenefits.setError(INVALID_INPUT);
            retirementBenefits.setError("Please Enter Integer");
            isValid = false;
        }

        return isValid;
    }
}