package books;

import employees.FirstCharacterIsUppercase;

public record BookDto(long id, @FirstCharacterIsUppercase String title) {
}
