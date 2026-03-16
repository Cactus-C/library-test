package md.project.library.service.mapper;

import static md.project.library.domain.PublisherAsserts.*;
import static md.project.library.domain.PublisherTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PublisherMapperTest {

    private PublisherMapper publisherMapper;

    @BeforeEach
    void setUp() {
        publisherMapper = new PublisherMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getPublisherSample1();
        var actual = publisherMapper.toEntity(publisherMapper.toDto(expected));
        assertPublisherAllPropertiesEquals(expected, actual);
    }
}
