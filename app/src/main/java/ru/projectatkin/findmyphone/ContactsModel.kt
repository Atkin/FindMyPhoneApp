package ru.projectatkin.findmyphone

class ContactsModel(
    private val contactsDataSource: ContactsDatasourse
) {

    fun getContacts() = contactsDataSource.getContacts()
}