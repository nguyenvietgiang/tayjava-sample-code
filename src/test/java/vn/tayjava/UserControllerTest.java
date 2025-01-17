package vn.tayjava;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import vn.tayjava.controller.UserController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddUser_Success() throws Exception {
        // Request payload
        String userJson = """
            {
                "firstName": "John",
                "lastName": "Doe",
                "email": "john.doe@example.com",
                "phoneNumber": "1234567890"
            }
        """;

        mockMvc.perform(post("/user/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("User added successfully,"))
                .andExpect(jsonPath("$.data").value(1));
    }

    @Test
    public void testUpdateUser_Success() throws Exception {
        String userJson = """
            {
                "firstName": "Jane",
                "lastName": "Smith",
                "email": "jane.smith@example.com",
                "phoneNumber": "0987654321"
            }
        """;

        mockMvc.perform(put("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message").value("User updated successfully"));
    }

    @Test
    public void testDeleteUser_Success() throws Exception {
        mockMvc.perform(delete("/user/1"))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.message").value("User deleted successfully"));
    }

    @Test
    public void testGetUser_Success() throws Exception {
        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("user"))
                .andExpect(jsonPath("$.data.firstName").value("Tay"))
                .andExpect(jsonPath("$.data.lastName").value("Java"));
    }

    @Test
    public void testGetAllUsers_Success() throws Exception {
        mockMvc.perform(get("/user/list")
                        .param("pageNo", "0")
                        .param("pageSize", "20"))
                .andExpect(status().isUnauthorized());
    }
}