package md.project.library.service.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import md.project.library.domain.Author;
import md.project.library.domain.Book;
import md.project.library.domain.Publisher;
import md.project.library.service.dto.AuthorDTO;
import md.project.library.service.dto.BookDTO;
import md.project.library.service.dto.PublisherDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Book} and its DTO {@link BookDTO}.
 */
@Mapper(componentModel = "spring")
public interface BookMapper extends EntityMapper<BookDTO, Book> {
    @Mapping(target = "publisher", source = "publisher", qualifiedByName = "publisherName")
    @Mapping(target = "authors", source = "authors", qualifiedByName = "authorLastNameSet")
    BookDTO toDto(Book s);

    @Mapping(target = "removeAuthor", ignore = true)
    Book toEntity(BookDTO bookDTO);

    @Named("publisherName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    PublisherDTO toDtoPublisherName(Publisher publisher);

    @Named("authorLastName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "lastName", source = "lastName")
    AuthorDTO toDtoAuthorLastName(Author author);

    @Named("authorLastNameSet")
    default Set<AuthorDTO> toDtoAuthorLastNameSet(Set<Author> author) {
        return author.stream().map(this::toDtoAuthorLastName).collect(Collectors.toSet());
    }
}
