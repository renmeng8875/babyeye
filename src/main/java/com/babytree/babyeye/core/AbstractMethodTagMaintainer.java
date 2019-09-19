package com.babytree.babyeye.core;

import com.babytree.babyeye.base.MethodTag;

public abstract class AbstractMethodTagMaintainer {

    public abstract int addMethodTag(MethodTag methodTag);

    public abstract MethodTag getMethodTag(int methodId);

    public abstract int getMethodTagCount();

}
