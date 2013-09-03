package org.mattensoft.places;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.inject.Named;
import org.modelmapper.internal.util.Assert;
import org.resthub.common.util.PostInitialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class EmbeddedMongoInitializer {

    static final Logger LOG = LoggerFactory.getLogger(EmbeddedMongoInitializer.class);
    @Inject
    private Mongo mongo;

    @PostInitialize(order = 1)
    public void init() throws UnknownHostException, IOException {
        if (mongoAvailable()) {
            LOG.info("no mongo instance running, starting an embedded instance...");
            startEmbeddedMongo();
            LOG.info("finished starting embedded instance");
        }
    }

    private void startEmbeddedMongo() throws UnknownHostException, IOException {
        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(mongo.getAddress().getPort(), Network.localhostIsIPv6()))
                .build();

        MongodStarter runtime = MongodStarter.getDefaultInstance();
        MongodExecutable mongodExecutable = runtime.prepare(mongodConfig);
        MongodProcess mongod = mongodExecutable.start();
        Assert.isTrue(mongoAvailable());
    }

    private boolean mongoAvailable() {
        boolean available = false;
        try {
            mongo.getDatabaseNames();
        } catch (MongoException e) {
            if (e.getCause() instanceof ConnectException) {
                available = true;
            } else {
                throw e;
            }
        }
        return available;
    }
}
