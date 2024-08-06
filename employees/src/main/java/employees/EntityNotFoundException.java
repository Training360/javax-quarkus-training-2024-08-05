package employees;

public class EntityNotFoundException extends RuntimeException {

    private Class clazz;

    private Long id;

    public EntityNotFoundException(String message, Class clazz, Long id) {
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
