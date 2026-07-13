package br.com.elementosearte.elementosearte_api.venda.dto.mapper;


import br.com.elementosearte.elementosearte_api.venda.VendasEntity;
import br.com.elementosearte.elementosearte_api.venda.dto.VendasRequestDto;
import br.com.elementosearte.elementosearte_api.venda.dto.VendasResponseDto;
import org.springframework.stereotype.Component;

@Component
public class VendasMapperDto {

    public VendasEntity toEntity(VendasRequestDto requestDto) {
        VendasEntity entity = new VendasEntity();
        entity.setFormaPagamento(requestDto.getFormaPagamento());
        return entity;
    }

    public VendasResponseDto toResponseDto(VendasEntity entity) {
        VendasResponseDto responseDto = new VendasResponseDto();
        responseDto.setIdVenda(entity.getIdVenda());
        responseDto.setIdUsuario(entity.getUsuario().getIdUsuario());
        responseDto.setNomeUsuario(entity.getUsuario().getNome());
        responseDto.setFormaPagamento(entity.getFormaPagamento());
        responseDto.setValorTotal(entity.getValorTotal());
        return responseDto;
    }
}
