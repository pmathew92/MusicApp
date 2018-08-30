package com.prince.musicapp.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.R.attr.name



@Entity(tableName = "suggestions")
data class AutocompleteSuggestions(@PrimaryKey val suggestion: String){
    override fun toString(): String {
        return suggestion
    }
}