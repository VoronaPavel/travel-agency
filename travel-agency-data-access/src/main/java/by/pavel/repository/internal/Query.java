package by.pavel.repository.internal;

import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.countMatches;

public class Query {

    private final String query;
    private final ImmutableList<Object> parameters;

    private Query(String query, List<Object> parameters) {
        this.query = query;
        this.parameters = ImmutableList.copyOf(parameters);
    }

    public String getQuery() {
        return query;
    }

    public ImmutableList<Object> getParameters() {
        return parameters;
    }

    public static Builder builder(String query) {
        return new Builder(query);
    }

    public static class Builder {

        private String query;
        private ImmutableList<Object> parameters;

        private Builder(String query) {
            this.query = query;
        }

        public Builder setParameters(Object... parameters) {
            checkArgument(countMatches(query, '?') == parameters.length, "Parameters don't match query.");

            this.parameters = ImmutableList.copyOf(parameters);
            return this;
        }

        public Query build() {
            return new Query(query, parameters);
        }
    }
}
