package com.example.livraria.controller;


import com.example.livraria.domain.Pedido;
import com.example.livraria.repository.PedidoRepository;
import com.example.livraria.service.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    PedidoService service;
    PedidoRepository repository;

}
