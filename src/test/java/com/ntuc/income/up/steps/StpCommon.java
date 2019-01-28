package com.ntuc.income.up.steps;

import com.google.protobuf.Message;
import com.ntuc.income.up.model.RegisterOuterClass;
import com.ntuc.income.up.utilities.EnvironmentData;
import com.ntuc.income.up.utilities.ProtoMapper;

import java.util.List;
import java.util.Map;

public class StpCommon {

    public static <T> List<T> load_test_data(String arg1, String arg2, Message prototype) throws Throwable {
        List<Map<String, String>> excelMap = new EnvironmentData(arg1).getRowsData(arg2);
        return ProtoMapper.transposeAndCast(excelMap, prototype);
    }
}
