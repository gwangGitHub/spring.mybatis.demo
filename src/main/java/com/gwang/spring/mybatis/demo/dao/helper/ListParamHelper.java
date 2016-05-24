package com.gwang.spring.mybatis.demo.dao.helper;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

public class ListParamHelper {


    /**
     * 如果传入参数对应的value不存在，则会返回where("1=1"),表示忽略此参数的过滤
     *
     * @param params       参数MAP
     * @param paramKey     参数名称
     * @param fieldInTable 参数对应的值，要匹配的数据库字段
     * @param haveToExist  表示fieldInTable在查询时是否必须有值，如果必须有值，但paramKey对应的value为空，则返回where("1=0")
     */
    public static String preparedInSql(Map<String, Object> params, String paramKey, String fieldInTable, boolean haveToExist) {

        StringBuffer whereSql = new StringBuffer();

        Assert.isTrue(needMoreThanOneInvoke(paramKey), "paramKey[" + paramKey + "]是个复杂的数据结构,不能直接获取成list，preparedSqlWithPropertyList");

        List valueList = params.containsKey(paramKey) ? (List) params.get(paramKey) : null;

        if (CollectionUtils.isEmpty(valueList)) {
            if (haveToExist) {
                whereSql.append("1=0");
            } else {
                whereSql.append("1=1");
            }

        } else {
            whereSql.append(fieldInTable + " in (");
            for (int i = 0, size = valueList.size(); i < size; i++) {
                if (i == 0) {
                    whereSql.append("#{" + paramKey + "[" + i + "]}");
                } else {
                    whereSql.append(",").append("#{" + paramKey + "[" + i + "]}");
                }
            }
            whereSql.append(")");
        }

        return whereSql.toString();
    }


    public static String preparedInSql(Map<String, Object> params, String paramKey, String fieldInTable) {
        return preparedInSql(params, paramKey, fieldInTable, false);
    }

    /**
     * 如果是多层调用List内的值，在此方法内部判断List是否为空不合适；
     * 因为需要知道List对象的具体方法，这样就需要具体到类的类型；
     * 而具体到类的类型，不是我们此工具方法的职责。
     */
    public static String preparedSqlWithPropertyList(List valueList, String paramKey, String fieldInTable, boolean haveToExist) {

        StringBuffer whereSql = new StringBuffer();

        if (CollectionUtils.isEmpty(valueList)) {
            if (haveToExist) {
                whereSql.append("1=0");
            } else {
                whereSql.append("1=1");
            }

        } else {

            whereSql.append(fieldInTable + " in (");
            for (int i = 0, size = valueList.size(); i < size; i++) {
                if (i == 0) {
                    whereSql.append("#{" + paramKey + "[" + i + "]}");
                } else {
                    whereSql.append(",").append("#{" + paramKey + "[" + i + "]}");
                }
            }
            whereSql.append(")");
        }

        return whereSql.toString();
    }

    //TODO refactor yaoliuqing 参数paramKey,fieldITable都是String类型，有填错的风险 ； Introduce Parameter Object(295)[Martin Fowler]
    public static String preparedSqlWithPropertyList(List valueList, String paramKey, String fieldInTable) {
        return preparedSqlWithPropertyList(valueList, paramKey, fieldInTable, false);
    }

    private static boolean needMoreThanOneInvoke(String paramKey) {
        if (paramKey.contains(".")) {
            return false;
        }
        return true;
    }

}
