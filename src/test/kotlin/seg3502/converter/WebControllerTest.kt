package seg3502.converter

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
class WebControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun request_to_home() {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.view().name("home"))
    }

    @Test
    fun addition() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/convert")
                .param("input1", "5")
                .param("input2", "3")
                .param("operation", "+")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.model().attribute("result", 8.0))
            .andExpect(MockMvcResultMatchers.view().name("home"))
    }

    @Test
    fun subtraction() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/convert")
                .param("input1", "5")
                .param("input2", "3")
                .param("operation", "-")
        )
            .andExpect(MockMvcResultMatchers.model().attribute("result", 2.0))
    }

    @Test
    fun multiplication() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/convert")
                .param("input1", "5")
                .param("input2", "3")
                .param("operation", "*")
        )
            .andExpect(MockMvcResultMatchers.model().attribute("result", 15.0))
    }

    @Test
    fun division() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/convert")
                .param("input1", "5")
                .param("input2", "2")
                .param("operation", "/")
        )
            .andExpect(MockMvcResultMatchers.model().attribute("result", 2.5))
    }

    @Test
    fun division_by_zero() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/convert")
                .param("input1", "5")
                .param("input2", "0")
                .param("operation", "/")
        )
            .andExpect(
                MockMvcResultMatchers.model()
                    .attribute("error", "DivisionByZeroError")
            )
    }
}