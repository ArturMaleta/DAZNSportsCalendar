package mal.art.daznsportscalendar.util.item

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import mal.art.daznsportscalendar.R
import mal.art.daznsportscalendar.dashboard.model.SportEvent
import mal.art.daznsportscalendar.util.koin.timeHelper
import mal.art.daznsportscalendar.util.time.TimeHelper
import org.joda.time.DateTime
import org.koin.core.KoinComponent
import org.koin.core.inject

class EventListItem(val event: SportEvent) : AbstractItem<EventListItem.ViewHolder>() {
    override val layoutRes = R.layout.list_recycler_view_item
    override val type = R.layout.list_recycler_view_item

    override fun getViewHolder(v: View) = ViewHolder(v)

    class ViewHolder(v: View) : FastAdapter.ViewHolder<EventListItem>(v), KoinComponent {
        private val timeHelper by inject<TimeHelper>()
        var eventThumbnail: ImageView = v.findViewById(R.id.image)
        var eventTitleTv: TextView = v.findViewById(R.id.event_title_tv)
        var eventTypeTv: TextView = v.findViewById(R.id.event_type_tv)
        var eventDateTv: TextView = v.findViewById(R.id.event_date_tv)

        override fun bindView(item: EventListItem, payloads: List<Any>) {
            Glide.with(itemView.context).load(item.event.imageUrl).into(eventThumbnail)
            eventTitleTv.text = item.event.title
            eventTypeTv.text = item.event.subtitle
            eventDateTv.text = convertDate(item.event.date)
        }

        override fun unbindView(item: EventListItem) {
            Glide.with(itemView.context).clear(eventThumbnail)
            eventTitleTv.text = null
            eventTypeTv.text = null
            eventDateTv.text = null
        }

        private fun convertDate(dateFromDb: String) =
            timeHelper.convertDate(DateTime(dateFromDb).millis)
    }

}