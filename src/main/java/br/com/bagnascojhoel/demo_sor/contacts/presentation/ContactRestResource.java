package br.com.bagnascojhoel.demo_sor.contacts.presentation;

import java.net.URI;
import java.util.Objects;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.jaxrs.RestResponseBuilderImpl;

import br.com.bagnascojhoel.demo_sor.contacts.domain.Contact;
import br.com.bagnascojhoel.demo_sor.contacts.domain.ContactService;
import br.com.bagnascojhoel.demo_sor.contacts.domain.Page;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

public class ContactRestResource implements ContactRestResourceAPI {

    @Inject
    private ContactService contactService;

    @Override
    public RestResponse<ContactResponse> getPage(Integer page) {
        page = Objects.requireNonNullElse(page, 1);
        page = page < 1 ? 1 : page;
        final Page<Contact> contacsPage = contactService.getPage(page);
        return RestResponseBuilderImpl.ok(
                new ContactResponse(contacsPage.getContent()))
                .header("Current-Page", page)
                .header("Page-Items", contacsPage.getPageSize())
                .header("Total-Count", contacsPage.getTotalItens())
                .header("Total-Pages", contacsPage.getTotalPages())
                .build();
    }

    @Override
    public Response post(Contact contact) {
        int contactId = contactService.create(contact);
        return Response.created(URI.create(ContactRestResourceAPI.PATH + "/" + contactId)).build();
    }

    // JODO: How to add exception handling?
}
