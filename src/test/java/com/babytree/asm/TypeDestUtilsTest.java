package com.babytree.asm;

import org.junit.Assert;
import org.junit.Test;

import static com.babytree.babyeye.asm.utils.TypeDescUtils.getMethodParamsDesc;


public class TypeDestUtilsTest {

    @Test
    public void test() {
        Assert.assertEquals(getMethodParamsDesc("()V"),"");
        Assert.assertEquals(getMethodParamsDesc("(IF)V"),"int, float");
        Assert.assertEquals(getMethodParamsDesc("(Ljava/lang/Object;)I"),"Object");
        Assert.assertEquals(getMethodParamsDesc("(ILjava/lang/String;)[I"),"int, String");
        Assert.assertEquals(getMethodParamsDesc("(ILjava/lang/Map;)[I"),"int, Map");
        Assert.assertEquals(getMethodParamsDesc("([I)Ljava/lang/Object;"),"int[]");
        Assert.assertEquals(getMethodParamsDesc("([ILjava/lang/Object;[Ljava/lang/Object;[Ljava/lang/String;)Ljava/lang/Object;"),"int[], Object, Object[], String[]");
        Assert.assertEquals(getMethodParamsDesc("([[ILjava/lang/Object;[[[Ljava/lang/Object;[[[[[Ljava/lang/String;)Ljava/lang/Object;"),"int[][], Object, Object[][][], String[][][][][]");
    }

}
