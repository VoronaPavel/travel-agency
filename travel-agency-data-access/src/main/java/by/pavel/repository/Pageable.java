package by.pavel.repository;

public class Pageable {

    private final int pageNumber;
    private final int pageSize;

    private Pageable(Builder builder) {
        this.pageNumber = builder.pageNumber;
        this.pageSize = builder.pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getOffset() {
        return pageSize * (pageNumber - 1);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private int pageNumber;
        private int pageSize;

        public Builder setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        public Builder setPageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Pageable build() {
            return new Pageable(this);
        }
    }
}
