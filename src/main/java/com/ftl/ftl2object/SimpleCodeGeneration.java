package com.ftl.ftl2object;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.ftl.ftl2object.entity.TableClass;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleCodeGeneration {



    public static void main(String[] args) throws IOException, TemplateException {

        String excelPath = "C:\\Users\\fang\\Documents\\工作簿1.xlsx";

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

        // freemarker 配置
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("UTF-8");
        // 指定模板的路径
        configuration.setDirectoryForTemplateLoading(new File("classpath:templates"));
        // 根据模板名称获取路径下的模板
        Template template = configuration.getTemplate("obj.ftl");
        // 渲染模板
        FileWriter fileWriter = new FileWriter("C:\\Users\\fang\\Desktop\\1.txt");
        // freemarker 引擎将动态数据绑定的模板并导出为文件
        Map map = new HashMap(2);
        map.put("className","txt111");
        map.put("tableClassList",tableClassList);
        template.process(map, fileWriter);
        System.out.println("生成成功 路径");
    }


}
