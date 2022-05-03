package com.example.blogit.blog;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogMapper {
    BlogMapper INSTANCE = Mappers.getMapper(BlogMapper.class);

//    Blog blogDtoToBlog(BlogDto blogDto);

    @Mapping(source = "bannerImg", target = "banner")
    @Mapping(source = "author", target = "author")
    BlogDto blogToBlogDto(Blog blog);
}
