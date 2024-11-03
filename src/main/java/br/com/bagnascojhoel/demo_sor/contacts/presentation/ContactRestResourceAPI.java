package br.com.bagnascojhoel.demo_sor.contacts.presentation;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestQuery;
import org.jboss.resteasy.reactive.RestResponse;

import br.com.bagnascojhoel.demo_sor.contacts.domain.Contact;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(ContactRestResourceAPI.PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Contacts")
@SecurityRequirement(name = "API Key")
public interface ContactRestResourceAPI {
        public static final String PATH = "/api/v1/contacts";

        @Operation(summary = "Get page of contacts")
        @APIResponse(responseCode = "200", headers = {
                        @Header(name = "current-page", description = "Current page number", schema = @Schema(type = SchemaType.INTEGER)),
                        @Header(name = "page-items", description = "Number of itens in the current page", schema = @Schema(type = SchemaType.INTEGER)),
                        @Header(name = "total-count", description = "Total number of itens of this resource", schema = @Schema(type = SchemaType.INTEGER)),
                        @Header(name = "total-pages", description = "Total number of pages of this resource given the page size of 20", schema = @Schema(type = SchemaType.INTEGER))
        }, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContactResponse.class)))
        @GET
        RestResponse<ContactResponse> getPage(@RestQuery("page") Integer page);

        @Operation(summary = "Create contact")
        @POST
        Response post(@RequestBody(content = @Content(schema = @Schema(properties = {
                        @SchemaProperty(name = "id", readOnly = true)
        }, implementation = Contact.class))) Contact contact);
}
