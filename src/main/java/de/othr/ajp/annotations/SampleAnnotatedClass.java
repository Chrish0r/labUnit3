package de.othr.ajp.annotations;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Review(reviewers = {"John Doe", "Max Mustermann"}, criticality = Criticality.SEVERE)
public class SampleAnnotatedClass {

    @PublishOnline(value = "http://othr.de/ajp/services/testresult")
    public double getTestResultByStudentId(String studentId) {
        return 0.0;
    }
    @PublishOnline()
    public String getHelpText() {
        return "helping text";
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        // setting up reflection
        Class<?> clazz = Class.forName("de.othr.ajp.annotations.SampleAnnotatedClass");
        System.out.println("Annotations on class:");
        // getting the annotation values
        Annotation[] annotations = clazz.getAnnotations();
        String indent = "   ";

        for(Annotation annotation : annotations) {
            System.out.println("@" + annotation.annotationType().getSimpleName() + "(");
            if(annotation instanceof Review) {
                Review reviewAnnotation = (Review) annotation;
                System.out.print(indent + "reviewers = [");
                for(int i = 0; i < reviewAnnotation.reviewers().length; i++) {
                    if(i != reviewAnnotation.reviewers().length - 1) {
                        System.out.print(reviewAnnotation.reviewers()[i] + ", ");
                        continue;
                    }
                    System.out.println(reviewAnnotation.reviewers()[i] + "]");
                }
                    System.out.println(indent + "criticality = " + reviewAnnotation.criticality());
            }
            System.out.println(")");
        }

        // annotations in regard to methods
        System.out.println();
        System.out.println("Annotations on methods:");

        Method methodStudentId = clazz.getDeclaredMethod("getTestResultByStudentId", String.class);
        Annotation[] annotationsMethodStudentId = methodStudentId.getAnnotations();
        System.out.println(methodStudentId.getName());
        int i = 0;
        System.out.println("@" + annotationsMethodStudentId[i].annotationType().getSimpleName() + "(");
        for(i = 0; i < annotationsMethodStudentId.length; i++) {
            if(annotationsMethodStudentId[i] instanceof PublishOnline) {
                PublishOnline publishOnlineAnnotation1 = (PublishOnline) annotationsMethodStudentId[i];
                System.out.println(indent + "value = " + publishOnlineAnnotation1.value());
            }
        }
        System.out.println(")");

        Method methodGetHelpText = clazz.getDeclaredMethod("getHelpText");
        Annotation[] annotationsGetHelpText = methodGetHelpText.getAnnotations();
        System.out.println(methodGetHelpText.getName());

        for(Annotation annotation : annotationsGetHelpText) {
            System.out.println("@" + annotation.annotationType().getSimpleName() + "(");
            if(annotation instanceof PublishOnline) {
                PublishOnline publishOnlineAnnotation2 = (PublishOnline) annotation;
                System.out.println(indent + "value = " + ((PublishOnline) annotation).value());
            }
        }
        System.out.println(")");
    }
}
