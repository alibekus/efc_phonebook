package kz.akbar.efc.phonebook.model;


import kz.akbar.efc.phonebook.util.DateTimeUtil;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_request_log")
public class UserRequest extends AbstractBaseEntity {

    @Column(name = "user_id")
    private int userId;

    @Column(name = "req_datetime")
    @DateTimeFormat(pattern = DateTimeUtil.LOG_DATE_TIME_PATTERN)
    private LocalDateTime requestDatetime;

    //@Enumerated(EnumType.STRING)
    //@ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "req_param")
    @NotNull
    private String reqParam;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int clientId) {
        this.userId = clientId;
    }

    public LocalDateTime getRequestDatetime() {
        return requestDatetime;
    }

    public void setRequestDatetime(LocalDateTime requestDatetime) {
        this.requestDatetime = requestDatetime;
    }

    public String getReqParam() {
        return reqParam;
    }

    public void setReqParam(String reqParams) {
        this.reqParam = reqParams;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "userId=" + userId +
                ", requestDatetime=" + requestDatetime +
                ", reqParams='" + reqParam + '\'' +
                ", id=" + id +
                '}';
    }
}
