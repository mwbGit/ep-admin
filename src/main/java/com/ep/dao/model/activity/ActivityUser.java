package com.ep.dao.model.activity;

import com.ep.dao.model.user.User;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by MengWeiBo on 2017-10-12
 */
public class ActivityUser {
    private Integer id;
    private BigDecimal price;
    private Date createDate;
    private User user;
    private Activity activity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
