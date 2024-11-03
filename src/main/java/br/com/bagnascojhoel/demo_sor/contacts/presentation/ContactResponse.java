package br.com.bagnascojhoel.demo_sor.contacts.presentation;

import java.util.List;

import br.com.bagnascojhoel.demo_sor.contacts.domain.Contact;

public class ContactResponse {
    private final List<Contact> contacts;

    public ContactResponse(
            final List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}
