package com.wq.glide.compiler;

import com.wq.glide.annotation.compiler.OnClick;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.VariableElement;

public class OnClickMethod {

    private final ExecutableElement methodElement;
    final int[] ids;
    private final Name methodSimpleName;

    public OnClickMethod(Element element) {
        if (element.getKind()!= ElementKind.METHOD){
            throw new IllegalArgumentException("invalid element");
        }

        methodElement = (ExecutableElement) element;
        this.ids = methodElement.getAnnotation(OnClick.class).value();
        methodSimpleName = methodElement.getSimpleName();
        List<? extends VariableElement> parameters = methodElement.getParameters();
        System.out.println("parameters size: "+parameters.size());
        if (parameters.size() != 1) {
            throw new IllegalArgumentException("this method must have one parameters");
        }
    }

    public Name getMethodName() {
        return methodSimpleName;
    }
}
