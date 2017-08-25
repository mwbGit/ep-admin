package com.ep.controller.complain;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ep.controller.common.ServiceResponse;
import com.ep.controller.complain.api.ComplainVO;
import com.ep.controller.complain.api.DimensionRequest;
import com.ep.controller.complain.api.DimensionVO;
import com.ep.controller.complain.api.ItemRequest;
import com.ep.dao.mapper.ComplainMapper;
import com.ep.dao.model.complain.*;
import com.ep.util.DownloadUtil;
import com.ep.util.POIUtil;

@Controller
@RequestMapping(value = "/complain")
public class ComplainController {

    @Resource
    private ComplainMapper complainMapper;

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> complainList(Integer typeId, Integer iDisplayStart, Integer iDisplayLength) {
        List<Complain> complains = complainMapper.selectComplain(typeId, iDisplayStart, iDisplayLength);

        int count = complainMapper.countComplain(typeId);

        Map<String, Object> map = new HashMap<>();
        map.put("aaData", ComplainVO.toVOs(complains));
        map.put("iTotalDisplayRecords", count);
        map.put("iTotalRecords", count);

        return map;
    }


    @RequestMapping(value = "/dimension/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam("itemId") Integer itemId) {
        List<Dimension> dimensions = complainMapper.selectDimension(itemId);

        Map<String, Object> map = new HashMap<>();
        map.put("aaData", DimensionVO.toVOs(dimensions));

//        map.put("recordsTotal", 1);
//        map.put("draw", 1);
//        map.put("recordsFiltered", 1);
        return map;
    }

    @RequestMapping(value = "/dimension/add")
    @ResponseBody
    public ServiceResponse add(DimensionRequest request) {
        Dimension dimension = new Dimension();
        dimension.setName(request.getName());
        dimension.setRatio(request.getRatio());
        dimension.setType(DimensionType.fromCode(request.getType()));
        dimension.setItemId(request.getItemId());

        complainMapper.insertDimension(dimension);

        return new ServiceResponse();
    }

    @RequestMapping(value = "/dimension/modify")
    @ResponseBody
    public ServiceResponse modify(DimensionRequest request) {
        Dimension dimension = new Dimension();
        dimension.setId(request.getId());
        dimension.setName(request.getName());
        dimension.setRatio(request.getRatio());
        dimension.setType(DimensionType.fromCode(request.getType()));

        complainMapper.updateDimension(dimension);

        return new ServiceResponse();
    }

    @RequestMapping(value = "/dimension/delete")
    @ResponseBody
    public ServiceResponse delete(@RequestParam("id") Integer id) {

        complainMapper.deleteDimensionById(id);

        return new ServiceResponse();
    }


    @RequestMapping(value = "/item/list")
    @ResponseBody
    public Map<String, Object> itemList() {
        Map<String, Object> map = new HashMap<>();
        List<ServiceItem> items = complainMapper.selectItem();
        map.put("data", items);
        map.put("empty", CollectionUtils.isEmpty(items));

        return map;
    }

    @RequestMapping(value = "/item/add")
    @ResponseBody
    public ServiceResponse itemAdd(ItemRequest request) {
        ServiceItem item = new ServiceItem();
        item.setName(request.getName());
        item.setRatio(request.getRatio());
        item.setType(ServiceItemType.fromCode(request.getType()));

        complainMapper.insertServiceItem(item);

        return new ServiceResponse();
    }

    @RequestMapping(value = "/item/modify")
    @ResponseBody
    public ServiceResponse itemModify(ItemRequest request) {
        ServiceItem item = new ServiceItem();
        item.setId(request.getId());
        item.setName(request.getName());
        item.setRatio(request.getRatio());
        item.setType(ServiceItemType.fromCode(request.getType()));

        complainMapper.updateServiceItem(item);

        return new ServiceResponse();
    }

    @RequestMapping(value = "/item/delete")
    @ResponseBody
    public ServiceResponse itemDelete(@RequestParam("id") Integer id) {

        complainMapper.deleteDimensionByItemId(id);
        complainMapper.deleteServiceItem(id);

        return new ServiceResponse();
    }

    @RequestMapping("/queryComplainForExcel")
    @ResponseBody
    public void queryComplainForExcel(HttpServletRequest request, HttpServletResponse response) {

        List<Complain> complains = complainMapper.selectComplain(null, null, null);

        // 数据写入Excel
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("吐槽列表");
        // 通用样式
        XSSFCellStyle style = POIUtil.crateCellStyle(wb);
        // 第一行
        XSSFRow firstRow = sheet.createRow(0);
        XSSFCell c0 = firstRow.createCell(0);
        c0.setCellValue("序号");
        c0.setCellStyle(style);
        XSSFCell c1 = firstRow.createCell(1);
        c1.setCellValue("服务项");
        c1.setCellStyle(style);
        XSSFCell c2 = firstRow.createCell(2);
        c2.setCellValue("维度名称");
        c2.setCellStyle(style);
        XSSFCell c3 = firstRow.createCell(3);
        c3.setCellValue("所属分类");
        c3.setCellStyle(style);
        XSSFCell c4 = firstRow.createCell(4);
        c4.setCellValue("评分");
        c4.setCellStyle(style);
        XSSFCell c5 = firstRow.createCell(5);
        c5.setCellValue("吐槽内容");
        c5.setCellStyle(style);

        // 写入数据
        XSSFRow row = null;
        XSSFCell cell = null;
        for (int i = 0; complains != null && i < complains.size(); i++) {
            Complain tr = complains.get(i);
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(i + 1);
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("服务项");
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(tr.getDimension().getName());
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue(tr.getDimension().getType().getDescription());
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue(tr.getGrade());
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue(tr.getContent());
            cell.setCellStyle(style);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 生成流对象
        try {
            wb.write(byteArrayOutputStream);// 将excel写入流
            // 工具类，封装弹出下载框：
            String outFile = "吐槽列表.xlsx";
            DownloadUtil down = new DownloadUtil();
            down.download(byteArrayOutputStream, request, response, outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
