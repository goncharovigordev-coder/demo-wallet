package com.example.demowallet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class AssetAdapter extends RecyclerView.Adapter<AssetAdapter.AssetViewHolder> {

    public interface OnAssetClickListener {
        void onAssetClick(Asset asset);
    }

    private final List<Asset> assets;
    private final OnAssetClickListener listener;

    public AssetAdapter(List<Asset> assets, OnAssetClickListener listener) {
        this.assets = assets;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AssetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_asset, parent, false);
        return new AssetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssetViewHolder holder, int position) {
        Asset asset = assets.get(position);
        holder.tvIconLetter.setText(asset.getIconLetter());
        holder.tvSymbol.setText(asset.getSymbol());
        holder.tvName.setText(asset.getName());
        holder.tvAmount.setText(String.format(Locale.US, "%,.4f", asset.getAmount()));
        holder.tvUsdValue.setText(String.format(Locale.US, "≈ $%,.2f", asset.getUsdValue()));

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onAssetClick(asset);
        });
    }

    @Override
    public int getItemCount() {
        return assets.size();
    }

    static class AssetViewHolder extends RecyclerView.ViewHolder {
        TextView tvIconLetter, tvSymbol, tvName, tvAmount, tvUsdValue;

        AssetViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIconLetter = itemView.findViewById(R.id.tvIconLetter);
            tvSymbol = itemView.findViewById(R.id.tvSymbol);
            tvName = itemView.findViewById(R.id.tvName);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvUsdValue = itemView.findViewById(R.id.tvUsdValue);
        }
    }
}
