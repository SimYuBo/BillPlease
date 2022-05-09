package sg.edu.rp.c346.id21024095.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //Declare field variables
    TextView tvTotal;
    TextView tvSplit;
    Button btnCalc;
    Button btnReset;
    EditText etAmtInput;
    EditText etDiscInput;
    EditText etPaxInput;
    ToggleButton tbtnGst;
    ToggleButton tbtnSvs;
    RadioGroup rgPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link field variables to UI variables in layout
        tvTotal = findViewById(R.id.totalBillTextView);
        tvSplit = findViewById(R.id.splitBillTextView);
        btnCalc = findViewById(R.id.calculateButton);
        btnReset = findViewById(R.id.resetButton);
        etAmtInput = findViewById(R.id.editAmountInput);
        etDiscInput = findViewById(R.id.editDiscountInput);
        etPaxInput = findViewById(R.id.editPaxInput);
        tbtnGst = findViewById(R.id.toggleGst);
        tbtnSvs = findViewById(R.id.toggleSvs);
        rgPayment = findViewById(R.id.radioGroupPayment);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for action
                // Check for svs or gst
                double svs = 0;
                double gst = 0;
                if (tbtnSvs.isChecked()) {
                    svs = 0.1;
                }
                if (tbtnGst.isChecked()) {
                    gst = 0.07;
                }
                // Convert input amount to int
                int amount = Integer.parseInt(etAmtInput.getText().toString());
                // Covert input discount to double
                double discount = Double.parseDouble(etDiscInput.getText().toString());
                // Convert input pax to int
                int pax = Integer.parseInt(etPaxInput.getText().toString());
                // Calculation for total bill
                double totalBill = (amount + (amount * (svs + gst))) * (1 - discount/100);
                // Calculation for split bill
                double splitBill = totalBill / pax;
                // String output for split bill
                String splitBillText = String.format("Each Pays: $%.2f", splitBill);
                int checkedRadioId = rgPayment.getCheckedRadioButtonId();
                if (checkedRadioId == R.id.radioCash) {
                    splitBillText = splitBillText + " in cash";
                } else {
                    splitBillText = splitBillText + " via PayNow to 91234567";
                }
                tvTotal.setText("Total Bill: $" + totalBill);
                tvSplit.setText(splitBillText);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for action
                etAmtInput.setText("");
                etPaxInput.setText("");
                etDiscInput.setText("");
                tbtnGst.setChecked(false);
                tbtnSvs.setChecked(false);
                rgPayment.check(0);
                tvTotal.setText("Total Bill:");
                tvSplit.setText("Each Person Pays:");
            }
        });
    }
}