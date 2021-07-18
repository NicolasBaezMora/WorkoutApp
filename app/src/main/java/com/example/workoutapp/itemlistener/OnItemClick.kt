package com.example.workoutapp.itemlistener

import com.example.workoutapp.rest.responses.ParentElementResponse

interface OnItemClick {
    fun onItemClickListener(item: ParentElementResponse)
}