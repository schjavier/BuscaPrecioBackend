package com.buscaprecio.api.comercioTest;

import com.buscaprecio.api.repositorio.ComercioRepository;
import com.buscaprecio.api.servicios.ComercioService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ComercioServiceTest {

    @Mock
    private ComercioRepository comercioRepository;

    @InjectMocks
    private ComercioService comercioService;

    

}
