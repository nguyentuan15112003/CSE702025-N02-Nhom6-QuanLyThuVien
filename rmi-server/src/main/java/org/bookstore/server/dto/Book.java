package org.bookstore.server.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String title;
    private String author;
    private String publisher;
    private Date publicationDate;
    private String category;
    private BigDecimal price;
    private Integer quantity;

    public static Book mapToBook(Object[] row) {
        return Book.builder()
                .id((Integer) row[0])
                .title((String) row[1])
                .author((String) row[2])
                .publisher((String) row[3])
                .publicationDate((Date) row[4])
                .category((String) row[5])
                .price((BigDecimal) row[6])
                .quantity((Integer) row[7])
                .build();
    }
}
