package de.othr.ajp.annotations;


import java.lang.annotation.Annotation;

@Review(reviewers = {"John Doe", "Max Mustermann"}, criticality = Criticality.SEVERE)
public class SampleAnnotatedClass {
    // TODO method and PublishOnline-annotation

    public static void main(String[] args) throws ClassNotFoundException {
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




    }
}
