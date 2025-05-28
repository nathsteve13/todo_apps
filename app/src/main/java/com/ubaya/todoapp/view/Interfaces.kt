package com.ubaya.todoapp.view

import android.view.View
import android.widget.CompoundButton
import com.ubaya.todoapp.model.Todo

interface TodoListChangeListener {
    fun onCheckedChanged(cb: CompoundButton,
                         isChecked:Boolean,
                         obj: Todo
    )

    fun onTodoEditClick(v: View)
}

interface RadioClickListener {
    fun onRadioClick(v:View)
    fun onClick(v:View)
}
