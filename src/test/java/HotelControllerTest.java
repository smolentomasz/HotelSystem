import com.hotelsystem.hotelsystem.HotelSystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = HotelSystem.class)
@AutoConfigureMockMvc
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllHotelsShouldReturnOkStatus() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/hotels"))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void getHotelByNameShouldReturnOkStatus() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/hotels/Zacisze"))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void getHotelsByNameShouldReturnNotFoundStatus() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/hotels/Polonez"))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
