package kz.akbar.efc.phonebook.web.controller;

import kz.akbar.efc.phonebook.model.DataGrid;
import kz.akbar.efc.phonebook.model.UserRequest;
import kz.akbar.efc.phonebook.service.UserRequestService;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = UserRequestController.REST_URL)
public class UserRequestController {

    static final String REST_URL = "/phonebook/user-request";
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final UserRequestService service;

    public UserRequestController(UserRequestService service) {
        this.service = service;
    }

    public List<UserRequest> getByUserId(long id) {
        return service.getByUserId(id);
    }

    /*
    From Chris Schaefer, Clarence Ho, Rob Harrop. Pro Spring 4. Ed.4
    For jqGrid request Ajax paging data.
     */
    @RequestMapping(value = "/clientlistgrid", method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public DataGrid listGrid(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "rows", required = false) Integer rows,
                             @RequestParam(value = "sidx", required = false) String sortBy,
                             @RequestParam(value = "sord", required = false) String order) {

        log.info("Listing clients for grid with page: {}, rows: {} ",
                page, rows);
        log.info("Listing clients for grid with sort: {}, order: {}",
                sortBy, order);
        // Обработать поле, по которому производится сортировка
        Sort sort = null;
        String orderBy = sortBy;
        if (orderBy != null && orderBy.equals("requestDatetime")) {
            orderBy = "requestDatetime";
        }
        if (orderBy != null && order != null) {
            if (order.equals("desc")) {
                sort = Sort.by(Sort.Direction.DESC, orderBy);
            } else {
                sort = Sort.by(Sort.Direction.ASC, orderBy);
            }
        }
        // Сконструировать страничный запрос для текущей страницы.
        // Примечание: нумерация страниц для Spring Data JPA начинается с О,
        // тогда как в jqGrid - с 1
        PageRequest pageRequest = null;
        if (sort != null) {
            pageRequest = PageRequest.of(page - 1, rows, sort);
        } else {
            pageRequest = PageRequest.of(page - 1, rows);
        }
        Page<UserRequest> clientPage =
                service.getAllByPage(pageRequest);
        // Сконструировать сетку, которая вернет данные в формате JSON
        DataGrid<UserRequest> dataGrid = new DataGrid<UserRequest>();
        dataGrid.setCurrentPage(clientPage.getNumber() + 1);
        dataGrid.setTotalPages(clientPage.getTotalPages());
        dataGrid.setTotalRecords(clientPage.getTotalElements());
        dataGrid.setData(Lists.newArrayList(clientPage.iterator()));
        return dataGrid;
    }
}
