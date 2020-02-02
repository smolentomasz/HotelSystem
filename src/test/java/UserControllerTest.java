import com.hotelsystem.hotelsystem.HotelSystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = HotelSystem.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loginTestShouldReturnBadRequestStatus() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/login").header("Login", "").header("Password", ""))
               .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void loginTest2ShouldReturnBadRequestStatus() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/login").header("Login", "91457325468").header("Password", ""))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void loginTest3ShouldReturnBadRequestStatus() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/login").header("Login", "").header("Password", "domino123"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void loginTestShouldReturnForbiddenStatus() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/login").header("Login", "12345678901").header("Password", "domino123"))
                .andExpect(status().isForbidden())
                .andReturn();
    }
    @Test
    public void loginTestShouldReturnOkStatus() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/login").header("Login", "91457325468").header("Password", "domino123"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
