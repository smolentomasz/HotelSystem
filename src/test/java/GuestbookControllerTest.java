import com.hotelsystem.hotelsystem.HotelSystem;
import com.hotelsystem.hotelsystem.web.data_models.User;
import com.hotelsystem.hotelsystem.web.repositories.UserRepository;
import com.hotelsystem.hotelsystem.web.security.JwtUtil;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = HotelSystem.class)
@AutoConfigureMockMvc
public class GuestbookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void getAllOpinionsShouldReturnOkStatus() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/guestbook"))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void addNewOpinionShouldReturnBadRequestStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("91457325468");
        final String jwtToken = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("nickname","");
        body.put("opinion", "");
        JSONObject object = new JSONObject(body);
        mockMvc.perform(MockMvcRequestBuilders.post("/guestbook").header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void addNewOpinion2ShouldReturnBadRequestStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("91457325468");
        final String jwtToken = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("nickname","");
        body.put("opinion", "Dobry hotel!");
        JSONObject object = new JSONObject(body);
        mockMvc.perform(MockMvcRequestBuilders.post("/guestbook").header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void addNewOpinion3ShouldReturnBadRequestStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("91457325468");
        final String jwtToken = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("nickname","Klient");
        body.put("opinion", "");
        JSONObject object = new JSONObject(body);
        mockMvc.perform(MockMvcRequestBuilders.post("/guestbook").header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void addNewOpinionShouldReturnOkStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("91457325468");
        final String jwtToken = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("nickname","Klient");
        body.put("opinion", "Dobry hotel!");
        JSONObject object = new JSONObject(body);
        mockMvc.perform(MockMvcRequestBuilders.post("/guestbook").header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void editOpinionShouldReturnNotFoundStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("91457325468");
        final String jwtToken = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("nickname","Klient");
        body.put("opinion", "Dobry hotel!");
        JSONObject object = new JSONObject(body);
        mockMvc.perform(MockMvcRequestBuilders.put("/guestbook/20").header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isNotFound())
                .andReturn();
    }
    @Test
    public void editOpinionShouldReturnBadRequestStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("91457325468");
        final String jwtToken = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("nickname","");
        body.put("opinion", "");
        JSONObject object = new JSONObject(body);
        mockMvc.perform(MockMvcRequestBuilders.put("/guestbook/13").header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void editOpinionShouldReturnOkStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("91457325468");
        final String jwtToken = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("nickname","Klient");
        body.put("opinion", "Dobry hotel!");
        JSONObject object = new JSONObject(body);
        mockMvc.perform(MockMvcRequestBuilders.put("/guestbook/13")
                .header("Authorization", jwtToken).contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void deleteOpinionShouldReturnNotFoundStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("91457325468");
        final String jwtToken = jwtUtil.generateToken(user);
        mockMvc.perform(MockMvcRequestBuilders.delete("/guestbook/20").header("Authorization", jwtToken))
                .andExpect(status().isNotFound())
                .andReturn();
    }
    @Test
    public void deleteOpinionShouldReturnNoContentStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("91457325468");
        final String jwtToken = jwtUtil.generateToken(user);
        mockMvc.perform(MockMvcRequestBuilders.delete("/guestbook/13").header("Authorization", jwtToken))
                .andExpect(status().isNoContent())
                .andReturn();
    }
}
