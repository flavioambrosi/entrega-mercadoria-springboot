package br.ambrosi.flavio;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import javax.ws.rs.core.Application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import br.ambrosi.flavio.model.Aresta;
import br.ambrosi.flavio.repository.ArestaRepository;
import br.ambrosi.flavio.repository.VerticeRepository;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class RestServicesTests {

	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private String userName = "bdussault";

    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    
    @Autowired
	ArestaRepository aRepo;

	@Autowired
	VerticeRepository vRepo;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void readSingleBookmark() throws Exception {
//        mockMvc.perform(get("/servicos/adicionaMapa/"
//                + this.bookmarkList.get(0).getId()))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(contentType))
//                .andExpect(jsonPath("$.id", is(this.bookmarkList.get(0).getId().intValue())))
//                .andExpect(jsonPath("$.uri", is("http://bookmark.com/1/" + userName)))
//                .andExpect(jsonPath("$.description", is("A description")));
    }
    

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
