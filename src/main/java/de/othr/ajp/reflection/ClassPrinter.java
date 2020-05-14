package de.othr.ajp.reflection;


import java.lang.reflect.*;
import java.util.Set;
import java.util.TreeSet;

public class ClassPrinter {

    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> clazz = Class.forName("java.util.ArrayList");
        System.out.println("Information on " + clazz);
        System.out.println();

        //--------------inheritance tree---------------------------
        System.out.println("The inheritance tree of the class " + clazz.getName() + " is:");

        Set<String> inheritanceTree = getInheritanceTree(clazz);
        String indent = "";

        for(String i : inheritanceTree) {
            System.out.println(indent + i);
            indent += "   ";
        }
        System.out.println();

        //---------------fields-----------------------------------------
        Field[] fields = clazz.getDeclaredFields();
        System.out.println("The declared fields of the class " + clazz.getName() + " are:");
        for(Field f : fields) {
            System.out.println(printModifier(f.getModifiers(), clazz) + f.getType().getSimpleName() + " " + f.getName());
        }
        System.out.println();

        //-------------constructors--------------------------------------
        System.out.println("The declared constructors of the class " + clazz.getName() + " are: ");
        Constructor[] constructors = clazz.getDeclaredConstructors();

        for(Constructor c : constructors) {
            System.out.println(printModifier(c.getModifiers(), clazz) + " " + c.getName());
        }
        System.out.println();

        //---------------methods-----------------------------------------
        Method[] methods = clazz.getDeclaredMethods();
        System.out.println("The class " + clazz.getName() + " does contain following methods:");
        for(Method m : methods) {
            // TODO: long string -> needs lots of ressources -> create a StringBuilder.
           // System.out.println("Test :"   + printParametersString(m, clazz));
              System.out.println(printModifier(m.getModifiers(), clazz) + m.getReturnType().getSimpleName() + " "
                      + m.getName() + "(" + printMethodParametersString(m, clazz) + ")");
        }
   }

   //------------------logic-------------------------------------------------------------------

   public static Set<String> getInheritanceTree(Class<?> clazz) {
        Set<String> inheritanceTree = new TreeSet<>();
         getInheritanceRrecursively(clazz, inheritanceTree);
      // getInheritanceDiscursively(clazz, inheritanceTree);

        return inheritanceTree;
   }
  public static void getInheritanceRrecursively(Class<?> clazz, Set<String> inheritanceTree) {
        if(clazz.getSuperclass() != null) {
            getInheritanceRrecursively(clazz.getSuperclass(), inheritanceTree);
        }
        inheritanceTree.add(getClassString(clazz));
   }
   public static String getClassString(Class<?> superClass) {
        return  superClass.getName();
   }

   private static String printModifier(int modifierInt, Class<?> clazz) {
        String modifierStr = "";
        if(Modifier.isPublic(modifierInt)) {
            modifierStr = "public ";
        }
        else if(Modifier.isPrivate(modifierInt)) {
            modifierStr = "private ";
        }
        else if(Modifier.isProtected((modifierInt))){
            modifierStr = "protected ";
        }
        if(Modifier.isStatic(modifierInt)) {
            modifierStr += "static ";
        }
        if(Modifier.isAbstract(modifierInt)) {
            modifierStr += "abstract ";
        }
        if(Modifier.isFinal(modifierInt)) {
            modifierStr += "final ";
        }
       if(Modifier.isInterface(modifierInt)) {
           modifierStr += "interface ";
       }
       else if(clazz.equals(Class.class)) {
           modifierStr += "class ";
       }
       if(Modifier.isSynchronized(modifierInt)) {
           modifierStr += "synchronized ";
       }
       if(Modifier.isTransient(modifierInt)) {
           modifierStr += "transient ";
       }
       if(Modifier.isVolatile(modifierInt)) {
           modifierStr += "volatile ";
       }
       if(Modifier.isNative(modifierInt)) {
           modifierStr += "native ";
       }
       if(Modifier.isStrict((modifierInt))) {
           modifierStr += "strict ";
       }
        return modifierStr;
    }

    private static String printMethodParametersString(Method m, Class<?> clazz) {
       String paraString = "";
       Class[] parameterTypes = m.getParameterTypes();
       Parameter[] parameters = m.getParameters();
       /*
       if(parameterTypes.length != parameters.length) {
           // error - but here not relevant since impossible (methods exist already)
       }
        */

       for(int i = 0; i < parameters.length; i++) {
           if (i != parameters.length - 1) {
               paraString += parameterTypes[i].getSimpleName() + " " + parameters[i].getName() + ", ";
               continue;
           }
           paraString += parameterTypes[i].getSimpleName() + " " + parameters[i].getName();
       }
       return paraString;
    }

    // TODO - to continue...
}


