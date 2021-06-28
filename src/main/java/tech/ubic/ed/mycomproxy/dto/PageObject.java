package tech.ubic.ed.mycomproxy.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
@Builder
public class PageObject {
    private int currentPage;
    private int pageSize;
    private int pagesCount;
    private long total;
    private String sortField;
    private Sort.Direction sortDir;
}