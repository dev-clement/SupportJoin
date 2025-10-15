package org.franco.be.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.franco.be.entity.UserEntity;
import org.franco.be.service.UserService;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    public List<UserEntity> getAll() {
        return this.userService.listAll();
    }

    @POST
    public void addUser(UserEntity user) {
        this.userService.addUser(user);
    }

    @PUT
    @Path("/{id}")
    public void updateUser(@PathParam("id") Long id, UserEntity user) {
        this.userService.updateUser(id, user);
    }

    @DELETE
    @Path("/{id}")
    public boolean deleteUser(@PathParam("id") Long id) {
        return this.userService.deleteUser(id);
    }

    @DELETE
    public boolean deleteAll() {
        this.userService.deleteAll();
        return true;
    }
}
