package br.com.fiap.ecommerce.dtos;

import br.com.fiap.ecommerce.model.Produto;
import lombok.Getter;
import lombok.Setter;
import static br.com.fiap.ecommerce.dtos.been.Utilities.modelMapper;


@Getter
@Setter
public class ProdutoRequestUpdateDto {
    private Long id;
	private String nome;

    public Produto toModel(Long id) {
        Produto result = modelMapper.map(this, Produto.class);
        result.setId(id);
        return result;
    }
}
