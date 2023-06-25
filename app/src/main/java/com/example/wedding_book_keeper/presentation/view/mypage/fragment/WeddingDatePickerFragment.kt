import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.wedding_book_keeper.R

class WeddingDatePickerFragment : Fragment() {
    private lateinit var backButton: Button
    private lateinit var submitButton: Button
    private lateinit var datePicker: DatePicker
    private lateinit var textInput: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflater = LayoutInflater.from(requireContext())
        val view = inflater.inflate(R.layout.fragment_date_picker, null)

        backButton = view.findViewById(R.id.backButton)
        submitButton = view.findViewById(R.id.submitButton)
        datePicker = view.findViewById(R.id.datePicker)
        textInput = view.findViewById(R.id.textInput)

        backButton.setOnClickListener {
//            dismiss()
        }

        submitButton.setOnClickListener {
            // Handle submit button click
        }

//        val dialog = Dialog(requireContext())
//        dialog.setContentView(view)
        return view
    }
}
