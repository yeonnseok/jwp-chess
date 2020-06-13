package chess.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ChessControllerTest {

    @Autowired
    private MockMvc mvc;

    @DisplayName("index page load")
    @Test
    void index() throws Exception {
        // when
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(content().string(containsString("체스 게임 목록")));
    }

    @DisplayName("board page load")
    @Test
    void start() throws Exception {
        // when
        mvc.perform(get("/start"))
                .andExpect(status().isOk())
                .andExpect(view().name("board"))
                .andExpect(content().string(containsString("보스독의 체스 게임")));
    }
}