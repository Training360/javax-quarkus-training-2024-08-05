package employees;

public class NotFoundException extends RuntimeException {

    private Class clazz;

    private Long id;

    public NotFoundException(String message, Class clazz, Long id) {
        super(message);
        this.clazz = clazz;
        this.id = id;
    }

    public Class getClazz() {
        return clazz;
    }

    public Long getId() {
        return id;
    }
}
