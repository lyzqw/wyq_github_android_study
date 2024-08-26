import android.app.Notification
import com.google.android.exoplayer2.offline.Download
import com.google.android.exoplayer2.offline.DownloadManager
import com.google.android.exoplayer2.offline.DownloadService
import com.google.android.exoplayer2.scheduler.Scheduler


const val FOREGROUND_NOTIFICATION_ID = 123
const val CHANNEL_ID = "123123"

class SilentDownloadService : DownloadService(
    FOREGROUND_NOTIFICATION_ID,
    DEFAULT_FOREGROUND_NOTIFICATION_UPDATE_INTERVAL,
    CHANNEL_ID,
    0,
    0
) {
    override fun getDownloadManager(): DownloadManager {
        TODO("Not yet implemented")
    }

    override fun getScheduler(): Scheduler? {
        TODO("Not yet implemented")
    }

    override fun getForegroundNotification(p0: MutableList<Download>, p1: Int): Notification {
        TODO("Not yet implemented")
    }


}
