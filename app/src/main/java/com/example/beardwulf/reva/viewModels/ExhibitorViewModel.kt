package com.example.beardwulf.reva.viewModels

import android.arch.lifecycle.ViewModel
import com.example.beardwulf.reva.domain.Exhibitor

/**
 * TODO
 */
class ExhibitorViewModel : ViewModel {
    lateinit var exhibitor: Exhibitor
    var isNew: Boolean = true
    constructor()
}