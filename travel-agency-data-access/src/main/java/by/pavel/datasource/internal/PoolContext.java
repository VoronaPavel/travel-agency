package by.pavel.datasource.internal;

import com.google.inject.name.Named;

import javax.inject.Inject;

class PoolContext {

    final long initialConnections;

    @Inject PoolContext(@Named("pool.connections.initial") long initialConnections) {
        this.initialConnections = initialConnections;
    }
}
