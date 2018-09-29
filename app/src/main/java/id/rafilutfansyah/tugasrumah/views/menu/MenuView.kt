package id.rafilutfansyah.tugasrumah.views.menu

import id.rafilutfansyah.tugasrumah.base.View

interface MenuView : View {
    fun onShowCurrentUser(name: String, email: String, photoUrl: String)
    fun onShowLogin()
}