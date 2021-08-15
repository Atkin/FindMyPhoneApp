package ru.projectatkin.findmyphone

interface ContactsDatasourse {
    fun getContacts(): MutableList<Contacts>
}