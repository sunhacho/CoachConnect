// DateAdapter.kt
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coachconnect.R

class DateAdapter(private var dateList: List<String>) :
    RecyclerView.Adapter<DateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_date, parent, false)
        return DateViewHolder(view)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.dateTextView.text = dateList[position]
    }

    override fun getItemCount(): Int {
        return dateList.size
    }

    fun updateDateList(newDateList: List<String>) {
        dateList = newDateList
        notifyDataSetChanged()
    }
}
