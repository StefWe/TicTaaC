package com.github.rusakovichma.tictaac.engine.el.expression;

import com.github.rusakovichma.tictaac.engine.el.EvaluationContext;

public class OrExpression extends CompoundExpression {

    public OrExpression(EvaluationContext context) {
        super(context);
    }

    @Override
    public void interpret(EvaluationContext context) {
        exprOne.interpret(context);
        exprAnother.interpret(context);

        boolean oneResult = (Boolean) context.getEvaluationResult(exprOne);
        boolean anotherResult = (Boolean) context.getEvaluationResult(exprAnother);

        context.addEvaluationResult(this, oneResult || anotherResult);
    }
}