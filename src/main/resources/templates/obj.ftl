package com.cpic.life.common.operation.center.infrastructure.interfaceVo.${className};

import com.cpic.life.common.operation.center.infrastruct ure.protocol.fixed.length.annotation.FixedLengthProtocol;
import com.cpic.life.common.operation.center.infrastructur e protocol.fixed.length.annotation.FixedLengthProtocolProperty;
import lombok.Data;


@Data
@FixedLengthProtocol(charset = "gbk", countLengthByBytes = true)
public class ${className} {

    <#list tableClassList as tc>
        /**
        * ${tc.desc}
        */
        @FixedLengthProtocolProperty(length = ${tc.strLength})
        private String ${tc.name};
    </#list>

}
