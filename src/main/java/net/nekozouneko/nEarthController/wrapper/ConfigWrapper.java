package net.nekozouneko.nEarthController.wrapper;

import net.nekozouneko.nEarthController.NEarthController;

public class ConfigWrapper {
    //isEnabled
    public static boolean isEndCrystalDisablerEnabled =
            NEarthController.getInstance().getConfig().getBoolean("Patch.EndCrystalDisabler.Enabled");
    public static boolean isWitherSummonSoundRestrictionEnabled =
            NEarthController.getInstance().getConfig().getBoolean("Patch.WitherSummonSoundRestriction.Enabled");

    //Settings
    public static int getWitherSummonSoundRestrictionHearableRadius =
            NEarthController.getInstance().getConfig().getInt("Settings.WitherSummonSoundRestriction.HearableRadius");
}
