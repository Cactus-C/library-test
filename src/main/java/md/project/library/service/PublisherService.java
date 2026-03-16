package md.project.library.service;

import java.util.Optional;
import md.project.library.domain.Publisher;
import md.project.library.repository.PublisherRepository;
import md.project.library.service.dto.PublisherDTO;
import md.project.library.service.mapper.PublisherMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link md.project.library.domain.Publisher}.
 */
@Service
@Transactional
public class PublisherService {

    private static final Logger LOG = LoggerFactory.getLogger(PublisherService.class);

    private final PublisherRepository publisherRepository;

    private final PublisherMapper publisherMapper;

    public PublisherService(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    /**
     * Save a publisher.
     *
     * @param publisherDTO the entity to save.
     * @return the persisted entity.
     */
    public PublisherDTO save(PublisherDTO publisherDTO) {
        LOG.debug("Request to save Publisher : {}", publisherDTO);
        Publisher publisher = publisherMapper.toEntity(publisherDTO);
        publisher = publisherRepository.save(publisher);
        return publisherMapper.toDto(publisher);
    }

    /**
     * Update a publisher.
     *
     * @param publisherDTO the entity to save.
     * @return the persisted entity.
     */
    public PublisherDTO update(PublisherDTO publisherDTO) {
        LOG.debug("Request to update Publisher : {}", publisherDTO);
        Publisher publisher = publisherMapper.toEntity(publisherDTO);
        publisher = publisherRepository.save(publisher);
        return publisherMapper.toDto(publisher);
    }

    /**
     * Partially update a publisher.
     *
     * @param publisherDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PublisherDTO> partialUpdate(PublisherDTO publisherDTO) {
        LOG.debug("Request to partially update Publisher : {}", publisherDTO);

        return publisherRepository
            .findById(publisherDTO.getId())
            .map(existingPublisher -> {
                publisherMapper.partialUpdate(existingPublisher, publisherDTO);

                return existingPublisher;
            })
            .map(publisherRepository::save)
            .map(publisherMapper::toDto);
    }

    /**
     * Get all the publishers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PublisherDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Publishers");
        return publisherRepository.findAll(pageable).map(publisherMapper::toDto);
    }

    /**
     * Get one publisher by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PublisherDTO> findOne(Long id) {
        LOG.debug("Request to get Publisher : {}", id);
        return publisherRepository.findById(id).map(publisherMapper::toDto);
    }

    /**
     * Delete the publisher by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Publisher : {}", id);
        publisherRepository.deleteById(id);
    }
}
