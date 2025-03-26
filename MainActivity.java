package com.example.smartrate;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private EditText editTextValue;
    private TextView textViewResult;
    private RadioGroup radioGroup;
    private RadioButton radioButtonDinarToEuro, radioButtonEuroToDinar;
    private static final float EXCHANGE_RATE = 147.0f; // معدل التحويل

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ضبط الـ Toolbar ليتم استخدامه كـ ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextValue = findViewById(R.id.editTextValue);
        textViewResult = findViewById(R.id.textViewResult);
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonDinarToEuro = findViewById(R.id.radioButtonDinarToEuro);
        radioButtonEuroToDinar = findViewById(R.id.radioButtonEuroToDinar);
    }

    public void convertir(android.view.View v) {
        String inputValue = editTextValue.getText().toString();

        if (inputValue.isEmpty()) {
            Toast.makeText(this, "الرجاء إدخال قيمة", Toast.LENGTH_SHORT).show();
            return;
        }

        float value = Float.parseFloat(inputValue);
        float result;
        String currency;

        if (radioButtonDinarToEuro.isChecked()) {
            result = dinarToEuro(value);
            currency = "EUR";
        } else if (radioButtonEuroToDinar.isChecked()) {
            result = euroToDinar(value);
            currency = "DZD";
        } else {
            Toast.makeText(this, "الرجاء اختيار نوع التحويل", Toast.LENGTH_SHORT).show();
            return;
        }

        textViewResult.setText(String.format("Résultat: %.2f %s", result, currency));
    }

    private float dinarToEuro(float dinarValue) {
        return dinarValue / EXCHANGE_RATE;
    }

    private float euroToDinar(float euroValue) {
        return euroValue * EXCHANGE_RATE;
    }

    // *************** قائمة الخيارات (Options Menu) مضافة مباشرة داخل Java ***************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Changer le taux"); // خيار تغيير معدل التحويل
        menu.add(0, 2, 1, "Quitter");         // خيار الخروج
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1: // "Changer le taux"
                Toast.makeText(this, "تغيير معدل التحويل (قيد التطوير)", Toast.LENGTH_SHORT).show();
                return true;
            case 2: // "Quitter"
                finish(); // إغلاق التطبيق
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
