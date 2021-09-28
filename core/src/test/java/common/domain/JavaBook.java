package common.domain;

public class JavaBook extends Book {

    private String future;

    private String version;

    @Override
    public Integer getPrice() {
        return super.getPrice();
    }

    @Override
    public JavaBook setPrice(Integer price) {
        super.setPrice(price);
        return this;
    }

    @Override
    public String getAuthor() {
        return super.getAuthor();
    }

    @Override
    public JavaBook setAuthor(String author) {
        super.setAuthor(author);
        return this;
    }

//    @Override
//    public String toString() {
//        return
//    }


    @Override
    public String toString() {
        return "JavaBook{" +
                "price='" + super.getPrice() + '\'' +
                ", author='" + super.getAuthor() + '\'' +
                ", future='" + future + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

    public String getFuture() {
        return future;
    }

    public JavaBook setFuture(String future) {
        this.future = future;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public JavaBook setVersion(String version) {
        this.version = version;
        return this;
    }
}
