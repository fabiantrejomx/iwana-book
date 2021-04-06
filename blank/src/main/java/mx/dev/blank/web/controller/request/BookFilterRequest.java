package mx.dev.blank.web.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class BookFilterRequest {

    private int price;
    private int minPrice;
    private int maxPrice;
    private int pagesNumber;
    private String nameOrLastname;
    private String category;
    private Date publicationDate;
    private Date initialPublicationDate;
    private Date finalPublicationDate;


}
