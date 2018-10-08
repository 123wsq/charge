package com.example.wsq.library.struct;

/**
 * Created by wsq on 2018/1/20.
 */

public abstract class FunctionWithParamAndResult<Result, Param> extends Function{


    public FunctionWithParamAndResult(String functionName) {
        super(functionName);
    }


    public abstract Result function(Param data);
}
