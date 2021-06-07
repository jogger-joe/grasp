package net.usemyskills.grasp.service;

import net.usemyskills.grasp.R;
import net.usemyskills.grasp.model.Asset;

import java.util.ArrayList;
import java.util.List;

public class AssetProvider {
    public static List<Asset> getAvailableIcons() {
        List<Asset> availableAssets =  new ArrayList<>();
        availableAssets.add(new Asset(R.drawable.ic_default, "default"));
        availableAssets.add(new Asset(R.drawable.ic_run, "run"));
        availableAssets.add(new Asset(R.drawable.ic_person, "person"));
        availableAssets.add(new Asset(R.drawable.ic_heart, "heart"));
        return availableAssets;
    }
}
