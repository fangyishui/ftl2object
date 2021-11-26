package com.ftl.ftl2object.controller.service;


import com.ftl.ftl2object.entity.TableClass;

import java.util.List;

public interface GenCodeService {


    String genCode(List<TableClass> tableClassList, String realPath);

}