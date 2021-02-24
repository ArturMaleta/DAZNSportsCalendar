package mal.art.daznsportscalendar.dashboard.model

data class SportEvent(
    val id: Int,
    val title: String,
    val subtitle: String,
    val date: String,
    val imageUrl: String,
    val videoUrl: String
)