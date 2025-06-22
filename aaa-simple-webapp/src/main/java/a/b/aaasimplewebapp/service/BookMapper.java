package a.b.aaasimplewebapp.service;

import a.b.aaasimplewebapp.domain.Book;
import a.b.aaasimplewebapp.repo.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
  BookMapper INSTANCE = Mappers.getMapper( BookMapper.class );

  Book toDomain(BookEntity bookEntity);

  BookEntity toEntity(Book book);
}
