package com.ftl.ftl2object;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.ftl.ftl2object.entity.TableClass;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Ftl2objectApplicationTests {


    @Autowired
    Configuration freeMarker;

    @Test
    void contextLoads() throws IOException, TemplateException {

        String excelPath = "C:\\Users\\fang\\Documents\\工作簿1.xlsx";
        File excelFile = new File(excelPath);
        if(!excelFile.exists()){
            System.out.println("该excel不存在！！！");
        }
        String name = FileNameUtil.mainName(new File(excelPath));

        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(excelPath));
//        List<Map<String, Object>> mapList = reader.readAll();
        List<List<Object>> readAll = reader.read();

        List<TableClass> tableClassList = new ArrayList<>();
        for (int i = 0; i < readAll.size(); i++) {
            List<Object> list = readAll.get(i);
            TableClass tc = new TableClass();
            tc.setDesc(list.get(1).toString());
            tc.setName(list.get(2).toString());
            tc.setStrLength(list.get(4).toString());
            tableClassList.add(tc);
        }
        Template template = freeMarker.getTemplate("obj.ftl");
        // 渲染模板
        String savePath = "C:\\fang1\\"+name+"_"+System.currentTimeMillis()+".txt";
        File saveFile = new File(savePath);
        if(!saveFile.getParentFile().exists()){
            saveFile.getParentFile().mkdirs();
        }

        if(!saveFile.exists()){
            saveFile.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(savePath);
        // freemarker 引擎将动态数据绑定的模板并导出为文件
        Map map = new HashMap(2);
        map.put("className",name);
        map.put("tableClassList",tableClassList);
        template.process(map, fileWriter);
        System.out.println("生成成功 路径：" + savePath);

    }

}
