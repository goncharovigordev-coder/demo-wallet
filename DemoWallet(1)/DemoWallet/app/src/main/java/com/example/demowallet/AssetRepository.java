package com.example.demowallet;

import java.util.ArrayList;
import java.util.List;

/**
 * Простое in-memory хранилище демо-активов.
 * Данные живут только пока приложение открыто и сбрасываются при перезапуске.
 * Это намеренно — приложение учебное и не хранит "реальные" данные о деньгах.
 */
public class AssetRepository {

    private static AssetRepository instance;
    private final List<Asset> assets = new ArrayList<>();

    private AssetRepository() {
        // Стартовый набор демо-активов
        assets.add(new Asset("USDT", "Tether", "₮", 1250.00, 1.0));
        assets.add(new Asset("BTC", "Bitcoin", "₿", 0.015, 64000.0));
        assets.add(new Asset("ETH", "Ethereum", "Ξ", 0.42, 3400.0));
        assets.add(new Asset("USDC", "USD Coin", "$", 300.00, 1.0));
    }

    public static AssetRepository getInstance() {
        if (instance == null) {
            instance = new AssetRepository();
        }
        return instance;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public double getTotalUsdValue() {
        double total = 0;
        for (Asset a : assets) {
            total += a.getUsdValue();
        }
        return total;
    }
}
