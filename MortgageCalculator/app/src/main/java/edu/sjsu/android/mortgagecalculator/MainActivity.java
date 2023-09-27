package edu.sjsu.android.mortgagecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity  {
    TextView txtMonthlyIntrest, txtInterest;
    EditText txtPrinciple;
    Button button;
    int months=0;
    boolean isTaxIncluded;
    SeekBar interestRate;
    CheckBox checkBox;
    RadioGroup radioGroup;
    RadioButton fifteenYear, twentyYear, thirtyYear;

    private static final DecimalFormat df = new DecimalFormat("0.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPrinciple=(EditText) this.findViewById(R.id.principleAmount);
        fifteenYear=this.findViewById(R.id.fifteenYear);
        twentyYear=this.findViewById(R.id.twentyYear);
        thirtyYear=this.findViewById(R.id.thirtyYear);
        txtMonthlyIntrest=(TextView)this.findViewById(R.id.txtMonthlyIntrest);
        txtInterest=(TextView)this.findViewById(R.id.textIntrest);
        radioGroup =this.findViewById(R.id.radioGroup);


        interestRate=this.findViewById(R.id.intrestRateSeekBar);
        interestRate.setProgress(10);

        interestRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtInterest.setText("Interest Rate:: " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        checkBox=this.findViewById(R.id.taxInsuranceCheck);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isTaxIncluded=b;
            }
        });
        button=this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Boolean fifteenYearBool = fifteenYear.isChecked();
                    Boolean twentyYearBool = twentyYear.isChecked();
                    Boolean thirtyYearBool = thirtyYear.isChecked();
                    String principleAmount = txtPrinciple.getText().toString();
                    if(fifteenYearBool){
                        months=15*12;
                    }
                    else if(twentyYearBool){
                        months=20*12;
                    }
                    else if(thirtyYearBool){
                        months=30*12;
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Select Valid loan duration", Toast.LENGTH_SHORT).show();
                    }
                    if (months == 0) {
                        Toast.makeText(MainActivity.this, "Please select the loan duration", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(principleAmount)) {
                        Toast.makeText(MainActivity.this, "Please enter the valid amount", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    double insuranceAmount = 0, monthly_intrest = 0;;
                    double principle = Float.parseFloat(principleAmount);
                    double interestRate1 = interestRate.getProgress();

                    if (interestRate1 != 0)  monthly_intrest = interestRate1 / 1200;

                    insuranceAmount=(isTaxIncluded==true) ? 0.001*principle:0;

                    double denominator = 1 - Math.pow(1 + monthly_intrest, -1*months);
                    double temp=monthly_intrest/denominator;
                    double m;
                    if(monthly_intrest!=0)
                    {
                        m = (principle * temp) + insuranceAmount;
                    }
                    else{
                        m=(principle/months)+insuranceAmount;
                    }
                txtMonthlyIntrest.setText("Monthly Payment: " + df.format(m));

            }

        });
    }

}