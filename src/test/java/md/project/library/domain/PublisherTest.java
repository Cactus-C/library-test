package md.project.library.domain;

import static md.project.library.domain.PublisherTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import md.project.library.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PublisherTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Publisher.class);
        Publisher publisher1 = getPublisherSample1();
        Publisher publisher2 = new Publisher();
        assertThat(publisher1).isNotEqualTo(publisher2);

        publisher2.setId(publisher1.getId());
        assertThat(publisher1).isEqualTo(publisher2);

        publisher2 = getPublisherSample2();
        assertThat(publisher1).isNotEqualTo(publisher2);
    }
}
