package com.example.beardwulf.reva.domain

import android.arch.lifecycle.ViewModel

class ExhibitorViewModel : ViewModel() {

    public lateinit var currentExhibitor: Exhibitor
    var isNew: Boolean = true
}