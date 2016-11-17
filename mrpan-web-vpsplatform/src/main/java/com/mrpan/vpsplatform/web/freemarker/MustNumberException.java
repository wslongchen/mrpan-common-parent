package com.mrpan.vpsplatform.web.freemarker;

import freemarker.template.TemplateModelException;
/**
 * Created by mrpan on 2016/11/14.
 */

public class MustNumberException extends TemplateModelException {
    public MustNumberException(String paramName) {
        super("The \"" + paramName + "\" parameter must be a number.");
    }
}