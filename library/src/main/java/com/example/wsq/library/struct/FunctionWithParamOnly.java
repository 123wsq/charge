package com.example.wsq.library.struct;

/**
 * Created by wsq on 2018/1/20.
 */

public abstract class FunctionWithParamOnly<Param> extends Function{


    public FunctionWithParamOnly(String functionName) {
        super(functionName);
    }


    public abstract void function(Param data);
}
