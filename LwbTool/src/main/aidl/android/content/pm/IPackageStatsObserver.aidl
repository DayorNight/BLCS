package android.content.pm;
import android.content.pm.PackageStats;
interface IPackageStatsObserver {
    oneway void onGetStatsCompleted(in PackageStats pStats, boolean succeeded);
}
