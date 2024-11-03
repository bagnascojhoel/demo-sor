package br.com.bagnascojhoel.demo_sor.contacts.domain;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ContactService {
    private static final int PAGE_SIZE = 20;

    /**
     * @implNote the page size is 20.
     * @param page an integer greater than or equal to 1.
     * @return list representing the page.
     */
    public Page<Contact> getPage(final int page) {
        if (page < 1) {
            throw new IllegalArgumentException("page count starts at 1");
        }

        final int initialIndex = (page - 1) * PAGE_SIZE;
        final int lastIndex = initialIndex + PAGE_SIZE;
        final List<Contact> allContacts = Contact.listAll();

        if (initialIndex >= allContacts.size()) {
            return Page.empty(page, allContacts.size(), PAGE_SIZE);
        }

        if (lastIndex > allContacts.size()) {
            return new Page<Contact>(
                    allContacts.subList(initialIndex, allContacts.size()),
                    allContacts.size(),
                    PAGE_SIZE,
                    page);
        }

        return new Page<Contact>(
                allContacts.subList(initialIndex, lastIndex),
                allContacts.size(),
                PAGE_SIZE,
                page);
    }

    @Transactional
    public int create(Contact contact) {
        contact.persist();

        return contact.getId();
    }

}
