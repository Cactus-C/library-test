package md.project.library.service;

import java.time.Instant;
import java.util.Optional;
import md.project.library.domain.Book;
import md.project.library.domain.BorrowedBook;
import md.project.library.repository.BookRepository;
import md.project.library.repository.BorrowedBookRepository;
import md.project.library.service.dto.BorrowedBookDTO;
import md.project.library.service.mapper.BorrowedBookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link md.project.library.domain.BorrowedBook}.
 */
@Service
@Transactional
public class BorrowedBookService {

    private static final Logger LOG = LoggerFactory.getLogger(BorrowedBookService.class);

    private final BorrowedBookRepository borrowedBookRepository;
    private final BookRepository bookRepository;

    private final BorrowedBookMapper borrowedBookMapper;

    public BorrowedBookService(
        BorrowedBookRepository borrowedBookRepository,
        BookRepository bookRepository,
        BorrowedBookMapper borrowedBookMapper
    ) {
        this.borrowedBookRepository = borrowedBookRepository;
        this.bookRepository = bookRepository;
        this.borrowedBookMapper = borrowedBookMapper;
    }

    /**
     * Save a borrowedBook.
     *
     * @param borrowedBookDTO the entity to save.
     * @return the persisted entity.
     */
    public BorrowedBookDTO save(BorrowedBookDTO borrowedBookDTO) {
        LOG.debug("Request to save BorrowedBook : {}", borrowedBookDTO);
        BorrowedBook borrowedBook = borrowedBookMapper.toEntity(borrowedBookDTO);
        borrowedBook = borrowedBookRepository.save(borrowedBook);
        return borrowedBookMapper.toDto(borrowedBook);
    }

    /**
     * Update a borrowedBook.
     *
     * @param borrowedBookDTO the entity to save.
     * @return the persisted entity.
     */
    public BorrowedBookDTO update(BorrowedBookDTO borrowedBookDTO) {
        LOG.debug("Request to update BorrowedBook : {}", borrowedBookDTO);
        BorrowedBook borrowedBook = borrowedBookMapper.toEntity(borrowedBookDTO);
        borrowedBook = borrowedBookRepository.save(borrowedBook);
        return borrowedBookMapper.toDto(borrowedBook);
    }

    /**
     * Partially update a borrowedBook.
     *
     * @param borrowedBookDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BorrowedBookDTO> partialUpdate(BorrowedBookDTO borrowedBookDTO) {
        LOG.debug("Request to partially update BorrowedBook : {}", borrowedBookDTO);

        return borrowedBookRepository
            .findById(borrowedBookDTO.getId())
            .map(existingBorrowedBook -> {
                borrowedBookMapper.partialUpdate(existingBorrowedBook, borrowedBookDTO);

                return existingBorrowedBook;
            })
            .map(borrowedBookRepository::save)
            .map(borrowedBookMapper::toDto);
    }

    /**
     * Get all the borrowedBooks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BorrowedBookDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all BorrowedBooks");
        return borrowedBookRepository.findAll(pageable).map(borrowedBookMapper::toDto);
    }

    /**
     * Get all the borrowedBooks with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<BorrowedBookDTO> findAllWithEagerRelationships(Pageable pageable) {
        return borrowedBookRepository.findAllWithEagerRelationships(pageable).map(borrowedBookMapper::toDto);
    }

    /**
     * Get one borrowedBook by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BorrowedBookDTO> findOne(Long id) {
        LOG.debug("Request to get BorrowedBook : {}", id);
        return borrowedBookRepository.findOneWithEagerRelationships(id).map(borrowedBookMapper::toDto);
    }

    /**
     * Delete the borrowedBook by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete BorrowedBook : {}", id);
        borrowedBookRepository.deleteById(id);
    }

    public BorrowedBookDTO borrowBook(BorrowedBookDTO borrowedBookDTO) {
        LOG.debug("Request to borrow a Book: {} Client: {}", borrowedBookDTO.getBook().getId(), borrowedBookDTO.getClient().getId());

        Book book = bookRepository
            .findById(borrowedBookDTO.getBook().getId())
            .orElseThrow(() -> new RuntimeException("Book: " + borrowedBookDTO.getClient().getId() + "not found"));

        if (book.getCopies() == null || book.getCopies() <= 0) {
            throw new RuntimeException("There is not enough stock of books.");
        }

        book.setCopies(book.getCopies() - 1);
        bookRepository.save(book);

        borrowedBookDTO.setBorrowDate(Instant.now());

        BorrowedBook borrowedBook = borrowedBookMapper.toEntity(borrowedBookDTO);
        borrowedBook = borrowedBookRepository.save(borrowedBook);
        return borrowedBookMapper.toDto(borrowedBook);
    }

    public boolean returnBook(Long borrowedBookId) {
        LOG.debug("Request to return Book with BorrowedBook id : {}", borrowedBookId);

        if (!borrowedBookRepository.existsById(borrowedBookId)) return false;

        BorrowedBook borrowedBook = borrowedBookRepository.findById(borrowedBookId).get();

        Book book = borrowedBook.getBook();
        book.setCopies(book.getCopies() + 1);
        bookRepository.save(book);

        borrowedBookRepository.deleteById(borrowedBookId);

        return true;
    }
}
