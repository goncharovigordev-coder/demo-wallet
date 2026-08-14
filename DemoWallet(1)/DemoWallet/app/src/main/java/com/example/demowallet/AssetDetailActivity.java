package com.example.demowallet;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;

/**
 * Экран деталей одного демо-актива.
 * Позволяет вручную задать сумму — исключительно для наглядности UI в учебных целях.
 * Никакие реальные транзакции, кошельки или сети при этом не затрагиваются.
 */
public class AssetDetailActivity extends AppCompatActivity {

    public static final String EXTRA_SYMBOL = "extra_symbol";

    private Asset currentAsset;

    private TextView tvDetailIcon, tvDetailSymbol, tvDetailAmount, tvDetailUsdValue;
    private EditText etNewAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_detail);

        tvDetailIcon = findViewById(R.id.tvDetailIcon);
        tvDetailSymbol = findViewById(R.id.tvDetailSymbol);
        tvDetailAmount = findViewById(R.id.tvDetailAmount);
        tvDetailUsdValue = findViewById(R.id.tvDetailUsdValue);
        etNewAmount = findViewById(R.id.etNewAmount);
        Button btnSaveAmount = findViewById(R.id.btnSaveAmount);

        String symbol = getIntent().getStringExtra(EXTRA_SYMBOL);
        currentAsset = findAssetBySymbol(symbol);

        if (currentAsset == null) {
            Toast.makeText(this, "Актив не найден", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        bindAsset();

        btnSaveAmount.setOnClickListener(v -> saveNewAmount());
    }

    private Asset findAssetBySymbol(String symbol) {
        List<Asset> assets = AssetRepository.getInstance().getAssets();
        for (Asset a : assets) {
            if (a.getSymbol().equals(symbol)) return a;
        }
        return null;
    }

    private void bindAsset() {
        tvDetailIcon.setText(currentAsset.getIconLetter());
        tvDetailSymbol.setText(currentAsset.getSymbol());
        tvDetailAmount.setText(String.format(Locale.US, "%,.4f %s", currentAsset.getAmount(), currentAsset.getSymbol()));
        tvDetailUsdValue.setText(String.format(Locale.US, "≈ $%,.2f", currentAsset.getUsdValue()));
        etNewAmount.setText(String.format(Locale.US, "%.2f", currentAsset.getAmount()));
    }

    private void saveNewAmount() {
        String input = etNewAmount.getText().toString().trim();
        if (input.isEmpty()) {
            Toast.makeText(this, "Введите сумму", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            double newAmount = Double.parseDouble(input);
            if (newAmount < 0) {
                Toast.makeText(this, "Сумма не может быть отрицательной", Toast.LENGTH_SHORT).show();
                return;
            }
            currentAsset.setAmount(newAmount);
            bindAsset();
            Toast.makeText(this, "Демо-баланс обновлён (только локально)", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Некорректное число", Toast.LENGTH_SHORT).show();
        }
    }
}
