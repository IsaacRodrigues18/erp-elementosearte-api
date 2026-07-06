package br.com.elementosearte.elementosearte_api.venda.dto.mapper;

import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
import br.com.elementosearte.elementosearte_api.venda.VendasEntity;
import br.com.elementosearte.elementosearte_api.venda.VendasItensEntity;
import br.com.elementosearte.elementosearte_api.venda.dto.VendasItemRequestDto;
import br.com.elementosearte.elementosearte_api.venda.dto.VendasItensResponseDto;
import br.com.elementosearte.elementosearte_api.venda.dto.VendasResponseDto;
import org.springframework.stereotype.Component;

@Component
public class VendasItensMapperDto {

    public VendasItensEntity toEntity(
            VendasItemRequestDto dto,
            ProdutoEntity produto,
            VendasEntity venda) {

        VendasItensEntity entity = new VendasItensEntity();

        entity.setProduto(produto);
        entity.setVenda(venda);

        entity.setQuantidade(dto.getQuantidade());
        entity.setValorUnitario(dto.getValorUnitario());
        entity.setTipoDesconto(dto.getTipoDesconto());
        entity.setValorDesconto(dto.getValorDesconto());
        entity.setSubTotal(dto.getSubTotal());

        return entity;
    }
        public VendasItensResponseDto toResponseDto(VendasItensEntity vendasItensEntity){
            VendasItensResponseDto vendasItensResponseDto = new VendasItensResponseDto(
                    vendasItensEntity.getIdVendaItem(),
                    vendasItensEntity.getVenda().getIdVenda(),
                    vendasItensEntity.getProduto().getIdProduto(),
                    vendasItensEntity.getProduto().getNomeProduto(),
                    vendasItensEntity.getQuantidade(),
                    vendasItensEntity.getValorUnitario(),
                    vendasItensEntity.getTipoDesconto(),
                    vendasItensEntity.getValorDesconto(),
                    vendasItensEntity.getSubTotal());

                    return vendasItensResponseDto;

        }
}
