package com.massimodamiano.agenda

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.massimodamiano.agenda.domain.Contact

class ContactsAdapter(
    private var contacts: List<Contact>,
    private val onClick: (Contact) -> Unit
) : RecyclerView.Adapter<ContactsAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
    )
    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(contacts[position], onClick)
    override fun getItemCount() = contacts.size

    class Holder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.tvContactName)
        private val phone = itemView.findViewById<TextView>(R.id.tvContactPhone)
        fun bind(contact: Contact, onClick: (Contact) -> Unit) {
            name.text = itemView.context.getString(R.string.full_name, contact.firstName, contact.lastName)
            phone.text = contact.phone
            itemView.setOnClickListener { onClick(contact) }
        }
    }

    fun submitList(newContacts: List<Contact>) {
        contacts = newContacts
        notifyDataSetChanged()
    }
}
