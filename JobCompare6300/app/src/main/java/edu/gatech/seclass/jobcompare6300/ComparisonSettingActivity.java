package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

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
        ComparisonSetting comparisonSetting = this.createSettingsFromForm();
        comparisonSettingDetail.update(comparisonSetting);
        // Todo: update job score for each job
        this.finish();
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
}