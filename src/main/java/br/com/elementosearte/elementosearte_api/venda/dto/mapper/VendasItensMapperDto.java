package br.com.elementosearte.elementosearte_api.venda.dto.mapper;

import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
import br.com.elementosearte.elementosearte_api.venda.VendasItensEntity;
import br.com.elementosearte.elementosearte_api.venda.dto.VendasItemRequestDto;
import br.com.elementosearte.elementosearte_api.venda.dto.VendasItensResponseDto;
import br.com.elementosearte.elementosearte_api.venda.dto.VendasResponseDto;
import org.springframework.stereotype.Component;

@Component
public class VendasItensMapperDto {

    public VendasItensEntity toEntity(VendasItemRequestDto vendasItemRequestDto, ProdutoEntity produto) {
        VendasItensEntity vendaItemEntity = new VendasItensEntity();
        vendaItemEntity.setProduto(produto);
        vendaItemEntity.setQuantidade(vendasItemRequestDto.getQuantidade());
        vendaItemEntity.setValorUnitario(vendasItemRequestDto.getValorUnitario());
        vendaItemEntity.setTipoDesconto(vendasItemRequestDto.getTipoDesconto());
        vendaItemEntity.setValorDesconto(vendasItemRequestDto.getValorDesconto());
        vendaItemEntity.setSubTotal(vendasItemRequestDto.getSubTotal());
        return vendaItemEntity;
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
