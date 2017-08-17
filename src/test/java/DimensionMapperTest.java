import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ep.dao.mapper.ComplainMapper;
import com.ep.dao.model.complain.Dimension;

/**
 * Created by MengWeiBo on 2017-08-09
 */
public class DimensionMapperTest extends AbstractMapperTest {

    @Autowired
    private ComplainMapper mapper;

//    @Test
    public void  select(){
      List<Dimension> list =  mapper.selectDimension();
        System.out.println(list.size());
    }

}
