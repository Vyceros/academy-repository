package com.example.baseandroidproject.adapters

import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidproject.R
import com.example.baseandroidproject.data.FieldType
import com.example.baseandroidproject.data.KeyboardType
import com.example.baseandroidproject.data.ProfileDto
import com.example.baseandroidproject.databinding.ChooserItemBinding
import com.example.baseandroidproject.databinding.InputItemBinding
import com.example.baseandroidproject.helpers.showDatePickerDialog
import com.example.baseandroidproject.helpers.showGenderDropDown

class InputItemAdapter(private val items: List<ProfileDto>,
    private val onInputChanged : (Int?, String) -> Unit):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val INPUT_FIELD = 1
        private const val CHOOSER_FIELD = 2
        private val GENDERS = listOf("Male", "Female")
    }

    inner class InputItemViewHolder(private val binding: InputItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = items[adapterPosition]
            with(binding) {

                root.isEnabled = item.isActive == true
                etInput.hint = item.hint
                divider.isVisible = adapterPosition < items.lastIndex
                etInput.inputType = when (item.keyboard) {
                    KeyboardType.Text -> InputType.TYPE_CLASS_TEXT
                    KeyboardType.Number -> InputType.TYPE_CLASS_NUMBER
                    null -> InputType.TYPE_CLASS_TEXT
                }
                when(item.hint?.lowercase()){
                    "username" -> inputIcon.setImageResource(R.drawable.person)
                    "email" -> inputIcon.setImageResource(R.drawable.lock)
                    "password" -> inputIcon.setImageResource(R.drawable.person)
                    "phone" -> inputIcon.setImageResource(R.drawable.call_24px)
                    "fullname" -> inputIcon.setImageResource(R.drawable.person)
                    "jemali" -> inputIcon.setImageResource(R.drawable.person)
                }

                etInput.doAfterTextChanged{
                    onInputChanged.invoke(item.fieldId, it.toString())
                }

            }
        }
    }

    inner class ChooserItemViewHolder(private val binding: ChooserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = items[adapterPosition]

            with(binding) {
                root.isEnabled = item.isActive == true
                dropdownList.hint = item.hint
                divider.isVisible = adapterPosition < items.lastIndex


                when (item.hint?.lowercase()) {
                    "birthday" -> setupDatePicker(binding).also { chooserIcon.setImageResource(R.drawable.cake) }
                    "gender" -> setupGenderDropdown(binding).also { chooserIcon.setImageResource(R.drawable.person) }
                }
            }
        }

        private fun setupDatePicker(binding: ChooserItemBinding) {


            binding.dropdownList.setOnClickListener {
                showDatePickerDialog(binding.root.context) { selectedDate ->
                    binding.dropdownList.text = selectedDate
                    onInputChanged.invoke(items[adapterPosition].fieldId, selectedDate)
                }
            }
        }

        private fun setupGenderDropdown(binding: ChooserItemBinding) {
            binding.dropdownList.setOnClickListener {
                showGenderDropDown(it, GENDERS) { selectedGender ->
                    binding.dropdownList.text = selectedGender
                    onInputChanged.invoke(items[adapterPosition].fieldId, selectedGender)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == INPUT_FIELD) {
            InputItemViewHolder(
                InputItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else {
            ChooserItemViewHolder(
                ChooserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is InputItemViewHolder -> holder.bind()
            is ChooserItemViewHolder -> holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].fieldType) {
            FieldType.Input -> INPUT_FIELD
            FieldType.Chooser -> CHOOSER_FIELD
            else -> INPUT_FIELD
        }
    }
}