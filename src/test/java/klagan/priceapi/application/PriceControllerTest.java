package klagan.priceapi.application;

import klagan.priceapi.infrastructure.adapter.PriceServiceAdapter;
import klagan.priceapi.infrastructure.web.PriceController;
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
    private PriceServiceAdapter priceServiceAdapter;

    private MockMvc mockMvc;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new PriceController(priceServiceAdapter)).build();
    }

    /*
     * Request at 10:00 a.m. on the 14th for product 35455 for brand 1
     * Should return status 200
     */
    @Test
    public void testCalculatePrice_1() throws Exception {
        String expected = "{\"productId\":35455,\"chain\":1,\"rate\":1,\"startDate\":[2020,6,14,0,0],\"endDate\":[2020,12,31,23,59,59],\"finalPrice\":35.5}";
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
        String expected = "{\"productId\":35455,\"chain\":1,\"rate\":2,\"startDate\":[2020,06,14,15,0],\"endDate\":[2020,06,14,18,30],\"finalPrice\":25.45}";
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
        String expected = "{\"productId\":35455,\"chain\":1,\"rate\":1,\"startDate\":[2020,6,14,0,0],\"endDate\":[2020,12,31,23,59,59],\"finalPrice\":35.5}";
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
        String expected = "{\"productId\":35455,\"chain\":1,\"rate\":3,\"endDate\":[2020,6,15,11,0],\"startDate\":[2020,6,15,0,0],\"finalPrice\":30.5}";
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
        String expected = "{\"productId\":35455,\"chain\":1,\"rate\":4,\"endDate\":[2020,12,31,23,59,59],\"startDate\":[2020,6,15,16,0],\"finalPrice\":38.95}";
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