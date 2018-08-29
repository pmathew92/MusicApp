package com.prince.musicapp.ui

import com.prince.musicapp.BasePresenter
import com.prince.musicapp.BaseView

interface PlayerContract {
    interface PlayerView : BaseView<PlayerPresenter> {

    }

    interface PlayerPresenter : BasePresenter {

    }
}