package br.com.fiap.ecommerce.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.ecommerce.dtos.ProdutoRequestCreateDto;
import br.com.fiap.ecommerce.dtos.ProdutoRequestUpdateDto;
import br.com.fiap.ecommerce.dtos.ProdutoResponseDto;
import br.com.fiap.ecommerce.model.Produto;
import br.com.fiap.ecommerce.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDto>> list() {
        List<ProdutoResponseDto> dtos = produtoService.list().parallelStream()
                .map(produto -> new ProdutoResponseDto().toDto(produto)).toList();

        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDto> create(@RequestBody ProdutoRequestCreateDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ProdutoResponseDto().toDto(
                    produtoService.save(
                            dto.toModel(dto)
                    )
                )
        );
    }

    // localhost:8080/produtos/5
    /* body/payload: {
            "id": 5,
            "nome": "Um nome novo"
        }
     */
    @PutMapping("{id}")
    public ResponseEntity<ProdutoResponseDto> update(@PathVariable Long id, @RequestBody ProdutoRequestUpdateDto dto) {

        if (!produtoService.existsById(id)) {
            throw new RuntimeException("Id inexistente");
        }
        return ResponseEntity.ok().body(
                new ProdutoResponseDto().toDto(
                        produtoService.save(
                                dto.toModel(id)
                        )
                )
        );

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if (!produtoService.existsById(id)) {
            throw new RuntimeException("Id inexistente");
        }

        produtoService.delete(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(
                produtoService.findById(id)
                .map(produto -> new ProdutoResponseDto().toDto(produto))
                .orElseThrow(() -> new RuntimeException("Id inexistente"))
        );
    }


}
