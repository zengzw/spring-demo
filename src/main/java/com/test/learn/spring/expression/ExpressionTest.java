package com.test.learn.spring.expression;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author zengzw
 * @date 2019-06-04 14:09:58
 */
public class ExpressionTest {

    private static final String DEFAULT_SUBJECT = "#{application.name} (#{application.id}) is #{to.status}";


    public static void main(String[] args) {
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("application.name","ExpressTest");
        context.setVariable("application.id","99009");

        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(DEFAULT_SUBJECT, ParserContext.TEMPLATE_EXPRESSION);
        System.out.println(expression.getValue());

    }
}
