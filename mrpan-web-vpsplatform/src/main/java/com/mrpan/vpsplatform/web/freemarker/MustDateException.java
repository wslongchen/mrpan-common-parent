package com.mrpan.vpsplatform.web.freemarker;

import freemarker.template.TemplateModelException;

/**
 * Created by mrpan on 2016/11/14.
 */
/**
 * 非布尔参数异常
 */
@SuppressWarnings("serial")
public class MustDateException extends TemplateModelException {
    public MustDateException(String paramName) {
        super("The \"" + paramName + "\" parameter must be a date.");
    }
}
