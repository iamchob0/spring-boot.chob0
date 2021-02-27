package com.iam.chobo.domain.board.dto;

import javax.validation.constraints.NotEmpty;

import com.iam.chobo.domain.board.dao.BoardDao;

import org.modelmapper.ModelMapper;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BoardDto {
    @Getter(value = lombok.AccessLevel.NONE)
    @Setter(value = lombok.AccessLevel.NONE)
    private ModelMapper modelMapper = new ModelMapper();

    @NotEmpty
    private String title;
    private String contents;

    public BoardDao getVO() {
        return modelMapper.map(this, BoardDao.class);
    }
}
