package my.important.job.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import my.important.job.dto.MessageCreateDto;
import my.important.job.entity.Message;

import java.util.ArrayList;
import java.util.List;

@Path("/v1/message")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageRestController {

    private final static List<Message> BD = new ArrayList<>();
//    private final Service<MessageCreateDto, MessageFindDto, Integer> messageService;

//    public MessageRestController(Service<MessageCreateDto, MessageFindDto, Integer> messageService) {
//        this.messageService = messageService;
//    }

    @POST
    public Response save(final MessageCreateDto messageDto) {
        if (messageDto.text().isBlank() || messageDto.senderId() <= 0)
            return Response.status(Response.Status.BAD_REQUEST).build();

        final var message = messageDto.toEntity();
        BD.add(message);
        return Response.status(Response.Status.CREATED).entity(message).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") final int id) {
        return BD.stream()
                .filter(message -> message.getMessageId() == id)
                .findFirst()
                .map(message -> Response.status(Response.Status.OK).entity(message).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}
