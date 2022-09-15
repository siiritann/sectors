package sectors.sectors.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sectors.sectors.SectorsApplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SectorsApplication.class)
class SectorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void get_success() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void create_success() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/")
                .param("name", "siiri")
                .param("sectors", "19", "293", "238")
                .param("agreedToTerms", "on");

        mvc.perform(request)
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void create_nameMissing_error() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/")
                .param("sectors", "19", "293", "238")
                .param("agreedToTerms", "on");

        var resultAction = mvc.perform(request)
                .andReturn().getFlashMap();

        assertThat(resultAction.get("errorMessage")).isEqualTo("Name is required");
    }

    @Test
    void create_sectorsMissing_error() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/")
                .param("name", "siiri")
                .param("agreedToTerms", "on");

        var resultAction = mvc.perform(request)
                .andReturn().getFlashMap();

        assertThat(resultAction.get("errorMessage")).isEqualTo("At least one sector is required");
    }

    @Test
    void create_agreedToTermsMissing_error() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/")
                .param("name", "siiri")
                .param("sectors", "19", "293", "238");

        var resultAction = mvc.perform(request)
                .andReturn().getFlashMap();

        assertThat(resultAction.get("errorMessage")).isEqualTo("You must agree to terms");
    }

}