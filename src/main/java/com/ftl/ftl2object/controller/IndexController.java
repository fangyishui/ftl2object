package com.ftl.ftl2object.controller;

import com.ftl.ftl2object.entity.PageData;
import com.ftl.ftl2object.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 在是一个普通的 Controller 类
 * */
@Controller
public class IndexController {


    /**
     * 路由 /index
     * 返回 index 这里默认配置自动映射到 templages/index
     * */
    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("welcome","hello fishpro");
        return "index";
    }

    /**
     * 导出报表
     *
     * @return
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取数据
//        List<PageData> list = userService.serviceList();
        List<PageData> list = new ArrayList<>();
        
        PageData pageData = new PageData();
        pageData.setId(10L);
        pageData.setUsername("Tom");
        pageData.setPassword("123");
        pageData.setPhone("456");
        pageData.setCreateTime(new Date());



        list.add(pageData);


        //excel标题
        String[] title = {"用户ID", "用户名称", "用户密码", "用户手机","创建时间"};

        //excel文件名
        String fileName = "用户信息表" + System.currentTimeMillis() + ".xls";

        //sheet名
        String sheetName = "用户信息表";

        String [][] content = new String[list.size()][5];

        for (int i = 0; i < list.size(); i++) {
            content[i] = new String[title.length];
            PageData obj = list.get(i);
            content[i][0] = obj.getId().toString();
            content[i][1] = obj.getUsername();
            content[i][2] = obj.getPassword();
            content[i][3] = obj.getPhone();
            content[i][4] = obj.getCreateTime().toString();
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook(sheetName, title, content, null);

        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送响应流方法
     */
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
