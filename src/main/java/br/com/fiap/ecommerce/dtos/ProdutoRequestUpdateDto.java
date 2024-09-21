package br.com.fiap.ecommerce.dtos;

import br.com.fiap.ecommerce.model.Produto;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ProdutoRequestUpdateDto {
    private Long id;
	private String nome;
    private static final ModelMapper modelMapper = new ModelMapper();

    public Produto toModel(ProdutoRequestUpdateDto dto) {
        return modelMapper.map(dto, Produto.class);
    }
}
