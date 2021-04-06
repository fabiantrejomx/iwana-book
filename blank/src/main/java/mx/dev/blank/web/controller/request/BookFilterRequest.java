package mx.dev.blank.web.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class BookFilterRequest {

    private Date publicationDate;
    private int price;
    private int minPrice;
    private int maxPrice;
    private Date initialPublicationDate;
    private Date finalPublicationDate;

}
