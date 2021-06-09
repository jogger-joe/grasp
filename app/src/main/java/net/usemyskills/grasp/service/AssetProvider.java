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
        availableAssets.add(new Asset(R.drawable.ic_audio, "audio"));
        availableAssets.add(new Asset(R.drawable.ic_bedtime, "bedtime"));
        availableAssets.add(new Asset(R.drawable.ic_card, "card"));
        availableAssets.add(new Asset(R.drawable.ic_frost, "frost"));
        availableAssets.add(new Asset(R.drawable.ic_money, "money"));
        availableAssets.add(new Asset(R.drawable.ic_stopwatch, "stopwatch"));
        availableAssets.add(new Asset(R.drawable.ic_play, "play"));
        return availableAssets;
    }
}
