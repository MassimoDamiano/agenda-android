package com.massimodamiano.agenda

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.massimodamiano.agenda.domain.Contact

class ContactsAdapter(private val contacts: List<Contact>) : RecyclerView.Adapter<ContactsAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
    )
    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(contacts[position])
    override fun getItemCount() = contacts.size

    class Holder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.tvContactName)
        private val phone = itemView.findViewById<TextView>(R.id.tvContactPhone)
        fun bind(contact: Contact) {
            name.text = itemView.context.getString(R.string.full_name, contact.firstName, contact.lastName)
            phone.text = contact.phone
        }
    }
}
