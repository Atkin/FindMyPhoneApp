package ru.projectatkin.findmyphone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter(var cellClickListener: CellClickListener, var contacts: MutableList<Contacts>) : RecyclerView.Adapter<ContactsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_parent, parent, false)
        return ContactsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bind(contacts[position])
        holder.contactsLayout?.setOnClickListener {
            cellClickListener.onCellClickListener(position.toString())
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}