package com.example.todoapi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.controllers.ToDoController;
import com.example.models.ToDoModel;
import com.example.services.ToDoServiceDB;

@SpringBootTest
@AutoConfigureMockMvc
class ToDoApiApplicationTests {

    private MockMvc mockMvc;
    private UUID toUpdate = UUID.randomUUID();
    private UUID notExist = UUID.randomUUID();
    private UUID toUpdateFalse = UUID.randomUUID();
    private UUID notExistFalse = UUID.randomUUID();

    @BeforeEach
    public void setup() {
        List<ToDoModel> todos = new ArrayList<>();
        todos.add(new ToDoModel("Do the laundry", LocalDate.of(2023, 1, 11), "High", LocalDate.of(2023, 1, 15), null,
                UUID.randomUUID(), true));
        todos.add(new ToDoModel("Clean de the dishes", LocalDate.of(2023, 2, 1), "Low", LocalDate.of(2023, 4, 16), null,
                UUID.randomUUID(), true));
        todos.add(new ToDoModel("Use sunblock", LocalDate.of(2023, 6, 1), "Medium", LocalDate.of(2023, 2, 1)));
        todos.add(new ToDoModel("Finish the proyect", LocalDate.of(2023, 4, 12), "Medium", LocalDate.of(2023, 9, 1),
                null, this.toUpdate, false));
        todos.add(new ToDoModel("Do the meal", LocalDate.of(2023, 1, 1), "Low", LocalDate.of(2023, 12, 1)));
        todos.add(new ToDoModel("Wash the clothes", LocalDate.of(2023, 5, 14), "High", LocalDate.of(2023, 11, 11), null,
                toUpdateFalse, true));
        todos.add(new ToDoModel("Fix the TV", LocalDate.of(2023, 9, 21), "High", LocalDate.of(2023, 9, 30)));
        todos.add(new ToDoModel("Create the new DB", LocalDate.of(2023, 3, 13), "Low", LocalDate.of(2023, 4, 13), null,
                UUID.randomUUID(), true));
        ToDoServiceDB db = new ToDoServiceDB(todos);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new ToDoController(db)).build();
    }

    @Test
    public void testGetfilter() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/todos")
                        .param("page", "1")
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testPrioritySortUp() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/todos")
                        .param("page", "1")
                        .param("prioritySort", "true")
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testPrioritySortDown() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/todos")
                        .param("page", "1")
                        .param("prioritySort", "false")
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testDateSortUp() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/todos")
                        .param("page", "1")
                        .param("dueDateSort", "true")
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testDateSortDown() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/todos")
                        .param("page", "1")
                        .param("dueDateSort", "false")
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testIsDoneTrue() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/todos")
                        .param("page", "1")
                        .param("isDone", "Done")
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testIsDoneFalse() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/todos")
                        .param("page", "1")
                        .param("isDone", "Undone")
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testIsDoneError() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/todos")
                        .param("page", "1")
                        .param("isDone", "X")
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void filterByName() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/todos")
                        .param("page", "1")
                        .param("name", "Do")
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void filterByPriorityHigh() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/todos")
                        .param("page", "1")
                        .param("priority", "High")
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void filterByPriorityMedium() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/todos")
                        .param("page", "1")
                        .param("priority", "Medium")
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void filterByPriorityLow() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/todos")
                        .param("page", "1")
                        .param("priority", "Low")
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testAddTaskOk() throws Exception {
        String jsonBody = "{\"text\": \"Crear Z\",\"dueDate\": \"2023-05-15T20:00:00.000Z\",\"priority\": \"Medium\",\"creationDate\": \"2023-05-19T19:42:04.868Z\"}";

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testAddTaskBadReq() throws Exception {
        String jsonBody = "{\"dueDate\": \"2023-05-15T20:00:00.000Z\",\"priority\": \"Medium\",\"creationDate\": \"2023-05-19T19:42:04.868Z\"}";

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testAddTaskBadReq2() throws Exception {
        String jsonBody = "{text\": \"Crear Z\",\"dueDate\": \"abc\",\"priority\": \"Medium\",\"creationDate\": \"2023-05-19T19:42:04.868Z\"}";

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUpdateTrueTodo() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/todos/{id}/done", this.toUpdate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUpdateTrueTodoError() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/todos/{id}/done", this.notExist)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUpdateFalseTodo() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/todos/{id}/undone", this.toUpdateFalse)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUpdateFalseTodoError() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/todos/{id}/undone", this.notExistFalse)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUpdateDataCorrect() throws Exception {
        String jsonBody = "{" +
                "\"text\": \"Finish this thing!\"," +
                "\"dueDate\": \"" + LocalDate.of(2023, 4, 13) + "\"," +
                "\"priority\": \"Low\"," +
                "\"creationDate\": \"" + LocalDate.of(2023, 9, 1) + "\"," +
                "\"uuid\": \"" + this.toUpdate + "\"," +
                "\"isDone\": \"" + false + "\"" +
                "}";
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/todos/{id}", this.toUpdate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isOk());
                // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUpdateDataErrorData() throws Exception {
        String jsonBody = "{" +
                "\"dueDate\": \"" + LocalDate.of(2023, 4, 13) + "\"," +
                "\"priority\": \"Low\"," +
                "\"creationDate\": \"" + LocalDate.of(2023, 9, 1) + "\"," +
                "\"uuid\": \"" + this.toUpdate + "\"," +
                "\"isDone\": \"" + false + "\"" +
                "}";
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/todos/{id}", this.toUpdate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
                // .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUpdateDataErrorId() throws Exception {
        String jsonBody = "{" +
                "\"text\": \"Finish this thing!\"," +
                "\"dueDate\": \"" + LocalDate.of(2023, 4, 13) + "\"," +
                "\"priority\": \"Low\"," +
                "\"creationDate\": \"" + LocalDate.of(2023, 9, 1) + "\"," +
                "\"uuid\": \"" + this.toUpdate + "\"," +
                "\"isDone\": \"" + false + "\"" +
                "}";
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/todos/{id}", this.notExist)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
                // .andDo(MockMvcResultHandlers.print());
    }

}
