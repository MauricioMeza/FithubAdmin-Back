package com.api.controladores;

import com.api.dto.SesionDTO;
import com.api.modelos.Instructor;
import com.api.modelos.Sesion;
import com.api.modelos.TipoSesion;
import com.api.modelos.Usuario;
import com.api.repositorios.SesionRepositorio;
import com.api.repositorios.UsuarioRepositorio;
import com.api.seguridad.UserPrincipalDetailsService;
import com.api.servicios.InstructorServicio;
import com.api.servicios.SesionServicio;
import com.api.servicios.UsuarioServicio;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(InstructorControlador.class)
class InstructorControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SesionRepositorio sesionRepositorio;

    @MockBean
    private InstructorServicio instructorServicio;

    @MockBean
    private UsuarioServicio usuarioServicio;

    @MockBean
    private UserPrincipalDetailsService userPrincipalDetailsService;

    @MockBean
    private SesionServicio sesionServicio;

    @MockBean
    private UsuarioRepositorio usuarioRepositorio;

    @Test
    public void registroSesion() throws Exception {

        Instructor instructor = instructorServicio.getInstructorByName("admin");

        Usuario usuario = usuarioServicio.getUserByEmail("duvan@micorreo.com");

        List<Usuario> usuarioList = new ArrayList<>();

        usuarioList.add(usuario);

        Date date = new Date(2020, 05, 17);

        Sesion mockSesion = new Sesion("id", date, new TipoSesion(), instructor, 20, usuarioList);

        String sesion = "{\"fecha\":\"2020-05-17\",\"sesion\":\"Clase de cardio\",\"instructor\":\"admin\"}";

        Mockito.when(sesionServicio.addSesion(Mockito.any(SesionDTO.class))).thenReturn(ResponseEntity.ok(mockSesion.toString())); 

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/Admin/agregarSesion").
                accept(MediaType.APPLICATION_JSON).content(sesion).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

}
