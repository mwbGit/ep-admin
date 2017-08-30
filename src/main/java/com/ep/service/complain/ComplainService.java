package com.ep.service.complain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ep.dao.mapper.ComplainMapper;
import com.ep.service.complain.api.IComplainService;

/**
 * Created by MengWeiBo on 2017-08-30
 */

@Service
public class ComplainService implements IComplainService {
    @Autowired
    private ComplainMapper complainMapper;

    @Override
    public void deleteDimension(Integer id) {
        complainMapper.deleteComplain(id, null);

        complainMapper.deleteDimensionById(id);
    }

    @Override
    public void deleteItem(Integer id) {
        complainMapper.deleteComplain(null, id);

        complainMapper.deleteDimensionByItemId(id);

        complainMapper.deleteServiceItem(id);
    }
}
