package klagan.priceapi.application;

import klagan.priceapi.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PriceControllerTest {

    @Autowired
    private PriceService priceService;

    private MockMvc mockMvc;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new PriceController(priceService)).build();
    }

    /*
     * Request at 10:00 a.m. on the 14th for product 35455 for brand 1
     * Should return status 200
     */
    @Test
    public void testCalculatePrice_1() throws Exception {
        String expected = "{\"product\":35455,\"chain\":1,\"rate\":1,\"endDate\":[2020,12,31,23,59,59],\"starDate\":[2020,6,14,0,0],\"price\":35.5}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/prices/calculate")
                        .param("applicationDate", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    /*
     * Request at 4:00 p.m. on the 14th for product 35455 for brand 1
     * Should return status 200
     */
    @Test
    public void testCalculatePrice_2() throws Exception {
        String expected = "{\"product\":35455,\"chain\":1,\"rate\":1,\"endDate\":[2020,12,31,23,59,59],\"starDate\":[2020,6,14,0,0],\"price\":35.5}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/prices/calculate")
                        .param("applicationDate", "2020-06-14T16:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    /*
     * Request at 9:00 a.m. on the 14th for product 35455 for brand 1
     * Should return status 200
     */
    @Test
    public void testCalculatePrice_3() throws Exception {
        String expected = "{\"product\":35455,\"chain\":1,\"rate\":1,\"endDate\":[2020,12,31,23,59,59],\"starDate\":[2020,6,14,0,0],\"price\":35.5}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/prices/calculate")
                        .param("applicationDate", "2020-06-14T09:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    /*
     * Request at 10:00 a.m. on the 15th for product 35455 for brand 1
     * Should return status 200
     */
    @Test
    public void testCalculatePrice_4() throws Exception {
        String expected = "{\"product\":35455,\"chain\":1,\"rate\":1,\"endDate\":[2020,12,31,23,59,59],\"starDate\":[2020,6,14,0,0],\"price\":35.5}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/prices/calculate")
                        .param("applicationDate", "2020-06-15T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    /*
     * Request at 9:00 p.m. on the 16th for product 35455 for brand 1
     * Should return status 200
     */
    @Test
    public void testCalculatePrice_5() throws Exception {
        String expected = "{\"product\":35455,\"chain\":1,\"rate\":1,\"endDate\":[2020,12,31,23,59,59],\"starDate\":[2020,6,14,0,0],\"price\":35.5}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/prices/calculate")
                        .param("applicationDate", "2020-06-16T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    /*
     * Request at 4:00 p.m. on the 14th 2021 for product 35455 for brand 1
     * Should return status 204
     */
    @Test
    public void testCalculatePrice_NotContent() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/prices/calculate")
                        .param("applicationDate", "2021-06-14T16:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
    }
}