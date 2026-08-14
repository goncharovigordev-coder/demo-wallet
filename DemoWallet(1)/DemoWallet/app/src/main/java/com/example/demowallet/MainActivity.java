package com.example.demowallet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

/**
 * Главный экран учебного демо-кошелька.
 * Все данные — фейковые, хранятся в памяти (см. AssetRepository) и предназначены
 * только для демонстрации UI/UX. Приложение не подключается к реальным блокчейнам
 * или платёжным системам.
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvTotalBalance;
    private AssetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTotalBalance = findViewById(R.id.tvTotalBalance);
        RecyclerView rvAssets = findViewById(R.id.rvAssets);
        Button btnAddAsset = findViewById(R.id.btnAddAsset);

        rvAssets.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AssetAdapter(AssetRepository.getInstance().getAssets(), this::openAssetDetail);
        rvAssets.setAdapter(adapter);

        btnAddAsset.setOnClickListener(v ->
                Toast.makeText(this, "Демо: добавление актива не реализовано в учебном примере", Toast.LENGTH_SHORT).show());

        updateTotalBalance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Обновляем список и баланс, если пользователь вернулся с экрана деталей
        adapter.notifyDataSetChanged();
        updateTotalBalance();
    }

    private void openAssetDetail(Asset asset) {
        Intent intent = new Intent(this, AssetDetailActivity.class);
        intent.putExtra(AssetDetailActivity.EXTRA_SYMBOL, asset.getSymbol());
        startActivity(intent);
    }

    private void updateTotalBalance() {
        double total = AssetRepository.getInstance().getTotalUsdValue();
        tvTotalBalance.setText(String.format(Locale.US, "$%,.2f", total));
    }
}
