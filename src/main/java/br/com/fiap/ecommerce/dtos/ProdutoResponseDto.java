package br.com.fiap.ecommerce.dtos;

import br.com.fiap.ecommerce.model.Produto;
import lombok.Getter;
import lombok.Setter;
import static br.com.fiap.ecommerce.dtos.been.Utilities.modelMapper;


@Getter
@Setter
public class ProdutoResponseDto {
    private Long id;
	private String nome;

    public ProdutoResponseDto toDto(Produto produto) {
        return modelMapper.map(produto, ProdutoResponseDto.class);
    }
}
