package meghaduta.config;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MDConfigReaderTest {

    @Test
    public void shouldReadConfig() {
        MDConfig config = MDConfigReader.load();

        assertThat(config.getStoreImpl(), is("rocksdb"));
        assertThat(config.getDBLocation(), is("/tmp/meghaduta/rocksdb-test"));
        assertThat(config.getSharedFolder(), is("shared-folder/IN"));
        assertThat(config.getNotifierImpl(), is("file"));
        assertThat(config.getNotifierFileName(), is("notifications.log"));
    }

}