package ru.projectatkin.findmyphone

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load

class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var avatar: ImageView? = null
    var name: TextView? = null
    var phone: TextView? = null
    var contactsLayout: ConstraintLayout? = null

    init {
        avatar = itemView.findViewById(R.id.contactAvatar)
        name = itemView.findViewById(R.id.contactName)
        phone = itemView.findViewById(R.id.contactPhoneNumber)
        contactsLayout = itemView.findViewById(R.id.root_contact)
    }

    fun bind(contacts: Contacts) {
        avatar?.load(contacts.avatarUrl)

        name?.text = contacts.name
        phone?.text = contacts.telephone
    }
}