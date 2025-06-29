package com.ubaya.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.todoapp.R
import com.ubaya.todoapp.databinding.FragmentCreateTodoBinding
import com.ubaya.todoapp.model.Todo
import com.ubaya.todoapp.viewmodel.DetailTodoViewModel

class EditTodoFragment : Fragment(), RadioClickListener  {
    private lateinit var binding: FragmentCreateTodoBinding
    private lateinit var viewModel: DetailTodoViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        binding.txtJudulTodo.text = "Edit Todo"
        binding.btnAdd.text = "Save Changes"

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()

        binding.btnAdd.setOnClickListener {
            val radio =
                view.findViewById<RadioButton>(binding.radioGroupPriority.checkedRadioButtonId)
            viewModel.update(binding.txtTitle.text.toString(),
                binding.txtNotes.text.toString(), radio.tag.toString().toInt(), uuid)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }

        binding.listener = this

    }

    override fun onCreateView(inflater: LayoutInflater, container:
    ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCreateTodoBinding.inflate(inflater,container,
            false)
        return binding.root
    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            binding.todo = it

//            binding.txtTitle.setText(it.title)
//            binding.txtNotes.setText(it.notes)

//            when (it.priority) {
//                1 -> binding.radioLow.isChecked = true
//                2 -> binding.radioMedium.isChecked = true
//                else -> binding.radioHigh.isChecked = true
//            }

        })
    }

    override fun onRadioClick(v: View) {
        val priority = v.tag.toString().toInt()
        binding.todo?.priority = priority
    }

    override fun onClick(v: View) {
        viewModel.updateTodo(binding.todo as Todo)
        Toast.makeText(context, "Todo updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }


}