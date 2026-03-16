package md.project.library.service.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import md.project.library.domain.Author;
import md.project.library.domain.Book;
import md.project.library.service.dto.AuthorDTO;
import md.project.library.service.dto.BookDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Author} and its DTO {@link AuthorDTO}.
 */
@Mapper(componentModel = "spring")
public interface AuthorMapper extends EntityMapper<AuthorDTO, Author> {
    @Mapping(target = "books", source = "books", qualifiedByName = "bookNameSet")
    AuthorDTO toDto(Author s);

    @Mapping(target = "books", ignore = true)
    @Mapping(target = "removeBook", ignore = true)
    Author toEntity(AuthorDTO authorDTO);

    @Named("bookName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    BookDTO toDtoBookName(Book book);

    @Named("bookNameSet")
    default Set<BookDTO> toDtoBookNameSet(Set<Book> book) {
        return book.stream().map(this::toDtoBookName).collect(Collectors.toSet());
    }
}
