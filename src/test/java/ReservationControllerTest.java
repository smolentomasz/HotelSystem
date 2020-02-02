import com.hotelsystem.hotelsystem.HotelSystem;
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
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void getAllReservationsShouldReturnOkStatus() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/reservation"))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void getReservationByIdShouldReturnNotFoundStatus() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/reservation/20"))
                .andExpect(status().isNotFound())
                .andReturn();
    }
    @Test
    public void getReservationByUserShouldReturnOkStatus() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/reservation/93541875265"))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void addNewReservationShouldReturnBadRequestStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("91457325468");
        final String jwtToken = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("client_id", "");
        body.put("room_id", "");
        body.put("start_date", "");
        body.put("end_date", "");
        JSONObject object = new JSONObject(body);
        mockMvc.perform(MockMvcRequestBuilders.post("/reservation").header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void addNewReservationShouldReturnNotFoundStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("91457325468");
        final String jwtToken = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("client_id", "91457325468");
        body.put("room_id", "231");
        body.put("start_date", "2020-01-15");
        body.put("end_date", "2020-01-21");
        JSONObject object = new JSONObject(body);
        mockMvc.perform(MockMvcRequestBuilders.post("/reservation").header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isNotFound())
                .andReturn();
    }
    @Test
    public void addNewReservation2ShouldReturnNotFoundStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("91457325468");
        final String jwtToken = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("client_id", "91457775468");
        body.put("room_id", "230");
        body.put("start_date", "2020-01-15");
        body.put("end_date", "2020-01-21");
        JSONObject object = new JSONObject(body);
        mockMvc.perform(MockMvcRequestBuilders.post("/reservation").header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isNotFound())
                .andReturn();
    }
    @Test
    public void editReservationByIdShouldReturnOkStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("93541875265");
        final String jwtToken = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("client_id", "93541875265");
        body.put("room_id", "230");
        body.put("start_date", "2020-01-13");
        body.put("end_date", "2020-01-19");
        JSONObject object = new JSONObject(body);
        mockMvc.perform(MockMvcRequestBuilders.put("/reservation/3").header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void editReservationByIdShouldReturnNotFoundStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("93541875265");
        final String jwtToken = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("client_id", "93541875265");
        body.put("room_id", "230");
        body.put("start_date", "2020-01-13");
        body.put("end_date", "2020-01-19");
        JSONObject object = new JSONObject(body);
        mockMvc.perform(MockMvcRequestBuilders.put("/reservation/20").header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isNotFound())
                .andReturn();
    }
    @Test
    public void editReservationById2ShouldReturnNotFoundStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("93541875265");
        final String jwtToken = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("client_id", "93541899265");
        body.put("room_id", "230");
        body.put("start_date", "2020-01-13");
        body.put("end_date", "2020-01-19");
        JSONObject object = new JSONObject(body);
        mockMvc.perform(MockMvcRequestBuilders.put("/reservation/2").header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isNotFound())
                .andReturn();
    }
    @Test
    public void editReservationById3ShouldReturnNotFoundStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("93541875265");
        final String jwtToken = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("client_id", "88541875265");
        body.put("room_id", "230");
        body.put("start_date", "2020-01-13");
        body.put("end_date", "2020-01-19");
        JSONObject object = new JSONObject(body);
        mockMvc.perform(MockMvcRequestBuilders.put("/reservation/2").header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isNotFound())
                .andReturn();
    }
    @Test
    public void editReservationById4ShouldReturnNotFoundStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("93541875265");
        final String jwtToken = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("client_id", "93541875265");
        body.put("room_id", "231");
        body.put("start_date", "2020-01-13");
        body.put("end_date", "2020-01-19");
        JSONObject object = new JSONObject(body);
        mockMvc.perform(MockMvcRequestBuilders.put("/reservation/2").header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isNotFound())
                .andReturn();
    }
    @Test
    public void editReservationById5ShouldReturnBadRequestStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("91457325468");
        final String jwtToken = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("client_id", "");
        body.put("room_id", "");
        body.put("start_date", "");
        body.put("end_date", "");
        JSONObject object = new JSONObject(body);
        mockMvc.perform(MockMvcRequestBuilders.put("/reservation/2").header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void deleteReservationByIdShouldReturnBadRequestStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("91457325468");
        final String jwtToken = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("client_id", "");
        JSONObject object = new JSONObject(body);
        mockMvc.perform(MockMvcRequestBuilders.delete("/reservation/20").header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void deleteReservationByIdShouldReturnNotFoundStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("93541875265");
        final String jwtToken = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("client_id", "91457775468");
        JSONObject object = new JSONObject(body);
        mockMvc.perform(MockMvcRequestBuilders.delete("/reservation/20").header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isNotFound())
                .andReturn();
    }
    @Test
    public void deleteReservationById2ShouldReturnNoContentStatus() throws Exception{
        UserDetails user = userRepository.findByPesel("93541875265");
        final String jwtToken = jwtUtil.generateToken(user);
        Map<String, String> body = new HashMap<>();
        body.put("client_id", "93541875265");
        JSONObject object = new JSONObject(body);
        mockMvc.perform(MockMvcRequestBuilders.delete("/reservation/5").header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isNoContent())
                .andReturn();
    }
}
