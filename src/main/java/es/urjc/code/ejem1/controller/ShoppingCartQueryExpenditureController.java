package es.urjc.code.ejem1.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.code.ejem1.domain.ShoppingCartExpenditureService;

@RestController
@RequestMapping("/api/cartexpenditure")
public class ShoppingCartQueryExpenditureController {

    ShoppingCartExpenditureService shoppingCartExpenditureService;
    private ModelMapper mapper = new ModelMapper();

    public ShoppingCartQueryExpenditureController(ShoppingCartExpenditureService shoppingCartExpenditureService) {
        this.shoppingCartExpenditureService = shoppingCartExpenditureService;
    }

    @GetMapping("/")
    public List<ShoppingCartExpenditureResponseDTO> getAllExpenditures() {
        return this.shoppingCartExpenditureService
                .getAllExpenditures()
                .stream()
                .map(expenditure -> mapper.map(expenditure, ShoppingCartExpenditureResponseDTO.class))
                .collect(Collectors.toList());
    }

    
}
