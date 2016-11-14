package com.mrpan.vpsplatform.web.utils;

import com.mrpan.vpsplatform.web.freemarker.MustBooleanException;
import com.mrpan.vpsplatform.web.freemarker.MustNumberException;
import com.mrpan.vpsplatform.web.freemarker.MustSplitNumberException;
import com.mrpan.vpsplatform.web.freemarker.MustStringException;
import freemarker.core.Environment;
import freemarker.template.*;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by mrpan on 2016/11/14.
 */
public class DirectiveUtils {/**
 * 输出参数：对象数据
 */
public static final String OUT_BEAN = "tag_bean";
    /**
     * 输出参数：列表数据
     */
    public static final String OUT_LIST = "tag_list";
    /**
     * 输出参数：分页数据
     */
    public static final String OUT_PAGINATION = "tag_pagination";

    /**
     * 将params的值复制到variable中
     *
     * @param env
     * @param params
     * @return 原Variable中的值
     * @throws TemplateException
     */
    public static Map<String, TemplateModel> addParamsToVariable(Environment env, Map<String, TemplateModel> params) throws TemplateException {
        Map<String, TemplateModel> origMap = new HashMap<String, TemplateModel>();
        if (params.size() <= 0) {
            return origMap;
        }
        Set<Map.Entry<String, TemplateModel>> entrySet = params.entrySet();
        String key;
        TemplateModel value;
        for (Map.Entry<String, TemplateModel> entry : entrySet) {
            key = entry.getKey();
            value = env.getVariable(key);
            if (value != null) {
                origMap.put(key, value);
            }
            env.setVariable(key, entry.getValue());
        }
        return origMap;
    }

    /**
     * 将variable中的params值移除
     *
     * @param env
     * @param params
     * @param origMap
     * @throws TemplateException
     */
    public static void removeParamsFromVariable(Environment env, Map<String, TemplateModel> params, Map<String, TemplateModel> origMap) throws TemplateException {
        if (params.size() <= 0) {
            return;
        }
        for (String key : params.keySet()) {
            env.setVariable(key, origMap.get(key));
        }
    }

    public static String getString(String name, Map<String, TemplateModel> params) throws TemplateException {
        TemplateModel model = params.get(name);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateScalarModel) {
            return ((TemplateScalarModel) model).getAsString();
        } else if ((model instanceof TemplateNumberModel)) {
            return ((TemplateNumberModel) model).getAsNumber().toString();
        } else {
            throw new MustStringException(name);
        }
    }

    public static Long getLong(String name, Map<String, TemplateModel> params) throws TemplateException {
        TemplateModel model = params.get(name);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateScalarModel) {
            String s = ((TemplateScalarModel) model).getAsString();
            if (StringUtils.isBlank(s)) {
                return null;
            }
            try {
                return Long.parseLong(s);
            } catch (NumberFormatException e) {
                throw new MustNumberException(name);
            }
        } else if (model instanceof TemplateNumberModel) {
            return ((TemplateNumberModel) model).getAsNumber().longValue();
        } else {
            throw new MustNumberException(name);
        }
    }

    public static Integer getInt(String name, Map<String, TemplateModel> params) throws TemplateException {
        TemplateModel model = params.get(name);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateScalarModel) {
            String s = ((TemplateScalarModel) model).getAsString();
            if (StringUtils.isBlank(s)) {
                return null;
            }
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                throw new MustNumberException(name);
            }
        } else if (model instanceof TemplateNumberModel) {
            return ((TemplateNumberModel) model).getAsNumber().intValue();
        } else {
            throw new MustNumberException(name);
        }
    }

    public static Integer[] getIntArray(String name, Map<String, TemplateModel> params) throws TemplateException {
        String str = DirectiveUtils.getString(name, params);
        if (StringUtils.isBlank(str)) {
            return null;
        }
        String[] arr = StringUtils.split(str, ',');
        Integer[] ids = new Integer[arr.length];
        int i = 0;
        try {
            for (String s : arr) {
                ids[i++] = Integer.valueOf(s);
            }
            return ids;
        } catch (NumberFormatException e) {
            throw new MustSplitNumberException(name, e);
        }
    }

    public static Boolean getBool(String name, Map<String, TemplateModel> params) throws TemplateException {
        TemplateModel model = params.get(name);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateBooleanModel) {
            return ((TemplateBooleanModel) model).getAsBoolean();
        } else if (model instanceof TemplateNumberModel) {
            return !(((TemplateNumberModel) model).getAsNumber().intValue() == 0);
        } else if (model instanceof TemplateScalarModel) {
            String s = ((TemplateScalarModel) model).getAsString();
            // 空串应该返回null还是true呢？
            if (!StringUtils.isBlank(s)) {
                return !(s.equals("0") || s.equalsIgnoreCase("false") || s.equalsIgnoreCase("f"));
            } else {
                return null;
            }
        } else {
            throw new MustBooleanException(name);
        }
    }

    /*
     * public static Date getDate(String name, Map<String, TemplateModel>
     * params) throws TemplateException { TemplateModel model =
     * params.get(name); if (model == null) { return null; } if (model
     * instanceof TemplateDateModel) { return ((TemplateDateModel)
     * model).getAsDate(); } else if (model instanceof TemplateScalarModel) {
     * DateTypeEditor editor = new DateTypeEditor();
     * editor.setAsText(((TemplateScalarModel) model).getAsString()); return
     * (Date) editor.getValue(); } else { throw new MustDateException(name); } }
     */
    public static Set<String> getKeysByPrefix(String prefix, Map<String, TemplateModel> params) {
        Set<String> keys = params.keySet();
        Set<String> startWithPrefixKeys = new HashSet<String>();
        if (keys == null) {
            return null;
        }
        for (String key : keys) {
            if (key.startsWith(prefix)) {
                startWithPrefixKeys.add(key);
            }
        }
        return startWithPrefixKeys;
    }
}
