package kz.akbar.efc.phonebook.web.controller;

import kz.akbar.efc.phonebook.model.Client;
import kz.akbar.efc.phonebook.model.DataGrid;
import kz.akbar.efc.phonebook.service.ClientService;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ClientController.REST_URL)
public class ClientController {

    static final String REST_URL = "/phonebook/client";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ClientService service;

    @GetMapping("/all")
    public List<Client> getAllClients() {
        log.info("getAll");
        return service.getAll();
    }

    @GetMapping("/id/{id}")
    public Client get(@PathVariable int id) {
        log.info("get client with id = {}", id);
        return service.get(id);
    }

    @GetMapping("/iin/{iin}")
    public Client getByIin(@PathVariable String iin) {
        log.info("get client with iin = {}", iin);
        return service.getByIin(iin);
    }

    @GetMapping("/phonenumber/{phoneNumber}")
    public Client getByPhoneNumber(@PathVariable String phoneNumber) {
        log.info("get client with phone number = {}", phoneNumber);
        return service.getByPhoneNumber(phoneNumber);
    }

    @GetMapping("/name/{name}")
    public List<Client> getByName(@PathVariable String name) {
        log.info("get clients by name = {}", name);
        return service.getByName(name);
    }

    @PostMapping
    public Client save(Client client) {
        log.info("save {}", client);
        return service.create(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(int id) {
        log.info("delete user with id = {}", id);
        service.delete(id);
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
        if (orderBy != null && orderBy.equals("birthDate")) {
            orderBy = "birthDate";
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
        Page<Client> clientPage =
                service.getAllByPage(pageRequest);
        // Сконструировать сетку, которая вернет данные в формате JSON
        DataGrid<Client> dataGrid = new DataGrid<Client>();
        dataGrid.setCurrentPage(clientPage.getNumber() + 1);
        dataGrid.setTotalPages(clientPage.getTotalPages());
        dataGrid.setTotalRecords(clientPage.getTotalElements());
        dataGrid.setData(Lists.newArrayList(clientPage.iterator()));
        return dataGrid;
    }
}