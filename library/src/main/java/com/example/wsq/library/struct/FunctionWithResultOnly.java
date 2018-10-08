package com.example.wsq.library.struct;

/**
 * Created by wsq on 2018/1/20.
 */

public abstract class FunctionWithResultOnly<Result> extends Function{


    public FunctionWithResultOnly(String functionName) {
        super(functionName);
    }


    public abstract Result function();
}
