package br.com.fiap.ecommerce.dtos;

import br.com.fiap.ecommerce.model.Produto;
import lombok.Getter;
import lombok.Setter;
import static br.com.fiap.ecommerce.dtos.been.Utilities.modelMapper;


@Getter
@Setter
public class ProdutoRequestCreateDto {
    private String nome;

    public Produto toModel(ProdutoRequestCreateDto dto) {
        return modelMapper.map(dto, Produto.class);
    }
}
