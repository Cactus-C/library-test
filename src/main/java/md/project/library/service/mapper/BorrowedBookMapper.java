package md.project.library.service.mapper;

import md.project.library.domain.Book;
import md.project.library.domain.BorrowedBook;
import md.project.library.domain.Client;
import md.project.library.service.dto.BookDTO;
import md.project.library.service.dto.BorrowedBookDTO;
import md.project.library.service.dto.ClientDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BorrowedBook} and its DTO {@link BorrowedBookDTO}.
 */
@Mapper(componentModel = "spring")
public interface BorrowedBookMapper extends EntityMapper<BorrowedBookDTO, BorrowedBook> {
    @Mapping(target = "book", source = "book", qualifiedByName = "bookName")
    @Mapping(target = "client", source = "client", qualifiedByName = "clientLastName")
    BorrowedBookDTO toDto(BorrowedBook s);

    @Named("bookName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    BookDTO toDtoBookName(Book book);

    @Named("clientLastName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "lastName", source = "lastName")
    ClientDTO toDtoClientLastName(Client client);
}
